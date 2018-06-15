package com.overall.developer.overrendicion.data.repository.Rendicion.api;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;
import com.overall.developer.overrendicion.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

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
