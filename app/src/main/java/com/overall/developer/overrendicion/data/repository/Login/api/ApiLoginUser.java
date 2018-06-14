package com.overall.developer.overrendicion.data.repository.Login.api;

import com.overall.developer.overrendicion.data.model.entity.UserEntity;

public interface ApiLoginUser
{
    void validateUserApi(UserEntity userEntity);
    void loginRecovery(String dniUser);
}
