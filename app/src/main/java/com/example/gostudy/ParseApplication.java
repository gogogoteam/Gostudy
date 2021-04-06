package com.example.gostudy;

import android.app.Application;

import com.example.gostudy.models.Course;
import com.example.gostudy.models.Plan;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(Plan.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("icIDkWCRmyZtaR5koXlDkEanXoNP3f5vyYVUyQoj")
                .clientKey("Ynb1MydLKjeB90BBKWtE5UlGoJwNXoPaigAH4sAi")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
