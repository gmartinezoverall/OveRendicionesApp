package com.overall.developer.overrendicion.data.model.entity.formularioEntity;

public class MovilidadEntity
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

    public MovilidadEntity() {
    }

    public MovilidadEntity(String idMovilidad, String rdoId, String dniTrabajador, String datosTrabajador, String motivo, String destino, String monto, String fechaDocumento, String rtgId, String tipoMov, String fecha, String fechaHastaM, String foto) {

        this.idMovilidad = idMovilidad;
        this.rdoId = rdoId;
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

    public String getRdoId() {
        return rdoId;
    }

    public String getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(String idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public String getCodLiquidacion() {
        return codLiquidacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getDniTrabajador() {
        return dniTrabajador;
    }

    public String getDatosTrabajador() {
        return datosTrabajador;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDestino() {
        return destino;
    }

    public String getMonto() {
        return monto;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public String getRtgId() {
        return rtgId;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public String getFecha() {
        return fecha;
    }

    public String getFechaHastaM() {
        return fechaHastaM;
    }

    public String getFoto() {
        return foto;
    }
}
