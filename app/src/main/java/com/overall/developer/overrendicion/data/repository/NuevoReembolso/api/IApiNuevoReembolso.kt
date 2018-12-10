package com.overall.developer.overrendicion.data.repository.NuevoReembolso.api

import com.overall.developer.overrendicion.data.model.request.ReembolsoRequest

interface IApiNuevoReembolso {
    fun insertNewRefundApi(reembolsoRequest: ReembolsoRequest)
}