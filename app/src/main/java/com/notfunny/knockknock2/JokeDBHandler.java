package com.notfunny.knockknock2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JokeDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jokesDB.sqlite";

    private static final String TABLE_JOKES = "jokes";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_FAVOURITE = "favourite";

    private Context context;

    public JokeDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, context.getExternalCacheDir() + "/" + DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String getJoke(String category) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_CATEGORY + " = \"" + category + "\"" +
                " ORDER BY RANDOM()";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            return cursor.getString(1).toString();
        return null;
    }

    public String getJoke(int jokeId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_CATEGORY + " = " + jokeId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            return cursor.getString(1).toString();
        return null;
    }
}