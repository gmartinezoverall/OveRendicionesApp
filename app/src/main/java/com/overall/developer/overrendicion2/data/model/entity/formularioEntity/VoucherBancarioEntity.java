package com.overall.developer.overrendicion2.data.model.entity.formularioEntity;

import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;

public class VoucherBancarioEntity implements FormularioEntity
{
    private String codLiquidacion;
    private String tipoDocumento;
    private String fechaDeposito;
    private String numeroDocumento;
    private String bcoCod;
    private String tipoMoneda;
    private String valorVenta;
    private String tipoGasto;
    private String foto;

    public VoucherBancarioEntity() {
    }

    public VoucherBancarioEntity(String tipoDocumento, String fechaDeposito, String numeroDocumento, String bcoCod, String tipoMoneda, String valorVenta, String tipoGasto, String foto) {
        this.tipoDocumento = tipoDocumento;
        this.fechaDeposito = fechaDeposito;
        this.numeroDocumento = numeroDocumento;
        this.bcoCod = bcoCod;
        this.tipoMoneda = tipoMoneda;
        this.valorVenta = valorVenta;
        this.tipoGasto = tipoGasto;
        this.foto= foto;

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

    public String getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(String fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getBcoCod() {
        return bcoCod;
    }

    public void setBcoCod(String bcoCod) {
        this.bcoCod = bcoCod;
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

    @Override
    public RendicionEntity getEntity(Object obj) {
        VoucherBancarioEntity voucherEntity = (VoucherBancarioEntity) obj;
        RendicionEntity entity = new RendicionEntity();
        entity.setCodLiquidacion(voucherEntity.codLiquidacion);
        entity.setRdoId(voucherEntity.tipoDocumento);
        entity.setFechaDocumento(voucherEntity.fechaDeposito);
        entity.setNumeroDoc(voucherEntity.numeroDocumento);
        entity.setBcoCod(voucherEntity.bcoCod);
        entity.setTipoMoneda(voucherEntity.tipoMoneda);
        entity.setPrecioTotal(voucherEntity.valorVenta);
        entity.setRtgId(voucherEntity.tipoGasto);
        entity.setFoto(voucherEntity.foto);
        return entity;
    }
}
