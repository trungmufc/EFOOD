package efood.com.food.Alam;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import efood.com.food.MainActivity;
import efood.com.food.R;

/**
 * Created by loc on 12/03/2016.
 */
public class SchedureService extends IntentService {

    /// thuc hien notification
    private NotificationManager mNotificationManager;
    private int NOTIFICATION_ID = 1;

    public SchedureService() {
        super("");
    }

    @Override

    protected void onHandleIntent(Intent intent) {
        sendNotification("Bạn nên nấu ăn");
        AlamReserver.completeWakefulIntent(intent);
        Log.e("asasasa", "Service");

    }

    public void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Thông Báo")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
