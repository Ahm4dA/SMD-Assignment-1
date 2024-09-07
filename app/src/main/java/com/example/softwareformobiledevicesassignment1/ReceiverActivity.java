package com.example.softwareformobiledevicesassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String senderEmail = intent.getStringExtra("senderEmail");
        String senderFullName = intent.getStringExtra("senderFullName");
        String senderContact = intent.getStringExtra("senderContact");
        String senderCountry = intent.getStringExtra("senderCountry");
        String senderAddress = intent.getStringExtra("senderAddress");

        Button nextButton = findViewById(R.id.receiverNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Email
                final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
                final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                EditText emailEditText = findViewById(R.id.receiverEmailEditText);
                String email = emailEditText.getText().toString();
                Matcher matcher = pattern.matcher(email);
                if(email.isEmpty() || !matcher.matches()){
                    Toast.makeText(getApplicationContext(), "invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Name
                EditText nameEditText = findViewById(R.id.receiverFullNameEditText);
                String fullName = nameEditText.getText().toString();
                if(fullName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "invalid Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Contact
                EditText contactEditText = findViewById(R.id.receiverContactInfoEditText);
                String contact = contactEditText.getText().toString();
                if(contact.length() != 11){
                    Toast.makeText(getApplicationContext(), "invalid Contact Information", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Country
                EditText countryEditText = findViewById(R.id.receiverCountryEditText);
                String country = countryEditText.getText().toString();
                if(country.isEmpty()){
                    Toast.makeText(getApplicationContext(), "invalid Country", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] countryList = getResources().getStringArray(R.array.country_list);
                boolean isValidCountry = Arrays.stream(countryList)
                        .map(String::toLowerCase)
                        .anyMatch(country::equals);

                if(!isValidCountry){
                    Toast.makeText(getApplicationContext(), "invalid Country", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Address
                EditText addressEditText = findViewById(R.id.receiverAddressEditText);
                String address = addressEditText.getText().toString();
                if(address.isEmpty()){
                    Toast.makeText(getApplicationContext(), "invalid Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                //re-get the country if it is lowered

                Intent newIntent = new Intent(ReceiverActivity.this, ReviewInformationActivity.class);
                newIntent.putExtra("senderEmail", senderEmail);
                newIntent.putExtra("senderFullName", senderFullName);
                newIntent.putExtra("senderContact", senderContact);
                newIntent.putExtra("senderCountry", senderCountry);
                newIntent.putExtra("senderAddress", senderAddress);

                newIntent.putExtra("receiverEmail", email);
                newIntent.putExtra("receiverFullName", fullName);
                newIntent.putExtra("receiverContact", contact);
                newIntent.putExtra("receiverCountry", country);
                newIntent.putExtra("receiverAddress", address);
                startActivity(newIntent);
            }
        });
    }
}