package scnxq.com.niexqlibdemo;

import android.app.Application;

import scnxq.com.niexqlib.AppBaseConfig;
import scnxq.com.niexqlib.AppBaseInit;


public class DemoApplication extends Application {

    public static DemoApplication app = null;
    public static AppBaseConfig config;

    static {
        config = new AppBaseConfig();
        config.setSdcardBaseDir("niexqlib-demo");
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
