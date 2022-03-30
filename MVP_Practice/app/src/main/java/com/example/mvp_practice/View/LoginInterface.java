package com.example.mvp_practice.View;

public interface LoginInterface {
    void onLoginSuccess(String msg);

    void onLoginFailed(String msg);

    void onProgressBarVisibility(int visibility);
}
