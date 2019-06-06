package com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.view

import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity

interface IDocumentosListView {
    fun listRendicionSuccess(rendicionEntityList: ArrayList<RendicionEntity>)
}