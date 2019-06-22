package com.example.what2cook.restclient;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EdamamApiClient {
    private final static String TAG = EdamamApiClient.class.getSimpleName();

    public EdamamApiClient() {
    }
    // Status Response Codes
    // 200 - SUCCESS
    // 401 - ERROR
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public HttpResponseWrapper getRecipesByIngredientsList(List<String> ingredientsList) throws ExecutionException, InterruptedException {
        // TODO
        HttpRequestTask httpGetRequest = new HttpRequestTask(ingredientsList);
        String jsonResponse = httpGetRequest.execute().get();
        HttpResponseWrapper httpGetResponse = new HttpResponseWrapper(jsonResponse);
        return httpGetResponse;
    }

}