package com.ransomer.rabbitmqonandroid;

import com.ransomer.rabbitmqonandroid.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

/**
 * An activity representing a single SDNEvent detail screen. This activity is
 * only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a {@link SDNEventDetailActivity}
 * .
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link SDNEventDetailFragment}.
 */
public class SDNEventDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdnevent_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(SDNEventDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(SDNEventDetailFragment.ARG_ITEM_ID));
			SDNEventDetailFragment fragment = new SDNEventDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.sdnevent_detail_container, fragment).commit();
		}
		 Log.d("SDNEventDetailActivity", "Activity Created...");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					SDNEventDetailActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    protected void onStart() {
        super.onStart();
        Log.d("SDNEventDetailActivity", "Activity started");
        
        
    }
	
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("SDNEventDetailActivity", "Activity Resumed");
	}
	
	@Override
	public void onRestart() {
		super.onRestart();
		Log.d("SDNEventDetailActivity", "Activity Restarted");
	}
	
	 @Override
	 public void onPause() {
		 super.onPause();
		 Log.d("SDNEventDetailActivity", "Activity Paused...");
	     
	 }
	 
	 @Override
	 public void onStop() {
		 super.onStop();
		 Log.d("SDNEventDetailActivity", "Activity Stopped...");
	     
	 }
	 
	 @Override
	 public void onDestroy() {
		 super.onDestroy();
		 Log.d("SDNEventDetailActivity", "Activity Destroyed...");
	     
	 }
}
