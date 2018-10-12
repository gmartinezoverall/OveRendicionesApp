package com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadMultipleEntity;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios.FormularioInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios.FormularioInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioView;

import java.util.List;

public class FormularioPresenterImpl implements FormularioPresenter
{
    FormularioView mView;
    FormularioInteractor mInteractor;

    public FormularioPresenterImpl(FormularioView view, Context context)
    {
        this.mView = view;

        mInteractor = new FormularioInteractorImpl(this, context);
    }

    @Override
    public List<TipoGastoEntity> getDocumentForId(String idDocumento) {
        return mInteractor.getDocumentForId(idDocumento);
    }

    @Override
    public List<ProvinciaEntity> getProvinciaDestinoList() {
        return mInteractor.getProvinciaDestinoList();
    }

    @Override
    public void saveData(List<String> typeFragment, Object objectDinamyc)
    {
        mInteractor.saveData(typeFragment, objectDinamyc);
    }

    @Override
    public void saveDataSuccess()
    {
        mView.saveDataSuccess();
    }

    @Override
    public List<String> getDefaultValues() {
        return mInteractor.getDefaultValues();
    }

    @Override
    public RendicionEntity setRendicionForEdit(String idRendicion) {
        return mInteractor.setRendicionForEdit(idRendicion);
    }

    @Override
    public UserBean getUser() {
        return mInteractor.getUser();
    }

    @Override
    public void finisLogin()
    {
        mInteractor.finisLogin();
    }

    @Override
    public String getCodLiquidacion() {
        return mInteractor.getCodLiquidacion();
    }

    @Override
    public List<BancoEntity> getAllBancos() {
        return mInteractor.getAllBancos();
    }

    @Override
    public TipoGastoEntity getDefaultTipoGasto(String rtgId)
    {
        return mInteractor.getDefaultTipoGasto(rtgId);
    }

    @Override
    public ProvinciaEntity getDefaultProvincia(String codDestino) {
        return mInteractor.getDefaultProvicia(codDestino);
    }

    @Override
    public BancoEntity getDefaultBanco(String bcoCod) {
        return mInteractor.getDefaultBanco(bcoCod);
    }

    @Override
    public RendicionDetalleEntity setMovilidadForEdit(int idMovilidad) {
        return mInteractor.setMovilidadForEdit(idMovilidad);
    }

    @Override
    public void saveDataMovilidad(MovilidadEntity movilidadEntity)
    {
        mInteractor.saveDataMovilidad(movilidadEntity);

    }

    @Override
    public void searchRuc(String ruc)
    {
        mInteractor.searchRuc(ruc);
    }

    @Override
    public void searchRucSuccess(String razonSocial)
    {
        mView.searchRucSuccess(razonSocial);

    }

    @Override
    public void searchRucError()
    {
        mView.searchRucError();
    }

    @Override
    public void saveDataMovilidadMultiple(MovilidadMultipleEntity movilidadEntity) {
        mInteractor.saveDataMovilidadMultiple(movilidadEntity);
    }

    @Override
    public void setTipoCambio()
    {
        mInteractor.setTipoCambio();
    }

    @Override
    public LiquidacionEntity getLiquidacion() {
        return mInteractor.getLiquidacion();
    }

}
