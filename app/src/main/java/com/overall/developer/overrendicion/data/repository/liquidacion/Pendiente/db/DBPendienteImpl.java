package com.overall.developer.overrendicion.data.repository.liquidacion.Pendiente.db;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.crash.FirebaseCrash;
import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.OtrosBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.liquidacion.Pendiente.PendienteRepository;
import com.overall.developer.overrendicion.utils.Util;

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
        RealmResults<LiquidacionBean> pendienteBeans;
        if (entidad.equals("monto"))
        {
            pendienteBeans = mRealm.where(LiquidacionBean.class).equalTo(entidad, Double.valueOf(texto)).findAll();
        }
        else
        {
            pendienteBeans = mRealm.where(LiquidacionBean.class).contains(entidad, texto.toUpperCase()).findAll();
        }

        return pendienteBeans;
    }

    @Override
    public void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm -> mRealm.insertOrUpdate(provinciaBeanList));

    }

    @Override
    public boolean validateRendicionisEmpyDB(String codLiquidacion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<LiquidacionBean> liquidacionList = mRealm.where(LiquidacionBean.class).equalTo("codLiquidacion", codLiquidacion).findAll();

        return liquidacionList.average("aCuenta") > 0;
    }

    @Override
    public void insertRmvDB(String sueldo)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            OtrosBean bean = new OtrosBean();
            bean.setSueldo(sueldo);
            mRealm.insertOrUpdate(bean);
        });

    }

    @Override
    public boolean checkingPhone(String numDocBeneficiario)
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeanList = mRealm.where(UserBean.class).equalTo("numDocBeneficiario", numDocBeneficiario).findFirst();
        if (userBeanList.getTelefono()!= null)return true;
        return false;
    }

    @Override
    public void saveTelefonoDB(String numTelefono)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            UserBean userBean = getUser();
            userBean.setTelefono(numTelefono);
            Crashlytics.setString("Name", String.valueOf(userBean.getNombre()));
            Crashlytics.setString("Number", String.valueOf(userBean.getTelefono()));
            //FirebaseCrash.log("ASD.Cell -> "+ String.valueOf(userBean.getTelefono()));
            mRealm.insertOrUpdate(userBean);
        });

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
            for (LiquidacionBean bean : pendienteBean)
            {
                bean.setCodLiquidacion(bean.getCodLiquidacion().substring(bean.getCodLiquidacion().length()-6, bean.getCodLiquidacion().length()));
                bean.setFechaPago(Util.getChangeOrderDate(bean.getFechaPago().substring(0,10)));
                bean.setFechaViatico(Util.getChangeOrderDate(bean.getFechaViatico().substring(0,10)));
                bean.setFechaDesde(Util.getChangeOrderDate(bean.getFechaDesde().substring(0,10)));
                bean.setFechaHasta(Util.getChangeOrderDate(bean.getFechaHasta().substring(0,10)));
                bean.setFechaInicioLiq(Util.getChangeOrderDate(bean.getFechaInicioLiq().substring(0,10)));
                bean.setFechaFinLiq(Util.getChangeOrderDate(bean.getFechaFinLiq().substring(0,10)));
                bean.setFechaDesdeR(Util.getChangeOrderDate(bean.getFechaDesdeR().substring(0,10)));
                bean.setFechaHastaR(Util.getChangeOrderDate(bean.getFechaHastaR().substring(0,10)));
            }
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
