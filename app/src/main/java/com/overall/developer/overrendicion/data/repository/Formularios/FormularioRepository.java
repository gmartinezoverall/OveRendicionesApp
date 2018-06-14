package com.overall.developer.overrendicion.data.repository.Formularios;


import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface FormularioRepository
{
    List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento);
    List<ProvinciaBean> getProvinciaDestinoList();
    void saveDataDB(RendicionBean rendicionBean);
    void sendDataApi(RendicionEntity entity);
    String getCodLiquidacionDB();

    String getIdUsuarioDB();

    RendicionBean setRendicionForEditDB(Integer idRendicion);
}
