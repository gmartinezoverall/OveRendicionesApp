package com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadEntity;

import java.util.List;

public interface FormularioPresenter
{
    List<TipoGastoEntity> getDocumentForId(String idDocumento);
    List<String> getProvinciaDestinoList();
    void saveData(List<String> typeFragment, Object objectDinamyc);


    void saveDataSuccess();

    List<String> getDefaultValues();

    RendicionEntity setRendicionForEdit(String idRendicion);

    UserBean getUser();

    void finisLogin();

    String getCodLiquidacion();

    List<BancoEntity> getAllBancos();


    TipoGastoEntity getDefaultTipoGasto(String rtgId);

    BancoEntity getDefaultBanco(String bcoCod);

    RendicionDetalleEntity setMovilidadForEdit(int idMovilidad);

    void saveDataMovilidad(MovilidadEntity movilidadEntity);
}
