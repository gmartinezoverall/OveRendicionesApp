package com.overall.developer.overrendicion.data.repository.user.CreateAccount;


import com.overall.developer.overrendicion.data.repository.user.CreateAccount.api.ApiCreateAccount;
import com.overall.developer.overrendicion.data.repository.user.CreateAccount.api.ApiCreateAccountImpl;
import com.overall.developer.overrendicion.ui.user.interactor.CreateAccount.CreateAccountInteractor;

/**
 * Created by terry on 3/9/2018.
 */

public class CreateAccountRepositoryImpl implements CreateAccountRepository
{
    private CreateAccountInteractor mInteractor;
    private ApiCreateAccount apiCreateAccount;

    public CreateAccountRepositoryImpl(CreateAccountInteractor createAccountInteractor)
    {
        this.mInteractor = createAccountInteractor;
        apiCreateAccount = new ApiCreateAccountImpl(this);

    }

    //region Interfaces
    @Override
    public void createAccountApi(String dni, String password, String email, String telefono)
    {
        apiCreateAccount.createAccountApi(dni, password, email, telefono);

    }

    @Override
    public void createAccountSuccess(String message)
    {
        mInteractor.createAccountSuccess(message);

    }

    @Override
    public void createAccountError(String message)
    {
        mInteractor.createAccountError(message);
    }
    //endregion
}
