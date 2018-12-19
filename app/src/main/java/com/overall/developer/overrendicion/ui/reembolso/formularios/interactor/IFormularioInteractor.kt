package com.overall.developer.overrendicion.ui.reembolso.formularios.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity

interface IFormularioInteractor {

    fun getReembolso(): ReembolsoEntity
    fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity>
    fun searchRuch(ruc: String)
    fun searchRucSuccess(razon_social: String)
    fun searchRucError()
    fun saveDate(typeFragment: ArrayList<String>, objectDinamyc: Any)
}