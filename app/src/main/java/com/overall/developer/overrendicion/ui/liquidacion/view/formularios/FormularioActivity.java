package com.overall.developer.overrendicion.ui.liquidacion.view.formularios;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asksira.dropdownview.DropDownView;
import com.github.florent37.awesomebar.AwesomeBar;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.BancoEntity;
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
import com.overall.developer.overrendicion.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static maes.tech.intentanim.CustomIntent.customType;


public class FormularioActivity extends AppCompatActivity implements FormularioView, NavigationView.OnNavigationItemSelectedListener {

    //region Injeccion de vistas
    @BindView(R.id.dropdownview)
    DropDownView dropdownview;
    @BindView(R.id.fytFormularios)
    FrameLayout mFytFormularios;
    @BindView(R.id.txvTitulo)
    TextView txvTitulo;
    @BindView(R.id.txvMonto)
    TextView txvMonto;
    @BindView(R.id.txvAcuenta)
    TextView txvAcuenta;
    @BindView(R.id.txvSaldo)
    TextView txvSaldo;
    @BindView(R.id.toolbarFormularios)
    AwesomeBar mToolbar;
    @BindView(R.id.nav_view_formularios)
    NavigationView navViewFormularios;
    @BindView(R.id.drawer_layout_formularios)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.lytDropdownview)
    LinearLayout lytDropdownview;
    //endregion

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FormularioPresenter mPresenter;
    private RendicionEntity rendicionEntity;
    private String nombreUser, emailUser, codLiquidacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ButterKnife.bind(this);
        mPresenter = new FormularioPresenterImpl(this, this);

        List<String> listDefault = mPresenter.getDefaultValues();
        txvAcuenta.setText(listDefault.get(0));
        txvMonto.setText(listDefault.get(1));//devuelvo el codigo de liquidaicon
        txvSaldo.setText(listDefault.get(2));
        txvTitulo.setText("Formularios");


        List<String> typeFormList = Arrays.asList(getResources().getStringArray(R.array.formularios));
        sesionManager();
        initialDrawable();
        fragmentManager = getSupportFragmentManager();
        dropdownview.setDropDownListItem(typeFormList);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            rendicionEntity = mPresenter.setRendicionForEdit(String.valueOf(bundle.getString("idRendicion")));//se llenaron los datos del formulario automaticamente
            dropdownview.setSelectingPosition(Util.getFragmentForRdoId(Integer.valueOf(rendicionEntity.getRdoId())));//formulario para editar


        } else  dropdownview.setSelectingPosition(6);//formulario por defecto

        dropdownview.setOnSelectionListener((view, position) ->
        {
            replaceFragment(dropdownview.getSelectingPosition());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(dropdownview.getWindowToken(), 0);

        });

        codLiquidacion = mPresenter.getCodLiquidacion();

        replaceFragment(dropdownview.getSelectingPosition());

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

    public RendicionEntity getDefaultValues() {
        return rendicionEntity;//devuelve los valores por defecto
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

    public List<BancoEntity> getAllBancos() {
        return mPresenter.getAllBancos();
    }

    public void saveAndSendData(int idFragment, Object objectDinamyc) {
        List<String> typeFragment = new ArrayList<>();
        typeFragment.add(String.valueOf(idFragment));
        typeFragment.add(String.valueOf(dropdownview.getFilterTextView().getText()));
        if (rendicionEntity != null)
            typeFragment.add(String.valueOf(rendicionEntity.getIdRendicion()));
        mPresenter.saveData(typeFragment, objectDinamyc);
    }


    @Override
    public void saveDataSuccess() {
        startActivity(new Intent(this, RendicionActivity.class));
        customType(this, "fadein-to-fadeout");
        finish();
    }

    //region NavigationView

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initialDrawable() {
        mToolbar.setOnMenuClickedListener(v -> mDrawerLayout.openDrawer(Gravity.START));
        mToolbar.displayHomeAsUpEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view_formularios);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView txvUserDNI = view.findViewById(R.id.txvUserNombre);
        TextView txvUseEmail = view.findViewById(R.id.txvUserEmail);
        txvUserDNI.setText(String.valueOf(nombreUser));
        txvUseEmail.setText(String.valueOf(emailUser));

    }

    //region SesionManager
    private void sesionManager() {
        UserBean userBean = mPresenter.getUser();
        nombreUser = userBean.getNombre();
        emailUser = userBean.getEmail();
    }
    //endregion

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_soliRend) {
            // Handle the camera action
        } else if (id == R.id.nav_actContra) {

        } else if (id == R.id.nav_actCorreo) {

        } else if (id == R.id.nav_liqPend) {

        } else if (id == R.id.nav_reenbolso) {

        } else if (id == R.id.nav_sesion) {
            startActivity(new Intent(this, LoginActivity.class));
            customType(this, "fadein-to-fadeout");
            mPresenter.finisLogin();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_formularios);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion
}
