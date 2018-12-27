package com.overall.developer.overrendicion.data.repository.reembolso.Reembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean

interface IDbReembolso
{
    fun changeStateAllReembolsoDB()

    fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>)

    fun getUserDb(): UserBean

    fun changeStatusReembolsoDB(codReembolso: String)
}