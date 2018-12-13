package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor

import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.UserBean
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.UserEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoBeanInEntity
import com.overall.developer.overrendicion.data.model.entity.convertReembolsoEntityInBean
import com.overall.developer.overrendicion.data.model.request.convertInsertReembolsoEntityToRequest
import com.overall.developer.overrendicion.data.model.request.convertUpdateReembolsoEntityToRequest
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.api.ApiNuevoReembolso
import com.overall.developer.overrendicion.data.repository.NuevoReembolso.db.DbNuevoReembolso
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.INuevoReembolsoPresenter
import java.text.SimpleDateFormat
import java.util.*

class NuevoReembolsoInteractor(internal val mPresenter: INuevoReembolsoPresenter): INuevoReembolsoInteractor
{

    internal val mDBNuevoReembolso = DbNuevoReembolso(this)
    internal val mApiNuevoReembolso = ApiNuevoReembolso(this)

    override fun saveDateNewRefund(reembolsoEntity: ReembolsoEntity) {

        val userBean = mDBNuevoReembolso.getUser()
        val reembolsoBean = mDBNuevoReembolso.getReembolso()

        reembolsoBean?.let { reembolsoEntity.codReemboslo = reembolsoBean.codReemboslo}
        reembolsoEntity.codComp = userBean.codCia
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

        if(reembolsoEntity.codReemboslo == "-")  mApiNuevoReembolso.insertNewRefundApi(convertInsertReembolsoEntityToRequest(reembolsoEntity))//ingresar nuevo reembolso por el WS
        else mApiNuevoReembolso.updateRefundApi(convertUpdateReembolsoEntityToRequest(reembolsoEntity))



    }

    override fun successRestApi()
    {
        mPresenter.successRestApi()
    }

    override fun getUser(): List<String>{

        val bean = mDBNuevoReembolso.getUser()

        return listOf(bean.compania, bean.numDocBeneficiario, bean.nombre)
    }

    override fun getDefaultValesReembolso(codReemboslo: String): ReembolsoEntity {
        val reembolsoBean: ReembolsoBean = mDBNuevoReembolso.getDefaultValesReembolso(codReemboslo)
        return convertReembolsoBeanInEntity(reembolsoBean)
    }
}