package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class OtrosBean extends RealmObject
{
    @PrimaryKey
    private Integer id;
    private String tipoCambio;
    private String sueldo;

    public OtrosBean() {
    }

    public OtrosBean(Integer id, String tipoCambio) {
        this.id = id;
        this.tipoCambio = tipoCambio;
    }

    public Integer getId() {
        return id;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
