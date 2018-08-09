package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class CartaPorteAereoEntity implements FormularioEntity
{

    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String numeroDocumento;
    private String fechaDocumento;
    private String tipoMoneda;
    private String igv;
    private String afectoIgv;
    private String otrosGatos;
    private String precioVenta;
    private String tipoGasto;
    private String observaciones;
    private String foto;

    public CartaPorteAereoEntity() {
    }

    public CartaPorteAereoEntity(String tipoDocumento, String ruc, String razonSocial, String numeroDocumento, String fechaDocumento, String tipoMoneda,String igv, String afectoIgv, String otrosGatos, String precioVenta, String tipoGasto, String observaciones, String foto) {
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
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj) {
        CartaPorteAereoEntity cartaPorteAereoEntity = (CartaPorteAereoEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(cartaPorteAereoEntity.codLiquidacion);
        entity.setRdoId(cartaPorteAereoEntity.tipoDocumento);
        entity.setRuc(cartaPorteAereoEntity.ruc);
        entity.setRazonSocial(cartaPorteAereoEntity.razonSocial);
        entity.setNumeroDoc(cartaPorteAereoEntity.numeroDocumento);
        entity.setFechaDocumento(cartaPorteAereoEntity.fechaDocumento);
        entity.setTipoMoneda(cartaPorteAereoEntity.tipoMoneda);
        entity.setIgv(cartaPorteAereoEntity.igv);
        entity.setAfectoIgv(cartaPorteAereoEntity.afectoIgv);
        entity.setOtroGasto(cartaPorteAereoEntity.otrosGatos);
        entity.setPrecioTotal(cartaPorteAereoEntity.precioVenta);
        entity.setRtgId(cartaPorteAereoEntity.tipoGasto);
        entity.setObservacion(cartaPorteAereoEntity.observaciones);
        entity.setFoto(cartaPorteAereoEntity.foto);

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
