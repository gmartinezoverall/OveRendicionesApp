package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

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
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.ArrendamientoEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.communicator.Communicator;
import com.overall.developer.overrendicion.ui.communicator.OttoBus;
import com.overall.developer.overrendicion.utils.Util;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

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

public class ArrendamientoFragment extends Fragment {

    //region Injeccion de Vistas
    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
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
    @BindView(R.id.etxNSerie)
    CustomEditText etxNSerie;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.etxMonto)
    CustomEditText etxMonto;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    //endregion

    private SpinnerDialog spinnerDialog;
    private String rtgId, pathImage, razonSocial;
    private RendicionEntity rendicionEntity;
    private TipoGastoEntity gastoEntity;
    private LiquidacionEntity liquidacionEntity;

    Unbinder unbinder;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_arrendamiento, container, false);
        unbinder = ButterKnife.bind(this, mView);

        liquidacionEntity = ((FormularioActivity) getContext()).getLiquidacion();
        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();

        initialCalendar();
        if (rendicionEntity != null) setAllDefaultValues();

        RxTextView.textChanges(etxRuc)
                .filter(etx -> (etx.length() > 0 && etx.length() != 11))
                .subscribe(etx -> etxRuc.setError(getResources().getString(R.string.validarRuc)));

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());

        if (itemList.size() == 1) {
            spnTipoGasto.setText(itemList.get(0).toString());
            rtgId = ((TipoGastoEntity) itemList.get(0)).getRtgId();
        }
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes());
            rtgId = ((TipoGastoEntity) item).getRtgId();
        });

/*        etxNSerie.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNSerie != null)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });
        etxNDocumento.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus && etxNDocumento != null)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });*/

        PushDownAnim.setPushDownAnimTo(btnGuardar, spnTipoGasto, btnFoto, btnSearch);

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setAllDefaultValues() {
        gastoEntity = ((FormularioActivity) getContext()).getDefaultTipoGasto();

        String[] strings = rendicionEntity.getNumeroDoc().split("\\-");
        etxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        etxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        txvFechaDocumento.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        etxNSerie.setText(String.valueOf(strings[0]));
        etxNDocumento.setText(String.valueOf(strings[1]));
        etxMonto.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        spnTipoGasto.setText(String.valueOf(gastoEntity.getRtgDes()));
        rtgId = String.valueOf(gastoEntity.getRtgId());
        //imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));
        Picasso.get()
                //.load("https://s3.us-east-2.amazonaws.com/overrendicion-userfiles-mobilehub-1058830409/uploads/20180826233027.jpg")
                .load(rendicionEntity.getFoto())
                .placeholder(R.drawable.ic_add_a_photo)
                .error(R.drawable.ic_highlight_off)
                .into(imgFoto);
    }

    public void searchRucSuccess(String razonSocial) {
        if (etxRazonSocial != null) etxRazonSocial.setText(String.valueOf(razonSocial));

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


    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar, R.id.btnSearch, R.id.lytFecha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnTipoGasto:
                spinnerDialog.showSpinerDialog();
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                if (ValideWidgets()) {
                    //String tipoMoneda = mSpnTipoDocumento.getSelectedIndex() == 0 ? "S" : "D";
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new ArrendamientoEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxRuc.getText()), String.valueOf(etxRazonSocial.getText()),
                            String.valueOf(txvFechaDocumento.getText()), String.valueOf(etxNSerie.getText()) + "-" + String.valueOf(etxNDocumento.getText()), String.valueOf(etxMonto.getText()), String.valueOf(rtgId), String.valueOf(pathImage)));

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
        }


    }

    private boolean ValideWidgets() {
        if ((etxRuc.getText().toString().isEmpty() && etxRuc.getText().toString().trim().length() != 11) || etxRazonSocial.getText().toString().isEmpty() || txvFechaDocumento.getText().toString().isEmpty()
                || etxNDocumento.getText().toString().isEmpty() || etxMonto.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar")
                || pathImage == null) {
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
