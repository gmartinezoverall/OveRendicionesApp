package com.overall.developer.overrendicion.ui.liquidacion.interactor.Formularios;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;


import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.model.bean.BancoBean;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.MovilidadBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.MovilidadEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.BoletaVentaEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.FacturaEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.VoucherBancarioEntity;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepository;
import com.overall.developer.overrendicion.data.repository.Formularios.FormularioRepositoryImpl;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios.FormularioPresenter;
import com.overall.developer.overrendicion.utils.Util;
import com.overall.developer.overrendicion.utils.aws.AwsUtility;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FormularioInteractorImpl implements FormularioInteractor
{
    FormularioPresenter mPresenter;
    FormularioRepository mRepository;
    Context mContext;

    public FormularioInteractorImpl(FormularioPresenter presenter, Context context)
    {
        this.mPresenter = presenter;
        mRepository = new FormularioRepositoryImpl(this);
        mContext = context;

    }

    @Override
    public List<TipoGastoEntity> getDocumentForId(String idDocumento)
    {
        List<TipoGastoEntity> mList = new ArrayList<>();

        for (TipoDocumentoBean bean : mRepository.getDocumentForIdDB(idDocumento))
        {
            mList.add(new TipoGastoEntity(bean.getRtgId(),bean.getRtgDes()));
        }

        return  mList;
    }


    @Override
    public List<String> getProvinciaDestinoList()
    {
        List<String> mList = new ArrayList<>();

        for (ProvinciaBean bean : mRepository.getProvinciaDestinoList()) mList.add(bean.getDesc());

        return mList;
    }

    @Override
    public void saveData(List<String> typeFragment, Object dinamyObj)
    {
        String codLiqui = mRepository.getCodLiquidacionDB().getCodLiquidacion();

        RendicionEntity entity =  filterFragment(Integer.valueOf(typeFragment.get(0)), dinamyObj);//jugada para traer el Texto y el id del tipo de fragment

        if (typeFragment.size() > 2)entity.setIdRendicion(Integer.valueOf(typeFragment.get(2)));
        entity.setCodRendicion(entity.getIdRendicion() == null ? "-" : mRepository.getCodRendicion(entity.getIdRendicion()));
        entity.setCodLiquidacion(codLiqui);
        entity.setIdUsuario(mRepository.getIdUsuarioDB());
        Log.i("NDaImage", entity.getFoto());
        String pathTemp = entity.getFoto();

        entity.setFoto(Util.SaveImage(entity.getFoto()));//se guardo la foto en el archivo overRendicion
        Log.i("NDaImage", entity.getFoto());
        File file = new File(pathTemp);
        file.delete();

        RendicionBean bean = new RendicionBean(entity.getIdRendicion(), entity.getCodRendicion(), entity.getRdoId(), typeFragment.get(1), entity.getCodLiquidacion(), entity.getIdUsuario(),
                entity.getNumeroDoc(), entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getValorNeto(), entity.getPrecioTotal(), entity.getObservacion(),
                entity.getFechaDocumento(), entity.getFechaVencimiento(), entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(),
                entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(), entity.getCodSuspencionH(), entity.getTipoMoneda(),
                entity.getTipoCambio(), entity.getFoto(), false);

        Integer idRendicion = mRepository.saveDataDB(bean);


        if (Util.isOnline())
        {
            AwsUtility.UploadTransferUtilityS3(mContext,entity.getFoto());
            entity.setFoto(BuildConfig.URL_AWS + entity.getFoto().substring(34));//se genera la URL de AWS para enviarlo por el WS

            if (entity.getCodRendicion().equals("-"))
            {
                entity.setCodRendicion("");
                RendicionRequest request = new RendicionRequest(entity.getCodRendicion(), entity.getRdoId(), entity.getCodLiquidacion(), entity.getIdUsuario(), entity.getNumeroDoc(),
                        entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getPrecioTotal(), entity.getObservacion(), entity.getFechaDocumento(), entity.getFechaVencimiento(),
                        entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(), entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(),
                        entity.getCodSuspencionH(), entity.getTipoMoneda(), entity.getTipoCambio(), entity.getFoto());
                mRepository.sendDataForInsertApi(request, idRendicion);
            }
            else
            {
                RendicionRequest request = new RendicionRequest(entity.getCodRendicion(), entity.getRdoId(), entity.getCodLiquidacion(), entity.getIdUsuario(), entity.getNumeroDoc(),
                        entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getPrecioTotal(), entity.getObservacion(), entity.getFechaDocumento(), entity.getFechaVencimiento(),
                        entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(), entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(),
                        entity.getCodSuspencionH(), entity.getTipoMoneda(), entity.getTipoCambio(), entity.getFoto());
                mRepository.sendDataForUpdateApi(request, idRendicion);
            }
        }
        mPresenter.saveDataSuccess();
    }

    @Override
    public List<String> getDefaultValues()
    {
        LiquidacionBean bean = mRepository.getCodLiquidacionDB();
        List<String> mList = new ArrayList<>();
        mList.add(String.valueOf(bean.getaCuenta()));
        mList.add(String.valueOf(bean.getMonto()));
        mList.add(String.valueOf(bean.getSaldo()));
        return mList;
    }

    @Override
    public RendicionEntity setRendicionForEdit(String idRendicion)
    {
        RendicionBean bean = mRepository.setRendicionForEditDB(Integer.parseInt(idRendicion));

        RendicionEntity entity = new RendicionEntity(bean.getIdRendicion(), bean.getCodRendicion(), bean.getRdoId(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getValorNeto(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(), bean.getFechaVencimiento(),
                bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(), bean.getCodDestino(), bean.getAfectoRetencion(),
                bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.getFoto(), bean.isSend());

        return entity;
    }


    @Override
    public MovilidadEntity setMovilidadForEdit(int idMov)
    {
        MovilidadBean bean = mRepository.setMovilidadForEditDB(idMov);

        MovilidadEntity entity = new MovilidadEntity(bean.getId(), bean.getIdMovilidad(), bean.getCodRendicion(), bean.getRdoId(), bean.getRtgId(), bean.getPrecioTotal(), bean.getFechaRendicion(),
                bean.getEstado(), bean.getDestinoMovilidad(), bean.getMontoMovilidad(), bean.getMotivoMovilidad(), bean.getBeneficiario(), bean.getFechaDesde(), bean.getFechaHasta(),
                bean.getNumBeneficiario());

        return entity;

    }

    @Override
    public UserBean getUser() {
        return mRepository.getUserDB();
    }

    @Override
    public void finisLogin() {
        mRepository.finisLoginDB();
    }

    @Override
    public String getCodLiquidacion() {
        return  mRepository.getCodLiquidacionDB().getCodLiquidacion();
    }

    @Override
    public List<BancoEntity> getAllBancos()
    {
        List<BancoEntity> mList = new ArrayList<>();

        for (BancoBean bean : mRepository.getAllBancos()) mList.add(new BancoEntity(bean.getCode(), bean.getDesc()));

        return mList;

    }

    @Override
    public TipoGastoEntity getDefaultTipoGasto(String rtgId)
    {
        TipoDocumentoBean bean = mRepository.getDefaultTipoGastoDB(rtgId);
        TipoGastoEntity entity = new TipoGastoEntity(bean.getRtgId(), bean.getRtgDes());
        return entity;
    }

    @Override
    public BancoEntity getDefaultBanco(String bcoCod)
    {
        BancoBean bean = mRepository.getDefaultBancoDB(bcoCod);
        BancoEntity entity = new BancoEntity(bean.getCode(), bean.getDesc());
        return entity;
    }

    private RendicionEntity filterFragment(int typeFragment, Object dinamyObj)
    {
        RendicionEntity entity = new RendicionEntity();
        switch (typeFragment)
        {
            case 1:
                entity = new FacturaEntity().getEntity(dinamyObj);
                break;

            case 2:
                entity = new BoletaVentaEntity().getEntity(dinamyObj);
                break;
            case 17:
                entity = new VoucherBancarioEntity().getEntity(dinamyObj);
                break;


        }
        return entity;

    }
}
