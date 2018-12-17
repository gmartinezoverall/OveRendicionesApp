package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.interactor

import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion.data.repository.reembolso.documentosReembolsoList.api.ApiDocumentosList
import com.overall.developer.overrendicion.data.repository.reembolso.documentosReembolsoList.db.DbDocumentosList
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.presenter.IDocumentosListPresenter

class DocumentosListInteractor(internal val mPresenter: IDocumentosListPresenter): IDocumentosListInteractor
{
    internal val mDb = DbDocumentosList(this)
    internal val mApi = ApiDocumentosList(this)

    override fun setDocumentosList()
    {
        mApi.setDocumentosListApi(mDb.getReembolsoBean().codReembolso)
    }

    override fun getDocumentosReembolso(): ArrayList<RendicionEntity> {
        val rendicionBeanList = mDb.getDocumentosReembolsoDB()
        val rendicionList = ArrayList<RendicionEntity>()
        rendicionBeanList.map {
            rendicionList.add(RendicionEntity(it.idRendicion, it.codRendicion, it.rdoId, it.codReembolso,
                    it.idUsuario, it.numeroDoc, it.bienServicio, it.igv, it.afectoIgv, it.valorNeto, it.precioTotal,
                    it.observacion, it.fechaDocumento, it.fechaVencimiento, it.ruc, it.razonSocial, it.bcoCod,
                    it.tipoServicio, it.rtgId, it.otroGasto, it.codDestino, it.afectoRetencion, it.codSuspencionH,
                    it.tipoMoneda, it.tipoCambio, it.foto, it.send))
        }
        return rendicionList
    }

    override fun successGetRendicionesListtApi(reembolsoBeans: ArrayList<RendicionReembolsoBean>) {
        mDb.insertRendicionDB(reembolsoBeans)
        //val rendicionEntityList = arrayListOf<RendicionEntity>()

/*        reembolsoBeans.map { rendicionEntityList.add(RendicionEntity(it.idRendicion, it.codRendicion, it.rdoId,
                it.codReembolso, it.idUsuario, it.numeroDoc, it.bienServicio, it.igv, it.afectoIgv, it.valorNeto,
                it.precioTotal, it.observacion, it.fechaDocumento, it.fechaVencimiento, it.ruc, it.razonSocial,
                it.bcoCod, it.tipoServicio, it.rtgId, it.otroGasto, it.codDestino, it.afectoRetencion, it.codSuspencionH,
                it.tipoMoneda, it.tipoCambio, it.foto, it.send)) }*/

        mPresenter.listRendicionSuccess(getDocumentosReembolso())

    }
}