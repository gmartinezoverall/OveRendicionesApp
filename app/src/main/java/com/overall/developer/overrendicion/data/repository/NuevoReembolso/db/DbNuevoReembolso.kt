package com.overall.developer.overrendicion.data.repository.NuevoReembolso.db

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean
import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor.INuevoReembolsoInteractor
import io.realm.Realm

class DbNuevoReembolso(internal val mInteractor: INuevoReembolsoInteractor): IDbNuevoReembolso
{
    override fun insertNewRefundDB(reembolsoBean: ReembolsoBean) {
        val realm = Realm.getDefaultInstance()

        //reembolsoBean.codReemboslo = (reembolsoBean.codReemboslo.toInt() + 1).toString()
        realm.executeTransaction{realm ->
            run {
                realm.insert(reembolsoBean)
                var bean: LiquidacionBean? = realm.where(LiquidacionBean::class.java).findFirst()
                bean?.isStatus = true
                realm.insertOrUpdate(bean)
            }
        }


    }

}