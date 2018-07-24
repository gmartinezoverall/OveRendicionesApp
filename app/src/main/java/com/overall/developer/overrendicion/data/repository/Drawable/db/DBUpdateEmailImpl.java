package com.overall.developer.overrendicion.data.repository.Drawable.db;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Drawable.UpdateEmailRepository;

import io.realm.Realm;

public class DBUpdateEmailImpl implements DBUpdateEmail
{
    private UpdateEmailRepository mRepository;

    public DBUpdateEmailImpl(UpdateEmailRepository repository)
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
