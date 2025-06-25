package com.example.citycyclerentals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {
    private List<PaymentMethod> paymentMethod;

    public PaymentMethodsAdapter(List<PaymentMethod> paymentMethods) {
        this.paymentMethod = paymentMethods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment_method, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentMethod method = paymentMethod.get(position);
        holder.methodTextView.setText(method.getMethod());
        holder.detailsTextView.setText(method.getDetails());
    }

    @Override
    public int getItemCount() { return paymentMethod.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView methodTextView, detailsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            methodTextView = itemView.findViewById(R.id.methodTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
        }
    }
}