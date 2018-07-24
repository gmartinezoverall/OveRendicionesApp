package com.overall.developer.overrendicion.ui.user.presenter.Drawable;

public interface RecoveryPasswordPresenter
{

    void sendData(String newPassword);

    void responseRoveryPasswordSuccess(String message);

    void responseRoveryPasswordError(String message);
}
