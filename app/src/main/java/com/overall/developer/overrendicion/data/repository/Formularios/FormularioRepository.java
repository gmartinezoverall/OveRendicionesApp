package com.overall.developer.overrendicion.data.repository.Formularios;


import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.MovilidadBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;

import java.util.List;

public interface FormularioRepository
{
    List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento);
    List<ProvinciaBean> getProvinciaDestinoList();
    Integer saveDataDB(RendicionBean rendicionBean);
    void sendDataForInsertApi(RendicionRequest request, Integer idRendicion);
    void sendDataForUpdateApi(RendicionRequest request, Integer idRendicion);
    LiquidacionBean getCodLiquidacionDB();

    String getIdUsuarioDB();

    RendicionBean setRendicionForEditDB(Integer idRendicion);

    void deleteRendicionSend(String codRendicion, Integer idRendicion);

    UserBean getUserDB();

    void finisLoginDB();



    String getCodRendicion(Integer idRendicion);

    List<BancoBean> getAllBancos();

    TipoDocumentoBean getDefaultTipoGastoDB(String rtgId);

    BancoBean getDefaultBancoDB(String bcoCod);

    RendicionDetalleBean setMovilidadForEditDB(int idMov);

    void insertMovilidadDB(MovilidadBean movilidadBean);

    void sendDataInsertMovilidadApi(MovilidadInsertRequest movilidadRequest);

    void sendDataUpdateMovilidadApi(MovilidadUpdateRequest updateRequest);

    void searchRucApi(String ruc);

    void searchRucSuccess(String razonSocial);

    void sendDataInsertMovilidadMultipleApi(MovilidadMultipleRequest movilidadMultipleRequest);
}
