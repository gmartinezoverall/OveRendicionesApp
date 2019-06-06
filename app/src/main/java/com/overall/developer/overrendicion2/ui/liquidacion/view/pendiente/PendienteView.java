package com.overall.developer.overrendicion2.ui.liquidacion.view.pendiente;

import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;

import java.util.List;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteView
{

    //region Sets
    void successPendienteList(String message);

    void refreshListSuccess(List<LiquidacionBean> listPendiente);

    void errorPendienteList(String message);

    void setListPendiente(List<LiquidacionBean> pendienteBean);

    void searchListPendienteResult(List<LiquidacionBean> pendienteBean);

    void setListPendienteForUser(List<LiquidacionBean> pendienteBean);

    void successSendResume();

    void errorSendResume();

    //endregion

}
