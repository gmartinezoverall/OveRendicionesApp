package com.overall.developer.overrendicion.data.model.request

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

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
            reembolsoEntity.codReemboslo,
            reembolsoEntity.codTReembolso,
            reembolsoEntity.descTReembolso,
            reembolsoEntity.fechaDesde,
            reembolsoEntity.tipoMoneda,
            reembolsoEntity.motivoReembolso

    )
}