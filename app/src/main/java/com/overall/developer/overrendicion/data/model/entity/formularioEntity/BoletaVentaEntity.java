package com.overall.developer.overrendicion.data.model.entity.formularioEntity;


import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class BoletaVentaEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String numeroDocumento;
    private String fechaDocumento;
    private String tipoMoneda;
    private String valorVenta;
    private String igv;
    private String afectoIgv;
    private String otrosGatos;
    private String precioVenta;
    private String tipoGasto;
    private String observaciones;


    public BoletaVentaEntity() {
    }

    public BoletaVentaEntity(String tipoDocumento, String ruc, String razonSocial, String numeroDocumento, String fechaDocumento, String tipoMoneda, String igv, String afectoIgv, String otrosGatos, String precioVenta, String tipoGasto, String observaciones) {
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.numeroDocumento = numeroDocumento;
        this.fechaDocumento = fechaDocumento;
        this.tipoMoneda = tipoMoneda;
        this.igv = igv;
        this.afectoIgv = afectoIgv;
        this.otrosGatos = otrosGatos;
        this.precioVenta = precioVenta;
        this.tipoGasto = tipoGasto;
        this.observaciones = observaciones;
    }


    @Override
    public RendicionEntity getEntity(Object obj) {
        BoletaVentaEntity boletaVentaEntity = (BoletaVentaEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(boletaVentaEntity.codLiquidacion);
        entity.setRdoId(boletaVentaEntity.tipoDocumento);
        entity.setRuc(boletaVentaEntity.ruc);
        entity.setRazonSocial(boletaVentaEntity.razonSocial);
        entity.setNumeroDoc(boletaVentaEntity.numeroDocumento);
        entity.setFechaDocumento(boletaVentaEntity.fechaDocumento);
        entity.setTipoMoneda(boletaVentaEntity.tipoMoneda);
        entity.setIgv(boletaVentaEntity.igv);
        entity.setAfectoIgv(boletaVentaEntity.afectoIgv);
        entity.setOtroGasto(boletaVentaEntity.otrosGatos);
        entity.setPrecioTotal(boletaVentaEntity.precioVenta);
        entity.setRtgId(boletaVentaEntity.tipoGasto);
        entity.setObservacion(boletaVentaEntity.observaciones);

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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
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

    public String getOtrosGatos() {
        return otrosGatos;
    }

    public void setOtrosGatos(String otrosGatos) {
        this.otrosGatos = otrosGatos;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
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

}
