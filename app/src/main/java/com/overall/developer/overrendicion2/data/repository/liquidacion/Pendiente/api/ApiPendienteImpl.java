package com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.api;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.RendicionApplication;
import com.overall.developer.overrendicion2.data.model.bean.BancoBean;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.PendienteRepository;
import com.overall.developer.overrendicion2.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiPendienteImpl implements ApiPendiente
{
    private PendienteRepository mRepository;

    public ApiPendienteImpl(PendienteRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void setAllDocumentApi() {
        setAllDocuments();
    }

    @Override
    public void setAllBancoApi()
    {
        setAllBancos();
    }

    private void setAllBancos()
    {
        Rx2AndroidNetworking.post(UrlApi.urlListarBancos)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
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
                        try
                        {
                            if (jsonObject.getString("message").equals("OK"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad PendienteBean
                                Type collectionType = new TypeToken<Collection<BancoBean>>(){}.getType();
                                List<BancoBean> bancoBeans = gson.fromJson(jsonObject.getString("bancos"), collectionType);
                                mRepository.registerBancoDB(bancoBeans);

                            }
                            else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.getMessage();
                            Log.i("NDa",  e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public Observable listPendienteApi(String dniUser)
    {
        return listPendiente(dniUser);
    }

    @Override
    public void refreshListPendienteApi(String dniUser)
    {
        AndroidNetworking.post(UrlApi.urlListPendiente)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dniUser)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Gson gson = new Gson();
                        try
                        {
                            if (response.getString("message").equals("OK"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad PendienteBean
                                Type collectionType = new TypeToken<Collection<LiquidacionBean>>(){}.getType();
                                List<LiquidacionBean> mPendienteBean = gson.fromJson(response.getString("liquidations"), collectionType);
                                mRepository.registerPendienteDB(mPendienteBean);
                                mRepository.refreshListSuccess(dniUser);

                            }
                            else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.getMessage();
                            Log.i("NDa",  e.getMessage());
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {

                    }
                });
    }

    @Override
    public void insertProvinciaApi(String dniUser)
    {
        Rx2AndroidNetworking.post(UrlApi.urlListProvinces)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni",dniUser)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        Gson gson = new Gson();
                        Type collectionType = new TypeToken<Collection<ProvinciaBean>>(){}.getType();
                        List<ProvinciaBean> mProvinciaBeansList = null;
                        try
                        {
                            mProvinciaBeansList = gson.fromJson(jsonObject.getString("provinces"), collectionType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mRepository.insertProvinciaDB(mProvinciaBeansList);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void sendResumeEmailApi(String dni,String codRendicion)
    {
        AndroidNetworking.post(UrlApi.urlSendResumeEmail)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dni)
                .addBodyParameter("codLiquidacion", codRendicion)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        mRepository.successSendResume();
                    }

                    @Override
                    public void onError(ANError anError)
                    {
                        mRepository.errorSendResume();
                    }
                });

    }

    @Override
    public void setRmvApi()
    {
        AndroidNetworking.post(UrlApi.urlObtieneRMV)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("message").equals("OK"))
                            {
                                String sueldo = response.getJSONObject("rmv").getString("valor");
                                mRepository.insertRmvDB(sueldo);
                            }
                            else;

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError)
                    {

                    }
                });

    }

    private Observable listPendiente(final String dniUser)
    {
        Rx2AndroidNetworking.post(UrlApi.urlListPendiente)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dniUser)
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
                        try
                        {
                            if (jsonObject.getString("message").equals("OK"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad PendienteBean
                                Type collectionType = new TypeToken<Collection<LiquidacionBean>>(){}.getType();
                                List<LiquidacionBean> mPendienteBean = gson.fromJson(jsonObject.getString("liquidations"), collectionType);
                                mRepository.registerPendienteDB(mPendienteBean);

                            }
                            else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.getMessage();
                            Log.i("NDa",  e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mRepository.errorPendienteList(RendicionApplication.getContext().getResources().getString(R.string.servidorError));

                    }

                    @Override
                    public void onComplete()
                    {
                        mRepository.successPendienteList(RendicionApplication.getContext().getResources().getString(R.string.successPendienteList), dniUser);

                    }
                });

        return null;

    }


    private void setAllDocuments()
    {
        Rx2AndroidNetworking.post(UrlApi.urlTiposDocumentos)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
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
                        try
                        {
                            if (jsonObject.getString("message").equals("OK"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad PendienteBean
                                Type collectionType = new TypeToken<Collection<TipoDocumentoBean>>(){}.getType();
                                List<TipoDocumentoBean> tipoDocumentoBeans = gson.fromJson(jsonObject.getString("tipogasto"), collectionType);
                                mRepository.registerTypeDocDB(tipoDocumentoBeans);

                            }
                            else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.getMessage();
                            Log.i("NDa",  e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
