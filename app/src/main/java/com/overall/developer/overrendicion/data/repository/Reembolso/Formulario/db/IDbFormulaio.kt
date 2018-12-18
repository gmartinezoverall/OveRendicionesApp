package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean
import io.realm.RealmResults

interface IDbFormulaio {
    fun getReembolsoDB(): ReembolsoBean
    fun getDocumentForIdDB(idFragment: String): RealmResults<TipoDocumentoBean>
}