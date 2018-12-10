package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor

import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoEntityInBean
import com.overall.developer.overrendicion.data.model.request.convertReembolsoEntityToRequest
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.api.ApiNuevoReembolso
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.db.DbNuevoReembolso
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.INuevoReembolsoPresenter

class NuevoReembolsoInteractor(internal val mPresenter: INuevoReembolsoPresenter): INuevoReembolsoInteractor
{

    internal val mDBNuevoReembolso = DbNuevoReembolso(this)
    internal val mApiNuevoReembolso = ApiNuevoReembolso(this)

    override fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity) {

        mDBNuevoReembolso.insertNewRefundDB(convertReembolsoEntityInBean(reembolsoEntity))//Inserta en el BD un nuevo reembolso

        mApiNuevoReembolso.insertNewRefundApi(convertReembolsoEntityToRequest(reembolsoEntity))//ingresar nuevo reembolso por el WS

        mPresenter.insertNRSuccess()
    }

    override fun insertNRSuccess()
    {
        mPresenter.insertNRSuccess()
    }

}