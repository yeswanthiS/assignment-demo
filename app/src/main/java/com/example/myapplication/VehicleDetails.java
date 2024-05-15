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

public class VehicleDetails extends AppCompatActivity {

    private EditText editTextModel;
    private EditText editTextYear;
    private EditText editTextPhoto;
    private Button buttonSubmit;
    private RequestQueue requestQueue;
    private static final String BASE_URL = "https://assignment-demo-1.onrender.com/vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        editTextModel = findViewById(R.id.editTextModel);
        editTextYear = findViewById(R.id.editTextYear);
        editTextPhoto = findViewById(R.id.editTextPhoto);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        requestQueue = Volley.newRequestQueue(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitVehicleDetails();
            }
        });
    }

    private void submitVehicleDetails() {
        // Get user input from EditText fields
        String model = editTextModel.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();
        String photo = editTextPhoto.getText().toString().trim();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", model);
            jsonBody.put("year", year);
            jsonBody.put("photo", photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("msg");
                            if (msg.equals("Success")) {
                                Toast.makeText(VehicleDetails.this, "Vehicle details submitted successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(VehicleDetails.this, "Error submitting vehicle details", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VehicleDetails.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error occurred", error);
                Toast.makeText(VehicleDetails.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
