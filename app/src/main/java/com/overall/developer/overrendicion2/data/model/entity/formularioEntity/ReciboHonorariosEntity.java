package com.overall.developer.overrendicion2.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;

public class ReciboHonorariosEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String ruc;
    private String razonSocial;
    private String numDocumento;
    private String fechaDocumento;
    private String monto;
    private String retencion;
    private String afectoRetencion;
    private String codSuspencion;
    private String tipoGasto;
    private String foto;

    public ReciboHonorariosEntity() {
    }

    public ReciboHonorariosEntity(String tipoDocumento, String ruc, String razonSocial, String numDocumento, String fechaDocumento, String monto, String afectoRetencion, String codSuspencion, String tipoGasto, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.numDocumento = numDocumento;
        this.fechaDocumento = fechaDocumento;
        this.monto = monto;
        this.afectoRetencion = afectoRetencion;
        this.codSuspencion = codSuspencion;
        this.tipoGasto = tipoGasto;
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj) {
        ReciboHonorariosEntity reciboHonorariosEntity = (ReciboHonorariosEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(reciboHonorariosEntity.codLiquidacion);
        entity.setRdoId(reciboHonorariosEntity.tipoDocumento);
        entity.setRuc(reciboHonorariosEntity.ruc);
        entity.setRazonSocial(reciboHonorariosEntity.razonSocial);
        entity.setNumeroDoc(reciboHonorariosEntity.numDocumento);
        entity.setFechaDocumento(reciboHonorariosEntity.fechaDocumento);
        entity.setPrecioTotal(reciboHonorariosEntity.monto);
        //entity.setAfectoRetencion(reciboHonorariosEntity.afectoRetencion);
        entity.setIgv(reciboHonorariosEntity.retencion); // Por Gustavo M. 28/03 para ingresar el valor de la retencion, en este caso el 0.08
        entity.setAfectoRetencion(reciboHonorariosEntity.afectoRetencion); // Por Gustavo M. 28/03 se reemplazó afectoRetencion por afectoIgv, porque el SP hace la operación con ese campo
        entity.setCodSuspencionH(reciboHonorariosEntity.codSuspencion);
        entity.setRtgId(reciboHonorariosEntity.tipoGasto);
        entity.setFoto(reciboHonorariosEntity.foto);

        return entity;
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

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String retencion) {
        this.retencion = retencion;
    }

    public String getAfectoRetencion() {
        return afectoRetencion;
    }

    public void setAfectoRetencion(String afectoRetencion) {
        this.afectoRetencion = afectoRetencion;
    }

    public String getCodSuspencion() {
        return codSuspencion;
    }

    public void setCodSuspencion(String codSuspencion) {
        this.codSuspencion = codSuspencion;
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
