package com.overall.developer.overrendicion2.data.repository.Drawable;

public interface RecoveryPasswordRepository
{
    void sendData(String dni, String newPassword);

    void responseRoveryPasswordSuccess(String message);

    void responseRoveryPasswordError(String message);

    String getDniDB();
}
