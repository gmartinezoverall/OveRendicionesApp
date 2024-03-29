package com.overall.developer.overrendicion2.ui.reembolso.reembolso.interactor

import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean

interface IReembolsoInteractor
{
    fun changeStateAllReembolso()

    fun getReembolsoList()

    fun listReembolsoApiSuccess(reembolsoBeans: List<ReembolsoBean>)

    fun listReembolsoApiError()

    fun changeStatusReembolso(codReembolso: String)

    fun sendResume(codReembolso: String)

    fun sendResumeSuccess()

}