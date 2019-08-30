package scnxq.com.appbase.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder Result = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                Result.append(line);
            bufReader.close();
            bufReader = null;
            return Result.toString();
        } catch (Exception e) {
            LogUtils.error(e);
            return "";
        }
    }

}
