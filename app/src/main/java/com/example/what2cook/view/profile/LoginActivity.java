package com.example.what2cook.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2cook.R;
import com.example.what2cook.database.DbHelper;
import com.example.what2cook.view.ingredients.IngredientsActivity;

/**
 * Class
 */
public class LoginActivity extends AppCompatActivity {
    private final static String TAG = LoginActivity.class.getSimpleName();
    private final int LOGIN_ATTEMPT_LIMIT = 3;

    // Initialize widgets
    TextView bannerTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signUpButton;

    private Session session;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bannerTextView = findViewById(R.id.bannerTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        // Handle user authentication
        db = new DbHelper(this);
        session = new Session(this);
        if(session.loggedin()) {
            startActivity(new Intent(LoginActivity.this, IngredientsActivity.class));
            finish();
        }
    }

    /**
     * Starts the {@link LoginActivity}
     * Called when the user taps the login button
     *
     *
     * @param view
     */
    public void onClickLogin(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (db.getUser(username, password)) {
            // Success user was able to login
            session.setLoggedin(true);
            Intent intent = new Intent(this, IngredientsActivity.class);
            startActivity(intent);
            finish();
        } else {
            displayToast("Wrong username/password");
        }
    }

    public void onClickRegister(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
