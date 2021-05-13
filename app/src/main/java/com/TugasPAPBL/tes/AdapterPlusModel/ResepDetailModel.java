package com.TugasPAPBL.tes.AdapterPlusModel;

public class ResepDetailModel {
    String recipeID, recipeName, recipeDesc, recipeImages;
    String direction, ingredient;

    public ResepDetailModel(String recipeID, String recipeName, String recipeDesc, String recipeImages, String direction, String ingredient) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeDesc = recipeDesc;
        this.recipeImages = recipeImages;
        this.direction = direction;
        this.ingredient = ingredient;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }

    public String getRecipeImages() {
        return recipeImages;
    }

    public void setRecipeImages(String recipeImages) {
        this.recipeImages = recipeImages;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}