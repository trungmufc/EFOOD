package efood.com.food.Alam.Demo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import efood.com.food.Data.Dao.Dao_Schedule;
import efood.com.food.Data.Preferen;
import efood.com.food.Model.Schedule;
import efood.com.food.Model.Time;

public class Receiver extends WakefulBroadcastReceiver {
    AlarmManager alarmManager;
    Preferen preferen;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }


    public void SetAlamBreakfas(Context context) {

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        preferen = new Preferen(context);
        //
        Dao_Schedule dao_schedule = new Dao_Schedule(context);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Schedule> c = dao_schedule.Schecdules(simpleDateFormat.format(date.getTime()), 1);

        Calendar calendar = Calendar.getInstance();

        if (c.size() > 0) {
            String st[] = c.get(0).getDate_mead().split("-");
            int year = Integer.parseInt(st[0]);
            int mounth = Integer.parseInt(st[1]);
            int day = Integer.parseInt(st[2]);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.MONTH, mounth);
            calendar.set(Calendar.YEAR, year);
        }
        Time time = preferen.getBREAK();
        calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
        calendar.set(Calendar.MINUTE, time.getMinutes());
        //----
        Intent myIntent = new Intent(context, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        ComponentName receiver = new ComponentName(context, BReserver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void SetAlamlunch(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        preferen = new Preferen(context);
        Log.d("MyActivity", "Alarm On");
        Calendar calendar = Calendar.getInstance();
        Time time = preferen.getLUNCH();
        calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
        calendar.set(Calendar.DAY_OF_MONTH, 19);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.MINUTE, time.getMinutes());
        Intent myIntent = new Intent(context, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }

}
