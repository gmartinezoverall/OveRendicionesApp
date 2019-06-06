package com.overall.developer.overrendicion2.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion2.ui.reembolso.reembolso.interactor.ReembolsoInteractor
import com.overall.developer.overrendicion2.ui.reembolso.reembolso.view.IReembolsoView

class ReembolsoPresenter (internal val mView:IReembolsoView):IReembolsoPresenter
{
    internal val mInteractor = ReembolsoInteractor(this)

    override fun changeStateAllReembolso() {
        mInteractor.changeStateAllReembolso()
    }

    override fun getReembolsoList()
    {
        mInteractor.getReembolsoList()
    }

    override fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)
    {
        mView.listReembolsoSuccess(reembolsoEntityList)
    }

    override fun changeStatusReembolso(codReembolso: String) { mInteractor.changeStatusReembolso(codReembolso) }

    override fun sendResume(codReembolso: String) { mInteractor.sendResume(codReembolso) }

    override fun sendResumeSuccess() { mView.sendResumeSuccess()}
}