package com.example.overtakers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    /**
     * INITIALIZATION
     */

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "overtakers";

    public static final String TABLE_NEWS = "news";           // табилца новостей
    public static final String TABLE_USER = "user";           // таблица пользователя
    public static String TABLE_CHAT = "chat_general";           // таблица чатов
    public static final String TABLE_GOODS = "shop";          // таблица магазина (мерча)



    public static final String NEWS_DATE = "date";
    public static final String NEWS_CATEGORY = "category";
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_DESCRIPTION = "description";

    public static final String USER_LOGIN = "login";    // only for unique usersName
    public static final String USER_CRED = "cred";      // login + password = cred
    public static final String USER_CHATS = "chats";    // format: lol:kek:imho:omg
    public static final String USER_AVATAR = "avatar";

    public static final String CHAT_NAME = "name";
    public static final String CHAT_MESSAGE = "message"; // sort by date when output

    public static final String GOODS_TITLE = "title";
    public static final String GOODS_DESCRIPTION = "description";
    public static final String GOODS_SALE = "sale";
    public static final String GOODS_PRICE = "price";

    // orders
    private static final String CREATE_TABLE_NEWS = "create table "
            + TABLE_NEWS
            + "("
            + "_id"
            + " integer primary key autoincrement, "
            + NEWS_TITLE
            + " text not null, "
            + NEWS_DESCRIPTION
            + " text not null, "
            + NEWS_DATE
            + " text not null, "
            + NEWS_CATEGORY
            + " text not null"
            + ");";

    // user
    private static final String CREATE_TABLE_USER = "create table "
            + TABLE_USER
            + "("
            + "_id"
            + " integer primary key autoincrement, "
            + USER_LOGIN
            + " text not null, "
            + USER_CRED
            + " text not null, "
            + USER_AVATAR
            + " text not null, "
            + USER_CHATS
            + " text not null"
            + ");";

    // goods
    private static final String CREATE_TABLE_GOODS = "create table "
            + TABLE_GOODS
            + "("
            + "_id"
            + " integer primary key autoincrement, "
            + GOODS_TITLE
            + " text not null, "
            + GOODS_DESCRIPTION
            + " text not null, "
            + GOODS_SALE
            + " text not null, "
            + GOODS_PRICE
            + " text not null"
            + ");";

    // chat
    private static final String CREATE_TABLE_CHAT = "create table "
            + TABLE_CHAT
            + "("
            + "_id"
            + " integer primary key autoincrement, "
            + CHAT_NAME
            + " text not null, "
            + CHAT_MESSAGE
            + " text not null"
            + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NEWS);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GOODS);
        db.execSQL(CREATE_TABLE_CHAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        onCreate(db);
    }

    private ContentValues createContentValues(String[] parameters, String[] values) {
        ContentValues contentValues = new ContentValues();
        for (int i=0; i < parameters.length; i++) {
            contentValues.put(parameters[i], values[i]);
        }
        return contentValues;
    }

    /**
     * APPLICATION PROGRAM INTERFACE
     */

    public long createNewChat (SQLiteDatabase db, String chatName) {
        try {
            String chatNameLast = this.TABLE_CHAT;
            this.TABLE_CHAT = chatName;
            db.execSQL(CREATE_TABLE_CHAT);
            this.TABLE_CHAT = chatNameLast;
            return 0;
        } catch (Exception e) {return 1;}
    }

    public long dbRowCreate(String table, String[] parameters, String[] values) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues initialValues = createContentValues(parameters, values);
            long row = db.insert(table, null, initialValues);
            db.close();
            return row;
        } catch (Exception e) { return -1;}
    }

    public String[] dbRowGetById(String table, long id, String[] parameters) {
        try {
            Cursor mCursor = null;
            SQLiteDatabase db = this.getReadableDatabase();
            // get values by parameters array
            mCursor = db.query(true, table, parameters,
                    "_id" + "=" + id, null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
                // set values to new array by requested parameters (String[] parameters)
                String[] values = new String[parameters.length];
                for (int i=0; i < parameters.length; i++ ){
                    values[i] = mCursor.getString(i);
                }
                mCursor.close();
                db.close();
                return values;
            }
        } catch (Exception e) { Log.d("contentValues", "cant return in dbHelper " + e);}
        return new String[] {};
    }

    public Cursor dbRowAll(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(true, table, null, null, null, null, null, null, null);
    }

    public boolean dbRowUpdate(String table, long id, String[] parameters, String[] values) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValues = createContentValues(parameters, values);
        return db.update(table, updateValues, "_id" + "=" + id, null) > 0;
    }

    public void dbContentDelete(String TABLE) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE);
        db.close();
    }

    public String[] dbTablesName() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_schema WHERE type ='table' AND name NOT LIKE 'sqlite_%';", null);
        String[] tables = null;
        for (int i=0; i < cursor.getCount(); i++) {
            tables[i] = cursor.getString(i);
        }
        return tables;
    }
}
