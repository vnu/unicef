package org.unicef.unicefkid;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Challenge extends Activity {

	private static final int CHALLENGE_ACTIVITY_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge);
	}
	
	public void onComposeView(Menu v) {
		Intent intent = new Intent(this, Challenge.class);
		startActivityForResult(intent, Challenge.CHALLENGE_ACTIVITY_ID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.challenge, menu);
		return true;
	}

}
