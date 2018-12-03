package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReembolsoBean extends RealmObject
{
    @PrimaryKey
    String codReemboslo;
    String codComp;
    String numBenDni;
    String nombreBen;
    String monto;
    String tipoMoneda;
    String nombreConsultora;
    String codTReembolso;
    String descTReembolso;
    String motivoReembolso;
    String fechaReembolso;
    String fechaPago;
    String fechaDesde;
    String fechaHasta;
    String estado;

    public ReembolsoBean() {
    }

    public ReembolsoBean(String codReemboslo, String codComp, String numBenDni, String nombreBen, String monto, String tipoMoneda, String nombreConsultora, String codTReembolso, String descTReembolso, String motivoReembolso, String fechaReembolso, String fechaPago, String fechaDesde, String fechaHasta, String estado) {
        this.codReemboslo = codReemboslo;
        this.codComp = codComp;
        this.numBenDni = numBenDni;
        this.nombreBen = nombreBen;
        this.monto = monto;
        this.tipoMoneda = tipoMoneda;
        this.nombreConsultora = nombreConsultora;
        this.codTReembolso = codTReembolso;
        this.descTReembolso = descTReembolso;
        this.motivoReembolso = motivoReembolso;
        this.fechaReembolso = fechaReembolso;
        this.fechaPago = fechaPago;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
    }

    public String getCodReemboslo() {
        return codReemboslo;
    }

    public void setCodReemboslo(String codReemboslo) {
        this.codReemboslo = codReemboslo;
    }

    public String getCodComp() {
        return codComp;
    }

    public void setCodComp(String codComp) {
        this.codComp = codComp;
    }

    public String getNumBenDni() {
        return numBenDni;
    }

    public void setNumBenDni(String numBenDni) {
        this.numBenDni = numBenDni;
    }

    public String getNombreBen() {
        return nombreBen;
    }

    public void setNombreBen(String nombreBen) {
        this.nombreBen = nombreBen;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getNombreConsultora() {
        return nombreConsultora;
    }

    public void setNombreConsultora(String nombreConsultora) {
        this.nombreConsultora = nombreConsultora;
    }

    public String getCodTReembolso() {
        return codTReembolso;
    }

    public void setCodTReembolso(String codTReembolso) {
        this.codTReembolso = codTReembolso;
    }

    public String getDescTReembolso() {
        return descTReembolso;
    }

    public void setDescTReembolso(String descTReembolso) {
        this.descTReembolso = descTReembolso;
    }

    public String getMotivoReembolso() {
        return motivoReembolso;
    }

    public void setMotivoReembolso(String motivoReembolso) {
        this.motivoReembolso = motivoReembolso;
    }

    public String getFechaReembolso() {
        return fechaReembolso;
    }

    public void setFechaReembolso(String fechaReembolso) {
        this.fechaReembolso = fechaReembolso;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
