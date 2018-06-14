package com.overall.developer.overrendicion.data.repository.Login.db;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepository;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class DBLoginUserImpl implements DBLoginUser
{
    private LoginRepository mRepository;


    public DBLoginUserImpl(LoginRepository loginRepository)
    {
        this.mRepository = loginRepository;

    }

    @Override
    public Observable validateUserDB(String user, String password)
    {
        UserBean userBean = validateUserRealm(user, password);
        if (userBean.getNumDocBeneficiario()  != null) return mRepository.validateSucces(userBean.getNumDocBeneficiario(), userBean.getNombre(), userBean.getEmail());
        else  return mRepository.validateError(RendicionApplication.getContext().getResources().getString(R.string.sessionError));

         //list.size() > 0 ? true : false ;
    }

    @Override
    public Boolean searchUserBD() {
        List<UserBean> list= searchUserRealm();
        return list.size() > 0 ? true : false ;
    }


    @Override
    public boolean checkLogin()
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBean = mRealm.where(UserBean.class).equalTo("status",true).findFirst();

        if (String.valueOf(userBean) != "null")
        {
            return userBean.isStatus();
        }

        return false;
    }

    //region Funciones
    @Override
    public void registerUserDB(UserBean userBean)
    {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<UserBean> initId = mRealm.where(UserBean.class).findAll();
        int nextID = initId.size() == 0 ? 1 : initId.size()+1;

        userBean.setmId(nextID);
        userBean.setStatus(true);

        mRealm.executeTransaction(realm -> mRealm.insertOrUpdate(userBean));

    }

    private UserBean validateUserRealm(String user, String password)
    {
        Realm mRealm = Realm.getDefaultInstance();
        final UserBean userBeans = mRealm.where(UserBean.class).
                equalTo("mUser",user).
                and().
                equalTo("mPassword", password).findFirst();

        return userBeans;

    }

    private RealmResults<UserBean> searchUserRealm()
    {
        Realm mRealm = Realm.getDefaultInstance();
        final RealmResults<UserBean> userBeans = mRealm.where(UserBean.class).findAll();

        return userBeans;
    }
    //endregion
}
