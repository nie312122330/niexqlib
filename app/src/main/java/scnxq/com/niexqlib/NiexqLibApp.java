package scnxq.com.niexqlib;


import android.app.Application;

import scnxq.com.appbase.AppBaseConfig;
import scnxq.com.appbase.AppBaseInit;

public class NiexqLibApp extends Application {
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
        AppBaseInit.initLog(config);
        AppBaseInit.initDb(this, config);
        AppBaseInit.initImageLoader(this, config);
    }

}
