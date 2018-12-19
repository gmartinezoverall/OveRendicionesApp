package com.overall.developer.overrendicion.data.model.request

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity

data class InsertRendicionRequest
(
        var codComp: String,
        var codTrab: String,
        var numBenDni: String,
        var nombreBen: String,
        var codTRembolso: String,
        var descTReembolso: String,
        var fechaDesde: String,
        var fechaHasta: String,
        var tipoMoneda: String,
        var motivoReembolso: String
)
/*
fun convertInsertRendicionEntityToRequest(rendicionEntity: RendicionEntity): InsertRendicionRequest
{
    return InsertRendicionRequest(
            rendicionEntity.
    )
}*/
