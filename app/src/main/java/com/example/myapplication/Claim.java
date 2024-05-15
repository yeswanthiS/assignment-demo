package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Claim extends AppCompatActivity {
    private EditText editTextHolderName;
    private EditText editTextDate;
    private EditText editTextMobileNumber;
    private EditText editTextPolicyReport;
    private EditText editTextPlace;
    private EditText editTextDamageDetails;
    private Button buttonSubmit;
    private RequestQueue requestQueue;
    private static final String BASE_URL = "https://assignment-demo-1.onrender.com/claim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);

        editTextHolderName = findViewById(R.id.editTextHolderName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextMobileNumber = findViewById(R.id.editTextMobileNumber);
        editTextPolicyReport = findViewById(R.id.editTextPolicyReport);
        editTextPlace = findViewById(R.id.editTextPlace);
        editTextDamageDetails = findViewById(R.id.editTextDamageDetails);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        requestQueue = Volley.newRequestQueue(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileClaim();
            }
        });
    }

    private void fileClaim() {
        String holderName = editTextHolderName.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String mobileNumber = editTextMobileNumber.getText().toString().trim();
        String policyReport = editTextPolicyReport.getText().toString().trim();
        String place = editTextPlace.getText().toString().trim();
        String damageDetails = editTextDamageDetails.getText().toString().trim();

        // Create a JSON object with the user input
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("holdername", holderName);
            jsonBody.put("date", date);
            jsonBody.put("mobilenumbe", mobileNumber);
            jsonBody.put("policyreport", policyReport);
            jsonBody.put("place", place);
            jsonBody.put("damagedetails", damageDetails);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a POST request using Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("msg");
                            if (msg.equals("Success")) {
                                // Claim filed successfully
                                Toast.makeText(Claim.this, "Claim filed successfully", Toast.LENGTH_SHORT).show();
                                // Optionally, you can finish this activity or perform other actions
                            } else {
                                // Error filing claim
                                Toast.makeText(Claim.this, "Error filing claim", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Claim.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error occurred", error);
                Toast.makeText(Claim.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}
