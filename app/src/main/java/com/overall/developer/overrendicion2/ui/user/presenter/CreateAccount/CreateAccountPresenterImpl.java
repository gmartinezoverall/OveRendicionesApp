package com.overall.developer.overrendicion2.ui.user.presenter.CreateAccount;

import com.overall.developer.overrendicion2.ui.user.interactor.CreateAccount.CreateAccountInteractor;
import com.overall.developer.overrendicion2.ui.user.interactor.CreateAccount.CreateAccountInteractorImpl;
import com.overall.developer.overrendicion2.ui.user.view.CreateAccount.CreateAccountView;

/**
 * Created by terry on 3/9/2018.
 */

public class CreateAccountPresenterImpl implements CreateAccountPresenter
{
    private CreateAccountView mView;
    private CreateAccountInteractor mInteractor;

    public CreateAccountPresenterImpl(CreateAccountView createAccountView)
    {
        this.mView = createAccountView;
        mInteractor = new CreateAccountInteractorImpl(this);
    }

    //region Interfaces
    @Override
    public void createAccountApi(String dni, String password, String email, String telefono)
    {
        mInteractor.createAccountApi(dni, password, email, telefono);

    }

    @Override
    public void createAccountSuccess(String message)
    {
        mView.createAccountSuccess(message);
    }

    @Override
    public void createAccountError(String message)
    {
        mView.createAccountError(message);
    }

    //endregion
}
