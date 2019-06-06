package com.overall.developer.overrendicion2.data.repository.reembolso.documentosReembolsoList.db

import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion2.data.model.bean.RendicionReembolsoBean
import io.realm.RealmResults

interface IDbDocumentosList {

    fun getReembolsoBean(): ReembolsoBean

    fun getDocumentosReembolsoDB(): RealmResults<RendicionReembolsoBean>

    fun insertRendicionDB(reembolsoBeans: ArrayList<RendicionReembolsoBean>)

    fun deleteRendicionDb(codRendicion: String)
}