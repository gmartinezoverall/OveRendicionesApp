package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface RendicionView
{

    void getListRendicion(List<RendicionEntity> rendicionList);

    void updateListRendicion(List<RendicionEntity> rendicionBeans);
}
