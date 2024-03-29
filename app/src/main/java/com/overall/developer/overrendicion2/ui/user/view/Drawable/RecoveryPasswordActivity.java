package com.overall.developer.overrendicion2.ui.user.view.Drawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.libizo.CustomEditText;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.RecoveryPasswordPresenter;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.RecoveryPasswordPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecoveryPasswordActivity extends AppCompatActivity implements RecoveryPasswordView {

    @BindView(R.id.etxNewPassword)
    CustomEditText etxNewPassword;
    @BindView(R.id.btnEnviar)
    Button btnEnviar;

    private RecoveryPasswordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reocvery_password);
        ButterKnife.bind(this);

        mPresenter = new RecoveryPasswordPresenterImpl(this);


    }


    @Override
    public void responseRoveryPasswordSuccess(String message)
    {
        Toast.makeText(this, getResources().getString(R.string.recoveryPasswordSuccess), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void responseRoveryPasswordError(String message) {
        Toast.makeText(this, getResources().getString(R.string.recoveryPasswordError), Toast.LENGTH_LONG).show();
      
    }

    private boolean valideWidgets() {
        if (etxNewPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    @OnClick(R.id.btnEnviar)
    public void onViewClicked()
    {
        if (valideWidgets()) mPresenter.sendData(String.valueOf(etxNewPassword.getText()));

    }
}
