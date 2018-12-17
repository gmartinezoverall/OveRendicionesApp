package com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.api;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.RendicionRepository;
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

    @Override
    public void insertListMovilidadApi(String codLiquidacionDB)
    {
        AndroidNetworking.post(UrlApi.urlListarMovilidad)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codLiquidacion", codLiquidacionDB)
                 .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("code").equals("0"))
                            {
                                Gson gson = new Gson();
                                Type collectionType = new TypeToken<Collection<RendicionDetalleBean>>(){}.getType();
                                List<RendicionDetalleBean> detalleBeanList = gson.fromJson(response.getString("rendicion"), collectionType);

                                mRepository.insertListMovilidadDB(codLiquidacionDB, detalleBeanList);
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {

                    }
                });
    }

    @Override
    public void deleteDetMovForCodApi(String idDetMov)
    {
        AndroidNetworking.post(UrlApi.urlEliminarGastoMovilidad)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("idMovilidad", idDetMov)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("code").equals("0"))
                            {
                                //mRepository.deleteMovSuccess();

                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {

                    }
                });

    }

    @Override
    public void sendDataPhoteApi(String codRendicion, String pathImage)
    {
        AndroidNetworking.post(UrlApi.urlUpdateFotoRendicion)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codRendicion", codRendicion)
                .addBodyParameter("foto", pathImage)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Toast.makeText(RendicionApplication.getContext(), "Se Guardo la Foto Correctamente", Toast.LENGTH_LONG).show();
                            if (response.getString("code").equals("0"))
                            {
                                mRepository.sendPhotoSuccess();

                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {
                        mRepository.sendPhotoError();

                    }
                });


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
                            mRepository.insertListRendicionInDB(mRendionList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
