package com.overall.developer.overrendicion.data.repository.documentosReembolsoList.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import io.realm.RealmResults

interface IDbDocumentosList {
    fun getReembolsoBean(): ReembolsoBean
    fun getDocumentosReembolsoDB(): RealmResults<RendicionReembolsoBean>
    fun insertRendicionDB(reembolsoBeans: ArrayList<RendicionReembolsoBean>)
}