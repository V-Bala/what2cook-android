package com.example.what2cook.restclient;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.util.List;

public class ApiHandler {

    private final static String TAG = ApiHandler.class.getSimpleName();
    private final static boolean DEBUG = true;
    private final static String APP_ID = "425ce525";
    private final static String APP_KEY = "4f33d222f3e99d09933fe0d8fde71e23";
    private final static String BASE_URL = "https://api.edamam.com";
    private final static String SEARCH_URL = BASE_URL + "/search" + "?q=";
    private final static String MAX_RESULT_LIMIT = "20";

    @TargetApi(Build.VERSION_CODES.O)
    public static String getRecipesByIngredientsList(List<String> ingredientsList) {
        // Build HTTP Request URL using ingredients list
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(SEARCH_URL);
        urlStringBuilder.append(String.join(", ", ingredientsList));
        urlStringBuilder.append("&app_id=" + APP_ID + "&app_key=" + APP_KEY + "&from=0&to="+MAX_RESULT_LIMIT);
        String finalUrl = urlStringBuilder.toString();
        print(finalUrl);
        return finalUrl;
    }

    /**
     * LOGGER
     */
    public static void log(List<String> ingredientsList) {
        if (DEBUG) {
            for (String ingredient : ingredientsList) {
                Log.d(TAG, ingredient);
            }
        }

        //String example = "https://api.edamam.com/search?q=chicken&app_id=425ce525&app_key" +
                //"=4f33d222f3e99d09933fe0d8fde71e23&from=0&to=3&calories=591-722&health=alcohol" +
                //"-free";
    }

    private static void print(String url) {
        System.out.println(TAG + "-" + url);
    }
}
