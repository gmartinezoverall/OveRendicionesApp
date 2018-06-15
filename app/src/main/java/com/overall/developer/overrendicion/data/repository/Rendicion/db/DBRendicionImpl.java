package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBRendicionImpl implements DBRendicion
{
    RendicionRepository mRepository;

    public DBRendicionImpl(RendicionRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public List<RendicionBean> listRendicion()
    {
        Realm mRealm = Realm.getDefaultInstance();

        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("status",true).findFirst();
        String codLiquidacion  = liquidacionBean.getCodLiquidacion().substring(liquidacionBean.getCodLiquidacion().length() - 6 , liquidacionBean.getCodLiquidacion().length());
        List<RendicionBean>  rendicionList = mRealm.where(RendicionBean.class).equalTo("codLiquidacion", codLiquidacion).findAll();
        return rendicionList;
    }

    @Override
    public String deleteRendicionForCodDB(int position)
    {
        Realm mRealm = Realm.getDefaultInstance();
        String codRendicion;
        RendicionBean rendicionBean = mRealm.where(RendicionBean.class).equalTo("idRendicion",position).findFirst();
        codRendicion = rendicionBean.getCodRendicion();
        mRealm.executeTransaction(realm -> rendicionBean.deleteFromRealm());

        return codRendicion;
    }

    @Override
    public void changeStatusLiquidacion()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("status", true).findFirst();
        if (liquidacionBean != null)
        {
            mRealm.executeTransaction(realm ->
            {
                liquidacionBean.setStatus(false);
                mRealm.insertOrUpdate(liquidacionBean);
            });
        }
    }

}
