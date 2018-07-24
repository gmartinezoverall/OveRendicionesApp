package com.overall.developer.overrendicion.ui.user.view.Drawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.libizo.CustomEditText;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.ui.user.presenter.Drawable.UpdateEmailPresenter;
import com.overall.developer.overrendicion.ui.user.presenter.Drawable.UpdateEmailPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateEmailActivity extends AppCompatActivity implements UpdateEmailView {
    @BindView(R.id.etxNewEmail)
    CustomEditText etxNewEmail;
    @BindView(R.id.btnEnviar)
    Button btnEnviar;
    private UpdateEmailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        ButterKnife.bind(this);

        mPresenter = new UpdateEmailPresenterImpl(this);


    }

    @OnClick(R.id.btnEnviar)
    public void onViewClicked()
    {
        if (valideWidgets())mPresenter.sendData(String.valueOf(etxNewEmail.getText()));
    }

    private boolean valideWidgets() {
        if (etxNewEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.validarCampos), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    @Override
    public void responseUpdateEmailSuccess(String message)
    {
        Toast.makeText(this, "Se Actualizo tu Correo Correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void responseUpdateEmailError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
