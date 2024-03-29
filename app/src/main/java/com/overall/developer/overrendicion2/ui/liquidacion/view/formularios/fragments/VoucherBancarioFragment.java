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
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion2.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.VoucherBancarioEntity;
import com.overall.developer.overrendicion2.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion2.utils.Util;
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

public class VoucherBancarioFragment extends Fragment
{

    //region Injeccion de Vistas
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
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.spnBanco)
    TextView spnBanco;
    @BindView(R.id.spnTipoMoneda)
    NiceSpinner spnTipoMoneda;
    @BindView(R.id.etxPrecioVenta)
    CustomEditText etxPrecioVenta;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    //endregion

    private SpinnerDialog spinnerDialogTipoDoc, spnDialogBanco;
    private String rtgId, banco;

    private RendicionEntity rendicionEntity;
    private TipoGastoEntity gastoEntity;
    private String pathImage;
    private BancoEntity bancoEntity;
    private LiquidacionEntity liquidacionEntity;

    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_voucher_bancario, container, false);
        unbinder = ButterKnife.bind(this, mView);

        liquidacionEntity = ((FormularioActivity) getContext()).getLiquidacion();
        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();


        initialCalendar();

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);

        if (rendicionEntity != null) setAllDefaultValues(); //11-04 por Gustavo M. porque primero se debe llenar todos los campos

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoDoc = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoDoc.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes());
            rtgId = ((TipoGastoEntity) item).getRtgId();
        });

        ArrayList<Object> itemListBanco = new ArrayList<>();
        itemListBanco.addAll(((FormularioActivity) getContext()).getAllBancos());
        spnDialogBanco = new SpinnerDialog(getActivity(), itemListBanco, getResources().getString(R.string.tittleSpinerBanco));
        spnDialogBanco.bindOnSpinerListener((item, position) ->
        {
            spnBanco.setText(((BancoEntity) item).getDesc());
            banco = ((BancoEntity) item).getCode();
        });
/*
        RxTextView.textChanges(etxPrecioVenta)
                .filter(etx -> (etx.length() > 0 && Double.valueOf(etx.toString()) > liquidacionEntity.getMonto() ))
                .subscribe(etx -> etxPrecioVenta.setError(getResources().getString(R.string.validateValorVentaLidacion) + " "+String.valueOf(liquidacionEntity.getMonto())));
*/

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto, spnBanco);
        return mView;

    }

    private void setAllDefaultValues() {
        gastoEntity = ((FormularioActivity) getContext()).getDefaultTipoGasto();
        bancoEntity = ((FormularioActivity) getContext()).getDefaultBanco();

        txvFechaDocumento.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        etxNDocumento.setText(String.valueOf(rendicionEntity.getNumeroDoc()));

        spnTipoMoneda.setSelectedIndex((rendicionEntity.getTipoMoneda().equals("S") ? 0 : 1));
        etxPrecioVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));

        spnTipoGasto.setText(gastoEntity.getRtgDes());
        rtgId = (gastoEntity.getRtgId());
        spnBanco.setText(String.valueOf(bancoEntity.getDesc()));
        banco = bancoEntity.getCode();

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //region OnClick
    @OnClick({R.id.spnBanco, R.id.spnTipoGasto, R.id.btnGuardar, R.id.btnFoto, R.id.lytFecha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnBanco:
                spnDialogBanco.showSpinerDialog();
                break;
            case R.id.spnTipoGasto:
                spinnerDialogTipoDoc.showSpinerDialog();
                break;
            case R.id.btnGuardar:
                if (ValideWidgets()) {
                    if(validarMonto()) {
                        String tipoMoneda = spnTipoMoneda.getSelectedIndex() == 0 ? "S" : "D";
                        ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new VoucherBancarioEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                                String.valueOf(txvFechaDocumento.getText()), String.valueOf(etxNDocumento.getText()), String.valueOf(banco), String.valueOf(tipoMoneda), String.valueOf(etxPrecioVenta.getText()), String.valueOf(rtgId),
                                String.valueOf(pathImage)));
                    }
                }
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 5);
                break;
            case R.id.lytFecha:
                lytFecha.setVisibility(View.GONE);
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);
                calendarView.clearDate();
                break;
        }
    }
    //endregion

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

                                            Log.i("ErrorCompressImage", throwable.getMessage())
                            );

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
                    Pix.start(this, 100, 1);
                } else {
                    Toast.makeText(mView.getContext(), "Tiene que aprobar los permisos para seleccionar una Foto", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }
    //endregion

    private boolean ValideWidgets() {
        if(etxNDocumento.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta Número de Voucher", Toast.LENGTH_SHORT).show();
            return false;
        }else if(txvFechaDocumento.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta Fecha Documento", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etxPrecioVenta.getText().toString().isEmpty()){
            Toast.makeText(mView.getContext(), "Falta el Importe", Toast.LENGTH_SHORT).show();
            return false;
        } else if(spnTipoGasto.getText().equals("Seleccionar")){
            Toast.makeText(mView.getContext(), "Falta Tipo de gasto", Toast.LENGTH_SHORT).show();
            return false;
        }else if(spnBanco.getText().equals("Seleccionar")) {
            Toast.makeText(mView.getContext(), "Falta el Banco", Toast.LENGTH_LONG).show();
            return false;
        }else if(pathImage == null){
            Toast.makeText(mView.getContext(), "Falta la Foto", Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
        /*
        if (txvFechaDocumento.getText().toString().isEmpty() || etxNDocumento.getText().toString().isEmpty() || spnBanco.getText().equals("Seleccionar")
                || etxPrecioVenta.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar") || pathImage == null) {
            Toast.makeText(mView.getContext(), getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
        */
    }

    //26-03 por Gustavo M. para validar el monto que sea menos de 700 soles
    private boolean validarMonto(){
        if(Double.parseDouble(etxPrecioVenta.getText().toString())>liquidacionEntity.getMonto()){
            Toast.makeText(mView.getContext(),getResources().getString(R.string.validateValorVentaLidacion) + " "+String.valueOf(liquidacionEntity.getMonto()),Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
    }

}
