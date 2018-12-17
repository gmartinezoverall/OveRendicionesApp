package com.overall.developer.overrendicion.data.repository.user.Login.db;

import com.overall.developer.overrendicion.data.model.bean.UserBean;

import io.reactivex.Observable;

public interface DBLoginUser
{
    boolean checkLogin();
    void registerUserDB(UserBean userBean);
    Observable validateUserDB(String user, String password);
    Boolean searchUserBD();
}
