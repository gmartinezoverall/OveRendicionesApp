package com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface RendicionPresenter
{
    List<RendicionEntity> listRendicion();

    void deleteRendicionForCod(int position);

}
