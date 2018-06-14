package com.overall.developer.overrendicion.ui.user.interactor.CreateAccount;

/**
 * Created by terry on 3/9/2018.
 */

public interface CreateAccountInteractor
{
    //region Gets
    void createAccountApi(String dni, String password, String email, String telefono);
    //endregion


    //region Sets
    void createAccountSuccess(String message);
    void createAccountError(String message);

    //endregion

}
