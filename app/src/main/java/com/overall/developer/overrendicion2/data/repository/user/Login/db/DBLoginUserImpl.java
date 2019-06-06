package com.overall.developer.overrendicion2.data.repository.user.Login.db;

import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.RendicionApplication;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.repository.user.Login.LoginRepository;

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
        RealmResults<UserBean> list= searchUserRealm();
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
        mRealm.executeTransaction(realm ->
        {
            userBean.setStatus(true);
            mRealm.insertOrUpdate(userBean);

        });

    }

    private UserBean validateUserRealm(String user, String password)
    {
        Realm mRealm = Realm.getDefaultInstance();
        final UserBean userBean = mRealm.where(UserBean.class).
                equalTo("numDocBeneficiario",user).
                and().
                equalTo("password", password).findFirst();



        mRealm.executeTransaction(realm ->
        {
            userBean.setStatus(true);
            mRealm.insertOrUpdate(userBean);

        });

        return userBean;

    }

    private RealmResults<UserBean> searchUserRealm()
    {
        Realm mRealm = Realm.getDefaultInstance();
        final RealmResults<UserBean> userBeans = mRealm.where(UserBean.class).findAll();

        return userBeans;
    }
    //endregion
}
