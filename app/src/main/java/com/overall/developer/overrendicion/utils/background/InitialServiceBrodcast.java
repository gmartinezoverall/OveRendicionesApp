package com.overall.developer.overrendicion.utils.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.overall.developer.overrendicion.utils.Util;

public class InitialServiceBrodcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (Util.isOnline())
        {
            Toast.makeText(context,"hay internet", Toast.LENGTH_LONG).show();
        }

    }
}
