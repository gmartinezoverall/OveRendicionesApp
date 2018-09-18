package com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente;


import com.overall.developer.overrendicion.data.model.bean.UserBean;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteInteractor
{
    //region Gets.
    void setAllDocument();
    void setAllBanco();
    UserBean getUser();
    void finisLogin();
    void listPendiente(String dniUser);
    void filterLiquidacion(String entidad, String texto);
    void filterLiquidacionForUser(String dniUser);
    void insertProvincia(String dniUser);
    void refreshList(String dniUser);
    void sendResumeEmail();
    int pendienteListCount();
    //endregion

    //region Sets
    void successPendienteList(String message, String dniUser);
    void errorPendienteList(String message);
    void refreshListSuccess(String dniUser);
    void successSendResume();
    void errorSendResume();


    //endregion
}
