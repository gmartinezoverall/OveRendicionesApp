package com.overall.developer.overrendicion.ui.reembolso.formularios.presenter

import com.overall.developer.overrendicion.ui.reembolso.formularios.interactor.FormularioInteractor
import com.overall.developer.overrendicion.ui.reembolso.formularios.view.IFormularioView

class FormularioPresenter (internal val mView: IFormularioView): IFormularioPresenter
{
    internal val mInteractor= FormularioInteractor(this)
}