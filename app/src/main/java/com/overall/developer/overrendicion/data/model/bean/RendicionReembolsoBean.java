package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RendicionReembolsoBean extends RealmObject {
    @PrimaryKey
    private Integer idRendicion;
    private String codRendicion;
    private String rdoId;
    private String rdoDes;
    private String codReembolso;
    private String idUsuario;
    private String numeroDoc;
    private String bienServicio;
    private String igv;
    private String afectoIgv;
    private String valorNeto;
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
    private String foto;
    private Boolean send;


    public Integer getIdRendicion() {
        return idRendicion;
    }

    public void setIdRendicion(Integer idRendicion) {
        this.idRendicion = idRendicion;
    }

    public String getCodRendicion() {
        return codRendicion;
    }

    public void setCodRendicion(String codRendicion) {
        this.codRendicion = codRendicion;
    }

    public String getRdoId() {
        return rdoId;
    }

    public void setRdoId(String rdoId) {
        this.rdoId = rdoId;
    }

    public String getRdoDes() {
        return rdoDes;
    }

    public void setRdoDes(String rdoDes) {
        this.rdoDes = rdoDes;
    }

    public String getCodReembolso() {
        return codReembolso;
    }

    public void setCodReembolso(String codReembolso) {
        this.codReembolso = codReembolso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getBienServicio() {
        return bienServicio;
    }

    public void setBienServicio(String bienServicio) {
        this.bienServicio = bienServicio;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getAfectoIgv() {
        return afectoIgv;
    }

    public void setAfectoIgv(String afectoIgv) {
        this.afectoIgv = afectoIgv;
    }

    public String getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(String valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getBcoCod() {
        return bcoCod;
    }

    public void setBcoCod(String bcoCod) {
        this.bcoCod = bcoCod;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getRtgId() {
        return rtgId;
    }

    public void setRtgId(String rtgId) {
        this.rtgId = rtgId;
    }

    public String getOtroGasto() {
        return otroGasto;
    }

    public void setOtroGasto(String otroGasto) {
        this.otroGasto = otroGasto;
    }

    public String getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(String codDestino) {
        this.codDestino = codDestino;
    }

    public String getAfectoRetencion() {
        return afectoRetencion;
    }

    public void setAfectoRetencion(String afectoRetencion) {
        this.afectoRetencion = afectoRetencion;
    }

    public String getCodSuspencionH() {
        return codSuspencionH;
    }

    public void setCodSuspencionH(String codSuspencionH) {
        this.codSuspencionH = codSuspencionH;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }
}
