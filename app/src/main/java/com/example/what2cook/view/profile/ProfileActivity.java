package com.example.what2cook.view.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2cook.MainActivity;
import com.example.what2cook.R;
import com.example.what2cook.database.DbHelper;
import com.example.what2cook.database.Recipe;
import com.example.what2cook.view.ingredients.IngredientsActivity;
import com.example.what2cook.view.recipes.SavedRecipesActivity;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private DbHelper db;
    private long activeUserId;
    private Session session;
    SharedPreferences preferences;
    TextView userNameTextViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameTextViewProfile = findViewById(R.id.userNameTextViewProfile);

        db = new DbHelper(this);
        session = new Session(this);

        preferences =
                getSharedPreferences("myapp", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "default");
        long userId = preferences.getLong("userId", 0);
        if (userId < 0) {
            Toast.makeText(getApplicationContext(), "Error user with ID " + userId + "does not " +
                            "exist in the database",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        userNameTextViewProfile.setText(username);
        activeUserId = userId;
    }

    public void onStartIngredientsActivityClick(View v) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        startActivity(intent);
        finish();
    }
    public void onLogoutButtonClicked(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        session.setLoggedin(false, activeUserId, preferences.getString("username", ""),
                preferences.getString("password", ""));
        startActivity(intent);
        finish();
    }
    public void onShowSavedRecipesList(View v) {
        ArrayList<Recipe> userSavedRecipeList = db.getSavedRecipesByUserId(activeUserId);
        Intent intent = new Intent(this, SavedRecipesActivity.class);
        intent.putParcelableArrayListExtra("RECIPE_LIST", userSavedRecipeList);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
            }
            else {
                // failed to un-install
            }
        }
    }

}
