package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.BoletaVentaEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;

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


public class BoletaVentaFragment extends Fragment
{

    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
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
    @BindView(R.id.etxObservaciones)
    CustomEditText etxObservaciones;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;

    private SpinnerDialog spinnerDialog;
    private String rtgId;

    RendicionEntity rendicionEntity;
    TipoGastoEntity gastoEntity;
    String pathImage;

    Unbinder unbinder;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_boleta_venta, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();
        if (rendicionEntity != null) setAllDefaultValues();

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);


        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });
        etxValorVenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) etxPrecioVenta.setText(String.valueOf(etxValorVenta.getText()));
        });


        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto);

        return mView;
    }

    private void setAllDefaultValues()
    {
        gastoEntity = ((FormularioActivity)getContext()).getDefaultTipoGasto();

        etxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        etxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        etxNDocumento.setText(String.valueOf(rendicionEntity.getNumeroDoc()));
        etxCalendar.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        spnTipoMoneda.setSelectedIndex((rendicionEntity.getTipoMoneda().equals("S") ? 0 : 1));
        etxPrecioVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        etxValorVenta.setText(String.valueOf(Double.valueOf(rendicionEntity.getPrecioTotal()) - Double.valueOf(rendicionEntity.getIgv())));
        etxOtrosGastos.setText(String.valueOf(rendicionEntity.getOtroGasto()));
        if (rendicionEntity.getAfectoIgv().equals("1")) chkAfectoIgv.setChecked(true);
        txvMontoIGV.setText(String.valueOf(rendicionEntity.getIgv()));
        spnTipoGasto.setText(gastoEntity.getRtgDes());
        rtgId = (gastoEntity.getRtgId());
        etxObservaciones.setText(String.valueOf(rendicionEntity.getObservacion()));
        //imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showDatePickerDialog() {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mView.getContext(), (view, year, month, dayOfMonth) ->
        {
            etxCalendar.setText(String.valueOf(dayOfMonth) + "/" + month + "/" + year);

        }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    @OnClick({R.id.btnGuardar, R.id.chkAfectoIgv, R.id.spnTipoGasto, R.id.btnFoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGuardar:

                if (ValideWidgets())
                {
                    String tipoMoneda = spnTipoMoneda.getSelectedIndex() == 0 ? "S" : "D";
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                        ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new BoletaVentaEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxRuc.getText()),
                            String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNDocumento.getText()), String.valueOf(etxCalendar.getText()), tipoMoneda, String.valueOf(getResources().getString(R.string.IGV)), String.valueOf(chkAfectoIgv.isChecked() ? "1" : "0"),
                            String.valueOf(etxOtrosGastos.getText()), String.valueOf(etxPrecioVenta.getText()), String.valueOf(rtgId), String.valueOf(etxObservaciones.getText()), String.valueOf(pathImage)));

                }
                break;

            case R.id.spnTipoGasto:
                spinnerDialog.showSpinerDialog();
                break;

            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;

            case R.id.chkAfectoIgv:
                if (!etxValorVenta.getText().toString().isEmpty()) {
                    txvMontoIGV.setText(chkAfectoIgv.isChecked() ? String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.18) : "0.00");
                    etxPrecioVenta.setText(String.valueOf(Double.valueOf(txvMontoIGV.getText().toString()) + Double.valueOf(etxValorVenta.getText().toString())));
                } else {
                    Toast.makeText(mView.getContext(), "Debe ingresar Valor de Venta", Toast.LENGTH_LONG).show();
                    chkAfectoIgv.setChecked(false);
                }
                break;
        }
    }

    private boolean ValideWidgets()
    {
        if (etxRuc.getText().toString().isEmpty() || etxRazonSocial.getText().toString().isEmpty() || etxNDocumento.getText().toString().isEmpty() || etxCalendar.getText().toString().isEmpty() ||
                etxValorVenta.getText().toString().isEmpty() || etxOtrosGastos.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar") || etxObservaciones.getText().toString().isEmpty()
                ||  pathImage == null)
        {
            Toast.makeText(mView.getContext(), getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }

    //region Foto

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (100): {
                if (resultCode == Activity.RESULT_OK)
                {
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
