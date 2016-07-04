package com.ijinshan.liemo.items.action;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.ijinshan.liemo.utils.AppContextUtil;
import com.ijinshan.liemo.utils.logs.ELog;

import java.util.List;

/**
 * Created by Li Guoqing on 2016/5/26.
 */
public class ActionHelper {
    private static final String LOG = "ActionHelper,";

    public enum ActionType {
        ACTIVITY, WEBVIEW, BROWSER, GP;

        public static ActionType getEnumFromString(String string) {
            if (string != null) {
                try {
                    return Enum.valueOf(ActionType.class, string.trim());
                } catch (IllegalArgumentException ex) {
                }
            }
            return null;
        }

    }

    public static class Action {
        ActionType mActionType;
        String mUrl;

        public Action(ActionType type, String url) {
            mActionType = type;
            mUrl = url;
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(mUrl);
        }
    }

    public static void doAction(Action action) {
        if (action == null || !action.isValid()) {
            ELog.e(LOG + " doAction Error");
            return;
        }
        ELog.e(LOG + " doAction " + action.mActionType.toString());
        switch (action.mActionType) {
            case ACTIVITY:
                doActivityAction(action.mUrl);
                break;
        }
    }

    private static final String PKGNAME = "com.ijinshan.liemoapp";
    private static void doActivityAction(String packageName) {
        try {
            Context context = AppContextUtil.getInstance().getAppContext();
            Intent intent = new Intent();
            ComponentName cn = new ComponentName(PKGNAME, packageName);
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
