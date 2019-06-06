package com.overall.developer.overrendicion2.ui.user.view.Drawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.libizo.CustomEditText;
import com.overall.developer.overrendicion2.R;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.UpdateEmailPresenter;
import com.overall.developer.overrendicion2.ui.user.presenter.Drawable.UpdateEmailPresenterImpl;
import com.overall.developer.overrendicion2.utils.Util;

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

        etxNewEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                if (Util.isEmailValid(String.valueOf(etxNewEmail.getText())))
                {
                    etxNewEmail.setError(getResources().getString(R.string.createEmailError));

                }
            }
        });

        RxTextView.textChanges(etxNewEmail)
                .filter(etx -> (etx.length() > 0 && !Patterns.EMAIL_ADDRESS.matcher(etx).matches()))
                .subscribe(etx -> etxNewEmail.setError(getResources().getString(R.string.createEmailError)));


    }

    @OnClick(R.id.btnEnviar)
    public void onViewClicked()
    {
        if (valideWidgets())mPresenter.sendData(String.valueOf(etxNewEmail.getText()));
    }

    private boolean valideWidgets() {
        if (etxNewEmail.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(String.valueOf(etxNewEmail.getText())).matches()) {
            Toast.makeText(this, getResources().getString(R.string.createEmailError), Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    @Override
    public void responseUpdateEmailSuccess(String message)
    {
        Toast.makeText(this, "Se Actualizo tu Correo Correctamente", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void responseUpdateEmailError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
