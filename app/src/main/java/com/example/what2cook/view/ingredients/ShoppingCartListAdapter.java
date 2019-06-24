package com.example.what2cook.view.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.what2cook.R;

import java.util.ArrayList;

public class ShoppingCartListAdapter extends ArrayAdapter<String> {

    public ShoppingCartListAdapter(Context context, ArrayList<String> ingredientsList) {
        super(context, 0, ingredientsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String selectedIngredient = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_ingredients_cart_item, parent, false);
        }
        // Lookup view for data population
        TextView tvIngredientLabel = (TextView) convertView.findViewById(R.id.tvIngredientLabel);

        // Populate the data into the template view using the data object
        tvIngredientLabel.setText(selectedIngredient);
        // Return the completed view to render on screen
        return convertView;
    }

}
