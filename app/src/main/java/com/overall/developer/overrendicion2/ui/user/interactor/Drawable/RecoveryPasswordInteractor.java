package com.overall.developer.overrendicion2.ui.user.interactor.Drawable;

public interface RecoveryPasswordInteractor
{
    void sendData(String newPassword);

    void responseRoveryPasswordSuccess(String message);

    void responseRoveryPasswordError(String message);
}
