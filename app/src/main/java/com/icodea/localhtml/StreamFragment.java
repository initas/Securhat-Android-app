package com.icodea.localhtml;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.app.Fragment;

public class StreamFragment extends Fragment {

    static WebView webView;
    WebSettings webSettings;
    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        webView = (WebView)rootView.findViewById(R.id.webview);
        progress = (ProgressBar)rootView.findViewById(R.id.pbLoader);

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
        loadStream();
        return rootView;
    }
    public static void loadStream(){
        webView.clearCache(true);
        webView.loadUrl(Constants.CURHAT_PAGE);
        webView.setBackgroundColor(0);
    }
    public static boolean canGoBack(){
        return webView.canGoBack();
    }
    public static void goBack(){
        webView.goBack();
    }
}