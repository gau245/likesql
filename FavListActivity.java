package com.example.favlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.favlist.Adapter.FavAdapter;
import com.example.favlist.Model.ModelFavList;
import com.example.favlist.R;
import com.example.favlist.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavListActivity extends AppCompatActivity {

    MyDatabase myDatabase;
    RecyclerView recyclerView;
    List<ModelFavList> dataList = new ArrayList<>();
    FavAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

        recyclerView = findViewById(R.id.recyclerView);

        myDatabase = new MyDatabase(this);
        favAdapter = new FavAdapter(this, dataList);

        refreshData();

    }

    public void refreshData() {

        favAdapter.clearRecyclerView();

        Cursor cursor = myDatabase.getData();
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            String pos = cursor.getString(0);
            String name = cursor.getString(1);
            int image = cursor.getInt(2);

            ModelFavList modelFavList = new ModelFavList(pos, name, image);
            dataList.add(modelFavList);

            cursor.moveToNext();
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(favAdapter);

    }
}