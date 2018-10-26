package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.BoletoTerrestreEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.Communicator;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.OttoBus;
import com.overall.developer.overrendicion.utils.Util;
import com.squareup.otto.Subscribe;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;

public class BoletoTerrestreFragment extends Fragment
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
    private TipoGastoEntity gastoEntity;
    private ProvinciaEntity provinciaEntity;
    private String pathImage;

    View mView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_boleto_terrestre, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();

        initialCalendar();
        if (rendicionEntity != null) setAllDefaultValues();

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);

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
        if (itemList2.size() == 1) {
            spnTipoGasto.setText(itemList2.get(0).toString());
            rtgId = ((TipoGastoEntity) itemList2.get(0)).getRtgId();
        }
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList2, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes());
            rtgId = ((TipoGastoEntity) item).getRtgId();
        });


        RxTextView.textChanges(etxRuc)
                .filter(etx -> (etx.length() > 0 && etx.length() != 11))
                .subscribe(etx -> etxRuc.setError(getResources().getString(R.string.validarRuc)));

        etxNSerie.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNSerie != null) etxNSerie.setText(String.valueOf(etxNSerie.getText()) + getResources().getString(R.string.autocomplete));

        });

        etxNDocumento.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNDocumento!= null)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });

        RxTextView.textChanges(etxOtrosGastos).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());
        RxTextView.textChanges(etxValorVenta).filter(etx -> (etx.length() > 0)).subscribe(etx -> sumaTotal());

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnDestinoViaje, spnTipoGasto, btnSearch);

        return mView;
    }

    //region Estados Fragment
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
        imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));

    }

    private void sumaTotal() {
        Double neto, igv, otros;

        neto = Double.valueOf(String.valueOf(etxValorVenta.getText().toString() .isEmpty() ? 0 : etxValorVenta.getText().toString()));
        igv = Double.valueOf(chkAfectoIgv.isChecked() ? String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.18) : "0.00");
        txvMontoIGV.setText(String.valueOf(String.format("%.2f", igv)));
        otros = Double.valueOf(String.valueOf(etxOtrosGastos.getText().toString().isEmpty() ? 0 : etxOtrosGastos.getText().toString()));

        etxPrecioVenta.setText(String.valueOf(neto + igv + otros));
    }


    @OnClick({R.id.chkAfectoIgv, R.id.btnFoto, R.id.btnGuardar, R.id.spnDestinoViaje, R.id.spnTipoGasto, R.id.lytFecha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chkAfectoIgv:
                if (!etxValorVenta.getText().toString().isEmpty())
                {
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
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new BoletoTerrestreEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxRuc.getText()),
                            String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNDocumento.getText()) + "-" + String.valueOf(etxNSerie.getText()), String.valueOf(idProvincia), String.valueOf(txvFechaDocumento.getText()), String.valueOf(tipoMoneda),
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

    //region Calendar
    private void initialCalendar()
    {
        //txvFechaDocumento.setText(String.valueOf(Util.getCurrentDate()));
        calendarView.setDateFormat("dd/MM/yyyy");
        calendarView.setPreventPreviousDate(false);
        //calendarView.setErrToastMessage(R.string.error_date);
        calendarView.setOnDaySelectedListener((startDay, endDay) ->
        {
            txvFechaDocumento.setText(Util.changeDateFormat(startDay));
            txvFechaDocumento.setTypeface(null, Typeface.BOLD);
            txvFechaDocumento.setTextColor(getResources().getColor(R.color.black));
            if (!startDay.equals("")) {
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);
                etxNDocumento.requestFocus();
                lytFecha.setVisibility(View.VISIBLE);
            }
        });
        calendarView.buildCalendar();
    }
    //endregion



    private boolean ValideWidgets() {
        if ((etxRuc.getText().toString().isEmpty() && etxRuc.getText().toString().trim().length() != 11) || etxRazonSocial.getText().toString().isEmpty() || etxNDocumento.getText().toString().isEmpty() || spnDestinoViaje.getText().equals("Seleccionar") || txvFechaDocumento.getText().toString().isEmpty() ||
                etxValorVenta.getText().toString().isEmpty() || etxOtrosGastos.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar")
                || pathImage == null) {
            Toast.makeText(mView.getContext(), getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }
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
