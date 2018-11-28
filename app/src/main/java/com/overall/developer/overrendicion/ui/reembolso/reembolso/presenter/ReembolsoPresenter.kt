package com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor.ReembolsoInteractor
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.IReembolsoView

class ReembolsoPresenter (internal var mView:IReembolsoView):IReembolsoPresenter
{
    internal var mInteractor = ReembolsoInteractor(this)


    override fun getReembolsoList(): List<ReembolsoEntity>
    {
        return mInteractor.getReembolsoList()
    }


    override fun onLogin(email: String, password: String)
    {
        mInteractor.onLogin(email, password)
    }
}