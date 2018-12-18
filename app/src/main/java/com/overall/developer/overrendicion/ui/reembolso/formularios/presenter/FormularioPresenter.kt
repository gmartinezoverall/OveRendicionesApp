package com.overall.developer.overrendicion.ui.reembolso.formularios.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity
import com.overall.developer.overrendicion.ui.reembolso.formularios.interactor.FormularioInteractor
import com.overall.developer.overrendicion.ui.reembolso.formularios.view.IFormularioView

class FormularioPresenter (internal val mView: IFormularioView): IFormularioPresenter
{
    internal val mInteractor= FormularioInteractor(this)

    override fun getReembolso(): ReembolsoEntity = mInteractor.getReembolso()

    override fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity> = mInteractor.getListSpinner(idFragment)

    override fun searchRuch(ruc: String) = mInteractor.searchRuch(ruc)

    override fun searchRucSuccess(razonSocial: String) {mView.searchRucSuccess(razonSocial)}

    override fun searchRucError() {mView.searchRucError()}
}