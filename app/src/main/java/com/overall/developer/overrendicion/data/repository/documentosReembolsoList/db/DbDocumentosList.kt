package com.overall.developer.overrendicion.data.repository.documentosReembolsoList.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.interactor.IDocumentosListInteractor
import io.realm.Realm
import io.realm.RealmResults

class DbDocumentosList(internal val mInteractor: IDocumentosListInteractor): IDbDocumentosList
{
    val realm = Realm.getDefaultInstance()!!
    override fun getReembolsoBean(): ReembolsoBean = realm.where(ReembolsoBean::class.java).equalTo("estadoR", true).findFirst()!!

    override fun getDocumentosReembolsoDB(): RealmResults<RendicionReembolsoBean> =
            realm.where(RendicionReembolsoBean::class.java).equalTo("codReembolso", getReembolsoBean().codReembolso).findAll()

    override fun insertRendicionDB(rendicionBeans: ArrayList<RendicionReembolsoBean>) {

            val count = realm.where(ReembolsoBean::class.java).findAll()
            var nextId:Int  = (if (count.size == 0) 1 else count.last()!!.idReembolso + 1  )

        realm.executeTransaction{realm->
            rendicionBeans.map{
                val rendicionBean = realm.where(RendicionReembolsoBean::class.java).equalTo("codRendicion", it.codReembolso).findFirst()
                rendicionBean.let{ rendicionBean?.deleteFromRealm()}

                it.idRendicion = nextId

            }
            nextId += 1
            realm.insert(rendicionBeans)
        }


    }
}