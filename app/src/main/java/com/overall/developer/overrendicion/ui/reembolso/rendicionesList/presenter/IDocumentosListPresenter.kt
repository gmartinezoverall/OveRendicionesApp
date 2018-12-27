package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.presenter

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity

interface IDocumentosListPresenter {

    fun getDocumentosReembolso(): ArrayList<RendicionEntity>

    fun setDocumentosList()

    fun listRendicionSuccess(rendicionEntityList: ArrayList<RendicionEntity>)

    fun deleteRendicion(codRendicion: String)
}