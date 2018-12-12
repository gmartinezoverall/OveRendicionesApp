package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter

import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.UserEntity

interface INuevoReembolsoPresenter {

    fun changeStateAllReembolso()
    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun insertNRSuccess()
    fun getUser(): List<String>

}