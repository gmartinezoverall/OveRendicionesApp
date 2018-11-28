package com.overall.developer.overrendicion.data.model.entity

data class ReembolsoEntity(
        var codReemboslo: String,
        var codComp: String,
        var numBenDni: String,
        var nombreBen: String,
        var monto: String,
        var tipoMoneda: String,
        var nombreConsultora: String,
        var codTReembolso: String,
        var descTReembolso: String,
        var motivoReembolso: String,
        var fechaReembolso: String,
        var fechaPago: String,
        var fechaDesde: String,
        var fechaHasta: String,
        var estado: String)
