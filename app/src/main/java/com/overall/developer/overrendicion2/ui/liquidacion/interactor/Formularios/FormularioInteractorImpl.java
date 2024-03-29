package com.overall.developer.overrendicion2.ui.liquidacion.interactor.Formularios;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.data.model.bean.BancoBean;
import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion2.data.model.bean.RendicionDetalleBean;
import com.overall.developer.overrendicion2.data.model.bean.TipoDocumentoBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;
import com.overall.developer.overrendicion2.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.ArrendamientoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.BoletaVentaEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.BoletoAereoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.BoletoTerrestreEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.CartaPorteAereoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.FacturaEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.MovilidadRendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.OtrosDocumentosEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.ReciboHonorariosEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.ReciboServiciosPublicosEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.SinSustentoTributarioEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.TicketMaquinaRegistradoraEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.VoucherBancarioEntity;
import com.overall.developer.overrendicion2.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion2.data.model.request.RendicionRequest;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.FormularioRepository;
import com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.FormularioRepositoryImpl;
import com.overall.developer.overrendicion2.ui.liquidacion.presenter.Formularios.FormularioPresenter;
import com.overall.developer.overrendicion2.utils.Util;
import com.overall.developer.overrendicion2.utils.aws.AwsUtility;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public List<ProvinciaEntity> getProvinciaDestinoList()
    {
        List<ProvinciaEntity> mList = new ArrayList<>();

        for (ProvinciaBean bean : mRepository.getProvinciaDestinoList()) mList.add(new ProvinciaEntity(bean.getCode(), bean.getDesc()));

        return mList;
    }

    @Override
    public void saveData(List<String> typeFragment, Object dinamyObj)
    {
        String codLiqui = mRepository.getCodLiquidacionDB().getCodLiquidacion();

        RendicionEntity entity =  filterFragment(Integer.valueOf(typeFragment.get(0)), dinamyObj);//jugada para traer el Texto y el id del tipo de fragment *-> devuelve la entidad a insertar

        if (typeFragment.size() > 2)entity.setIdRendicion(Integer.valueOf(typeFragment.get(2)));
        entity.setCodRendicion(entity.getIdRendicion() == null ? "-" : mRepository.getCodRendicion(entity.getIdRendicion()));
        entity.setCodLiquidacion(codLiqui);
        entity.setIdUsuario(mRepository.getIdUsuarioDB());
        entity.setPrecioTotal(String.valueOf(Double.valueOf(entity.getPrecioTotal()) - Double.valueOf(entity.getOtroGasto())));

        //21-03 por Gustavo M. para validar las rendiciones que sí usan el tipo de moneda
        //if (entity.getTipoMoneda().equals("D"))entity.setTipoCambio(mRepository.getTipoCambioDB());
        if(entity.getTipoMoneda()!=null) {
            if (entity.getTipoMoneda().equals("D"))
                entity.setTipoCambio(mRepository.getTipoCambioDB());
        }
/*
        RendicionBean bean = new RendicionBean(entity.getIdRendicion(), entity.getCodRendicion(), entity.getRdoId(), typeFragment.get(1), entity.getCodLiquidacion(), entity.getIdUsuario(),
                entity.getNumeroDoc(), entity.getBienServicio(), entity.getIgv(), entity.getAfectoIgv(), entity.getValorNeto(), entity.getPrecioTotal(), entity.getObservacion(),
                entity.getFechaDocumento(), entity.getFechaVencimiento(), entity.getRuc(), entity.getRazonSocial(), entity.getBcoCod(), entity.getTipoServicio(),
                entity.getRtgId(), entity.getOtroGasto(), entity.getCodDestino(), entity.getAfectoRetencion(), entity.getCodSuspencionH(), entity.getTipoMoneda(),
                entity.getTipoCambio(), entity.getFoto(), false);

        Integer idRendicion = mRepository.saveDataDB(bean);
*/
        Integer idRendicion =0;
        if (Util.isOnline())
        {
            if(entity.getFoto()!=null) {
                if (!entity.getFoto().isEmpty()) {
                    if (!entity.getFoto().substring(0, 4).equals("http")) {
                        Log.i("NDaImage", entity.getFoto());
                        String pathTemp = entity.getFoto();

                        entity.setFoto(Util.SaveImage(entity.getFoto()));//se guardo la foto en el archivo overRendicion //Se genera el nombre con la fecha
                        Log.i("NDaImage", entity.getFoto());
                        File file = new File(pathTemp);
                        file.delete();

                        AwsUtility.UploadTransferUtilityS3(mContext, entity.getFoto());
                        entity.setFoto(BuildConfig.URL_AWS + entity.getFoto().substring(34));//se genera la URL de AWS para enviarlo por el WS
                    }
                }
            }

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

            mPresenter.saveDataSuccess();

        }else
        {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.errorInternet), Toast.LENGTH_LONG).show();
            mPresenter.searchRucError();
        }
        //updateLiquidacion(bean);

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
    public RendicionDetalleEntity setMovilidadForEdit(int idMov)
    {
        RendicionDetalleBean bean = mRepository.setMovilidadForEditDB(idMov);

        RendicionDetalleEntity entity = new RendicionDetalleEntity(bean.getId(), bean.getIdMovilidad(), bean.getCodRendicion(), bean.getRdoId(), bean.getRtgId(), bean.getPrecioTotal(), bean.getFechaRendicion(),
                bean.getEstado(), bean.getDestinoMovilidad(), bean.getMontoMovilidad(), bean.getMotivoMovilidad(), bean.getBeneficiario(), bean.getFechaDesde(), bean.getFechaHasta(),
                bean.getNumBeneficiario(), bean.getDni(), bean.getDatosTrabajador(), bean.getFoto());

        return entity;

    }

    @Override
    public void saveDataMovilidad(MovilidadRendicionEntity rendicionEntity, MovilidadEntity movilidadEntity)
    {
        if (Util.isOnline())
        {
            if (movilidadEntity.getIdMovilidad().equals("-"))
            {
                MovilidadInsertRequest movilidadInsertRequest = new MovilidadInsertRequest(movilidadEntity.getRdoId(), getCodLiquidacion(), getUser().getIdUsuario(),
                        movilidadEntity.getMotivo(), movilidadEntity.getDestino(), movilidadEntity.getMonto(), movilidadEntity.getFechaDocumento(), movilidadEntity.getRtgId(), movilidadEntity.getTipoMov(),
                        movilidadEntity.getFecha(), movilidadEntity.getFechaHastaM());
                mRepository.sendDataInsertMovilidadApi(movilidadInsertRequest);

            }
            else
            {
                MovilidadUpdateRequest updateRequest = new MovilidadUpdateRequest(movilidadEntity.getIdMovilidad(), movilidadEntity.getFecha(), movilidadEntity.getMotivo(),
                        movilidadEntity.getDestino(), movilidadEntity.getMonto(), movilidadEntity.getDniTrabajador(), movilidadEntity.getDatosTrabajador(), movilidadEntity.getFoto());
                mRepository.sendDataUpdateMovilidadApi(updateRequest);

            }

            mPresenter.saveDataSuccess();

        }else
        {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.errorInternet), Toast.LENGTH_LONG).show();
            mPresenter.searchRucError();
