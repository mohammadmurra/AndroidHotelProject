package com.android.hotelbooking.HotelRooms.SingelRooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.LoginActivity;
import com.android.hotelbooking.R;
import com.android.hotelbooking.User.MainPageActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class bookingSingleRoom extends AppCompatActivity {
    TextView roomName;
    Button buttonReserve;
    LoginActivity loginActivity = new LoginActivity();
    String room,user;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_single_room);
        buttonReserve = findViewById(R.id.buttonReserve);
        Intent i = getIntent();
         room = i.getStringExtra("roomName");
         user = loginActivity.LoggedUser;
        roomName = findViewById(R.id.bookingRoomName);
        roomName.setText(room);
        url = "http://"+getResources().getString(R.string.databaseIp)+"/login/reserveRoom.php?roomName="+room+"&user="+user+"";
        buttonReserve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showPopupWindow popUpClass = new showPopupWindow();
                popUpClass.showPopupWindow(v);
            }
        });

    }

    public void ReserveRoom(View view) {
        System.out.println(room + "      "+ user);



            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(view.getContext(), "Successfully Reserved.", Toast.LENGTH_SHORT).show();


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
                    data.put("roomName", room);
                    data.put("user", user);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
            requestQueue.add(stringRequest);
        backToMain();
    }
    private void backToMain() {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
        finish();
    }



    }

