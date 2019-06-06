package com.overall.developer.overrendicion2.data.model.bean;

import io.realm.RealmObject;

public class MovilidadBean extends RealmObject
{
    private Integer id;
    private String idMovilidad;
    private String rdoId;
    private String codLiquidacion;
    private String idUsuario;
    private String dniTrabajador;
    private String datosTrabajador;
    private String motivo;
    private String destino;
    private String monto;
    private String fechaDocumento;
    private String rtgId;
    private String tipoMov;
    private String fecha;
    private String fechaHastaM;
    private String foto;

    public MovilidadBean() {
    }


    public MovilidadBean(String idMovilidad, String rdoId, String codLiquidacion, String idUsuario, String dniTrabajador, String datosTrabajador, String motivo, String destino, String monto, String fechaDocumento, String rtgId, String tipoMov, String fecha, String fechaHastaM, String foto) {
        this.idMovilidad = idMovilidad;
        this.rdoId = rdoId;
        this.codLiquidacion = codLiquidacion;
        this.idUsuario = idUsuario;
        this.dniTrabajador = dniTrabajador;
        this.datosTrabajador = datosTrabajador;
        this.motivo = motivo;
        this.destino = destino;
        this.monto = monto;
        this.fechaDocumento = fechaDocumento;
        this.rtgId = rtgId;
        this.tipoMov = tipoMov;
        this.fecha = fecha;
        this.fechaHastaM = fechaHastaM;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(String idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public String getRdoId() {
        return rdoId;
    }

    public void setRdoId(String rdoId) {
        this.rdoId = rdoId;
    }

    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public void setCodLiquidacion(String codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDniTrabajador() {
        return dniTrabajador;
    }

    public void setDniTrabajador(String dniTrabajador) {
        this.dniTrabajador = dniTrabajador;
    }

    public String getDatosTrabajador() {
        return datosTrabajador;
    }

    public void setDatosTrabajador(String datosTrabajador) {
        this.datosTrabajador = datosTrabajador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getRtgId() {
        return rtgId;
    }

    public void setRtgId(String rtgId) {
        this.rtgId = rtgId;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaHastaM() {
        return fechaHastaM;
    }

    public void setFechaHastaM(String fechaHastaM) {
        this.fechaHastaM = fechaHastaM;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
