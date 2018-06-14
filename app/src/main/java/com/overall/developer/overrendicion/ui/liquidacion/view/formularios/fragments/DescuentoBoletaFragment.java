package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overall.developer.overrendicion.R;


public class DescuentoBoletaFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_descuento_boleta, container,false);

        mView.setVisibility(View.GONE);
        return mView;
    }
}
