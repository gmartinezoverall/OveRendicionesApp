package com.overall.developer.overrendicion.data.repository.Rendicion;

import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
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
    public void deleteRendicionForCodDB(int position)
    {
        mDbRendicion.deleteRendicionForCodDB(position);

    }




}
