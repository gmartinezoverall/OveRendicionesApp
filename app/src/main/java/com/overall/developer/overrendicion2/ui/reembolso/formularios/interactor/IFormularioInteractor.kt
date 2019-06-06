package com.overall.developer.overrendicion2.ui.reembolso.formularios.interactor

import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity
import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity

interface IFormularioInteractor {

    fun getReembolso(): ReembolsoEntity

    fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity>

    fun searchRuch(ruc: String)

    fun searchRucSuccess(razon_social: String)

    fun searchRucError()

    fun saveDate(typeFragment: ArrayList<String>, objectDinamyc: Any)

    fun deleteRendicionSend(idRendicion: Int)

    fun getProvinciaDestinoList(): ArrayList<ProvinciaEntity>

    fun getRendicionForEdit(codRendicion: String?): RendicionEntity?

    fun getDefaultTipoGasto(rtgId: String?): TipoGastoEntity

    fun getDefaultProvincia(codDestino: String?): ProvinciaEntity
}