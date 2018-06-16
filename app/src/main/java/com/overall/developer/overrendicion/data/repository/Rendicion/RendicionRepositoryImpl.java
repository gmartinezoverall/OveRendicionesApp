package com.overall.developer.overrendicion.data.repository.Rendicion;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.repository.Rendicion.api.ApiRendicion;
import com.overall.developer.overrendicion.data.repository.Rendicion.api.ApiRendicionImpl;
import com.overall.developer.overrendicion.data.repository.Rendicion.db.DBRendicion;
import com.overall.developer.overrendicion.data.repository.Rendicion.db.DBRendicionImpl;
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

}
