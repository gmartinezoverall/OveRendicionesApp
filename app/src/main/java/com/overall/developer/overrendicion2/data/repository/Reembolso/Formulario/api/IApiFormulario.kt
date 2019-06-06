package com.overall.developer.overrendicion2.data.repository.reembolso.Formulario.api

import com.overall.developer.overrendicion2.data.model.request.EditRendicionReembolsoRequest
import com.overall.developer.overrendicion2.data.model.request.InsertRendicionReembolsoRequest

interface IApiFormulario {

    fun searchRuchApi(ruc: String)

    fun sendDataForInsertApi(request: InsertRendicionReembolsoRequest, idRendicion: Int)

    fun sendDataForUpdateApi(request: EditRendicionReembolsoRequest, idRendicion: Int)
}