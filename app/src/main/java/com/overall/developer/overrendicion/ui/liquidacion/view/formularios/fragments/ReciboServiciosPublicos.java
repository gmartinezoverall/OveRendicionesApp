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
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.ReciboServiciosPublicosEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.Communicator;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.OttoBus;
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

public class ReciboServiciosPublicos extends Fragment {

    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNumDoc)
    CustomEditText etxNumDoc;
    @BindView(R.id.spnTipoServicio)
    NiceSpinner spnTipoServicio;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
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
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;


    private SpinnerDialog spinnerDialogTipoGasto;
    private String rtgId, pathImage;

    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recibo_servicios_publicos, container, false);
        unbinder = ButterKnife.bind(this, mView);

        ArrayAdapter<String> adapterTipoServicio = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_servicio));
        spnTipoServicio.setAdapter(adapterTipoServicio);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });


        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        etxValorVenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                txvIgv.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.18));
                etxPrecioVenta.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) + Double.valueOf(txvIgv.getText().toString())));
            }

        });

        etxImpNoAfectado.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                etxPrecioVenta.setText(String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) + Double.valueOf(txvIgv.getText().toString()) + Double.valueOf(etxImpNoAfectado.getText().toString())));
            }

        });

        etxRuc.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                if (etxRuc.getText().toString().length() != 11)
                {
                    etxRuc.setError("Verificar RUC");

                }
            }
        });


        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto, btnSearch);

        return mView;
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
    public void searchRucSuccess(Communicator razonSocial)
    {
        etxRazonSocial.setText(razonSocial.getRazonSocial());
        etxRazonSocial.setEnabled(false);
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
            //if (!etxCalendar.getText().toString().isEmpty());

        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar, R.id.btnSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnTipoGasto:
                spinnerDialogTipoGasto.showSpinerDialog();
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                if (ValideWidgets())
                {
                    //String tipoMoneda = mSpnTipoDocumento.getSelectedIndex() == 0 ? "S" : "D";
                    // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new ReciboServiciosPublicosEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                            String.valueOf(etxRuc.getText()), String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNumDoc.getText()), String.valueOf(etxCalendar.getText()), String.valueOf(rtgId), String.valueOf(txvIgv.getText()), String.valueOf("1"),
                            String.valueOf(etxImpNoAfectado.getText()), String.valueOf(etxPrecioVenta.getText()), String.valueOf(pathImage)));

                }
                break;
            case R.id.btnSearch:
                if (etxRuc.getText().toString().length() == 11)
                {
                    ((FormularioActivity)getContext()).searchRuch(String.valueOf(etxRuc.getText()));
                }else
                {
                    Toast.makeText(getContext(), getResources().getString(R.string.validarRuc), Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    private boolean ValideWidgets()
    {
        if ( etxNumDoc.getText().toString().isEmpty() || etxCalendar.getText().toString().isEmpty() || etxValorVenta.getText().toString().isEmpty()
                || spnTipoGasto.getText().equals("Seleccionar") || etxImpNoAfectado.getText().toString().isEmpty() || pathImage == null)
        {
            return false;

        }else return true;
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
