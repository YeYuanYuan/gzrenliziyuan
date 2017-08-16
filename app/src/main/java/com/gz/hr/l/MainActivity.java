package com.gz.hr.l;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gz.hr.l.entity.ConnextEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.internal.ButterKnifeProcessor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
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

    TextView selectTextView;

    int quitTime = 1_500;
    AtomicInteger quitCode = new AtomicInteger(0);

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initTextView();
        initWebView();
    }

    private void initWebView() {

        id_content_WebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                TextView t = null;
                switch (url) {
                    case UrlUtil.SOUYE_URL:
                        t = id_main_souye_tv;
                        break;
                    case UrlUtil.ZHAOPIN_XINXI_URL:
                        t = id_main_zhaopin_tv;
                        break;
                    case UrlUtil.QIUZHI_XINXI_URL:
                        t = id_main_qiuzhi_tv;
                        break;
                    case UrlUtil.ZHAOPIN_HUI_URL:
                        t = id_main_zhaopinhui_tv;
                        break;
                    case UrlUtil.USER_URL:
                        t = id_main_user_tv;
                        break;
                }
                if (t != null) {
                    initTextColor();
                    selectTextView = t;
                    selectTextView.setTextColor(Color.BLUE);
                }
                super.onPageStarted(view, url, favicon);
            }
        });
        id_content_WebView.loadUrl(UrlUtil.SOUYE_URL);
        id_content_WebView.getSettings().setJavaScriptEnabled(true);
        id_content_WebView.getSettings().setSupportZoom(true);
        id_content_WebView.getSettings().setDisplayZoomControls(true);
        id_content_WebView.getSettings().setUseWideViewPort(true);
        id_content_WebView.getSettings().setLoadWithOverviewMode(true);

    }

    private void initTextView() {
        id_main_souye_tv.setText(getString(R.string.app_main_shouye));
        id_main_zhaopin_tv.setText(getString(R.string.app_main_zhaopin));
        id_main_qiuzhi_tv.setText(getString(R.string.app_main_qiuzhi));
        id_main_zhaopinhui_tv.setText(getString(R.string.app_main_zhaopinhui));
        id_main_user_tv.setText(getString(R.string.app_main_user));

        id_main_souye_tv.setOnClickListener(this);
        id_main_zhaopin_tv.setOnClickListener(this);
        id_main_qiuzhi_tv.setOnClickListener(this);
        id_main_zhaopinhui_tv.setOnClickListener(this);
        id_main_user_tv.setOnClickListener(this);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            id_content_WebView.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        } else {

            switch (quitCode.get()) {
                case -1:
                    handler.removeCallbacksAndMessages(null);
                    moveTaskToBack(false);
                    break;
                case 0:
                    quitCode.set(-1);
                    Toast.makeText(this, R.string.app_main_back_tip, Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            quitCode.set(0);
                        }
                    }, quitTime);
                    break;
            }

        }
        return true;
    }

    public void initTextColor() {
        int color = getResources().getColor(R.color.main_textColor);
        id_main_souye_tv.setTextColor(color);
        id_main_zhaopin_tv.setTextColor(color);
        id_main_qiuzhi_tv.setTextColor(color);
        id_main_zhaopinhui_tv.setTextColor(color);
        id_main_user_tv.setTextColor(color);
    }

    private void select(TextView t) {
        if (t == selectTextView) {
            return;
        }
        initTextColor();
        selectTextView = t;
        selectTextView.setTextColor(Color.BLUE);
        switch (t.getId()) {
            case R.id.id_main_souye_tv:
                id_content_WebView.loadUrl(UrlUtil.SOUYE_URL);
                break;
            case R.id.id_main_zhaopin_tv:
                id_content_WebView.loadUrl(UrlUtil.ZHAOPIN_XINXI_URL);
                break;
            case R.id.id_main_qiuzhi_tv:
                id_content_WebView.loadUrl(UrlUtil.QIUZHI_XINXI_URL);
                break;
            case R.id.id_main_zhaopinhui_tv:
                id_content_WebView.loadUrl(UrlUtil.ZHAOPIN_HUI_URL);
                break;
            case R.id.id_main_user_tv:
                id_content_WebView.loadUrl(UrlUtil.USER_URL);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        id_content_WebView.clearHistory();
        id_content_WebView.destroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ConnextEntity connextEntity){

        if(!ConnectReceiver.isConnect()){
            Log.i(TAG, "onMessage: connext change error");
//            Toast.makeText(this,R.string.connext_error,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        select((TextView) v);
    }
}
