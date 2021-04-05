package com.example.gostudy;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("icIDkWCRmyZtaR5koXlDkEanXoNP3f5vyYVUyQoj")
                .clientKey("Ynb1MydLKjeB90BBKWtE5UlGoJwNXoPaigAH4sAi")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
