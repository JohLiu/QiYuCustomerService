package com.joh.qiyudemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.qiyukf.unicorn.api.OnBotEventListener;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;

/**
 * application
 *
 * @author : Joh_hz
 * @date : 2019/5/24 15:26
 */
public class MyApplication extends Application {

    /**
     * 网易七鱼客服
     * 这里将YSFOptions提取出来是为了便于后面对七鱼配置的修改
     */
    public static YSFOptions ysfOptions;

    public static Context mContext;

    private String appKey = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //网易七鱼客服
        Unicorn.init(this, appKey, options(), new GlideImageLoader(this));
    }

    /**
     * 网易七鱼客服
     *
     * @return
     */
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        /**
         * 客服消息通知
         */
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.statusBarNotificationConfig.notificationSmallIconId = R.mipmap.ic_launcher;
        options.onBotEventListener = new OnBotEventListener() {
            @Override
            public boolean onUrlClick(Context context, String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                return true;
            }
        };

        ysfOptions = options;
        return options;
    }

    public static Context getContext() {
        return mContext;
    }

}