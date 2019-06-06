package com.overall.developer.overrendicion2.data.repository.Drawable;

import com.overall.developer.overrendicion2.data.repository.Drawable.api.ApiUpdateEmail;
import com.overall.developer.overrendicion2.data.repository.Drawable.api.ApiUpdateEmailImpl;
import com.overall.developer.overrendicion2.data.repository.Drawable.db.DBUpdateEmail;
import com.overall.developer.overrendicion2.data.repository.Drawable.db.DBUpdateEmailImpl;
import com.overall.developer.overrendicion2.ui.user.interactor.Drawable.UpdateEmailInteractor;

public class UpdateEmailRepositoryImpl implements UpdateEmailRepository
{
    private UpdateEmailInteractor mInteractor;
    private ApiUpdateEmail mApiUpdateEmail;
    private DBUpdateEmail mDbUpdateEmail;

    public UpdateEmailRepositoryImpl(UpdateEmailInteractor interactor)
    {
        this.mInteractor = interactor;
        mApiUpdateEmail = new ApiUpdateEmailImpl(this);
        mDbUpdateEmail = new DBUpdateEmailImpl(this);

    }

    @Override
    public void sendDataApi(String dni, String newEmail)
    {
        mApiUpdateEmail.sendDataApi(dni, newEmail);
    }

    @Override
    public String getDniDB()
    {
        return mDbUpdateEmail.getDniDB();
    }

    @Override
    public void responseUpdateEmailSuccess(String message)
    {
        mInteractor.responseUpdateEmailSuccess(message);
    }

    @Override
    public void responseUpdateEmailError(String message)
    {
        mInteractor.responseUpdateEmailError(message);
    }
}
