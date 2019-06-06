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
import android.widget.CheckBox;
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
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.BoletoAereoEntity;
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

public class BoletoAereoFragment extends Fragment
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
    @BindView(R.id.spnDestinoViaje)
    TextView spnDestinoViaje;
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
    @BindView(R.id.spnTipoMoneda)
    NiceSpinner spnTipoMoneda;
    @BindView(R.id.etxValorVenta)
    CustomEditText etxValorVenta;
    @BindView(R.id.chkAfectoIgv)
    CheckBox chkAfectoIgv;
    @BindView(R.id.txvMontoIGV)
    TextView txvMontoIGV;
    @BindView(R.id.etxPrecioVenta)
    TextView etxPrecioVenta;
    @BindView(R.id.etxOtrosGastos)
    CustomEditText etxOtrosGastos;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
     //endregion


    private SpinnerDialog spinnerDialogProv, spinnerDialogTipoGasto;
    private String rtgId, idProvincia;
    private RendicionEntity rendicionEntity;
    private LiquidacionEntity liquidacionEntity;
    private TipoGastoEntity gastoEntity;
    private ProvinciaEntity provinciaEntity;
    private String pathImage;


    Unbinder unbinder;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_boleto_aereo, container, false);
        unbinder = ButterKnife.bind(this, mView);

        liquidacionEntity = ((FormularioActivity) getContext()).getLiquidacion();
        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();

        initialCalendar();

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);

        if (rendicionEntity != null) setAllDefaultValues(); //09-04 por Gustavo M. porque primero se debe llenar el spinner y luego seleccionar

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListProvinciaDestino());
        spinnerDialogProv = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialogProv.bindOnSpinerListener((item, position) ->
        {
            spnDestinoViaje.setText(((ProvinciaEntity) item).getDesc());
            idProvincia = ((ProvinciaEntity) item).getCode();
        });

        ArrayList<Object> itemList2 = new ArrayList<>();
        itemList2.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList2, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes());
            rtgId = ((TipoGastoEntity) item).getRtgId();
        });


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

        RxTextView.textChanges(etxOtrosGastos).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());
        RxTextView.textChanges(etxValorVenta).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnDestinoViaje, spnTipoGasto, btnSearch);

        return mView;
    }

    //region Estados de Fragment
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
    //endregion

    @Subscribe
    public void searchRucSuccess(Communicator razonSocial) {
        etxRazonSocial.setText(razonSocial.getRazonSocial());
        etxRazonSocial.setEnabled(false);
    }

    private void sumaTotal() {
        Double neto, igv, otros;

        if (etxValorVenta != null) {

            //22-03 por Gustavo M. para complementar con 0 cuando ingresa primero un '.'
            if (etxValorVenta.getText().toString().isEmpty())etxValorVenta.setText("0");
            if(etxValorVenta.getText().toString().substring(0,1).equals(".")){
                etxValorVenta.setText("0"+etxValorVenta.getText());
                etxValorVenta.setSelection(etxValorVenta.length());
            }
            if(!etxOtrosGastos.getText().toString().isEmpty() && etxOtrosGastos.getText().toString().substring(0,1).equals(".")){
                etxOtrosGastos.setText("0"+etxOtrosGastos.getText());
                etxOtrosGastos.setSelection(etxOtrosGastos.length());
            }

            if (etxValorVenta.getText().toString().isEmpty())etxValorVenta.setText("0");
            neto = Double.valueOf(String.valueOf(etxValorVenta.getText().toString().isEmpty() ? 0 : etxValorVenta.getText().toString()));
            igv = Double.valueOf(chkAfectoIgv.isChecked() ? String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.18) : "0.00");
            txvMontoIGV.setText(String.valueOf(String.format("%.2f", igv)));
            otros = Double.valueOf(String.valueOf(etxOtrosGastos.getText().toString().isEmpty() ? 0 : etxOtrosGastos.getText().toString()));

            etxPrecioVenta.setText(String.valueOf(String.format("%.2f",neto + igv + otros)));

        }
    }

    private void setAllDefaultValues() {
        gastoEntity = ((FormularioActivity) getContext()).getDefaultTipoGasto();
        provinciaEntity = ((FormularioActivity) getContext()).getDefaultProvincia();

        String[] strings = rendicionEntity.getNumeroDoc().split("\\-");

        etxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        etxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        etxNSerie.setText(String.valueOf(strings[0]));
        etxNDocumento.setText(String.valueOf(strings[1]));
        spnDestinoViaje.setText(String.valueOf(provinciaEntity.getDesc()));
        idProvincia = String.valueOf(provinciaEntity.getCode());
        txvFechaDocumento.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        spnTipoMoneda.setSelectedIndex((rendicionEntity.getTipoMoneda().equals("S") ? 0 : 1));
        etxValorVenta.setText(String.valueOf(rendicionEntity.getValorNeto()));
        if (rendicionEntity.getAfectoIgv().equals("1")) chkAfectoIgv.setChecked(true);
        txvMontoIGV.setText(String.valueOf(rendicionEntity.getIgv()));
        etxPrecioVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        etxOtrosGastos.setText(String.valueOf(rendicionEntity.getOtroGasto()));
        spnTipoGasto.setText(String.valueOf(gastoEntity.getRtgDes()));
        rtgId = String.valueOf(gastoEntity.getRtgId());
        //imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));

        //25-03 por Gustavo M. para que valide la ruta de la foto
        if(rendicionEntity.getFoto()!=null) {
            if(!rendicionEntity.getFoto().isEmpty()) {
                Picasso.get()
                        .load(rendicionEntity.getFoto())
                        .placeholder(R.drawable.ic_add_a_photo)
                        .error(R.drawable.ic_highlight_off)
                        .into(imgFoto);
                pathImage = rendicionEntity.getFoto();
            }
        }
    }

    @OnClick({R.id.chkAfectoIgv, R.id.btnFoto, R.id.btnGuardar, R.id.spnDestinoViaje, R.id.spnTipoGasto, R.id.btnSearch, R.id.lytFecha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chkAfectoIgv:
                if (!etxValorVenta.getText().toString().isEmpty()) {
                    sumaTotal();
                } else {
                    Toast.makeText(mView.getContext(), "Debe ingresar Valor de Venta", Toast.LENGTH_LONG).show();
                    chkAfectoIgv.setChecked(false);
                }
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                if (ValideWidgets()) {
                    String tipoMoneda = spnTipoMoneda.getSelectedIndex() == 0 ? "S" : "D";
                    if(etxOtrosGastos.getText().toString().isEmpty())etxOtrosGastos.setText("0"); //25-03 por Gustavo M. para asignar 0 cuando este campo esté vacío
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new BoletoAereoEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxRuc.getText()),
                            String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNSerie.getText()) + "-" + String.valueOf(etxNDocumento.getText()), String.valueOf(idProvincia), String.valueOf(txvFechaDocumento.getText()), String.valueOf(tipoMoneda),
                            String.valueOf(etxPrecioVenta.getText()), String.valueOf(getResources().getString(R.string.IGV)), String.valueOf(chkAfectoIgv.isChecked() ? "1" : "0"), String.valueOf(etxOtrosGastos.getText()),
                            String.valueOf(rtgId), String.valueOf(pathImage)));

                }
                break;
            case R.id.spnDestinoViaje:
                spinnerDialogProv.showSpinerDialog();
                break;
            case R.id.spnTipoGasto:
                spinnerDialogTipoGasto.showSpinerDialog();
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
        }
    }

    //25-03 por Gustavo M. para validar cada campo y se quitó el campo OtrosGastos
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
        }else if (spnDestinoViaje.getText().equals("Seleccionar")) {
            Toast.makeText(mView.getContext(), "Falta Destino", Toast.LENGTH_LONG).show();
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
        if ((etxRuc.getText().toString().isEmpty() && etxRuc.getText().toString().trim().length() != 11) || etxNDocumento.getText().toString().isEmpty() || spnDestinoViaje.getText().equals("Seleccionar") || txvFechaDocumento.getText().toString().isEmpty() ||
                etxValorVenta.getText().toString().isEmpty() || etxOtrosGastos.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar")
                || pathImage == null) {
            Toast.makeText(mView.getContext(), getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
        */
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
