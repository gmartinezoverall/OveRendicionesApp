package com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;

import java.util.List;

public interface FormularioInteractor
{

    List<TipoGastoEntity> getDocumentForId(String idDocumento);
    List<String> getProvinciaDestinoList();
    void saveData(List<String> typeFragment, Object objectDinamyc);

    List<String> getDefaultValues();

    RendicionEntity setRendicionForEdit(String idRendicion);

}
