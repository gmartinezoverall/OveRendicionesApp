package com.overall.developer.overrendicion.data.repository.liquidacion.Formularios.db;

import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.OtrosBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

import java.util.List;

public interface DBFormularios
{
    List<TipoDocumentoBean> getDocumentForIdDB(String idDocumento);

    List<ProvinciaBean> getProvinciaDestinoList();

    Integer saveDataDB(RendicionBean rendicionBean);

    LiquidacionBean getCodLiquidacionDB();

    String getIdUsuarioDB();

    RendicionBean setRendicionForEditDB(Integer idRendicion);

    void deleteForCodRendicion(String codRendicion, Integer idRendicion);

    UserBean getUser();

    void finisLoginDB();

    String getCodRendicion(Integer idRendicion);

    List<BancoBean> getAllBancos();

    TipoDocumentoBean getDefaultTipoGastoDB(String rtgId);

    ProvinciaBean getDefaultProviciaDB(String codDestino);

    BancoBean getDefaultBancoDB(String bcoCod);

    RendicionDetalleBean setMovilidadForEditDB(int idMov);

    void insertMovilidadDB(RendicionBean rendicionBean, RendicionDetalleBean detalleBean);

    void insertTipoCambioDB(String desc);

    String getTipoCambioDB();

    LiquidacionBean getLiquidacionDB();

    void updateLiquidacionDB(RendicionBean bean);

    OtrosBean getOtrosBeanDB();

    Double getSumaAcuentaDB(String fechaViaje);

    RendicionBean getDetailMovOffLineDB();
}
