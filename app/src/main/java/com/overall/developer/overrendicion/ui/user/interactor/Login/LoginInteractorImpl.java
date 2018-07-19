package com.overall.developer.overrendicion.ui.user.interactor.Login;

import android.content.Context;
import android.widget.Toast;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.UserEntity;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepository;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepositoryImpl;
import com.overall.developer.overrendicion.ui.user.presenter.Login.LoginPresenter;
import com.overall.developer.overrendicion.utils.Util;


import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public class LoginInteractorImpl implements LoginInteractor
{
    private static final String TAG = "LoginInteractorImpl";
    private LoginPresenter mPresenter;
    private LoginRepository mRepository;
    private Context mContext;


    public LoginInteractorImpl(LoginPresenter loginPresenter, Context context)
    {
        this.mPresenter = loginPresenter;
        mRepository = new LoginRepositoryImpl(this);
        mContext = context;
    }


    @Override
    public boolean checkLogin()
    {
        return mRepository.checkLogin();
    }



    //region Login
    @Override
    public void loginAccess(String usuario, String password)
    {
        searchTypeConnect(usuario, password);

    }

    @Override
    public void loginRecovery(String dniUser)
    {
        if (Util.isOnline()) mRepository.loginRecovery(dniUser);
            else Toast.makeText(mContext,String.valueOf(mContext.getString(R.string.servidorError)), Toast.LENGTH_LONG).show();


    }

    @Override
    public void passwordRecoveryResponse(String message)
    {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

    }
    @Override
    public void passwordRecoveryError(String message)
    {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Observable validateSucces(String dniUser, String nombreUser, String emailUser)
    {
        return mPresenter.validateSucces(dniUser, nombreUser, emailUser);

    }

    @Override
    public Observable validateError(String errorMessaje) {
        return mPresenter.validateError(errorMessaje);
    }



    private void searchTypeConnect(String usuario, String password)
    {
        if (Util.isOnline())
        {
            mRepository.validateUserApi(new UserEntity(usuario,password));

        }else
        {
            if (mRepository.searchUserBD())  mRepository.validateUserDB(usuario, password);

            else mRepository.validateError(mContext.getResources().getString(R.string.servidorError) );


        }

    }
    //endregion
}
