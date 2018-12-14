package com.overall.developer.overrendicion.ui.reembolso.datosGenerales.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface INuevoReembolsoPresenter {

    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun successRestApi()
    fun getUser(): List<String>
    fun getDefaultValesReembolso(codReembolso: String): ReembolsoEntity

}