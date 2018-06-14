package com.overall.developer.overrendicion.data.repository.Pendiente;

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
    void getAllDocumentApi();
    Observable listPendienteApi(String dniUser);
    void insertProvinciaApi(String dniUser);

    //DB
    int getAllDocumentDB();
    void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList);
    UserBean getUser();
    void finisLogin();
    List<LiquidacionBean> listPendienteDB();
    List<LiquidacionBean > listPendienteDB(String entidad, String texto);
    String searchDniDB(String dniUser);
    Observable registerPendienteDB(List<LiquidacionBean> mPendienteBean);
    void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList);

    //endregion

    //region Sets
    void successPendienteList(String message, String dniUser);
    void errorPendienteList(String message);
    //endregion

}
