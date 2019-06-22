package com.example.what2cook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RecipeDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public RecipeDBHelper(Context context) {
        super(context, RecipeDBContract.DBName, null, RecipeDBContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(RecipeDBContract.CREATE_PROJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(RecipeDBContract.DROP_PROJECT_TABLE);
        onCreate(sqLiteDatabase);
    }

}
