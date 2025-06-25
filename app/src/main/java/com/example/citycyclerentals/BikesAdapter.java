package com.example.citycyclerentals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class BikesAdapter extends RecyclerView.Adapter<BikesAdapter.BikeViewHolder> {

    private Context context;
    private List<Bike> bikeList;

    public BikesAdapter(Context context, List<Bike> bikeList) {
        this.context = context;
        this.bikeList = bikeList;
    }

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bike, parent, false);
        return new BikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        Bike bike = bikeList.get(position);

        // Set bike details
        holder.bikeNameTextView.setText(bike.getName());
        holder.bikeTypeTextView.setText(bike.getType());
        holder.bikeStatusTextView.setText(bike.getStatus());



        // Handle Rent Now button click
        holder.rentButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PaymentActivity.class);
            intent.putExtra("bike_name", bike.getName());
            intent.putExtra("bike_type", bike.getType());
            context.startActivity(intent);
        });

        // Show rent button only if available
        holder.rentButton.setVisibility(
                bike.getStatus().equalsIgnoreCase("Available") ? View.VISIBLE : View.GONE
        );
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    public static class BikeViewHolder extends RecyclerView.ViewHolder {
        ImageView bikeImageView;
        TextView bikeNameTextView, bikeTypeTextView, bikeStatusTextView;
        Button rentButton;

        public BikeViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeImageView = itemView.findViewById(R.id.bikeImageView);
            bikeNameTextView = itemView.findViewById(R.id.bikeNameTextView);
            bikeTypeTextView = itemView.findViewById(R.id.bikeTypeTextView);
            bikeStatusTextView = itemView.findViewById(R.id.bikeStatusTextView);
            rentButton = itemView.findViewById(R.id.rentButton);
        }
    }
}