package com.gz.hr.l;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.id_content_WebView)
    WebView id_content_WebView;
    @Bind(R.id.id_bottom_layout)
    LinearLayout id_bottom_layout;
    @Bind(R.id.id_main_souye_tv)
    TextView id_main_souye_tv;
    @Bind(R.id.id_main_zhaopin_tv)
    TextView id_main_zhaopin_tv;
    @Bind(R.id.id_main_qiuzhi_tv)
    TextView id_main_qiuzhi_tv;
    @Bind(R.id.id_main_zhaopinhui_tv)
    TextView id_main_zhaopinhui_tv;
    @Bind(R.id.id_main_user_tv)
    TextView id_main_user_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }

    private void initTextView() {
        id_main_souye_tv.setText(getString(R.string.app_main_shouye));
        id_main_zhaopin_tv.setText(getString(R.string.app_main_zhaopin));
        id_main_qiuzhi_tv.setText(getString(R.string.app_main_qiuzhi));
        id_main_zhaopinhui_tv.setText(getString(R.string.app_main_zhaopinhui));
        id_main_user_tv.setText(getString(R.string.app_main_user));
    }

}
