package com.overall.developer.overrendicion2.ui.user.presenter.Drawable;

import com.overall.developer.overrendicion2.ui.user.interactor.Drawable.RecoveryPasswordInteractor;
import com.overall.developer.overrendicion2.ui.user.interactor.Drawable.RecoveryPasswordInteractorImpl;
import com.overall.developer.overrendicion2.ui.user.view.Drawable.RecoveryPasswordView;

public class RecoveryPasswordPresenterImpl implements RecoveryPasswordPresenter
{
    private RecoveryPasswordView mView;
    private RecoveryPasswordInteractor mInteractor;

    public RecoveryPasswordPresenterImpl(RecoveryPasswordView view)
    {
        this.mView = view;
        mInteractor = new RecoveryPasswordInteractorImpl(this);

    }

    @Override
    public void sendData(String newPassword)
    {
        mInteractor.sendData(newPassword);

    }

    @Override
    public void responseRoveryPasswordSuccess(String message)
    {
        mView.responseRoveryPasswordSuccess(message);

    }

    @Override
    public void responseRoveryPasswordError(String message)
    {
        mView.responseRoveryPasswordError(message);
    }
}
