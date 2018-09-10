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
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.ReciboHonorariosEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.Communicator;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.OttoBus;
import com.squareup.otto.Subscribe;
import com.thekhaeng.pushdownanim.PushDownAnim;

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

public class ReciboHonorariosFragment extends Fragment {
    //region injeccion de vistas
    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNumDoc)
    CustomEditText etxNumDoc;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.etxValorVenta)
    CustomEditText etxValorVenta;
    @BindView(R.id.chkRetencion)
    CheckBox chkRetencion;
    @BindView(R.id.txvRetencion)
    TextView txvRetencion;
    @BindView(R.id.etxPrecioVenta)
    TextView etxPrecioVenta;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.etxCodSuspencion)
    CustomEditText etxCodSuspencion;
    @BindView(R.id.lytCodSuspencion)
    LinearLayout lytCodSuspencion;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.etxNSerie)
    CustomEditText etxNSerie;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;

    //endregion


    private SpinnerDialog spinnerDialog;
    private String rtgId;

    String pathImage;


    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recibo_honorarios, container, false);
        unbinder = ButterKnife.bind(this, mView);

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        etxValorVenta.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) etxPrecioVenta.setText(String.valueOf(etxValorVenta.getText()));
        });


        etxNSerie.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus)
                etxNSerie.setText(String.valueOf(etxNSerie.getText()) + getResources().getString(R.string.autocomplete));

        });

        etxNDocumento.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });

        RxTextView.textChanges(etxRuc)
                .filter(etx -> (etx.length() > 0 && etx.length() != 11 ))
                .subscribe(etx -> etxRuc.setError(getResources().getString(R.string.validarRuc)));


        RxTextView.textChanges(etxValorVenta)
                .filter(etx -> (etx.length() > 0 && Double.valueOf(etx.toString()) > 700))
                .subscribe(etx -> etxValorVenta.setError(getResources().getString(R.string.validateValorVenta)));

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
    public void searchRucSuccess(Communicator razonSocial) {
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

        }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }


    @OnClick({R.id.chkRetencion, R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar, R.id.btnSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chkRetencion:
                if (!etxValorVenta.getText().toString().isEmpty()) {
                    txvRetencion.setText(chkRetencion.isChecked() ? String.valueOf(Double.valueOf(etxValorVenta.getText().toString()) * 0.08) : "0.00");
                    etxPrecioVenta.setText(String.valueOf(Double.valueOf(txvRetencion.getText().toString()) + Double.valueOf(etxValorVenta.getText().toString())));

                    if (!chkRetencion.isChecked()) lytCodSuspencion.setVisibility(View.VISIBLE);
                    else {
                        lytCodSuspencion.setVisibility(View.GONE);
                        etxCodSuspencion.setText("");
                    }
                } else {
                    Toast.makeText(mView.getContext(), "Debe ingresar Valor de Venta", Toast.LENGTH_LONG).show();
                    chkRetencion.setChecked(false);
                }

                break;
            case R.id.spnTipoGasto:
                spinnerDialog.showSpinerDialog();
                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                if (ValideWidgets()) {
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new ReciboHonorariosEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                            String.valueOf(etxRuc.getText()), String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNumDoc.getText()), String.valueOf(etxCalendar.getText()), String.valueOf(etxPrecioVenta),
                            String.valueOf(chkRetencion.isChecked() ? "1" : "0"), String.valueOf(etxCodSuspencion.getText()), String.valueOf(rtgId), String.valueOf(pathImage)));

                }
                break;
            case R.id.btnSearch:
                if (etxRuc.getText().toString().length() == 11) {
                    ((FormularioActivity) getContext()).searchRuch(String.valueOf(etxRuc.getText()));
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.validarRuc), Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    private boolean ValideWidgets() {
        if ((etxRuc.getText().toString().isEmpty() && etxRuc.getText().toString().trim().length() != 11) || etxRazonSocial.getText().toString().isEmpty() || etxNumDoc.getText().toString().isEmpty() || etxCalendar.getText().toString().isEmpty()
                || etxValorVenta.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar") || pathImage == null) {
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
