package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface INuevoReembolsoInteractor
{
    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun insertNRSuccess()

}