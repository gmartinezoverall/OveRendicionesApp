package com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios;

import android.content.Context;


import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.BoletaVentaEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.FacturaEntity;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepository;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepositoryImpl;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios.FormularioPresenter;
import com.overall.developer.overrendicion.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class FormularioInteractorImpl implements FormularioInteractor
{
    FormularioPresenter mPresenter;
    FormularioRepository mRepository;
    Context mContext;

    public FormularioInteractorImpl(FormularioPresenter presenter, Context context)
    {
        this.mPresenter = presenter;
        mRepository = new FormularioRepositoryImpl(this);

    }

    @Override
    public List<TipoGastoEntity> getDocumentForId(String idDocumento)
    {

        List<TipoGastoEntity> mList = new ArrayList<>();

        for (TipoDocumentoBean bean : mRepository.getDocumentForIdDB(idDocumento))
        {
            mList.add(new TipoGastoEntity(bean.getRtgId(),bean.getRtgDes()));
        }

        return  mList;
    }

    @Override
    public List<String> getProvinciaDestinoList()
    {
        List<String> mList = new ArrayList<>();

        for (ProvinciaBean bean : mRepository.getProvinciaDestinoList()) mList.add(bean.getDesc());

        return mList;
    }

    @Override
    public void saveData(List<String> typeFragment, Object dinamyObj)
    {
        String codLiqui = mRepository.getCodLiquidacionDB().getCodLiquidacion();

        RendicionEntity entity =  filterFragment(Integer.valueOf(typeFragment.get(0)), dinamyObj);//jugada para traer el Texto y el id del tipo de fragment

        if (typeFragment.size() > 2)entity.setIdRendicion(Integer.valueOf(typeFragment.get(2)));
        entity.setCodRendicion("-");
        entity.setCodLiquidacion(codLiqui);
        entity.setIdUsuario(mRepository.getIdUsuarioDB());

        RendicionBean bean = new RendicionBean(entity.getIdRendicion(), entity.getCodRendicion(), entity.getRdoId(), typeFragment.get(1), entity.getCodLiquidacion(), entity.getIdUsuario(),
                entity.getNumeroDoc(), entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getPrecioTotal(), entity.getObservacion(),
                entity.getFechaDocumento(), entity.getFechaVencimiento(), entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(),
                entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(), entity.getCodSuspencionH(), entity.getTipoMoneda(),
                entity.getTipoCambio(), false);

        Integer idRendicion = mRepository.saveDataDB(bean);




        if (Util.isOnline())
        {
            entity.setCodRendicion("");
            RendicionRequest request = new RendicionRequest(entity.getCodRendicion(), entity.getRdoId(), entity.getCodLiquidacion(), entity.getIdUsuario(), entity.getNumeroDoc(),
                    entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getPrecioTotal(), entity.getObservacion(), entity.getFechaDocumento(), entity.getFechaVencimiento(),
                    entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(), entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(),
                    entity.getCodSuspencionH(), entity.getTipoMoneda(), entity.getTipoCambio());
            mRepository.sendDataApi(request, idRendicion);
        }
        mPresenter.saveDataSuccess();
    }

    @Override
    public List<String> getDefaultValues()
    {
        LiquidacionBean bean = mRepository.getCodLiquidacionDB();
        List<String> mList = new ArrayList<>();
        mList.add(String.valueOf(bean.getaCuenta()));
        mList.add(String.valueOf(bean.getMonto()));
        mList.add(String.valueOf(bean.getSaldo()));
        return mList;
    }

    @Override
    public RendicionEntity setRendicionForEdit(String idRendicion)
    {
        RendicionBean bean = mRepository.setRendicionForEditDB(Integer.parseInt(idRendicion));

        RendicionEntity entity = new RendicionEntity(bean.getIdRendicion(), bean.getCodRendicion(), bean.getRdoId(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(), bean.getFechaVencimiento(),
                bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(), bean.getCodDestino(), bean.getAfectoRetencion(),
                bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.isSend());

        return entity;
    }

    @Override
    public UserBean getUser() {
        return mRepository.getUserDB();
    }

    @Override
    public void finisLogin() {
        mRepository.finisLoginDB();
    }

    @Override
    public String getCodLiquidacion() {
        return  mRepository.getCodLiquidacionDB().getCodLiquidacion();
    }

    private RendicionEntity filterFragment(int typeFragment, Object dinamyObj)
    {
        RendicionEntity entity = new RendicionEntity();
        switch (typeFragment)
        {
            case 1:
                entity = new FacturaEntity().getEntity(dinamyObj);
                break;

            case 2:
                entity = new BoletaVentaEntity().getEntity(dinamyObj);
                break;


        }
        return entity;

    }
}
