package com.example.what2cook.view.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.what2cook.R;
import com.example.what2cook.model.RecipeSimple;
import com.example.what2cook.view.profile.ProfileActivity;

import java.util.ArrayList;

public class RecipeListDetailsActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list_view);
        listView = findViewById(R.id.listView);

        Toast.makeText(this, "Select the title of the recipe to save it", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Select the link to go to the full " +
                "recipe description", Toast.LENGTH_LONG).show();

        // Get user input
        Intent intent = getIntent();
        ArrayList<RecipeSimple> resultRecipesList =
                (ArrayList<RecipeSimple>) intent.getSerializableExtra("RECIPE_LIST");

        print(RecipeListDetailsActivity.class.getSimpleName());
        for (RecipeSimple rec : resultRecipesList) {
            print(rec.toString());
        }

        SharedPreferences preferences =
                getSharedPreferences("myapp", Context.MODE_PRIVATE);
        long userId = preferences.getLong("userId", 0);

        //choose your favorite adapter
        RecipeListAdapter adapter = new RecipeListAdapter(this, resultRecipesList, userId);
        listView.setAdapter(adapter);
    }

    public void onOpenProfileActivtyClick(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

}
