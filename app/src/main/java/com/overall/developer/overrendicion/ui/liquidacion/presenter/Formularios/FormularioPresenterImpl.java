package com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios.FormularioInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios.FormularioInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioView;

import java.io.File;
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
    public List<String> getProvinciaDestinoList() {
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

}
