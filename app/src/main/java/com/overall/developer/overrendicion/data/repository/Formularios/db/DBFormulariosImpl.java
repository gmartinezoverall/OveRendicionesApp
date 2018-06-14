package com.overall.developer.overrendicion.data.repository.Formularios.db;


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
    public void saveDataDB(RendicionBean rendicionBean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            RealmResults<RendicionBean> initId = mRealm.where(RendicionBean.class).findAll();
            int nextID = initId.size() == 0 ? 1 : initId.last().getIdRendicion()+1;
            rendicionBean.setIdRendicion(nextID);

            mRealm.insertOrUpdate(rendicionBean);
        });

    }

    @Override
    public String getCodLiquidacionDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacion = mRealm.where(LiquidacionBean.class).equalTo("status",true).findFirst();
        return liquidacion.getCodLiquidacion();
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
}
