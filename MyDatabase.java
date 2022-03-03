package com.example.favlist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, "mydb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table bookmark(pos integer primary key, name text, image int)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        String query = "drop table if exists bookmark";
        db.execSQL(query);
    }

    public void insertData(String pos, String name, int image) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pos", pos);
        contentValues.put("name", name);
        contentValues.put("image", image);
        database.insert("bookmark", null, contentValues);
    }

    public void removeData(String pos) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pos", pos);
        long res = database.delete("bookmark", "pos=?", new String[]{pos});
        if (res == 1) {
            Log.e("student", "removeData: success" + res);
        } else {
            Log.e("student", "removeData: failed!..........................." + res);
        }

    }

    public Cursor getData() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from bookmark";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
}
