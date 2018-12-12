package com.overall.developer.overrendicion.data.model.entity;

public class TipoReembolso
{
    private Integer codComp;
    private String descripcion;

    public TipoReembolso() {
    }

    public TipoReembolso(Integer codComp, String descripcion) {
        this.codComp = codComp;
        this.descripcion = descripcion;
    }

    public Integer getCodComp() {
        return codComp;
    }

    public void setCodComp(Integer codComp) {
        this.codComp = codComp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
