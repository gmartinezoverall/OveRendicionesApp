package com.overall.developer.overrendicion.data.model.entity;

public class UserEntity
{
    String dni;
    String password;

    public UserEntity()
    {

    }

    public UserEntity(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
