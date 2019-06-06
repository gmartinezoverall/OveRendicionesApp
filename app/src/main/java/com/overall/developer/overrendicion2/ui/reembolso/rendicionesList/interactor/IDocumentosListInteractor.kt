package com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.interactor

import com.overall.developer.overrendicion2.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity

interface IDocumentosListInteractor {

    fun getDocumentosReembolso(): ArrayList<RendicionEntity>

    fun setDocumentosList()

    fun successGetRendicionesListtApi(reembolsoBeans: ArrayList<RendicionReembolsoBean>)

    fun deleteRendicion(codRendicion: String)
}