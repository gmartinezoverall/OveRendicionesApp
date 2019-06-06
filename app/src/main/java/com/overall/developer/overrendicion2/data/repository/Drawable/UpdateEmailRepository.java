package com.overall.developer.overrendicion2.data.repository.Drawable;

public interface UpdateEmailRepository
{
    void sendDataApi(String dni,String newEmail);

    String getDniDB();

    void responseUpdateEmailSuccess(String message);

    void responseUpdateEmailError(String message);
}
