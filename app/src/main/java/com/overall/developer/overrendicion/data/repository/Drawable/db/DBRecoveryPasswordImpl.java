package com.overall.developer.overrendicion.data.repository.Drawable.db;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Drawable.RecoveryPasswordRepository;

import io.realm.Realm;

public class DBRecoveryPasswordImpl implements DBRecoveryPassword
{
    private RecoveryPasswordRepository mRepository;

    public DBRecoveryPasswordImpl(RecoveryPasswordRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public String getDniDB()
    {
        Realm mRealm = Realm.getDefaultInstance();
        UserBean userBeans = mRealm.where(UserBean.class).equalTo("status", true).findFirst();
        return userBeans.getNumDocBeneficiario();
    }
}
