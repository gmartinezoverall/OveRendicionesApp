package com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface IReembolsoPresenter
{
    fun getReembolsoList()
    fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)
}