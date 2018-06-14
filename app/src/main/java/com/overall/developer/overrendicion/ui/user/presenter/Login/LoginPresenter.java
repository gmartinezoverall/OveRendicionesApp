package com.overall.developer.overrendicion.ui.user.presenter.Login;

import android.widget.FrameLayout;

import com.goka.kenburnsview.KenBurnsView;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginPresenter
{
    boolean checkLogin();
    void backgroundAnimation(KenBurnsView kenBurnsView, FrameLayout frameLayout);

    void loginAccess(String usuario, String password);
    void loginRecovery(String dniUser);

    Observable validateSucces(String dniUser, String nombrUser, String emamilUser);
    Observable validateError(String errorMessaje);
}
