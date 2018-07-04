package com.overall.developer.overrendicion.data.repository.DatosGenerales.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.DatosGenerales.DatosGeneralesRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DBDatosGeneralesImpl implements DBDatosGenerales
{
    private DatosGeneralesRepository mRepository;


    public DBDatosGeneralesImpl(DatosGeneralesRepository repository)
    {
        this.mRepository = repository;

    }

    @Override
    public LiquidacionBean getForCodLiquidacionDB(String codLiquidacion)
    {
        Realm mRealm = Realm.getDefaultInstance();

        List<LiquidacionBean> listBean = mRealm.where(LiquidacionBean.class).equalTo("status",true).findAll();

        if (listBean != null)
        {
            mRealm.executeTransaction(realm ->
            {
                for(LiquidacionBean beam : listBean) beam.setStatus(false);
                mRealm.insertOrUpdate(listBean);
            });

        }


        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("codLiquidacion",codLiquidacion).findFirst();
        mRealm.executeTransaction(realm ->
        {
            liquidacionBean.setStatus(true);
            mRealm.insertOrUpdate(liquidacionBean);

        });
        return liquidacionBean;
    }

    @Override
    public List<ProvinciaBean> listProvinciaForSpinnerDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        List<ProvinciaBean> provinciaBeanList = mRealm.where(ProvinciaBean.class).findAll();
        return provinciaBeanList;

    }

    @Override
    public UserBean getUser()
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        return userBeans;
    }

    @Override
    public void updateLiquidacionDB(LiquidacionBean liquidacionBean)
    {
        Realm mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(realm -> mRealm.insertOrUpdate(liquidacionBean));

    }

    @Override
    public boolean existsRendicionDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("status",true).findFirst();
        String codLiquidacion  = liquidacionBean.getCodLiquidacion();
        List<RendicionBean> rendicionList = mRealm.where(RendicionBean.class).equalTo("codLiquidacion", codLiquidacion).findAll();
        return rendicionList.isEmpty();
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

    @Override
    public ProvinciaBean getProvinciaDB(String idProvincia)
    {
        Realm mRealm = Realm.getDefaultInstance();
        ProvinciaBean bean = mRealm.where(ProvinciaBean.class).equalTo("code",idProvincia).findFirst();
        if (bean != null) return bean;
        else return null;

    }

    @Override
    public void finisLogin()
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        mRealm.executeTransaction(realm -> userBeans.setStatus(false));

    }
}
