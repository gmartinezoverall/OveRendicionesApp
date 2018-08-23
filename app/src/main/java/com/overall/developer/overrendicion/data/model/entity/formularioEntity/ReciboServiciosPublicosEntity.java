package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class ReciboServiciosPublicosEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String numeroDoc;
    private String fechaDocumento;
    private String tipoGasto;
    private String igv;
    private String afectoIgv;
    private String montoNoAfectado;
    private String monto;
    private String foto;

    public ReciboServiciosPublicosEntity() {
    }

    public ReciboServiciosPublicosEntity(String tipoDocumento, String ruc, String razonSocial, String numeroDoc, String fechaDocumento, String tipoGasto, String igv, String afectoIgv, String montoNoAfectado, String monto, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.numeroDoc = numeroDoc;
        this.fechaDocumento = fechaDocumento;
        this.tipoGasto = tipoGasto;
        this.igv = igv;
        this.afectoIgv = afectoIgv;
        this.montoNoAfectado = montoNoAfectado;
        this.monto = monto;
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj) {
        ReciboServiciosPublicosEntity reciboServiciosPublicosEntity = (ReciboServiciosPublicosEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(reciboServiciosPublicosEntity.codLiquidacion);
        entity.setRdoId(reciboServiciosPublicosEntity.tipoDocumento);
        entity.setNumeroDoc(reciboServiciosPublicosEntity.numeroDoc);
        entity.setFechaDocumento(reciboServiciosPublicosEntity.fechaDocumento);
        entity.setIgv(reciboServiciosPublicosEntity.igv);
        entity.setAfectoIgv(reciboServiciosPublicosEntity.afectoIgv);
        entity.setPrecioTotal(reciboServiciosPublicosEntity.monto);
        entity.setOtroGasto(reciboServiciosPublicosEntity.montoNoAfectado);
        entity.setRtgId(reciboServiciosPublicosEntity.tipoGasto);
        entity.setFoto(reciboServiciosPublicosEntity.foto);

        return entity;
    }


    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public void setCodLiquidacion(String codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
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

    public String getMontoNoAfectado() {
        return montoNoAfectado;
    }

    public void setMontoNoAfectado(String montoNoAfectado) {
        this.montoNoAfectado = montoNoAfectado;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
