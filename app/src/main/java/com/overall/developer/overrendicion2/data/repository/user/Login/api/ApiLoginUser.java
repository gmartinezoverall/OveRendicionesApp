package com.overall.developer.overrendicion2.data.repository.user.Login.api;

import com.overall.developer.overrendicion2.data.model.entity.UserEntity;

public interface ApiLoginUser
{
    void validateUserApi(UserEntity userEntity);
    void loginRecovery(String dniUser);
}
