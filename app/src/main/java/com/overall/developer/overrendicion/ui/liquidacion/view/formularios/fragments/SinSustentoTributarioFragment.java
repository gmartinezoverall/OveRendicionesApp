package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.libizo.CustomEditText;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SinSustentoTributarioFragment extends Fragment {

    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;

    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;

    Unbinder unbinder;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sin_sustento_tributario, container, false);
        unbinder = ButterKnife.bind(this, mView);

        ArrayAdapter<TipoGastoEntity> adapterTipoGasto = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, ((FormularioActivity) getContext()).getListSpinner());
/*        spnTipoGasto.setAdapter(adapterTipoGasto);
        spnTipoGasto.setTitle("Seleciona un Tipo de Gasto");
        spnTipoGasto.setPositiveButton("CANCELAR");*/

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
