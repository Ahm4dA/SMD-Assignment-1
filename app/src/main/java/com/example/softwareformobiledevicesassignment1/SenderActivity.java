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

public class SenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sender);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button nextButton = findViewById(R.id.senderNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Email
                final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
                final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                EditText emailEditText = findViewById(R.id.senderEmailEditText);
                String email = emailEditText.getText().toString();
                Matcher matcher = pattern.matcher(email);
                if(email.isEmpty() || !matcher.matches()){
                    Toast.makeText(getApplicationContext(), "invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Name
                EditText nameEditText = findViewById(R.id.senderFullNameEditText);
                String fullName = nameEditText.getText().toString();
                if(fullName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "invalid Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Contact
                EditText contactEditText = findViewById(R.id.senderContactInfoEditText);
                String contact = contactEditText.getText().toString();
                if(contact.length() != 11){
                    Toast.makeText(getApplicationContext(), "invalid Contact Information", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check Country
                EditText countryEditText = findViewById(R.id.senderCountryEditText);
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
                EditText addressEditText = findViewById(R.id.senderAddressEditText);
                String address = addressEditText.getText().toString();
                if(address.isEmpty()){
                    Toast.makeText(getApplicationContext(), "invalid Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                //re-get the country if it is lowered

                Intent intent = new Intent(SenderActivity.this, ReceiverActivity.class);
                intent.putExtra("senderEmail", email);
                intent.putExtra("senderFullName", fullName);
                intent.putExtra("senderContact", contact);
                intent.putExtra("senderCountry", country);
                intent.putExtra("senderAddress", address);
                startActivity(intent);
            }
        });
    }
}