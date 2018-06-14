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
    void getAllDocument();
    UserBean getUser();
    void listPendiente(String dniUser);
    void filterLiquidacion(String entidad, String texto);
    void filterLiquidacionForUser(String dniUser);
    void insertProvincia(String dniUser);
    //endregion



    //region Gets
    void successPendienteList(String message);
    void errorPendienteList(String message);
    void setListPendiente(List<LiquidacionBean> pendienteBean);
    void setListPendienteForUser(List<LiquidacionBean> pendienteBean);
    void searchListPendienteResult(List<LiquidacionBean> pendienteBean);
    void finisLogin();
    //endregion
}
