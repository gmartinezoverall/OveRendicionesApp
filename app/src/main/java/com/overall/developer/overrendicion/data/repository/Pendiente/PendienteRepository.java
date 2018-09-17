package com.overall.developer.overrendicion.data.repository.Pendiente;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteRepository
{
    //region Gets
    //API
    void setAllDocumentApi();
    void setAllBancoApi();
    Observable listPendienteApi(String dniUser);
    void insertProvinciaApi(String dniUser);
    void refreshListPendienteApi(String dniUser);


    //DB
    int setAllDocumentDB();
    int setAllBancoDB();
    void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList);
    void registerBancoDB(List<BancoBean> bancoBeans);
    UserBean getUser();
    void finisLogin();
    List<LiquidacionBean> listPendienteDB();
    List<LiquidacionBean > listPendienteDB(String entidad, String texto);
    String searchDniDB(String dniUser);
    Observable registerPendienteDB(List<LiquidacionBean> mPendienteBean);
    void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList);
    String getCodLiquidacionDB();
    void sendResumeEmailApi(String codLiquidacion);

    //endregion

    //region Sets
    void successPendienteList(String message, String dniUser);
    void errorPendienteList(String message);
    void refreshListSuccess(String dniUser);

    void successSendResume();
    void errorSendResume();
    //endregion

}
