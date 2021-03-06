package com.shopclients.martixui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gaurav Badarkhe on 3/22/18.
 */

public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.MarticViewHolder> {

    int totalItems;
    ArrayList<String> resultList;
    Context context;
    Set<Integer> updatedPosition = new HashSet<>();

    public MatrixAdapter(int totalItems, Context context) {
        this.totalItems = totalItems;
        this.context = context;
    }

    public MatrixAdapter(ArrayList<String> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @Override
    public MarticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarticViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_cell, parent, false));

    }

    @Override
    public void onBindViewHolder(MarticViewHolder holder, final int position) {



        holder.edt_cell.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable != null && editable.toString() != null && editable.toString().trim().length() > 0) {
                    ((MainActivity) context).updateCellValue(Integer.valueOf(editable.toString().trim()), position);
                }


            }
        });


        if (resultList != null) {
            holder.edt_cell.setText(resultList.get(position));
        } else {
            holder.edt_cell.setText(((MainActivity) context).makeListCurrentFromMatrix().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : totalItems;
    }

    public static class MarticViewHolder extends RecyclerView.ViewHolder {

        EditText edt_cell;

        public MarticViewHolder(View itemView) {
            super(itemView);
            edt_cell = itemView.findViewById(R.id.edt_cell);
        }
    }
}
