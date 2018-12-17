package com.overall.developer.overrendicion.ui.reembolso.formularios.interactor

import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.api.ApiFormulario
import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db.DbFormulario
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.IFormularioPresenter

class FormularioInteractor(internal val mPresenter: IFormularioPresenter): IFormularioInteractor
{
    internal val mDb = DbFormulario(this)
    internal val mApi = ApiFormulario(this)

}