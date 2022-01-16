package com.android.hotelbooking.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddRoomActivity extends AppCompatActivity {
    Spinner spinner;
    EditText RoomNameEditText;
    String RoomNameString,RoomTypeString,URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
         spinner = (Spinner) findViewById(R.id.RoomTypespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Room_Type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        RoomNameEditText=findViewById(R.id.RoomName);
    }

    public void AddRoom(View view) {
         RoomNameString=RoomNameEditText.getText().toString();
         RoomTypeString=spinner.getSelectedItem().toString();


        if(!RoomNameString.equals("") && !RoomNameString.equals("") ){
            if (RoomTypeString.equals("Single")){
                URL = "http://"+getString(R.string.databaseIp)+"/login/AddSingleRoom.php";
            }else if (RoomTypeString.equals("Double")){
                URL = "http://"+getString(R.string.databaseIp)+"/login/AddDoubleRoom.php";
            } else if (RoomTypeString.equals("Delux")) {
                URL = "http://"+getString(R.string.databaseIp)+"/login/AddDeluxRoom.php";
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Successfully, New Room Added.", Toast.LENGTH_SHORT).show();


                    } else if (response.equals("failure")) {
                        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
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
                    data.put("RoomName", RoomNameString);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
}