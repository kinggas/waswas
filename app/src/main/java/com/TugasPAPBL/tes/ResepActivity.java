package com.TugasPAPBL.tes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.TugasPAPBL.tes.AdapterPlusModel.ResepAdapter;
import com.TugasPAPBL.tes.AdapterPlusModel.ResepModel;
import com.TugasPAPBL.tes.Service.ServiceCall;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResepActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private Toolbar toolbar;
    private String TAG = "ResepActivity";
    private RecyclerView recyclerView;
    private ResepAdapter adapter;
    private ArrayList<ResepModel> resepArrayList = new ArrayList<>();
    private ProgressDialog pDialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(ResepActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(ResepActivity.this, biodataActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(1).setChecked(true);

        //View Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recView_resep);

        final EditText editTextSearch = findViewById(R.id.editTxt_resep);
        Button btnCari = findViewById(R.id.btn_cari_resep);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextSearch.getText().toString() != null) {
                    new searchRecipe(editTextSearch.getText().toString()).execute();
                    Log.e(TAG, "editTextSearch : " + editTextSearch.getText().toString());
                    closeKeyboard();
                } else {
                    Log.e(TAG, "editTextSearch is null");
                }
            }
        });
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    class searchRecipe extends AsyncTask<Void, Void, Void> {

        String query;

        public searchRecipe(String query) {
            this.query = query;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ResepActivity.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceCall serviceCall = new ServiceCall();
            JSONObject recipe = serviceCall.searchRecipeCall(this.query);
            JSONArray RECIPES_ARRAY;
            resepArrayList.clear();
            try {
                if (recipe != null) {
                    RECIPES_ARRAY = recipe.getJSONArray("recipe");
                    Log.i("asdf", RECIPES_ARRAY.toString());
                    if (RECIPES_ARRAY != null) {
                        for (int i = 0; i < RECIPES_ARRAY.length(); i++) {
                            JSONObject recipe_items = RECIPES_ARRAY.optJSONObject(i);
                            String imgRecipe;
                            String idRecipe = recipe_items.getString("recipe_id");
                            String namaRecipe = recipe_items.getString("recipe_name");
                            if (recipe_items.has("recipe_image")) {
                                imgRecipe = recipe_items.getString("recipe_image");
                            } else {
                                imgRecipe = "https://static.ivqonsanada.com/img/default-thumbnail-food.jpg";
                            }
                            String descRecipe = recipe_items.getString("recipe_description");

                            JSONObject nutrient = recipe_items.getJSONObject("recipe_nutrition");

                            String kaloriRecipe = nutrient.getString("calories") + " kcal";

                            resepArrayList.add(new ResepModel(idRecipe,
                                    namaRecipe, imgRecipe, descRecipe, kaloriRecipe));
                        }
                    }
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
            recyclerView = findViewById(R.id.recView_resep);
            adapter = new ResepAdapter(ResepActivity.this, resepArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(ResepActivity.this));
            recyclerView.setAdapter(adapter);
        }
    }
}