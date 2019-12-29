package scnxq.com.appbase.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * AssertFile工具类
 * Created by niexiaoqiang on 2016/7/21.
 */
public class AssertFileUtils {
    /**
     * 获取文本文件内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getAssetsFile(Context context, String fileName) {
        InputStream open = null;
        try {
            open = context.getResources().getAssets().open(fileName);
            byte[] bytes = consumeInputStream(open);
            String result = null;
            if (null != bytes) {
                result = new String(bytes, StandardCharsets.UTF_8);
            }
            open.close();
            return result;
        } catch (Exception e) {
            LogUtils.error(e);
            return "";
        }
    }

    public static byte[] consumeInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        byte[] outData = null;
        try {
            for (int count; (count = inputStream.read(buffer)) != -1; ) {
                byteArrayOutputStream.write(buffer, 0, count);
            }
            byteArrayOutputStream.flush();
            outData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return outData;
        } catch (IOException ex) {
            LogUtils.error(ex);
            return null;
        }
    }

}
