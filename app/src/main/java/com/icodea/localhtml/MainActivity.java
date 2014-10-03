package com.icodea.localhtml;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	WebView webView;
	WebSettings webSettings;
	ProgressBar progress;
	boolean doubleBackToExitPressedOnce;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView=(WebView)findViewById(R.id.webview);
		progress=(ProgressBar)findViewById(R.id.pbLoader);
		
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
		webView.loadUrl("file:///android_asset/www/index.html?module=stream");
        webView.setBackgroundColor(0);
	}

	//Back Button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode){
            	case KeyEvent.KEYCODE_BACK:
	                if(webView.canGoBack()){
	                	webView.goBack();
	                }else{
	                    if (doubleBackToExitPressedOnce) {
	                        this.moveTaskToBack(true);
	                        return true;
	                    }
		        	    this.doubleBackToExitPressedOnce = true;
		        	    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
		        	    new Handler().postDelayed(new Runnable() {
		        	        @Override
		        	        public void run() {
		        	            doubleBackToExitPressedOnce=false;                       
		        	        }
		        	    }, 2000);
	                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
    //Option Menu
    private int group1Id = 1;
    int homeId = Menu.FIRST;
	int profileId = Menu.FIRST +1;
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    //option menu
		menu.add(group1Id, homeId, homeId, "home");
		menu.add(group1Id, profileId, profileId, "about");
		
	    //actionbar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    
	    return super.onCreateOptionsMenu(menu);
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	    // Handle presses on the action bar items
	   switch (item.getItemId()) {
	   		case 1:
	   			Toast msg = Toast.makeText(MainActivity.this, "halooo", Toast.LENGTH_LONG);
	   			msg.show();
				return true;
   			case 2:
   				//Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
				//startActivity(iAbout);
				return true;
			case R.id.action_search:
				webView.loadUrl("file:///android_asset/www/index.html?module=welcome");
				return true;
   			default:
				break;
	   }
	   return super.onOptionsItemSelected(item);
	}
}
