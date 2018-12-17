package com.overall.developer.overrendicion.data.repository.liquidacion.Pendiente.db;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;

public interface DBPendiente
{
    int setAllDocumentDB();

    int setAllBancoDB();

    void registerTypeDocDB(List<TipoDocumentoBean> documentoBeanList);

    void registerBancoDB(List<BancoBean> bancoBeans);

    UserBean getUser();

    void finisLogin();

    Observable registerPendienteDB(List<LiquidacionBean> mPendienteBean);

    String getCodLiquidacionDB();

    String searchDniDB(String dniUser);

    List<LiquidacionBean> listPendienteDB();

    List<LiquidacionBean> listPendienteDB(String entidad, String texto);

    void insertProvinciaDB(List<ProvinciaBean> provinciaBeanList);

    boolean validateRendicionisEmpyDB(String codLiquidacion);

    void insertRmvDB(String sueldo);

    boolean checkingPhone(String numDocBeneficiario);

    void saveTelefonoDB(String numTelefono);
}
