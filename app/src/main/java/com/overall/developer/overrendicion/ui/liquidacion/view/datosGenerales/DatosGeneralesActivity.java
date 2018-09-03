package com.overall.developer.overrendicion.ui.liquidacion.view.datosGenerales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.awesomebar.AwesomeBar;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.DatosGenerales.DatosGeneralesPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.DatosGenerales.DatosGeneralesPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.RecoveryPasswordActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.UpdateEmailActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.angmarch.views.NiceSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rmiri.buttonloading.ButtonLoading;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;

import static maes.tech.intentanim.CustomIntent.customType;

public class DatosGeneralesActivity extends AppCompatActivity implements DatosGeneralesView, NavigationView.OnNavigationItemSelectedListener {
    //region Injeccion de vistas
    @BindView(R.id.toolbarDatosGenerales)
    AwesomeBar mToolbar;
    @BindView(R.id.drawer_layout_DG)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.txvDGMonto)
    TextView mTxvDGMonto;
    @BindView(R.id.txvDGAcuenta)
    TextView mTxvDGAcuenta;
    @BindView(R.id.txvDGSaldo)
    TextView mTxvDGSaldo;

    @BindView(R.id.calendarView)
    SectionCalendarView mCalendarView;
    @BindView(R.id.lytCalendar)
    LinearLayout mLayoutCalendar;
    @BindView(R.id.lytFecha)
    LinearLayout mLayoutFecha;
    @BindView(R.id.txvFInicio)
    TextView mTxvFInicio;
    @BindView(R.id.txvFFin)
    TextView mTxvFFin;
    @BindView(R.id.lytArrow)
    LinearLayout mlytArrow;
    @BindView(R.id.txvSysDate)
    TextView mTxvSysDate;
    @BindView(R.id.lytTipoViatico)
    LinearLayout mLytTipoViatico;
    @BindView(R.id.btnMotivo)
    Button mBtnMotivo;
    @BindView(R.id.lytMotivo)
    LinearLayout mLytMotivo;
    @BindView(R.id.btnSaveDate)
    ButtonLoading mbtnSaveDate;
    @BindView(R.id.spnDestinoViaje)
    TextView mSpnDestinoViaje;
    @BindView(R.id.txvDetMotivo)
    EditText mTxvDetMotivo;
    @BindView(R.id.spnTipoViatico)
    NiceSpinner mSpnTipoViatico;
    @BindView(R.id.txvTMonto)
    TextView txvTMonto;
    @BindView(R.id.txvTACuenta)
    TextView txvTACuenta;
    @BindView(R.id.txvTSaldo)
    TextView txvTSaldo;


    //endregion

    private DatosGeneralesPresenter mPresenter;
    private LiquidacionEntity mLiquidacionEntity;
    private UserBean userBean;
    private SpinnerDialog spinnerDialog;
    private String idProvincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales);
        ButterKnife.bind(this);

        mPresenter = new DatosGeneralesPresenterImpl(this, this);
        mLiquidacionEntity = mPresenter.getForCodLiquidacion(String.valueOf(getIntent().getStringExtra("CodLiquidacion")));
        userBean = mPresenter.getUser();
        initialDrawable();
        initialBase();
        initialCalendar();

        Log.i("NDaFecha", mTxvFInicio.getText().toString());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        mPresenter.changeStatusLiquidacion();
    }


    //region Calendar
    private void initialCalendar()
    {
        mCalendarView.setDateFormat("dd/MM/yyyy");
        mCalendarView.setPreventPreviousDate(false);
        mCalendarView.setErrToastMessage(R.string.error_date);
        mCalendarView.setOnDaySelectedListener((startDay, endDay) ->
        {
            mTxvFInicio.setText(startDay);
            mTxvFFin.setText(endDay.equals("") ? startDay : endDay);
            mTxvFFin.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            mTxvFFin.setTypeface(null, Typeface.BOLD);
            mTxvFInicio.setTypeface(null, Typeface.BOLD);
            mTxvFFin.setTextColor(getResources().getColor(R.color.black));
            mTxvFInicio.setTextColor(getResources().getColor(R.color.black));
            if (!endDay.equals("")) {
                mLayoutCalendar.setVisibility(mLayoutCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                mlytArrow.setRotation(mlytArrow.getRotation() == 90 ? 0 : 90);
            }
        });
        mCalendarView.buildCalendar();
    }
    //endregion

    //region IntialBase
    private void initialBase() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);

        mLytTipoViatico.setVisibility(mLiquidacionEntity.getDescripcionLiquidacion().equals("VIATICOS") ? View.VISIBLE : View.GONE);
        mTxvDGMonto.setText(String.valueOf(mLiquidacionEntity.getMonto()));
        mTxvDGAcuenta.setText(String.valueOf(mLiquidacionEntity.getaCuenta()));
        mTxvDGSaldo.setText(String.valueOf(mLiquidacionEntity.getSaldo()));
        PushDownAnim.setPushDownAnimTo(mbtnSaveDate, mBtnMotivo);


        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong ->
                {
                    mTxvDGMonto.setTextColor(getResources().getColor(R.color.white));
                    mTxvDGAcuenta.setTextColor(getResources().getColor(R.color.white));
                    mTxvDGSaldo.setTextColor(getResources().getColor(R.color.white));
                    txvTMonto.setTextColor(getResources().getColor(R.color.white));
                    txvTACuenta.setTextColor(getResources().getColor(R.color.white));
                    txvTSaldo.setTextColor(getResources().getColor(R.color.white));

                });


        ArrayAdapter<String> adapterTipoMoneda = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.tipo_liquidacion));
        mSpnTipoViatico.setAdapter(adapterTipoMoneda);

        if (mLiquidacionEntity.getMotivoViaje() != null)
            mTxvDetMotivo.setText(String.valueOf(mLiquidacionEntity.getMotivoViaje()));
        mTxvSysDate.setText(String.valueOf(formattedDate));
        if (mLiquidacionEntity.getFechaDesde() != null)
            mTxvFInicio.setText(String.valueOf(mLiquidacionEntity.getFechaDesde().substring(0, 10)));
        if (mLiquidacionEntity.getFechaHasta() != null)
            mTxvFFin.setText(String.valueOf(mLiquidacionEntity.getFechaHasta().substring(0, 10)));
        if (mLiquidacionEntity.getTipoViatico() != null)
            mSpnTipoViatico.setSelectedIndex(mLiquidacionEntity.getTipoViatico().equals("N") ? 0 : 1);
        if (mLiquidacionEntity.getUbigeoProvDestino() != null)
        {
            mSpnDestinoViaje.setText(mLiquidacionEntity.getUbigeoProvDestino().getDesc());
            idProvincia = (mLiquidacionEntity.getUbigeoProvDestino().getCode());
        }

        mToolbar.setOnMenuClickedListener(v -> mDrawerLayout.openDrawer(Gravity.START));
        mToolbar.displayHomeAsUpEnabled(false);

        ArrayList<Object> itemList = new ArrayList<>();
        itemList.addAll(mPresenter.listProvinciaForSpinner());

        spinnerDialog = new SpinnerDialog(this, itemList, "Selecciona una Provincia");
        spinnerDialog.bindOnSpinerListener((item, position) ->
        {
            mSpnDestinoViaje.setText(((ProvinciaEntity)item).getDesc().toString());
            idProvincia = ((ProvinciaEntity)item).getCode().toString();
        });

    }
    //endregion


    //region NavigationView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initialDrawable() {
        NavigationView navigationView = findViewById(R.id.nav_view_DG);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView txvUserDNI = view.findViewById(R.id.txvUserNombre);
        TextView txvUseEmail = view.findViewById(R.id.txvUserEmail);
        txvUserDNI.setText(String.valueOf(userBean.getNumDocBeneficiario()));
        txvUseEmail.setText(String.valueOf(userBean.getEmail()));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_soliRend) {
            // Handle the camera action
        } else if (id == R.id.nav_actContra)
        {
            startActivity(new Intent(this, RecoveryPasswordActivity.class));

        } else if (id == R.id.nav_actCorreo)
        {
            startActivity(new Intent(this, UpdateEmailActivity.class));

        } else if (id == R.id.nav_liqPend) {

            startActivity(new Intent(this, PendienteActivity.class));

        } else if (id == R.id.nav_reenbolso) {

        } else if (id == R.id.nav_sesion) {
            mPresenter.finisLogin();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_DG);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion

    //region Click
    @OnClick({R.id.btnMotivo, R.id.btnSaveDate, R.id.lytFecha, R.id.spnDestinoViaje})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMotivo:

                mBtnMotivo.setText(String.valueOf(mBtnMotivo.getText()).equals("GUARDAR") ? "EDITAR" : "GUARDAR");
                mLytMotivo.setVisibility(mLytMotivo.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mBtnMotivo.getWindowToken(), 0);

                break;

            case R.id.lytFecha:

                mLayoutCalendar.setVisibility(mLayoutCalendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                mlytArrow.setRotation(mlytArrow.getRotation() == 90 ? 0 : 90);

                break;

            case R.id.btnSaveDate:

                if (ValideWidgets()) {

                    mPresenter.saveData(String.valueOf(mLiquidacionEntity.getCodLiquidacion()), String.valueOf(mTxvSysDate.getText())
                            , String.valueOf(mTxvDetMotivo.getText()), idProvincia, String.valueOf(mTxvFInicio.getText())
                            , String.valueOf(mTxvFFin.getText()), String.valueOf(mSpnTipoViatico.getText()));

                    Intent intent = new Intent(this, RendicionActivity.class);
                    intent.putExtra("CodLiquidacion", String.valueOf(mLiquidacionEntity.getCodLiquidacion()));
                    startActivity(intent);
                    customType(this, "fadein-to-fadeout");
                }
                break;


            case R.id.spnDestinoViaje:
                spinnerDialog.showSpinerDialog();

                break;
        }
    }

    private boolean ValideWidgets()
    {
        if (mTxvDetMotivo.getText().toString().isEmpty() || (mLytTipoViatico.getVisibility() == View.VISIBLE && mSpnDestinoViaje.getText().equals("Seleccionar"))
                || mTxvFInicio.getText().equals("0001-01-01"))
        {
            Toast.makeText(this, getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;

    }
    //endregion


}


