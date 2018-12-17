package com.overall.developer.overrendicion.data.repository.user.CreateAccount.api;

import io.reactivex.Observable;

public interface ApiCreateAccount
{
    Observable createAccountApi(String dni, String password, String email, String telefono);
}
