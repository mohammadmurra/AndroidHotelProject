package com.android.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        finish();
    }

    public void openDoubleRoom(View view) {
        Toast.makeText(getApplicationContext(), "double", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SingleRoomActivity.class);
        startActivity(intent);
        finish();
    }


    public void openDeluxRoom(View view) {
        Toast.makeText(getApplicationContext(), "delux", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SingleRoomActivity.class);
        startActivity(intent);
        finish();
    }
}