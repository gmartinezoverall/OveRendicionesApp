package com.overall.developer.overrendicion.ui.reembolso.reembolso.view

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface IReembolsoView
{
    fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)

    fun sendResumeSuccess()
}