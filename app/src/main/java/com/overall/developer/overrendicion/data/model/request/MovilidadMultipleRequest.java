package com.overall.developer.overrendicion.data.model.request;

public class MovilidadMultipleRequest
{
    private String rdoId;
    private String codLiquidacion;
    private String idUsuario;
    private String motivo;
    private String destino;
    private String monto;
    private String fechaDocumento;
    private String rtgId;
    private String tipoMov;
    private String fecha;
    private String dni;
    private String datosTrabajador;

    public MovilidadMultipleRequest() {
    }

    public MovilidadMultipleRequest(String rdoId, String codLiquidacion, String idUsuario, String motivo, String destino, String monto, String fechaDocumento, String rtgId, String tipoMov, String fecha, String dni, String datosTrabajador) {
        this.rdoId = rdoId;
        this.codLiquidacion = codLiquidacion;
        this.idUsuario = idUsuario;
        this.motivo = motivo;
        this.destino = destino;
        this.monto = monto;
        this.fechaDocumento = fechaDocumento;
        this.rtgId = rtgId;
        this.tipoMov = tipoMov;
        this.fecha = fecha;
        this.dni = dni;
        this.datosTrabajador = datosTrabajador;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDatosTrabajador() {
        return datosTrabajador;
    }

    public void setDatosTrabajador(String datosTrabajador) {
        this.datosTrabajador = datosTrabajador;
    }
}
