package com.example.citycyclerentals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RentalsAdapter extends RecyclerView.Adapter<RentalsAdapter.RentalViewHolder> {
    private Context context;
    private List<Rental> rentalList;


    public RentalsAdapter(Context context, List<Rental> rentalList) {
        this.context = context;
        this.rentalList = rentalList;
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rental, parent, false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        Rental rental = rentalList.get(position);

        holder.bikeIdTextView.setText("Bike ID: " + rental.getBikeId());
        holder.startTimeTextView.setText("Start: " + rental.getStartTime());
        holder.endTimeTextView.setText("End: " + rental.getEndTime());
        holder.priceTextView.setText("Price: $" + rental.getPrice());
        holder.statusTextView.setText("Status: " + rental.getStatus());
    }

    @Override
    public int getItemCount() {
        return rentalList.size();
    }

    public static class RentalViewHolder extends RecyclerView.ViewHolder {
        TextView bikeIdTextView, startTimeTextView, endTimeTextView, priceTextView, statusTextView;

        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeIdTextView = itemView.findViewById(R.id.bikeIdTextView);
            startTimeTextView = itemView.findViewById(R.id.startTimeTextView);
            endTimeTextView = itemView.findViewById(R.id.endTimeTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}