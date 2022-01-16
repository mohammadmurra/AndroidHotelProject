package com.android.hotelbooking.ReservedRoom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.LoginActivity;
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

public class MyRoomDetailsActivity extends AppCompatActivity {
    TextView roomName;
    Button buttonReserve;
    LoginActivity loginActivity = new LoginActivity();
    String room,user,type,URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_details);
        buttonReserve = findViewById(R.id.buttonUnReserve);
        Intent i = getIntent();
        room = i.getStringExtra("roomName");
        user = loginActivity.LoggedUser;
        type=i.getStringExtra("type");
        roomName = findViewById(R.id.SelctedRoomName);
        roomName.setText(room);
        buttonReserve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showPopupWindowUnreserved popUpClass = new showPopupWindowUnreserved();
                popUpClass.showPopupWindow(v);
            }
        });

    }

    public void UnReserveRoom(View view) {

        if(type.equals("Single")){
            URL  = "http://"+getString(R.string.databaseIp)+"/login/UnReserveRoomSingle.php";
        }else if (type.equals("Double")){
            URL  = "http://"+getString(R.string.databaseIp)+"/login/UnReserveRoomDouble.php";
        }else if (type.equals("Delux")){
            URL  = "http://"+getString(R.string.databaseIp)+"/login/UnReserveRoomDelux.php";
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(view.getContext(), "Successfully UnReserved The Room.", Toast.LENGTH_SHORT).show();


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
        Toast.makeText(getApplicationContext(), "double", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyReservedRooms.class);
        startActivity(intent);
        finish();
    }

}
