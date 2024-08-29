package cn.sddman.download;

import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.xunlei.downloadlib.XLTaskHelper;

import org.xutils.x;

import cn.sddman.download.common.DelegateApplicationPackageManager;

/*
本项目的下载任务创建，参考 
app\src\main\java\cn\sddman\download\activity\UrlDownLoadActivity.java
app\src\main\java\cn\sddman\download\App.java
app\src\main\java\cn\sddman\download\mvp\m\DownLoadModelImp.java
app\src\main\java\cn\sddman\download\mvp\p\DownloadSuccessPresenterImp.java
app\src\main\java\cn\sddman\download\mvp\p\TorrentInfoPresenterImp.java
app\src\main\java\cn\sddman\download\thread\DownUpdateUI.java
*/


public class App extends Application {
    public static App instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG);
        XLTaskHelper.init(getApplicationContext());
        instance = this;
    }
    public static App appInstance() {
        return instance;
    }
    @Override
    public String getPackageName() {
        if(Log.getStackTraceString(new Throwable()).contains("com.xunlei.downloadlib")) {
            return "com.xunlei.downloadprovider";
        }
        return super.getPackageName();
    }
    @Override
    public PackageManager getPackageManager() {
        if(Log.getStackTraceString(new Throwable()).contains("com.xunlei.downloadlib")) {
            return new DelegateApplicationPackageManager(super.getPackageManager());
        }
        return super.getPackageManager();
    }
}
