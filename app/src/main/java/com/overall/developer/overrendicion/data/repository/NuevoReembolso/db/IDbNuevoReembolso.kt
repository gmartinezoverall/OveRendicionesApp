package com.overall.developer.overrendicion.data.repository.NuevoReembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean

interface IDbNuevoReembolso
{
    fun insertNewRefundDB(reembolsoBean: ReembolsoBean)
    fun getUser(): UserBean
    fun getDefaultValesReembolso(codReembolso: String): ReembolsoBean

}