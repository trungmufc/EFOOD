package efood.com.food.Data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import efood.com.food.Data.database;
import efood.com.food.Data.dbhelper;

public class UserDao implements database {
    SQLiteDatabase db;

    public UserDao(Context context) {
        dbhelper dbhelper = new dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long Insert() {
        ContentValues values = new ContentValues();

        return 0;
    }


}
