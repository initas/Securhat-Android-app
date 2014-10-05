package com.icodea.localhtml;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.Fragment;

public class StreamFragment extends Fragment {
    WebView webView;
    WebSettings webSettings;
    ProgressBar progress;
    boolean doubleBackToExitPressedOnce;

    public StreamFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        final WebView webView = (WebView)rootView.findViewById(R.id.webview);
        final ProgressBar progress = (ProgressBar)rootView.findViewById(R.id.pbLoader);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                progress.setVisibility(ProgressBar.VISIBLE);
                webView.setVisibility(ProgressBar.INVISIBLE);
            }
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                progress.setVisibility(ProgressBar.INVISIBLE);
                webView.setVisibility(ProgressBar.VISIBLE);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/my-error-page.html");
            }
        });
        webView.clearCache(true);
        webView.loadUrl(Constants.CURHAT_PAGE);
        webView.setBackgroundColor(0);

        return rootView;
    }
}
class Constants{
    public static final String BASE_URL = "file:///android_asset/www/";
    public static final String CURHAT_PAGE = BASE_URL+"index.html?module=stream";
}