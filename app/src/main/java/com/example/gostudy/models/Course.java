package com.example.gostudy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParsePlugins;

@ParseClassName("course")
public class Course extends ParseObject {
    public static final String KEY_COURSE_NAME = "courseName";
    public static final String KEY_CREDIT = "credit";
    public static final String KEY_PLAN = "plan";


    public String getCourseName() {
        return getString(KEY_COURSE_NAME);
    }

    public String getCredits() {
        return getString(KEY_CREDIT);
    }

    public ParseObject getPlan() {
        return getParseObject(KEY_PLAN);
    }

    public void setCourseName(String name) {
        put(KEY_COURSE_NAME, name);
    }

    public void setCredits(float credits) {
        put(KEY_CREDIT, credits);
    }

    public void setPlan(ParseObject plan) {
        put(KEY_PLAN, plan);
    }
}
