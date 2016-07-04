package com.ijinshan.liemo.fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ijinshan.liemoapp.R;

/**
 * Created by Li Guoqing on 2016/5/30.
 */
@TargetApi(11)
public class TitleFragment extends Fragment {
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_fragment, container, false);
        return view;
    }
}
