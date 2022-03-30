package com.example.mvp_practice.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.mvp_practice.Model.UserInterface;
import com.example.mvp_practice.Model.UserModel;
import com.example.mvp_practice.View.LoginInterface;

public class LoginPresenter implements LoginPresenterInterface {

    //for connecting with view this is the main of MVP
    //here presenter must be connect with view
    private LoginInterface loginInterface;
    private Handler handler;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String email, String password) {

        //presenter also communicate with model via interface
        // because all business logic know model
        UserInterface userInterface = new UserModel(email, password);
        int login_code = userInterface.checkValidity();

        handler.postDelayed(() -> {
            if (login_code == 0) {
                loginInterface.onLoginFailed("Please Enter Your Mail!");
            } else if (login_code == 1) {
                loginInterface.onLoginFailed("Please Enter A Valid Mail!");
            } else if (login_code == 2) {
                loginInterface.onLoginFailed("Please Enter A Your Password!");
            } else if (login_code == 3) {
                loginInterface.onLoginFailed("Your Password should be more then 6!");
            } else {
                loginInterface.onLoginSuccess("Login Successful!!");
            }
        }, 2000);
    }

    @Override
    public void setProgressBar(int visibility) {
        loginInterface.onProgressBarVisibility(visibility);
    }
}
