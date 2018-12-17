package com.overall.developer.overrendicion.data.repository.user.Login;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.UserEntity;
import com.overall.developer.overrendicion.data.repository.user.Login.api.ApiLoginUser;
import com.overall.developer.overrendicion.data.repository.user.Login.api.ApiLoginUserImpl;
import com.overall.developer.overrendicion.data.repository.user.Login.db.DBLoginUser;
import com.overall.developer.overrendicion.data.repository.user.Login.db.DBLoginUserImpl;
import com.overall.developer.overrendicion.ui.user.interactor.Login.LoginInteractor;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public class LoginRepositoryImpl implements LoginRepository
{
    private LoginInteractor mInteractor;
    private ApiLoginUser mApiLoginUser;
    private DBLoginUser mDBLoginUser;

    public LoginRepositoryImpl(LoginInteractor loginInteractor)
    {
        this.mInteractor= loginInteractor;
        mApiLoginUser = new ApiLoginUserImpl( this);
        mDBLoginUser = new DBLoginUserImpl(this);
    }

    @Override
    public boolean checkLogin() {
        return mDBLoginUser.checkLogin();
    }

    @Override
    public void validateUserApi(UserEntity userEntity)
    {
        mApiLoginUser.validateUserApi(userEntity);

    }

    @Override
    public Observable validateUserDB(String user, String password)
    {
       return mDBLoginUser.validateUserDB(user, password);
    }

    @Override
    public Boolean searchUserBD() {
        return mDBLoginUser.searchUserBD();
    }

    @Override
    public Observable validateSucces(String dniUser,String nombreUser, String emailUser)
    {
        return mInteractor.validateSucces(dniUser, nombreUser, emailUser);

    }

    @Override
    public Observable validateError(String errorMessaje)
    {
        return mInteractor.validateError(errorMessaje);
    }

    @Override
    public void registerUserDB(UserBean userBean)
    {
        mDBLoginUser.registerUserDB(userBean);

    }

    @Override
    public void loginRecovery(String dniUser)
    {
        mApiLoginUser.loginRecovery(dniUser);
    }

    @Override
    public void passwordRecoveryResponse(String message)
    {
        mInteractor.passwordRecoveryResponse(message);
    }

    @Override
    public void passwordRecoveryError(String message)
    {
        mInteractor.passwordRecoveryError(message);
    }

}
