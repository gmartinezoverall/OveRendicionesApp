package com.overall.developer.overrendicion.data.repository.Formularios.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface DBFormularios
{
    List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento);
    List<ProvinciaBean> getProvinciaDestinoList();
    Integer saveDataDB(RendicionBean rendicionBean);

    LiquidacionBean getCodLiquidacionDB();

    String getIdUsuarioDB();

    RendicionBean setRendicionForEditDB(Integer idRendicion);

    void setCodRendicion(String codRendicion, Integer idRendicion);

    UserBean getUser();

    void finisLoginDB();
}
