package com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoBeanInEntity
import com.overall.developer.overrendicion.data.repository.Reembolso.api.ApiReembolso
import com.overall.developer.overrendicion.data.repository.Reembolso.db.DbReembolso
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.IReembolsoPresenter

class ReembolsoInteractor (internal val mPresenter: IReembolsoPresenter): IReembolsoInteractor
{
    internal val mDbReembolso = DbReembolso(this)
    internal val mApiReembolso = ApiReembolso(this)

    override fun getReembolsoList()
    {
        mApiReembolso.listReembolsoApi("72372900")
    }

    override fun listReembolsoApiSuccess(reembolsoBeans: List<ReembolsoBean>)
    {
        val reembolsoList = mDbReembolso.insertReembolsoListDB(reembolsoBeans)//por siacaso tenga que filtrarlos
        var reembolsoEntityList = arrayListOf<ReembolsoEntity>()

        for (reembolso: ReembolsoBean in reembolsoList)
        {

            var reembolsoEntity = convertReembolsoBeanInEntity(reembolso)
            //reembolsoEntityList.put(reembolso.idReembolso, reembolsoEntity)
            reembolsoEntityList.add(reembolsoEntity)

        }

        mPresenter.listReembolsoSuccess(reembolsoEntityList)
    }

    override fun listReembolsoApiError() {

    }
}