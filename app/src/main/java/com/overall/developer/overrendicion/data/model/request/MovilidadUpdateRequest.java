package com.overall.developer.overrendicion.data.model.request;

public class MovilidadUpdateRequest
{
    private String idMovilidad;
    private String fecha;
    private String motivo;
    private String destino;
    private String monto;

    public MovilidadUpdateRequest() {
    }

    public MovilidadUpdateRequest(String idMovilidad, String fecha, String motivo, String destino, String monto) {
        this.idMovilidad = idMovilidad;
        this.fecha = fecha;
        this.motivo = motivo;
        this.destino = destino;
        this.monto = monto;
    }

    public String getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(String idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}
