package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;

import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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
        RealmResults<RendicionBean>  rendicionList = mRealm.where(RendicionBean.class).equalTo("codLiquidacion", liquidacionBean.getCodLiquidacion()).sort("idRendicion", Sort.DESCENDING).findAll();
        return rendicionList;
    }

    @Override
    public String deleteRendicionForCodDB(int position)
    {
        Realm mRealm = Realm.getDefaultInstance();

        RendicionBean rendicionBean = mRealm.where(RendicionBean.class).equalTo("idRendicion",position).findFirst();
        String codRendicion = rendicionBean.getCodRendicion();
        RealmResults<RendicionDetalleBean> detalleBean = mRealm.where(RendicionDetalleBean.class).equalTo("codRendicion", codRendicion).findAll();
        mRealm.executeTransaction(realm ->
        {
            rendicionBean.deleteFromRealm();
            detalleBean.deleteAllFromRealm();

        });

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
                bean.setIgv(bean.getIgv().replace(",","."));
                bean.setValorNeto(bean.getValorNeto().replace(",","."));
                bean.setPrecioTotal(bean.getPrecioTotal().replace(",","."));
                bean.setOtroGasto(bean.getOtroGasto().replace(",","."));
                bean.setSend(true);
                nextID++;
            }
            mRealm.insertOrUpdate(mRendionList);

        });
    }

    @Override
    public LiquidacionBean getForCodLiquidacion(String codLiquidacion)
    {
        Realm mRealm = Realm.getDefaultInstance();

        RealmResults<LiquidacionBean> listBean = mRealm.where(LiquidacionBean.class).equalTo("status",true).findAll();
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

    @Override
    public ProvinciaBean getProvinciaDB(String ubigeoProvDestino)
    {
        Realm mRealm = Realm.getDefaultInstance();
        ProvinciaBean bean = mRealm.where(ProvinciaBean.class).equalTo("code",ubigeoProvDestino).findFirst();
        if (bean != null) return bean;
        else return null;
    }

    @Override
    public void insertListMovilidadDB(String codLiquidacionDB, List<RendicionDetalleBean> movilidadList)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            RealmResults<RendicionDetalleBean> initId = mRealm.where(RendicionDetalleBean.class).findAll();
            //int nextID = initId.size() == 0 ? 1 : initId.last().getIdMovilidad()+1;
            int nextID = initId.size() == 0 ? 1 : initId.max("id").intValue()+ 1;
            for (RendicionDetalleBean bean : movilidadList)
            {
                RendicionDetalleBean detalleBean = realm.where(RendicionDetalleBean.class).equalTo("idMovilidad", bean.getIdMovilidad()).findFirst();
                if (detalleBean != null)detalleBean.deleteFromRealm();
                bean.setId(nextID);
                bean.setCodLiquidacion(codLiquidacionDB);
                bean.setPrecioTotal(bean.getPrecioTotal().replace(",","."));
                bean.setMontoMovilidad(bean.getMontoMovilidad().replace(",","."));
                bean.setSend(true);
                nextID++;
            }
            mRealm.insertOrUpdate(movilidadList);

        });
    }

    @Override
    public List<RendicionDetalleBean> getListMovilidadDB(String codRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<RendicionDetalleBean> bean = mRealm.where(RendicionDetalleBean.class).equalTo("codRendicion", codRendicion).findAll();
        return bean;
    }

    @Override
    public String deleteDetMovForCodDB(int rdoId, int idDetMov)
    {
        String idMovilidad, codRendicion;
        final Double[] totalMontoMovilidad = {0.0};
        Realm mRealm = Realm.getDefaultInstance();
        RendicionDetalleBean movilidadBean = mRealm.where(RendicionDetalleBean.class).equalTo("id", idDetMov).findFirst();
        idMovilidad = movilidadBean.getIdMovilidad();
        codRendicion = movilidadBean.getCodRendicion();
        mRealm.executeTransaction(realm ->
        {
            movilidadBean.deleteFromRealm();
            //RealmResults<RendicionBean> beanList = mRealm.where(RendicionBean.class).equalTo("codRendicion", codRendicion).findAll();
            RealmResults<RendicionDetalleBean> detalleBeansList = mRealm.where(RendicionDetalleBean.class).equalTo("codRendicion", codRendicion).findAll();
            if (detalleBeansList.size() == 0)
            {
                RendicionBean beanList = mRealm.where(RendicionBean.class).equalTo("codRendicion", codRendicion).findFirst();
                beanList.deleteFromRealm();
            }
            for (RendicionDetalleBean bean : detalleBeansList)
            {
                totalMontoMovilidad[0] = totalMontoMovilidad[0] + Double.valueOf(bean.getMontoMovilidad());
            }


            mRepository.deleteMovSuccess(rdoId,totalMontoMovilidad[0]);
            //mRepository.deleteDetMovSuccess(beanList, detalleBeansList);
        });
        return idMovilidad;

    }

    @Override
    public String getUrlImageDB(String codRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionBean rendicionBean = mRealm.where(RendicionBean.class).equalTo("codRendicion",codRendicion).findFirst();
        return rendicionBean.getFoto();
    }

}
