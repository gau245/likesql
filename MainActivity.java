package com.example.favlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.favlist.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_dashboard, btn_fav_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dashboard = findViewById(R.id.btn_dashboard);
        btn_fav_list = findViewById(R.id.btn_fav_list);

        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DashBordActivity.class));
            }
        });

        btn_fav_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavListActivity.class));
            }
        });
    }
}