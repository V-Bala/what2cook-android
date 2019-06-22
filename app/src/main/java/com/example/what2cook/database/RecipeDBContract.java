package com.example.what2cook.database;

/**
 * Created by danazh on 4/18/18.
 */

public final class RecipeDBContract {
    public static final String DBName = "what2cook.db0";
    public static final int DB_VERSION = 2;

    public static final class ProjectContract{
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_PROJECT_ID = "recipe_id";
        public static final String COLUMN_PROJECT_Title = "recipe_title";
        public static final String COLUMN_PROJECT_SUMMARY = "recipe_summary";
        public static final String COLUMN_PROJECT_AUTHOR = "recipe_ingredients";
        public static final String COLUMN_PROJECT_LINKS = "project_links";
        public static final String COLUMN_PROJECT_ISFAVORITE = "project_isfavorite";
        public static final String COLUMN_PROJECT_SEARCH = "project_search";
    }

    public static final String CREATE_PROJECT_TABLE =
            "CREATE TABLE " +
            RecipeDBContract.ProjectContract.TABLE_NAME +
            "(" +
            RecipeDBContract.ProjectContract.COLUMN_PROJECT_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RecipeDBContract.ProjectContract.COLUMN_PROJECT_Title +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_SUMMARY +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_AUTHOR +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_LINKS +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_ISFAVORITE +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_SEARCH +
            " TEXT" +
            ");";

    public static final String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS "
            + RecipeDBContract.ProjectContract.TABLE_NAME;
}
