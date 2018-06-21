package com.overall.developer.overrendicion.data.repository.Formularios.db;


import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBFormulariosImpl implements DBFormularios
{
    FormularioRepository mRepository;

    public DBFormulariosImpl(FormularioRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<TipoDocumentoBean> documentosList = mRealm.where(TipoDocumentoBean.class).equalTo("rdoId",idDocumento).findAll();
        return documentosList;
    }

    @Override
    public List<ProvinciaBean> getProvinciaDestinoList() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<ProvinciaBean> provinciaList = mRealm.where(ProvinciaBean.class).findAll();
        return provinciaList;
    }

    @Override
    public Integer saveDataDB(RendicionBean rendicionBean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            if (rendicionBean.getIdRendicion() == null)
            {
                Number id = realm.where(RendicionBean.class).max("idRendicion");
                int nextID = (id == null) ? 1 : id.intValue() + 1;
                rendicionBean.setIdRendicion(nextID);
            }

            mRealm.insertOrUpdate(rendicionBean);
        });
        return rendicionBean.getIdRendicion();
    }

    @Override
    public LiquidacionBean getCodLiquidacionDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacion = mRealm.where(LiquidacionBean.class).equalTo("status",true).findFirst();
        return liquidacion;
    }

    @Override
    public String getIdUsuarioDB() {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBean = mRealm.where(UserBean.class).equalTo("status",true).findFirst();
        return userBean.getIdUsuario();
    }

    @Override
    public RendicionBean setRendicionForEditDB(Integer idRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionBean rendicionBean = mRealm.where(RendicionBean.class).equalTo("idRendicion",idRendicion).findFirst();
        return rendicionBean;
    }

    @Override
    public void deleteForCodRendicion(String codRendicion, Integer idRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionBean bean = mRealm.where(RendicionBean.class).equalTo("idRendicion", idRendicion).findFirst();
        mRealm.executeTransaction(realm -> bean.deleteFromRealm(bean));

    }

    @Override
    public UserBean getUser() {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        return userBeans;
    }

    @Override
    public void finisLoginDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<UserBean> userBeanList = mRealm.where(UserBean.class).equalTo("status", true).findAll();
        if (userBeanList.size() > 0)
        {
            mRealm.executeTransaction(realm ->  {for (UserBean userBean : userBeanList) userBean.setStatus(false);});
        }

    }

    @Override
    public String getCodRendicion(Integer idRendicion)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionBean bean = mRealm.where(RendicionBean.class).equalTo("idRendicion", idRendicion).findFirst();
        return bean.getCodRendicion();
    }

    @Override
    public List<BancoBean> getAllBancos()
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<BancoBean> bancoList = mRealm.where(BancoBean.class).findAll();
        return bancoList;
    }

}
