package com.itaycohen.utils;

import com.google.gson.Gson;

public class GsonContainer {

    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null)
            instance = new Gson();
        return instance;
    }
}
