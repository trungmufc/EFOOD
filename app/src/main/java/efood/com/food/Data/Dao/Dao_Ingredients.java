package efood.com.food.Data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import efood.com.food.Data.database;
import efood.com.food.Data.dbhelper;
import efood.com.food.Model.Ingredient;


public class Dao_Ingredients implements database {
    SQLiteDatabase db;

    public Dao_Ingredients(Context context) {
        db = (new dbhelper(context)).getWritableDatabase();
    }

    /*  public static String TABLE_INGREDIENS = "CREATE TABLE \"ingredients\" " +
                "(\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT , " +
                "\"title\" VARCHAR(255), " +
                "\"Re_Id\" INTEGER, " +
                "\"quantity\" DOUBLE, " +
                "\"unit_type\" INTEGER)";*/
    public long Insert(Ingredient i) {
        ContentValues values = new ContentValues();
        values.put(title, i.getTitle());
        values.put(Re_Id, i.getRe_id());
        values.put(quantity, i.getQuantity());
        values.put(unit_type, i.getUnit_Type());
        return db.insert(ingredients, null, values);
    }

    public ArrayList<Ingredient> GetlistByReciperId(int id) {
        ArrayList<Ingredient> list = new ArrayList<>();
        String Select = "select  *  from " + ingredients + " where " + Re_Id + " = " + id;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Ingredient i = new Ingredient();
                i.setId(c.getInt(c.getColumnIndex(Id)));
                i.setTitle(c.getString(c.getColumnIndex(title)));
                i.setRe_id(c.getInt(c.getColumnIndex(Re_Id)));
                i.setQuantity(c.getDouble(c.getColumnIndex(quantity)));
                i.setUnit_Type(c.getString(c.getColumnIndex(unit_type)));
                list.add(i);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Ingredient> getlistById(int id) {

        ArrayList<Ingredient> list = new ArrayList<>();
        String Select = "select  *  from " + ingredients + " where " + Id + " = " + id;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Ingredient i = new Ingredient();
                i.setId(c.getInt(c.getColumnIndex(Id)));
                i.setTitle(c.getString(c.getColumnIndex(title)));
                i.setRe_id(c.getInt(c.getColumnIndex(Re_Id)));
                i.setQuantity(c.getDouble(c.getColumnIndex(quantity)));
                i.setUnit_Type(c.getString(c.getColumnIndex(unit_type)));
                list.add(i);
            } while (c.moveToNext());
        }
        return list;
    }
//    public ArrayList<Ingredient> GetlistByReCiperID(int id) {
//        ArrayList<Ingredient> list = new ArrayList<>();
//        String Select = "select  *  from " + ingredients + " where " + Re_Id + " = " + id;
//        Cursor c = db.rawQuery(Select, null);
//        if (c.moveToFirst()) {
//            do {
//                Ingredient i = new Ingredient();
//                i.setId(c.getInt(c.getColumnIndex(Id)));
//                i.setTitle(c.getString(c.getColumnIndex(title)));
//                i.setRe_id(c.getInt(c.getColumnIndex(Re_Id)));
//                i.setQuantity(c.getDouble(c.getColumnIndex(quantity)));
//                i.setUnit_Type(c.getString(c.getColumnIndex(unit_type)));
//                list.add(i);
//            } while (c.moveToNext());
//        }
//        return list;
//    }


    public int DeleteByReId(int id) {
        return db.delete(ingredients, Re_Id + " =?", new String[]{String.valueOf(id)});
    }

    public int Update(Ingredient i) {
        ContentValues values = new ContentValues();
        values.put(title, i.getTitle());
        values.put(Re_Id, i.getRe_id());
        values.put(quantity, i.getQuantity());
        values.put(unit_type, i.getUnit_Type());
        Log.e("Inc","Success");
        return db.update(ingredients, values, Id + " =?", new String[]{String.valueOf(i.getId())});
    }

    public int DeleteById(int id) {
        return db.delete(ingredients, Id + " =?", new String[]{String.valueOf(id)});
    }
}
