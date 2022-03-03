package com.example.favlist.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favlist.Activity.DashBordActivity;
import com.example.favlist.Model.ModelFavList;
import com.example.favlist.R;
import com.example.favlist.database.MyDatabase;

public class DashbordAdapter extends RecyclerView.Adapter<DashbordAdapter.DataHolder> {

    Context context;
    String[] name;
    int[] image;
    LayoutInflater inflater;
    //boolean isFav = false;
    MyDatabase myDatabase;

    public DashbordAdapter(DashBordActivity dashBordActivity, String[] name, int[] image) {
        context = dashBordActivity;
        this.name = name;
        this.image=image;
        inflater = LayoutInflater.from(context);
        myDatabase = new MyDatabase(context);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_dashbord, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {


        //TODO: open fav listed data...
        Cursor cursor = myDatabase.getData();
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            String pos = cursor.getString(0);
            String name = cursor.getString(1);
            String image = cursor.getString(2);

            if (pos.equals(String.valueOf(position))) {
//                holder.iv_fav.setImageResource(R.drawable.fav_fill);
                holder.iv_Fav.setVisibility(View.VISIBLE);
                holder.iv_notFav.setVisibility(View.INVISIBLE);
                Log.e("student", "fav red fill : " + pos + "==" + position);
            }
            cursor.moveToNext();
        }


        holder.txt_name.setText(name[position]);
        holder.iv_fruit.setImageResource(image[position]);

        if (holder.iv_notFav.getVisibility() == View.VISIBLE) {

            holder.iv_notFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_notFav.setVisibility(View.INVISIBLE);
                    holder.iv_Fav.setVisibility(View.VISIBLE);

                    //insert data...
                    String pos = String.valueOf(position);
                    myDatabase.insertData(pos, name[position], image[position]);
                    Toast.makeText(context, "Add to favourite!", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (holder.iv_Fav.getVisibility() == View.VISIBLE) {

            holder.iv_Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_Fav.setVisibility(View.INVISIBLE);
                    holder.iv_notFav.setVisibility(View.VISIBLE);

                    //remove data...
                    myDatabase.removeData(String.valueOf(position));
                    Toast.makeText(context, "Remove from favourite!", Toast.LENGTH_SHORT).show();
                }
            });

        }

//        holder.iv_notFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!isFav) {
//                    holder.iv_fav.setImageResource(R.drawable.fav_fill);
//                    isFav = true;
//                    String pos = String.valueOf(position);
//                    myDatabase.insertData(pos, name[position]);
//                    Toast.makeText(context, "Add to favourite!", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    myDatabase.removeData(String.valueOf(position));
//                    holder.iv_fav.setImageResource(R.drawable.fav_line);
//                    isFav = false;
//                    Toast.makeText(context, "Remove from favourite!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private ImageView iv_notFav;
        private ImageView iv_Fav;
        private ImageView iv_fruit;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            iv_notFav = itemView.findViewById(R.id.iv_notFav);
            iv_Fav = itemView.findViewById(R.id.iv_Fav);
            iv_fruit = itemView.findViewById(R.id.iv_fruit);
        }
    }
}
