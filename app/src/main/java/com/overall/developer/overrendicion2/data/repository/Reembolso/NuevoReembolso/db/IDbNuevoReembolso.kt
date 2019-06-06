package com.overall.developer.overrendicion2.data.repository.reembolso.NuevoReembolso.db

import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion2.data.model.bean.UserBean

interface IDbNuevoReembolso
{
    fun insertNewRefundDB(reembolsoBean: ReembolsoBean)
    fun getUser(): UserBean
    fun getDefaultValesReembolso(codReembolso: String): ReembolsoBean
    fun getReembolso(): ReembolsoBean

}