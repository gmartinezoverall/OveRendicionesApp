package com.overall.developer.overrendicion.data.repository.Rendicion.api;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;
import com.overall.developer.overrendicion.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiRendicionImpl implements ApiRendicion
{
    RendicionRepository mRepository;

    public ApiRendicionImpl(RendicionRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void deleteRendicionForCodApi(String codCodRendicion)
    {
        deleteRendicionForApi(codCodRendicion);
    }

    @Override
    public void insertListRendicionesApi(String codLiquidacion)
    {
        insertListRendiciones(codLiquidacion);
    }

    private void insertListRendiciones(String codLiquidacion)
    {
        Rx2AndroidNetworking.post(UrlApi.urlListarRendicion)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codLiquidacion", codLiquidacion)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        Gson gson = new Gson();
                        Type collectionType = new TypeToken<Collection<RendicionBean>>(){}.getType();
                        List<RendicionBean> mRendionList = null;
                        try
                        {
                            mRendionList = gson.fromJson(jsonObject.getString("rendicion"), collectionType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mRepository.insertListRendicionInDB(mRendionList);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete()
                    {
                        mRepository.insertListCompleted();
                    }
                });
    }

    private void deleteRendicionForApi(String codCodRendicion)
    {
        Rx2AndroidNetworking.post(UrlApi.urlEliminarRendicion)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codRendicion", codCodRendicion)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        Log.i("NDa", jsonObject.toString());

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i("NDa", e.toString());
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });

    }
}
