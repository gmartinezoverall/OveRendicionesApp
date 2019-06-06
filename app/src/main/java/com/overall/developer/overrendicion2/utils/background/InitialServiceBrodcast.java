package com.overall.developer.overrendicion2.utils.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.androidnetworking.common.Priority;
import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.RendicionApplication;
import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion2.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion2.utils.UrlApi;
import com.overall.developer.overrendicion2.utils.Util;
import com.overall.developer.overrendicion2.utils.aws.AwsUtility;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class InitialServiceBrodcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (Util.isOnline())
        {
            Toast.makeText(RendicionApplication.getContext(), "Iniciando Sincronizacion", Toast.LENGTH_SHORT).show();
            sendDataOffLine();
        }//else Toast.makeText(RendicionApplication.getContext(), "No hay iternet", Toast.LENGTH_LONG).show();

    }

    public static void sendDataOffLine()
    {

        List<RendicionBean> rendicionList = rendicionBeanist();
        if (rendicionList.size() > 0)
        {
            Toast.makeText(RendicionApplication.getContext(), "Iniciando Sincronizacion", Toast.LENGTH_SHORT).show();
            for (RendicionBean rendicionBean : rendicionList)
            {
                if (!rendicionBean.getRdoId().equals("10") && !rendicionBean.getRdoId().equals("19"))sendRendicionApi(getRequest(rendicionBean), rendicionBean.getIdRendicion());//se sincronizan todas las Rendiciones excepto las Movilidades
                else
                    {   //se comienza a sincronizar las movilidades
                        List<RendicionDetalleBean> detalleList = detalleBeanList(rendicionBean);
                        if (detalleList.size() > 0)
                        {
                            for (RendicionDetalleBean detalleBean : detalleList)
                            {
                                if (rendicionBean.getRdoId().equals("10"))sendDetalleRendicionApi(getRequestDetalle(rendicionBean, detalleBean), detalleBean.getId());
                                else sendDetalleRendicionMultApi(getRequestDetalleMult(rendicionBean, detalleBean), detalleBean.getId());
                            }
                        }

                    }
            }
        }

    }
    private static List<RendicionBean> rendicionBeanist()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<RendicionBean> rendicionList = mRealm.where(RendicionBean.class).equalTo("send", false).findAll();
        return rendicionList;

    }

    private static List<RendicionDetalleBean> detalleBeanList(RendicionBean bean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<RendicionDetalleBean> detalleList = mRealm.where(RendicionDetalleBean.class)
                .equalTo("codRendicion", bean.getCodRendicion())
                .and()
                .equalTo("send", false)
                .findAll();
        return detalleList;

    }

    private static RendicionRequest getRequest(RendicionBean bean)
    {
        RendicionRequest request = new RendicionRequest("", bean.getRdoId(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(), bean.getFechaVencimiento(),
                bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(), bean.getCodDestino(), bean.getAfectoRetencion(),
                bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto());
        return request;
    }

    private static MovilidadInsertRequest getRequestDetalle(RendicionBean rendicionBean, RendicionDetalleBean detalleBean)
    {
        MovilidadInsertRequest movilidadInsertRequest = new MovilidadInsertRequest(detalleBean.getRdoId(), detalleBean.getCodLiquidacion(), rendicionBean.getIdUsuario(),
                detalleBean.getMotivoMovilidad(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(), detalleBean.getFechaRendicion(), detalleBean.getRtgId(), detalleBean.getTipoMov(),
                detalleBean.getFechaDesde(), detalleBean.getFechaHasta());

        return movilidadInsertRequest;
    }

    private static MovilidadMultipleRequest getRequestDetalleMult(RendicionBean rendicionBean, RendicionDetalleBean detalleBean)
    {
        AwsUtility.UploadTransferUtilityS3(RendicionApplication.getContext() ,detalleBean.getFoto());
        detalleBean.setFoto(BuildConfig.URL_AWS + detalleBean.getFoto().substring(34));//se genera la URL de AWS para enviarlo por el WS

        MovilidadMultipleRequest movilidadMultipleRequest = new MovilidadMultipleRequest(detalleBean.getRdoId(), detalleBean.getCodLiquidacion(), rendicionBean.getIdUsuario(),
                detalleBean.getMotivoMovilidad(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(), detalleBean.getFechaRendicion(), detalleBean.getRtgId(), detalleBean.getTipoMov(),
                String.valueOf(Util.getCurrentDate()), detalleBean.getDni(), detalleBean.getDatosTrabajador(), detalleBean.getFoto());

        return movilidadMultipleRequest;
    }

    private static void sendRendicionApi(RendicionRequest request, Integer idRendicion)
    {
        Rx2AndroidNetworking.post(UrlApi.urlInsertarRendicion)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(request)
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
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                deleteRendicion(idRendicion);
                            }
                        }
                        catch (Exception e)
                        {
                            //Log.i("DatosLog",String.valueOf(e.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        //Log.i("NDa", e.toString());
                    }

                    @Override
                    public void onComplete()
                    {
                        //Log.i("NDa","Completado");
                    }
                });
    }

    private static void sendDetalleRendicionApi(MovilidadInsertRequest movilidadRequest, Integer detalleId)
    {
        Rx2AndroidNetworking.post(UrlApi.urlInsertarGastoMovilidad)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(movilidadRequest)
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
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                deleteDetalleRendicion(detalleId);
                            }
                        }
                        catch (Exception e)
                        {
                            //Log.i("DatosLog",String.valueOf(e.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                       // Log.i("NDa", e.toString());
                    }

                    @Override
                    public void onComplete()
                    {
                        //Log.i("NDa","Completado");
                    }
                });
    }


    private static void sendDetalleRendicionMultApi(MovilidadMultipleRequest movilidadMultipleRequest, Integer detalleId)
    {
        Rx2AndroidNetworking.post(UrlApi.urlInsertarGastoMovilidadMultiple)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(movilidadMultipleRequest)
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
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                deleteDetalleRendicion(detalleId);
                            }
                        }
                        catch (Exception e)
                        {
                            //Log.i("DatosLog",String.valueOf(e.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        // Log.i("NDa", e.toString());
                    }

                    @Override
                    public void onComplete()
                    {
                        //Log.i("NDa","Completado");
                    }
                });
    }


    private static void deleteRendicion(Integer idRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionBean bean = mRealm.where(RendicionBean.class).equalTo("idRendicion", idRendicion).findFirst();
        mRealm.executeTransaction(realm -> bean.deleteFromRealm(bean));

    }

    private static void deleteDetalleRendicion(Integer detalleId)
    {
        String codRendicion = null;

        Realm mRealm = Realm.getDefaultInstance();
        RendicionDetalleBean detalleBean = mRealm.where(RendicionDetalleBean.class).equalTo("id", detalleId).findFirst();
        codRendicion = detalleBean.getCodRendicion();
        mRealm.executeTransaction(realm -> detalleBean.deleteFromRealm(detalleBean));
        RealmResults<RendicionDetalleBean> detalleList = mRealm.where(RendicionDetalleBean.class).equalTo("codRendicion", codRendicion).findAll();

        if (detalleList.size() == 0)
        {
            RendicionBean bean = mRealm.where(RendicionBean.class).equalTo("codRendicion", codRendicion).findFirst();
            mRealm.executeTransaction(realm -> bean.deleteFromRealm(bean));

        }


    }


}
