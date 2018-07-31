package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.MovilidadBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;

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

    void insertListMovilidadDB(List<MovilidadBean> movilidadList);

    List<MovilidadBean> getListMovilidadDB(String codLiquidacion);
}
