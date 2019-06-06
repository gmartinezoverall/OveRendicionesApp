package com.overall.developer.overrendicion2.ui.reembolso.datosGenerales.presenter

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity

interface INuevoReembolsoPresenter {

    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun successRestApi()
    fun getUser(): List<String>
    fun getDefaultValesReembolso(codReembolso: String): ReembolsoEntity

}