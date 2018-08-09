package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.github.florent37.awesomebar.AwesomeBar;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenterImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter.RendicionAdapter;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.contentFoto.FotoContentActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.RecoveryPasswordActivity;
import com.overall.developer.overrendicion.ui.user.view.Drawable.UpdateEmailActivity;
import com.overall.developer.overrendicion.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    RendicionPresenter mPresenter;
    private RealmBrowser realmBrowser;
    private LiquidacionEntity mLiquidacionEntity;
    private RecyclerView.Adapter mAdapter;

    List<RendicionEntity> mRendicionList = new ArrayList<>();
    List<RendicionDetalleEntity> mMovilidadList = new ArrayList<>();
    String nombreUser;
    String emailUser;
    ImageView imgFoto;
    String pathImage;

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

    //region Estados Actividad
    @Override
    protected void onResume() {
        super.onResume();
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(this);

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
        } else if (id == R.id.menu_refresh)
        {
            mPresenter.listRendicion();
            Log.i("Rendicion","Listando");

        } else if (id == R.id.menu_sync) {

        }

    }
    //endregion

    @Override
    public void getListRendicion(List<RendicionEntity> rendicionList)
    {
        this.mRendicionList = rendicionList;

    }

    @Override
    public void updateListRendicion(List<RendicionEntity> rendicionBeans) {
        mRendicionList = rendicionBeans;
        usedAdapter();
    }

    @Override
    public void getListMovilidad(List<RendicionDetalleEntity> listMovilidad)
    {
        mMovilidadList = listMovilidad;
        usedAdapter();
    }

    @Override
    public void deleteDetMovSuccess(List<RendicionEntity> entityList, List<RendicionDetalleEntity> detalleEntityList)
    {

    }

    private void usedAdapter()
    {
        rcvRendicion.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RendicionAdapter(this, this, mRendicionList, mMovilidadList, (view, position) -> {
            switch (view.getId())
            {
                case R.id.lytEdit:
                    Intent intent = new Intent(view.getContext(), FormularioActivity.class);
                    intent.putExtra("idRendicion", String.valueOf(mRendicionList.get(position).getIdRendicion()));
                    customType(view.getContext(), "fadein-to-fadeout");
                    startActivity(intent);
                    break;

                case R.id.lytNew:
                    Intent intent2 = new Intent(view.getContext(), FormularioActivity.class);
                    //intent2.putExtra("defaultRtg", String.valueOf(rendicionList.get(position).getRtgId()));
                    intent2.putExtra("defaultRtg", "10");
                    customType(view.getContext(), "fadein-to-fadeout");
                    startActivity(intent2);
                    break;

                case R.id.lytRemove:
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.tittleDialog);
                    builder.setMessage(R.string.messageDialog);
                    builder.setPositiveButton(R.string.btnPositive, (dialog, id) ->
                    {
                        mPresenter.deleteRendicionForCod(mRendicionList.get(position).getIdRendicion());
                        mRendicionList.remove(position);
                        mAdapter.notifyItemRemoved(position);

                    });
                    builder.setNegativeButton(R.string.btnNegative, (dialog, id) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    break;

                case R.id.lytRemoveDet:

                    mPresenter.deleteDetMovForCod(position);
                    mPresenter.listRendicion();

                    break;

                case R.id.lytFoto:

                    showCustomDialog(mRendicionList.get(position).getCodRendicion());


                    break;

            }
        });

        ((RendicionAdapter) mAdapter).setMode(Attributes.Mode.Single);
        rcvRendicion.setAdapter(mAdapter);
    }
    //region CustomDialog
    private void showCustomDialog(String codRendicion)
    {

        Dialog mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_foto);
        ImageButton btnFoto = mDialog.findViewById(R.id.btnFotoDialog);
        btnFoto.setOnClickListener(v-> Pix.start(this, 100, 1));
        imgFoto = mDialog.findViewById(R.id.imgFoto);
        TextView txvCancelar = mDialog.findViewById(R.id.txvCancelar);
        txvCancelar.setOnClickListener(v -> mDialog.dismiss());
        TextView txvGuardar = mDialog.findViewById(R.id.txvGuardar);
        txvGuardar.setOnClickListener(v ->
        {
            mPresenter.sendDataPhote(codRendicion, pathImage);
            mDialog.dismiss();

        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        PushDownAnim.setPushDownAnimTo(btnFoto);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (100): {
                if (resultCode == Activity.RESULT_OK) {
                    File imageFile = new File(data.getStringArrayListExtra(Pix.IMAGE_RESULTS).get(0));

                    new Compressor(this)
                            .setQuality(95)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .compressToFileAsFlowable(imageFile)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(file ->
                            {
                                imgFoto.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                                pathImage = file.getAbsolutePath();

                            }, throwable ->
                            {
                                Log.i("ErrorCompressImage", throwable.getMessage());
                            });

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
                    Pix.start(this, 100, getResources().getInteger(R.integer.numFotos));
                } else {
                    Toast.makeText(this, "Tiene que aprobar los permisos para seleccionar una Foto", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
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
        } else if (id == R.id.nav_actContra)
        {
            startActivity(new Intent(this, RecoveryPasswordActivity.class));

        } else if (id == R.id.nav_actCorreo)
        {
            startActivity(new Intent(this, UpdateEmailActivity.class));

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

    public interface ItemClick
    {
        void onClick(View view, int position);
    }

}



