package com.TugasPAPBL.tes.Service;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ServiceCall {

    private String TAG = "ServiceCall";

    public JSONObject searchFoodCall(String query) {
        JSONObject foods = null;
        RequestUrl reqUrl = new RequestUrl();
        try {
            URL url = new URL(reqUrl.searchFoodUrl(query));
            Log.e(TAG, "url : " + url.toString());
            URLConnection api = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null) builder.append(line);
            JSONObject food = new JSONObject(builder.toString());
            foods = food.getJSONObject("foods");
        } catch (Exception e) {
            Log.e(TAG, "searchFoodCall : " + e.toString());
        }
        return foods;
    }

    public JSONObject getFoodCall(Long id) {
        JSONObject foods = null;
        RequestUrl reqUrl = new RequestUrl();
        try {
            URL url = new URL(reqUrl.getFoodUrl(id));
            Log.e(TAG, "url : " + url.toString());
            URLConnection api = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null) builder.append(line);
            JSONObject food = new JSONObject(builder.toString());
            foods = food.getJSONObject("food");
        } catch (Exception e) {
            Log.e(TAG, "getFoodCall : " + e.toString());
        }
        return foods;
    }

    public JSONObject searchRecipeCall(String query) {
        JSONObject recipes = null;
        RequestUrl reqUrl = new RequestUrl();
        try {
            URL url = new URL(reqUrl.buildRecipesSearchUrl(query));
            Log.e(TAG, "url : " + url.toString());
            URLConnection api = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null) builder.append(line);
            JSONObject recipe = new JSONObject(builder.toString());
            recipes = recipe.getJSONObject("recipes");
        } catch (Exception e) {
            Log.e(TAG, "searchRecipeCall : " + e.toString());
        }
        return recipes;
    }

    public JSONObject getRecipeCall(Long id) {
        JSONObject recipes = null;
        RequestUrl reqUrl = new RequestUrl();
        try {
            URL url = new URL(reqUrl.buildRecipeGetUrl(id));
            Log.e(TAG, "url : " + url.toString());
            URLConnection api = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null) builder.append(line);
            JSONObject recipe = new JSONObject(builder.toString());
            recipes = recipe.getJSONObject("recipe");
        } catch (Exception e) {
            Log.e(TAG, "getRecipeCall : " + e.toString());
        }
        return recipes;
    }
}
