package com.overall.developer.overrendicion.ui.liquidacion.view.pendiente;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;

import java.util.List;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteView
{

    //region Sets
    void successPendienteList(String message);
    void errorPendienteList(String message);
    void setListPendiente(List<LiquidacionBean> pendienteBean);
    void searchListPendienteResult(List<LiquidacionBean> pendienteBean);
    void setListPendienteForUser(List<LiquidacionBean> pendienteBean);


    //endregion

}
