package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class BoletoAereoEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String numeroDocumento;
    private String destino;
    private String fechaDocumento;
    private String tipoMoneda;
    private String monto;
    private String igv;
    private String afectoIgv;
    private String otrosGatos;
    private String tipoGasto;
    private String foto;


    public BoletoAereoEntity() {
    }

    public BoletoAereoEntity(String tipoDocumento, String ruc, String razonSocial, String numeroDocumento, String destino, String fechaDocumento, String tipoMoneda, String monto, String igv, String afectoIgv, String otrosGatos, String tipoGasto, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.numeroDocumento = numeroDocumento;
        this.destino = destino;
        this.fechaDocumento = fechaDocumento;
        this.tipoMoneda = tipoMoneda;
        this.monto = monto;
        this.igv = igv;
        this.afectoIgv = afectoIgv;
        this.otrosGatos = otrosGatos;
        this.tipoGasto = tipoGasto;
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj)
    {
        BoletoAereoEntity boletoAereoEntity = (BoletoAereoEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(boletoAereoEntity.codLiquidacion);
        entity.setRdoId(boletoAereoEntity.tipoDocumento);
        entity.setRuc(boletoAereoEntity.ruc);
        entity.setRazonSocial(boletoAereoEntity.razonSocial);
        entity.setNumeroDoc(boletoAereoEntity.numeroDocumento);
        entity.setObservacion(boletoAereoEntity.destino);//esto no se donde ira
        entity.setFechaDocumento(boletoAereoEntity.fechaDocumento);
        entity.setTipoMoneda(boletoAereoEntity.tipoMoneda);
        entity.setPrecioTotal(boletoAereoEntity.monto);
        entity.setIgv(boletoAereoEntity.igv);
        entity.setAfectoIgv(boletoAereoEntity.afectoIgv);
        entity.setOtroGasto(boletoAereoEntity.otrosGatos);
        entity.setRtgId(boletoAereoEntity.tipoGasto);
        entity.setFoto(boletoAereoEntity.foto);

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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
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

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
