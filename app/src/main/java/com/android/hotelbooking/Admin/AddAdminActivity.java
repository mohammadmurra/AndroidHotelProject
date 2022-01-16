package com.android.hotelbooking.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddAdminActivity extends AppCompatActivity {
    EditText adminName,adminPassword,adminRepassword,adminEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        adminName=findViewById(R.id.newAdminName);
        adminPassword=findViewById(R.id.newAdminPass);
        adminRepassword=findViewById(R.id.newAdminRePass);
        adminEmail=findViewById(R.id.newAdminEmail);
    }

    public void addNewAdminToDB(View view) {
        String name = adminName.getText().toString().trim();
        String email = adminEmail.getText().toString().trim();
        String password = adminPassword.getText().toString().trim();
        String reenterPassword = adminRepassword.getText().toString().trim();
        String URL = "http://"+getString(R.string.databaseIp)+"/login/register.php";
        if(!password.equals(reenterPassword)){
            Toast.makeText(view.getContext(), "Password Mismatch", Toast.LENGTH_SHORT).show();
        } else if(!name.equals("") && !email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(view.getContext(), "Successfully registered,New Admin Was Added.", Toast.LENGTH_SHORT).show();


                    } else if (response.equals("failure")) {
                        Toast.makeText(view.getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(view.getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", password);
                    data.put("userType", "admin");
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
            requestQueue.add(stringRequest);
        }

        BackToAdminPage();
    }

    private void BackToAdminPage() {
        Intent intent = new Intent(this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }

}