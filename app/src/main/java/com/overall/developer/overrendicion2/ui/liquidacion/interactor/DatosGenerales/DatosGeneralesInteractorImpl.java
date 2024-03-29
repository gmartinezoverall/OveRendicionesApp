package com.overall.developer.overrendicion2.ui.liquidacion.interactor.DatosGenerales;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion2.data.model.request.UpdateLiquidationRequest;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.DatosGeneralesRepository;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.DatosGeneralesRepositoryImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.presenter.DatosGenerales.DatosGeneralesPresenter;
import com.overall.developer.overrendicion2.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion2.utils.Util;

import java.util.ArrayList;
import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;


public class DatosGeneralesInteractorImpl implements DatosGeneralesInteractor
{
    private DatosGeneralesPresenter mPresenter;
    private DatosGeneralesRepository mRepository;
    private Context mContext;

    public DatosGeneralesInteractorImpl(DatosGeneralesPresenter presenter, Context context)
    {
        this.mPresenter = presenter;
        mRepository = new DatosGeneralesRepositoryImpl(this);
        mContext = context;

    }

    @Override
    public LiquidacionEntity getForCodLiquidacion(String codLiquidacion)
    {
        LiquidacionBean bean =  mRepository.getForCodLiquidacion(codLiquidacion);

        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        if (!bean.getUbigeoProvDestino().isEmpty()) {
            ProvinciaBean provBean = mRepository.getProvinciaDB(bean.getUbigeoProvDestino());
            provinciaEntity = new ProvinciaEntity(provBean.getCode(), provBean.getDesc());
        }
        LiquidacionEntity entity = new LiquidacionEntity(bean.getCodLiquidacion(), bean.getTipoLiquidacion(), bean.getDescripcionLiquidacion(), bean.getMonto(),
                bean.getNombre(), bean.getIdPeriodo(), bean.getFechaPago(), bean.getCodComp(), bean.getaCuenta(), bean.getSaldo(), bean.getDni(), bean.getFechaViatico(),
                bean.getMotivoViaje(),provinciaEntity , Util.getChangeOrderDate(bean.getFechaDesde()), Util.getChangeOrderDate(bean.getFechaHasta()), bean.getTipoViatico(), bean.getEstado(), bean.getCodEgreso(),
                bean.getFechaInicioLiq(), bean.getFechaFinLiq(), bean.getFechaDesdeR(), bean.getFechaHastaR(), bean.isStatus());
        return entity;
    }

    @Override
    public List<ProvinciaEntity> listProvinciaForSpinner()
    {
        List<ProvinciaEntity> entityList = new ArrayList<>();
        for (ProvinciaBean bean : mRepository.listProvinciaForSpinner()) entityList.add(new ProvinciaEntity(bean.getCode(),bean.getDesc()));


        return entityList;
    }

    @Override
    public UserBean getUser() {
        return mRepository.getUser();
    }

    @Override
    public void saveData(String codLiquidacion, String fechaViatico, String motivoViaje, String ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico)
    {
        LiquidacionBean mLiquidacionBean =  new LiquidacionBean();

        LiquidacionBean mBean = mRepository.getForCodLiquidacion(codLiquidacion);
        //Datos DB
        mLiquidacionBean.setTipoLiquidacion(mBean.getTipoLiquidacion());
        mLiquidacionBean.setDescripcionLiquidacion(mBean.getDescripcionLiquidacion());
        mLiquidacionBean.setMonto(mBean.getMonto());
        mLiquidacionBean.setNombre(mBean.getNombre());
        mLiquidacionBean.setIdPeriodo(mBean.getIdPeriodo());
        mLiquidacionBean.setFechaPago(mBean.getFechaPago());
        mLiquidacionBean.setCodComp(mBean.getCodComp());
        mLiquidacionBean.setaCuenta(mBean.getaCuenta());
        mLiquidacionBean.setSaldo(mBean.getSaldo());
        mLiquidacionBean.setDni(mBean.getDni());
        mLiquidacionBean.setEstado(mBean.getEstado());
        mLiquidacionBean.setCodEgreso(mBean.getCodEgreso());

        //Datos View
        mLiquidacionBean.setCodLiquidacion(codLiquidacion);
        mLiquidacionBean.setFechaViatico(fechaViatico);
        mLiquidacionBean.setMotivoViaje(motivoViaje);
        mLiquidacionBean.setUbigeoProvDestino(ubigeoProvDestino);
        mLiquidacionBean.setTipoViatico(tipoViatico.equals("Nacional") ? "N" : "E");
        mLiquidacionBean.setFechaDesde(fechaDesde);
        mLiquidacionBean.setFechaHasta(fechaHasta);
        mLiquidacionBean.setFechaInicioLiq(fechaDesde);
        mLiquidacionBean.setFechaFinLiq(fechaHasta);
        mLiquidacionBean.setFechaDesdeR(mBean.getFechaDesdeR());
        mLiquidacionBean.setFechaHastaR(mBean.getFechaHastaR());
        mLiquidacionBean.setStatus(true);


        mRepository.updateLiquidacionDB(mLiquidacionBean);


        //se pone los parametros por internet
        UpdateLiquidationRequest requestObj = new UpdateLiquidationRequest();

        requestObj.setCodLiquidacion(codLiquidacion.substring(codLiquidacion.length() - 6, codLiquidacion.length()));
        requestObj.setFechaViatico(fechaViatico);
        requestObj.setMotivoViaje(motivoViaje);
        requestObj.setUbigeoProvDestino(ubigeoProvDestino);
        requestObj.setFechaDesde(fechaDesde);
        requestObj.setFechaHasta(fechaHasta);
        requestObj.setTipoViatico(tipoViatico.equals("Nacional") ? "N" : "E");
        requestObj.setEstado(String.valueOf("1"));

        mRepository.sendUpdateLiquidacionAPI(requestObj);

    }

    @Override
    public boolean existsRendicion()
    {
        return mRepository.existsRendicion();
    }

    @Override
    public void changeStatusLiquidacion()
    {
        mRepository.changeStatusLiquidacion();
    }

    @Override
    public void updateSuccess(String message)
    {
        Toast.makeText(mContext, mContext.getResources().getString(R.string.updateSuccess), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateError(String message)
    {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finisLogin()
    {
        mRepository.finisLogin();
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
        customType(mContext, "fadein-to-fadeout");
    }





}
