package com.overall.developer.overrendicion.ui.user.presenter.Drawable;

public interface UpdateEmailPresenter
{
    void sendData(String newEmail);

    void responseUpdateEmailSuccess(String message);

    void responseUpdateEmailError(String message);
}
