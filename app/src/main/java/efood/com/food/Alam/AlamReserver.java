package efood.com.food.Alam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.text.ParseException;
import java.util.Calendar;

import efood.com.food.Alam.Demo.BReserver;
import efood.com.food.Data.Preferen;

/**
 * Created by loc on 12/03/2016.
 */

public class AlamReserver extends WakefulBroadcastReceiver {
    private AlarmManager alarmManager;
    PendingIntent pendingIntent;
//    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, SchedureService.class);
        startWakefulService(context, service);
    }

    int i = 1;
//

    public void setAlam(Context context) throws ParseException {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NOTI", 0);
        Preferen preferen = new Preferen(context);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlamReserver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 10);
//        calendar.set(Calendar.YEAR, 2016);
//        calendar.set(Calendar.MINUTE, 10);
//        calendar.set(Calendar.SECOND, 00);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
//        Intent myIntent = new Intent(AlarmActivity.this, Receiver.class);
//        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
//        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        } else {
//
//            Log.e("Sasaá", "SÁAS");
//        }

//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 180000, AlarmManager.INTERVAL_DAY, pendingIntent);
//        ComponentName receiver = new ComponentName(context, BootReserver.class);
//        PackageManager pm = context.getPackageManager();
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);

    }

    //final
    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        // Disable {@code SampleBootReceiver} so that it doesn't automatical        ly restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, BReserver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
    // END_INCLUDE(cancel_alarm)
}




