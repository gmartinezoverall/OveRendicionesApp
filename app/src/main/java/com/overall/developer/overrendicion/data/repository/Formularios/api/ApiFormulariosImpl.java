package com.overall.developer.overrendicion.data.repository.Formularios.api;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepository;
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

public class ApiFormulariosImpl implements ApiFormularios
{
    FormularioRepository mRepository;

    public ApiFormulariosImpl(FormularioRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void sendDataApi(RendicionRequest request, Integer idRendicion)
    {
        sendRendicionApi(request, idRendicion);
    }

    private void sendRendicionApi(RendicionRequest request, Integer idRendicion)
    {
        Rx2AndroidNetworking.post(UrlApi.urlInsertarRendicion)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(request)
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
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                mRepository.insertRendicionSuccess(String.valueOf(jsonObject.getString("codRendicion")), idRendicion);

                            }else
                            {
                                //mRepository.insertRendicionError(String.valueOf(jsonObject.getString("message")));
                            }

                        }
                        catch (Exception e)
                        {
                            Log.i("DatosLog",String.valueOf(e.getMessage()));
                        }
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
