package efood.com.food.Data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import efood.com.food.Data.database;
import efood.com.food.Data.dbhelper;
import efood.com.food.Model.Reciper;


/**
 * Created by loc on 01/03/2016.
 */
public class Dao_Reciper implements database {
    SQLiteDatabase db;

    public Dao_Reciper(Context context) {
        dbhelper dbhelper = new dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    /*    public static String TABLE_RECIPER = "CREATE TABLE \"reciper\" " +
                "(\"Id\" INTEGER PRIMARY KEY  AUTOINCREMENT ," +
                " \"title\" VARCHAR(255), " +
                "\"introdustion\" TEXT," +
                "\"link\" TEXT," +
                " \"servings\" INTEGER, " +

                "\"Cook_time\" INTEGER, " +
                "\"prep_time\" INTEGER, " +
                "\"category_id\" INTEGER)";*/

    public long Insert(Reciper r) {
        ContentValues values = new ContentValues();
        values.put(title, r.getTitle());
        values.put(link, r.getLink());
        values.put(servings, r.getNumber());
        values.put(introdustion, r.getContent());
        values.put(Cook_time, r.getCook_time());
        values.put(prep_time, r.getPrep_time());
        values.put(category_id, r.getId_cate());
        return db.insert(reciper, null, values);
    }


    public int Update(Reciper r) {
        ContentValues values = new ContentValues();
        values.put(title, r.getTitle());
        values.put(link, r.getLink());
        values.put(servings, r.getNumber());
        values.put(introdustion, r.getContent());
        values.put(Cook_time, r.getCook_time());
        values.put(prep_time, r.getPrep_time());
        values.put(category_id, r.getId_cate());
        return db.update(reciper, values, Id + " =?", new String[]{String.valueOf(r.getId())});
    }
    public ArrayList<Reciper> getlist() {
        ArrayList<Reciper> list = new ArrayList<>();
        String Select = "Select * from  " + reciper;
        Cursor c = db.rawQuery(Select, null);

        if (c.moveToFirst()) {
            do {
                Reciper r = new Reciper();
                r.setId(c.getInt(c.getColumnIndex(Id)));
                r.setContent(c.getString(c.getColumnIndex(introdustion)));
                r.setCook_time(c.getInt(c.getColumnIndex(Cook_time)));
                r.setId_cate(c.getInt(c.getColumnIndex(category_id)));
                r.setLink(c.getString(c.getColumnIndex(link)));
                r.setNumber(c.getInt(c.getColumnIndex(servings)));
                r.setPrep_time(c.getInt(c.getColumnIndex(prep_time)));
                r.setTitle(c.getString(c.getColumnIndex(title)));
                list.add(r);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Reciper> getlistById(int id) {
        ArrayList<Reciper> list = new ArrayList<>();
        String Select = "Select * from  " + reciper + " Where " + Id + " =  " + id;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Reciper r = new Reciper();
                r.setId(c.getInt(c.getColumnIndex(Id)));
                r.setContent(c.getString(c.getColumnIndex(introdustion)));
                r.setCook_time(c.getInt(c.getColumnIndex(Cook_time)));
                r.setId_cate(c.getInt(c.getColumnIndex(category_id)));
                r.setLink(c.getString(c.getColumnIndex(link)));
                r.setNumber(c.getInt(c.getColumnIndex(servings)));
                r.setPrep_time(c.getInt(c.getColumnIndex(prep_time)));
                r.setTitle(c.getString(c.getColumnIndex(title)));
                list.add(r);
            } while (c.moveToNext());
        }
        return list;
    }


    public Reciper getReciperById(int id) {
//        ArrayList<Reciper> list = new ArrayList<>();
        Reciper r = new Reciper();
        String Select = "Select * from  " + reciper + " Where " + Id + " =  " + id;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {

                r.setId(c.getInt(c.getColumnIndex(Id)));
                r.setContent(c.getString(c.getColumnIndex(introdustion)));
                r.setCook_time(c.getInt(c.getColumnIndex(Cook_time)));
                r.setId_cate(c.getInt(c.getColumnIndex(category_id)));
                r.setLink(c.getString(c.getColumnIndex(link)));
                r.setNumber(c.getInt(c.getColumnIndex(servings)));
                r.setPrep_time(c.getInt(c.getColumnIndex(prep_time)));
                r.setContent(c.getString(c.getColumnIndex(introdustion)));
                r.setTitle(c.getString(c.getColumnIndex(title)));
            } while (c.moveToNext());
        }
        return r;
    }

    public ArrayList<Reciper> getlistByCateId(int id) {
        ArrayList<Reciper> list = new ArrayList<>();
        String Select = "Select * from  " + reciper + " Where " + category_id + " =  " + id;
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Reciper r = new Reciper();
                r.setId(c.getInt(c.getColumnIndex(Id)));
                r.setContent(c.getString(c.getColumnIndex(introdustion)));
                r.setCook_time(c.getInt(c.getColumnIndex(Cook_time)));
                r.setId_cate(c.getInt(c.getColumnIndex(category_id)));
                r.setLink(c.getString(c.getColumnIndex(link)));
                r.setNumber(c.getInt(c.getColumnIndex(servings)));
                r.setPrep_time(c.getInt(c.getColumnIndex(prep_time)));
                r.setTitle(c.getString(c.getColumnIndex(title)));
                list.add(r);
            } while (c.moveToNext());
        }
        return list;
    }

    public int Delete(int id) {
        return db.delete(reciper, Id + " =?", new String[]{String.valueOf(id)});
    }
//    public int update() {
//        return db.update(TB_Hotel, values, HotelID + " =?", new String[]{String.valueOf(hotel.getHotelId())});k
//    }

//    public ArrayList<Reciper> getLisbyNameAndStyle(Date) {

//    }
}

