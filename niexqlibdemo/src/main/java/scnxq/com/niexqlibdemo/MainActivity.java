package scnxq.com.niexqlibdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import scnxq.com.niexqlib.NiexqBaseActivity;

public class MainActivity extends NiexqBaseActivity {
    @BindView(R.id.text_view)
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableSwipeBack(true);
        showTitleBar(true, Color.WHITE);
        //设置状态栏颜色
        setStatusBarTextColor2White(isLightColor(Color.WHITE));
        text_view.setText("LibDemo");
    }
}
