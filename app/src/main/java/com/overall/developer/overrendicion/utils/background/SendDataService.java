package com.overall.developer.overrendicion.utils.background;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import com.overall.developer.overrendicion.utils.Util;

public class SendDataService extends Service
{
    private static BroadcastReceiver mBroadcastReceiver;
    public SendDataService() {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        initialBrodcast();

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mBroadcastReceiver = null;
    }

    private void initialBrodcast()
    {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                if (Util.isOnline())
                {
                    Toast.makeText(context,"hay internet", Toast.LENGTH_LONG).show();
                }
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mBroadcastReceiver, filter);

    }



}
