package com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadMultipleEntity;

import java.util.List;

public interface FormularioInteractor
{

    List<TipoGastoEntity> getDocumentForId(String idDocumento);

    List<ProvinciaEntity> getProvinciaDestinoList();

    void saveData(List<String> typeFragment, Object objectDinamyc);

    List<String> getDefaultValues();

    RendicionEntity setRendicionForEdit(String idRendicion);

    UserBean getUser();

    void finisLogin();

    String getCodLiquidacion();

    List<BancoEntity> getAllBancos();

    TipoGastoEntity getDefaultTipoGasto(String rtgId);

    ProvinciaEntity getDefaultProvicia(String codDestino);

    BancoEntity getDefaultBanco(String bcoCod);

    RendicionDetalleEntity setMovilidadForEdit(int idMovilidad);

    void saveDataMovilidad(MovilidadEntity movilidadEntity);

    void searchRuc(String ruc);

    void searchRucSuccess(String razonSocial);

    void searchRucError();

    void saveDataMovilidadMultiple(MovilidadMultipleEntity movilidadEntity);

    void setTipoCambio();

    LiquidacionEntity getLiquidacion();

    void errorTipoCambio();

    Boolean validateMontoMovilidad(Double monto, String fechaViaje, String fechaFin);
}
