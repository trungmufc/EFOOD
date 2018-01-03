package efood.com.food.Data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import efood.com.food.Data.database;
import efood.com.food.Data.dbhelper;
import efood.com.food.Model.mCategory;


public class Dao_Category implements database {
    SQLiteDatabase db;

    public Dao_Category(Context context) {
        db = (new dbhelper(context)).getWritableDatabase();
    }

    /*    public static String TABLE_CATEGORY = "CREATE TABLE \"mCategory\" " +
            "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
            "\"Title\" VARCHAR(255))";*/
    public long insert(mCategory c) {
        ContentValues values = new ContentValues();
        values.put(title, c.getTitle());
        return db.insert(Category, null, values);
    }

    public ArrayList<mCategory> getlist() {
        ArrayList<mCategory> categories = new ArrayList<>();
        String Select = "Select * from " + Category;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                mCategory ca = new mCategory();
                ca.setTitle(c.getString(c.getColumnIndex(title)));
                ca.setId(c.getInt(c.getColumnIndex(Id)));
                categories.add(ca);
            } while (c.moveToNext());
        }
        return categories;
    }

    public boolean getbyname(String name) {
        String Value = "";
        String Select = "Select   " + title + " from   " + Category + " WHERE   " + title
                + " like " + "'%" + name + "%'";
        Cursor cursor = db.rawQuery(Select, null);
        if (cursor.moveToFirst()) {
            do {
                Value = cursor.getString(cursor.getColumnIndex(title));
            } while (cursor.moveToNext());
        }
        if (!Value.trim().equals("")) return false;
        return true;
    }

    public mCategory getItemById(int id) {
        String Select = "Select * from   " + Category + " WHERE   " + Id
                + " = " + id;
        mCategory ca = new mCategory();
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                ca.setTitle(c.getString(c.getColumnIndex(title)));
                ca.setId(c.getInt(c.getColumnIndex(Id)));
            } while (c.moveToNext());
        }
        return ca;
    }

    public int delete(int id) {
        return db.delete(Category, Id + " =?", new String[]{String.valueOf(id)});

    }

    public int update(mCategory c) {
        ContentValues values = new ContentValues();
        values.put(title, c.getTitle());
        return db.update(Category, values, Id + " =?", new String[]{String.valueOf(c.getId())});
    }
}
