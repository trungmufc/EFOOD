package efood.com.food.Alam.Demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by loc on 19/03/2016.
 */
public class BReserver extends BroadcastReceiver {
    Receiver receiver;
    @Override
    public void onReceive(Context context, Intent intent) {
        receiver = new Receiver();
        receiver.SetAlamBreakfas(context);
    }
}
