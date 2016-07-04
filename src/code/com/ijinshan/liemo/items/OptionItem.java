package com.ijinshan.liemo.items;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ijinshan.liemo.items.action.ActionHelper;
import com.ijinshan.liemo.items.action.ActionHelper.Action;
import com.ijinshan.liemo.items.action.ActionHelper.ActionType;
import com.ijinshan.liemo.utils.AppContextUtil;
import com.ijinshan.liemo.utils.VolleyUtil;
import com.ijinshan.liemo.utils.files.AssetsUtils;
import com.ijinshan.liemoapp.R;

import org.json.JSONObject;

/**
 * Created by Li Guoqing on 2016/5/26.
 */
public class OptionItem {
    private static final String KEY_NAME = "name";
    private static final String KEY_ACTION = "action";
    private static final String KEY_URL = "url";
    private static final String KEY_ICON = "icon";

    private String mName;
    private String mAction;
    private String mUrl;
    private String mIcon;

    public OptionItem(String name, String action, String url, String icon){
        mName = name;
        mAction = action;
        mUrl = url;
        mIcon = icon;
    }
    public static OptionItem createItemFromJson(JSONObject object){
        if(object == null){
            return null;
        }
        OptionItem item = null;
        try{
            String name = object.optString(KEY_NAME);
            String action = object.optString(KEY_ACTION);
            String url = object.optString(KEY_URL);
            String icon = object.optString(KEY_ICON);
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(action)
                    || TextUtils.isEmpty(url) || TextUtils.isEmpty(icon)){
                return null;
            }
            item = new OptionItem(name, action, url, icon);
        }catch (Exception e){
            item = null;
        }
        return item;
    }
    //method click
    public void action(){
        ActionHelper.doAction(new Action(ActionType.getEnumFromString(mAction),mUrl));
    }
    //method show
    public View getView(){
        Context context = AppContextUtil.getInstance().getAppContext();
        View viewGroup = LayoutInflater.from(context).inflate(R.layout.option_item, null);
        ImageView iconView = (ImageView)viewGroup.findViewById(R.id.option_item_icon);
        renderImageView(iconView, mIcon);
        TextView nameView = (TextView)viewGroup.findViewById(R.id.option_item_name);
        nameView.setText(mName);
        return viewGroup;
    }

    private void renderImageView(ImageView imageView, String url){
        if(TextUtils.isEmpty(url) || imageView == null){
            return;
        }
        if(url.startsWith("http") || url.startsWith("https")){
            VolleyUtil.loadImage(imageView, url);
        }else if(url.startsWith("assets")){
            String[] splits = url.split("#");
            imageView.setImageBitmap(AssetsUtils.getBitmapByAssets(splits[1]));
        }
    }

    //method update
}
