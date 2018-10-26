package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

public class MovilidadRendicionEntity
{
    private String codRendicion;
    private String rdoId;
    private String numeroDoc;
    private String codLiquidacion;
    private String precioTotal = "0";
    private String tipoGasto;
    private String foto;

    public MovilidadRendicionEntity() {
    }

    public MovilidadRendicionEntity(String rdoId, String codLiquidacion, String precioTotal, String tipoGasto, String foto) {
        this.rdoId = rdoId;
        this.codLiquidacion = codLiquidacion;
        this.precioTotal = precioTotal;
        this.tipoGasto = tipoGasto;
        this.foto = foto;
    }

    public String getCodRendicion() {
        return codRendicion;
    }

    public void setCodRendicion(String codRendicion) {
        this.codRendicion = codRendicion;
    }

    public String getRdoId() {
        return rdoId;
    }

    public void setRdoId(String rdoId) {
        this.rdoId = rdoId;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public void setCodLiquidacion(String codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
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
