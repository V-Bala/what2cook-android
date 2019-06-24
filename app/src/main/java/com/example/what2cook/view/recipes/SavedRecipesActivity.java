package com.example.what2cook.view.recipes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.what2cook.R;
import com.example.what2cook.database.Recipe;

import java.util.ArrayList;

public class SavedRecipesActivity extends AppCompatActivity {

    ListView savedRecipeListView;
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
        super.setContentView(R.layout.activity_saved_recipes_list);
        savedRecipeListView = findViewById(R.id.savedRecipeListView);

        // Get user input
        Intent intent = getIntent();
        ArrayList<Recipe> resultRecipesList =
                intent.getParcelableArrayListExtra("RECIPE_LIST");
        SavedRecipeListAdapter adapter = new SavedRecipeListAdapter(this, resultRecipesList);
        savedRecipeListView.setAdapter(adapter);
        Toast.makeText(this, "Showing all recipes you've saved", Toast.LENGTH_LONG).show();
    }

    public void closeSavedRecipesActivity(View v) {
        finish();
    }

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
