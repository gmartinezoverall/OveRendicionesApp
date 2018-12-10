package com.overall.developer.overrendicion.data.repository.Reembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor.IReembolsoInteractor
import io.realm.Realm

class DbReembolso (internal val mInteractor: IReembolsoInteractor) : IDbReembolso
{
    override fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>) : List<ReembolsoBean>
    {
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction{realm ->

/*            for (bean in reembolsoBeans)
            {
                bean.monto = bean.monto?.replace(",",".")
            }*/
            reembolsoBeans.map {it.monto.replace(",",".") }
            realm.insertOrUpdate(reembolsoBeans)
        }
        return reembolsoBeans
    }


}