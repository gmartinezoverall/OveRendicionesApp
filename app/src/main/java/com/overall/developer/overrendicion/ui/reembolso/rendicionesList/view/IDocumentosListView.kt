package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.view

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity

interface IDocumentosListView {
    fun listRendicionSuccess(rendicionEntityList: ArrayList<RendicionEntity>)
}