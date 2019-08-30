package scnxq.com.appbase;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.DbManager;
import org.xutils.db.DbManagerImpl;

import scnxq.com.appbase.utils.SdCardUtils;
import scnxq.com.appbase.utils.ViewUtils;

public class AppBaseInit {
    private static DbManager dbManager;

    public static DbManager getDbManager() {
        if (null == dbManager) {
            throw new RuntimeException("使用前请先调用AppBaseInit.init()");
        }
        return dbManager;
    }

    public static void init(Application application, AppBaseConfig appBaseConfig) {
        SdCardUtils.ROOT_DIR = appBaseConfig.getSdcardBaseDir();
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setAllowTransaction(appBaseConfig.getDbAllowTransaction());
        daoConfig.setDbName(appBaseConfig.getDbName());
        daoConfig.setDbVersion(appBaseConfig.getDbVersion());
        daoConfig.setDbDir(SdCardUtils.getDbDir(application));
        dbManager = DbManagerImpl.getInstance(application, daoConfig);
        initImageLoaderConfig(application);
    }

    private static void initImageLoaderConfig(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
        builder.threadPriority(Thread.NORM_PRIORITY - 2);
        //拒绝一个图片多个大小在内存中
        builder.memoryCacheExtraOptions(ViewUtils.getScreenWidth(context), ViewUtils.getScreenHeight(context));
        builder.denyCacheImageMultipleSizesInMemory();
        builder.threadPoolSize(5);
        builder.memoryCacheSize(100 * 1024 * 1024);
        builder.memoryCache(new WeakMemoryCache());
        //图片缓存目录
        builder.diskCache(new UnlimitedDiskCache(SdCardUtils.getImageLoaderCache(context)));
        builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        //图片默认的缓存目录为//cache/url-image
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        //日志
        com.nostra13.universalimageloader.utils.L.writeLogs(BuildConfig.DEBUG);
        //	builder.writeDebugLogs();
        ImageLoader.getInstance().init(builder.build());
    }

}