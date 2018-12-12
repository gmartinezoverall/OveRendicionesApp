package com.overall.developer.overrendicion.data.repository.Reembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean

interface IDbReembolso
{
    fun changeStateAllReembolsoDB()
    fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>): List<ReembolsoBean>
    fun getUserDb(): UserBean
}