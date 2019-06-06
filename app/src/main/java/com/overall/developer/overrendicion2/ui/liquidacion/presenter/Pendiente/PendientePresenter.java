package com.overall.developer.overrendicion2.ui.liquidacion.presenter.Pendiente;


import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;

import java.util.List;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendientePresenter
{
    //region Sets
    UserBean getUser();

    void listPendiente(String dniUser);

    void filterLiquidacion(String entidad, String texto);

    void filterLiquidacionForUser(String dniUser);

    void insertProvincia(String dniUser);

    void refreshList(String dniUser);

    void sendResumeEmail(String codRendicion);

    boolean validateRendicionisEmpy(String codLiquidacion);

    void initialDefaultApis();

    boolean checkingPhone();
    //endregion



    //region Gets
    void successPendienteList(String message);

    void refreshListSuccess(List<LiquidacionBean> listPendiente);

    void errorPendienteList(String message);

    void setListPendiente(List<LiquidacionBean> pendienteBean);

    void setListPendienteForUser(List<LiquidacionBean> pendienteBean);

    void searchListPendienteResult(List<LiquidacionBean> pendienteBean);

    void finisLogin();

    void successSendResume();

    void errorSendResume();

    void saveTelefono(String numTelefono);


    //endregion
}
