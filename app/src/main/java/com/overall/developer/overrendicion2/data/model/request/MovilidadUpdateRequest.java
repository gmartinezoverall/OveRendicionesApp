package com.overall.developer.overrendicion2.data.model.request;

public class MovilidadUpdateRequest
{
    private String idMovilidad;
    private String fecha;
    private String motivo;
    private String destino;
    private String monto;
    private String dni;
    private String datosTrabajador;
    private String foto;

    public MovilidadUpdateRequest() {
    }

    public MovilidadUpdateRequest(String idMovilidad, String fecha, String motivo, String destino, String monto, String dni, String datosTrabajador, String foto) {
        this.idMovilidad = idMovilidad;
        this.fecha = fecha;
        this.motivo = motivo;
        this.destino = destino;
        this.monto = monto;
        this.dni = dni;
        this.datosTrabajador = datosTrabajador;
        this.foto = foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
