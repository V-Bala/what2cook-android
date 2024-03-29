package com.example.what2cook.view.recipes;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2cook.R;
import com.example.what2cook.database.DbHelper;
import com.example.what2cook.model.RecipeSimple;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<RecipeSimple> {

    private DbHelper db;
    private long userId;

    public RecipeListAdapter(Context context,
                             ArrayList<RecipeSimple> recipeList,
                             long userId) {
        super(context, 0, recipeList);
        this.db = new DbHelper(getContext());
        this.userId = userId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final RecipeSimple selectedRecipe = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_recipes_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        TextView tvFullUrl = (TextView) convertView.findViewById(R.id.tvFullUrl);

        tvLabel.setClickable(true);
        tvLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Saved recipe to database",
                        Toast.LENGTH_LONG).show();
                db.saveRecipe(selectedRecipe, userId);
            }
        });
        tvFullUrl.setClickable(true);
        tvFullUrl.setMovementMethod(LinkMovementMethod.getInstance());

        // Populate the data into the template view using the data object
        tvLabel.setText(selectedRecipe.getLabel());
        tvFullUrl.setText(selectedRecipe.getUrl());
        // Return the completed view to render on screen
        return convertView;
    }

}
