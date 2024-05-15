package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonClaim = findViewById(R.id.buttonClaim);
        Button buttonStatus = findViewById(R.id.buttonStatus);
        Button buttonVehicleDetails = findViewById(R.id.buttonVehicleDetails);

        buttonClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Claim.class));
            }
        });

        buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open StatusActivity when Status button is clicked
                startActivity(new Intent(MainActivity.this, Status.class));
            }
        });

        buttonVehicleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open VehicleDetailsActivity when Vehicle Details button is clicked
                startActivity(new Intent(MainActivity.this, VehicleDetails.class));
            }
        });
    }
}
