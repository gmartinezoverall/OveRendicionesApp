package com.overall.developer.overrendicion2.ui.user.interactor.Drawable;

import com.overall.developer.overrendicion2.data.repository.Drawable.UpdateEmailRepository;
import com.overall.developer.overrendicion2.data.repository.Drawable.UpdateEmailRepositoryImpl;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.UpdateEmailPresenter;

public class UpdateEmailInteractorImpl implements UpdateEmailInteractor
{
    private UpdateEmailPresenter mPresenter;
    private UpdateEmailRepository mRepository;

    public UpdateEmailInteractorImpl(UpdateEmailPresenter presenter)
    {
        this.mPresenter = presenter;
        mRepository = new UpdateEmailRepositoryImpl(this);

    }

    @Override
    public void sendData(String newEmail)
    {
        String dni = mRepository.getDniDB();
        mRepository.sendDataApi(dni, newEmail);
    }

    @Override
    public void responseUpdateEmailSuccess(String message)
    {
        mPresenter.responseUpdateEmailSuccess(message);
    }

    @Override
    public void responseUpdateEmailError(String message)
    {
        mPresenter.responseUpdateEmailError(message);
    }
}
