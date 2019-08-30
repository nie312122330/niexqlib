package scnxq.com.niexqlib;

import android.os.Bundle;

import scnxq.com.appbase.NiexqBaseActivity;

public class MainActivity extends NiexqBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableSwipeBack(true);
    }
}
