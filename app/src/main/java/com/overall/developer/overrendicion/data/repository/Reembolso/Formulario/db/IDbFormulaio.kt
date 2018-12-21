package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db

import com.overall.developer.overrendicion.data.model.bean.*
import io.realm.RealmResults

interface IDbFormulaio {

    fun getReembolsoDB(): ReembolsoBean

    fun getDocumentForIdDB(idFragment: String): RealmResults<TipoDocumentoBean>

    fun getIdUsuarioDB(): UserBean

    fun getOtrosBean(): OtrosBean

    fun saveDataDB(bean: RendicionBean): Int

    fun deleteRendicionSend(idRendicion: Int)
}