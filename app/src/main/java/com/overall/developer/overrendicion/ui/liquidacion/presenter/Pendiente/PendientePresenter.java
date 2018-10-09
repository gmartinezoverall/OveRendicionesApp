package com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente;


import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

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
    //endregion



    //region Gets
    void successPendienteList(String message);
    void refreshListSuccess(List<LiquidacionBean> listPendiente);
    void errorPendienteList(String message);
    void setListPendiente(List<LiquidacionBean> pendienteBean);
    void setListPendienteForUser(List<LiquidacionBean> pendienteBean);
    void searchListPendienteResult(List<LiquidacionBean> pendienteBean);
    void finisLogin();

    //endregion
}
