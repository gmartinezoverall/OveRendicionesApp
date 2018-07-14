package com.overall.developer.overrendicion.data.repository.Formularios;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion.data.repository.Formularios.api.ApiFormularios;
import com.overall.developer.overrendicion.data.repository.Formularios.api.ApiFormulariosImpl;
import com.overall.developer.overrendicion.data.repository.Formularios.db.DBFormularios;
import com.overall.developer.overrendicion.data.repository.Formularios.db.DBFormulariosImpl;
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
    public BancoBean getDefaultBancoDB(String bcoCod) {
        return mDbFormularios.getDefaultBancoDB(bcoCod);
    }


}
