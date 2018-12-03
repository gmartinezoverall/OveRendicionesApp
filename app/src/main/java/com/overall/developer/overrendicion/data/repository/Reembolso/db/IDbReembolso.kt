package com.overall.developer.overrendicion.data.repository.Reembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean

interface IDbReembolso
{
    fun insertReembolsoListDB(reembolsoBeans: List<ReembolsoBean>): List<ReembolsoBean>
}