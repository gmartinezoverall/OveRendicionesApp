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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.MovilidadEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.utils.Util;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import info.hoang8f.android.segmented.SegmentedGroup;

public class MovilidadFragment extends Fragment {
    CustomEditText txvFechaHastaDisable;
    @BindView(R.id.btnIndividual)
    RadioButton btnIndividual;
    @BindView(R.id.btnMasivo)
    RadioButton btnMasivo;
    @BindView(R.id.segmented2)
    SegmentedGroup segmented2;
    @BindView(R.id.etxFechaDesde)
    CustomEditText etxFechaDesde;
    @BindView(R.id.txtFechaHasta)
    TextView txtFechaHasta;
    @BindView(R.id.etxFechaHasta)
    CustomEditText etxFechaHasta;
    @BindView(R.id.etxMotivo)
    CustomEditText etxMotivo;
    @BindView(R.id.etxDestino)
    CustomEditText etxDestino;
    @BindView(R.id.etxMonto)
    CustomEditText etxMonto;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    @BindView(R.id.lytfechaFinal)
    LinearLayout lytfechaFinal;

    private SpinnerDialog spinnerDialog;
    private String idProvincia;
    private String rtgId;
    TipoGastoEntity gastoEntity;
    private RendicionDetalleEntity rendicionDetalleEntity;
    String pathImage;

    Unbinder unbinder;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movilidad, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionDetalleEntity = ((FormularioActivity) getContext()).getDetalleDefaultValues();
        if (rendicionDetalleEntity != null) setAllDefaultValues();

        etxFechaDesde.setText(String.valueOf(Util.getCurrentDate()));
        etxFechaDesde.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog(1);
        });
        etxFechaHasta.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog(2);
        });

        PushDownAnim.setPushDownAnimTo(btnGuardar, spnTipoGasto);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());

        if (itemList.size() == 1)
        {
            spnTipoGasto.setText(itemList.get(0).toString());
            rtgId = ((TipoGastoEntity) itemList.get(0)).getRtgId().toString();
        }
        spinnerDialog = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        return mView;
    }

    private void setAllDefaultValues()
    {

        etxFechaDesde.setText(String.valueOf(rendicionDetalleEntity.getFechaDesde()));
        etxFechaHasta.setText(String.valueOf(rendicionDetalleEntity.getFechaHasta()));
        etxMotivo.setText(String.valueOf(rendicionDetalleEntity.getMotivoMovilidad()));
        etxDestino.setText(String.valueOf(rendicionDetalleEntity.getDestinoMovilidad()));
        etxMonto.setText(String.valueOf(rendicionDetalleEntity.getMontoMovilidad()));

        gastoEntity = ((FormularioActivity)getContext()).getDefaultTipoGastoDetail();
        spnTipoGasto.setText(gastoEntity.getRtgDes());
        rtgId = (gastoEntity.getRtgId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showDatePickerDialog(int tipo) {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mView.getContext(), (view, year, month, dayOfMonth) ->
        {
            if (tipo == 1)
            {
                etxFechaDesde.setText(String.valueOf(dayOfMonth) + "/" + month + "/" + year);
                if (btnIndividual.isChecked())etxMotivo.requestFocus();
                else etxFechaHasta.requestFocus();
            }
            if (tipo == 2) {
                etxFechaHasta.setText(String.valueOf(dayOfMonth) + "/" + month + "/" + year);
                etxMotivo.requestFocus();
            }

        }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

    @OnClick({R.id.btnIndividual, R.id.btnMasivo, R.id.spnTipoGasto, R.id.btnGuardar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnIndividual:
                lytfechaFinal.setVisibility(View.GONE);
                etxFechaHasta.setText("");
                break;
            case R.id.btnMasivo:
                lytfechaFinal.setVisibility(View.VISIBLE);

                break;
            case R.id.spnTipoGasto:
                spinnerDialog.showSpinerDialog();
                break;
            case R.id.btnGuardar:
                // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
                ((FormularioActivity) getContext()).saveAndSendDataForMovilidad(new MovilidadEntity(((FormularioActivity) getContext()).getIdMovilidad(),
                        String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxMotivo.getText()), String.valueOf(etxDestino.getText()), String.valueOf(etxMonto.getText()),
                        String.valueOf(Util.getCurrentDate()),String.valueOf(rtgId), String.valueOf(btnIndividual.isChecked() ? "I": "M"),
                        String.valueOf(etxFechaDesde.getText()), String.valueOf(etxFechaHasta.getText())
                ));


                break;
        }
    }
}
