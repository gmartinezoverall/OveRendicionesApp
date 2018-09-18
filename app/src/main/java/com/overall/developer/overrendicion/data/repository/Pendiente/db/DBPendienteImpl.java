package com.overall.developer.overrendicion.data.repository.Pendiente.db;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Pendiente.PendienteRepository;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class DBPendienteImpl implements DBPendiente
{
    private PendienteRepository mRepository;


    public DBPendienteImpl(PendienteRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public int setAllDocumentDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<TipoDocumentoBean> countDocument = mRealm.where(TipoDocumentoBean.class).findAll();

        return countDocument.size();
    }

    @Override
    public int setAllBancoDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<BancoBean> countBanco = mRealm.where(BancoBean.class).findAll();

        return countBanco.size();
    }

    @Override
    public void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList)
    {
        Realm mRealm = Realm.getDefaultInstance();

        int nextID = 1;
        for (TipoDocumentoBean bean : documentoBeanList)
        {
            bean.setId(nextID);

            nextID++;
        }
        mRealm.executeTransaction(realm -> mRealm.insert(documentoBeanList));
    }

    @Override
    public UserBean getUser()
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        return userBeans;
    }

    @Override
    public void finisLogin()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<UserBean> userBeanList = mRealm.where(UserBean.class).equalTo("status", true).findAll();
        if (userBeanList.size() > 0)
        {
            mRealm.executeTransaction(realm ->  {for (UserBean userBean : userBeanList) userBean.setStatus(false);});
        }

    }

    //region Interfaces
    @Override
    public Observable registerPendienteDB(List<LiquidacionBean> pendienteBean)
    {
        return insertOrUpdatePendienteDB(pendienteBean);

    }

    @Override
    public String getCodLiquidacionDB() {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacionBean = mRealm.where(LiquidacionBean.class).equalTo("status", true).findFirst();
        String codLiquidacion  = liquidacionBean.getCodLiquidacion();
        return codLiquidacion;
    }

    @Override
    public int pendienteListCountDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<LiquidacionBean> pendienteBeans = mRealm.where(LiquidacionBean.class).findAll();
        return pendienteBeans.size();
    }

    @Override
    public String searchDniDB(String dniUser)
    {
        return searchDniRealm(dniUser);
    }

    @Override
    public List<LiquidacionBean> listPendienteDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<LiquidacionBean> pendienteBeans = mRealm.where(LiquidacionBean.class).findAll();
        return pendienteBeans;
    }

    @Override
    public List<LiquidacionBean> listPendienteDB(String entidad, String texto)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<LiquidacionBean> pendienteBeans = mRealm.where(LiquidacionBean.class).contains(entidad, texto.toUpperCase()).findAll();
        return pendienteBeans;
    }

    @Override
    public void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm -> mRealm.insertOrUpdate(provinciaBeanList));

    }

    @Override
    public void registerBancoDB(List<BancoBean> bancoBeans)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm -> mRealm.insertOrUpdate(bancoBeans));

    }

    //endregion

    //region Funciones
    private Observable insertOrUpdatePendienteDB(List<LiquidacionBean> pendienteBean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            for (LiquidacionBean bean : pendienteBean) bean.setCodLiquidacion(bean.getCodLiquidacion().substring(bean.getCodLiquidacion().length()-6, bean.getCodLiquidacion().length()));
            mRealm.insertOrUpdate(pendienteBean);

        });
        return null;

    }

    private String searchDniRealm(String dniUser)
    {
        Realm mRealm = Realm.getDefaultInstance();
        final UserBean userBeans = mRealm.where(UserBean.class).findFirst();
        return userBeans.getNumDocBeneficiario();
    }


    //endregion
}
