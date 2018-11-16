package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface RendicionView
{

    void getListRendicion(List<RendicionEntity> rendicionList);

    void updateListRendicion(List<RendicionEntity> rendicionBeans);

    void getListMovilidad(List<RendicionDetalleEntity> listMovilidad);

    void deleteDetMovSuccess(List<RendicionEntity> entityList, List<RendicionDetalleEntity> detalleEntityList);

    void sendPhotoSuccess();

    void deleteMovSuccess(int rdoId, Double totalMontoMovilidad);
}
