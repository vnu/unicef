package org.unicef.unicefkid;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import com.google.android.gcm.GCMRegistrar;

@SuppressLint("SetJavaScriptEnabled")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WelcomeActivity extends Activity {

	public static final String SENDER_ID = "897901395152";
	public static final String TAG = "CP WebActivity";
	public static final String API_KEY = "AIzaSyCdZ2j43JIN7tbz4pmKho5AZiUJ2D7rNGQ";
	
	

	WebView myWebview;
	String webUrl = "http://unicefkid.herokuapp.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_welcome);
		myWebview = (WebView) findViewById(R.id.wvHome);
		overLoadWebview();
	}

	public void overLoadWebview() {
		myWebview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.equalsIgnoreCase(webUrl + "/pitch_sites")) {
					byte[] postData = EncodingUtils.getBytes("a=b&c=d&e=f",
							"BASE64");
					view.postUrl(
							"https://unicefkid.herokuapp.com/",
							postData);
				}
				Log.e("url: ", url);

				// view.postUrl("https://crushpath-staging.herokuapp.com",
				// postData);
				view.loadUrl(url);
				return true;
			}
		});
	}

	/**
	 * Enables Javascript on Web View Settings and Loads the page with webUrl
	 * 
	 */
	public void loadPage() {
		myWebview.getSettings().setJavaScriptEnabled(true);
		Log.d("DEBUG", webUrl);
		myWebview.loadUrl(webUrl);
	}

	/**
	 * @param key
	 * @param value
	 * 
	 *            <p>
	 *            Takes a key value pair, that are stored in the Shared
	 *            Preference for the Application and can be retrieved for later
	 *            use
	 *            <p>
	 */
	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * @return String Retuns the Registration Id for Push Notifications
	 * 
	 *         Returns Registration ID or "" from Shared Preferences
	 */
	private String loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String regId = sharedPreferences.getString("registrationId", "");
		return regId;
	}

	/**
	 * Registers with GCM after checking the following Conditions and stores the
	 * value in Shared Preference
	 * <p>
	 * <ul>
	 * <li>RegistrationID is not already stored in the Shared Preference
	 * <li>Device is not already registered with Google using
	 * GCMRegistrar.getRegistrationId;
	 * 
	 * <ul>
	 * <p>
	 * <li>If the above are not there, then Register with GCM and save it in
	 * Shared Preference.
	 * 
	 */


	@Override
	protected void onResume() {
		super.onResume();
		loadPage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
