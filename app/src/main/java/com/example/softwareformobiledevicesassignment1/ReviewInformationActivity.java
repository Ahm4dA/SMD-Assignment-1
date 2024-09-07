package com.example.softwareformobiledevicesassignment1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReviewInformationActivity extends AppCompatActivity {

    private LinearLayout reviewContainer;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton faButton = findViewById(R.id.addFab);
        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewInformationActivity.this, SenderActivity.class);
                startActivity(intent);
            }
        });

        reviewContainer = findViewById(R.id.reviewContainer);
        inflater = LayoutInflater.from(this);

        addRows();
    }

    private void addRows() {
        Intent intent = getIntent();  // Corrected: Use getIntent() to retrieve the incoming intent.

        String senderEmail = intent.getStringExtra("senderEmail");
        String senderFullName = intent.getStringExtra("senderFullName");
        String senderContact = intent.getStringExtra("senderContact");
        String senderCountry = intent.getStringExtra("senderCountry");
        String senderAddress = intent.getStringExtra("senderAddress");
        String receiverEmail = intent.getStringExtra("receiverEmail");
        String receiverFullName = intent.getStringExtra("receiverFullName");
        String receiverContact = intent.getStringExtra("receiverContact");
        String receiverCountry = intent.getStringExtra("receiverCountry");
        String receiverAddress = intent.getStringExtra("receiverAddress");

        // Inflate sender row layout
        View senderRowView = inflater.inflate(R.layout.row_item_review_information, reviewContainer, false);
        TextView senderNameTextView = senderRowView.findViewById(R.id.nameTextView);
        TextView senderCountryTextView = senderRowView.findViewById(R.id.countryTextView);
        TextView senderAddressTextView = senderRowView.findViewById(R.id.addressTextView);
        TextView senderContactTextView = senderRowView.findViewById(R.id.contactTextView);

        senderNameTextView.setText(senderFullName);
        senderCountryTextView.setText(senderCountry);
        senderAddressTextView.setText(senderAddress);
        senderContactTextView.setText(senderContact);

        // Add sender row to container
        reviewContainer.addView(senderRowView);

        // Add an image view (if needed)
        View imageView = inflater.inflate(R.layout.row_image_review_information, reviewContainer, false);
        reviewContainer.addView(imageView);

        // Inflate receiver row layout
        View receiverRowView = inflater.inflate(R.layout.row_item_review_information, reviewContainer, false);
        TextView receiverNameTextView = receiverRowView.findViewById(R.id.nameTextView);
        TextView receiverCountryTextView = receiverRowView.findViewById(R.id.countryTextView);
        TextView receiverAddressTextView = receiverRowView.findViewById(R.id.addressTextView);
        TextView receiverContactTextView = receiverRowView.findViewById(R.id.contactTextView);

        receiverNameTextView.setText(receiverFullName);
        receiverCountryTextView.setText(receiverCountry);
        receiverAddressTextView.setText(receiverAddress);
        receiverContactTextView.setText(receiverContact);

        // Add receiver row to container
        reviewContainer.addView(receiverRowView);
    }

    private String getPreviousActivity() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);

        if (!tasks.isEmpty()) {
            ActivityManager.RunningTaskInfo task = tasks.get(0);
            return task.topActivity.getClassName();
        }

        return "No previous activity";
    }
}