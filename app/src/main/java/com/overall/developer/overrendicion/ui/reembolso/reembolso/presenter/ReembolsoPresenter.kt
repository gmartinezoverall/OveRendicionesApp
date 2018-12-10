package com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor.ReembolsoInteractor
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.IReembolsoView

class ReembolsoPresenter (internal val mView:IReembolsoView):IReembolsoPresenter
{
    internal val mInteractor = ReembolsoInteractor(this)

    override fun getReembolsoList()
    {
        mInteractor.getReembolsoList()
    }

    override fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)
    {
        mView.listReembolsoSuccess(reembolsoEntityList)
    }
}