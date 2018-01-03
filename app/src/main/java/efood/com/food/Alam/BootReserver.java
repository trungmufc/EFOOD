package efood.com.food.Alam;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by loc on 12/03/2016.
 */
public class BootReserver extends BroadcastReceiver {
    AlamReserver Alarm = new AlamReserver();

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Toast.makeText(((Activity)context), "roo", Toast.LENGTH_SHORT).show();
            Alarm.setAlam(context);
        } catch (Exception e) {

        }

    }
}
