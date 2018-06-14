package com.overall.developer.overrendicion.ui.user.interactor.Login;

import io.reactivex.Observable;
import android.widget.FrameLayout;

import com.goka.kenburnsview.KenBurnsView;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginInteractor
{
    boolean checkLogin();
    void backgroundAnimation(KenBurnsView kenBurnsView, FrameLayout frameLayout);

    void loginAccess(String usuario, String password);
    void loginRecovery(String dniUser);
    void passwordRecoveryResponse(String message);
    void passwordRecoveryError(String message);

    Observable validateSucces(String dniUser, String nombreUser, String emailUser);
    Observable validateError(String errorMessaje);
}
