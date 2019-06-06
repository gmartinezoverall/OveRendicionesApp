package com.overall.developer.overrendicion2.ui.user.interactor.Drawable;

public interface UpdateEmailInteractor
{
    void sendData(String newEmail);

    void responseUpdateEmailSuccess(String message);

    void responseUpdateEmailError(String message);
}
