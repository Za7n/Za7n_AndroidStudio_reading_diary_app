package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.PrivateKey;

class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Diary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_diary";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "book_date";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_CHILD = "book_child";
    private static final String COLUMN_TEACHER = "book_teacher";
    //pages read
    //Child comments/enjoyment rating
    //Teacher/Parent comments

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_PAGES + " TEXT, " +
                        COLUMN_CHILD + " TEXT, " +
                        COLUMN_TEACHER + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String title, String date, String pages, String child, String teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_CHILD, child);
        cv.put(COLUMN_TEACHER, teacher);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    void updateData(String row_id, String title, String date, String pages, String child, String teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_CHILD, child);
        cv.put(COLUMN_TEACHER, teacher);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }
//deleteOneRow()
    void deleteData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
