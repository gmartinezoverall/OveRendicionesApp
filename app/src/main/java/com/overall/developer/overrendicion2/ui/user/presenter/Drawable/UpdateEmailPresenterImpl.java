package com.overall.developer.overrendicion2.ui.user.presenter.Drawable;

import com.overall.developer.overrendicion2.ui.user.interactor.Drawable.UpdateEmailInteractor;
import com.overall.developer.overrendicion2.ui.user.interactor.Drawable.UpdateEmailInteractorImpl;
import com.overall.developer.overrendicion2.ui.user.view.Drawable.UpdateEmailView;

public class UpdateEmailPresenterImpl implements UpdateEmailPresenter
{
    private UpdateEmailView mView;
    private UpdateEmailInteractor mInteractor;

    public UpdateEmailPresenterImpl(UpdateEmailView view)
    {
        this.mView = view;
        mInteractor = new UpdateEmailInteractorImpl(this);

    }

    @Override
    public void sendData(String newEmail)
    {
        mInteractor.sendData(newEmail);

    }

    @Override
    public void responseUpdateEmailSuccess(String message)
    {
        mView.responseUpdateEmailSuccess(message);
    }

    @Override
    public void responseUpdateEmailError(String message)
    {
        mView.responseUpdateEmailError(message);
    }
}
