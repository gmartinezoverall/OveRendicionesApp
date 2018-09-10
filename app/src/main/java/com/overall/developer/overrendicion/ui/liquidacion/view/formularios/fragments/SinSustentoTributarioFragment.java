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
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.SinSustentoTributarioEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
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

public class SinSustentoTributarioFragment extends Fragment {

    //region Injeccion de Vistas
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.etxMontoNoAfectado)
    CustomEditText etxMontoNoAfectado;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.etxObservaciones)
    CustomEditText etxObservaciones;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    //endregion

    private SpinnerDialog spinnerDialogTipoGasto;
    private String rtgId, pathImage;

    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sin_sustento_tributario, container, false);
        unbinder = ButterKnife.bind(this, mView);


        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto);

        return mView;
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

    private boolean ValideWidgets() {
        if (etxCalendar.getText().toString().isEmpty() || etxMontoNoAfectado.getText().toString().isEmpty() || spnTipoGasto.getText().equals("Seleccionar")
                || etxObservaciones.getText().toString().isEmpty()|| pathImage == null) {
            Toast.makeText(mView.getContext(), getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar})
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
                    ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new SinSustentoTributarioEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                            String.valueOf(etxCalendar.getText()),  String.valueOf(etxMontoNoAfectado.getText()), String.valueOf(rtgId),
                            String.valueOf(etxObservaciones.getText()), String.valueOf(pathImage)));

                }
                break;
        }
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
