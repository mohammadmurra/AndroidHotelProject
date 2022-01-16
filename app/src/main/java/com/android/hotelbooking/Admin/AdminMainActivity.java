package com.android.hotelbooking.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.R;
import com.android.hotelbooking.User.MainPageActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminMainActivity extends AppCompatActivity {
    TextView allRoomNumber, singleRoomNumber, doubleRoomNumber, deluxRoomNumber, singleRoomReserved, doubleRoomReserved, deluxRoomReserved;
    ArrayList<String> RoomsSingle;
    private RequestQueue queue;


    int number=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        singleRoomNumber = findViewById(R.id.SingleRoomNum);
        doubleRoomNumber=findViewById(R.id.DoubleRoomNum);
        deluxRoomNumber=findViewById(R.id.DeluxRoomNum);
        singleRoomReserved=findViewById(R.id.SingleReserved);
        doubleRoomReserved=findViewById(R.id.DoubleReservd);
        deluxRoomReserved=findViewById(R.id.DeluxReserved);
        String urlinti1 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getInfoRooms.php?name=singlerooms";
        String urlinti2 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getInfoRooms.php?name=doublerooms";
        String urlinti3 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getInfoRooms.php?name=deluxrooms";
        String urlinti4 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getReservedRoomInfo.php?name=singlerooms";
        String urlinti5 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getReservedRoomInfo.php?name=doublerooms";
        String  urlinti6 = "http://"+getResources().getString(R.string.databaseIp)+"/login/getReservedRoomInfo.php?name=deluxrooms";

        loadData(singleRoomNumber, urlinti1);
        loadData(doubleRoomNumber, urlinti2);
        loadData(deluxRoomNumber, urlinti3);
        loadData(singleRoomReserved, urlinti4);
        loadData(doubleRoomReserved, urlinti5);
        loadData(deluxRoomReserved, urlinti6);
    }

    public void AdminMenu(View view) {
        showPopupWindowAdmin popUpClass = new showPopupWindowAdmin();
        popUpClass.showPopupWindow(view);
    }

    public void addNewAdmin(View view) {
        Intent intent = new Intent(this, AddAdminActivity.class);
        startActivity(intent);
    }

    public void addNewRoom(View view) {
        Intent intent = new Intent(this, AddRoomActivity.class);
        startActivity(intent);
    }

    public void GoToHomePage(View view) {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);

    }

    public void RemoveNewAdmin(View view) {
        Intent intent = new Intent(this, RemoveAdminActivity.class);
        startActivity(intent);

    }

    public void loadData(TextView textV, String url) {
        ArrayList<String> roomlist=new ArrayList<>();
        queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    number++;
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        roomlist.add(obj.getString("roomName"));

                    } catch (JSONException exception) {
                        Log.d("Error", exception.toString());
                    }
                }
                Log.d("TAG", "onResponse: "+number);
                textV.setText(roomlist.size()+"");

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);



    }
}