package com.example.what2cook.view.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.what2cook.R;
import com.example.what2cook.model.RecipeSimple;

import java.util.ArrayList;

public class RecipeListDetailsActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list_view);

        // Get user input
        Intent intent = getIntent();
        ArrayList<RecipeSimple> resultRecipesList =
                (ArrayList<RecipeSimple>) intent.getSerializableExtra("RECIPE_LIST");

        print(RecipeListDetailsActivity.class.getSimpleName());
        for (RecipeSimple rec : resultRecipesList) {
            print(rec.toString());
        }

        listView = findViewById(R.id.listView);

        //choose your favorite adapter
        RecipeListAdapter adapter = new RecipeListAdapter(this, resultRecipesList);
        listView.setAdapter(adapter);
    }

    private void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

}
