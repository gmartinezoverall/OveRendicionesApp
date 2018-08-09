package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class ArrendamientoEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String fechaDocumento;
    private String docIdentoidad;
    private String numeroDoc;
    private String monto;
    private String tipoGasto;
    private String foto;

    public ArrendamientoEntity() {
    }

    public ArrendamientoEntity(String tipoDocumento, String ruc, String razonSocial, String fechaDocumento, String docIdentoidad, String numeroDoc, String monto, String tipoGasto, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.fechaDocumento = fechaDocumento;
        this.docIdentoidad = docIdentoidad;
        this.numeroDoc = numeroDoc;
        this.monto = monto;
        this.tipoGasto = tipoGasto;
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj) {
        ArrendamientoEntity arrendamientoEntity = (ArrendamientoEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(arrendamientoEntity.codLiquidacion);
        entity.setRdoId(arrendamientoEntity.tipoDocumento);
        entity.setRuc(arrendamientoEntity.ruc);
        entity.setRazonSocial(arrendamientoEntity.razonSocial);
        entity.setFechaDocumento(arrendamientoEntity.fechaDocumento);
        entity.setOtroGasto(arrendamientoEntity.docIdentoidad);//no se donde ira
        entity.setNumeroDoc(arrendamientoEntity.numeroDoc);
        entity.setPrecioTotal(arrendamientoEntity.monto);
        entity.setRtgId(arrendamientoEntity.tipoGasto);
        entity.setFoto(arrendamientoEntity.foto);

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

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getDocIdentoidad() {
        return docIdentoidad;
    }

    public void setDocIdentoidad(String docIdentoidad) {
        this.docIdentoidad = docIdentoidad;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
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
