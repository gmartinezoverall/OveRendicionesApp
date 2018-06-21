package com.overall.developer.overrendicion.data.repository.Formularios.api;


import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;

public interface ApiFormularios
{
    void sendDataForInsertApi(RendicionRequest request, Integer idRendicion);

    void sendDataForUpdateApi(RendicionRequest request, Integer idRendicion);
}
