package com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity

interface IReembolsoInteractor
{
    fun onLogin(email:String, password:String)
    fun getReembolsoList(): List<ReembolsoEntity>
}