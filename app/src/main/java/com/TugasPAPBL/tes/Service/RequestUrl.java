package com.TugasPAPBL.tes.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestUrl {

    final static private String HTTP_METHOD = "GET";
    final static private String APP_URL = "https://platform.fatsecret.com/rest/server.api";

    FatSecretHandler fshClass = new FatSecretHandler();

    //mendapatkan url food.search dari api fatsecret
    public String searchFoodUrl(String query) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(fshClass.getOauthParameters()));
        String[] template = new String[1];
        params.add("method=foods.search");
        params.add("region=ID");
        params.add("language=id");
        params.add("format=json");
        params.add("search_expression=" + fshClass.encode(query));
        params.add("oauth_signature=" + fshClass.signature(HTTP_METHOD, APP_URL, params.toArray(template)));
        return APP_URL + "?" + fshClass.paramify(params.toArray(template));
    }

    //mendapatkan url food.get dari api fatsecret
    public String getFoodUrl(Long id) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(fshClass.getOauthParameters()));
        String[] template = new String[1];
        params.add("method=food.get");
        params.add("region=ID");
        params.add("language=id");
        params.add("format=json");
        params.add("food_id=" + id);
        params.add("oauth_signature=" + fshClass.signature(HTTP_METHOD, APP_URL, params.toArray(template)));
        return APP_URL + "?" + fshClass.paramify(params.toArray(template));
    }

    //mendapatkan url recipes.search dari api fatsecret
    public String buildRecipesSearchUrl(String query) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(fshClass.getOauthParameters()));
        String[] template = new String[1];
        params.add("method=recipes.search");
        params.add("region=ID");
        params.add("language=id");
        params.add("format=json");
        params.add("search_expression=" + fshClass.encode(query));
        params.add("oauth_signature=" + fshClass.signature(HTTP_METHOD, APP_URL, params.toArray(template)));
        return APP_URL + "?" + fshClass.paramify(params.toArray(template));
    }

    //mendapatkan url recipe.get dari api fatsecret
    public String buildRecipeGetUrl(Long id) throws Exception {
        List<String> params = new ArrayList(Arrays.asList(fshClass.getOauthParameters()));
        String[] template = new String[1];
        params.add("method=recipe.get");
        params.add("region=ID");
        params.add("language=id");
        params.add("format=json");
        params.add("recipe_id=" + id);
        params.add("oauth_signature=" + fshClass.signature(HTTP_METHOD, APP_URL, params.toArray(template)));
        return APP_URL + "?" + fshClass.paramify(params.toArray(template));
    }

}
