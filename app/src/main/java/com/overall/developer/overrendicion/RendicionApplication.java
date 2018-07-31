package com.overall.developer.overrendicion;

import android.app.Application;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RendicionApplication extends Application
{
    private static Context sContext;
    private static RendicionApplication sLiquidacionApplication ;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        sLiquidacionApplication = this;
        sContext = getApplicationContext();
        AndroidNetworking.initialize(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("DBLiquidacion.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);


    }


    public static Context getContext() {
        return sContext;
    }
}
