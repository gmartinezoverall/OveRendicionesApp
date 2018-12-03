package com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean

interface IReembolsoInteractor
{
    fun getReembolsoList()
    fun listReembolsoApiSuccess(reembolsoBeans: List<ReembolsoBean>)
    fun listReembolsoApiError()
}