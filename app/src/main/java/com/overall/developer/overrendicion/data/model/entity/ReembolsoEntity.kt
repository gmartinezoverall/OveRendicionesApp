package com.overall.developer.overrendicion.data.model.entity

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean

data class ReembolsoEntity
(
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
        var estado: String
)
fun convertReembolsoBeanInEntity(reembosloBean: ReembolsoBean) : ReembolsoEntity
{
    return ReembolsoEntity(
            reembosloBean.codReemboslo,
            reembosloBean.codComp,
            reembosloBean.numBenDni,
            reembosloBean.nombreBen,
            reembosloBean.monto,
            reembosloBean.tipoMoneda,
            reembosloBean.nombreConsultora,
            reembosloBean.codTReembolso,
            reembosloBean.descTReembolso,
            reembosloBean.motivoReembolso,
            reembosloBean.fechaReembolso,
            reembosloBean.fechaPago,
            reembosloBean.fechaDesde,
            reembosloBean.fechaHasta,
            reembosloBean.estado)
}