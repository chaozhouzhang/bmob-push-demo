package cn.bmob.push;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created on 17/8/24 13:18
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化日志工具
         */
        Logger.addLogAdapter(new AndroidLogAdapter());
        //TODO 集成：1.4、初始化数据服务SDK、保存设备信息并启动推送服务
        /**
         * 初始化比目数据SDK
         */
      Bmob.initialize(this, "e9b559bd68461777602a46dead1b581b");
        /**
         * 保存设备信息，用于推送功能
         */
        BmobInstallation.getCurrentInstallation().save();
        /**
         * 启动推送服务
         */
        BmobPush.startWork(this);
    }
}
