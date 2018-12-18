package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean
import com.overall.developer.overrendicion.ui.reembolso.formularios.interactor.IFormularioInteractor
import io.realm.Realm
import io.realm.RealmResults

class DbFormulario(internal val mInteractor: IFormularioInteractor): IDbFormulaio
{
    val realm = Realm.getDefaultInstance()
    override fun getReembolsoDB(): ReembolsoBean = realm.where(ReembolsoBean::class.java).equalTo("estadoR",true).findFirst()!!

    override fun getDocumentForIdDB(idFragment: String): RealmResults<TipoDocumentoBean> = realm.where(TipoDocumentoBean::class.java).equalTo("rdoId", idFragment).findAll()
}