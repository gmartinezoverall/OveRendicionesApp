package com.overall.developer.overrendicion.data.repository.Pendiente;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Pendiente.api.ApiPendiente;
import com.overall.developer.overrendicion.data.repository.Pendiente.api.ApiPendienteImpl;
import com.overall.developer.overrendicion.data.repository.Pendiente.db.DBPendiente;
import com.overall.developer.overrendicion.data.repository.Pendiente.db.DBPendienteImpl;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente.PendienteInteractor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by terry on 3/22/2018.
 */

public class PendienteRepositoryImpl implements PendienteRepository
{
    private PendienteInteractor mInteractor;
    private ApiPendiente mApiPendiente;
    private DBPendiente mDbPendiente;

    public PendienteRepositoryImpl(PendienteInteractor interactor)
    {
        this.mInteractor = interactor;
        mApiPendiente = new ApiPendienteImpl(this);
        mDbPendiente = new DBPendienteImpl(this);

    }

    //region Interfaces
    @Override
    public int getAllDocumentDB()
    {
        return mDbPendiente.getAllDocumentDB();
    }

    @Override
    public void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList) {
        mDbPendiente.registerTypeDocDB(documentoBeanList);
    }

    @Override
    public void getAllDocumentApi()
    {
        mApiPendiente.getAllDocumentApi();
    }

    @Override
    public String searchDniDB(String dniUser)
    {
        return mDbPendiente.searchDniDB(dniUser);
    }

    @Override
    public Observable listPendienteApi(String dniUser)
    {
        return mApiPendiente.listPendienteApi(dniUser);
    }

    @Override
    public Observable registerPendienteDB(List<LiquidacionBean> pendienteBean)
    {
        return mDbPendiente.registerPendienteDB(pendienteBean);
    }

    @Override
    public void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList)
    {
        mDbPendiente.insertProvinciaDB(provinciaBeanList);

    }

    @Override
    public List<LiquidacionBean>  listPendienteDB()
    {
        return mDbPendiente.listPendienteDB();
    }

    @Override
    public List<LiquidacionBean> listPendienteDB(String entidad, String texto) {
        return mDbPendiente.listPendienteDB(entidad, texto);
    }

    @Override
    public void insertProvinciaApi(String dniUser)
    {
        mApiPendiente.insertProvinciaApi(dniUser);
    }

    @Override
    public UserBean getUser() {
        return mDbPendiente.getUser();
    }

    @Override
    public void finisLogin()
    {
        mDbPendiente.finisLogin();
    }

    @Override
    public void successPendienteList(String message, String dniUser)
    {
        mInteractor.successPendienteList(message, dniUser);

    }

    @Override
    public void errorPendienteList(String message)
    {
        mInteractor.errorPendienteList(message);
    }



    //endregion
}
