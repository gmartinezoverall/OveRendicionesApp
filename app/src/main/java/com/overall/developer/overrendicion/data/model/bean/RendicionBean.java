package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RendicionBean extends RealmObject {
    @PrimaryKey
    private Integer idRendicion;
    private String codRendicion;
    private String rdoId;
    private String rdoDescipcion;
    private String codLiquidacion;
    private String idUsuario;
    private String numeroDoc;
    private String bienServicio;
    private String igv;
    private String afectoIgv;
    private String precioTotal;
    private String observacion;
    private String fechaDocumento;
    private String fechaVencimiento;
    private String ruc;
    private String razonSocial;
    private String bcoCod;
    private String tipoServicio;
    private String rtgId;
    private String otroGasto;
    private String codDestino;
    private String afectoRetencion;
    private String codSuspencionH;
    private String tipoMoneda;
    private String tipoCambio;


    public RendicionBean() {
    }

    public RendicionBean(Integer idRendicion, String codRendicion, String rdoId, String rdoDescipcion, String codLiquidacion, String idUsuario, String numeroDoc, String bienServicio, String igv, String afectoIgv, String precioTotal, String observacion, String fechaDocumento, String fechaVencimiento, String ruc, String razonSocial, String bcoCod, String tipoServicio, String rtgId, String otroGasto, String codDestino, String afectoRetencion, String codSuspencionH, String tipoMoneda, String tipoCambio) {
        this.idRendicion = idRendicion;
        this.codRendicion = codRendicion;
        this.rdoId = rdoId;
        this.rdoDescipcion = rdoDescipcion;
        this.codLiquidacion = codLiquidacion;
        this.idUsuario = idUsuario;
        this.numeroDoc = numeroDoc;
        this.bienServicio = bienServicio;
        this.igv = igv;
        this.afectoIgv = afectoIgv;
        this.precioTotal = precioTotal;
        this.observacion = observacion;
        this.fechaDocumento = fechaDocumento;
        this.fechaVencimiento = fechaVencimiento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.bcoCod = bcoCod;
        this.tipoServicio = tipoServicio;
        this.rtgId = rtgId;
        this.otroGasto = otroGasto;
        this.codDestino = codDestino;
        this.afectoRetencion = afectoRetencion;
        this.codSuspencionH = codSuspencionH;
        this.tipoMoneda = tipoMoneda;
        this.tipoCambio = tipoCambio;
    }

    public void setIdRendicion(Integer idRendicion) {
        this.idRendicion = idRendicion;
    }

    public Integer getIdRendicion() {
        return idRendicion;
    }

    public String getCodRendicion() {
        return codRendicion;
    }

    public String getRdoId() {
        return rdoId;
    }

    public String getRdoDescipcion() {
        return rdoDescipcion;
    }

    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public String getBienServicio() {
        return bienServicio;
    }

    public String getIgv() {
        return igv;
    }

    public String getAfectoIgv() {
        return afectoIgv;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getBcoCod() {
        return bcoCod;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getRtgId() {
        return rtgId;
    }

    public String getOtroGasto() {
        return otroGasto;
    }

    public String getCodDestino() {
        return codDestino;
    }

    public String getAfectoRetencion() {
        return afectoRetencion;
    }

    public String getCodSuspencionH() {
        return codSuspencionH;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }
}