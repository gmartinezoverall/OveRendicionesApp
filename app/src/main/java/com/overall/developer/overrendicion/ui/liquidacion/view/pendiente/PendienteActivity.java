package com.overall.developer.overrendicion.ui.liquidacion.view.pendiente;


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
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.github.florent37.awesomebar.AwesomeBar;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente.PendientePresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente.PendientePresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.adapter.PendienteAdapter;
import com.overall.developer.overrendicion.utils.toolbarRippleEffect.RippleEffect;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.jvm.internal.Intrinsics;
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration;
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;
import xyz.sangcomz.stickytimelineview.model.SectionInfo;


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
    //endregion


    private TimeLineRecyclerView mRecyclerView;
    private List<LiquidacionBean> pendienteBeanList;
    private PendientePresenter mPresenter;
    private String dniUser = "";
    private String nombreUser = "";
    private String emailUser = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendiente);
        ButterKnife.bind(this);
        mPresenter = new PendientePresenterImpl(this, this);

        initialToolbar();
        sesionManager();
        initialDrawable();
        awsInitial();

        mRecyclerView = findViewById(R.id.rvPendiente);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (dniUser != null)mPresenter.listPendiente(String.valueOf(dniUser));//listar Liquidaciones Pendientes
        mPresenter.insertProvincia(dniUser);
        mPresenter.setAllDocument();//sets Provincias por Api
        mPresenter.setAllBanco();//sets Provincias por Api

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


    }

    private void awsInitial()
    {
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("AWS", "Conneccion exitosa a AWS :D");
            }
        }).execute();
    }

    //region Toolbar
    private void initialToolbar()
    {
        mSearchBar.setOnSearchActionListener(this);
        mSearchBar.inflateMenu(R.menu.main);
        mSearchBar.setCardViewElevation(5);
        mSearchBar.setTxvTipoBuscar("Estado");

        mTool_bar.addAction(R.drawable.awsb_ic_edit_animated, "Buscar");
        //tool_bar.setOverflowActionItemClickListener((position, item) -> Toast.makeText(getBaseContext(), item + " clicked", Toast.LENGTH_LONG).show());
        mTool_bar.setOnMenuClickedListener(v -> drawerLayout.openDrawer(Gravity.START));
        mTool_bar.displayHomeAsUpEnabled(false);
        mTool_bar.setActionItemClickListener((position, actionItem) ->   RippleEffect.ShowSearchBar(mTool_bar, mSearchBar, searchAppBarLayout));

        mSearchBar.getMenu().setOnMenuItemClickListener(item ->
        {
            mSearchBar.setTxvTipoBuscar(String.valueOf(item));
            return false;
        });
    }

    @Override
    public void onSearchStateChanged(boolean enabled)
    {
        if (!enabled) {
            RippleEffect.hideSearchBar(mTool_bar, mSearchBar, searchAppBarLayout);
            if (pendienteBeanList.size()==0)
                mPresenter.filterLiquidacionForUser(dniUser);

        }
    }

    @Override
    public void onSearchConfirmed(CharSequence text)
    {
        mPresenter.filterLiquidacion( String.valueOf(mSearchBar.getTxvTipoBuscar()), String.valueOf(text));

    }

    @Override
    public void onButtonClicked(int buttonCode)
    {
        switch (buttonCode)
        {
            case MaterialSearchBar.BUTTON_CLEAR:
                mPresenter.filterLiquidacionForUser(dniUser);
                break;
        }

    }


    //endregion

    //region SesionManager
    private void sesionManager()
    {
        UserBean userBean = mPresenter.getUser();
        dniUser = userBean.getNumDocBeneficiario();
        nombreUser = userBean.getNombre();
        emailUser = userBean.getEmail();
    }
    //endregion

    //region recylearView
    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List singerList) {
        return (new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return Intrinsics.areEqual(((LiquidacionBean) singerList.get(position)).getFechaPago().substring(0, 10), ((LiquidacionBean) singerList.get(position - 1)).getFechaPago().substring(0, 10)) ^ true;
            }

            @Nullable
            public SectionInfo getSectionHeader(int position) {
                return new SectionInfo(((LiquidacionBean) singerList.get(position)).getFechaPago().substring(0, 10), ((LiquidacionBean) singerList.get(position)).getNombre());
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
        } else if (id == R.id.nav_actContra) {

        } else if (id == R.id.nav_actCorreo) {

        } else if (id == R.id.nav_liqPend) {

        } else if (id == R.id.nav_reenbolso) {

        } else if (id == R.id.nav_sesion)
        {
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
    public void errorPendienteList(String message) {

    }


    @Override
    public void setListPendiente(List<LiquidacionBean> pendienteList) {
        pendienteBeanList = pendienteList;
        initialRecyclerView();
    }

    @Override
    public void searchListPendienteResult(List<LiquidacionBean> pendienteList)
    {
        pendienteBeanList = pendienteList;
        mRecyclerView.setAdapter(new PendienteAdapter(this, pendienteBeanList, this));
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_bottom);
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void setListPendienteForUser(List<LiquidacionBean> pendienteList)
    {
        pendienteBeanList = pendienteList;
        mRecyclerView.setAdapter(new PendienteAdapter(this, pendienteList, this));
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_bottom);
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }

    private void initialRecyclerView()
    {
        mRecyclerView.setAdapter(new PendienteAdapter(this, pendienteBeanList, this));
        mRecyclerView.addItemDecoration(this.getSectionCallback(pendienteBeanList));

        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(mRecyclerView.getContext(), R.anim.layout_slide_bottom);

        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();

    }

    //endregion

    //region Estados Actividad
    @Override
    protected void onResume() {
        super.onResume();

        Log.i("NDa","NDa");
/*        realmBrowser = new RealmBrowser();
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
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }
    //endregion
}
