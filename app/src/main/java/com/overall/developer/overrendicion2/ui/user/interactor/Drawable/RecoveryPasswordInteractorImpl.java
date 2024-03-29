package com.overall.developer.overrendicion2.ui.user.interactor.Drawable;

import com.overall.developer.overrendicion2.data.repository.Drawable.RecoveryPasswordRepository;
import com.overall.developer.overrendicion2.data.repository.Drawable.RecoveryPasswordRepositoryImpl;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.RecoveryPasswordPresenter;

public class RecoveryPasswordInteractorImpl implements RecoveryPasswordInteractor
{
    private RecoveryPasswordPresenter mPresenter;
    private RecoveryPasswordRepository mRepository;

    public RecoveryPasswordInteractorImpl(RecoveryPasswordPresenter presenter)
    {
        this.mPresenter = presenter;
        mRepository = new RecoveryPasswordRepositoryImpl(this);

    }

    @Override
    public void sendData(String newPassword)
    {
        String dni = mRepository.getDniDB();
        mRepository.sendData(dni, newPassword);
    }

    @Override
    public void responseRoveryPasswordSuccess(String message)
    {
        mPresenter.responseRoveryPasswordSuccess(message);

    }

    @Override
    public void responseRoveryPasswordError(String message)
    {
        mPresenter.responseRoveryPasswordError(message);
    }
}
