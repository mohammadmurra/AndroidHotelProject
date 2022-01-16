package com.android.hotelbooking.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.hotelbooking.HotelRooms.DeluxRooms.DeluxRoomActivity;
import com.android.hotelbooking.HotelRooms.DoubleRooms.DoubleRoomActivity;
import com.android.hotelbooking.HotelRooms.SingelRooms.SingleRoomActivity;
import com.android.hotelbooking.R;
import com.android.hotelbooking.ReservedRoom.MyReservedRooms;

public class MainPageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar

    }
    public void openSingleRoom(View view) {
        Toast.makeText(getApplicationContext(), "single", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SingleRoomActivity.class);
        startActivity(intent);

    }

    public void openDoubleRoom(View view) {
        Toast.makeText(getApplicationContext(), "double", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DoubleRoomActivity.class);
        startActivity(intent);

    }


    public void openDeluxRoom(View view) {
        Toast.makeText(getApplicationContext(), "delux", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DeluxRoomActivity.class);
        startActivity(intent);

    }

    public void openMyRoomsClass(View view) {
        Toast.makeText(getApplicationContext(), "Myroom", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyReservedRooms.class);
        startActivity(intent);
    }



}