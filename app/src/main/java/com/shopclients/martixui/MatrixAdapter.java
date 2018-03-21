package com.shopclients.martixui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Gaurav Badarkhe on 3/22/18.
 */

public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.MarticViewHolder> {

    int totalItems;

    public MatrixAdapter(int totalItems) {
        this.totalItems = totalItems;
    }

    @Override
    public MarticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarticViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_cell, parent, false));

    }

    @Override
    public void onBindViewHolder(MarticViewHolder holder, int position) {
        holder.edt_cell.setText(position+"");
       
    }

    @Override
    public int getItemCount() {
        return totalItems;
    }

    public static class MarticViewHolder extends RecyclerView.ViewHolder {

        EditText edt_cell;

        public MarticViewHolder(View itemView) {
            super(itemView);
            edt_cell = itemView.findViewById(R.id.edt_cell);
        }
    }
}
