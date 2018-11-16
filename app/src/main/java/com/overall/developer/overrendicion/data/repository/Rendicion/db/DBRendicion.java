package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

import java.util.List;

public interface DBRendicion
{
    List<RendicionBean> listRendicion();

    String deleteRendicionForCodDB(int position);

    void changeStatusLiquidacion();

    String getCodLiquidacionDB();

    void insertListRendicionInDB(List<RendicionBean> mRendionList);

    LiquidacionBean getForCodLiquidacion(String codLiquidacion);

    UserBean getUserDB();

    void finishLogin();

    ProvinciaBean getProvinciaDB(String ubigeoProvDestino);

    void insertListMovilidadDB(String codLiquidacionDB, List<RendicionDetalleBean> movilidadList);

    List<RendicionDetalleBean> getListMovilidadDB(String codRendicion);

    String deleteDetMovForCodDB(int rdoId, int idDetMov);

    String getUrlImageDB(String codRendicion);
}
