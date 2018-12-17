package com.overall.developer.overrendicion.data.repository.user.Login;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public interface LoginRepository
{
    //region Sets
    boolean checkLogin();
    void validateUserApi(UserEntity userEntity);
    Observable validateUserDB(String user, String password);
    Boolean searchUserBD();
    void registerUserDB(UserBean userBean);
    void loginRecovery(String dniUser);
    void passwordRecoveryResponse(String message);
    void passwordRecoveryError(String message);
    //endregion

    //region Gets
    Observable validateSucces(String dniUser, String nombreUser, String emailUser);
    Observable validateError(String errorMessaje);


    //endregion
}
