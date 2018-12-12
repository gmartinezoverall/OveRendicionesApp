package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor

import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.UserEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoEntityInBean
import com.overall.developer.overrendicion.data.model.request.convertReembolsoEntityToRequest
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.api.ApiNuevoReembolso
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.db.DbNuevoReembolso
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.INuevoReembolsoPresenter
import java.text.SimpleDateFormat
import java.util.*

class NuevoReembolsoInteractor(internal val mPresenter: INuevoReembolsoPresenter): INuevoReembolsoInteractor
{

    internal val mDBNuevoReembolso = DbNuevoReembolso(this)
    internal val mApiNuevoReembolso = ApiNuevoReembolso(this)

    override fun changeStateAllReembolso() {
        mDBNuevoReembolso.changeStateAllReembolsoDB()
    }

    override fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity) {

        val userBean = mDBNuevoReembolso.getUser()

        reembolsoEntity.codComp = "03"
        reembolsoEntity.codTrab = userBean.idUsuario//codTrab
         when(reembolsoEntity.descTReembolso)
        {
             "VIATICOS" -> reembolsoEntity.codTReembolso = "16"
             "MOVILIDAD" -> reembolsoEntity.codTReembolso = "17"
             "CAJA CHICA" -> reembolsoEntity.codTReembolso = "18"
             "ENTREGAS A RENDIR" -> reembolsoEntity.codTReembolso = "19"
        }
        reembolsoEntity.tipoMoneda = (if (reembolsoEntity.tipoMoneda.equals("Soles") ) "S" else "D")

        //if (reembolsoEntity.codReemboslo == "-") reembolsoEntity.codReemboslo = SimpleDateFormat("yyyyMMddHHmmss").format(Date())

        mDBNuevoReembolso.insertNewRefundDB(convertReembolsoEntityInBean(reembolsoEntity))//Inserta en el BD un nuevo reembolso

        mApiNuevoReembolso.insertNewRefundApi(convertReembolsoEntityToRequest(reembolsoEntity))//ingresar nuevo reembolso por el WS

    }

    override fun insertNRSuccess()
    {
        mPresenter.insertNRSuccess()
    }

    override fun getUser(): List<String>{

        val bean = mDBNuevoReembolso.getUser()

        return listOf("OVERALL STRATEGY", bean.numDocBeneficiario, bean.nombre)
    }
}