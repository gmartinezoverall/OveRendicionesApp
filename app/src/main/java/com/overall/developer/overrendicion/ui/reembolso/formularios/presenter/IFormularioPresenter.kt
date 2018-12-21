package com.overall.developer.overrendicion.ui.reembolso.formularios.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity

interface IFormularioPresenter {

    fun getReembolso(): ReembolsoEntity

    fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity>

    fun searchRuch(ruc: String)

    fun searchRucSuccess(razonSocial: String)

    fun searchRucError()

    fun saveDate(typeFragment: ArrayList<String>, objectDinamyc: Any)

    fun saveDataSuccess()
}