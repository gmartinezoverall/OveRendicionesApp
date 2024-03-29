package com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.api;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion2.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.FormularioRepository;
import com.overall.developer.overrendicion2.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class ApiFormulariosImpl implements ApiFormularios
{
    FormularioRepository mRepository;

    public ApiFormulariosImpl(FormularioRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void sendDataForInsertApi(RendicionRequest request, Integer idRendicion)
    {
        sendRendicionForInsertApi(request, idRendicion);
    }

    @Override
    public void sendDataForUpdateApi(RendicionRequest request, Integer idRendicion)
    {
        sendRendicionForUpdateApi(request, idRendicion);

    }

    @Override
    public void sendDataInsertMovilidadApi(MovilidadInsertRequest movilidadRequest)
    {
        AndroidNetworking.post(UrlApi.urlInsertarGastoMovilidad)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(movilidadRequest)
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
    public void sendDataUpdateMovilidadApi(MovilidadUpdateRequest updateRequest)
    {
        AndroidNetworking.post(UrlApi.urlUpdateGastoMovilidad)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(updateRequest)
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
    public void searchRucApi(String ruc)
    {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.get(UrlApi.urlWSRuc)
                .addPathParameter("ruc", ruc)
                .setPriority(Priority.IMMEDIATE)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            mRepository.searchRucSuccess(jsonObject.getString("razon_social"));
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError)
                    {
                        // mRepository.searchRucError(); //08-04 por Gustavo M. termina porque el SP del otro servicio no existe
                        searchRucProveedoresAPi(ruc);
                    }
                });


    }

    private void searchRucProveedoresAPi(String ruc)
    {
        AndroidNetworking.post(UrlApi.urlSearchProveedores)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("ruc", ruc)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("message").equals("OK"))
                            {
                                String desc = response.getJSONArray("proveedor").getJSONObject(0).getString("desc");
                                mRepository.searchRucSuccess(desc);
                            }
                            else
                            {
                                mRepository.searchRucError();
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError)
                    {
                        Log.e("ApiFormulariosImpl", anError.getResponse().toString());
                    }
                });
    }



    @Override
    public void sendDataInsertMovilidadMultipleApi(MovilidadMultipleRequest movilidadMultipleRequest)
    {
        AndroidNetworking.post(UrlApi.urlInsertarGastoMovilidadMultiple)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(movilidadMultipleRequest)
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
    public void setTipoCambioApi(String fecha)
    {
        AndroidNetworking.post(UrlApi.urlTipoCambio)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("fecha", fecha)
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
                                String desc = response.getJSONArray("tipo_cambio").getJSONObject(0).getString("code");
                                mRepository.insertTipoCambioDB(desc);
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {
                        mRepository.errorTipoCambio();
                    }
                });
    }

    private void sendRendicionForUpdateApi(RendicionRequest request, Integer idRendicion)
    {
        Rx2AndroidNetworking.post(UrlApi.urlEditarRendicion)
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
                                mRepository.deleteRendicionSend(String.valueOf(jsonObject.getString("codRendicion")), idRendicion);

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

    private void sendRendicionForInsertApi(RendicionRequest request, Integer idRendicion)
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
                                mRepository.deleteRendicionSend(String.valueOf(jsonObject.getString("codRendicion")), idRendicion);

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
                        Log.i("NDa","Completado");
                    }
                });
    }

}
