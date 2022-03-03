package com.example.favlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favlist.Activity.DashBordActivity;
import com.example.favlist.Activity.FavListActivity;
import com.example.favlist.Model.ModelFavList;
import com.example.favlist.R;
import com.example.favlist.database.MyDatabase;

import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.DataHolder> {

    Context context;
    List<ModelFavList> dataList;
    LayoutInflater inflater;
 //   boolean isFav = true;
    MyDatabase myDatabase;

    public FavAdapter(FavListActivity dashBordActivity, List<ModelFavList> dataList) {
        context = dashBordActivity;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
        myDatabase = new MyDatabase(context);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fav, parent, false);
        return new DataHolder(view);
    }

    public void clearRecyclerView() {
        int size = this.dataList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                dataList.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.txt_name.setText(dataList.get(position).getName());
        holder.iv_fruit.setImageResource(dataList.get(position).getImage());

        holder.iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String pos = dataList.get(position).getPos();

                    myDatabase.removeData(pos);
                    Toast.makeText(context, "Remove from favourite!", Toast.LENGTH_SHORT).show();

                    ((FavListActivity) context).refreshData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private ImageView iv_fav;
        private ImageView iv_fruit;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            iv_fav = itemView.findViewById(R.id.iv_fav);
            iv_fruit = itemView.findViewById(R.id.iv_fruit);
        }
    }
}
