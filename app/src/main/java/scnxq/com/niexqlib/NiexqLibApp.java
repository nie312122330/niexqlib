package scnxq.com.niexqlib;

import android.support.multidex.MultiDexApplication;

import scnxq.com.appbase.AppBaseConfig;
import scnxq.com.appbase.AppBaseInit;

public class NiexqLibApp extends MultiDexApplication {
    public static NiexqLibApp app = null;
    public static AppBaseConfig config;

    static {
        config = new AppBaseConfig();
        config.setSdcardBaseDir("niexqlib");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        AppBaseInit.init(app, config);
    }

}
