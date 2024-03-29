package com.overall.developer.overrendicion2.data.model.entity;

public class RendicionDetalleEntity
{
    private Integer id;
    private String idMovilidad;
    private String codRendicion;
    private String rdoId;
    private String rtgId;
    private String precioTotal;
    private String fechaRendicion;
    private String estado;
    private String destinoMovilidad;
    private String montoMovilidad;
    private String motivoMovilidad;
    private String beneficiario;
    private String fechaDesde;
    private String fechaHasta;
    private String numBeneficiario;
    private String dni;
    private String datosTrabajador;
    private String foto;

    public RendicionDetalleEntity() {
    }

    public RendicionDetalleEntity(Integer id, String idMovilidad, String codRendicion, String rdoId, String rtgId, String precioTotal, String fechaRendicion, String estado, String destinoMovilidad, String montoMovilidad, String motivoMovilidad, String beneficiario, String fechaDesde, String fechaHasta, String numBeneficiario, String dni, String datosTrabajador, String foto) {
        this.id = id;
        this.idMovilidad = idMovilidad;
        this.codRendicion = codRendicion;
        this.rdoId = rdoId;
        this.rtgId = rtgId;
        this.precioTotal = precioTotal;
        this.fechaRendicion = fechaRendicion;
        this.estado = estado;
        this.destinoMovilidad = destinoMovilidad;
        this.montoMovilidad = montoMovilidad;
        this.motivoMovilidad = motivoMovilidad;
        this.beneficiario = beneficiario;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.numBeneficiario = numBeneficiario;
        this.dni = dni;
        this.datosTrabajador = datosTrabajador;
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

    public String getRtgId() {
        return rtgId;
    }

    public void setRtgId(String rtgId) {
        this.rtgId = rtgId;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFechaRendicion() {
        return fechaRendicion;
    }

    public void setFechaRendicion(String fechaRendicion) {
        this.fechaRendicion = fechaRendicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDestinoMovilidad() {
        return destinoMovilidad;
    }

    public void setDestinoMovilidad(String destinoMovilidad) {
        this.destinoMovilidad = destinoMovilidad;
    }

    public String getMontoMovilidad() {
        return montoMovilidad;
    }

    public void setMontoMovilidad(String montoMovilidad) {
        this.montoMovilidad = montoMovilidad;
    }

    public String getMotivoMovilidad() {
        return motivoMovilidad;
    }

    public void setMotivoMovilidad(String motivoMovilidad) {
        this.motivoMovilidad = motivoMovilidad;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
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

    public String getNumBeneficiario() {
        return numBeneficiario;
    }

    public void setNumBeneficiario(String numBeneficiario) {
        this.numBeneficiario = numBeneficiario;
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
