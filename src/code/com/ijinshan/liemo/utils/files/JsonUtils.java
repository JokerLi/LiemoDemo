package com.ijinshan.liemo.utils.files;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Li Guoqing on 2016/5/26.
 */
public class JsonUtils {

    //get Json Array
    public static JSONArray getJsonArrayFromStr(String sourceStr){
        JSONArray jsonArray = null;
        try{
            jsonArray = new JSONArray(sourceStr);
        }catch (Exception e){
        }
        return jsonArray;
    }
    //get Json Object List
    public static List<JSONObject> getJsonObjectListFromStr(String sourceStr){
        JSONArray jsonArray = null;
        List<JSONObject> jsonObjectList = null;
        try{
            jsonArray = new JSONArray(sourceStr);
            if(jsonArray.length() <= 0){
                return null;
            }
            jsonObjectList = new LinkedList<JSONObject>();
            for(int index = 0; index < jsonArray.length() ;index++){
                JSONObject object = jsonArray.getJSONObject(index);
                if(object != null){
                    jsonObjectList.add(object);
                }
            }
        }catch (Exception e){
            jsonObjectList = null;
        }
        return jsonObjectList;
    }
    //get Json Object
    public static JSONObject getJsonObjectFromStr(String sourceStr){
        return null;
    }
}
