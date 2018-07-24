package com.overall.developer.overrendicion.data.repository.Drawable;

import com.overall.developer.overrendicion.data.repository.Drawable.api.ApiRecoveryPassword;
import com.overall.developer.overrendicion.data.repository.Drawable.api.ApiRecoveryPasswordImpl;
import com.overall.developer.overrendicion.data.repository.Drawable.db.DBRecoveryPassword;
import com.overall.developer.overrendicion.data.repository.Drawable.db.DBRecoveryPasswordImpl;
import com.overall.developer.overrendicion.ui.user.interactor.Drawable.RecoveryPasswordInteractor;

public class RecoveryPasswordRepositoryImpl implements RecoveryPasswordRepository
{
    private RecoveryPasswordInteractor mInteractor;
    private ApiRecoveryPassword mApiRecoveryPassword;
    private DBRecoveryPassword mDbRecoveryPassword;

    public RecoveryPasswordRepositoryImpl(RecoveryPasswordInteractor interactor)
    {
        this.mInteractor = interactor;
        mApiRecoveryPassword = new ApiRecoveryPasswordImpl(this);
        mDbRecoveryPassword = new DBRecoveryPasswordImpl(this);
    }

    @Override
    public void sendData(String dni, String newPassword)
    {
        mApiRecoveryPassword.sendData(dni ,newPassword);

    }

    @Override
    public void responseRoveryPasswordSuccess(String message)
    {
        mInteractor.responseRoveryPasswordSuccess(message);

    }

    @Override
    public void responseRoveryPasswordError(String message)
    {
        mInteractor.responseRoveryPasswordError(message);
    }

    @Override
    public String getDniDB() {
        return mDbRecoveryPassword.getDniDB();
    }
}
