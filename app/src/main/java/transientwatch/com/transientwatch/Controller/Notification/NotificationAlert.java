package transientwatch.com.transientwatch.Controller.Notification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import transientwatch.com.transientwatch.R;

public class NotificationAlert extends Activity {

    private static final int NOTIFY_ME_ID=1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        /*********** Create notification ***********/

//        final NotificationManager mgr=
//                (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification note=new Notification(R.drawable.ic_launcher,
//                "Android Example Status message!",
//                System.currentTimeMillis());
//
//        // This pending intent will open after notification click
//        PendingIntent i=PendingIntent.getActivity(this, 0,
//                new Intent(this, NotifyMessage.class),
//                0);
//
//        note.setLatestEventInfo(this, "Android Example Notification Title",
//                "This is the android example notification message", i);
//
//        //After uncomment this line you will see number of notification arrived
//        //note.number=2;
//        mgr.notify(NOTIFY_ME_ID, note);
    }
}