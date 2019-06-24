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

public class RegisterActivity extends AppCompatActivity{
    private Button reg;
    private TextView tvLogin;
    private EditText etUsername, etPass;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DbHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPass);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register(){
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();
        if(username.isEmpty() || pass.isEmpty()){
            displayToast("Username/password field empty");
        }else{
            long userId = db.addUser(username,pass);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
