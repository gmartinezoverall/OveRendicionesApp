package com.overall.developer.overrendicion2.data.repository.reembolso.Reembolso.db

import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion2.data.model.bean.UserBean

interface IDbReembolso
{
    fun changeStateAllReembolsoDB()

    fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>)

    fun getUserDb(): UserBean

    fun changeStatusReembolsoDB(codReembolso: String)
}