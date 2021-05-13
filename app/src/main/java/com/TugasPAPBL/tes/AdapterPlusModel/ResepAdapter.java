package com.TugasPAPBL.tes.AdapterPlusModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.TugasPAPBL.tes.R;
import com.TugasPAPBL.tes.ResepDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ResepViewHolder> {
    private ArrayList<ResepModel> dataRecipe;
    private LayoutInflater mInflater;
    private Context context;

    public ResepAdapter(Context context, ArrayList<ResepModel> dataRecipe) {
        this.dataRecipe = dataRecipe;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ResepAdapter.ResepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row_resep, parent, false);
        return new ResepAdapter.ResepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ResepAdapter.ResepViewHolder holder, final int position) {
        holder.recipeID = Long.parseLong(dataRecipe.get(position).getRecipeID());
        holder.txtNamaResep.setText(dataRecipe.get(position).getRecipeName());
        holder.txtDescResep.setText(dataRecipe.get(position).getRecipeDesc());
        holder.txtJumKalori.setText(dataRecipe.get(position).getKalori());

        Picasso.get().load(dataRecipe.get(position).getRecipeImage()).into(holder.imgResep);

        holder.txtNamaResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ResepDetailActivity.class);
                intent.putExtra("recipeID", dataRecipe.get(position).getRecipeID());
                intent.putExtra("recipeImg", dataRecipe.get(position).getRecipeImage());
                intent.putExtra("recipeDesc", dataRecipe.get(position).getRecipeDesc());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataRecipe != null) ? dataRecipe.size() : 0;
    }

    public class ResepViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaResep, txtDescResep, txtJumKalori;
        private ImageView imgResep;
        private Long recipeID;

        public ResepViewHolder(View itemView) {
            super(itemView);
            imgResep = itemView.findViewById(R.id.img_resep);
            txtNamaResep = itemView.findViewById(R.id.txt_nama_resep);
            txtDescResep = itemView.findViewById(R.id.txt_deskripsi_resep);
            txtJumKalori = itemView.findViewById(R.id.txt_kalori_resep);
        }
    }
}
