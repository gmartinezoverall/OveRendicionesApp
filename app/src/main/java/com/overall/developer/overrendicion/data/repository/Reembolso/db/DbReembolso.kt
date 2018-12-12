package com.overall.developer.overrendicion.data.repository.Reembolso.db

import android.util.Log
import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor.IReembolsoInteractor
import com.overall.developer.overrendicion.utils.Util
import io.realm.Realm

class DbReembolso (internal val mInteractor: IReembolsoInteractor) : IDbReembolso
{
    override fun getUserDb(): UserBean {
        val realm = Realm.getDefaultInstance()
        return realm.where(UserBean::class.java).equalTo("status",true).findFirst()!!
    }

    override fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>) : List<ReembolsoBean>
    {
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction{realm ->

            val count = realm.where(ReembolsoBean::class.java).findAll()
            var nextId:Int  = (if (count.size == 0) 1 else count.last()!!.idReembolso + 1  )

            reembolsoBeans.map {
                val reembolsoBean = realm.where(ReembolsoBean::class.java).equalTo("codReemboslo", it.codReemboslo).or().equalTo("codReemboslo", "-").findFirst()
                reembolsoBean.let {  reembolsoBean?.deleteFromRealm()}

                it.idReembolso = nextId
                it.monto = it.monto.replace(",",".")
                it.fechaDesde = Util.getChangeOrderDate(it.fechaDesde.substring(0,10))
                it.fechaHasta = Util.getChangeOrderDate(it.fechaHasta.substring(0,10))
                it.fechaReembolso = Util.getChangeOrderDate(it.fechaReembolso.substring(0,10))
                it.fechaPago = Util.getChangeOrderDate(it.fechaPago.substring(0,10))
                when(it.estado){
                    "P" -> it.estado = "Por Enviar"
                    "E" -> it.estado = "En Espera"
                    "A" -> it.estado = "Aprobado"
                    "R" -> it.estado = "Rechazado"
                }
                nextId += 1
            }
            realm.insert(reembolsoBeans)
        }

        return reembolsoBeans
    }


}