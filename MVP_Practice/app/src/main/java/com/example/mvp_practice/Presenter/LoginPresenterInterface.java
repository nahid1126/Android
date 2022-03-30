package com.example.mvp_practice.Presenter;

public interface LoginPresenterInterface {
    void doLogin(String email, String password);

    void setProgressBar(int visibility);
}