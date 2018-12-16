package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.interactor

import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity

interface IDocumentosListInteractor {
    fun getDocumentosReembolso(): ArrayList<RendicionEntity>
    fun setDocumentosList()
    fun successGetRendicionesListtApi(reembolsoBeans: ArrayList<RendicionReembolsoBean>)
}