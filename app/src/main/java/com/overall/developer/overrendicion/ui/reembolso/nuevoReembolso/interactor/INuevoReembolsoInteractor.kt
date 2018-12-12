package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor

import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.UserEntity

interface INuevoReembolsoInteractor
{
    fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity)
    fun insertNRSuccess()
    fun getUser(): List<String>
    fun getDefaultValesReembolso(codReembolso: String ): ReembolsoEntity

}