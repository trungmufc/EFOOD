package efood.com.food.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.util.Calendar;

import efood.com.food.Alam.AlamReserver;
import efood.com.food.Alam.Demo.Receiver;
import efood.com.food.Data.Preferen;
import efood.com.food.MainActivity;
import efood.com.food.Model.Time;
import efood.com.food.R;

public class Activity_Setting extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    LinearLayout container;
    SharedPreferences sharedPreferences;
    Preferen preferen;
    String IsNotification = "NOTIFICATION";
    String RINGNOTIFICATION = "RINGNOTIFICATION";
    String RESERNGNOTIFICATION = "RNGNOTIFICATION";
    SharedPreferences.Editor editor;
    AlamReserver alamReserver;
    RelativeLayout btn_Breakfas, btn_lunch, btn_dinner;
    TextView txtBreakfash, tx_lunch, txt_dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        alamReserver = new AlamReserver();
        preferen = new Preferen(this);
        Actionbar();
        init();
        First();
        insert();
    }

    public void Actionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Thông báo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId())
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return super.onOptionsItemSelected(item);
    }


    public String Convert(Time time) {
        String list = new String();
        String sthour = time.getHour() < 10 ? "0" + time.getHour() : "" + time.getHour();
        String Stminutes = time.getMinutes() < 10 ? "0" + time.getMinutes() + "" : "" + time.getMinutes();
        String Stsec = time.getSeccon() < 10 ? "0" + time.getSeccon() + "" : "" + time.getSeccon();
        list = sthour + ":" + Stminutes + ":" + Stsec;
        return list;
    }

    public void First() {
        Time time = preferen.getBREAK();
        txtBreakfash.setText(Convert(time));
        time = preferen.getLUNCH();
        tx_lunch.setText(Convert(time));
        time = preferen.getDINNER();
        txt_dinner.setText(Convert(time));
    }

    public void TimeInit() {
        btn_Breakfas = (RelativeLayout) findViewById(R.id.btn_Breakfas);
        btn_lunch = (RelativeLayout) findViewById(R.id.btn_lunch);
        btn_dinner = (RelativeLayout) findViewById(R.id.btn_dinner);
        btn_Breakfas.setOnClickListener(this);
        btn_lunch.setOnClickListener(this);
        btn_dinner.setOnClickListener(this);
    }

    public void insert() {
        ra1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(IsNotification, true);
                editor.putBoolean(RINGNOTIFICATION, false);
                editor.putBoolean(RESERNGNOTIFICATION, false);
                alamReserver.cancelAlarm(Activity_Setting.this);
                Log.e("Alam", "Notifcation");
                editor.commit();
            }
        });
        ra2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    editor.putBoolean(IsNotification, false);
                    editor.putBoolean(RINGNOTIFICATION, true);
                    editor.putBoolean(RESERNGNOTIFICATION, false);
                    alamReserver.setAlam(Activity_Setting.this);
                    editor.commit();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        ra3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    editor.putBoolean(IsNotification, false);
                    editor.putBoolean(RINGNOTIFICATION, false);
                    editor.putBoolean(RESERNGNOTIFICATION, true);
                    alamReserver.setAlam(Activity_Setting.this);
                    editor.commit();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    RadioButton ra1, ra2, ra3;

    private void init() {
        sharedPreferences = getSharedPreferences("NOTI", 0);
        editor = sharedPreferences.edit();
        container = (LinearLayout) findViewById(R.id.container);
        ra1 = (RadioButton) findViewById(R.id.ra1);
        ra2 = (RadioButton) findViewById(R.id.ra2);
        ra3 = (RadioButton) findViewById(R.id.ra3);

        txtBreakfash = (TextView) findViewById(R.id.txtBreakfash);
        tx_lunch = (TextView) findViewById(R.id.tx_lunch);
        txt_dinner = (TextView) findViewById(R.id.txt_dinner);
        TimeInit();
        ra1.setChecked(sharedPreferences.getBoolean(IsNotification, false));
        ra2.setChecked(sharedPreferences.getBoolean(RINGNOTIFICATION, false));
        ra3.setChecked(sharedPreferences.getBoolean(RESERNGNOTIFICATION, false));

    }

    @Override
    public void onResume() {
        super.onResume();
        TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (tpd != null) tpd.setOnTimeSetListener(this);
        if (dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Breakfas:
                DateTime("Buổi Sáng");
                break;
            case R.id.btn_dinner:
                DateTime("Buổi Tối");
                break;
            case R.id.btn_lunch:
                DateTime("Buổi Trưa");

                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
//        dateTextView.setText(date);
        Log.e("ssassa", date);
    }

    public void DateTime(final String title) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

                        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                        String minuteString = minute < 10 ? "0" + minute : "" + minute;
                        String secondString = second < 10 ? "0" + second : "" + second;

                        if (title.trim().equals("Buổi Sáng")) {
                            if (hourOfDay >= 10 || hourOfDay <= 4) {
                                Snackbar.make(container, "Buổi sáng của quá trễ", Snackbar.LENGTH_SHORT).show();
                            } else {

                                txtBreakfash.setText(hourString + ":" + minuteString + ":" + secondString);
                                Time time = new Time(hourOfDay, minute, second);
                                preferen.setBREAK(time);

                                Receiver alamReserver = new Receiver();
                                alamReserver.SetAlamBreakfas(Activity_Setting.this);
                            }
                        } else if (title.trim().equals("Buổi Trưa")) {
                            tx_lunch.setText(hourString + ":" + minuteString + ":" + secondString);
                            Time time = new Time(hourOfDay, minute, second);
                            preferen.setLUNCH(time);
                            Receiver alamReserver = new Receiver();
                            alamReserver.SetAlamlunch(Activity_Setting.this);
                        } else if (title.trim().equals("Buổi Tối")) {
                            if (hourOfDay >= 1 && hourOfDay <= 5) {
                                Snackbar.make(container, "Buổi tối  của quá sớm", Snackbar.LENGTH_SHORT).show();
                            } else {
                                txt_dinner.setText(hourString + ":" + minuteString + ":" + secondString);
                                Time time = new Time(hourOfDay, minute, second);
                                preferen.setDINNER(time);
                                Receiver alamReserver = new Receiver();
                                alamReserver.SetAlamBreakfas(Activity_Setting.this);
                            }
                        }
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.setThemeDark(true);
        tpd.vibrate(false);
        tpd.dismissOnPause(false);
        tpd.enableSeconds(true);
        if (true) {
            tpd.setAccentColor(Color.parseColor("#8BC34A"));
        }

        tpd.setTitle(title);
        tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("TimePicker", "Dialog was cancelled");
            }
        });
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

    }
}


