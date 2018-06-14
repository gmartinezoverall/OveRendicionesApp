package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TipoDocumentoBean extends RealmObject
{
    @PrimaryKey
    Integer id;
    String rtgId;
    String rtgDes;
    String rtgCuentaE;
    String rtgCuentaI;
    String flagCeco;
    String rtgBienServicio;
    String codProducto;
    String codCategoriaFinal;
    String rdoId;

    public TipoDocumentoBean() {
    }

    public TipoDocumentoBean(Integer id, String rtgId, String rtgDes, String rtgCuentaE, String rtgCuentaI, String flagCeco, String rtgBienServicio, String codProducto, String codCategoriaFinal, String rdoId) {
        this.id = id;
        this.rtgId = rtgId;
        this.rtgDes = rtgDes;
        this.rtgCuentaE = rtgCuentaE;
        this.rtgCuentaI = rtgCuentaI;
        this.flagCeco = flagCeco;
        this.rtgBienServicio = rtgBienServicio;
        this.codProducto = codProducto;
        this.codCategoriaFinal = codCategoriaFinal;
        this.rdoId = rdoId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getRtgId() {
        return rtgId;
    }

    public String getRtgDes() {
        return rtgDes;
    }

    public String getRtgCuentaE() {
        return rtgCuentaE;
    }

    public String getRtgCuentaI() {
        return rtgCuentaI;
    }

    public String getFlagCeco() {
        return flagCeco;
    }

    public String getRtgBienServicio() {
        return rtgBienServicio;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public String getCodCategoriaFinal() {
        return codCategoriaFinal;
    }

    public String getRdoId() {
        return rdoId;
    }
}