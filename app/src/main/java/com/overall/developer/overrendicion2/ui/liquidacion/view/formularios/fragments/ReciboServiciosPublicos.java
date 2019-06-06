package com.overall.developer.overrendicion2.ui.liquidacion.view.formularios.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.ReciboServiciosPublicosEntity;
import com.overall.developer.overrendicion2.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion2.ui.communicator.Communicator;
import com.overall.developer.overrendicion2.ui.communicator.OttoBus;
import com.overall.developer.overrendicion2.utils.Util;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;

public class ReciboServiciosPublicos extends Fragment
{

    //region Injeccion de Vistas
    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNSerie)
    CustomEditText etxNSerie;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.spnTipoServicio)
    NiceSpinner spnTipoServicio;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.txvFechaDocumento)
    TextView txvFechaDocumento;
    @BindView(R.id.lytArrow)
    LinearLayout lytArrow;
    @BindView(R.id.lytFecha)
    LinearLayout lytFecha;
    @BindView(R.id.calendarView)
    SectionCalendarView calendarView;
    @BindView(R.id.lytCalendar)
    LinearLayout lytCalendar;
    @BindView(R.id.view2)
    View view2;
    //@BindView(R.id.etxVencimiento)
    //CustomEditText etxVencimiento;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.txvVencimiento)
    TextView txvVencimiento;
    @BindView(R.id.lytArrowVen)
    LinearLayout lytArrowVen;
    @BindView(R.id.lytFechaVen)
    LinearLayout lytFechaVen;
    @BindView(R.id.calendarViewVen)
    SectionCalendarView calendarViewVen;
    @BindView(R.id.lytCalendarVen)
    LinearLayout lytCalendarVen;
    @BindView(R.id.view4)
    View view4;

    @BindView(R.id.etxValorVenta)
    CustomEditText etxValorVenta;
    @BindView(R.id.txvIgv)
    TextView txvIgv;
    @BindView(R.id.etxPrecioVenta)
    TextView etxPrecioVenta;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.etxImpNoAfectado)
    CustomEditText etxImpNoAfectado;
    @BindView(R.id.lytCodSuspencion)
    LinearLayout lytCodSuspencion;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    //endregion


    private SpinnerDialog spinnerDialogTipoGasto;
    private String rtgId, pathImage;

    private RendicionEntity rendicionEntity;
    private LiquidacionEntity liquidacionEntity;
    private TipoGastoEntity gastoEntity;

    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recibo_servicios_publicos, container, false);
        unbinder = ButterKnife.bind(this, mView);

        liquidacionEntity = ((FormularioActivity) getContext()).getLiquidacion();
        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();

        initialCalendar();
        initialCalendarVen();

        ArrayAdapter<String> adapterTipoServicio = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_servicio));
        spnTipoServicio.setAdapter(adapterTipoServicio);

        if (rendicionEntity != null) setAllDefaultValues(); //10-04 por Gustavo M. porque primero se debe llenar el spinner y luego seleccionar

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });


        etxValorVenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && etxValorVenta != null && !etxValorVenta.getText().toString().isEmpty()) sumaTotal();
        });

/*
        etxValorVenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && etxValorVenta != null && !etxValorVenta.getText().toString().isEmpty()) {
                txvIgv.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.18));
                etxPrecioVenta.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) + Double.valueOf(txvIgv.getText().toString())));
            }

        });


        etxImpNoAfectado.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                etxPrecioVenta.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) + Double.valueOf(txvIgv.getText().toString()) + Double.valueOf(etxImpNoAfectado.getText().toString())));
            }

        });
*/
        RxTextView.textChanges(etxRuc)
                .filter(etx -> (etx.length() > 0 && etx.length() != 11))
                .subscribe(etx -> etxRuc.setError(getResources().getString(R.string.validarRuc)));



