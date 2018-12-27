package com.overall.developer.overrendicion.ui.reembolso.formularios.interactor

import android.content.Context
import com.overall.developer.overrendicion.BuildConfig
import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.data.model.bean.RendicionBean
import com.overall.developer.overrendicion.data.model.entity.*
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.*
import com.overall.developer.overrendicion.data.model.request.InsertRendicionReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.RendicionRequest
import com.overall.developer.overrendicion.data.model.request.convertEditRendicionEntityToRequest
import com.overall.developer.overrendicion.data.model.request.convertInsertRendicionEntityToRequest
import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.api.ApiFormulario
import com.overall.developer.overrendicion.data.repository.reembolso.Formulario.db.DbFormulario
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.IFormularioPresenter
import com.overall.developer.overrendicion.utils.Util
import com.overall.developer.overrendicion.utils.aws.AwsUtility
import java.io.File

class FormularioInteractor(internal val mPresenter: IFormularioPresenter, context: Context): IFormularioInteractor
{
    private val mDb = DbFormulario(this)
    private val mApi = ApiFormulario(this)
    private val mContext = context

    override fun getReembolso(): ReembolsoEntity {
        val bean = mDb.getReembolsoDB()
        return convertReembolsoBeanInEntity(bean)
    }

    override fun getListSpinner(idFragment: String): ArrayList<TipoGastoEntity> {

        val mList = ArrayList<TipoGastoEntity>()
        mDb.getDocumentForIdDB(idFragment).map { mList.add(TipoGastoEntity(it.rtgId,it.rtgDes)) }
        return mList
    }

    override fun getProvinciaDestinoList(): ArrayList<ProvinciaEntity> {
        //for (bean in mRepository.getProvinciaDestinoList()) mList.add(ProvinciaEntity(bean.getCode(), bean.getDesc()))
        val mList = ArrayList<ProvinciaEntity>()
        mDb.getProvinciaDestinoList().map{mList.add(ProvinciaEntity(it.code, it.desc)) }
        return mList
    }

    override fun searchRuch(ruc: String) { mApi.searchRuchApi(ruc)}

    override fun searchRucSuccess(razonSocial: String) {mPresenter.searchRucSuccess(razonSocial)   }

    override fun searchRucError() {mPresenter.searchRucError()}

    override fun saveDate(typeFragment: ArrayList<String>, objectDinamyc: Any) {

        val codReembolso = mDb.getReembolsoDB().codReembolso

        val entity = filterFragment(typeFragment[0].toInt(), objectDinamyc)
        if (typeFragment.size > 2)entity.idRendicion = typeFragment[2].toInt()
        entity.codRendicion = (if(entity.idRendicion == null) "-" else  mDb.getRendicion(entity.idRendicion).codRendicion)
        entity.codLiquidacion = codReembolso
        entity.idUsuario = mDb.getIdUsuarioDB().idUsuario
        entity.precioTotal = (entity.precioTotal.toDouble() - entity.otroGasto.toDouble()).toString()
        if (entity.tipoMoneda == "D") entity.tipoCambio = mDb.getOtrosBean().tipoCambio
        val pathTemp = entity.foto
        entity.foto = Util.SaveImage(entity.foto)
        val file = File(pathTemp)
        file.delete()

        val bean = RendicionBean(entity.idRendicion, entity.codRendicion, entity.rdoId, typeFragment[1], entity.codLiquidacion, entity.idUsuario,
                entity.numeroDoc, entity.bienServicio, entity.igv, entity.afectoIgv, entity.valorNeto, entity.precioTotal,
                entity.observacion, entity.fechaDocumento, entity.fechaVencimiento, entity.ruc, entity.razonSocial,
                entity.bcoCod, entity.tipoServicio, entity.rtgId, entity.otroGasto, entity.codDestino,
                entity.afectoRetencion, entity.codSuspencionH, entity.tipoMoneda, entity.tipoCambio, entity.foto, false)

        val idRendicion = mDb.saveDataDB(bean)

        if (Util.isOnline()){
            AwsUtility.UploadTransferUtilityS3(mContext, entity.foto)
            entity.foto = BuildConfig.URL_AWS + entity.foto.substring(34)//se genera la URL de AWS para enviarlo por el WS

            if (entity.codRendicion == "-") {
                entity.codRendicion = ""

                val request = convertInsertRendicionEntityToRequest(entity)
                mApi.sendDataForInsertApi(request, idRendicion)
            } else {

                val request = convertEditRendicionEntityToRequest(entity)

                mApi.sendDataForUpdateApi(request, idRendicion)
            }

        }
        mPresenter.saveDataSuccess()

    }

    override fun deleteRendicionSend(idRendicion: Int) {
        mDb.deleteRendicionSend(idRendicion)
    }

    override fun getRendicionForEdit(codRendicion: String?): RendicionEntity? {

        val bean = mDb.getRendicionForEdit(codRendicion)!!

        return  RendicionEntity(bean.idRendicion, bean.codRendicion, bean.rdoId, bean.codReembolso, bean.idUsuario, bean.numeroDoc, bean.bienServicio,
                bean.afectoIgv, bean.afectoIgv, bean.valorNeto, bean.precioTotal, bean.observacion, bean.fechaDocumento, bean.fechaVencimiento, bean.ruc, bean.razonSocial,
                bean.bcoCod, bean.tipoServicio, bean.rtgId, bean.otroGasto, bean.codDestino, bean.afectoRetencion, bean.codSuspencionH, bean.tipoMoneda, bean.tipoCambio,
                bean.foto, bean.send)

    }

    override fun getDefaultTipoGasto(rtgId: String?): TipoGastoEntity {
        val bean = mDb.getDefaultTipoGastoDB(rtgId)
        return TipoGastoEntity(bean.rtgId, bean.rtgDes)
    }

    override fun getDefaultProvincia(codDestino: String?): ProvinciaEntity {
        val bean = mDb.getDefaultProvinciaDB(codDestino)
        return ProvinciaEntity(bean.code, bean.desc)
    }

    private fun filterFragment(typeFragment: Int, dinamyObj: Any) : RendicionEntity {

        return when (typeFragment) {
            1 -> FacturaEntity().getEntity(dinamyObj)
            2 -> BoletaVentaEntity().getEntity(dinamyObj)
            5 -> ReciboHonorariosEntity().getEntity(dinamyObj)
            8 -> BoletoAereoEntity().getEntity(dinamyObj)
            9 -> BoletoTerrestreEntity().getEntity(dinamyObj)
            11 -> ReciboServiciosPublicosEntity().getEntity(dinamyObj)
            12 -> SinSustentoTributarioEntity().getEntity(dinamyObj)
            13 -> OtrosDocumentosEntity().getEntity(dinamyObj)
            14 -> ArrendamientoEntity().getEntity(dinamyObj)
            15 -> TicketMaquinaRegistradoraEntity().getEntity(dinamyObj)
            17 -> VoucherBancarioEntity().getEntity(dinamyObj)
            //21 -> CartaPorteAereoEntity().getEntity(dinamyObj)
            else -> {
                CartaPorteAereoEntity().getEntity(dinamyObj)
            }
        }
    }
}