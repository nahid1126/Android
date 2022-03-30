package com.example.mvp_practice.Model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class UserModel implements UserInterface {
    private String email, password;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getMail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int checkValidity() {
        if (TextUtils.isEmpty(getMail())) {
            return 0;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getMail()).matches()) {
            return 1;
        } else if (TextUtils.isEmpty(getPassword())) {
            return 2;
        } else if (getPassword().length() < 6) {
            return 3;
        } else {
            return -1;
        }
    }
}
