package com.overall.developer.overrendicion2.ui.reembolso.reembolso.view

import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity

interface IReembolsoView
{
    fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)

    fun sendResumeSuccess()
}