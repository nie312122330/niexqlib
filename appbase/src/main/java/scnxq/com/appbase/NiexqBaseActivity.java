package scnxq.com.appbase;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import scnxq.com.appbase.utils.ViewUtils;

/**
 * 1.该页面已支持了左滑返回，覆盖该方法返回对应true或者false即可
 */
public class NiexqBaseActivity extends SwipeBackActivity {
    private NiexqBaseActivity niexqBaseActivity = null;
    protected FrameLayout top1Framelayout;
    protected LinearLayout top2LinearLayout;
    protected ViewGroup contentView;

    protected LinearLayout layout_niexq_title;
    protected ImageButton niexq_title_back_button;
    protected RelativeLayout niexq_title_right_layout;
    protected FrameLayout niexq_title_left_layout;
    protected TextView niexq_title_textview;
    protected View niexq_title_bottom_line;
    protected RelativeLayout niexq_relative_title_continaer;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        niexqBaseActivity = this;
        //引导层可添加到guideFrame
        top1Framelayout = new FrameLayout(this);
        //根布局
        top2LinearLayout = new LinearLayout(this);
        top2LinearLayout.setOrientation(LinearLayout.VERTICAL);
        top1Framelayout.addView(top2LinearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(int layoutResID) {
        contentView = (ViewGroup) LayoutInflater.from(this).inflate(layoutResID, top2LinearLayout, false);
        layout_niexq_title = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_niexq_title, top2LinearLayout, false);
        niexq_title_back_button = (ImageButton) layout_niexq_title.findViewById(R.id.niexq_title_back_button);
        niexq_title_right_layout = (RelativeLayout) layout_niexq_title.findViewById(R.id.niexq_title_right_layout);
        niexq_title_left_layout = (FrameLayout) layout_niexq_title.findViewById(R.id.niexq_title_left_layout);
        niexq_title_textview = (TextView) layout_niexq_title.findViewById(R.id.niexq_title_textview);
        niexq_title_bottom_line = layout_niexq_title.findViewById(R.id.niexq_title_bottom_line);
        niexq_relative_title_continaer = layout_niexq_title.findViewById(R.id.niexq_relative_title_continaer);
        top2LinearLayout.addView(layout_niexq_title);
        top2LinearLayout.addView(contentView);
        setContentView(top1Framelayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        unbinder = ButterKnife.bind(this);
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


    @Override
    protected void onResume() {
        super.onResume();
        changeToDefalutResourcesConf();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

    protected void showTitleBar(boolean show, @ColorInt int color) {
        if (show) {
            layout_niexq_title.setVisibility(View.VISIBLE);
        } else {
            layout_niexq_title.setVisibility(View.GONE);
        }
        layout_niexq_title.setBackgroundColor(color);
        layout_niexq_title.setPadding(0, ViewUtils.getStatusBarHeight(this), 0, 0);
    }


    /**
     * 设置状态栏字体颜色为白色
     */
    public void setStatusBarTextColor2White(boolean whiteText) {
        // 设置状态栏底色透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        // 设置状态栏字体黑色
        if (whiteText) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        }
    }

    /**
     * 判断颜色是不是亮色
     */
    protected boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    /**
     * 系统字体修改不影响
     */
    private void changeToDefalutResourcesConf() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        config.fontScale = 1.0f;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }


}
