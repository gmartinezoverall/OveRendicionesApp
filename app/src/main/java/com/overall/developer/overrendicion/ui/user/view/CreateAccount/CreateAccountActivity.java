package com.overall.developer.overrendicion.ui.user.view.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.user.presenter.CreateAccount.CreateAccountPresenter;
import com.overall.developer.overrendicion.ui.user.presenter.CreateAccount.CreateAccountPresenterImpl;
import com.overall.developer.overrendicion.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion.utils.Util;

import static com.flaviofaria.kenburnsview.KenBurnsView.TransitionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import io.rmiri.buttonloading.ButtonLoading;

import android.util.Patterns;

/**
 * Created by terry on 3/9/2018.
 */

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView, TransitionListener {
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
    @BindView(R.id.image)
    KenBurnsView image;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;

    private  boolean imageState;
    private int mTransitionsCount = 0;
    private static final int TRANSITIONS_TO_SWITCH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mPresenter = new CreateAccountPresenterImpl(this);

        image.setTransitionListener(this);

        mBtnRegistrar.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick()
            {
                if (ValideWidgets())finishLoading();
                else mBtnRegistrar.setProgress(false);


            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });

        RxTextView.textChanges(mEtxEmail)
                .filter(etx -> (etx.length() > 0 && !Patterns.EMAIL_ADDRESS.matcher(etx).matches()))
                .subscribe(etx -> mEtxEmail.setError(getResources().getString(R.string.createEmailError)));

/*        mEtxEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                if (Util.isEmailValid(String.valueOf(mEtxEmail.getText())))
                {
                    mEtxEmail.setError(getResources().getString(R.string.createEmailError));

                }
            }
        });*/

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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        mBtnRegistrar.setProgress(false);
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void createAccountError(String message) {
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

    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition)
    {
        mTransitionsCount++;
        if (mTransitionsCount == TRANSITIONS_TO_SWITCH)
        {
            if (imageState)
            {
                image.setImageDrawable(getResources().getDrawable(R.drawable.login02));
                imageState = false;
            }
            else
            {
                image.setImageDrawable(getResources().getDrawable(R.drawable.login01));
                imageState = true;
            }

            mTransitionsCount = 0;
        }
    }

    private boolean ValideWidgets()
    {
        if (mEtxUserDni.getText().toString().isEmpty() || mEtxPassword.getText().toString().isEmpty() || mEtxEmail.getText().toString().isEmpty() || mEtxPhone.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(String.valueOf(mEtxEmail.getText())).matches())
        {
            Toast.makeText(this, getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }

}
