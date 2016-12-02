package com.ijinshan.liemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ijinshan.liemo.controllers.OptionController;
import com.ijinshan.liemo.views.DragLayout;
import com.ijinshan.liemo.views.render.CommonViewRender;
import com.ijinshan.liemo.views.render.CommonViewTemplate;
import com.ijinshan.liemo.views.render.ICommonViewData;
import com.ijinshan.liemoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{
    private DragLayout mDragLayout;
    private ListView mOptionListView;
    private OptionController mOptionController;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initOptionListView();
        initDragLayout();
        initListView();
    }

    private void initView() {
        mOptionListView = (ListView) findViewById(R.id.option_listview);
        mListView = (ListView)findViewById(R.id.content_listview);
        mDragLayout = (DragLayout) findViewById(R.id.main_draglayout);
    }

    private void initDragLayout() {
        mDragLayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {
            }

            @Override
            public void onDrag(float percent) {
            }
        });
    }

    private void initOptionListView() {
        mOptionController = new OptionController();
        mOptionController.bindData(mOptionListView);
    }

    private void initListView(){
        List<ICommonViewData> list = getInitedListData();
        mListView.setAdapter(new DataAdapter(list));
    }

    private List<ICommonViewData> getInitedListData() {
        List<ICommonViewData> dataList = new ArrayList<ICommonViewData>();
        dataList.add(new Data("Loading View", LoadingViewActivity.class.getName()));
        return dataList;
    }

    private class DataAdapter extends BaseAdapter {
        List<ICommonViewData> mList;
        CommonViewTemplate mTemplate;
        DataAdapter(List<ICommonViewData> list){
            mList = new ArrayList<ICommonViewData>();
            mList.addAll(list);
            mTemplate = new CommonViewTemplate.Builder(R.layout.main_list_item)
                    .titleId(R.id.title)
                    .build();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommonViewRender render = null;
            if(convertView == null || convertView.getTag() == null
                    || !(convertView.getTag() instanceof CommonViewRender)){
                render = new CommonViewRender(MainActivity.this.getApplicationContext(), mTemplate);
            }else{
                render = (CommonViewRender)convertView.getTag();
            }
            ICommonViewData data = mList.get(position);
            convertView = render.getBindedView(data);
            data.registerView(convertView);
            convertView.setTag(render);
            return convertView;
        }
    }

    private class Data implements ICommonViewData{
        String mTitle;
        String mClassName;
        Data(String title, String className){
            mTitle = title;
            mClassName = className;
        }

        @Override
        public void registerView(View view) {
            setListener(view, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(mClassName);
                }
            });
        }

        public void setListener(View view,View.OnClickListener onClickListener){
            if(view == null){
                return;
            }
            view.setOnClickListener(onClickListener);
            if(view instanceof ViewGroup){
                ViewGroup vp = (ViewGroup) view;
                for(int i = 0; i < vp.getChildCount(); i++){
                    View childView = vp.getChildAt(i);
                    setListener(childView, onClickListener);
                }
            }
        }

        public void startActivity(String entry) {
            try {
                Class activityClass = Class.forName(entry);
                Intent intent = new Intent(MainActivity.this, activityClass);
                MainActivity.this.startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public CharSequence getTitle() {
            return mTitle;
        }

        @Override
        public CharSequence getBody() {
            return null;
        }

        @Override
        public CharSequence getSmallBody() {
            return null;
        }

        @Override
        public CharSequence getButton() {
            return null;
        }

        @Override
        public String getIcon() {
            return null;
        }

        @Override
        public String getImageUrl() {
            return null;
        }
    }
}
