package com.overall.developer.overrendicion.ui.liquidacion.view.formularios;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.dropdownview.DropDownView;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios.FormularioPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Formularios.FormularioPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.ArrendamientoFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.BoletaVentaFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.BoletoAereoFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.BoletoTerrestreFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.CartaPorteAereoFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.DescuentoBoletaFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.FacturaFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.MovilidadIndividualFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.MovilidadMultipleFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.NotaCreditoFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.OtrosDocumentosFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.ReciboHonorariosFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.SinSustentoTributarioFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.TicketMaquinaRegistradoraFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.VoucherBancarioFragment;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;
import com.overall.developer.overrendicion.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static maes.tech.intentanim.CustomIntent.customType;


public class FormularioActivity extends AppCompatActivity implements FormularioView {

    //region Injeccion de vistas
    @BindView(R.id.dropdownview)
    DropDownView dropdownview;
    @BindView(R.id.fytFormularios)
    FrameLayout mFytFormularios;
    @BindView(R.id.txvCodLiquidacion)
    TextView txvCodLiquidacion;
    @BindView(R.id.txvTitulo)
    TextView txvTitulo;
    @BindView(R.id.txvDNI)
    TextView txvDNI;
    //endregion

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FormularioPresenter mPresenter;
    private RendicionEntity rendicionEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ButterKnife.bind(this);
        mPresenter = new FormularioPresenterImpl(this, this);

        txvCodLiquidacion.setText(mPresenter.getDefaultValues().get(0));//devuelvo el codigo de liquidaicon
        txvTitulo.setText("Formularios");
        txvDNI.setText("46454545");

        List<String> yourFilterList = Arrays.asList(getResources().getStringArray(R.array.formularios));

        fragmentManager = getSupportFragmentManager();
        dropdownview.setDropDownListItem(yourFilterList);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            rendicionEntity = mPresenter.setRendicionForEdit(String.valueOf(bundle.getString("idRendicion")));//se llenaron los datos del formulario automaticamente
            dropdownview.setSelectingPosition(Util.getFragmentForRdoId(Integer.valueOf(rendicionEntity.getRdoId())));//formulario para editar
        } else dropdownview.setSelectingPosition(6);//formulario por defecto


        replaceFragment(dropdownview.getSelectingPosition());

        dropdownview.setOnSelectionListener((view, position) ->
        {
            replaceFragment(dropdownview.getSelectingPosition());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(dropdownview.getWindowToken(), 0);

        });


    }

    private void replaceFragment(int nameFragment) {
        Fragment fragment;
        fragment = fragmentManager.findFragmentById(R.id.fytFormularios);

        if (nameFragment == 0) fragment = new ArrendamientoFragment();
        if (nameFragment == 1) fragment = new BoletaVentaFragment();
        if (nameFragment == 2) fragment = new BoletoAereoFragment();
        if (nameFragment == 3) fragment = new BoletoTerrestreFragment();
        if (nameFragment == 4) fragment = new CartaPorteAereoFragment();
        if (nameFragment == 5) fragment = new DescuentoBoletaFragment();
        if (nameFragment == 6) fragment = new FacturaFragment();
        if (nameFragment == 7) fragment = new MovilidadIndividualFragment();
        if (nameFragment == 8) fragment = new NotaCreditoFragment();
        if (nameFragment == 9) fragment = new OtrosDocumentosFragment();
        if (nameFragment == 10) fragment = new ReciboHonorariosFragment();
        if (nameFragment == 11) fragment = new MovilidadMultipleFragment();
        if (nameFragment == 12) fragment = new SinSustentoTributarioFragment();
        if (nameFragment == 13) fragment = new TicketMaquinaRegistradoraFragment();
        if (nameFragment == 14) fragment = new VoucherBancarioFragment();


        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fytFormularios, fragment, "fragments");
        fragmentTransaction.commit();

    }

    public List<String> getDefaultValues() {
        return mPresenter.getDefaultValues();//devuelve la lista
    }

    public List<TipoGastoEntity> getListSpinner() {
        return mPresenter.getDocumentForId(String.valueOf(Util.getIdFragment(dropdownview.getSelectingPosition())));
    }

    public List<String> getListProvinciaDestino() {
        return mPresenter.getProvinciaDestinoList();
    }

    public int getSelectTypoDoc() {
        return Util.getIdFragment(dropdownview.getSelectingPosition());
    }

    public void saveAndSendData(int idFragment, Object objectDinamyc) {
        List<String> typeFragment = new ArrayList<>();
        typeFragment.add(String.valueOf(idFragment));
        typeFragment.add(String.valueOf(dropdownview.getFilterTextView().getText()));
        mPresenter.saveData(typeFragment, objectDinamyc);
    }


    @Override
    public void saveDataSuccess() {
        startActivity(new Intent(this, RendicionActivity.class));
        customType(this, "fadein-to-fadeout");

    }

    @Override
    public void setCodRendicionSuccess(String codRendicion)
    {
        //Toast.makeText(this, codRendicion, Toast.LENGTH_LONG).show();
    }
}
