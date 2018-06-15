package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;

import org.angmarch.views.NiceSpinner;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ArrendamientoFragment extends Fragment {
    //region Injeccion de Vistas
    @BindView(R.id.etxRuc)
    CustomEditText mEtxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText mEtxRazonSocial;
    @BindView(R.id.spnTipoDocumento)
    NiceSpinner mSpnTipoDocumento;
    @BindView(R.id.spnTipoGasto)
    NiceSpinner mSpnTipoGasto;

    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;

    //endregion

    Unbinder unbinder;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_arrendamiento, container, false);
        unbinder = ButterKnife.bind(this, mView);


        ArrayAdapter<String> tipoDocumentoAdapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_documento));
        mSpnTipoDocumento.setAdapter(tipoDocumentoAdapter);


/*        ArrayAdapter<String> tipoGastoAdapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_gasto));
        mSpnTipoGasto.setAdapter(tipoGastoAdapter);*/

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


}
