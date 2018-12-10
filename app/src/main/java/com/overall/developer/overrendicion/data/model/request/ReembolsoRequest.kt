package com.overall.developer.overrendicion.data.model.request

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

data class ReembolsoRequest
(
    var codComp: String,
    var numBenDni: String,
    var nombreBen: String,
    var codTRembolso: String,
    var descTReembolso: String,
    var tipoMoneda: String,
    var motivoReembolso: String,
    var monto: String,
    var fechaDesde: String,
    var fechaHasta: String,
    var fechaReembolso: String
)

fun convertReembolsoEntityToRequest(reembolsoEntity: ReembolsoEntity) : ReembolsoRequest
{
    return ReembolsoRequest(
            reembolsoEntity.codComp,
            reembolsoEntity.numBenDni,
            reembolsoEntity.nombreBen,
            reembolsoEntity.codTReembolso,
            reembolsoEntity.descTReembolso,
            reembolsoEntity.tipoMoneda,
            reembolsoEntity.motivoReembolso,
            reembolsoEntity.monto,
            reembolsoEntity.fechaDesde,
            reembolsoEntity.fechaHasta,
            reembolsoEntity.fechaReembolso
    )
}
