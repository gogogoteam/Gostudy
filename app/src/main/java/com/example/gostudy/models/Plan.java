package com.example.gostudy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("plans")
public class Plan extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_PLAN_NAME = "planName";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public String getPlanName() {
        return getString(KEY_PLAN_NAME);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER,user);
    }

    public void setPlanName(String name) {
        put(KEY_PLAN_NAME, name);
    }
}
