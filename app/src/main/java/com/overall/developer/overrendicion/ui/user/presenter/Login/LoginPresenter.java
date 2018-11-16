package com.overall.developer.overrendicion.ui.user.presenter.Login;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginPresenter
{
    boolean checkLogin();


    void loginAccess(String usuario, String password);
    void loginRecovery(String dniUser);

    Observable validateSucces(String dniUser, String nombrUser, String emamilUser);
    Observable validateError(String errorMessaje);

}
