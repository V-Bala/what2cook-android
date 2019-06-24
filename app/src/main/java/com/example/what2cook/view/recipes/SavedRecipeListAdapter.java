package com.example.what2cook.view.recipes;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.what2cook.R;
import com.example.what2cook.database.DbHelper;
import com.example.what2cook.database.Recipe;

import java.util.ArrayList;

public class SavedRecipeListAdapter extends ArrayAdapter {

    private DbHelper db;

    public SavedRecipeListAdapter(Context context, ArrayList<Recipe> recipeList) {
        super(context, 0, recipeList);
        db = new DbHelper(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Recipe selectedRecipe = (Recipe) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_recipes_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        TextView tvFullUrl = (TextView) convertView.findViewById(R.id.tvFullUrl);
        tvFullUrl.setClickable(true);
        tvFullUrl.setMovementMethod(LinkMovementMethod.getInstance());

        // Populate the data into the template view using the data object
        tvLabel.setText(selectedRecipe.getTitle());
        tvFullUrl.setText(selectedRecipe.getUrl());
        // Return the completed view to render on screen
        return convertView;
    }

}
