package com.overall.developer.overrendicion2.data.repository.liquidacion.Rendicion;


import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;

import java.util.List;

public interface RendicionRepository
{
    List<RendicionBean> listRendicion();

    String deleteRendicionForCodDB(int position);

    void deleteRendicionForCodApi(String codCodRendicion);

    void changeStatusLiquidacionDB();

    void insertListRendicionesApi(String codLiquidacion);

    String getCodLiquidacionDB();

    void insertListRendicionInDB(List<RendicionBean> mRendionList);

    void insertListCompleted();

    LiquidacionBean getForCodLiquidacionDB(String codLiquidacion);

    UserBean getUserDB();

    void finishLoginDB();

    ProvinciaBean getProvinciaDB(String ubigeoProvDestino);

    void insertListMovilidadApi(String codLiquidacionDB);

    void insertListMovilidadDB(String codLiquidacionDB, List<RendicionDetalleBean> movilidadList);

    List<RendicionDetalleBean> getListMovilidadDB(String codRendicion);

    String deleteDetMovForCodDB(int rdoId, int idDetMov);

    void deleteDetMovForCodApi(String idDetMov);

    void deleteDetMovSuccess(List<RendicionBean> beanList, List<RendicionDetalleBean> detalleBeansList);

    void sendDataPhoteApi(String codRendicion, String pathImage);

    void sendPhotoSuccess();

    void sendPhotoError();

    String getUrlImageDB(String codRendicion);

    void deleteMovSuccess(int rdoId, Double totalMontoMovilidad);
}
