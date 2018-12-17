package com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.api.ApiRendicion;
import com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.api.ApiRendicionImpl;
import com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.db.DBRendicion;
import com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.db.DBRendicionImpl;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion.RendicionInteractor;

import java.util.List;

public class RendicionRepositoryImpl implements RendicionRepository
{
    RendicionInteractor mInteractor;
    DBRendicion mDbRendicion;
    ApiRendicion mApiRendicion;

    public RendicionRepositoryImpl(RendicionInteractor interactor)
    {
        this.mInteractor = interactor;
        mDbRendicion = new DBRendicionImpl(this);
        mApiRendicion = new ApiRendicionImpl(this);

    }

    @Override
    public List<RendicionBean> listRendicion()
    {
        return mDbRendicion.listRendicion();
    }

    @Override
    public String deleteRendicionForCodDB(int position)
    {
        return mDbRendicion.deleteRendicionForCodDB(position);

    }

    @Override
    public void deleteRendicionForCodApi(String codCodRendicion)
    {
        mApiRendicion.deleteRendicionForCodApi(codCodRendicion);
    }

    @Override
    public void changeStatusLiquidacionDB() {
        mDbRendicion.changeStatusLiquidacion();
    }

    @Override
    public void insertListRendicionesApi(String codLiquidacion)
    {
        mApiRendicion.insertListRendicionesApi(codLiquidacion);
    }

    @Override
    public String getCodLiquidacionDB() {
        return mDbRendicion.getCodLiquidacionDB();
    }

    @Override
    public void insertListRendicionInDB(List<RendicionBean> mRendionList) {
        mDbRendicion.insertListRendicionInDB(mRendionList);
    }

    @Override
    public void insertListCompleted()
    {
        mInteractor.updateListRendicion(mDbRendicion.listRendicion());
    }

    @Override
    public LiquidacionBean getForCodLiquidacionDB(String codLiquidacion)
    {
        return mDbRendicion.getForCodLiquidacion(codLiquidacion);
    }

    @Override
    public UserBean getUserDB() {
        return mDbRendicion.getUserDB();
    }

    @Override
    public void finishLoginDB()
    {
        mDbRendicion.finishLogin();
    }

    @Override
    public ProvinciaBean getProvinciaDB(String ubigeoProvDestino)
    {
        return mDbRendicion.getProvinciaDB(ubigeoProvDestino);
    }

    @Override
    public void insertListMovilidadApi(String codLiquidacionDB)
    {
        mApiRendicion.insertListMovilidadApi(codLiquidacionDB);

    }

    @Override
    public void insertListMovilidadDB(String codLiquidacionDB, List<RendicionDetalleBean> movilidadList)
    {
        mDbRendicion.insertListMovilidadDB(codLiquidacionDB, movilidadList);
        mInteractor.successListMovilidad(movilidadList);
    }

    @Override
    public List<RendicionDetalleBean> getListMovilidadDB(String codRendicion)
    {
        return mDbRendicion.getListMovilidadDB(codRendicion);
    }

    @Override
    public String deleteDetMovForCodDB(int rdoId, int idDetMov) {
        return mDbRendicion.deleteDetMovForCodDB(rdoId, idDetMov);
    }

    @Override
    public void deleteDetMovForCodApi(String idDetMov) {
        mApiRendicion.deleteDetMovForCodApi(idDetMov);
    }

    @Override
    public void deleteDetMovSuccess(List<RendicionBean> beanList, List<RendicionDetalleBean> detalleBeansList)
    {
        mInteractor.deleteDetMovSuccess(beanList, detalleBeansList);

    }

    @Override
    public void sendDataPhoteApi(String codRendicion, String pathImage) {
        mApiRendicion.sendDataPhoteApi(codRendicion, pathImage);
    }

    @Override
    public void sendPhotoSuccess()
    {
        mInteractor.sendPhotoSuccess();
    }

    @Override
    public void sendPhotoError()
    {
        mInteractor.sendPhotoError();
    }

    @Override
    public String getUrlImageDB(String codRendicion) {
        return mDbRendicion.getUrlImageDB(codRendicion);
    }

    @Override
    public void deleteMovSuccess(int rdoId, Double totalMontoMovilidad)
    {
        mInteractor.deleteMovSuccess(rdoId, totalMontoMovilidad);
    }


}
