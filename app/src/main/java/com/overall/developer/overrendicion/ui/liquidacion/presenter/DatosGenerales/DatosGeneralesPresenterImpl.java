package com.overall.developer.overrendicion.ui.liquidacion.presenter.DatosGenerales;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.DatosGenerales.DatosGeneralesInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.DatosGenerales.DatosGeneralesInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.datosGenerales.DatosGeneralesView;

import java.util.ArrayList;
import java.util.List;

public class DatosGeneralesPresenterImpl implements DatosGeneralesPresenter
{
    private DatosGeneralesView mView;
    private DatosGeneralesInteractor mInteractor;

    public DatosGeneralesPresenterImpl(DatosGeneralesView view, Context context)
    {
        this.mView = view;
        mInteractor = new DatosGeneralesInteractorImpl(this, context);
    }

    @Override
    public LiquidacionEntity getForCodLiquidacion(String codLiquidacion)
    {
        return mInteractor.getForCodLiquidacion(codLiquidacion);

    }

    @Override
    public List<ProvinciaEntity> listProvinciaForSpinner() {
        return mInteractor.listProvinciaForSpinner();
    }

    @Override
    public UserBean getUser() {
        return mInteractor.getUser();
    }

    @Override
    public void saveData(String codLiquidacion, String fechaViatico, String motivoViaje, String ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico)
    {
        mInteractor.saveData(codLiquidacion, fechaViatico, motivoViaje, ubigeoProvDestino, fechaDesde, fechaHasta, tipoViatico);
    }

    @Override
    public boolean existsRendicion()
    {
        return mInteractor.existsRendicion();
    }

    @Override
    public void changeStatusLiquidacion() {
        mInteractor.changeStatusLiquidacion();
    }

    @Override
    public void finisLogin() {
        mInteractor.finisLogin();
    }
}
