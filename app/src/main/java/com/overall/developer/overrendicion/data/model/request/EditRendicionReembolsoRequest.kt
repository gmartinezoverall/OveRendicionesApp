package com.overall.developer.overrendicion.data.model.request

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity

data class EditRendicionReembolsoRequest(

        var codRendicionR: String?,
        var rdoId: String?,
        var codLiquidacion: String?,
        var idUsuario: String?,
        var numeroDoc: String?,
        var bienServicio: String?,
        var igv: String?,
        var afectoIgv: String?,
        var precioTotal: String?,
        var observacion: String?,
        var fechaDocumento: String?,
        var fechaVencimiento: String?,
        var ruc: String?,
        var razonSocial: String?,
        var bcoCod: String?,
        var tipoServicio: String?,
        var rtgId: String?,
        var otroGasto: String?,
        var codDestino: String?,
        var afectoRetencion: String?,
        var codSuspencionH: String?,
        var tipoMoneda: String?,
        var tipoCambio: String?,
        var foto: String?
)
fun convertEditRendicionEntityToRequest(rendicionEntity: RendicionEntity): EditRendicionReembolsoRequest
{
    return EditRendicionReembolsoRequest(
            rendicionEntity.codRendicion,
            rendicionEntity.rdoId,
            rendicionEntity.codLiquidacion,
            rendicionEntity.idUsuario,
            rendicionEntity.numeroDoc,
            rendicionEntity.bienServicio,
            rendicionEntity.igv,
            rendicionEntity.afectoIgv,
            rendicionEntity.precioTotal,
            rendicionEntity.observacion,
            rendicionEntity.fechaDocumento,
            rendicionEntity.fechaVencimiento,
            rendicionEntity.ruc,
            rendicionEntity.razonSocial,
            rendicionEntity.bcoCod,
            rendicionEntity.tipoServicio,
            rendicionEntity.rtgId,
            rendicionEntity.otroGasto,
            rendicionEntity.codDestino,
            rendicionEntity.afectoRetencion,
            rendicionEntity.codSuspencionH,
            rendicionEntity.tipoMoneda,
            rendicionEntity.tipoCambio,
            rendicionEntity.foto
    )
}