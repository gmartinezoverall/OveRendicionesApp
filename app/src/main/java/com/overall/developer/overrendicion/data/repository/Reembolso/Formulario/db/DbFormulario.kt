package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db

import com.overall.developer.overrendicion.data.model.bean.*
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity
import com.overall.developer.overrendicion.ui.reembolso.formularios.interactor.IFormularioInteractor
import io.realm.Realm
import io.realm.RealmResults

class DbFormulario(internal val mInteractor: IFormularioInteractor): IDbFormulaio
{

    override fun getReembolsoDB(): ReembolsoBean {
        val realm = Realm.getDefaultInstance()
        return realm.where(ReembolsoBean::class.java).equalTo("estadoR", true).findFirst()!!
    }

    override fun getDocumentForIdDB(idFragment: String): RealmResults<TipoDocumentoBean> {
        val realm = Realm.getDefaultInstance()
        return realm.where(TipoDocumentoBean::class.java).equalTo("rdoId", idFragment).findAll()
    }

    override fun getIdUsuarioDB(): UserBean{
        val realm = Realm.getDefaultInstance()
        return realm.where(UserBean::class.java).equalTo("status", true).findFirst()!!
    }

    override fun getOtrosBean(): OtrosBean{
        val realm = Realm.getDefaultInstance()
        return realm.where(OtrosBean::class.java).findFirst()!!
    }

    override fun saveDataDB(bean: RendicionBean): Int {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction{realm ->
            if (bean.idRendicion == null)
            {
                val id = realm.where(RendicionBean::class.java).max("idRendicion")
                val nextID = if (id == null) 1 else id.toInt() + 1
                val codRendicion = String.format("%4s", nextID).replace(' ', '0')
                bean.idRendicion = nextID
                bean.codRendicion = "OFF$codRendicion"
            }
            realm.insertOrUpdate(bean)
        }
        return bean.idRendicion
    }

    override fun getProvinciaDestinoList(): List<ProvinciaBean> {

        val realm = Realm.getDefaultInstance()

        return  realm.where(ProvinciaBean::class.java).findAll()
    }

    override fun deleteRendicionSend(idRendicion: Int) {
        val mRealm = Realm.getDefaultInstance()
        val bean = mRealm.where(RendicionBean::class.java).equalTo("idRendicion", idRendicion).findFirst()
        mRealm.executeTransaction { bean!!.deleteFromRealm()}
    }

    override fun getRendicionForEdit(codRendicion: String?): RendicionReembolsoBean? {
        val mRealm = Realm.getDefaultInstance()
        return mRealm.where(RendicionReembolsoBean::class.java).equalTo("codRendicionR", codRendicion).findFirst()
    }

    override fun getDefaultTipoGastoDB(rtgId: String?): TipoDocumentoBean {
        val mRealm = Realm.getDefaultInstance()
        return mRealm.where(TipoDocumentoBean::class.java).equalTo("rtgId", rtgId).findFirst()!!
    }

    override fun getDefaultProvinciaDB(codDestino: String?): ProvinciaBean {
        val mRealm = Realm.getDefaultInstance()
        return mRealm.where(ProvinciaBean::class.java).equalTo("code", codDestino).findFirst()!!
    }

    override fun getRendicion(idRendicion: Int?): RendicionReembolsoBean {
        val mRealm = Realm.getDefaultInstance()
        return mRealm.where(RendicionReembolsoBean::class.java).equalTo("idRendicion", idRendicion).findFirst()!!
    }
}