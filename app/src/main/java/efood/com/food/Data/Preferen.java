package efood.com.food.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import efood.com.food.Model.Time;

public class Preferen {
    //    Context context;
    String LUNCH = "LUNCH", DINNER = "DINNER", BREAK = "BREAK";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edtEditor;

    String IsNotification = "NOTIFICATION";
    String RINGNOTIFICATION = "RINGNOTIFICATION";
    String RESERNGNOTIFICATION = "RNGNOTIFICATION";

    public Preferen(Context context) {
        sharedPreferences = context.getSharedPreferences("TIME", 0);
        edtEditor = sharedPreferences.edit();
    }

    public Time getLUNCH() {
        Gson gson = new Gson();
        //
        String json = sharedPreferences.getString(LUNCH, "");
        Time time = gson.fromJson(json, Time.class);
        return time;
    }

    public boolean setLUNCH(Time time) {
        Gson gson = new Gson();
        // chuyen thanh chuoi
        String json = gson.toJson(time);
///
        edtEditor.putString(LUNCH, json);
        return edtEditor.commit();
    }

    public Time getDINNER() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(DINNER, "");
        Time time = gson.fromJson(json, Time.class);
        return time;
    }

    public boolean setDINNER(Time time) {
        Gson gson = new Gson();
        String json = gson.toJson(time);
        edtEditor.putString(DINNER, json);
        return edtEditor.commit();
    }

    public Time getBREAK() {
        Gson gson = new Gson();
        //
        String json = sharedPreferences.getString(BREAK, "");
        //
        Time time = gson.fromJson(json, Time.class);
        return time;
    }

    public boolean setBREAK(Time time) {
        // gọ gson
        Gson gson = new Gson();
        // thuc hinec chuyen doi doi tuong time thành mọt chuoi json
//        { hour : 1
//            minute : 02

//
// }
        String json = gson.toJson(time);
        // LƯU VÔ SAVE
        edtEditor.putString(BREAK, json);
        return edtEditor.commit();
    }
}
