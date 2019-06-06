package com.overall.developer.overrendicion2.ui.liquidacion.interactor.Pendiente;


import com.overall.developer.overrendicion2.data.model.bean.UserBean;

/**
 * Created by terry on 3/22/2018.
 */

public interface PendienteInteractor
{
    //region Gets.
    UserBean getUser();

    void finisLogin();

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

    //region Sets
    void successPendienteList(String message, String dniUser);

    void errorPendienteList(String message);

    void refreshListSuccess(String dniUser);

    void successSendResume();

    void errorSendResume();

    void saveTelefono(String numTelefono);

    //endregion
}
