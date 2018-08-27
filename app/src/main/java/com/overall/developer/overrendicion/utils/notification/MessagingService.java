package com.overall.developer.overrendicion.utils.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.Notification.NotificationEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;

public class MessagingService extends FirebaseMessagingService
{
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationEntity mNotification = new NotificationEntity();
        mNotification.setId(remoteMessage.getFrom());
        mNotification.setTitulo(remoteMessage.getNotification().getTitle());
        mNotification.setDescripcion(remoteMessage.getNotification().getBody());
        showNotification(mNotification);

    }

    private void showNotification(NotificationEntity notification)
    {
        Intent intent = new Intent(this, RendicionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "chanelID")
                .setSmallIcon(R.drawable.ic_add_a_photo)
                .setContentTitle(notification.getTitulo())
                .setContentText(notification.getDescripcion())
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }
}
