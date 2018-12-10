package com.overall.developer.overrendicion.data.repository.NuevoReembolso.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean

interface IDbNuevoReembolso {
    fun insertNewRefundDB(reembolsoBean: ReembolsoBean)
}