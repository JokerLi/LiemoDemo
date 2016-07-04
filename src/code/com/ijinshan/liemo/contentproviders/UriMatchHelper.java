package com.ijinshan.liemo.contentproviders;

import android.content.UriMatcher;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by Li Guoqing on 2016/6/1.
 */
public class UriMatchHelper {
    private static final String AUTHORITIE_STR = "liemo.test.setting.provider";
    private static final String PATH_STR = "elements";
    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;


    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITIE_STR, PATH_STR, ALLROWS);
        sUriMatcher.addURI(AUTHORITIE_STR, PATH_STR + "/#", SINGLE_ROW);
    }

    public static String getElements(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        Uri uri = Uri.parse(path);
        switch (sUriMatcher.match(uri)){
            case ALLROWS:
                return "allrows";
            case SINGLE_ROW:
                return "singlerow";
        }
        return null;
    }
}