/*        etxNSerie.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNSerie != null) etxNSerie.setText(String.valueOf(etxNSerie.getText()) + getResources().getString(R.string.autocomplete));

        });

        etxNDocumento.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNDocumento!= null)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });*/

        RxTextView.textChanges(etxImpNoAfectado).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());

        RxTextView.textChanges(etxValorVenta).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto, btnSearch);

        return mView;
    }

    private void sumaTotal() {
        Double neto, igv, otros;

        if (etxValorVenta != null) {

            if (etxValorVenta.getText().toString().isEmpty())etxValorVenta.setText("0");
            if(etxValorVenta.getText().toString().substring(0,1).equals(".")){
                etxValorVenta.setText("0"+etxValorVenta.getText());
                etxValorVenta.setSelection(etxValorVenta.length());
            }
            if(!etxImpNoAfectado.getText().toString().isEmpty() && etxImpNoAfectado.getText().toString().substring(0,1).equals(".")){
                etxImpNoAfectado.setText("0"+etxImpNoAfectado.getText());
                etxImpNoAfectado.setSelection(etxImpNoAfectado.length());
            }

            if (etxValorVenta.getText().toString().isEmpty())etxValorVenta.setText("0");
            neto = Double.valueOf(String.valueOf(etxValorVenta.getText().toString().isEmpty() ? 0 : etxValorVenta.getText().toString()));
            igv = Double.valueOf(etxValorVenta.getText().toString()) * 0.18;
            txvIgv.setText(String.valueOf(String.format("%.2f", igv)));
            otros = Double.valueOf(String.valueOf(etxImpNoAfectado.getText().toString().isEmpty() ? 0 : etxImpNoAfectado.getText().toString()));

            etxPrecioVenta.setText(String.valueOf(String.format("%.3f",neto + igv + otros)));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        OttoBus.getBus().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        OttoBus.getBus().unregister(this);
    }

    @Subscribe
    public void searchRucSuccess(Communicator razonSocial) {
        etxRazonSocial.setText(razonSocial.getRazonSocial());
        etxRazonSocial.setEnabled(false);
    }

    private void setAllDefaultValues() {
        gastoEntity = ((FormularioActivity) getContext()).getDefaultTipoGasto();
        String[] strings = rendicionEntity.getNumeroDoc().split("\\-");

        etxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        etxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        etxNSerie.setText(String.valueOf(strings[0]));
        etxNDocumento.setText(String.valueOf(strings[1]));
        txvFechaDocumento.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        if(!rendicionEntity.getFechaVencimiento().isEmpty())txvVencimiento.setText(String.valueOf(rendicionEntity.getFechaVencimiento()));
        etxValorVenta.setText(String.valueOf(rendicionEntity.getValorNeto()));
        etxPrecioVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        txvIgv.setText(String.valueOf(rendicionEntity.getIgv()));
        spnTipoGasto.setText(String.valueOf(gastoEntity.getRtgDes()));
        rtgId = String.valueOf(gastoEntity.getRtgId());
        etxImpNoAfectado.setText(String.valueOf(rendicionEntity.getOtroGasto()));
        //imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));

        //01-04 por Gustavo M. para que valide la ruta de la foto
        if(rendicionEntity.getFoto()!=null) {
            if (!rendicionEntity.getFoto().isEmpty()) {
                Picasso.get()
                        .load(rendicionEntity.getFoto())
                        .placeholder(R.drawable.ic_add_a_photo)
                        .error(R.drawable.ic_highlight_off)
                        .into(imgFoto);
                pathImage = rendicionEntity.getFoto();
            }
        }

        //01-04 por Gustavo M. para llenar el spinner
        int i = 0;
        if(rendicionEntity.getTipoServicio().equals("L")) i=0;
        if(rendicionEntity.getTipoServicio().equals("A")) i=1;
        if(rendicionEntity.getTipoServicio().equals("T")) i=2;
        spnTipoServicio.setSelectedIndex(i);

    }

    //region OnClick
    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar, R.id.btnSearch, R.id.lytFecha, R.id.lytFechaVen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnTipoGasto:
                spinnerDialogTipoGasto.showSpinerDialog();
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                if (ValideWidgets()) {
                    //String tipoMoneda = mSpnTipoDocumento.getSelectedIndex() == 0 ? "S" : "D";
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                    if(etxImpNoAfectado.getText().toString().isEmpty())etxImpNoAfectado.setText("0"); //26-04 por Gustavo M. para que este campo se completo con cero cuando esté vacío
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new ReciboServiciosPublicosEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                            String.valueOf(etxRuc.getText()), String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNSerie.getText()) + "-" + String.valueOf(etxNDocumento.getText()), String.valueOf(txvFechaDocumento.getText()), String.valueOf(txvVencimiento.getText()), String.valueOf(rtgId),  getIndexTipoServicio(),
                            String.valueOf(getResources().getString(R.string.IGV)), String.valueOf("1"), String.valueOf(etxImpNoAfectado.getText()), String.valueOf(etxPrecioVenta.getText()), String.valueOf(pathImage))); //10-04 por Gustavo M. para insertar en igv el 18%
                }
                break;
            case R.id.btnSearch:
                if (etxRuc.getText().toString().length() == 11) {
                    ((FormularioActivity) getContext()).searchRuch(String.valueOf(etxRuc.getText()));
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.validarRuc), Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.lytFecha:
                lytFecha.setVisibility(View.GONE);
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);
                calendarView.clearDate();
                break;

            case R.id.lytFechaVen:
                lytFechaVen.setVisibility(View.GONE);
                lytCalendarVen.setVisibility(lytCalendarVen.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrowVen.setRotation(lytArrowVen.getRotation() == 90 ? 0 : 90);
                calendarViewVen.clearDate();
                break;
        }
    }
    //endregion

    private boolean ValideWidgets() {
        if (etxRuc.getText().toString().isEmpty() || etxRuc.getText().toString().trim().length() != 11){
            Toast.makeText(mView.getContext(), "Ingrese RUC válido", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etxRazonSocial.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta Razón social", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etxNSerie.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta N. Serie", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etxNDocumento.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta N. Documento", Toast.LENGTH_SHORT).show();
            return false;
        }else if(txvFechaDocumento.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta Fecha Documento", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etxValorVenta.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta el Monto", Toast.LENGTH_SHORT).show();
            return false;
        }else if(spnTipoGasto.getText().equals("Seleccionar")){
            Toast.makeText(mView.getContext(), "Falta Tipo de gasto", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pathImage == null){
            Toast.makeText(mView.getContext(), "Falta la Foto", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
        /*
        if (etxNDocumento.getText().toString().isEmpty() || txvFechaDocumento.getText().toString().isEmpty() || etxValorVenta.getText().toString().isEmpty()
                || spnTipoGasto.getText().equals("Seleccionar") || etxImpNoAfectado.getText().toString().isEmpty() || pathImage == null) {
            return false;

        } else return true;
        */
    }

    //29-04* por Gustavo M. para obtener la primera sigla del tipo de servicio
    private String getIndexTipoServicio(){

        String iServicio="";
        switch (spnTipoServicio.getSelectedIndex()){

            case 0: iServicio= "L"; break;
            case 1: iServicio= "A"; break;
            case 2: iServicio= "T"; break;
        }
        return iServicio;
    }

    //region Calendar
    private void initialCalendar() {
        //txvFechaDocumento.setText(String.valueOf("-"));
        calendarView.setDateFormat("dd/MM/yyyy");
        calendarView.setPreventPreviousDate(false);
        //calendarView.setErrToastMessage(R.string.error_date);
        calendarView.setOnDaySelectedListener((startDay, endDay) ->
        {
            if (!startDay.isEmpty()) {
               /* if (Date.valueOf(Util.changeDateFormat(startDay)).after(Date.valueOf( Util.getChangeOrderDate(liquidacionEntity.getFechaInicioLiq().substring(0,10)))) &&
                        Date.valueOf(Util.changeDateFormat(startDay)).before(Date.valueOf(Util.getChangeOrderDate(liquidacionEntity.getFechaFinLiq().substring(0,10)))))*/

                try {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateNow = format.parse(Util.changeDateFormat(startDay));
                    Date dateBefore = format.parse(liquidacionEntity.getFechaDesde());
                    Date dateAfter = format.parse(liquidacionEntity.getFechaHasta());

                    if (!dateNow.before(dateBefore) && !dateNow.after(dateAfter))
                    {
                        txvFechaDocumento.setText(Util.changeDateFormat(startDay));
                        txvFechaDocumento.setTypeface(null, Typeface.BOLD);
                        txvFechaDocumento.setTextColor(getResources().getColor(R.color.black));
                        if (!startDay.equals("")) {
                            lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                            lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);
                            //etxValorVenta.requestFocus();
                            lytFecha.setVisibility(View.VISIBLE);
                        }

                    } else {
                        //Toast.makeText(getContext(), getResources().getString(R.string.errorDate) , Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(), getResources().getString(R.string.errorDate)+ " entre " + liquidacionEntity.getFechaDesde() + " y " + liquidacionEntity.getFechaHasta(), Toast.LENGTH_LONG).show();
                        calendarView.clearDate();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        calendarView.buildCalendar();
    }

    private void initialCalendarVen() {
        //txvFechaDocumento.setText(String.valueOf("-"));
        calendarViewVen.setDateFormat("dd/MM/yyyy");
        calendarViewVen.setPreventPreviousDate(false);
        //calendarView.setErrToastMessage(R.string.error_date);
        calendarViewVen.setOnDaySelectedListener((startDay, endDay) ->
        {
            if (!startDay.isEmpty()) {
               /* if (Date.valueOf(Util.changeDateFormat(startDay)).after(Date.valueOf( Util.getChangeOrderDate(liquidacionEntity.getFechaInicioLiq().substring(0,10)))) &&
                        Date.valueOf(Util.changeDateFormat(startDay)).before(Date.valueOf(Util.getChangeOrderDate(liquidacionEntity.getFechaFinLiq().substring(0,10)))))*/

                        txvVencimiento.setText(Util.changeDateFormat(startDay));
                        txvVencimiento.setTypeface(null, Typeface.BOLD);
                        txvVencimiento.setTextColor(getResources().getColor(R.color.black));
                        if (!startDay.equals("")) {
                            lytCalendarVen.setVisibility(lytCalendarVen.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                            lytArrowVen.setRotation(lytArrowVen.getRotation() == 90 ? 0 : 90);
                            //etxValorVenta.requestFocus();
                            lytFechaVen.setVisibility(View.VISIBLE);
                        }

            }

        });
        calendarViewVen.buildCalendar();
    }
    //endregion

    //region Foto

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (100): {
                if (resultCode == Activity.RESULT_OK) {
                    File imageFile = new File(data.getStringArrayListExtra(Pix.IMAGE_RESULTS).get(0));

                    new Compressor(mView.getContext())
                            .setQuality(95)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .compressToFileAsFlowable(imageFile)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(file ->
                            {
                                imgFoto.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                                pathImage = file.getAbsolutePath();

                            }, throwable ->
                            {
                                Log.i("ErrorCompressImage", throwable.getMessage());
                            });

                }
            }
            break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(this, 100, getResources().getInteger(R.integer.numFotos));
                } else {
                    Toast.makeText(mView.getContext(), "Tiene que aprobar los permisos para seleccionar una Foto", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }

    //endregion
}
