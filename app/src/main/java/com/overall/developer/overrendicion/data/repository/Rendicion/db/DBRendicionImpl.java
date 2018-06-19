package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;

import java.nio.MappedByteBuffer;
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
        String codLiquidacion  = liquidacionBean.getCodLiquidacion();
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

    @Override
    public String getCodLiquidacionDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("status", true).findFirst();
        String codLiquidacion  = liquidacionBean.getCodLiquidacion();
        return codLiquidacion;
    }

    @Override
    public void insertListRendicionInDB(List<RendicionBean> mRendionList)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            RealmResults<RendicionBean> initId = mRealm.where(RendicionBean.class).findAll();
            int nextID = initId.size() == 0 ? 1 : initId.last().getIdRendicion()+1;
            for (RendicionBean bean : mRendionList)
            {

                RendicionBean rendicionBean = realm.where(RendicionBean.class).equalTo("codRendicion", bean.getCodRendicion()).findFirst();
                if (rendicionBean != null) rendicionBean.deleteFromRealm();

                bean.setIdRendicion(nextID);
                nextID++;
            }
            mRealm.insertOrUpdate(mRendionList);

        });
    }

    @Override
    public LiquidacionBean getForCodLiquidacion(String codLiquidacion)
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
    public UserBean getUserDB() {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        return userBeans;
    }

    @Override
    public void finishLogin()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<UserBean> userBeanList = mRealm.where(UserBean.class).equalTo("status", true).findAll();
        if (userBeanList.size() > 0)
        {
            mRealm.executeTransaction(realm ->  {for (UserBean userBean : userBeanList) userBean.setStatus(false);});
        }

    }

}
