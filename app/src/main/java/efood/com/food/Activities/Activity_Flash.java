package efood.com.food.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.ParseException;

import efood.com.food.Alam.AlamReserver;
import efood.com.food.Data.Preferen;
import efood.com.food.MainActivity;
import efood.com.food.Model.Time;
import efood.com.food.R;

public class Activity_Flash extends AppCompatActivity {
    Preferen preferen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferen = new Preferen(this);
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        setValueInfirst();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_fl);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } catch (Exception e) {
                    Log.e("ValueError", e.getMessage());
                }
            }
        };
        thread.start();
    }

    public void setValueInfirst() {
        SharedPreferences sharedPreferences = getSharedPreferences("NOTI", 0);
        if (preferen.getBREAK() == null && preferen.getLUNCH() == null && preferen.getDINNER() == null) {
            AlamReserver alamReserver =new AlamReserver();
            try {
                alamReserver.setAlam(this);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Time time = new Time(7, 00, 00);
            preferen.setBREAK(time);
            time = new Time(10, 00, 00);
            preferen.setDINNER(time);
            time = new Time(5, 00, 00);
            preferen.setLUNCH(time);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("RINGNOTIFICATION", true);
            editor.commit();
        }
    }
}
