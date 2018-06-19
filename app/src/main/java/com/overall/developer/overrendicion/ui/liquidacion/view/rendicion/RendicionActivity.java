package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.florent37.awesomebar.AwesomeBar;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter.RendicionAdapter;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.OnActivityTouchListener;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.RecyclerTouchListener;
import com.overall.developer.overrendicion.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static maes.tech.intentanim.CustomIntent.customType;


/**
 * Created by cesar on 3/25/2018.
 */

public class RendicionActivity extends AppCompatActivity implements RendicionView, OnFABMenuSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rcvRendicion)
    RecyclerView rcvRendicion;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fabMenu)
    FABRevealMenu fabMenu;
    @BindView(R.id.txvCodLiquidacion)
    TextView txvCodLiquidacion;
    @BindView(R.id.nav_view_rendicion)
    NavigationView navViewRendicion;
    @BindView(R.id.drawer_layout_rendicion)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbarRendiciones)
    AwesomeBar mToolbar;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    RendicionPresenter mPresenter;
    private RealmBrowser realmBrowser;
    private LiquidacionEntity mLiquidacionEntity;

    List<RendicionEntity> entityList = new ArrayList<>();
    String nombreUser;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendicion);
        ButterKnife.bind(this);

        mPresenter = new RendicionPresenterImpl(this, this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mLiquidacionEntity = mPresenter.getForCodLiquidacion(String.valueOf(bundle.getString("CodLiquidacion")));
            txvCodLiquidacion.setText(String.valueOf(mLiquidacionEntity.getCodLiquidacion()));
        }
        sesionManager();
        initialDrawable();
        mPresenter.listRendicion();

        rcvRendicion.setAdapter(new RendicionAdapter(this, entityList));
        rcvRendicion.setLayoutManager(new LinearLayoutManager(this));

        onTouchListener = new RecyclerTouchListener(this, rcvRendicion);
        onTouchListener
                .setIndependentViews(R.id.rowButton)
                .setViewsToFade(R.id.rowButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //Toast.makeToast(getApplicationContext(), "Row " + (position + 1) + " clicked!");
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        //ToastUtil.makeToast(getApplicationContext(), "Button in row " + (position + 1) + " clicked!");
                    }
                })
                .setSwipeOptionViews(R.id.btnDetalle, R.id.edit, R.id.lytRemove)
                .setSwipeable(R.id.rowFG, R.id.rowBG, (viewID, position) -> {
                    String message = "";
                    if (viewID == R.id.edit) {
                        Intent intent = new Intent(this, FormularioActivity.class);
                        intent.putExtra("idRendicion", String.valueOf(entityList.get(position).getIdRendicion()));
                        customType(this, "fadein-to-fadeout");
                        startActivity(intent);

                    } else if (viewID == R.id.btnDetalle) {
                        message += "detalle";
                    } else if (viewID == R.id.lytRemove) {

                        mPresenter.deleteRendicionForCod(entityList.get(position).getIdRendicion());
                        entityList.remove(position);
                        rcvRendicion.getAdapter().notifyItemRemoved(position);


                    }

                });
        try {
            if (fab != null && fabMenu != null) {
                //setFabMenu(fabMenu);
                //attach menu to fab
                fabMenu.bindAnchorView(fab);
                //set menu selection listener
                fabMenu.setOnFABMenuSelectedListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }


    //region Estados Actividad
    @Override
    protected void onResume() {
        super.onResume();
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(this);
        rcvRendicion.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (realmBrowser != null) {
            realmBrowser.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        rcvRendicion.removeOnItemTouchListener(onTouchListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.changeStatusLiquidacion();
    }
    //endregion


    @Override
    public void onMenuItemSelected(View view, int id) {
        if (id == R.id.menu_add) {
            startActivity(new Intent(this, FormularioActivity.class));
            customType(this, "fadein-to-fadeout");

        } else if (id == R.id.menu_list) {
            mPresenter.changeStatusLiquidacion();
            startActivity(new Intent(this, PendienteActivity.class));
            customType(this, "fadein-to-fadeout");
            finish();
        } else if (id == R.id.menu_refresh) {

        } else if (id == R.id.menu_sync) {

        }

    }
    //endregion

    @Override
    public void getListRendicion(List<RendicionEntity> rendicionList) {
        entityList = rendicionList;

    }

    @Override
    public void updateListRendicion(List<RendicionEntity> rendicionBeans) {
        entityList = rendicionBeans;
        rcvRendicion.setAdapter(new RendicionAdapter(this, entityList));
        rcvRendicion.getAdapter().notifyDataSetChanged();
        if (rendicionBeans.isEmpty()) {
            startActivity(new Intent(this, FormularioActivity.class));
            customType(this, "fadein-to-fadeout");
        }
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
        NavigationView navigationView = findViewById(R.id.nav_view_rendicion);
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

        } else if (id == R.id.nav_sesion)
        {
            startActivity(new Intent(this, LoginActivity.class));
            customType(this, "fadein-to-fadeout");
            mPresenter.finisLogin();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_rendicion);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion
}



