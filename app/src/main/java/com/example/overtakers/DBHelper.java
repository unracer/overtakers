package com.example.overtakers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bafi";

    public static final String TABLE_ORDERS = "orders";         // табилца заказов
    public static final String TABLE_USER = "user";             // таблица пользователя
    public static final String TABLE_GOODS = "goods";           // таблица товаров (мерча)

    public static final String KEY_ID = "_id";
    public static final String KEY_PARAM = "param";
    public static final String KEY_VALUE = "value";

    // for fill table will method

    // good work mean if after coding world locks newest and your brain joy touching things


    // orders
    private static final String CREATE_TABLE_ORDERS = "create table "
            + TABLE_ORDERS
            + "("
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_PARAM
            + " text not null, "
            + KEY_VALUE
            + " text not null"
            + ");";

    // user
    private static final String CREATE_TABLE_USER = "create table "
            + TABLE_USER
            + "("
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_PARAM
            + " text not null, "
            + KEY_VALUE
            + " text not null"
            + ");";

    // goods
    private static final String CREATE_TABLE_GOODS = "create table "
            + TABLE_GOODS
            + "("
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_PARAM
            + " text not null, "
            + KEY_VALUE
            + " text not null"
            + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** СОЗДАНИЕ  ТАБЛТИЦ */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GOODS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);
        onCreate(db);
    }
    /**
     * СОЗДАНИЕ  НОВОЙ  ЗАПИСИ.
     * Если создан успешно - возвращается номер строки rowId, иначе -1
     */

    public long createRow(String table, String parameter, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = createContentValues(parameter, value);
        long row = db.insert(table, null, initialValues);
        db.close();
        return row;
    }

    //////////////////

    public long createRowORDER(String table, String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = createContentValues(key, value);
        long row = db.insert(table, null, initialValues);
        db.close();
        return row;
    }

    public boolean updateRow(String table, String parameter, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValues = createContentValues(parameter, value);
        return db.update(table, updateValues, KEY_PARAM + "=" + parameter, null) > 0;
    }

    // accept key
    // return value
    public Cursor getRow(String table, String parameter) throws SQLException {
        Cursor mCursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            mCursor = db.query(true, table, new String[] { KEY_ID, KEY_PARAM, KEY_VALUE },
                    KEY_PARAM + "=" + parameter, null, null, null, null, null);
            if (mCursor != null) { mCursor.moveToFirst(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mCursor;
    }

    public Cursor getFull(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(table, null, null,
                null, null, null, null);
    }

    public void deleteAll(String TABLE) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE);
        db.close();
    }

    private ContentValues createContentValues(String parameter, String value) {
        ContentValues values = new ContentValues();
        values.put(parameter, value);
        return values;
    }
}
