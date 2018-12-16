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

    override fun changeStateAllReembolso() {
        mDbReembolso.changeStateAllReembolsoDB()
    }

    override fun getReembolsoList()
    {

        mApiReembolso.listReembolsoApi(mDbReembolso.getUserDb().numDocBeneficiario)//get DNI
    }

    override fun listReembolsoApiSuccess(reembolsoBeans: List<ReembolsoBean>)
    {
        mDbReembolso.insertReembolsoListDB(reembolsoBeans)//por siacaso tenga que filtrarlos
        val reembolsoEntityList = arrayListOf<ReembolsoEntity>()

        reembolsoBeans.map { reembolsoEntityList.add(convertReembolsoBeanInEntity(it)) }

        mPresenter.listReembolsoSuccess(reembolsoEntityList)
    }

    override fun listReembolsoApiError() {

    }

    override fun changeStatusReembolso(codReembolso: String) {
        mDbReembolso.changeStatusReembolsoDB(codReembolso)
    }
}