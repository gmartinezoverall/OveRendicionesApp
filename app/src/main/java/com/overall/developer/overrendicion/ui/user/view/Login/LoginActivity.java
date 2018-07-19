package com.overall.developer.overrendicion.ui.user.view.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
import com.overall.developer.overrendicion.ui.user.presenter.Login.LoginPresenter;
import com.overall.developer.overrendicion.ui.user.presenter.Login.LoginPresenterImpl;
import com.overall.developer.overrendicion.ui.user.view.CreateAccount.CreateAccountActivity;
import com.overall.developer.overrendicion.utils.CustomDialog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.lib.colordialog.ColorDialog;
import io.reactivex.Observable;
import io.rmiri.buttonloading.ButtonLoading;
import pl.droidsonroids.gif.GifDrawable;

import static maes.tech.intentanim.CustomIntent.customType;

import static com.flaviofaria.kenburnsview.KenBurnsView.TransitionListener;

/**
 * Created by terry on 3/9/2018.
 */

public class LoginActivity extends AppCompatActivity implements LoginView, TransitionListener {
    //region injeccion de Vistas
    @BindView(R.id.view_pager_frame)
    FrameLayout mViewPagerFrame;
    @BindView(R.id.etUsuario)
    EditText mEtUsuario;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnIngresar)
    ButtonLoading mBtnIngresar;
    @BindView(R.id.tvRecovery)
    TextView mTvRecovery;
    @BindView(R.id.tvRegister)
    TextView mTvRegister;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.view6)
    View mView6;
    @BindView(R.id.image)
    KenBurnsView image;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;

    //endregion

    private LoginPresenter mPresenter;
    private Animation uptodown, downtoup;
    private static final int TRANSITIONS_TO_SWITCH = 2;

    private  boolean imageState;

    private int mTransitionsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenterImpl(this, this);
        checkLogin();
        image.setTransitionListener(this);

        initialAnim();

        mBtnIngresar.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick() {
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

    private void checkLogin() {
        if (mPresenter.checkLogin()) {
            startActivity(new Intent(this, PendienteActivity.class));
            finish();
        }
    }


    //region Estados Actividad
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    //endregion

    //region interfaces
    @Override
    public Observable validacionSucces(String dniUser, String nombreUser, String emailUser) {
        mBtnIngresar.setProgress(false);
        startActivity(new Intent(this, PendienteActivity.class));
        customType(this, "fadein-to-fadeout");
        finish();
        return null;


    }

    @Override
    public Observable validacionError(String errorMessaje) {
        Toast.makeText(this, errorMessaje, Toast.LENGTH_LONG).show();
        mBtnIngresar.setProgress(false);
        return null;

    }
    //endregion

    //region Click
    @OnClick({R.id.image, R.id.view_pager_frame, R.id.etUsuario, R.id.etPassword, R.id.tvRecovery, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                break;
            case R.id.view_pager_frame:
                break;
            case R.id.etUsuario:
                break;
            case R.id.etPassword:
                break;
            case R.id.tvRecovery:
                try {
                    GifDrawable gifFromResource = new GifDrawable(getResources(), R.drawable.password);
                    ColorDialog mDialog = CustomDialog.dialogGifAndEditText(view, gifFromResource, getString(R.string.recoveryPassword), null);
                    mDialog.setPositiveListener(R.string.send, dialog1 ->
                    {
                        dialog1.dismiss();
                        mPresenter.loginRecovery(String.valueOf(mDialog.getTextTXV()));
                        return mDialog.getTextTXV();

                    }).setNegativeListener(R.string.cancel, dialog12 -> dialog12.dismiss()).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.tvRegister:
                startActivity(new Intent(this, CreateAccountActivity.class));
                customType(LoginActivity.this, "fadein-to-fadeout");
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && mEtUsuario.requestFocus())
            mEtPassword.requestFocus();
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && mEtPassword.getText().length() > 0) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEtPassword.getWindowToken(), 0);
            mBtnIngresar.performClick();
        }
        return super.dispatchKeyEvent(event);
    }

    //endregion

    //region Animaciones
    private void initialAnim() {
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        mTextView.setAnimation(downtoup);
        mTextView2.setAnimation(uptodown);
        mEtUsuario.setAnimation(downtoup);
        mEtPassword.setAnimation(uptodown);
        mBtnIngresar.setAnimation(downtoup);
        mTvRecovery.setAnimation(uptodown);
        mView6.setAnimation(downtoup);
        mTvRegister.setAnimation(uptodown);
    }
    //endregion

    //region Timer
    void finishLoading() {
        //call setProgress(false) after 5 second
        new Handler().postDelayed(() -> mPresenter.loginAccess(String.valueOf(mEtUsuario.getText()), String.valueOf(mEtPassword.getText())), 2000);
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




}
