package scnxq.com.appbase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import scnxq.com.appbase.utils.ViewUtils;

/**
 * 1.该页面已支持了左滑返回，覆盖该方法返回对应true或者false即可
 */
public class NiexqBaseActivity extends SwipeBackActivity {
    private NiexqBaseActivity niexqBaseActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        niexqBaseActivity = this;
    }

    /**
     * 使用滑动返回
     */
    protected void enableSwipeBack(boolean enable) {
        if (enable) {
            getSwipeBackLayout().setEdgeSize(ViewUtils.dip2px(this, 20));
            getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
            getSwipeBackLayout().setEnableGesture(true);
        } else {
            getSwipeBackLayout().setEnableGesture(false);
        }
    }

}
