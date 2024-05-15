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
public class Status extends AppCompatActivity {
    private EditText editTextClaimNumber;
    private EditText editTextDate;
    private EditText editTextStatus;
    private Button buttonSubmit;
    private RequestQueue requestQueue;
    private static final String BASE_URL = "https://assignment-demo-1.onrender.com/status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editTextClaimNumber = findViewById(R.id.editTextClaimNumber);
        editTextDate = findViewById(R.id.editTextDate);
        editTextStatus = findViewById(R.id.editTextStatus);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        requestQueue = Volley.newRequestQueue(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus();
            }
        });
    }

    private void updateStatus() {
        String claimNumber = editTextClaimNumber.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("claimnumber", claimNumber);
            jsonBody.put("date", date);
            jsonBody.put("Status", status);
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
                                Toast.makeText(Status.this, "Status updated successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Status.this, "Error updating status", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Status.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error occurred", error);
                Toast.makeText(Status.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
