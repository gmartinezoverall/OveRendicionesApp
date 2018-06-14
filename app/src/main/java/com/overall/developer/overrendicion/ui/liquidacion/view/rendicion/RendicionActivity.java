package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;


import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter.RendicionAdapter;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.OnActivityTouchListener;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.recyclerView.RecyclerTouchListener;
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static maes.tech.intentanim.CustomIntent.customType;


/**
 * Created by cesar on 3/25/2018.
 */

public class RendicionActivity extends AppCompatActivity implements RendicionView {

    @BindView(R.id.rcvRendicion)
    RecyclerView rcvRendicion;
    @BindView(R.id.fbAgregar)
    FloatingActionButton fbAgregar;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    RendicionPresenter mPresenter;
    private RealmBrowser realmBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendicion);
        ButterKnife.bind(this);

        mPresenter = new RendicionPresenterImpl(this, this);

        List<RendicionEntity> entityList = mPresenter.listRendicion();

        rcvRendicion.setAdapter(new RendicionAdapter(this,entityList));
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
                    if (viewID == R.id.edit)
                    {
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

    //endregion

    @OnClick(R.id.fbAgregar)
    public void onViewClicked()
    {
        startActivity(new Intent(this, FormularioActivity.class));
        customType(this, "fadein-to-fadeout");

    }
    //endregion

}




/*
        Toolbar mToolbar = findViewById(R.id.);
        final TextView mTextView =  mToolbar.findViewById(R.id.tv_title);
        final TextView mTextView2 = mToolbar.findViewById(R.id.txvTittle2);
        final TextView mTextView3 = mToolbar.findViewById(R.id.txvTittle3);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextView.setTransitionName("monto");
            mTextView2.setTransitionName("acuenta");
            mTextView3.setTransitionName("saldo");
        }*/

/*
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        final TextView mTextView = (TextView) toolbar.findViewById(R.id.toolbar);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {

            mTextView.setTransitionName("Toolbar");
        }

    }*/

