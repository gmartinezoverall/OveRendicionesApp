package com.overall.developer.overrendicion2.utils.background;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.overall.developer.overrendicion2.utils.Util;

public class SendDataService extends Service
{
    private static BroadcastReceiver mBroadcastReceiver;
    public SendDataService() {
    }

    @Override
    public IBinder onBind(Intent intent)
    {


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
        //mBroadcastReceiver = null;
        unregisterReceiver(mBroadcastReceiver); //25-04 por Gustavo M. para destruir el servicio en segundo plano cuando termina la aplicación
    }

    private void initialBrodcast()
    {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                if (Util.isOnline())
                {
                    //Toast.makeText(context,"hay internetssss", Toast.LENGTH_LONG).show();
                }
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        try {
            registerReceiver(mBroadcastReceiver, filter);
        }catch (Exception e){
        }

    }



}
