package com.android.hotelbooking.ReservedRoom;

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

import com.android.hotelbooking.LoginActivity;
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

public class MyReservedRooms extends AppCompatActivity {
    String user= LoginActivity.LoggedUser;

    ListView lstSingle,lstDouble,lstDelux;
    private RequestQueue queue;
    ArrayList<String> RoomsSingle= new ArrayList<>();
    ArrayList<String>  RoomsDouble= new ArrayList<>();
    ArrayList<String>  RoomsDelux= new ArrayList<>();
    TextView singleLabel,doubleLabel,deluxLabel,emptyLabel;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserved_rooms);
        singleLabel=findViewById(R.id.signleRoomTitle);
        doubleLabel=findViewById(R.id.doubleRoomTitle);
        emptyLabel=findViewById(R.id.emptyRooms);
        deluxLabel=findViewById(R.id.deluxRoomTitle);
        lstSingle=findViewById(R.id.myListSingle);
        lstDelux=findViewById(R.id.myListDelux);
        lstDouble=findViewById(R.id.myListDouble);
        emptyLabel.setVisibility(View.GONE);
        String urlSingle = "http://"+getString(R.string.databaseIp)+"/login/MySingleRooms.php?cat="+user;
        String urlDouble = "http://"+getString(R.string.databaseIp)+"/login/MyDoubleRooms.php?cat="+user;
        String urlDelux = "http://"+getString(R.string.databaseIp)+"/login/MyDeluxRooms.php?cat="+user;
        loadData(urlSingle,lstSingle,RoomsSingle,singleLabel);
        loadData(urlDouble,lstDouble,RoomsDouble,doubleLabel);
        loadData(urlDelux,lstDelux,RoomsDelux,deluxLabel);


        lstSingle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "" +  RoomsSingle.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyRoomDetailsActivity.class);
                intent.putExtra("roomName",RoomsSingle.get(position));
                intent.putExtra("type","Single");
                startActivity(intent);
                finish();
            }
        });
        lstDouble.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "" +  RoomsDouble.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyRoomDetailsActivity.class);
                intent.putExtra("roomName",RoomsDouble.get(position));
                intent.putExtra("type","Double");
                startActivity(intent);
                finish();

            }
        });
        lstDelux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "" +  RoomsDelux.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyRoomDetailsActivity.class);
                intent.putExtra("roomName",RoomsDelux.get(position));
                intent.putExtra("type","Delux");
                startActivity(intent);
                finish();
            }
        });

    }



    public void loadData(String URl, ListView list, ArrayList<String> roomlist, TextView titleLabel){
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        roomlist.add( obj.getString("roomName"));

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[roomlist.size()];
                if (arr.length==0){
                titleLabel.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                }else {
                    flag=true;
                    arr = roomlist.toArray(arr);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            getApplicationContext(), android.R.layout.simple_list_item_1,
                            arr);
                    list.setAdapter(adapter);

                }
                if (flag==false){
                    emptyLabel.setVisibility(View.VISIBLE);
                }

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
