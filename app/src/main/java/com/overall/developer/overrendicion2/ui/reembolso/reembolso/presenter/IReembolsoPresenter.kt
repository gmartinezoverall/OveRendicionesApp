package com.overall.developer.overrendicion2.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity

interface IReembolsoPresenter
{
    fun changeStateAllReembolso()

    fun getReembolsoList()

    fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)

    fun changeStatusReembolso(codReembolso: String)

    fun sendResume(codReembolso: String)

    fun sendResumeSuccess()

}