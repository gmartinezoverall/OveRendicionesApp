package com.overall.developer.overrendicion2.data.model.entity;

public class RendicionEntity
{
    private Integer idRendicion;
    private String codRendicion;
    private String rdoId;
    private String codLiquidacion;
    private String idUsuario;
    private String numeroDoc;
    private String bienServicio;
    private String igv = "0";
    private String afectoIgv;
    private String valorNeto = "0";
    private String precioTotal = "0";
    private String observacion;
    private String fechaDocumento;
    private String fechaVencimiento;
    private String ruc;
    private String razonSocial;
    private String bcoCod;
    private String tipoServicio;
    private String rtgId;
    private String otroGasto = "0";
    private String codDestino;
    private String afectoRetencion;
    private String codSuspencionH;
    private String tipoMoneda;
    private String tipoCambio;
    private String foto;
    private boolean send = false;

    public RendicionEntity() {
    }

    public RendicionEntity(Integer idRendicion ,String codRendicion, String rdoId, String codLiquidacion, String idUsuario, String numeroDoc, String bienServicio, String igv, String afectoIgv, String valorNeto, String precioTotal, String observacion, String fechaDocumento, String fechaVencimiento, String ruc, String razonSocial, String bcoCod, String tipoServicio, String rtgId, String otroGasto, String codDestino, String afectoRetencion, String codSuspencionH, String tipoMoneda, String tipoCambio, String foto, boolean send) {
        this.idRendicion = idRendicion;
        this.codRendicion = codRendicion;
        this.rdoId = rdoId;
        this.codLiquidacion = codLiquidacion;
        this.idUsuario = idUsuario;
        this.numeroDoc = numeroDoc;
        this.bienServicio = bienServicio;
        this.igv = igv;
        this.afectoIgv = afectoIgv;
        this.valorNeto = valorNeto;
        this.precioTotal = precioTotal;
        this.observacion = observacion;
        this.fechaDocumento = fechaDocumento;
        this.fechaVencimiento = fechaVencimiento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.bcoCod = bcoCod;
        this.tipoServicio = tipoServicio;
        this.rtgId = rtgId;
        this.otroGasto = otroGasto;
        this.codDestino = codDestino;
        this.afectoRetencion = afectoRetencion;
        this.codSuspencionH = codSuspencionH;
        this.tipoMoneda = tipoMoneda;
        this.tipoCambio = tipoCambio;
        this.foto = foto;
        this.send = send;
    }
    public Integer getIdRendicion() {
        return idRendicion;
    }

    public void setIdRendicion(Integer idRendicion) {
        this.idRendicion = idRendicion;
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

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getBienServicio() {
        return bienServicio;
    }

    public void setBienServicio(String bienServicio) {
        this.bienServicio = bienServicio;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getAfectoIgv() {
        return afectoIgv;
    }

    public void setAfectoIgv(String afectoIgv) {
        this.afectoIgv = afectoIgv;
    }

    public String getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(String valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getBcoCod() {
        return bcoCod;
    }

    public void setBcoCod(String bcoCod) {
        this.bcoCod = bcoCod;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getRtgId() {
        return rtgId;
    }

    public void setRtgId(String rtgId) {
        this.rtgId = rtgId;
    }

    public String getOtroGasto() {
        return otroGasto;
    }

    public void setOtroGasto(String otroGasto) {
        this.otroGasto = otroGasto;
    }

    public String getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(String codDestino) {
        this.codDestino = codDestino;
    }

    public String getAfectoRetencion() {
        return afectoRetencion;
    }

    public void setAfectoRetencion(String afectoRetencion) {
        this.afectoRetencion = afectoRetencion;
    }

    public String getCodSuspencionH() {
        return codSuspencionH;
    }

    public void setCodSuspencionH(String codSuspencionH) {
        this.codSuspencionH = codSuspencionH;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}