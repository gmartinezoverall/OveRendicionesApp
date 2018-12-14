package com.overall.developer.overrendicion.data.model.entity

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean

data class ReembolsoEntity
(
        var codReembolso: String,
        var codComp: String,
        var codTrab: String,
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
            reembosloBean.codReembolso,
            reembosloBean.codComp,
            reembosloBean.codTrab,
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

fun convertReembolsoEntityInBean(reembolsoEntity: ReembolsoEntity): ReembolsoBean
{
    return ReembolsoBean(
            reembolsoEntity.codReembolso,
            reembolsoEntity.codComp,
            reembolsoEntity.codTrab,
            reembolsoEntity.numBenDni,
            reembolsoEntity.nombreBen,
            reembolsoEntity.monto,
            reembolsoEntity.tipoMoneda,
            reembolsoEntity.nombreConsultora,
            reembolsoEntity.codTReembolso,
            reembolsoEntity.descTReembolso,
            reembolsoEntity.motivoReembolso,
            reembolsoEntity.fechaReembolso,
            reembolsoEntity.fechaPago,
            reembolsoEntity.fechaDesde,
            reembolsoEntity.fechaHasta,
            reembolsoEntity.estado
    )
}