package com.example.what2cook.view.recipes;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2cook.R;
import com.example.what2cook.model.RecipeSimple;
import com.example.what2cook.restclient.HttpImageRequestTask;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecipesSwipeActivity extends AppCompatActivity {

    private List<String> recipesSwipeViewList;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    int imageIndex = 0;
    private List<String> recipeImageUrlList = new ArrayList<>();
    List<Drawable> listOfDrawableImages;

    SwipeFlingAdapterView flingContainer;
    TextView recipeSuggestionsBanner;
    ImageView recipeImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_recipes_swipe_view);

        // Get user input
        Intent intent = getIntent();
        List<RecipeSimple> resultRecipesList =
                (List<RecipeSimple>) intent.getSerializableExtra("RECIPE_LIST");

        print(RecipesSwipeActivity.class.getSimpleName());
        for (RecipeSimple rec : resultRecipesList) {
            print(rec.toString());
        }

        //add the view via xml or programmatically
        recipeSuggestionsBanner = findViewById(R.id.recipeSuggestionsBanner);
        recipeImageView = findViewById(R.id.recipeImageView);
        flingContainer = findViewById(R.id.recipeSwipeViewContainer);

        recipesSwipeViewList = new ArrayList<>();
        for (RecipeSimple recipeSimple : resultRecipesList) {
            // Traverse the recipes that were returned
            // And add to viewable list
            recipeImageUrlList.add(recipeSimple.getImage());
            recipesSwipeViewList.add(recipeSimple.getLabel() + "\n" + recipeSimple.getUrl());
        }
        //choose your favorite adapter
        arrayAdapter = new ArrayAdapter<>(this,
                R.layout.activity_recipes_swipe_item, R.id.recipeSuggestionView, recipesSwipeViewList );

        // Download images in an async background task
        HttpImageRequestTask httpImageRequestTask = new HttpImageRequestTask(recipeImageUrlList);

        try {
            listOfDrawableImages = httpImageRequestTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //set the listener and the adapter
        recipeImageView.setImageDrawable(listOfDrawableImages.get(imageIndex));
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                // Currently a No-Op
//                Log.d("LIST", "removed object!");
//                recipesSwipeViewList.remove(0);
//                arrayAdapter.notifyDataSetChanged();
                print("removeFirstObjectInAdapter()");
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(RecipesSwipeActivity.this, "Left!", Toast.LENGTH_SHORT).show();
                imageIndex--;
                if (imageIndex < 0) {
                    imageIndex = 0;
                }
                recipeImageView.setImageDrawable(listOfDrawableImages.get(imageIndex));
                print("onLeftCardExit()");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(RecipesSwipeActivity.this, "Right!", Toast.LENGTH_SHORT).show();
                imageIndex++;
                if (imageIndex > arrayAdapter.getCount()) {
                    imageIndex = arrayAdapter.getCount() - 1;
                }
                recipeImageView.setImageDrawable(listOfDrawableImages.get(imageIndex));
                print("onRightCardExit()");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                recipesSwipeViewList.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                // TODO - View details for each recipe
                print("onItemClicked()");
            }
        });
    }

    private void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}