/*
            RendicionBean rendicionBean = mRepository.getDetailMovOffLineDB();
            if (rendicionBean == null)//nueva movilidad
            {
                rendicionBean = new RendicionBean(0, "-", "10", "MOVILIDAD INDIVIDUAL - HOJA RUTA", getCodLiquidacion(), getUser().getIdUsuario(),
                        "-", "-", "-", "-", rendicionEntity.getPrecioTotal(), rendicionEntity.getPrecioTotal(), "-",
                        getLiquidacion().getFechaDesde(), getLiquidacion().getFechaHasta(), "-", "-", "-", "-",
                        rendicionEntity.getTipoGasto(), "-", "-", "-", "-", "S",
                        "-", rendicionEntity.getFoto(), false);

            }

            if (movilidadEntity.getTipoMov().equals("I"))
            {
                insertMovilidad(rendicionBean,movilidadEntity);

            }else
            {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date dateBefore = format.parse(movilidadEntity.getFecha());
                    Date dateAfter = format.parse(movilidadEntity.getFechaHastaM());

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(dateBefore);

                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(dateAfter);

                    while (calendar.before(endCalendar)) {
                        movilidadEntity.setFechaDocumento(String.valueOf(format.format(calendar.getTime())));
                        movilidadEntity.setTipoMov("I");
                        insertMovilidad(rendicionBean,movilidadEntity);
                        calendar.add(Calendar.DATE, 1);
                    }
                    movilidadEntity.setFechaDocumento(movilidadEntity.getFechaHastaM());
                    movilidadEntity.setTipoMov("I");
                    insertMovilidad(rendicionBean,movilidadEntity);
                } catch (ParseException e) {
                    e.printStackTrace();
                }




            }
            updateLiquidacion(rendicionBean);
*/
        }



    }

    private void insertMovilidad( RendicionBean rendicionBean, MovilidadEntity movilidadEntity)
    {
        RendicionDetalleBean detalleBean =  new RendicionDetalleBean(getCodLiquidacion(), movilidadEntity.getRdoId(), movilidadEntity.getTipoMov(),
                movilidadEntity.getRtgId(), movilidadEntity.getMonto(), movilidadEntity.getFechaDocumento(), "P", movilidadEntity.getFecha(),
                movilidadEntity.getDestino(), movilidadEntity.getMonto(), movilidadEntity.getMotivo(), getUser().getNombre(), movilidadEntity.getFecha(),
                movilidadEntity.getFechaHastaM(), getUser().getNumDocBeneficiario(), movilidadEntity.getDniTrabajador(), movilidadEntity.getDatosTrabajador(),"-", false);

        mRepository.insertMovilidadDB(rendicionBean, detalleBean);
    }

    @Override
    public void searchRuc(String ruc)
    {
        if (Util.isOnline())mRepository.searchRucApi(ruc);
        else
        {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.errorInternet), Toast.LENGTH_LONG).show();
            mPresenter.searchRucError();
        }


    }

    @Override
    public void searchRucSuccess(String razonSocial)
    {
        mPresenter.searchRucSuccess(razonSocial);

    }

    @Override
    public void searchRucError()
    {
        Toast.makeText(mContext, mContext.getResources().getString(R.string.rucError), Toast.LENGTH_LONG).show();
        mPresenter.searchRucError();
    }

    @Override
    public void saveDataMovilidadMultiple(MovilidadRendicionEntity entity, MovilidadEntity movilidadEntity)
    {

        if (Util.isOnline())
        {
            //29-03 por Gustavo M. para validar si la imagen se cambió
            if (!movilidadEntity.getFoto().substring(0, 4).equals("http")) {
                Log.i("NDaImage", entity.getFoto());
                String pathTemp = entity.getFoto();

                entity.setFoto(Util.SaveImage(entity.getFoto()));//se guardo la foto en el archivo overRendicion //Se genera el nombre con la fecha
                Log.i("NDaImage", entity.getFoto());
                File file = new File(pathTemp);
                file.delete();

                AwsUtility.UploadTransferUtilityS3(mContext, movilidadEntity.getFoto());
                movilidadEntity.setFoto(BuildConfig.URL_AWS + movilidadEntity.getFoto().substring(34));//se genera la URL de AWS para enviarlo por el WS
            }

            if (movilidadEntity.getIdMovilidad().equals("-")) {
                MovilidadMultipleRequest movilidadMultipleRequest = new MovilidadMultipleRequest(movilidadEntity.getRdoId(), getCodLiquidacion(), getUser().getIdUsuario(),
                        movilidadEntity.getMotivo(), movilidadEntity.getDestino(), movilidadEntity.getMonto(), String.valueOf(Util.getCurrentDate()), movilidadEntity.getRtgId(), movilidadEntity.getTipoMov(),
                        movilidadEntity.getFecha(), movilidadEntity.getDniTrabajador(), movilidadEntity.getDatosTrabajador(), movilidadEntity.getFoto());
                mRepository.sendDataInsertMovilidadMultipleApi(movilidadMultipleRequest);
            }else
            {
                MovilidadUpdateRequest updateRequest = new MovilidadUpdateRequest(movilidadEntity.getIdMovilidad(), movilidadEntity.getFecha(), movilidadEntity.getMotivo(),
                        movilidadEntity.getDestino(), movilidadEntity.getMonto(), movilidadEntity.getDniTrabajador(), movilidadEntity.getDatosTrabajador(), movilidadEntity.getFoto());
                mRepository.sendDataUpdateMovilidadApi(updateRequest);

            }

            mPresenter.saveDataSuccess();

        }else
        {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.errorInternet), Toast.LENGTH_LONG).show();
            mPresenter.searchRucError();
