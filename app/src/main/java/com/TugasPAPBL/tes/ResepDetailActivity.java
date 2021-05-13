package com.TugasPAPBL.tes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.TugasPAPBL.tes.AdapterPlusModel.ResepDetailAdapter;
import com.TugasPAPBL.tes.AdapterPlusModel.ResepDetailModel;
import com.TugasPAPBL.tes.Service.ServiceCall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResepDetailActivity extends AppCompatActivity {

    private String TAG = "ResepDetailActivity";
    private RecyclerView recyclerView;
    private ResepDetailAdapter adapter;
    private ArrayList<ResepDetailModel> resepDetailArrayList = new ArrayList<>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep_detail);
        Long recipeID = Long.parseLong(getIntent().getStringExtra("recipeID"));
        recyclerView = findViewById(R.id.recView_resep_detail);
        new getRecipe(recipeID).execute();
    }

    class getRecipe extends AsyncTask<Void, Void, Void> {
        Long id;

        public getRecipe(Long id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ResepDetailActivity.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceCall serviceCall = new ServiceCall();
            JSONObject recipe = serviceCall.getRecipeCall(this.id);

            Log.i("recipe: ", recipe.toString());


            resepDetailArrayList.clear();
            try {
                if (recipe != null) {
                    JSONObject directions = recipe.getJSONObject("directions");
                    JSONObject ingredients = recipe.getJSONObject("ingredients");
                    JSONArray directionsArr = directions.getJSONArray("direction");
                    JSONArray ingredientsArr = ingredients.getJSONArray("ingredient");
                    String ingredient = "";
                    String direction = "";

                    for (int i = 0; i < directionsArr.length(); i++) {
                        JSONObject direct = directionsArr.optJSONObject(i);
                        direction += direct.getString("direction_description") + "\n\n";
                    }

                    for (int i = 0; i < ingredientsArr.length(); i++) {
                        JSONObject ingre = ingredientsArr.optJSONObject(i);
                        ingredient += ingre.getString("ingredient_description") + "\n";
                    }

                    Log.i("ingredient", ingredient);
                    Log.i("direction", direction);

                    String imgRecipe = getIntent().getStringExtra("recipeImg");
                    String idRecipe = recipe.getString("recipe_id");
                    String nameRecipe = recipe.getString("recipe_name");
                    if (imgRecipe.isEmpty()) {
                        imgRecipe = "https://static.ivqonsanada.com/img/default-thumbnail-food.jpg";
                    }


                    String descRecipe = getIntent().getStringExtra("recipeDesc");
                    String kaloriRecipe = "10" + " kkal";


                    resepDetailArrayList.add(new ResepDetailModel(idRecipe, nameRecipe, descRecipe,
                            imgRecipe, direction, ingredient));
                }
            } catch (JSONException e) {
                Log.e(TAG, "JSONException : " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e.getMessage());
            } finally {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            recyclerView = findViewById(R.id.recView_resep_detail);
            adapter = new ResepDetailAdapter(ResepDetailActivity.this, resepDetailArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(ResepDetailActivity.this));
            recyclerView.setAdapter(adapter);
        }
    }


}
