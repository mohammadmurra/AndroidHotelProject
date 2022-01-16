package com.android.hotelbooking;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.Admin.AdminMainActivity;
import com.android.hotelbooking.User.MainPageActivity;
import com.android.hotelbooking.User.SignUp;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userName,userPassword;
    private RequestQueue queue;
     public static String LoggedUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intalizeInterface();

    }

    private void intalizeInterface() {
        userName=findViewById(R.id.userName);
        userPassword=findViewById(R.id.userPassword);

    }


    public void loginMethod(View view) {
       String email=userName.getText().toString();
        String password=userPassword.getText().toString();
        queue = Volley.newRequestQueue(this);
        String URL = "http://"+getString(R.string.databaseIp)+"/login/login.php?name="+email+"&password="+password+"";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    try {
                        LoggedUser=email;
                        JSONObject obj = response.getJSONObject(0);
                        String userType=obj.getString("userType");
                        Log.d("TAG", "this is user"+userType);
                        if (userType.equals("admin")){
                            Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                            startActivity(intent);

                        }else if (userType.equals("user")){
                            Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);

                        }
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("name", email);
                data.put("password", password);
                return data;
            }
        };

        queue.add(request);


    }


    public void signupMethod(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);

    }



}