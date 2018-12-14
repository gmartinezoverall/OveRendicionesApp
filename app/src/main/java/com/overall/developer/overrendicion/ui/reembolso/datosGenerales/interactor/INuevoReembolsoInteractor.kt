package com.overall.developer.overrendicion.ui.reembolso.datosGenerales.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface INuevoReembolsoInteractor
{
    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun successRestApi()
    fun getUser(): List<String>
    fun getDefaultValesReembolso(codReembolso: String ): ReembolsoEntity

}