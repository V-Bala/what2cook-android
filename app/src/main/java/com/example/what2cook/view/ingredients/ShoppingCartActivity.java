package com.example.what2cook.view.ingredients;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.what2cook.R;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    ListView ingredientsListView;
    Button closeShoppingCartButton;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ingredientsListView = findViewById(R.id.ingredientsListView);
        closeShoppingCartButton = findViewById(R.id.closeShoppingCartButton);

        // Get user input
        Intent intent = getIntent();
        ArrayList<String> userIngredientsList = intent.getStringArrayListExtra("INGREDIENTS_LIST");

        // Choose your favorite adapter
        ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, userIngredientsList);
        ingredientsListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void closeShoppingCartActivity(View v) {
        finish();
    }

}
