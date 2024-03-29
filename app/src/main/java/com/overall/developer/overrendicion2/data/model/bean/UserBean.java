package com.overall.developer.overrendicion2.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserBean extends RealmObject
{
    @PrimaryKey
    private String numDocBeneficiario;
    private String password;
    private String firstLogin;
    private String nombre;
    private String idUsuario;
    private String estadoUsuario;
    private String fechaNac;
    private String email;
    private String telefono;
    private String codCia;
    private String compania;
    private boolean status = false;

    public UserBean()
    {
    }

    public UserBean(String numDocBeneficiario, String password, String firstLogin, String nombre, String idUsuario, String estadoUsuario, String fechaNac, String email, boolean status) {
        this.numDocBeneficiario = numDocBeneficiario;
        this.password = password;
        this.firstLogin = firstLogin;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.estadoUsuario = estadoUsuario;
        this.fechaNac = fechaNac;
        this.email = email;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNumDocBeneficiario() {
        return numDocBeneficiario;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstLogin() {
        return firstLogin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodCia() {
        return codCia;
    }

    public void setCodCia(String codCia) {
        this.codCia = codCia;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public boolean isStatus() {
        return status;
    }
}

