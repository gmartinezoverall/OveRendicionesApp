package com.overall.developer.overrendicion2.data.model.request

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity

data class UpdateReembolsoRequest
(
        var codReembolso: String,
        var codTRembolso: String,
        var descTReembolso: String,
        var fechaDesde: String,
        var tipoMoneda: String,
        var motivoReembolso: String
)

fun convertUpdateReembolsoEntityToRequest(reembolsoEntity: ReembolsoEntity) : UpdateReembolsoRequest
{
    return UpdateReembolsoRequest(
            reembolsoEntity.codReembolso,
            reembolsoEntity.codTReembolso,
            reembolsoEntity.descTReembolso,
            reembolsoEntity.fechaDesde,
            reembolsoEntity.tipoMoneda,
            reembolsoEntity.motivoReembolso

    )
}