package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

public class SinSustentoTributarioEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String fechaDocumento;
    private String montoNoAfectado;
    private String tipoGasto;
    private String observaciones;
    private String foto;

    public SinSustentoTributarioEntity() {
    }

    public SinSustentoTributarioEntity(String tipoDocumento, String fechaDocumento, String montoNoAfectado, String tipoGasto, String observaciones, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.fechaDocumento = fechaDocumento;
        this.montoNoAfectado = montoNoAfectado;
        this.tipoGasto = tipoGasto;
        this.observaciones = observaciones;
        this.foto = foto;
    }

    @Override
    public RendicionEntity getEntity(Object obj) {
        SinSustentoTributarioEntity sinSustentoTributarioEntity = (SinSustentoTributarioEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(sinSustentoTributarioEntity.codLiquidacion);
        entity.setRdoId(sinSustentoTributarioEntity.tipoDocumento);
        entity.setFechaDocumento(sinSustentoTributarioEntity.fechaDocumento);
        entity.setOtroGasto(sinSustentoTributarioEntity.montoNoAfectado);
        entity.setRtgId(sinSustentoTributarioEntity.tipoGasto);
        entity.setObservacion(sinSustentoTributarioEntity.observaciones);
        entity.setFoto(sinSustentoTributarioEntity.foto);

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