/*
            RendicionBean rendicionBean = mRepository.getDetailMovOffLineDB();
            if (rendicionBean == null)//nueva movilidad
            {
                rendicionBean = new RendicionBean(0, "-", "19", "VARIOS TRABAJADORES-  MOVILIDAD MULTIPLE", getCodLiquidacion(), getUser().getIdUsuario(),
                        "-", "-", "-", "-", entity.getPrecioTotal(), entity.getPrecioTotal(), "-",
                        getLiquidacion().getFechaDesde(), getLiquidacion().getFechaHasta(), "-", "-", "-", "-",
                        entity.getTipoGasto(), "-", "-", "-", "-", "S",
                        "-", entity.getFoto(), false);

            }


            RendicionDetalleBean detalleBean = new RendicionDetalleBean(getCodLiquidacion(),  movilidadEntity.getRdoId() ,  "I", movilidadEntity.getRtgId(), movilidadEntity.getMonto(), movilidadEntity.getFechaDocumento(),
                    "P", movilidadEntity.getFechaDocumento(), movilidadEntity.getDestino(), movilidadEntity.getMonto(), movilidadEntity.getMotivo(), getUser().getNombre(),
                    getLiquidacion().getFechaDesde(), getLiquidacion().getFechaHasta(), getUser().getNumDocBeneficiario(), movilidadEntity.getDniTrabajador(), movilidadEntity.getDatosTrabajador(), movilidadEntity.getFoto(), false);

            mRepository.insertMovilidadDB(rendicionBean, detalleBean);
            updateLiquidacion(rendicionBean);
*/
        }


    }

    @Override
    public void setTipoCambio()
    {
        mRepository.setTipoCambioApi(String.valueOf(Util.getCurrentDate()));
    }

    @Override
    public LiquidacionEntity getLiquidacion()
    {
        LiquidacionEntity liquidacionEntity = new LiquidacionEntity(mRepository.getLiquidacionDB());
        return liquidacionEntity;
    }

    @Override
    public void errorTipoCambio()
    {
        //Toast.makeText(mContext, mContext.getResources().getString(R.string.servidorError), Toast.LENGTH_LONG).show();
    }

    @Override
    public Boolean validateMontoMovilidad(Double sueldo, String fechaViaje, String fechaFin)
    {
        Double sueldoMin = Double.valueOf(mRepository.getOtrosBeanDB().getSueldo())* 0.04;
        List<Date> datesInRange = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startday = sdf.parse(fechaViaje);
            Date endday = sdf.parse(fechaFin);

            datesInRange = new ArrayList<>();
            Calendar calendar =  Calendar.getInstance();
            calendar.setTime(startday);

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endday);

            while (calendar.before(endCalendar)) {
                Date result = calendar.getTime();
                datesInRange.add(result);
                calendar.add(Calendar.DATE, 1);
            }
            datesInRange.add(endCalendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (mRepository.getSumaAcuentaDB(fechaViaje) + sueldo > sueldoMin && fechaFin.equals("-")) return true;
        for (int i = 0; i < datesInRange.size(); i++)
        {
            if (mRepository.getSumaAcuentaDB(String.valueOf(sdf.format(datesInRange.get(i))))+ sueldo > sueldoMin) return true;
        }
        return false;
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
    public ProvinciaEntity getDefaultProvicia(String codDestino)
    {
        ProvinciaBean bean = mRepository.getDefaultProviciaDB(codDestino);
        ProvinciaEntity entity = new ProvinciaEntity(bean.getCode(), bean.getDesc());
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
            case 5:
                entity = new ReciboHonorariosEntity().getEntity(dinamyObj);
                break;
            case 8:
                entity = new BoletoAereoEntity().getEntity(dinamyObj);
                break;
            case 9:
                entity = new BoletoTerrestreEntity().getEntity(dinamyObj);
                break;
            case 11:
                entity = new ReciboServiciosPublicosEntity().getEntity(dinamyObj);
                break;
            case 12:
                entity = new SinSustentoTributarioEntity().getEntity(dinamyObj);
                break;
            case 13:
                entity = new OtrosDocumentosEntity().getEntity(dinamyObj);
                break;
            case 14:
                entity = new ArrendamientoEntity().getEntity(dinamyObj);
                break;
            case 15:
                entity = new TicketMaquinaRegistradoraEntity().getEntity(dinamyObj);
                break;
            case 17:
                entity = new VoucherBancarioEntity().getEntity(dinamyObj);
                break;
            case 21:
                entity = new CartaPorteAereoEntity().getEntity(dinamyObj);
                break;

        }
        return entity;

    }


    private void updateLiquidacion(RendicionBean bean)
    {
        mRepository.updateLiquidacionDB(bean);
    }
}
