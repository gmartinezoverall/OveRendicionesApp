package com.overall.developer.overrendicion2.ui.liquidacion.presenter.Rendicion;

import android.content.Context;

import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.ui.liquidacion.interactor.Rendicion.RendicionInteractor;
import com.overall.developer.overrendicion2.ui.liquidacion.interactor.Rendicion.RendicionInteractorImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.view.rendicion.RendicionView;

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
    public void deleteDetMovForCod(int rdoId, int idDetMov)
    {
        mInteractor.deleteDetMovForCod(rdoId, idDetMov);
    }

    @Override
    public void deleteDetMovSuccess(List<RendicionEntity> entityList, List<RendicionDetalleEntity> detalleEntityList)
    {
        mView.deleteDetMovSuccess(entityList, detalleEntityList);
    }

    @Override
    public void sendDataPhote(String codRendicion, String pathImage)
    {
        mInteractor.sendDataPhote(codRendicion, pathImage);
    }

    @Override
    public void sendPhotoSuccess() {
        mView.sendPhotoSuccess();
    }

    @Override
    public String getUrlImage(String codRendicion) {
        return mInteractor.getUrlImage(codRendicion);
    }

    @Override
    public String getCodLiquidacion() {
        return mInteractor.getCodLiquidacion();
    }

    @Override
    public List<RendicionDetalleEntity> getListRendicionDetalle(String codRendicion) {
        return mInteractor.getListRendicionDetalle(codRendicion);
    }

    @Override
    public void deleteMovSuccess(int rdoId, Double totalMontoMovilidad) {
        mView.deleteMovSuccess(rdoId, totalMontoMovilidad);
    }

}
