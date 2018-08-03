package com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.bean.MovilidadBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion.RendicionInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion.RendicionInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionView;

import java.util.List;

public class RendicionPresenterImpl implements RendicionPresenter
{
    private RendicionView mView;
    private RendicionInteractor mInteractor;

    public RendicionPresenterImpl(RendicionView view, Context context)
    {
        this.mView = view;
        mInteractor = new RendicionInteractorImpl(this);
    }

    @Override
    public void listRendicion()
    {
        mInteractor.listRendicion();
    }

    @Override
    public void deleteRendicionForCod(int position)
    {
         mInteractor.deleteRendicionForCod(position);

    }

    @Override
    public void changeStatusLiquidacion() {
        mInteractor.changeStatusLiquidacion();
    }

    @Override
    public void getListRendicion(List<RendicionEntity> rendicionList)
    {
        mView.getListRendicion(rendicionList);
    }

    @Override
    public void updateListRendicion(List<RendicionEntity> rendicionBeans)
    {
        mView.updateListRendicion(rendicionBeans);
    }

    @Override
    public LiquidacionEntity getForCodLiquidacion(String codLiquidacion)
    {
        return mInteractor.getForCodLiquidacion(codLiquidacion);
    }

    @Override
    public UserBean getUser() {
        return mInteractor.getUser();
    }

    @Override
    public void finisLogin()
    {
        mInteractor.finishLogin();
    }

    @Override
    public void getListMovilidad(List<RendicionDetalleEntity> listMovilidad)
    {
        mView.getListMovilidad(listMovilidad);
    }

    @Override
    public void deleteDetMovForCod(int idDetMov)
    {
        mInteractor.deleteDetMovForCod(idDetMov);
    }

    @Override
    public void deleteDetMovSuccess(List<RendicionEntity> entityList, List<RendicionDetalleEntity> detalleEntityList)
    {
        mView.deleteDetMovSuccess(entityList, detalleEntityList);
    }

}
