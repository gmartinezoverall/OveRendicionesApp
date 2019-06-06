package com.overall.developer.overrendicion2.data.repository.reembolso.documentosReembolsoList.db

import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion2.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.interactor.IDocumentosListInteractor
import io.realm.Realm
import io.realm.RealmResults

class DbDocumentosList(internal val mInteractor: IDocumentosListInteractor): IDbDocumentosList
{

    override fun getReembolsoBean(): ReembolsoBean {
        val realm = Realm.getDefaultInstance()
        return realm.where(ReembolsoBean::class.java).equalTo("estadoR", true).findFirst()!!
    }

    override fun getDocumentosReembolsoDB(): RealmResults<RendicionReembolsoBean>
    {
        val realm = Realm.getDefaultInstance()
        return  realm.where(RendicionReembolsoBean::class.java).equalTo("codReembolso", getReembolsoBean().codReembolso.toString()).findAll()
    }

    override fun insertRendicionDB(rendicionBeans: ArrayList<RendicionReembolsoBean>)
    {
        val realm = Realm.getDefaultInstance()!!
        val count = realm.where(RendicionReembolsoBean::class.java).findAll()
        var nextId:Int  = (if (count.size == 0) 1 else count.last()!!.idRendicion + 1  )

        realm.executeTransaction{realm->
            rendicionBeans.map{
                val rendicionBean = realm.where(RendicionReembolsoBean::class.java).equalTo("codRendicionR", it.codRendicion).findFirst()
                rendicionBean.let{ rendicionBean?.deleteFromRealm()}

                it.idRendicion = nextId
                it.igv = it.igv.replace(",", ".")
                it.valorNeto = it.valorNeto.replace(",", ".")
                it.precioTotal = it.precioTotal.replace(",", ".")
                it.otroGasto = it.otroGasto.replace(",", ".")
                it.send = true
                nextId += 1
            }

            realm.insertOrUpdate(rendicionBeans)
        }
    }

    override fun deleteRendicionDb(codRendicion: String) {
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction{
            run{
                val bean = realm.where(RendicionReembolsoBean::class.java).equalTo("codRendicionR", codRendicion).findFirst()
                bean?.deleteFromRealm()}
        }
    }
}