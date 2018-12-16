package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.presenter

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.interactor.DocumentosListInteractor
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.view.IDocumentosListView

class DocumentosListPresenter(internal val mView: IDocumentosListView): IDocumentosListPresenter
{
    internal val mInteractor = DocumentosListInteractor(this)

    override fun listRendicionSuccess(rendicionEntityList: ArrayList<RendicionEntity>) {
        mView.listRendicionSuccess(rendicionEntityList)
    }

    override fun setDocumentosList() {
        mInteractor.setDocumentosList()
    }

    override fun getDocumentosReembolso(): ArrayList<RendicionEntity> = mInteractor.getDocumentosReembolso()

}