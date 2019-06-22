package com.example.what2cook.view.recipes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.what2cook.R;
import com.example.what2cook.model.RecipeSimple;
import com.example.what2cook.restclient.EdamamApiClient;
import com.example.what2cook.restclient.HttpResponseWrapper;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecipesListActivity extends AppCompatActivity {
    private final String TAG = RecipesListActivity.class.getSimpleName();

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_recipes_overview);

        // Get user input
        Intent intent = getIntent();
        List<String> userIngredientsList = intent.getStringArrayListExtra("INGREDIENTS_LIST");

        // Fetch recipes using API client
        EdamamApiClient client = new EdamamApiClient();
        HttpResponseWrapper httpWrapper = null;
        try {
            httpWrapper = client.getRecipesByIngredientsList(userIngredientsList);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<RecipeSimple> recipeList = httpWrapper.convertJsonToRecipeObjectList();
        Intent outgoingIntent = new Intent(this, RecipeListDetailsActivity.class);
        outgoingIntent.putExtra("RECIPE_LIST", (Serializable) recipeList);
        startActivity(outgoingIntent);
        finish();
    }

//    private void updateView(List<RecipeSimple> recipeList) {
//        StringBuilder sb = new StringBuilder();
//        for (RecipeSimple recipe : recipeList) {
//            sb.append(recipe.toString() + "\n");
//        }
//        recipesOverviewTextView.setText(sb.toString());
//    }

    /**
     * Handle API response and update view.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}