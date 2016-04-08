package com.swchung.softchain;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;

/**
 * Created by icete on 2016-04-05.
 */
/*
public class MainService extends IntentService {
    public static final String SVC_TAG = "SC_SERVICE";

    public MainService() {
        super("MainService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
        String message = intent.getStringExtra("clicked");
        Log.i(SVC_TAG, "button clicked " + message);

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
*/
public class MainService extends Service {

    public static final String SVC_TAG = "SC_SERVICE";
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            String value = msg.getData().getString("MSG_ID");
            Log.i(SVC_TAG, "arg1 : " + msg.arg1 +  ", data : " + value);

        }
    }
/*
    public void sendBroadcastNotification(Bundle extras) {
        Intent intentBroadcast = new Intent(BROADCAST_MESSAGE_NAME);
        intentBroadcast.putExtra(CoreConstants.EXTRA_INTENT_MSG_ID,
                mIntentMsgId);

        sendBroadcast(intentBroadcast);
    }
*/
    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.setData(intent.getExtras());
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }


}