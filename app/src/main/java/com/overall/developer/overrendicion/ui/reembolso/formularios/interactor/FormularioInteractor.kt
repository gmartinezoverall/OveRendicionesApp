package com.overall.developer.overrendicion.ui.reembolso.formularios.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoBeanInEntity
import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.api.ApiFormulario
import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db.DbFormulario
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.IFormularioPresenter

class FormularioInteractor(internal val mPresenter: IFormularioPresenter): IFormularioInteractor
{
    internal val mDb = DbFormulario(this)
    internal val mApi = ApiFormulario(this)

    override fun getReembolso(): ReembolsoEntity {
        val bean = mDb.getReembolsoDB()
        return convertReembolsoBeanInEntity(bean)
    }

    override fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity> {

        val mList = ArrayList<TipoGastoEntity>()
        mDb.getDocumentForIdDB(idFragment).map { mList.add(TipoGastoEntity(it.rtgId,it.rtgDes)) }
        return mList
    }

    override fun searchRuch(ruc: String) { mApi.searchRuchApi(ruc)}

    override fun searchRucSuccess(razonSocial: String) {mPresenter.searchRucSuccess(razonSocial)   }

    override fun searchRucError() {mPresenter.searchRucError()}
}