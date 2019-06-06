package com.overall.developer.overrendicion2.ui.user.interactor.CreateAccount;

import com.overall.developer.overrendicion2.data.repository.user.CreateAccount.CreateAccountRepository;
import com.overall.developer.overrendicion2.data.repository.user.CreateAccount.CreateAccountRepositoryImpl;
import com.overall.developer.overrendicion2.ui.user.presenter.CreateAccount.CreateAccountPresenter;

/**
 * Created by terry on 3/9/2018.
 */

public class CreateAccountInteractorImpl implements CreateAccountInteractor
{
    private CreateAccountPresenter mPresenter;
    private CreateAccountRepository mRepository;


    public CreateAccountInteractorImpl(CreateAccountPresenter createAccountPresenter)
    {
        this.mPresenter = createAccountPresenter;
        mRepository = new CreateAccountRepositoryImpl(this);

    }

    //region Interfaces
    @Override
    public void createAccountApi(String dni, String password, String email, String telefono)
    {
        mRepository.createAccountApi(dni, password, email, telefono);
    }

    @Override
    public void createAccountSuccess(String message)
    {
        mPresenter.createAccountSuccess(message);
    }

    @Override
    public void createAccountError(String message)
    {
        mPresenter.createAccountError(message);
    }


    //endregion
}
