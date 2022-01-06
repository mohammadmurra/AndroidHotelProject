package com.android.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {

    private EditText userName, userRePassword, userPassword, userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intalizeInterface();

    }

    private void intalizeInterface() {
        userName = findViewById(R.id.userNameSignUp);
        userPassword = findViewById(R.id.userPasswordSignUp);
        userEmail = findViewById(R.id.userEmailSignUp);
        userRePassword = findViewById(R.id.userRePasswordSignUp);

    }

    public void NewSignupMethod(View view) {
        String name = userName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String reenterPassword = userRePassword.getText().toString().trim();
        String URL = "http://192.168.1.8/login/register.php";
        if(!password.equals(reenterPassword)){
            Toast.makeText(view.getContext(), "Password Mismatch", Toast.LENGTH_SHORT).show();
        } else if(!name.equals("") && !email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(view.getContext(), "Successfully registered.", Toast.LENGTH_SHORT).show();


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
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
            requestQueue.add(stringRequest);
        }
    }



    public void BackToLoginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}