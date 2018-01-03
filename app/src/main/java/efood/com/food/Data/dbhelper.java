package efood.com.food.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dbhelper extends SQLiteOpenHelper implements database {
    public dbhelper(Context context) {
        super(context, "data.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORY);
        db.execSQL(TABLE_INGREDIENS);
        db.execSQL(TABLE_RECIPER);
        db.execSQL(TABLE_SCHEDULE);
        db.execSQL(TABLE_USER);
        db.execSQL("insert into " + Category + " values(1,'Món khai vị')");
        db.execSQL("insert into " + Category + " values(2,'Thức uống')");
        db.execSQL("insert into " + Category + " values(3,'Món chính')");
        db.execSQL("insert into " + Category + " values(4,'Món rau thêm vào')");
        db.execSQL("insert into " + Category + " values(5,'Salad')");
        db.execSQL("insert into " + Category + " values(6,'Tráng miệng')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + User + " if  exists");
//        db.execSQL("drop table " + ingredients + " if exists");
//        db.execSQL("drop table " + mCategory + " if exists");
//        db.execSQL("drop table " + reciper + " if exists");
//        db.execSQL("drop table " + Schedule + " if exists");


    }
}
