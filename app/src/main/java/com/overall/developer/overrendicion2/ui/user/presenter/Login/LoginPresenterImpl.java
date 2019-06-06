package com.overall.developer.overrendicion2.ui.user.presenter.Login;

import android.content.Context;
import com.overall.developer.overrendicion2.ui.user.interactor.Login.LoginInteractor;
import com.overall.developer.overrendicion2.ui.user.interactor.Login.LoginInteractorImpl;
import com.overall.developer.overrendicion2.ui.user.view.Login.LoginView;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public class LoginPresenterImpl implements LoginPresenter
{
    private LoginView mView;
    private LoginInteractor mInteractor;

    public LoginPresenterImpl(LoginView loginView, Context context)
    {
        this.mView = loginView;
        mInteractor= new LoginInteractorImpl(this, context);

    }

    @Override
    public boolean checkLogin() {
        return mInteractor.checkLogin();
    }


    @Override
    public void loginAccess(String usuario, String password) {
        mInteractor.loginAccess(usuario,password);
    }

    @Override
    public void loginRecovery(String dniUser)
    {
        mInteractor.loginRecovery(dniUser);
    }

    @Override
    public Observable validateSucces(String dniUser, String nombreUser, String emailUser)
    {
        return mView.validacionSucces(dniUser, nombreUser, emailUser);

    }

    @Override
    public Observable validateError(String errorMessaje)
    {
        return mView.validacionError(errorMessaje);

    }
}
