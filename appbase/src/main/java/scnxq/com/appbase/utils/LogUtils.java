package scnxq.com.appbase.utils;

import android.util.Log;


/**
 * 统一使用该日志输出
 */
public class LogUtils {
    public static String TAG = "niexq";

    public static final void debug(Object object) {
        Log.d(TAG, null == object ? "" : object.toString());
    }

    public static final void info(Object object) {
        Log.i(TAG, null == object ? "" : object.toString());
    }

    public static final void warn(Object object) {
        Log.w(TAG, null == object ? "" : object.toString());
    }

    public static final void error(Object object) {
        ((Exception) (object)).printStackTrace();
        Log.e(TAG, object.toString());
    }
}
