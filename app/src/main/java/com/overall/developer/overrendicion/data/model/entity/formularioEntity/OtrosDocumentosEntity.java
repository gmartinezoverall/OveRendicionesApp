package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class OtrosDocumentosEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String fechaDocumento;
    private String numeroDoc;
    private String montoAfectado;
    private String montoNoAfectado;
    private String tipoGasto;
    private String observaciones;
    private String foto;

    public OtrosDocumentosEntity() {
    }

    public OtrosDocumentosEntity(String tipoDocumento, String fechaDocumento, String numeroDoc, String montoAfectado, String montoNoAfectado, String tipoGasto, String observaciones, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.fechaDocumento = fechaDocumento;
        this.numeroDoc = numeroDoc;
        this.montoAfectado = montoAfectado;
        this.montoNoAfectado = montoNoAfectado;
        this.tipoGasto = tipoGasto;
        this.observaciones = observaciones;
        this.foto = foto;
    }


    @Override
    public RendicionEntity getEntity(Object obj) {
        OtrosDocumentosEntity otrosDocumentosEntity = (OtrosDocumentosEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(otrosDocumentosEntity.codLiquidacion);
        entity.setRdoId(otrosDocumentosEntity.tipoDocumento);
        entity.setFechaDocumento(otrosDocumentosEntity.fechaDocumento);
        entity.setNumeroDoc(otrosDocumentosEntity.numeroDoc);
        entity.setPrecioTotal(otrosDocumentosEntity.montoAfectado);
        entity.setOtroGasto(otrosDocumentosEntity.montoNoAfectado);
        entity.setRtgId(otrosDocumentosEntity.tipoGasto);
        entity.setObservacion(otrosDocumentosEntity.observaciones);
        entity.setFoto(otrosDocumentosEntity.foto);

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

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getMontoAfectado() {
        return montoAfectado;
    }

    public void setMontoAfectado(String montoAfectado) {
        this.montoAfectado = montoAfectado;
    }

    public String getMontoNoAfectado() {
        return montoNoAfectado;
    }

    public void setMontoNoAfectado(String montoNoAfectado) {
        this.montoNoAfectado = montoNoAfectado;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
