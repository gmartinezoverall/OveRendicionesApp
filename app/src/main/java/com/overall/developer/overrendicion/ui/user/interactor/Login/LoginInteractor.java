package com.overall.developer.overrendicion.ui.user.interactor.Login;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginInteractor
{
    boolean checkLogin();

    void loginAccess(String usuario, String password);
    void loginRecovery(String dniUser);
    void passwordRecoveryResponse(String message);
    void passwordRecoveryError(String message);

    Observable validateSucces(String dniUser, String nombreUser, String emailUser);
    Observable validateError(String errorMessaje);
}
