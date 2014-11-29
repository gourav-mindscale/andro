package irishreloreportgen.activity.main;

import java.lang.reflect.Field;

import irishreloreportgen.activity.main.exterior.Exterior;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.utilities.Utilities;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class CommonGoToActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commongo_to);
		((Button) findViewById(R.id.savephase)).setVisibility(View.INVISIBLE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_go_to, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exitreport) {
			startActivity(new Intent(this, IrishreloLunch.class));
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onElementClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.gotoexterior:
				startActivity(new Intent(this, Exterior.class));
				break;
			case R.id.gotointerior:
				startActivity(new Intent(this, Interior.class));
				break;
			case R.id.gotoutility:
				startActivity(new Intent(this, Utilities.class));
				break;
			case R.id.proctonext:
				startActivity(new Intent(this, Utilities.class));
				break;
			case R.id.backtoprev:
				startActivity(new Intent(this, ReportBasicDetails.class));
				break;
		}
		finish();
	}

}
