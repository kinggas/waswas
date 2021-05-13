package com.TugasPAPBL.tes.AdapterPlusModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.TugasPAPBL.tes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResepDetailAdapter extends RecyclerView.Adapter<ResepDetailAdapter.ResepDetailViewHolder> {
    private ArrayList<ResepDetailModel> dataRecipe;
    private LayoutInflater mInflater;
    private Context context;

    public ResepDetailAdapter(Context context, ArrayList<ResepDetailModel> dataRecipe) {
        this.dataRecipe = dataRecipe;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ResepDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row_resep_detail, parent, false);
        return new ResepDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ResepDetailViewHolder holder, final int position) {
        holder.txtNamaResep.setText(dataRecipe.get(position).getRecipeName());
        holder.txtDescResep.setText(dataRecipe.get(position).getRecipeDesc());
        holder.txtIngredient.setText(dataRecipe.get(position).getIngredient());
        holder.txtDirection.setText(dataRecipe.get(position).getDirection());

        Picasso.get().load(dataRecipe.get(position).getRecipeImages()).into(holder.imgResep);

    }

    @Override
    public int getItemCount() {
        return (dataRecipe != null) ? dataRecipe.size() : 0;
    }

    public class ResepDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaResep, txtDescResep, txtIngredient, txtDirection;
        private ImageView imgResep;
        private Long recipeID;

        public ResepDetailViewHolder(View itemView) {
            super(itemView);
            imgResep = itemView.findViewById(R.id.image_resep_detail);
            txtNamaResep = itemView.findViewById(R.id.text_nama_resep_detail);
            txtDescResep = itemView.findViewById(R.id.text_deskripsi_resep_detail);
            txtDirection = itemView.findViewById(R.id.text_petunjuk_resep_detail);
            txtIngredient = itemView.findViewById(R.id.text_bahan_resep_detail);
        }
    }

}
