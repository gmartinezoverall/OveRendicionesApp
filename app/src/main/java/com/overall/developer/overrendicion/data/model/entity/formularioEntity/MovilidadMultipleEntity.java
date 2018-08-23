package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

public class MovilidadMultipleEntity
{

    private String idMovilidad;
    private String rdoId;
    private String dniTrabajador;
    private String datosTrabajador;
    private String motivo;
    private String destino;
    private String monto;
    private String fechaDocumento;
    private String rtgId;
    private String foto;

    public MovilidadMultipleEntity() {
    }

    public MovilidadMultipleEntity(String idMovilidad, String rdoId, String dniTrabajador, String datosTrabajador, String motivo, String destino, String monto, String fechaDocumento, String rtgId, String foto) {
        this.idMovilidad = idMovilidad;
        this.rdoId = rdoId;
        this.dniTrabajador = dniTrabajador;
        this.datosTrabajador = datosTrabajador;
        this.motivo = motivo;
        this.destino = destino;
        this.monto = monto;
        this.fechaDocumento = fechaDocumento;
        this.rtgId = rtgId;
        this.foto = foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
