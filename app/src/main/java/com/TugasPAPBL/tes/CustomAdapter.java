package com.TugasPAPBL.tes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.customViewHolder> {
    LayoutInflater mInflater;
    ArrayList<EntryBiodata> entryBiodata;
    private boolean hasLoadButton = true;

    public CustomAdapter(FragmentActivity context, ArrayList<EntryBiodata> entryBiodata) {
        this.mInflater = LayoutInflater.from(context);
        this.entryBiodata = entryBiodata;
    }

    public boolean isHasLoadButton() {
        return hasLoadButton;
    }

    public void setHasLoadButton(boolean hasLoadButton) {
        this.hasLoadButton = hasLoadButton;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view from layout
        View itemView = mInflater.inflate(R.layout.rv_item, parent, false);
        return new customViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, int position) {
        EntryBiodata current = entryBiodata.get(position);
        holder.biodataAtasView.setText(current.biodataAtas);
        holder.biodataBawahView.setText(current.biodataBawah);
    }

    @Override
    public int getItemCount() {
        return entryBiodata.size();
    }

    class customViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView.Adapter<customViewHolder> mAdapter;
        public TextView biodataAtasView;
        public TextView biodataBawahView;

        public customViewHolder(View itemView, CustomAdapter adapter) {
            super(itemView);
// Get the layout
            biodataAtasView = itemView.findViewById(R.id.textViewBiodataAtas);
            biodataBawahView = itemView.findViewById(R.id.textViewBiodataBawah);
// Associate with this adapter
            this.mAdapter = adapter;
        }
    }
}
