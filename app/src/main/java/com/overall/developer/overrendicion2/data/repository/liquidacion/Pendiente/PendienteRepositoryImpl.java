package com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente;

import com.overall.developer.overrendicion2.data.model.bean.BancoBean;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.api.ApiPendiente;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.api.ApiPendienteImpl;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.db.DBPendiente;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente.db.DBPendienteImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.interactor.Pendiente.PendienteInteractor;

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
    public int setAllDocumentDB()
    {
        return mDbPendiente.setAllDocumentDB();
    }

    @Override
    public int setAllBancoDB() {
        return mDbPendiente.setAllBancoDB();
    }

    @Override
    public void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList) {
        mDbPendiente.registerTypeDocDB(documentoBeanList);
    }

    @Override
    public void registerBancoDB(List<BancoBean> bancoBeans) {
        mDbPendiente.registerBancoDB(bancoBeans);
    }

    @Override
    public void setAllDocumentApi()
    {
        mApiPendiente.setAllDocumentApi();
    }

    @Override
    public void setAllBancoApi()
    {
        mApiPendiente.setAllBancoApi();
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
    public String getCodLiquidacionDB() {
        return mDbPendiente.getCodLiquidacionDB();
    }

    @Override
    public void sendResumeEmailApi(String dni,String codRendicion)
    {
        mApiPendiente.sendResumeEmailApi(dni, codRendicion);
    }

    @Override
    public boolean validateRendicionisEmpyDB(String codLiquidacion)
    {
        return mDbPendiente.validateRendicionisEmpyDB(codLiquidacion);
    }

    @Override
    public void insertRmvDB(String sueldo)
    {
        mDbPendiente.insertRmvDB(sueldo);
    }

    @Override
    public boolean checkingPhone(String numDocBeneficiario) {
        return mDbPendiente.checkingPhone(numDocBeneficiario);
    }

    @Override
    public void saveTelefonoDB(String numTelefono) {
        mDbPendiente.saveTelefonoDB(numTelefono);
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
    public void refreshListPendienteApi(String dniUser)
    {
        mApiPendiente.refreshListPendienteApi(dniUser);
    }

    @Override
    public void setRmvApi() {
        mApiPendiente.setRmvApi();
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

    @Override
    public void refreshListSuccess(String dniUser)
    {
        mInteractor.refreshListSuccess(dniUser);
    }

    @Override
    public void successSendResume() { mInteractor.successSendResume();}

    @Override
    public void errorSendResume() { mInteractor.errorSendResume();}

    //endregion
}
