package com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente;


import com.overall.developer.overrendicion.data.model.bean.UserBean;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteInteractor
{
    //region Gets.
    void getAllDocument();
    UserBean getUser();
    void finisLogin();
    void listPendiente(String dniUser);
    void filterLiquidacion(String entidad, String texto);
    void filterLiquidacionForUser(String dniUser);
    void insertProvincia(String dniUser);

    //endregion

    //region Sets
    void successPendienteList(String message, String dniUser);
    void errorPendienteList(String message);
    //endregion
}
