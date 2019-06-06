package com.overall.developer.overrendicion2.data.model.entity;

import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;

public class LiquidacionEntity {
    private String codLiquidacion;
    private String tipoLiquidacion;
    private String descripcionLiquidacion;
    private Double monto;
    private String nombre;
    private String idPeriodo;
    private String fechaPago;
    private String codComp;
    private Double aCuenta;
    private Double saldo;
    private String dni;
    private String fechaViatico;
    private String motivoViaje;
    private ProvinciaEntity ubigeoProvDestino;
    private String fechaDesde;
    private String fechaHasta;
    private String tipoViatico;
    private String estado;
    private String codEgreso;
    private String fechaInicioLiq;
    private String fechaFinLiq;
    private String fechaDesdeR;
    private String fechaHastaR;
    private boolean status;


    public LiquidacionEntity() {
    }

    public LiquidacionEntity(String codLiquidacion, String tipoLiquidacion, String descripcionLiquidacion, Double monto, String nombre, String idPeriodo, String fechaPago, String codComp, Double aCuenta, Double saldo, String dni, String fechaViatico, String motivoViaje, ProvinciaEntity ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico, String estado, String codEgreso, String fechaInicioLiq, String fechaFinLiq, String fechaDesdeR, String fechaHastaR, boolean status) {
        this.codLiquidacion = codLiquidacion;
        this.tipoLiquidacion = tipoLiquidacion;
        this.descripcionLiquidacion = descripcionLiquidacion;
        this.monto = monto;
        this.nombre = nombre;
        this.idPeriodo = idPeriodo;
        this.fechaPago = fechaPago;
        this.codComp = codComp;
        this.aCuenta = aCuenta;
        this.saldo = saldo;
        this.dni = dni;
        this.fechaViatico = fechaViatico;
        this.motivoViaje = motivoViaje;
        this.ubigeoProvDestino = ubigeoProvDestino;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.tipoViatico = tipoViatico;
        this.estado = estado;
        this.codEgreso = codEgreso;
        this.fechaInicioLiq = fechaInicioLiq;
        this.fechaFinLiq = fechaFinLiq;
        this.fechaDesdeR = fechaDesdeR;
        this.fechaHastaR = fechaHastaR;
        this.status = status;
    }

    public LiquidacionEntity(LiquidacionBean liquidacionBean)
    {
        this.codLiquidacion = liquidacionBean.getCodLiquidacion();
        this.tipoLiquidacion = liquidacionBean.getTipoLiquidacion();
        this.descripcionLiquidacion = liquidacionBean.getDescripcionLiquidacion();
        this.monto = liquidacionBean.getMonto();
        this.nombre = liquidacionBean.getNombre();
        this.idPeriodo = liquidacionBean.getIdPeriodo();
        this.fechaPago = liquidacionBean.getFechaPago();
        this.codComp = liquidacionBean.getCodComp();
        this.aCuenta = liquidacionBean.getaCuenta();
        this.saldo = liquidacionBean.getSaldo();
        this.dni = liquidacionBean.getDni();
        this.fechaViatico = liquidacionBean.getFechaViatico();
        this.motivoViaje = liquidacionBean.getMotivoViaje();
        this.fechaDesde = liquidacionBean.getFechaDesde();
        this.fechaHasta = liquidacionBean.getFechaHasta();
        this.tipoViatico = liquidacionBean.getTipoViatico();
        this.codEgreso = liquidacionBean.getCodEgreso();
        this.fechaInicioLiq = liquidacionBean.getFechaInicioLiq();
        this.fechaFinLiq = liquidacionBean.getFechaFinLiq();
        this.status = liquidacionBean.isStatus();


    }

    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public void setCodLiquidacion(String codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public String getTipoLiquidacion() {
        return tipoLiquidacion;
    }

    public void setTipoLiquidacion(String tipoLiquidacion) {
        this.tipoLiquidacion = tipoLiquidacion;
    }

    public String getDescripcionLiquidacion() {
        return descripcionLiquidacion;
    }

    public void setDescripcionLiquidacion(String descripcionLiquidacion) {
        this.descripcionLiquidacion = descripcionLiquidacion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCodComp() {
        return codComp;
    }

    public void setCodComp(String codComp) {
        this.codComp = codComp;
    }

    public Double getaCuenta() {
        return aCuenta;
    }

    public void setaCuenta(Double aCuenta) {
        this.aCuenta = aCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaViatico() {
        return fechaViatico;
    }

    public void setFechaViatico(String fechaViatico) {
        this.fechaViatico = fechaViatico;
    }

    public String getMotivoViaje() {
        return motivoViaje;
    }

    public void setMotivoViaje(String motivoViaje) {
        this.motivoViaje = motivoViaje;
    }

    public ProvinciaEntity getUbigeoProvDestino() {
        return ubigeoProvDestino;
    }

    public void setUbigeoProvDestino(ProvinciaEntity ubigeoProvDestino) {
        this.ubigeoProvDestino = ubigeoProvDestino;
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

    public String getTipoViatico() {
        return tipoViatico;
    }

    public void setTipoViatico(String tipoViatico) {
        this.tipoViatico = tipoViatico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodEgreso() {
        return codEgreso;
    }

    public void setCodEgreso(String codEgreso) {
        this.codEgreso = codEgreso;
    }

    public String getFechaInicioLiq() {
        return fechaInicioLiq;
    }

    public void setFechaInicioLiq(String fechaInicioLiq) {
        this.fechaInicioLiq = fechaInicioLiq;
    }

    public String getFechaFinLiq() {
        return fechaFinLiq;
    }

    public void setFechaFinLiq(String fechaFinLiq) {
        this.fechaFinLiq = fechaFinLiq;
    }

    public String getFechaDesdeR() {
        return fechaDesdeR;
    }

    public void setFechaDesdeR(String fechaDesdeR) {
        this.fechaDesdeR = fechaDesdeR;
    }

    public String getFechaHastaR() {
        return fechaHastaR;
    }

    public void setFechaHastaR(String fechaHastaR) {
        this.fechaHastaR = fechaHastaR;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}