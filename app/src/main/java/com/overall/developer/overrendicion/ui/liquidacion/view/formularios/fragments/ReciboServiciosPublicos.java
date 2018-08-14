package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class ReciboServiciosPublicos extends Fragment {

    @BindView(R.id.etxRuc)
    CustomEditText etxRuc;
    @BindView(R.id.etxRazonSocial)
    CustomEditText etxRazonSocial;
    @BindView(R.id.etxNumDoc)
    CustomEditText etxNumDoc;
    @BindView(R.id.spnTipoServicio)
    NiceSpinner spnTipoServicio;
    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.etxValorVenta)
    CustomEditText etxValorVenta;
    @BindView(R.id.txvRetencion)
    TextView txvRetencion;
    @BindView(R.id.etxPrecioVenta)
    TextView etxPrecioVenta;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.etxImpNoAfectado)
    CustomEditText etxImpNoAfectado;
    @BindView(R.id.lytCodSuspencion)
    LinearLayout lytCodSuspencion;
    @BindView(R.id.img_foto)
    ImageView imgFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    private SpinnerDialog spinnerDialogTipoGasto;
    private String rtgId, pathImage;

    Unbinder unbinder;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recibo_servicios_publicos, container, false);

        ArrayAdapter<String> adapterTipoServicio = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_servicio));
        spnTipoServicio.setAdapter(adapterTipoServicio);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoGasto = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoGasto.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        PushDownAnim.setPushDownAnimTo(btnGuardar, btnFoto, spnTipoGasto);

        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.spnTipoGasto, R.id.btnFoto, R.id.btnGuardar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnTipoGasto:
                break;
            case R.id.btnFoto:
                break;
            case R.id.btnGuardar:
                break;
        }
    }
}
