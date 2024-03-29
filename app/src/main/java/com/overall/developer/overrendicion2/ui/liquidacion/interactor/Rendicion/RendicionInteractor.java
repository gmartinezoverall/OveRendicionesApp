package com.overall.developer.overrendicion2.ui.liquidacion.interactor.Rendicion;

import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;

import java.util.List;

public interface RendicionInteractor
{
    void listRendicion();

    void deleteRendicionForCod(int position);

    void changeStatusLiquidacion();

    void updateListRendicion(List<RendicionBean> rendicionBeans);

    LiquidacionEntity getForCodLiquidacion(String codLiquidacion);

    UserBean getUser();

    void finishLogin();

    void successListMovilidad(List<RendicionDetalleBean> movilidadList);

    void deleteDetMovForCod(int rdoId, int idDetMov);

    void deleteDetMovSuccess(List<RendicionBean> beanList, List<RendicionDetalleBean> detalleBeansList);

    void sendDataPhote(String codRendicion, String pathImage);

    void sendPhotoError();

    void sendPhotoSuccess();

    String getUrlImage(String codRendicion);

    String getCodLiquidacion();

    List<RendicionDetalleEntity> getListRendicionDetalle(String codRendicion);

    void deleteMovSuccess(int rdoId, Double totalMontoMovilidad);
}
