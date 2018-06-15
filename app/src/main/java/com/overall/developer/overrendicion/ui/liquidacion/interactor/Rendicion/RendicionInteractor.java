package com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion;



import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface RendicionInteractor
{
    List<RendicionEntity> listRendicion();

    void deleteRendicionForCod(int position);

    void changeStatusLiquidacion();
}
