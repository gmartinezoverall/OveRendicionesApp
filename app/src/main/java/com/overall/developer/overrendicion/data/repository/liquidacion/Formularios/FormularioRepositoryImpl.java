package com.overall.developer.overrendicion.data.repository.liquidacion.Formularios;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.OtrosBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion.data.repository.liquidacion.Formularios.api.ApiFormularios;
import com.overall.developer.overrendicion.data.repository.liquidacion.Formularios.api.ApiFormulariosImpl;
import com.overall.developer.overrendicion.data.repository.liquidacion.Formularios.db.DBFormularios;
import com.overall.developer.overrendicion.data.repository.liquidacion.Formularios.db.DBFormulariosImpl;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios.FormularioInteractor;

import java.util.List;

public class FormularioRepositoryImpl implements FormularioRepository
{
    FormularioInteractor mInteractor;
    ApiFormularios mApiFormularios;
    DBFormularios mDbFormularios;

    public FormularioRepositoryImpl(FormularioInteractor interactor)
    {
        this.mInteractor = interactor;
        mApiFormularios = new ApiFormulariosImpl(this);
        mDbFormularios = new DBFormulariosImpl(this);
    }

    @Override
    public List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento) {
        return mDbFormularios.getDocumentForIdDB(idDocumento);
    }

    @Override
    public List<ProvinciaBean> getProvinciaDestinoList() {
        return mDbFormularios.getProvinciaDestinoList();
    }

    @Override
    public Integer saveDataDB(RendicionBean rendicionBean)
    {
        return mDbFormularios.saveDataDB(rendicionBean);
    }

    @Override
    public void sendDataForInsertApi(RendicionRequest request, Integer idRendicion)
    {
        mApiFormularios.sendDataForInsertApi(request, idRendicion);

    }

    @Override
    public void sendDataForUpdateApi(RendicionRequest request, Integer idRendicion) {
        mApiFormularios.sendDataForUpdateApi(request, idRendicion);
    }

    @Override
    public LiquidacionBean getCodLiquidacionDB() {
        return mDbFormularios.getCodLiquidacionDB();

    }

    @Override
    public String getIdUsuarioDB() {
        return mDbFormularios.getIdUsuarioDB();
    }

    @Override
    public RendicionBean setRendicionForEditDB(Integer idRendicion) {
        return mDbFormularios.setRendicionForEditDB(idRendicion);
    }

    @Override
    public void deleteRendicionSend(String codRendicion, Integer idRendicion)
    {
        mDbFormularios.deleteForCodRendicion(codRendicion, idRendicion);
    }

    @Override
    public UserBean getUserDB() {
        return mDbFormularios.getUser();
    }

    @Override
    public void finisLoginDB() {
        mDbFormularios.finisLoginDB();
    }

    @Override
    public String getCodRendicion(Integer idRendicion) {
        return mDbFormularios.getCodRendicion(idRendicion);
    }

    @Override
    public List<BancoBean> getAllBancos() {
        return mDbFormularios.getAllBancos();
    }

    @Override
    public TipoDocumentoBean getDefaultTipoGastoDB(String rtgId) {
        return mDbFormularios.getDefaultTipoGastoDB(rtgId);
    }

    @Override
    public ProvinciaBean getDefaultProviciaDB(String codDestino) {
        return mDbFormularios.getDefaultProviciaDB(codDestino);
    }

    @Override
    public BancoBean getDefaultBancoDB(String bcoCod) {
        return mDbFormularios.getDefaultBancoDB(bcoCod);
    }

    @Override
    public RendicionDetalleBean setMovilidadForEditDB(int idMov) {
        return mDbFormularios.setMovilidadForEditDB(idMov);
    }

    @Override
    public void insertMovilidadDB(RendicionBean rendicionBean, RendicionDetalleBean detalleBean)
    {
        mDbFormularios.insertMovilidadDB(rendicionBean, detalleBean);
    }

    @Override
    public void sendDataInsertMovilidadApi(MovilidadInsertRequest movilidadRequest)
    {
        mApiFormularios.sendDataInsertMovilidadApi(movilidadRequest);

    }

    @Override
    public void sendDataUpdateMovilidadApi(MovilidadUpdateRequest updateRequest) {
        mApiFormularios.sendDataUpdateMovilidadApi(updateRequest);
    }

    @Override
    public void searchRucApi(String ruc)
    {
        mApiFormularios.searchRucApi(ruc);
    }

    @Override
    public void searchRucSuccess(String razonSocial)
    {
        mInteractor.searchRucSuccess(razonSocial);
    }

    @Override
    public void searchRucError() { mInteractor.searchRucError();}

    @Override
    public void sendDataInsertMovilidadMultipleApi(MovilidadMultipleRequest movilidadMultipleRequest) { mApiFormularios.sendDataInsertMovilidadMultipleApi(movilidadMultipleRequest);}

    @Override
    public void setTipoCambioApi(String fecha)
    {
        mApiFormularios.setTipoCambioApi(fecha);
    }

    @Override
    public void insertTipoCambioDB(String desc)
    {
        mDbFormularios.insertTipoCambioDB(desc);
    }

    @Override
    public String getTipoCambioDB()
    {
        return mDbFormularios.getTipoCambioDB();
    }

    @Override
    public LiquidacionBean getLiquidacionDB()
    {
        return mDbFormularios.getLiquidacionDB();
    }

    @Override
    public void errorTipoCambio()
    {
        mInteractor.errorTipoCambio();
    }

    @Override
    public void updateLiquidacionDB(RendicionBean bean) { mDbFormularios.updateLiquidacionDB(bean); }

    @Override
    public OtrosBean getOtrosBeanDB()
    {
        return mDbFormularios.getOtrosBeanDB();
    }

    @Override
    public Double getSumaAcuentaDB(String fechaViaje) {
        return mDbFormularios.getSumaAcuentaDB(fechaViaje);
    }

    @Override
    public RendicionBean getDetailMovOffLineDB()
    {
        return mDbFormularios.getDetailMovOffLineDB();
    }

}
