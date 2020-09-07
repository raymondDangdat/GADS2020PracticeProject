package com.example.gadspracticeproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadspracticeproject.R;
import com.example.gadspracticeproject.models.LearnersModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.ViewHolder> {
    private List<LearnersModel> dataList;
    private Context context;

    public LearnersRecyclerAdapter(List<LearnersModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_learners, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        String hours_country = dataList.get(position).getHours() + " learning hours, " + dataList.get(position).getCountry() + ".";
        holder.hours_and_country.setText(hours_country);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView hours_and_country;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            hours_and_country = itemView.findViewById(R.id.hours_and_country);
            image = itemView.findViewById(R.id.image);

        }
    }
}
