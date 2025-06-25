package com.example.citycyclerentals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.StationViewHolder> {
    private Context context;
    private List<Station> stationList;

    public StationsAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_station, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.stationNameTextView.setText(station.getName());
        holder.stationAddressTextView.setText(station.getAddress());

        holder.stationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StationDetailsActivity.class);
                intent.putExtra("station_id", station.getId());
                intent.putExtra("station_name", station.getName());
                intent.putExtra("station_address", station.getAddress());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        TextView stationNameTextView, stationAddressTextView;
        CardView stationCardView;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            stationNameTextView = itemView.findViewById(R.id.stationNameTextView);
            stationAddressTextView = itemView.findViewById(R.id.stationAddressTextView);
            stationCardView = itemView.findViewById(R.id.stationCardView);
        }
    }
}
