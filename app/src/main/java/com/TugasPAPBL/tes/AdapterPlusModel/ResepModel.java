package com.TugasPAPBL.tes.AdapterPlusModel;

public class ResepModel {
    String recipeID, recipeName, recipeImage, recipeDesc;
    String kalori;

    public ResepModel(String recipeID, String recipeName, String recipeImage, String recipeDesc, String kalori) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.recipeDesc = recipeDesc;
        this.kalori = kalori;
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

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }

    public String getKalori() {
        return kalori;
    }

    public void setKalori(String kalori) {
        this.kalori = kalori;
    }
}
