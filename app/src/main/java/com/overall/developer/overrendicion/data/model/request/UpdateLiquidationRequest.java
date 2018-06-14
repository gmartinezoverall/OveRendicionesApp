package com.overall.developer.overrendicion.data.model.request;

public class UpdateLiquidationRequest
{
    private String codLiquidacion;
    private String fechaViatico;
    private String motivoViaje;
    private String ubigeoProvDestino;
    private String fechaDesde;
    private String fechaHasta;
    private String tipoViatico;
    private String estado;

    public UpdateLiquidationRequest() {
    }

    public UpdateLiquidationRequest(String codLiquidacion, String fechaViatico, String motivoViaje, String ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico, String estado) {
        this.codLiquidacion = codLiquidacion;
        this.fechaViatico = fechaViatico;
        this.motivoViaje = motivoViaje;
        this.ubigeoProvDestino = ubigeoProvDestino;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.tipoViatico = tipoViatico;
        this.estado = estado;
    }

    public void setCodLiquidacion(String codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public void setFechaViatico(String fechaViatico) {
        this.fechaViatico = fechaViatico;
    }

    public void setMotivoViaje(String motivoViaje) {
        this.motivoViaje = motivoViaje;
    }

    public void setUbigeoProvDestino(String ubigeoProvDestino) {
        this.ubigeoProvDestino = ubigeoProvDestino;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public void setTipoViatico(String tipoViatico) {
        this.tipoViatico = tipoViatico;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
