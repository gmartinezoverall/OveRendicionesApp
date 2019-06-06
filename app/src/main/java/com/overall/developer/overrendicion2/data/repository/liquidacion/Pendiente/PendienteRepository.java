package com.overall.developer.overrendicion2.data.repository.liquidacion.Pendiente;

import com.overall.developer.overrendicion2.data.model.bean.BancoBean;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;

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
    void setRmvApi();

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

    void sendResumeEmailApi(String dni,String codRendicion);
    boolean validateRendicionisEmpyDB(String codLiquidacion);
    void insertRmvDB(String sueldo);

    boolean checkingPhone(String numDocBeneficiario);
    void saveTelefonoDB(String numTelefono);

      //endregion

    //region Sets
    void successPendienteList(String message, String dniUser);
    void errorPendienteList(String message);
    void refreshListSuccess(String dniUser);

    void successSendResume();
    void errorSendResume();



    //endregion

}
