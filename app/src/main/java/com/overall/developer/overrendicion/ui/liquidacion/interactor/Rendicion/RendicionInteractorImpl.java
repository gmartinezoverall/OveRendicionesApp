package com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion;


import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepositoryImpl;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion.utils.Util;

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

            for (RendicionBean bean : mRepository.listRendicion())
            {
                rendicionList.add(new RendicionEntity(bean.getIdRendicion(),bean.getCodRendicion(), bean.getRdoDes(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                        bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getValorNeto(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(),
                        bean.getFechaVencimiento(), bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(),
                        bean.getCodDestino(), bean.getAfectoRetencion(), bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto(), bean.isSend()));
            }


        mPresenter.getListRendicion(rendicionList);
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
        }
        mPresenter.updateListRendicion(rendicionList);
    }

    @Override
    public LiquidacionEntity getForCodLiquidacion(String codLiquidacion)
    {
        LiquidacionBean bean =  mRepository.getForCodLiquidacionDB(codLiquidacion);
        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        if (!bean.getUbigeoProvDestino().isEmpty()) {
            ProvinciaBean provBean = mRepository.getProvinciaDB(bean.getUbigeoProvDestino());
            provinciaEntity = new ProvinciaEntity(provBean.getCode(), provBean.getDesc());
        }
        LiquidacionEntity entity = new LiquidacionEntity(bean.getCodLiquidacion(), bean.getTipoLiquidacion(), bean.getDescripcionLiquidacion(), bean.getMonto(),
                bean.getNombre(), bean.getIdPeriodo(), bean.getFechaPago(), bean.getCodComp(), bean.getaCuenta(), bean.getSaldo(), bean.getDni(), bean.getFechaViatico(),
                bean.getMotivoViaje(), provinciaEntity, bean.getFechaDesde(), bean.getFechaHasta(), bean.getTipoViatico(), bean.getEstado(), bean.isStatus());
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


}
