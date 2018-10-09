package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import info.hoang8f.android.segmented.SegmentedGroup;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;

public class MovilidadFragment extends Fragment {

    //region Injeccion de Vistas
    @BindView(R.id.btnIndividual)
    RadioButton btnIndividual;
    @BindView(R.id.btnMasivo)
    RadioButton btnMasivo;
    @BindView(R.id.segmented2)
    SegmentedGroup segmented2;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.txvFechaInicio)
    TextView txvFechaInicio;
    @BindView(R.id.txvFechaFin)
    TextView txvFechaFin;
    @BindView(R.id.lytfechaFinal)
    LinearLayout lytfechaFinal;
    @BindView(R.id.lytArrow)
    LinearLayout lytArrow;
    @BindView(R.id.calendarView)
    SectionCalendarView calendarView;
    @BindView(R.id.lytCalendar)
    LinearLayout lytCalendar;
    @BindView(R.id.view2)
    View view2;
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
    @BindView(R.id.lytFecha)
    LinearLayout lytFecha;
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
        mView = inflater.inflate(R.layout.fragment_movilidad, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionDetalleEntity = ((FormularioActivity) getContext()).getDetalleDefaultValues();

        initialCalendar();
        if (rendicionDetalleEntity != null) setAllDefaultValues();

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

        PushDownAnim.setPushDownAnimTo(btnGuardar, spnTipoGasto);
        return mView;
    }

    private void setAllDefaultValues() {
        txvFechaInicio.setText(String.valueOf(rendicionDetalleEntity.getFechaDesde()));
        txvFechaFin.setText(String.valueOf(rendicionDetalleEntity.getFechaHasta()));
        etxMotivo.setText(String.valueOf(rendicionDetalleEntity.getMotivoMovilidad()));
        etxDestino.setText(String.valueOf(rendicionDetalleEntity.getDestinoMovilidad()));
        etxMonto.setText(String.valueOf(rendicionDetalleEntity.getMontoMovilidad()));

        gastoEntity = ((FormularioActivity) getContext()).getDefaultTipoGastoDetail();
        spnTipoGasto.setText(gastoEntity.getRtgDes());
        rtgId = (gastoEntity.getRtgId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //region Calendar
    private void initialCalendar() {
        txvFechaInicio.setText(String.valueOf(Util.getCurrentDate()));
        txvFechaFin.setText(String.valueOf(Util.getCurrentDate()));
        calendarView.setDateFormat("dd/MM/yyyy");
        calendarView.setPreventPreviousDate(false);
        calendarView.setErrToastMessage(R.string.error_date);
        calendarView.setOnDaySelectedListener((startDay, endDay) ->
        {
            txvFechaInicio.setText(Util.changeDateFormat(startDay));
            txvFechaInicio.setTypeface(null, Typeface.BOLD);
            txvFechaInicio.setTextColor(getResources().getColor(R.color.black));
            txvFechaFin.setText(Util.changeDateFormat(endDay));
            txvFechaFin.setTypeface(null, Typeface.BOLD);
            txvFechaFin.setTextColor(getResources().getColor(R.color.black));
            if (btnIndividual.isChecked() && !startDay.equals(""))
            {
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);

            }else if (btnMasivo.isChecked() && !endDay.equals(""))
            {
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);

            }

        });
        calendarView.buildCalendar();
    }
    //endregion

    //region OnClick
    @OnClick({R.id.btnIndividual, R.id.btnMasivo, R.id.spnTipoGasto, R.id.btnGuardar, R.id.lytFecha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnIndividual:
                lytfechaFinal.setVisibility(View.GONE);
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
                        String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), "-", "-", String.valueOf(etxMotivo.getText()), String.valueOf(etxDestino.getText()), String.valueOf(etxMonto.getText()),
                        String.valueOf(Util.getCurrentDate()), String.valueOf(rtgId), String.valueOf(btnIndividual.isChecked() ? "I" : "M"),
                        String.valueOf(txvFechaInicio.getText()), String.valueOf(txvFechaFin.getText()), "-"
                ));
                break;
            case R.id.lytFecha:
                lytCalendar.setVisibility(lytCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                lytArrow.setRotation(lytArrow.getRotation() == 90 ? 0 : 90);
                calendarView.clearDate();
                break;
        }
    }
    //endregion
}
