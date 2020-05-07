package scnxq.com.niexqlib.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 显示吐丝
 */
public class ToastUtils {

    private static long lastTime = 0;

    public static void show(Context context, String message) {
        if (System.currentTimeMillis() - lastTime >= 3000) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            View view = toast.getView();
            if (view instanceof LinearLayout) {
                ((LinearLayout) view).setGravity(Gravity.CENTER);
            }
            toast.setText(message);
            toast.show();
            lastTime = System.currentTimeMillis();
        }
    }

    public static void showCenterToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = toast.getView();
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).setGravity(Gravity.CENTER);
        }
        toast.setText(msg);
        toast.show();
    }
}
