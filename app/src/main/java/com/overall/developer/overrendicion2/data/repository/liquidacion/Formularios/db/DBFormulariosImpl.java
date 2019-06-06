package com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.db;

import com.overall.developer.overrendicion2.data.model.bean.BancoBean;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.OtrosBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.FormularioRepository;

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
                String codRendicion = String.format("%4s", nextID).replace(' ', '0');
                rendicionBean.setIdRendicion(nextID);
                rendicionBean.setCodRendicion("OFF-"+codRendicion);
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

    @Override
    public TipoDocumentoBean getDefaultTipoGastoDB(String rtgId)
    {
        Realm mRealm = Realm.getDefaultInstance();
        TipoDocumentoBean bean = mRealm.where(TipoDocumentoBean.class).equalTo("rtgId", rtgId).findFirst();
        return bean;
    }

    @Override
    public ProvinciaBean getDefaultProviciaDB(String codDestino)
    {
        Realm mRealm = Realm.getDefaultInstance();
        ProvinciaBean bean = mRealm.where(ProvinciaBean.class).equalTo("code",codDestino).findFirst();
        return bean;
    }

    @Override
    public BancoBean getDefaultBancoDB(String bcoCod)
    {
        Realm mRealm = Realm.getDefaultInstance();
        BancoBean bean = mRealm.where(BancoBean.class).equalTo("code", bcoCod).findFirst();
        return bean;

    }

    @Override
    public RendicionDetalleBean setMovilidadForEditDB(int idMov)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RendicionDetalleBean rendicionDetalleBean = mRealm.where(RendicionDetalleBean.class).equalTo("id", Integer.valueOf(idMov)).findFirst();
        return rendicionDetalleBean;
    }

    @Override
    public void insertMovilidadDB(RendicionBean rendicionBean, RendicionDetalleBean detalleBean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            if (rendicionBean.getIdRendicion() == 0)
            {
                Number idRendicion = realm.where(RendicionBean.class).max("idRendicion");
                int nextID = (idRendicion == null) ? 1 : idRendicion.intValue() + 1;
                String codRendicion = String.format("%4s", nextID).replace(' ', '0');
                rendicionBean.setIdRendicion(nextID);
                rendicionBean.setCodRendicion("OFF-"+codRendicion);
            }

            mRealm.insertOrUpdate(rendicionBean);

            Number idDet = realm.where(RendicionDetalleBean.class).max("id");
            int nextID2 = (idDet == null) ? 1 : idDet.intValue() + 1;
            detalleBean.setId(nextID2);
            detalleBean.setCodRendicion(rendicionBean.getCodRendicion());
            mRealm.insertOrUpdate(detalleBean);

        });
    }

    @Override
    public void insertTipoCambioDB(String desc)
    {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            OtrosBean bean = mRealm.where(OtrosBean.class).findFirst();
            bean.setTipoCambio(desc);
            mRealm.insertOrUpdate(bean);
        });

    }

    @Override
    public String getTipoCambioDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        OtrosBean bean= mRealm.where(OtrosBean.class).findFirst();
        return bean.getTipoCambio();

    }

    @Override
    public LiquidacionBean getLiquidacionDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        LiquidacionBean liquidacion = mRealm.where(LiquidacionBean.class).equalTo("status",true).findFirst();
        return liquidacion;
    }

    @Override
    public void updateLiquidacionDB(RendicionBean rendicionBean)
    {
        LiquidacionBean liquidacionBean = getLiquidacionDB();
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(realm ->
        {
            Double acuenta, saldo, precio, sumMovilidad = 0.0;
            precio = Double.valueOf(rendicionBean.getPrecioTotal());
            if (rendicionBean.getRdoId().equals("10"))
            {
                RealmResults<RendicionDetalleBean> detalleList = mRealm.where(RendicionDetalleBean.class).equalTo("codRendicion",rendicionBean.getCodRendicion()).findAll();

                for (RendicionDetalleBean detalleBean: detalleList)
                {
                    sumMovilidad = sumMovilidad + Double.valueOf(detalleBean.getMontoMovilidad());
                }
                rendicionBean.setPrecioTotal(String.valueOf(sumMovilidad));
                mRealm.insertOrUpdate(rendicionBean);


            }
            acuenta = Double.valueOf(liquidacionBean.getaCuenta()) + precio;
            saldo = liquidacionBean.getMonto() - acuenta;
            liquidacionBean.setaCuenta(acuenta);
            liquidacionBean.setSaldo(saldo);
            mRealm.insertOrUpdate(liquidacionBean);
        });

    }

    @Override
    public OtrosBean getOtrosBeanDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        OtrosBean otrosBean = mRealm.where(OtrosBean.class).findFirst();
        return otrosBean;
    }

    @Override
    public Double getSumaAcuentaDB(String fechaViaje)
    {
        Double montoMovilidad = 0.0;
        Realm mRealm = Realm.getDefaultInstance();

        RealmResults<RendicionDetalleBean> detalleBean= mRealm.where(RendicionDetalleBean.class).equalTo("codLiquidacion", getLiquidacionDB().getCodLiquidacion()).and().equalTo("fechaRendicion", fechaViaje).findAll();

        for(RendicionDetalleBean bean: detalleBean)
        {
            montoMovilidad = montoMovilidad + Double.valueOf(bean.getMontoMovilidad());
        }

        return montoMovilidad;
    }

    @Override
    public RendicionBean getDetailMovOffLineDB()
    {
        Realm mRealm = Realm.getDefaultInstance();

        RendicionBean rendicionBean = mRealm.where(RendicionBean.class).equalTo("codLiquidacion", getLiquidacionDB().getCodLiquidacion()).and().equalTo("rdoId", "10").and().equalTo("send", false).findFirst();

        return rendicionBean;

    }

}
