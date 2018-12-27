package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.api

import com.overall.developer.overrendicion.data.model.request.EditRendicionReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.InsertRendicionReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.RendicionRequest

interface IApiFormulario {

    fun searchRuchApi(ruc: String)

    fun sendDataForInsertApi(request: InsertRendicionReembolsoRequest, idRendicion: Int)

    fun sendDataForUpdateApi(request: EditRendicionReembolsoRequest, idRendicion: Int)
}