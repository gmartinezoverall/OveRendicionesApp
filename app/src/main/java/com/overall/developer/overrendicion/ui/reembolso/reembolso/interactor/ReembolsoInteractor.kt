package com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.IReembolsoPresenter

class ReembolsoInteractor (internal val mPresenter: IReembolsoPresenter): IReembolsoInteractor
{
    override fun getReembolsoList(): List<ReembolsoEntity>
    {


    }

    override fun onLogin(email: String, password: String)
    {
        mPresenter.onLogin(email, password)

    }

}