package com.overall.developer.overrendicion2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.androidnetworking.AndroidNetworking;

import com.crashlytics.android.Crashlytics;

import com.overall.developer.overrendicion2.utils.background.SendDataService;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RendicionApplication extends Application {
    private static Context sContext;
    private static RendicionApplication sLiquidacionApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //21-03 por Gustavo M. para controlar la excepción cuando se quite de segundo plano
        try {
            startService(new Intent(this, SendDataService.class));
        }catch (Exception e){

        }

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
