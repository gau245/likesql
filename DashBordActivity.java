package com.example.favlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.favlist.Adapter.DashbordAdapter;
import com.example.favlist.R;

public class DashBordActivity extends AppCompatActivity {

    private DashbordAdapter dashbordAdapter;
    private RecyclerView rv;
    private String[] name = {"Apple", "Banana", "Cherry", "Grapes", "Kiwi", "Mango", "Orange", "Papaya", "Pineapple", "Strawberry", "Watermelone"};
    private int[] image = {
            R.drawable.fruit_apple,
            R.drawable.fruit_banana,
            R.drawable.fruit_cherry,
            R.drawable.fruit_grapes,
            R.drawable.fruit_kiwi,
            R.drawable.fruit_mango,
            R.drawable.fruit_orange,
            R.drawable.fruit_papaya,
            R.drawable.fruit_pineapple,
            R.drawable.fruit_strawberry,
            R.drawable.fruit_watermelon,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        rv = findViewById(R.id.rv);
        dashbordAdapter = new DashbordAdapter(this, name, image);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(dashbordAdapter);
    }
}