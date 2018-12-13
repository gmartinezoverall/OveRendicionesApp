package com.overall.developer.overrendicion.data.model.request

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

data class InsertReembolsoRequest
(
    var codComp: String,
    var codTrab: String,
    var numBenDni: String,
    var nombreBen: String,
    var codTRembolso: String,
    var descTReembolso: String,
    var tipoMoneda: String,
    var motivoReembolso: String,
    var fechaDesde: String,
    var fechaHasta: String
)

fun convertInsertReembolsoEntityToRequest(reembolsoEntity: ReembolsoEntity) : InsertReembolsoRequest
{
    return InsertReembolsoRequest(
            reembolsoEntity.codComp,
            reembolsoEntity.codTrab,
            reembolsoEntity.numBenDni,
            reembolsoEntity.nombreBen,
            reembolsoEntity.codTReembolso,
            reembolsoEntity.descTReembolso,
            reembolsoEntity.tipoMoneda,
            reembolsoEntity.motivoReembolso,
            reembolsoEntity.fechaDesde,
            reembolsoEntity.fechaHasta
    )
}
