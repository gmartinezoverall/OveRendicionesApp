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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.ArrendamientoEntity;
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

public class ArrendamientoFragment extends Fragment {
    //region Injeccion de Vistas
    @BindView(R.id.etxRuc)
    CustomEditText mEtxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText mEtxRazonSocial;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;

    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.etxMonto)
    TextView etxMonto;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.etxNSerie)
    CustomEditText etxNSerie;

    //endregion

    private SpinnerDialog spinnerDialog;
    private String rtgId, pathImage, razonSocial;
    private RendicionEntity rendicionEntity;
    private TipoGastoEntity gastoEntity;

    Unbinder unbinder;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_arrendamiento, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();
        if (rendicionEntity != null) setAllDefaultValues();

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        RxTextView.textChanges(mEtxRuc)
                .filter(etx -> (etx.length() > 0 && etx.length() != 11))
                .subscribe(etx -> mEtxRuc.setError(getResources().getString(R.string.validarRuc)));

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());

        if (itemList.size() == 1) {
            spnTipoGasto.setText(itemList.get(0).toString());
            rtgId = ((TipoGastoEntity) itemList.get(0)).getRtgId().toString();
        }
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        etxNSerie.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });
        etxNDocumento.setOnFocusChangeListener((v, hasFocus) ->
        {
            if (!hasFocus)
                etxNDocumento.setText(String.valueOf(etxNDocumento.getText()) + getResources().getString(R.string.autocomplete));

        });

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
        mEtxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        mEtxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        etxCalendar.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        etxNSerie.setText(String.valueOf(strings[0]));
        etxNDocumento.setText(String.valueOf(strings[1]));
        etxMonto.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        spnTipoGasto.setText(String.valueOf(gastoEntity.getRtgDes()));
        rtgId = String.valueOf(gastoEntity.getRtgId());
        imgFoto.setImageBitmap(BitmapFactory.decodeFile(rendicionEntity.getFoto()));

    }

    public void searchRucSuccess(String razonSocial) {
        if (mEtxRazonSocial != null) mEtxRazonSocial.setText(String.valueOf(razonSocial));

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
        mEtxRazonSocial.setText(razonSocial.getRazonSocial());
        mEtxRazonSocial.setEnabled(false);
    }


    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar, R.id.btnSearch})
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
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new ArrendamientoEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(mEtxRuc.getText()), String.valueOf(mEtxRazonSocial.getText()),
                            String.valueOf(etxCalendar.getText()), String.valueOf(etxNDocumento.getText()) + "-"+ String.valueOf(etxNSerie.getText()), String.valueOf(etxMonto.getText()), String.valueOf(rtgId), String.valueOf(pathImage)));

                }
                break;
            case R.id.btnSearch:
                if (mEtxRuc.getText().toString().length() == 11) {
                    ((FormularioActivity) getContext()).searchRuch(String.valueOf(mEtxRuc.getText()));
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.validarRuc), Toast.LENGTH_LONG).show();

                }
                break;
        }


    }

    private boolean ValideWidgets() {
        if ((mEtxRuc.getText().toString().isEmpty() && mEtxRuc.getText().toString().trim().length() != 11) || mEtxRazonSocial.getText().toString().isEmpty() || etxCalendar.getText().toString().isEmpty()
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
