package com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface IReembolsoPresenter
{
    fun changeStateAllReembolso()
    fun getReembolsoList()
    fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)
}