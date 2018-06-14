package com.overall.developer.overrendicion.ui.user.view.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.user.presenter.CreateAccount.CreateAccountPresenter;
import com.overall.developer.overrendicion.ui.user.presenter.CreateAccount.CreateAccountPresenterImpl;
import com.overall.developer.overrendicion.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import io.rmiri.buttonloading.ButtonLoading;

/**
 * Created by terry on 3/9/2018.
 */

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView
{
    //region Injeccion de Vistas
    @BindView(R.id.txvTittle)
    TextView mTxvTittle;
    @BindView(R.id.txvMessage)
    TextView mTxvMessage;
    @BindView(R.id.imgPerfil)
    ShapedImageView mImgPerfil;
    @BindView(R.id.etxUserDni)
    EditText mEtxUserDni;
    @BindView(R.id.etxEmail)
    EditText mEtxEmail;
    @BindView(R.id.etxPassword)
    EditText mEtxPassword;
    @BindView(R.id.etxPhone)
    EditText mEtxPhone;
    @BindView(R.id.btnRegistrar)
    ButtonLoading mBtnRegistrar;
    //endregion

    CreateAccountPresenter mPresenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mPresenter = new CreateAccountPresenterImpl(this);

        mBtnRegistrar.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener()
        {
            @Override
            public void onClick()
            {
                finishLoading();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });

    }

    //region Estados Actividad
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //endregion

    //region Interfaces

    @Override
    public void createAccountSuccess(String message)
    {
        mBtnRegistrar.setProgress(false);

        //sessionManager = new SessionManager(this);
        //sessionManager.createUserLoginSession(String.valueOf(mEtxUserDni.getText()), "NombreUser", String.valueOf(mEtxEmail.getText()));
        startActivity(new Intent(this, PendienteActivity.class));
    }

    @Override
    public void createAccountError(String message)
    {
        mBtnRegistrar.setProgress(false);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //endregion

    //region Timer
    void finishLoading() {
        //call setProgress(false) after 5 second
        new Handler().postDelayed(() -> mPresenter.createAccountApi(String.valueOf(mEtxUserDni.getText()), String.valueOf(mEtxPassword.getText()), String.valueOf(mEtxEmail.getText()), String.valueOf(mEtxPhone.getText())), 2000);
    }

    //endregion

}
