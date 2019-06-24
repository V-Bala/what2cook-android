package com.example.what2cook.view.profile;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(
            boolean logggedin,
            long userId,
            String username,
            String password){
        editor.putBoolean("loggedInmode",logggedin);
        editor.putLong("userId", userId);
        editor.putString("username", username); // Storing string
        editor.putString("password", password); // Storing string
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }
}
