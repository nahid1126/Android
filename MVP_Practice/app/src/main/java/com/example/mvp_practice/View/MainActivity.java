package com.example.mvp_practice.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp_practice.Presenter.LoginPresenter;
import com.example.mvp_practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginInterface {

    @BindView(R.id.txtUser)
    EditText txtUser;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenter loginPresenter;

    @OnClick(R.id.btnLogin)
    void OnLoginClicked() {
        loginPresenter.setProgressBar(View.VISIBLE);
        loginPresenter.doLogin(txtUser.getText().toString().trim(), txtPassword.getText().toString().trim());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setProgressBar(View.INVISIBLE);
    }

    @Override
    public void onLoginSuccess(String msg) {
        loginPresenter.setProgressBar(View.INVISIBLE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        loginPresenter.setProgressBar(View.INVISIBLE);
    }

    @Override
    public void onProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}