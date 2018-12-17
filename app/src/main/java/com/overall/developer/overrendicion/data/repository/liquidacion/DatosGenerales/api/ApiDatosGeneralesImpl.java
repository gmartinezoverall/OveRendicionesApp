package com.overall.developer.overrendicion.data.repository.liquidacion.DatosGenerales.api;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.model.request.UpdateLiquidationRequest;
import com.overall.developer.overrendicion.data.repository.liquidacion.DatosGenerales.DatosGeneralesRepository;
import com.overall.developer.overrendicion.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiDatosGeneralesImpl implements ApiDatosGenerales
{
    private DatosGeneralesRepository mRepository;
    public ApiDatosGeneralesImpl(DatosGeneralesRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void sendUpdateLiquidacionAPI(UpdateLiquidationRequest request)
    {
        Rx2AndroidNetworking.post(UrlApi.urlLiquidationUpdate)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(request)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                    }
                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                mRepository.updateSuccess(String.valueOf(jsonObject.getString("message")));

                            }else
                            {
                                mRepository.updateError(String.valueOf(jsonObject.getString("message")));
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

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });


    }
}
