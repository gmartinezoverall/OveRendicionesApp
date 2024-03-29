package com.overall.developer.overrendicion2.ui.liquidacion.interactor.Rendicion;


import android.widget.Toast;

import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.RendicionApplication;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Rendicion.RendicionRepository;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Rendicion.RendicionRepositoryImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion2.utils.Util;
import com.overall.developer.overrendicion2.utils.aws.AwsUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RendicionInteractorImpl implements RendicionInteractor
{
    RendicionPresenter mPresenter;
    RendicionRepository mRepository;


    public RendicionInteractorImpl(RendicionPresenter presenter)
    {
        this.mPresenter = presenter;
        mRepository = new RendicionRepositoryImpl(this);
    }

    @Override
    public void listRendicion()
    {
        List<RendicionEntity> rendicionList= new ArrayList<>();

            if (Util.isOnline())mRepository.insertListRendicionesApi(mRepository.getCodLiquidacionDB());
            else
            {
                for (RendicionBean bean : mRepository.listRendicion())
                {
                    rendicionList.add(new RendicionEntity(bean.getIdRendicion(),bean.getCodRendicion(), bean.getRdoDes(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                            bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getValorNeto(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(),
                            bean.getFechaVencimiento(), bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(),
                            bean.getCodDestino(), bean.getAfectoRetencion(), bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto(), bean.isSend()));

                }
                mPresenter.getListRendicion(rendicionList);
            }


    }

    @Override
    public void deleteRendicionForCod(int position)
    {
        String codCodRendicion = mRepository.deleteRendicionForCodDB(position);
        if (!codCodRendicion.equals("-")) mRepository.deleteRendicionForCodApi(codCodRendicion);
    }

    @Override
    public void changeStatusLiquidacion()
    {
        mRepository.changeStatusLiquidacionDB();

    }

    @Override
    public void updateListRendicion(List<RendicionBean> rendicionBeans)
    {
        List<RendicionEntity> rendicionList= new ArrayList<>();
        for (RendicionBean bean : mRepository.listRendicion())
        {
            rendicionList.add(new RendicionEntity(bean.getIdRendicion(),bean.getCodRendicion(), bean.getRdoDes(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                    bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getValorNeto(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(),
                    bean.getFechaVencimiento(), bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(),
                    bean.getCodDestino(), bean.getAfectoRetencion(), bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto(), bean.isSend()));

            if (bean.getRdoId().toString().equals("10") || bean.getRdoId().toString().equals("19"))//Movilidad individual 10, Movilidad Masiva
            {
                List<RendicionDetalleEntity> movilidadList = new ArrayList<>();
                if (Util.isOnline())mRepository.insertListMovilidadApi(bean.getCodLiquidacion());

                if (mRepository.getListMovilidadDB(bean.getCodRendicion()).size() > 0)
                {
                    for (RendicionDetalleBean detalleBean : mRepository.getListMovilidadDB(bean.getCodRendicion()))
                    {
                        movilidadList.add(new RendicionDetalleEntity(detalleBean.getId(),detalleBean.getIdMovilidad(), detalleBean.getCodRendicion(), detalleBean.getRdoId(), detalleBean.getRtgId(),
                                detalleBean.getPrecioTotal(), detalleBean.getFechaRendicion(), detalleBean.getEstado(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(),
                                detalleBean.getMotivoMovilidad(), detalleBean.getBeneficiario(), detalleBean.getFechaDesde(), detalleBean.getFechaHasta(), detalleBean.getNumBeneficiario(),
                                detalleBean.getDni(), detalleBean.getDatosTrabajador(), detalleBean.getFoto()));

                    }
                    mPresenter.getListMovilidad(movilidadList);
                }
            }
        }
        mPresenter.updateListRendicion(rendicionList);
    }

    @Override
    public LiquidacionEntity getForCodLiquidacion(String codLiquidacion)
    {
        LiquidacionBean bean =  mRepository.getForCodLiquidacionDB(codLiquidacion);
        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        if (bean.getUbigeoProvDestino() != null && !bean.getUbigeoProvDestino().equals("")) {
            ProvinciaBean provBean = mRepository.getProvinciaDB(bean.getUbigeoProvDestino());
            provinciaEntity = new ProvinciaEntity(provBean.getCode(), provBean.getDesc());
        }

        LiquidacionEntity entity = new LiquidacionEntity(bean.getCodLiquidacion(), bean.getTipoLiquidacion(), bean.getDescripcionLiquidacion(), bean.getMonto(),
                bean.getNombre(), bean.getIdPeriodo(), bean.getFechaPago(), bean.getCodComp(), bean.getaCuenta(), bean.getSaldo(), bean.getDni(), bean.getFechaViatico(),
                bean.getMotivoViaje(), provinciaEntity, bean.getFechaDesde(), bean.getFechaHasta(), bean.getTipoViatico(), bean.getEstado(), bean.getCodEgreso(),
                bean.getFechaInicioLiq(), bean.getFechaFinLiq(), bean.getFechaDesdeR(), bean.getFechaHastaR(), bean.isStatus());

        return entity;
    }

    @Override
    public UserBean getUser()
    {
        return mRepository.getUserDB();
    }

    @Override
    public void finishLogin()
    {
        mRepository.finishLoginDB();
    }

    @Override
    public void successListMovilidad(List<RendicionDetalleBean> movilidadList)
    {
        List<RendicionDetalleEntity> entityList = new ArrayList<>();
        for (RendicionDetalleBean detalleBean : movilidadList)
        {
            entityList.add(new RendicionDetalleEntity(detalleBean.getId(),detalleBean.getIdMovilidad(), detalleBean.getCodRendicion(), detalleBean.getRdoId(), detalleBean.getRtgId(),
                    detalleBean.getPrecioTotal(), detalleBean.getFechaRendicion(), detalleBean.getEstado(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(),
                    detalleBean.getMotivoMovilidad(), detalleBean.getBeneficiario(), detalleBean.getFechaDesde(), detalleBean.getFechaHasta(), detalleBean.getNumBeneficiario(),
                    detalleBean.getDni(), detalleBean.getDatosTrabajador(), detalleBean.getFoto()));

        }
        mPresenter.getListMovilidad(entityList);
    }

    @Override
    public void deleteDetMovForCod(int rdoId, int idDetMov)
    {
        String idMovilidad = mRepository.deleteDetMovForCodDB(rdoId, idDetMov);
        if (Util.isOnline())mRepository.deleteDetMovForCodApi(idMovilidad);//borro por background
    }

    @Override
    public void deleteDetMovSuccess(List<RendicionBean> beanList, List<RendicionDetalleBean> detalleBeansList)
    {
        List<RendicionEntity> rendicionList= new ArrayList<>();
        for (RendicionBean bean : mRepository.listRendicion()) {
            rendicionList.add(new RendicionEntity(bean.getIdRendicion(), bean.getCodRendicion(), bean.getRdoDes(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                    bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getValorNeto(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(),
                    bean.getFechaVencimiento(), bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(),
                    bean.getCodDestino(), bean.getAfectoRetencion(), bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto(), bean.isSend()));
        }

        List<RendicionDetalleEntity> detalleEntityList = new ArrayList<>();
        for (RendicionDetalleBean detalleBean : detalleBeansList)
        {
            detalleEntityList.add(new RendicionDetalleEntity(detalleBean.getId(),detalleBean.getIdMovilidad(), detalleBean.getCodRendicion(), detalleBean.getRdoId(), detalleBean.getRtgId(),
                    detalleBean.getPrecioTotal(), detalleBean.getFechaRendicion(), detalleBean.getEstado(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(),
                    detalleBean.getMotivoMovilidad(), detalleBean.getBeneficiario(), detalleBean.getFechaDesde(), detalleBean.getFechaHasta(), detalleBean.getNumBeneficiario(),
                    detalleBean.getDni(), detalleBean.getDatosTrabajador(), detalleBean.getFoto()));

        }
        mPresenter.deleteDetMovSuccess(rendicionList, detalleEntityList);

    }

    @Override
    public void sendDataPhote(String codRendicion, String pathImage)
    {
        String image = Util.SaveImage(pathImage);
        AwsUtility.UploadTransferUtilityS3(RendicionApplication.getContext(), image);
        String imageSend = (BuildConfig.URL_AWS + image.substring(34));//se genera la URL de AWS para enviarlo por el WS

        File file = new File(pathImage);
        file.delete();
        //mRepository.sendDataPhoteApi(codRendicion, Util.SaveImage(pathImage));
        mRepository.sendDataPhoteApi(codRendicion, imageSend);

    }

    @Override
    public void sendPhotoSuccess()
    {
        Toast.makeText(RendicionApplication.getContext(), "Se Actualizo su foto correctamente", Toast.LENGTH_LONG).show();
        mPresenter.sendPhotoSuccess();
    }

    @Override
    public void sendPhotoError()
    {
        Toast.makeText(RendicionApplication.getContext(), "No se pudo Actualizar su foto intento mas tarde", Toast.LENGTH_LONG).show();


    }

    @Override
    public String getUrlImage(String codRendicion) {
        return mRepository.getUrlImageDB(codRendicion);
    }

    @Override
    public String getCodLiquidacion()
    {
        return mRepository.getCodLiquidacionDB();
    }

    @Override
    public List<RendicionDetalleEntity> getListRendicionDetalle(String codRendicion)
    {
        List<RendicionDetalleEntity> detalleEntityList = new ArrayList<>();
        for (RendicionDetalleBean detalleBean : mRepository.getListMovilidadDB(codRendicion))
        {
            detalleEntityList.add(new RendicionDetalleEntity(detalleBean.getId(),detalleBean.getIdMovilidad(), detalleBean.getCodRendicion(), detalleBean.getRdoId(), detalleBean.getRtgId(),
                    detalleBean.getPrecioTotal(), detalleBean.getFechaRendicion(), detalleBean.getEstado(), detalleBean.getDestinoMovilidad(), detalleBean.getMontoMovilidad(),
                    detalleBean.getMotivoMovilidad(), detalleBean.getBeneficiario(), detalleBean.getFechaDesde(), detalleBean.getFechaHasta(), detalleBean.getNumBeneficiario(),
                    detalleBean.getDni(), detalleBean.getDatosTrabajador(), detalleBean.getFoto()));

        }

        return detalleEntityList;
    }

    @Override
    public void deleteMovSuccess(int rdoId, Double totalMontoMovilidad)
    {
        mPresenter.deleteMovSuccess(rdoId, totalMontoMovilidad);
    }

}
