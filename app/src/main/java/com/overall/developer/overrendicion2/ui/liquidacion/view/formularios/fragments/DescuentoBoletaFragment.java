package com.overall.developer.overrendicion2.ui.liquidacion.view.formularios.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.overall.developer.overrendicion2.R;


public class DescuentoBoletaFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_descuento_boleta, container,false);

        mView.setVisibility(View.GONE);
        Toast.makeText(mView.getContext(),"Esta opción no está disponible",Toast.LENGTH_LONG).show(); //29-03 por Gustavo M. para informar al usuario que esta opción no está disponible
        return mView;
    }
}
