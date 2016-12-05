package com.ijinshan.liemo.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ijinshan.liemoapp.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li Guoqing on 2016/12/2.
 */
public class LoadingViewActivity extends Activity{
    private ViewPager mViewPager;
    private List<String> mPagerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingview);
        initPagerItems();
        initView();
    }

    private void initPagerItems() {
        mPagerItems = new ArrayList<String>();
        mPagerItems.add(com.ijinshan.liemo.views.loadings.loading1.LoadingView.class.getName());
        mPagerItems.add(com.ijinshan.liemo.views.loadings.loading2.LoadingView.class.getName());
    }

    private void initView() {
        mViewPager = (ViewPager)findViewById(R.id.loading_viewpager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPagerItems.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = getView(mPagerItems.get(position));
                container.addView(view);
                return view;
            }

            public View getView(String entry) {
                try {
                    Class pagerClass = Class.forName(entry);
                    Constructor<View> con = pagerClass.getConstructor(Context.class);
                    View view = (View) con.newInstance(LoadingViewActivity.this.getApplicationContext());
                    return view;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }

        });
    }
}
