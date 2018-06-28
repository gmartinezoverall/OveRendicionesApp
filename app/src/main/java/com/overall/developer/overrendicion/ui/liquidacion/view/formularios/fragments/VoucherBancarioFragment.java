package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.data.model.entity.formularioEntity.VoucherBancarioEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.adapterImage.ImageAdapter;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class VoucherBancarioFragment extends Fragment {

    @BindView(R.id.etxCalendar)
    CustomEditText etxCalendar;
    @BindView(R.id.spnBanco)
    TextView spnBanco;
    @BindView(R.id.spnTipoMoneda)
    NiceSpinner spnTipoMoneda;
    @BindView(R.id.spnTipoGasto)
    TextView spnTipoGasto;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.etxPrecioVenta)
    CustomEditText etxPrecioVenta;
    @BindView(R.id.etxNDocumento)
    CustomEditText etxNDocumento;
    @BindView(R.id.rcvFoto)
    RecyclerView rcvFoto;
    @BindView(R.id.btnFoto)
    ImageButton btnFoto;

    private SpinnerDialog spinnerDialogTipoDoc, spnDialogBanco;
    private String rtgId, banco;


    Unbinder unbinder;
    View mView;
    RendicionEntity rendicionEntity;
    ImageAdapter adapter;
    ArrayList<String> listImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_voucher_bancario, container, false);
        unbinder = ButterKnife.bind(this, mView);

        rendicionEntity = ((FormularioActivity) getContext()).getDefaultValues();
        if (rendicionEntity != null) setAllDefaultValues();

        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_moneda));
        spnTipoMoneda.setAdapter(adapterTipoMoneda);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(((FormularioActivity) getContext()).getListSpinner());
        spinnerDialogTipoDoc = new SpinnerDialog(getActivity(), itemList, getResources().getString(R.string.tittleSpinerTipoGasto));
        spinnerDialogTipoDoc.bindOnSpinerListener((item, position) ->
        {
            spnTipoGasto.setText(((TipoGastoEntity) item).getRtgDes().toString());
            rtgId = ((TipoGastoEntity) item).getRtgId().toString();
        });

        ArrayList<Object> itemListBanco = new ArrayList<>();
        itemListBanco.addAll(((FormularioActivity) getContext()).getAllBancos());
        spnDialogBanco = new SpinnerDialog(getActivity(), itemListBanco, getResources().getString(R.string.tittleSpinerBanco));
        spnDialogBanco.bindOnSpinerListener((item, position) ->
        {
            spnBanco.setText(((BancoEntity) item).getDesc().toString());
            banco = ((BancoEntity) item).getCode().toString();
        });

        etxCalendar.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog();
        });

        rcvFoto.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        adapter = new ImageAdapter(mView.getContext());
        rcvFoto.setAdapter(adapter);

        return mView;

    }

    private void setAllDefaultValues() {
        etxCalendar.setText(String.valueOf(rendicionEntity.getFechaDocumento()));
        etxNDocumento.setText(String.valueOf(rendicionEntity.getNumeroDoc()));
        //spnBanco.setText(String.valueOf(rendicionEntity.banco));
        spnTipoMoneda.setSelectedIndex((rendicionEntity.getTipoMoneda().equals("S") ? 0 : 1));
        etxPrecioVenta.setText(String.valueOf(rendicionEntity.getPrecioTotal()));
        //spnTipoGasto.setText(String.valueOf(rendicionEntity.tipo));

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

    @OnClick({R.id.spnBanco, R.id.spnTipoGasto, R.id.btnGuardar, R.id.btnFoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spnBanco:
                spnDialogBanco.showSpinerDialog();
                break;
            case R.id.spnTipoGasto:
                spinnerDialogTipoDoc.showSpinerDialog();
                break;
            case R.id.btnGuardar:
                String tipoMoneda = spnTipoMoneda.getSelectedIndex() == 0 ? "S" : "D";
                ((FormularioActivity) getContext()).saveAndSendData(((FormularioActivity) getContext()).getSelectTypoDoc(), new VoucherBancarioEntity(String.valueOf(((FormularioActivity) getContext()).getSelectTypoDoc()),
                        String.valueOf(etxCalendar.getText()), String.valueOf(etxNDocumento.getText()), String.valueOf(banco), String.valueOf(tipoMoneda), String.valueOf(etxPrecioVenta.getText()), String.valueOf(rtgId),
                        String.valueOf(listImage.get(0))));

                break;
            case R.id.btnFoto:
                Pix.start(this, 100, 5);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (100): {
                if (resultCode == Activity.RESULT_OK) {
                    listImage = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                    adapter.AddImage(listImage);
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
                    Pix.start(this, 100, 1);
                } else {
                    Toast.makeText(mView.getContext(), "Tiene que aprobar los permisos para seleccionar una Foto", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }
}
