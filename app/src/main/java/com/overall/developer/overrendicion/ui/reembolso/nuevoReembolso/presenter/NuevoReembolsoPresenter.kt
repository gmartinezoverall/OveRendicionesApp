package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor.NuevoReembolsoInteractor
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.view.INuevoReembolsoView

class NuevoReembolsoPresenter (internal val mView: INuevoReembolsoView): INuevoReembolsoPresenter
{
    internal val mInteractor = NuevoReembolsoInteractor(this)

    override fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity){
        mInteractor.saveDateNewRefund(reembolsoEntity)
    }

    override fun insertNRSuccess() {
        mView.insertNRSuccess()
    }

    override fun getUser(): List<String> {
        return mInteractor.getUser()
    }

    override fun getDefaultValesReembolso(codReembolso: String): ReembolsoEntity {
        return mInteractor.getDefaultValesReembolso(codReembolso)
    }
}