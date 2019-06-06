package com.overall.developer.overrendicion2.ui.reembolso.datosGenerales.interactor

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity

interface INuevoReembolsoInteractor
{
    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun successRestApi()
    fun getUser(): List<String>
    fun getDefaultValesReembolso(codReembolso: String ): ReembolsoEntity

}