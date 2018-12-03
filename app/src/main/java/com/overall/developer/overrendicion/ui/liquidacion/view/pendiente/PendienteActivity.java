package com.overall.developer.overrendicion.ui.liquidacion.view.pendiente;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.awesomebar.AwesomeBar;
import com.jaredrummler.android.widget.AnimatedSvgView;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente.PendientePresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente.PendientePresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.adapter.PendienteAdapter;
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.ReembolsoActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.RecoveryPasswordActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.UpdateEmailActivity;
import com.overall.developer.overrendicion.utils.Util;
import com.overall.developer.overrendicion.utils.background.InitialServiceBrodcast;
import com.overall.developer.overrendicion.utils.background.SendDataService;
import com.overall.developer.overrendicion.utils.toolbarRippleEffect.RippleEffect;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration;
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;
import xyz.sangcomz.stickytimelineview.model.SectionInfo;

import static com.overall.developer.overrendicion.utils.background.InitialServiceBrodcast.sendDataOffLine;

public class PendienteActivity extends AppCompatActivity implements PendienteView, NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener {

    //region Injeccion de Vistas
    @BindView(R.id.toolbarSearch)
    MaterialSearchBar mSearchBar;
    @BindView(R.id.tool_bar)
    AwesomeBar mTool_bar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.appBar)
    AppBarLayout mBarLayout;
    @BindView(R.id.layout_appbar_search)
    View searchAppBarLayout;
    @BindView(R.id.pullRefresh)
    SmartRefreshLayout pullRefresh;
    @BindView(R.id.refresh)
    ClassicsHeader refresh;

    //endregion

    private TimeLineRecyclerView mRecyclerView;
    private List<LiquidacionBean> pendienteBeanList;
    private PendientePresenter mPresenter;
    private String dniUser = "";
    private String nombreUser = "";
    private String emailUser = "";
    Dialog mDialog, mDialogPhono;
    //private RealmBrowser realmBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendiente);
        ButterKnife.bind(this);


        mPresenter = new PendientePresenterImpl(this, this);

        sesionManager();
        initialDrawable();

        mRecyclerView = findViewById(R.id.rvPendiente);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (!mPresenter.checkingPhone()) PendienteActivity.this.runOnUiThread(() -> showDialogTelefono());
        else
        {
            if (dniUser != null) mPresenter.listPendiente(String.valueOf(dniUser));//listar Liquidaciones Pendientes
        }

        mPresenter.insertProvincia(dniUser);

        mPresenter.initialDefaultApis();

        refresh.REFRESH_HEADER_PULLDOWN="Continua Arrastrando";
        refresh.REFRESH_HEADER_REFRESHING="Actualizando..";
        refresh.REFRESH_HEADER_RELEASE="Suelta para Actualizar";
        refresh.REFRESH_HEADER_FINISH="Completado";

        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        refresh.setTimeFormat(mdformat);

        pullRefresh.setOnRefreshListener(refreshLayout ->
        {
            mPresenter.refreshList(String.valueOf(dniUser));
            showDialog();

        });


 /*       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());*/

    }


    //region Toolbar
    private void initialToolbar() {
        mSearchBar.setOnSearchActionListener(this);
        mSearchBar.inflateMenu(R.menu.main);
        mSearchBar.setCardViewElevation(5);
        mSearchBar.setTxvTipoBuscar("Descripcion");

        mTool_bar.addAction(R.drawable.ic_search, "Buscar");
        //tool_bar.setOverflowActionItemClickListener((position, item) -> Toast.makeText(getBaseContext(), item + " clicked", Toast.LENGTH_LONG).show());
        mTool_bar.setOnMenuClickedListener(v -> drawerLayout.openDrawer(Gravity.START));
        mTool_bar.displayHomeAsUpEnabled(false);
        mTool_bar.setActionItemClickListener((position, actionItem) -> RippleEffect.ShowSearchBar(mTool_bar, mSearchBar, searchAppBarLayout));
        if (pendienteBeanList != null)mTool_bar.setTextCount(String.valueOf("Pendientes:  " + Integer.valueOf(pendienteBeanList.size())));
        mSearchBar.getMenu().setOnMenuItemClickListener(item ->
        {
            mSearchBar.setTxvTipoBuscar(String.valueOf(item));
            return false;
        });
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        if (!enabled) {
            RippleEffect.hideSearchBar(mTool_bar, mSearchBar, searchAppBarLayout);
            if (pendienteBeanList.size() == 0)
                mPresenter.filterLiquidacionForUser(dniUser);

        }
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        mPresenter.filterLiquidacion(String.valueOf(mSearchBar.getTxvTipoBuscar()), String.valueOf(text));

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_CLEAR:
                mPresenter.filterLiquidacion(String.valueOf(mSearchBar.getTxvTipoBuscar()), "");
                break;
        }

    }
    //endregion

    //region SesionManager
    private void sesionManager() {
        UserBean userBean = mPresenter.getUser();
        dniUser = userBean.getNumDocBeneficiario();
        nombreUser = userBean.getNombre();
        emailUser = userBean.getEmail();
    }
    //endregion

    //region recylearView
    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(List<LiquidacionBean> liquidacionList) {
        return (new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                //return ((liquidacionList.get(position)).getFechaPago().substring(0, 10)) != ((liquidacionList.get(position-1)).getFechaPago().substring(0, 10));
                return Intrinsics.areEqual((liquidacionList.get(position)).getFechaPago().substring(0, 10), (liquidacionList.get(position - 1)).getFechaPago().substring(0, 10)) ^ true;
            }

            @Nullable
            public SectionInfo getSectionHeader(int position) {
                return new SectionInfo((liquidacionList.get(position)).getFechaPago().substring(0, 10), (liquidacionList.get(position)).getFechaPago().substring(0, 10));
            }
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView txvUserDNI = view.findViewById(R.id.txvUserNombre);
        TextView txvUseEmail = view.findViewById(R.id.txvUserEmail);
        txvUserDNI.setText(String.valueOf(nombreUser));
        txvUseEmail.setText(String.valueOf(emailUser));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_soliRend) {
            // Handle the camera action
        } else if (id == R.id.nav_sendResume) {
            //mPresenter.sendResumeEmail();

        } else if (id == R.id.nav_actContra) {
           startActivity(new Intent(this, RecoveryPasswordActivity.class));

        } else if (id == R.id.nav_actCorreo) {
           startActivity(new Intent(this, UpdateEmailActivity.class));

        } else if (id == R.id.nav_liqPend) {
            showDialog();
            mPresenter.refreshList(String.valueOf(dniUser));


        } else if (id == R.id.nav_reenbolso)
        {
            startActivity(new Intent(this, ReembolsoActivity.class));

        } else if (id == R.id.nav_sesion) {
            mPresenter.finisLogin();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion




    //region Interfaces
    @Override
    public void successPendienteList(String message) {

    }

    @Override
    public void refreshListSuccess(List<LiquidacionBean> listPendiente)
    {
        timerInterval();
        pendienteBeanList = listPendiente;
        if (mRecyclerView == null)mRecyclerView.getAdapter().notifyDataSetChanged();


    }

    @Override
    public void errorPendienteList(String message) {

    }


    @Override
    public void setListPendiente(List<LiquidacionBean> pendienteList) {
        if (pendienteList.size() > 0) {
            pendienteBeanList = pendienteList;
            mRecyclerView.setAdapter(new PendienteAdapter(this, pendienteBeanList, this));
            mRecyclerView.addItemDecoration(this.getSectionCallback(pendienteBeanList));
            final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_bottom);
            mRecyclerView.setLayoutAnimation(controller);
            mRecyclerView.getAdapter().notifyDataSetChanged();
            mRecyclerView.scheduleLayoutAnimation();
        } else {
            Toast.makeText(this, getResources().getString(R.string.messagePendienteListNull), Toast.LENGTH_LONG).show();
        }
        timerInterval();
        initialToolbar();
    }

    @Override
    public void searchListPendienteResult(List<LiquidacionBean> pendienteList) {
        pendienteBeanList = pendienteList;
        initialRecyclerView();

    }

    @Override
    public void setListPendienteForUser(List<LiquidacionBean> pendienteList) {
        pendienteBeanList = pendienteList;
        initialRecyclerView();
    }

    @Override
    public void successSendResume() { timerInterval(); }

    @Override
    public void errorSendResume() { timerInterval(); }


    private void initialRecyclerView() {
        mRecyclerView.setAdapter(new PendienteAdapter(this, pendienteBeanList, this));
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_bottom);
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();

    }

    public void sendResumeEmail(String codRendicion)
    {
        mPresenter.sendResumeEmail(codRendicion);
        showDialog();
    }

    public boolean validateRendicionisEmpy(String codLiquidacion)
    {
        return mPresenter.validateRendicionisEmpy(codLiquidacion);
    }

    //endregion

    //region Estados Actividad
    @Override
    protected void onResume() {
        super.onResume();


            showDialog();
            mPresenter.refreshList(String.valueOf(dniUser));
            if (Util.isOnline())sendDataOffLine();



/*        Log.i("NDa","NDa");
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(this);*/
    }


    @Override
    protected void onStop() {
        super.onStop();

/*        if (realmBrowser != null) {
            realmBrowser.stop();
        }*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent service = new Intent(this, SendDataService.class);
        startService(service);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }
    //endregion

    //region Dialog
    private void showDialog()
    {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_progress);
        AnimatedSvgView svgView = mDialog.findViewById(R.id.animated_svg_view);
        //svgView.postDelayed(() -> svgView.start(), 200);

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.backgroundtext_card)));
        mDialog.setCancelable(false);
        mDialog.show();

        Observable.interval(500, 3500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timer-> svgView.start());
    }

    private void showDialogTelefono()
    {

        mDialogPhono = new Dialog(this);
        mDialogPhono.setContentView(R.layout.dialog_telefono);
        EditText etxNumeroTel = mDialogPhono.findViewById(R.id.etxNumeroTel);
        TextView txvGuardar = mDialogPhono.findViewById(R.id.txvGuardar);
        txvGuardar.setOnClickListener(v ->
        {
            if (!etxNumeroTel.getText().toString().isEmpty())
            {
                mPresenter.saveTelefono(String.valueOf(etxNumeroTel.getText()));
                mDialogPhono.dismiss();
                if (dniUser != null) mPresenter.listPendiente(String.valueOf(dniUser));//listar Liquidaciones Pendientes
            }

        });

        mDialogPhono.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.backgroundtext_card)));
        mDialogPhono.setCancelable(false);
        mDialogPhono.show();

    }


    void timerInterval()
    {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribe(timer ->
                {
                    pullRefresh.finishRefresh();

                    mDialog.dismiss();
                });
    }

    //endregion
}
