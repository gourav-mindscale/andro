package irishreloreportgen.activity.main.exterior;
import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.InsertDb;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.activity.main.utilities.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExteriorApartmentParking extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exterior_apartment_form);
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
			renderData();
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		menu.removeItem( R.id.action_exterior);
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
		startActivity(new Intent(this,ExteriorIfApplicable.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,CommentNComp.class));
		finish();
	}
	
		
	public void saveData(View v)
	{
		ContentValues contentValues = new ContentValues();	
		RadioGroup radioyesnoGroup;
		RadioButton radioyesnoButton;
		int selectedId = 0;
		db.openDataBase();
		int Success = -1;
		
			//contentValues.put("inspect_type", "zapper");
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_zapper_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_zapper_quantity_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_checkin_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_pre_departure_zapper_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_pre_departure_zapper_quantity_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_predepart_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_departure_zapper_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_departure_zapper_quantity_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "zapper_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'zapper'", null);
			
			//contentValues.put("inspect_type", "keys");
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_keys_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_keys_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_checkin_qty");
			}
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_pre_departure_keys_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_pre_departure_keys_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_predepart_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_departure_keys_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_departure_keys_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "keys_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'keys'", null);
			try{
			//contentValues.put("inspect_type", "parking_code");
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_parking_code_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_parking_code_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_predepart_qty");
			}
			try
			{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_parking_code_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_code_depart_comm");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'parking_code'", null);
			
			try{
			//contentValues.put("inspect_type", "parking_space_number_if_applicable");
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_parking_space_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_parking_space_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_predepart_qty");
			}
			try
			{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_parking_space_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "parking_space_number_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'parking_space_number_if_applicable'", null);
			
			//contentValues.put("inspect_type", "miscellaneous");
			try
			{
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_Miscellaneous_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_Miscellaneous_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_predepart_qty");
			}
			try
			{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_Miscellaneous_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_depart_comm");
			}
			try{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "miscellaneous_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
			
			//contentValues.put("inspect_type", "action_plan_if_required");
			try
			{
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_action_plan_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_action_plan_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_predepart_qty");
			}
			try
			{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_action_plan_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "action_plan_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'action_plan_if_required'", null);
			try{
			//contentValues.put("inspect_type", "follow_up");
				contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_follow_up_editText)).getText().toString());

			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_follow_up_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_predepart_comm");
			}
			try
			{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_predepart_qty");
			}
			try
			{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_follow_up_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_depart_comm");
			}
			try
			{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "follow_up_predepart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'follow_up'", null);
			
			//contentValues.put("inspect_type", "final_comments");
			try
			{
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_final_comment_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_checkin_comm");
			}
			try
			{
			contentValues.put("checkin_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_checkin_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_pre_departure_final_comment_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_predepart_comm");
			}
			try{
			contentValues.put("predepart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_predepart_qty");
			}
			try{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_departure_final_comment_editText)).getText().toString());
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_depart_comm");
			}
			try{
			contentValues.put("depart_qty", "0");
			}
			catch(Exception E)
			{
				Log.v("for", "final_comments_depart_qty");
			}
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+editJobId+" AND inspect_type = 'final_comments'", null);
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "APARTMENT_PARKING", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"APARTMENT_PARKING", editJobId);
			//if(status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "APARTMENT_PARKING", editJobId,"create/update",CommentNComp.class);
				s2sv.frontEndSend();
			//}else{			
			}
			
		
	}
	void renderData()
	{
		RadioButton radioyesnoButton;
	
	Cursor cursor = SelectDb.getPhaseByData(db, "APARTMENT_PARKING", editJobId, false, true, false);
	if(cursor != null)
	{
		Log.v("countAp", ""+cursor.getCount());
		for(int j=0; j < cursor.getCount(); j++)
		{		
			
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("zapper"))
			{
				try{
				if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_zapper_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_zapper_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_checkin_zapper_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
				
				try{
				if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_pre_departure_zapper_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_pre_departure_zapper_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_pre_departure_zapper_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
				try{
				if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_departure_zapper_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_departure_zapper_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_departure_zapper_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
				
				cursor.moveToNext();
				continue;
			}
			
			
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("keys"))
			{
				try{
				if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_keys_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_keys_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_checkin_keys_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
				
				try{
				if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_pre_departure_keys_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_pre_departure_keys_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch(Exception e)
				{
				e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_pre_departure_keys_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
				try{
				if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_departure_keys_y);
					radioyesnoButton.setChecked(true);
					
				}
				else
				{
					radioyesnoButton=(RadioButton)findViewById(R.id.exterior_departure_keys_n);
					radioyesnoButton.setChecked(true);
				}
				}
				catch( Exception e)
				{
					e.getStackTrace();
				}
				((EditText)findViewById(R.id.exterior_departure_keys_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
				cursor.moveToNext();
				continue;
			}
			
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("parking_code"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_parking_code_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_parking_code_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_parking_code_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("parking_space_number_if_applicable"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_parking_space_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_parking_space_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_parking_space_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_Miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_Miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_Miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("action_plan_if_required"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("follow_up"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
			if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("final_comments"))
			{
				try{
				((EditText)findViewById(R.id.exterior_checkin_final_comment_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
				((EditText)findViewById(R.id.exterior_pre_departure_final_comment_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
				((EditText)findViewById(R.id.exterior_departure_final_comment_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				cursor.moveToNext();
				continue;
			}
		}		
	}	
	}
	void disableOrEnable(Boolean state)
	{
		//findViewById(R.id.deptdtic).setEnabled(state);		
	}

}