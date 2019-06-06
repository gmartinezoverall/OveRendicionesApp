package com.overall.developer.overrendicion2.ui.liquidacion.presenter.Rendicion;

import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;

import java.util.List;

public interface RendicionPresenter
{
    void listRendicion();

    void deleteRendicionForCod(int position);

    void changeStatusLiquidacion();

    void getListRendicion(List<RendicionEntity> rendicionList);

    void updateListRendicion(List<RendicionEntity> rendicionBeans);

    LiquidacionEntity getForCodLiquidacion(String codLiquidacion);

    UserBean getUser();

    void finisLogin();

    void getListMovilidad(List<RendicionDetalleEntity> listMovilidad);

    void deleteDetMovForCod(int rdoId, int idDetMov);

    void deleteDetMovSuccess(List<RendicionEntity> entityList, List<RendicionDetalleEntity> detalleEntityList);

    void sendDataPhote(String codRendicion, String pathImage);

    void sendPhotoSuccess();

    String getUrlImage(String codRendicion);

    String getCodLiquidacion();

    List<RendicionDetalleEntity> getListRendicionDetalle(String codRendicion);

    void deleteMovSuccess(int rdoId, Double totalMontoMovilidad);
}
