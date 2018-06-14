package com.overall.developer.overrendicion.ui.user.view.Login;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginView
{
    Observable validacionSucces(String dniUser, String nombreUser, String emailUser);
    Observable validacionError(String errorMessaje);
}
