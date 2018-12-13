package com.overall.developer.overrendicion.data.repository.NuevoReembolso.db

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean
import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor.INuevoReembolsoInteractor
import io.realm.Realm

class DbNuevoReembolso(internal val mInteractor: INuevoReembolsoInteractor): IDbNuevoReembolso
{

    override fun insertNewRefundDB(reembolsoBean: ReembolsoBean) {
        val realm = Realm.getDefaultInstance()

        //reembolsoBean.codReemboslo = (reembolsoBean.codReemboslo.toInt() + 1).toString()
        realm.executeTransaction{realm ->
            run {
                reembolsoBean.estadoR = true
                when(reembolsoBean.estado){
                    "P" -> reembolsoBean.estado = "Por Enviar"
                    "E" -> reembolsoBean.estado = "En Espera"
                    "A" -> reembolsoBean.estado = "Aprobado"
                    "R" -> reembolsoBean.estado = "Rechazado"
                }
                realm.insertOrUpdate(reembolsoBean)
/*                val userBean = realm.where(LiquidacionBean::class.java).findFirst()
                userBean?.isStatus = true
                realm.insertOrUpdate(userBean)*/
            }
        }
    }

    override fun getUser(): UserBean {
        val realm = Realm.getDefaultInstance()

        return realm.where(UserBean::class.java).equalTo("status",true).findFirst()!!
    }

    override fun getReembolso(): ReembolsoBean {
        val realm = Realm.getDefaultInstance()
        return realm.where(ReembolsoBean::class.java).equalTo("estadoR", true).findFirst()!!
    }

    override fun getDefaultValesReembolso(codReembolso: String): ReembolsoBean {
        val realm = Realm.getDefaultInstance()
        val reembolsoBean = realm.where(ReembolsoBean::class.java).equalTo("codReemboslo", codReembolso).findFirst()
        realm.executeTransaction{
            reembolsoBean?.estadoR = true
            realm.insertOrUpdate(reembolsoBean)
        }
        return reembolsoBean!!

    }
}