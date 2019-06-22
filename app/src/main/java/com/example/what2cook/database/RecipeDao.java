package com.example.what2cook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danazh on 4/18/18.
 */

public class RecipeDao {
    public static RecipeDao instance;

    public RecipeDBHelper projectPortalDBHelper;
    public SQLiteDatabase mReadableDB, mWritableDB;


    public RecipeDao(Context context){
        projectPortalDBHelper = new RecipeDBHelper(context);
    }

    public void openDb(){
        mReadableDB = projectPortalDBHelper.getReadableDatabase();
        mWritableDB = projectPortalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        mReadableDB.close();
        mWritableDB.close();
    }

    public static RecipeDao getInstance(Context context){
        if (instance == null)
            instance = new RecipeDao(context);
        return instance;
    }
}
