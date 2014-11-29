package irishreloreportgen.activity.main.utilities;

import java.text.SimpleDateFormat;

import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.exterior.Exterior;
import irishreloreportgen.activity.main.exterior.ExteriorIfApplicable;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Build;

public class CommunicationNPrecaution extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	DbHelper db;
	String editJobId = "0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		setContentView(R.layout.communication_nprecaution);
		//((Button) findViewById(R.id.proctonext)).setText("Finish"); 
		if(!editJobId.equals("0"))
			renderData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		menu.removeItem( R.id.action_utility);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id)
		{
			case  R.id.action_commgoto:
				startActivity(new Intent(this, CommonGoToActivity.class));
				finish();
				return true;
			case  R.id.action_utility:
				startActivity(new Intent(this, Utilities.class));
				finish();
				return true;
			case  R.id.action_interior:
				startActivity(new Intent(this, Interior.class));
				finish();
				return true;
			case  R.id.action_exterior:
				startActivity(new Intent(this, Exterior.class));
				finish();
				return true;
			case  R.id.action_home:
				startActivity(new Intent(this, IrishreloLunch.class));
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onElementClick(View v) {
		switch(v.getId())
		{
		case R.id.savephase:
			saveData(v);
			break;
		case R.id.proctonext:
			gotoNext(v);
			break;
		case R.id.backtoprev:
			backTheprocess(v);
			
			break;
		}
	}

	
	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,Utilities.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,MPRN_GAS.class));
		finish();
	}
	
	void saveData(View v)
	{
		ContentValues contentValues = new ContentValues();	
		RadioGroup radioSexGroup;
		RadioButton radioSexButton;
		int selectedId = 0;
		db.openDataBase();
		int Success = -1;
		try{
			contentValues.put("telephone_number", ((EditText)findViewById(R.id.telephoneno)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.isheadset);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);
			contentValues.put("phone_handset", radioSexButton.getText().toString().equals("Yes")?1:0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("headset_count", ((EditText)findViewById(R.id.noofhdset)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.isphwork);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);
			contentValues.put("phone_working", radioSexButton.getText().toString().equals("Yes")?1:0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("broadband_suplier", ((EditText)findViewById(R.id.broadbandsup)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}			
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.issetupworks);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);			
			contentValues.put("is_broradband_working", radioSexButton.getText().toString().equals("Yes")?1:0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			contentValues.put("alarm_code", ((EditText)findViewById(R.id.alarmcode)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		try{
			contentValues.put("supplier", ((EditText)findViewById(R.id.alarmsupp)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}			
		try{
			contentValues.put("precaut_instructor", ((EditText)findViewById(R.id.precat_instruct)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("smoke_alarm_nos", ((EditText)findViewById(R.id.smokalrmno)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("locations", ((EditText)findViewById(R.id.smokalrmloc)).getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.issmokalrmwork);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);	
			contentValues.put("smokalrm_working",  radioSexButton.getText().toString().equals("Yes")?1:0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("general_cp",  ((EditText)findViewById(R.id.gencollectpt)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			contentValues.put("general_cd",  ((EditText)findViewById(R.id.gencollectday)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		try{
			contentValues.put("general_prov", ((EditText)findViewById(R.id.genprovider)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("recycle_cp",  ((EditText)findViewById(R.id.reccollectpt)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("recycle_cd",  ((EditText)findViewById(R.id.recycollectday)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("recycle_prov", ((EditText)findViewById(R.id.recyprovider)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		try{
			contentValues.put("glass_cp",  ((EditText)findViewById(R.id.glscollectpt)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			contentValues.put("glass_cd",  ((EditText)findViewById(R.id.glscollectday)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
			contentValues.put("glass_prov", ((EditText)findViewById(R.id.glsprovider)).getText().toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.isbintags);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);				
			contentValues.put("bin_tag",  radioSexButton.getText().toString().equals("Yes")?1:0);			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.istelvsnwork);
		selectedId = radioSexGroup.getCheckedRadioButtonId();
		radioSexButton = (RadioButton) findViewById(selectedId);
		contentValues.put("tv_working",  radioSexButton.getText().toString().equals("Yes")?1:0);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.isdvdconn);
		selectedId = radioSexGroup.getCheckedRadioButtonId();
		radioSexButton = (RadioButton) findViewById(selectedId);
		contentValues.put("dvd_connected",  radioSexButton.getText().toString().equals("Yes")?1:0);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.iscableconn);
			selectedId = radioSexGroup.getCheckedRadioButtonId();
			radioSexButton = (RadioButton) findViewById(selectedId);
			contentValues.put("skyorcbl_connected",  radioSexButton.getText().toString().equals("Yes")?1:0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			Success = db.MyDB().update("COMMUNICATION_N_PRECAUTIONS", contentValues, "jobid ="+editJobId, null);
		}catch(Exception e){
				e.printStackTrace();	
		}
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "COMMUNICATION_N_PRECAUTIONS", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"COMMUNICATION_N_PRECAUTIONS", editJobId);
		//if(status.getInt(2) == 1)
		{
			SendToServer s2sv = new SendToServer(db, this, true, "COMMUNICATION_N_PRECAUTIONS", editJobId,"create/update",CommonGoToActivity.class);
			s2sv.frontEndSend();
		//}else{			
		}
						
	}
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "COMMUNICATION_N_PRECAUTIONS", editJobId, false, true, false);
		Log.v("count", ""+cursor.getCount());
		try{
			((EditText)findViewById(R.id.telephoneno)).setText(cursor.getString(cursor.getColumnIndex("telephone_number")));
		}catch(Exception e){
			e.printStackTrace();	
		}
		RadioGroup radioSexGroup;
		int selected = 0;
		try{
			selected = cursor.getInt(cursor.getColumnIndex("phone_handset"));
			radioSexGroup = (RadioGroup) findViewById(R.id.isheadset);
			if(selected==1)
				radioSexGroup.check(R.id.headset_y);
			else
				radioSexGroup.check(R.id.headset_n);
		}catch(Exception e){
				e.printStackTrace();	
		}
		
		((EditText)findViewById(R.id.noofhdset)).setText(cursor.getString(cursor.getColumnIndex("headset_count")));
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.isphwork);
			selected = cursor.getInt(cursor.getColumnIndex("phone_working"));
			if(selected==1)
				radioSexGroup.check(R.id.phwork_y);
			else
				radioSexGroup.check(R.id.phwork_n);
		}catch(Exception e){
				e.printStackTrace();	
		}
		((EditText)findViewById(R.id.broadbandsup)).setText(cursor.getString(cursor.getColumnIndex("broadband_suplier")));
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.issetupworks);		
			selected = cursor.getInt(cursor.getColumnIndex("is_broradband_working"));
			if(selected==1)
				radioSexGroup.check(R.id.setupwork_y);
			else
				radioSexGroup.check(R.id.setupwork_n);
			
		}catch(Exception e){
				e.printStackTrace();	
		}
		((EditText)findViewById(R.id.alarmcode)).setText(cursor.getString(cursor.getColumnIndex("alarm_code")));
		
		((EditText)findViewById(R.id.alarmsupp)).setText(cursor.getString(cursor.getColumnIndex("supplier")));
		((EditText)findViewById(R.id.precat_instruct)).setText(cursor.getString(cursor.getColumnIndex("precaut_instructor")));
		((EditText)findViewById(R.id.smokalrmno)).setText(cursor.getString(cursor.getColumnIndex("smoke_alarm_nos")));
		((EditText)findViewById(R.id.smokalrmloc)).setText(cursor.getString(cursor.getColumnIndex("locations")));
		
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.issmokalrmwork);		
			selected = cursor.getInt(cursor.getColumnIndex("smokalrm_working"));
			if(selected==1)
				radioSexGroup.check(R.id.smokalrm_y);
			else
				radioSexGroup.check(R.id.smokalrm_n);
				
		}catch(Exception e){
				e.printStackTrace();	
		}
		((EditText)findViewById(R.id.gencollectpt)).setText(cursor.getString(cursor.getColumnIndex("general_cp")));
		((EditText)findViewById(R.id.gencollectday)).setText(cursor.getString(cursor.getColumnIndex("general_cd")));
		((EditText)findViewById(R.id.genprovider)).setText(cursor.getString(cursor.getColumnIndex("general_prov")));
		
		((EditText)findViewById(R.id.reccollectpt)).setText(cursor.getString(cursor.getColumnIndex("recycle_cp")));
		((EditText)findViewById(R.id.recycollectday)).setText(cursor.getString(cursor.getColumnIndex("recycle_cd")));
		((EditText)findViewById(R.id.recyprovider)).setText(cursor.getString(cursor.getColumnIndex("recycle_prov")));
		
		((EditText)findViewById(R.id.glscollectpt)).setText(cursor.getString(cursor.getColumnIndex("glass_cp")));
		((EditText)findViewById(R.id.glscollectday)).setText(cursor.getString(cursor.getColumnIndex("glass_cd")));
		((EditText)findViewById(R.id.glsprovider)).setText(cursor.getString(cursor.getColumnIndex("glass_prov")));
		
		radioSexGroup = (RadioGroup) findViewById(R.id.isbintags);		
		selected = cursor.getInt(cursor.getColumnIndex("bin_tag"));
		if(selected==1)
			radioSexGroup.check(R.id.bintags_y);
		else
			radioSexGroup.check(R.id.bintags_n);
		
		try{
		
			radioSexGroup = (RadioGroup) findViewById(R.id.istelvsnwork);		
			selected = cursor.getInt(cursor.getColumnIndex("tv_working"));
			if(selected==1)
				radioSexGroup.check(R.id.telvsnwork_y);
			else
				radioSexGroup.check(R.id.telvsnwork_n);
					
		}catch(Exception e){
				e.printStackTrace();	
		}
		
		try{

			selected = cursor.getInt(cursor.getColumnIndex("dvd_connected"));

			if(selected==1)
				radioSexGroup.check(R.id.dvdconn_y);
			else
				radioSexGroup.check(R.id.dvdconn_n);
						
		}catch(Exception e){
				e.printStackTrace();	
		}
		try{
			radioSexGroup = (RadioGroup) findViewById(R.id.iscableconn);
			selected = cursor.getInt(cursor.getColumnIndex("skyorcbl_connected"));

			if(selected==1)
				radioSexGroup.check(R.id.cableconn_y);
			else
				radioSexGroup.check(R.id.cableconn_n);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
