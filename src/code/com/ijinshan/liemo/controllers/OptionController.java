package com.ijinshan.liemo.controllers;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ijinshan.liemo.items.OptionItem;
import com.ijinshan.liemo.utils.files.AssetsUtils;
import com.ijinshan.liemo.utils.files.JsonUtils;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Li Guoqing on 2016/5/26.
 */
public class OptionController {
    private LinkedList<OptionItem> mOptionItemsList;
    public OptionController(){
        initOptionItemsList();    
    }

    private void initOptionItemsList() {
        mOptionItemsList = new LinkedList<OptionItem>();
        List<OptionItem> itemList = getItemsList();
        if(itemList == null || itemList.isEmpty()){
            return;
        }
        mOptionItemsList.addAll(itemList);
    }

    public void bindData(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mOptionItemsList.get(position).action();
            }
        });
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mOptionItemsList.size();
            }

            @Override
            public Object getItem(int position) {
                return mOptionItemsList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = mOptionItemsList.get(position).getView();
                return view;
            }
        });
    }


    //get items
    private List<OptionItem> getItemsList(){
        LinkedList<OptionItem> itemsList = null;
        String jsonStr = AssetsUtils.getStrByAssets("options.json");
        if(TextUtils.isEmpty(jsonStr)){
            return itemsList;
        }
        List<JSONObject> jsonObjects = JsonUtils.getJsonObjectListFromStr(jsonStr);
        if(jsonObjects == null || jsonObjects.isEmpty()){
            return itemsList;
        }
        itemsList = new LinkedList<OptionItem>();
        for(JSONObject object : jsonObjects){
            if(object != null){
                OptionItem item = OptionItem.createItemFromJson(object);
                if(item != null) {
                    itemsList.add(item);
                }
            }
        }
        return itemsList;
    }
    //define action callback and Controller implements this callback set into ListView
}
