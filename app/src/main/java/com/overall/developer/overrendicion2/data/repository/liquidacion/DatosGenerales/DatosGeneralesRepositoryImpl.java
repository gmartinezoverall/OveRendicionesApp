package com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales;


import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.request.UpdateLiquidationRequest;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.api.ApiDatosGenerales;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.api.ApiDatosGeneralesImpl;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.db.DBDatosGenerales;
import com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.db.DBDatosGeneralesImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.interactor.DatosGenerales.DatosGeneralesInteractor;

import java.util.List;

public class DatosGeneralesRepositoryImpl implements DatosGeneralesRepository
{
    private DatosGeneralesInteractor mInteractor;
    private ApiDatosGenerales mApiDatosGenerales;
    private DBDatosGenerales mDBDatosGenerales;

    public DatosGeneralesRepositoryImpl(DatosGeneralesInteractor interactor)
    {
        this.mInteractor = interactor;
        mApiDatosGenerales = new ApiDatosGeneralesImpl(this);
        mDBDatosGenerales = new DBDatosGeneralesImpl(this);

    }

    @Override
    public LiquidacionBean getForCodLiquidacion(String codLiquidacion)
    {
        return mDBDatosGenerales.getForCodLiquidacionDB(codLiquidacion);

    }

    @Override
    public List<ProvinciaBean> listProvinciaForSpinner() {
        return mDBDatosGenerales.listProvinciaForSpinnerDB();
    }

    @Override
    public UserBean getUser() {
        return mDBDatosGenerales.getUser();
    }

    @Override
    public void updateLiquidacionDB(LiquidacionBean liquidacionBean)
    {
        mDBDatosGenerales.updateLiquidacionDB(liquidacionBean);
    }

    @Override
    public void sendUpdateLiquidacionAPI(UpdateLiquidationRequest request)
    {
        mApiDatosGenerales.sendUpdateLiquidacionAPI(request);
    }

    @Override
    public boolean existsRendicion() {
        return mDBDatosGenerales.existsRendicionDB();
    }

    @Override
    public void changeStatusLiquidacion()
    {
        mDBDatosGenerales.changeStatusLiquidacion();
    }

    @Override
    public ProvinciaBean getProvinciaDB(String idProvincia)
    {
        return mDBDatosGenerales.getProvinciaDB(idProvincia);
    }

    @Override
    public void updateSuccess(String message)
    {
        mInteractor.updateSuccess(message);
    }

    @Override
    public void updateError(String message)
    {
        mInteractor.updateError(message);
    }

    @Override
    public void finisLogin() {
        mDBDatosGenerales.finisLogin();
    }
}
