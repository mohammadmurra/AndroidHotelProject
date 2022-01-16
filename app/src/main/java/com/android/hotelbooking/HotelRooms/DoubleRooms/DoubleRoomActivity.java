package com.android.hotelbooking.HotelRooms.DoubleRooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.R;
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

public class DoubleRoomActivity extends AppCompatActivity {

    ListView lst;
    TextView numOfRooms;
    private RequestQueue queue;
    ArrayList<String> Rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_room);
        lst = findViewById(R.id.doubleRoomList);
        numOfRooms=findViewById(R.id.numOfRoomsDouble);
        String url = "http://"+getString(R.string.databaseIp)+"/login/info_jsonDouble.php";

        loadData(url);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "" +  Rooms.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), bookingDoubleRoom.class);
                intent.putExtra("roomName",Rooms.get(position));
                startActivity(intent);

            }
        });


    }
    public void loadData(String url){
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                 Rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Rooms.add( obj.getString("roomName"));
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[Rooms.size()];

                arr = Rooms.toArray(arr);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        DoubleRoomActivity.this, android.R.layout.simple_list_item_1,
                        arr);
                lst.setAdapter(adapter);
               int size=Rooms.size();
                numOfRooms.setText(size+"");
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



