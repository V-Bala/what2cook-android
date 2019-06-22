package com.example.what2cook.restclient;

import com.example.what2cook.model.RecipeSimple;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class HttpResponseWrapper {

    private String jsonResponse;

    public HttpResponseWrapper(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public void convertToRecipesOverview(String jsonResponse) {
    }

    public List<RecipeSimple> convertJsonToRecipeObjectList() {
        // Initialize return list
        List<RecipeSimple> recipeObjectList = new ArrayList<RecipeSimple>();

        // Convert string to json objects for easy parsing
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(jsonResponse);
        JsonElement jsonHitsElement = json.get("hits");

        for (JsonElement hit : jsonHitsElement.getAsJsonArray()) {
            // Hit represents each result - in this case recipes
            JsonObject hitObject = hit.getAsJsonObject();
            JsonObject jsonRecipeObject = hitObject.get("recipe").getAsJsonObject();

            // Get relevant fields from the recipe object
            String label = jsonRecipeObject.get("label").getAsString();
            String imgUrl = jsonRecipeObject.get("image").getAsString();
            String originalRecipeUrl = jsonRecipeObject.get("url").getAsString();

            // Store in Recipe objects and return
            RecipeSimple recipeObject = new RecipeSimple(label, imgUrl, originalRecipeUrl);
            recipeObjectList.add(recipeObject);
        }

        return recipeObjectList;
    }

    public String getResponse() {
        return jsonResponse;
    }
}
