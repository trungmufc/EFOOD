package efood.com.food.Data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import efood.com.food.Data.database;
import efood.com.food.Data.dbhelper;
import efood.com.food.Model.Reciper;
import efood.com.food.Model.Schedule;


public class Dao_Schedule implements database {
    SQLiteDatabase db;

    public Dao_Schedule(Context context) {
        db = (new dbhelper(context)).getWritableDatabase();
    }

    public long insert(Schedule s) throws ParseException {
        ContentValues value = new ContentValues();
        value.put(reciper_id, s.getReciper_id());
        value.put(type_meal, s.getType_meal());
        value.put(date_meal, s.getDate_mead());
        value.put(status, s.getStatus());
        return db.insert(schedule, null, value);
    }

    public void Today(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String Simple = simpleDateFormat.format(date);
        String Select = "SELECT *FROM " + schedule + " WHERE " + date_meal + " <=" + date;
        Cursor c = db.rawQuery(Select, null);
    }

    public ArrayList<Schedule> getListByDate(int id, String Date) {
        ArrayList<Schedule> list = new ArrayList<>();
        String Select = "SELECT * " + "FROM " + schedule + " where " + Id + "  =  " + id +
                "ORDER BY " + date_meal + " ASC|DESC";
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Schedule ch = new Schedule();
                ch.setId(c.getInt(c.getColumnIndex(Id)));
                ch.setStatus(c.getInt(c.getColumnIndex(status)));
                ch.setReciper_id(c.getInt(c.getColumnIndex(reciper_id)));
                ch.setType_meal(c.getInt(c.getColumnIndex(type_meal)));
                ch.setDate_mead(c.getString(c.getColumnIndex(date_meal)));
                list.add(ch);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Schedule> getRecheduleBytype(int type, String date) throws ParseException {
        ArrayList<Schedule> list = new ArrayList<>();
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
        String Select = null;
        Select = "SELECT * FROM  " + schedule + " where "
                + date_meal + " like  '" + date + "'   AND  " + type_meal + " = " + type;//dateTime.format(dateTime1.parse(date));
        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Schedule s = new Schedule();
                s.setId(c.getInt(c.getColumnIndex(Id)));
                s.setReciper_id(c.getInt(c.getColumnIndex(reciper_id)));
                s.setStatus(c.getInt(c.getColumnIndex(status)));
                s.setType_meal(c.getInt(c.getColumnIndex(type_meal)));
                dateTime = new SimpleDateFormat("dd-MM-yyy");
                s.setDate_mead(dateTime.format(dateTime.parse(c.getString(c.getColumnIndex(date_meal)))));
                list.add(s);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Schedule> getlistByDateAndReciper(int type, String Date, int reId) throws ParseException {
        ArrayList<Schedule> list = new ArrayList<>();

        String Select = "SELECT *  FROM  " + schedule + " where "
                + date_meal + " like  '" + Date + "'   AND  " + type_meal + " = " + type
                + " And  " + reciper_id + " = " + reId;

        Cursor c = db.rawQuery(Select, null);
        if (c.moveToFirst()) {
            do {
                Schedule s = new Schedule();
                s.setId(c.getInt(c.getColumnIndex(Id)));
                s.setReciper_id(c.getInt(c.getColumnIndex(reciper_id)));
                s.setStatus(c.getInt(c.getColumnIndex(status)));
                s.setType_meal(c.getInt(c.getColumnIndex(type_meal)));
                SimpleDateFormat dateTime = new SimpleDateFormat("dd-MM-yyy");
                s.setDate_mead(dateTime.format(dateTime.parse(c.getString(c.getColumnIndex(date_meal)))));
                list.add(s);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Reciper> getItemReciper(int type, String date) {
        ArrayList<Reciper> list = new ArrayList<>();
        String SelectSchedule = "SELECT " + reciper_id + " FROM  " + schedule + " where "
                + date_meal + " like  '" + date + "'   AND  " + type_meal + " = " + type;
        Cursor cursor = db.rawQuery(SelectSchedule, null);
        if (cursor.moveToFirst()) {
            do {
                String Select = "SELECT * FROM " + reciper + " where " + Id + " = " + cursor.getInt(cursor.getColumnIndex(reciper_id));
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
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Delete(int id) {
        return db.delete(reciper, Id + " =?", new String[]{String.valueOf(id)});
    }

    //     public void set
//     public void
    public ArrayList<Schedule> Schecdules(String date, int type) {
        ArrayList<Schedule> list = new ArrayList<>();
        String Select = "Select * from " + schedule + " where " + date_meal + " >= '" + date + "'" + " and " + type_meal + " = " + type + " ORDER BY " + date_meal;
        Log.e("SÁAS", Select + "");
        Cursor cursor = db.rawQuery(Select, null);
        if (cursor.moveToFirst()) {
            do {

                Schedule schedule = new Schedule();
                                                            /// 0
                schedule.setId(cursor.getInt(cursor.getColumnIndex(Id)));
                schedule.setStatus(cursor.getInt(cursor.getColumnIndex(status)));
                schedule.setDate_mead(cursor.getString(cursor.getColumnIndex(date_meal)));
                schedule.setType_meal(cursor.getInt(cursor.getColumnIndex(type_meal)));
                schedule.setReciper_id(cursor.getInt(cursor.getColumnIndex(reciper_id)));
                list.add(schedule);
            } while (cursor.moveToNext());
        }
        Log.e("sâssaas", "ssss" + list.size() + "Sss");
        return list;
    }
}
/* */