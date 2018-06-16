package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bluehomestudio.progresswindow.ProgressWindow;
import com.bluehomestudio.progresswindow.ProgressWindowConfiguration;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter.RendicionAdapter;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.OnActivityTouchListener;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.RecyclerTouchListener;
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static maes.tech.intentanim.CustomIntent.customType;


/**
 * Created by cesar on 3/25/2018.
 */

public class RendicionActivity extends AppCompatActivity implements RendicionView, OnFABMenuSelectedListener {

    @BindView(R.id.rcvRendicion)
    RecyclerView rcvRendicion;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fabMenu)
    FABRevealMenu fabMenu;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    RendicionPresenter mPresenter;
    private RealmBrowser realmBrowser;
    private LiquidacionEntity mLiquidacionEntity;

    List<RendicionEntity> entityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendicion);
        ButterKnife.bind(this);

        mPresenter = new RendicionPresenterImpl(this, this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) mLiquidacionEntity = mPresenter.getForCodLiquidacion(String.valueOf(bundle.getString("CodLiquidacion")));

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
    public void onMenuItemSelected(View view, int id)
    {
        if (id == R.id.menu_add)
        {
            startActivity(new Intent(this, FormularioActivity.class));
            customType(this, "fadein-to-fadeout");

        } else if (id == R.id.menu_list)
        {
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
    public void getListRendicion(List<RendicionEntity> rendicionList)
    {
        entityList = rendicionList;

    }

    @Override
    public void updateListRendicion(List<RendicionEntity> rendicionBeans)
    {
        rcvRendicion.setAdapter(new RendicionAdapter(this, rendicionBeans));
        rcvRendicion.getAdapter().notifyDataSetChanged();
        if (rendicionBeans.isEmpty())
        {
            startActivity(new Intent(this, FormularioActivity.class));
            customType(this, "fadein-to-fadeout");
            finish();
        }

    }
}



