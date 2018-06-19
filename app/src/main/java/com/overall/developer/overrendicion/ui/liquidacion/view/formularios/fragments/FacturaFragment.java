package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.libizo.CustomEditText;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.FacturaEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class FacturaFragment extends Fragment {

    //region Injeccion de Vistas
    @BindView(R.id.spnTipoMoneda)
    NiceSpinner spnTipoMoneda;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.etxValorVenta)
    CustomEditText etxValorVenta;
    @BindView(R.id.etxOtrosGastos)
    CustomEditText etxOtrosGastos;
    @BindView(R.id.etxPrecioVenta)
    TextView etxPrecioVenta;
    @BindView(R.id.etxObservaciones)
    CustomEditText etxObservaciones;

    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.btnAgregarFoto)
    Button btnAgregarFoto;

    @BindView(R.id.chkAfectoIgv)
    CheckBox chkAfectoIgv;
    @BindView(R.id.txvMontoIGV)
    TextView txvMontoIGV;

    //endregion

    private SpinnerDialog spinnerDialog;
    private String idProvincia;

    Unbinder unbinder;
    View mView;
    RendicionEntity rendicionEntity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_factura, container, false);
        unbinder = ButterKnife.bind(this, mView);

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);

        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();
        if (rendicionEntity != null)setAllDefaultValues();

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnAgregarFoto);

        spinnerDialog = new SpinnerDialog( getActivity(), itemList, getResources().getString(R.string.tittleSpinerSearch));
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity)item).getRtgDes().toString());
            idProvincia = ((TipoGastoEntity)item).getRtgId().toString();
        });

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        return mView;
    }

    private void setAllDefaultValues()
    {
        etxRuc.setText(String.valueOf(rendicionEntity.getRuc()));
        etxRazonSocial.setText(String.valueOf(rendicionEntity.getRazonSocial()));
        etxNDocumento.setText(String.valueOf(rendicionEntity.getNumeroDoc()));
        etxCalendar.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        spnTipoMoneda.setSelectedIndex((rendicionEntity.getTipoMoneda().equals("S")? 0 : 1));
        etxValorVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        etxOtrosGastos.setText(String.valueOf(rendicionEntity.getOtroGasto()));
        if (rendicionEntity.getAfectoIgv().equals("1"))chkAfectoIgv.setChecked(true);
        //spnTipoGasto.setText(String.valueOf(rendicionEntity.tipo));
        etxObservaciones.setText(String.valueOf(rendicionEntity.getObservacion()));

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

    @OnClick({R.id.btnGuardar, R.id.btnAgregarFoto, R.id.chkAfectoIgv, R.id.spnTipoGasto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGuardar:


                String tipoMoneda = spnTipoMoneda.getSelectedIndex() == 0 ? "S" : "D";
                // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());

                ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new FacturaEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()), String.valueOf(etxRuc.getText()),
                        String.valueOf(etxRazonSocial.getText()), String.valueOf(etxNDocumento.getText()), String.valueOf(etxCalendar.getText()), tipoMoneda, String.valueOf(getResources().getString(R.string.IGV)),String.valueOf(chkAfectoIgv.isChecked() ? "1" : "0"),
                        String.valueOf(etxOtrosGastos.getText()), String.valueOf(etxPrecioVenta.getText()), idProvincia, String.valueOf(etxObservaciones.getText())));

                break;
            case R.id.btnAgregarFoto:

                break;

             case R.id.spnTipoGasto:
                 spinnerDialog.showSpinerDialog();
                break;

            case R.id.chkAfectoIgv:
                if (!etxValorVenta.getText().toString().isEmpty())
                {
                    txvMontoIGV.setText(chkAfectoIgv.isChecked() ?  String.valueOf(Double.valueOf(etxValorVenta.getText().toString())* 0.18): "0.00");
                    etxPrecioVenta.setText(String.valueOf(Double.valueOf(txvMontoIGV.getText().toString())+ Double.valueOf(etxValorVenta.getText().toString())));
                }
                else
                {
                    Toast.makeText(mView.getContext(),"Debe ingresar Valor de Venta", Toast.LENGTH_LONG).show();
                    chkAfectoIgv.setChecked(false);
                }

                break;
        }
    }

}
