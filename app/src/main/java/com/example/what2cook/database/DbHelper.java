package com.example.what2cook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.what2cook.model.RecipeSimple;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 4;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASS = "password";

    public static final String RECIPE_TABLE = "recipes";
    public static final String COLUMN_USERID = "recipe_userId";
    public static final String COLUMN_RECIPE_TITLE = "recipe_title";
    public static final String COLUMN_RECIPE_URL = "recipe_url";

    /*
    create table users(
        id integer primary key autoincrement,
        username text,
        password text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    /*
create table recipes(
    id integer primary key autoincrement,
    recipe_title text,
    recipe_url text);
 */
    public static final String CREATE_TABLE_RECIPES =
            "CREATE TABLE " + RECIPE_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERID +" INTEGER,"
            + COLUMN_RECIPE_TITLE + " TEXT,"
            + COLUMN_RECIPE_URL + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_RECIPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();
        Log.d(TAG, "user inserted" + id);
        return id;
    }

    public void saveRecipe(RecipeSimple recipeSimple, long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_TITLE, recipeSimple.getLabel());
        values.put(COLUMN_RECIPE_URL, recipeSimple.getUrl());
        values.put(COLUMN_USERID, userId);

        long id = db.insert(RECIPE_TABLE, null, values);
        db.close();
        Log.d(TAG, "recipe inserted" + id);
    }

    public ArrayList<Recipe> getSavedRecipesByUserId(long searchUserId) {
        ArrayList<Recipe> recipeQueryResultList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select * from  " + RECIPE_TABLE + " where " +
                COLUMN_USERID + " = " + "'"+searchUserId+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()) {
            int userIdColumn = cursor.getColumnIndexOrThrow("recipe_userId");
            int userIdOfCurrentRecord = cursor.getInt(userIdColumn);
            if (userIdOfCurrentRecord != searchUserId)
            {
                // Only interested in records for the specified user ID
                continue;
            }

            int index;
            index = cursor.getColumnIndexOrThrow("recipe_title");
            String recipeTitle = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("recipe_url");
            String recipeUrl = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("_id");
            long id = cursor.getLong(index);
            Recipe recipe = new Recipe(id, recipeTitle, recipeUrl);
            recipeQueryResultList.add(recipe);
        }

        return recipeQueryResultList;
    }

    public long getUserIdByUsernamePassword(String username, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_USERNAME + " = " + "'"+username+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            int index = cursor.getColumnIndexOrThrow("_id");
            long id = cursor.getLong(index);
            return id;
        }
        cursor.close();
        db.close();

        return -1;
    }
    public long getUser(String username, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_USERNAME + " = " + "'"+username+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            int index = cursor.getColumnIndexOrThrow("_id");
            long id = cursor.getLong(index);
            return id;

        }
        cursor.close();
        db.close();

        return -1;
    }

}
