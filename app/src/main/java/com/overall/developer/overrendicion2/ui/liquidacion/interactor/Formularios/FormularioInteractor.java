package com.overall.developer.overrendicion2.ui.liquidacion.interactor.Formularios;

import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.MovilidadRendicionEntity;

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

    void saveDataMovilidad(MovilidadRendicionEntity rendicionEntity, MovilidadEntity movilidadEntity);

    void searchRuc(String ruc);

    void searchRucSuccess(String razonSocial);

    void searchRucError();

    void saveDataMovilidadMultiple(MovilidadRendicionEntity entity, MovilidadEntity movilidadEntity);

    void setTipoCambio();

    LiquidacionEntity getLiquidacion();

    void errorTipoCambio();

    Boolean validateMontoMovilidad(Double monto, String fechaViaje, String fechaFin);
}
