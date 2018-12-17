package com.overall.developer.overrendicion.data.repository.reembolso.NuevoReembolso.api

import com.overall.developer.overrendicion.data.model.request.InsertReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.UpdateReembolsoRequest

interface IApiNuevoReembolso {
    fun insertNewRefundApi(reembolsoRequest: InsertReembolsoRequest)
    fun updateRefundApi(updateRequest: UpdateReembolsoRequest)
}