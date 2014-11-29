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

public class ExteriorIfApplicable extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exterior_ifapplicable);
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
		startActivity(new Intent(this,Exterior.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,ExteriorApartmentParking.class));
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
		try{
			//contentValues.put("inspect_type", "zapper_entrance_for_gate");
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_gate_entrance_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "zapper_entrance_for_gate_comm");
		}
			try{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_gate_entrance_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "zapper_entrance_for_gate_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_preDeparture_gate_entrance_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "zapper_entrance_for_gate_pre_deptcomm");	
			}
			try
			{
				contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_preDeparture_gate_entrance_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "zapper_entrance_for_gate_pedept_qty");
			}
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_Departure_gate_entrance_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e){
				Log.v("for", "zapper_entrance_for_gate_dept_comm");
			}
			try {
				contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_Departure_gate_entrance_quantity_editText)).getText().toString());
			} catch (Exception e) {
				Log.v("for", "zapper_entrance_for_gate_dept_qty");
			}
			
		
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'zapper_entrance_for_gate'", null);

			//contentValues.put("inspect_type", "entrance_swipe_card");
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_swipe_card_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_swipe_card_comm");
			}
			try {
				
				contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_swipe_card_quantity_editText)).getText().toString());
			} catch (Exception e) {
				Log.v("for", "exterior_checkin_swipe_card_qty");
				}
			
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_preDeparture_swipe_card_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_swipe_card_comm");
			}
			try{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_preDeparture_swipe_card_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_swipe_card_qty");
			}
			try {
				radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_Departure_swipe_card_radioGroup);
				selectedId = radioyesnoGroup.getCheckedRadioButtonId();
				radioyesnoButton = (RadioButton) findViewById(selectedId);
				contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			} catch (Exception e) {
				Log.v("for", "exterior_depart_swipe_card_comm");
			}
			try{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_Departure_swipe_card_quantity_editText)).getText().toString());
			}
			catch (Exception e) {
				Log.v("for", "exterior_depart_swipe_card_qty");
			}
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'entrance_swipe_card'", null);
			try
			{
			//contentValues.put("inspect_type", "entrance_key");
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_entrance_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch (Exception e) {
				Log.v("for", "exterior_checkin_entrance_key_comm");
			}
			try
			{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_entrance_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_entrance_key_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_preDeparture_entrance_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_entrance_key_comm");
			}
			try{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_preDeparture_entrance_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_entrance_key_qty");
			}
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_Departure_entrance_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_entrance_key_comm");
			}
			try{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_Departure_entrance_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_entrance_key_qty");
			}
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'entrance_key'", null);
			try{
			//contentValues.put("inspect_type", "letter_box_key");
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_letter_box_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_letter_box_key_comm");
			}
			try
			{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_letter_box_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_letter_box_key_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_preDeparture_letter_box_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_letter_box_key_comm");
			}
			try{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_preDeparture_letter_box_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_letter_box_key_qty");
			}
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_Departure_letter_box_key_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_letter_box_key_comm");
			}
			try{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_Departure_letter_box_key_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_letter_box_key_qty");
			}
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'letter_box_key'", null);
			try{
			//contentValues.put("inspect_type", "lift_swipe_card");
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_checkin_lift_swipe_card_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_lift_swipe_card_comm");
			}
			try
			{
			contentValues.put("checkin_qty", ((EditText)findViewById(R.id.exterior_checkin_lift_swipe_card_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_lift_swipe_card_qty");
			}
			try
			{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_preDeparture_lift_swipe_card_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("predepart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_lift_swipe_card_comm");
			}
			try
			{
			contentValues.put("predepart_qty", ((EditText)findViewById(R.id.exterior_preDeparture_lift_swipe_card_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_lift_swipe_card_qty");
			}
			try{
			radioyesnoGroup = (RadioGroup) findViewById(R.id.exterior_Departure_lift_swipe_card_radioGroup);
			selectedId = radioyesnoGroup.getCheckedRadioButtonId();
			radioyesnoButton = (RadioButton) findViewById(selectedId);
			contentValues.put("depart_comm", radioyesnoButton.getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_lift_swipe_card_comm");
			}
			try{
			contentValues.put("depart_qty", ((EditText)findViewById(R.id.exterior_Departure_lift_swipe_card_quantity_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_lift_swipe_card_qty");
			}
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lift_swipe_card'", null);
			try{
			//contentValues.put("inspect_type", "pedestrian_access_code");
			contentValues.put("checkin_comm", ((EditText) findViewById(R.id.exterior_checkin_pedestrian_access_code_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_pedestrian_access_code_comm");
			}
			try
			{
			contentValues.put("checkin_qty", 0);
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_checkin_pedestrian_access_code_qty");
			}
			try
			{
			contentValues.put("predepart_comm", ((EditText) findViewById(R.id.exterior_preDeparture_pedestrian_access_code_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_pedestrian_access_code_comm");

			}
			
			try{
			contentValues.put("predepart_qty", 0);
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_predepart_pedestrian_access_code_comm");

			}
			try{
			contentValues.put("depart_comm", ((EditText) findViewById(R.id.exterior_Departure_pedestrian_access_code_editText)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_pedestrian_access_code_comm");
			}
			try{
			contentValues.put("depart_qty", 0);
			}
			catch(Exception e)
			{
				Log.v("for", "exterior_depart_pedestrian_access_code_qty");
			}
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+editJobId+" AND inspect_type = 'pedestrian_access_code'", null);
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "IF_APPLICABLE", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"IF_APPLICABLE", editJobId);
			//if(status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "IF_APPLICABLE", editJobId,"create/update",IrishreloLunch.class);
				s2sv.frontEndSend();
			//}else{			
			}
				
		
	}
	void renderData()
	{
		RadioButton radioyesnoButton;
		Cursor cursor = SelectDb.getPhaseByData(db, "IF_APPLICABLE", editJobId, false, true, false);
		
		if(cursor != null)
		{
			Log.v("countif", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{		
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("zapper_entrance_for_gate"))
				{
					try{
						if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
						{
							radioyesnoButton=(RadioButton)findViewById(R.id.checkin_entrance_y);
							radioyesnoButton.setChecked(true);
							
						}
						else
						{
							radioyesnoButton=(RadioButton)findViewById(R.id.checkin_entrance_n);
							radioyesnoButton.setChecked(true);
						}
					}catch(Exception e){
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_checkin_gate_entrance_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.preDeparture_entrance_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.preDeparture_entrance_n);
						radioyesnoButton.setChecked(true);
					}
					}catch(Exception e){
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_preDeparture_gate_entrance_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.Departure_entrance_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_gate_entrance_quantity_editText);
						radioyesnoButton.setChecked(true);
					}
					}catch(Exception e){
						e.getStackTrace();
					}
					
					((EditText)findViewById(R.id.exterior_Departure_gate_entrance_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
					cursor.moveToNext();
					continue;
				}


				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("entrance_swipe_card"))
				{
					try{
					if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}catch(Exception e){
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_checkin_swipe_card_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_preDeparture_swipe_card_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
					e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_Departure_swipe_card_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("entrance_key"))
				{
					try{
					if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_entrance_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_entrance_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e )
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_checkin_entrance_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
					
					try{
					if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_entrance_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_entrance_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_preDeparture_entrance_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_entrance_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_entrance_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_Departure_entrance_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
					cursor.moveToNext();
					continue;
				}
				
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("letter_box_key"))
				{
					try{
					if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_letter_box_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_letter_box_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_checkin_letter_box_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
					
					try{
					if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_letter_box_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_letter_box_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_preDeparture_letter_box_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
					
					try{
					if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_letter_box_key_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_letter_box_key_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_Departure_letter_box_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lift_swipe_card"))
				{
					try{
					if(cursor.getString(cursor.getColumnIndex("checkin_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_lift_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_checkin_lift_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_checkin_lift_swipe_card_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_qty")));
					
					try{
					if(cursor.getString(cursor.getColumnIndex("predepart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_lift_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_preDeparture_lift_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_preDeparture_lift_swipe_card_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_qty")));
					try{
					if(cursor.getString(cursor.getColumnIndex("depart_comm")).equalsIgnoreCase("yes"))
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_lift_swipe_card_y);
						radioyesnoButton.setChecked(true);
						
					}
					else
					{
						radioyesnoButton=(RadioButton)findViewById(R.id.exterior_Departure_lift_swipe_card_n);
						radioyesnoButton.setChecked(true);
					}
					}
					catch(Exception e)
					{
						e.getStackTrace();
					}
					((EditText)findViewById(R.id.exterior_Departure_entrance_key_quantity_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_qty")));
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("pedestrian_access_code"))
				{
					((EditText)findViewById(R.id.exterior_checkin_pedestrian_access_code_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.exterior_preDeparture_pedestrian_access_code_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.exterior_Departure_pedestrian_access_code_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
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
