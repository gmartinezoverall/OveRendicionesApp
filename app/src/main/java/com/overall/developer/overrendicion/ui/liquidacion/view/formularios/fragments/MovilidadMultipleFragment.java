package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxn.pix.Pix;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadMultipleEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MovilidadMultipleFragment extends Fragment {

    //region Injeccion de Vistas
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;

    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.btnListar)
    ImageButton btnListar;
    @BindView(R.id.btnSalir)
    Button btnSalir;
    @BindView(R.id.etxNumDoc)
    CustomEditText etxNumDoc;
    @BindView(R.id.etxdatosTra)
    CustomEditText etxdatosTra;
    @BindView(R.id.etxMotivo)
    CustomEditText etxMotivo;
    @BindView(R.id.etxDestino)
    CustomEditText etxDestino;
    @BindView(R.id.etxMonto)
    CustomEditText etxMonto;
    //endregion

    private SpinnerDialog spinnerDialog;
    private String idProvincia;
    private String rtgId;
    private TipoGastoEntity gastoEntity;
    private RendicionDetalleEntity rendicionDetalleEntity;
    private String pathImage;


    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movilidad_multiple, container, false);
        unbinder = ButterKnife.bind(this, mView);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());

        if (itemList.size() == 1) {
            spnTipoGasto.setText(itemList.get(0).toString());
            rtgId = ((TipoGastoEntity) itemList.get(0)).getRtgId().toString();
        }
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

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

    @OnClick({R.id.btnFoto, R.id.btnGuardar, R.id.btnListar, R.id.btnSalir, R.id.spnTipoGasto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFoto:
                Pix.start(this, 100, 1);//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
                break;
            case R.id.btnGuardar:
                ((FormularioActivity) getContext()).saveAndSendDataForMovilidadMultiple(new MovilidadMultipleEntity(((FormularioActivity) getContext()).getIdMovilidad(), String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                        String.valueOf(etxNumDoc.getText()), String.valueOf(etxdatosTra.getText()), String.valueOf(etxMotivo.getText()), String.valueOf(etxDestino.getText()), String.valueOf(etxMonto.getText()),
                        String.valueOf(etxCalendar.getText()), String.valueOf(rtgId), String.valueOf(pathImage)
                ));

                break;
            case R.id.spnTipoGasto:
                spinnerDialog.showSpinerDialog();
                break;
            case R.id.btnListar:
                break;
            case R.id.btnSalir:
                //getActivity().getFragmentManager().beginTransaction().remove().commit();
                getActivity().getSupportFragmentManager().popBackStack();

                break;
        }
    }
}
