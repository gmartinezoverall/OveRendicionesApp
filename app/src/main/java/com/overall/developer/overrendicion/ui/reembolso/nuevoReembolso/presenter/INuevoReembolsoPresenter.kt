package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface INuevoReembolsoPresenter {

    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun insertNRSuccess()
}