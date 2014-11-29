package irishreloreportgen.activity.main.utilities;

import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.utilities.CommunicationNPrecaution;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.exterior.Exterior;
import irishreloreportgen.activity.main.exterior.ExteriorIfApplicable;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.serv.SendToServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class Utilities extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat, timeFormat;
	DbHelper db;
	String editJobId = "0";
	final int CHKIN_ESB_DATE_DIALOG_ID = 996, CHKIN_GAS_DATE_DIALOG_ID = 997, DEPART_ESB_DATE_DIALOG_ID = 998, DEPART_GAS_DATE_DIALOG_ID = 999;
	Calendar chkInesbCal = null, chkIngasCal = null, departesbCal = null, departgasCal = null;
	Calendar chkInesbmornCal = null, chkInesbnitnCal = null, chkIngasmornCal = null, chkIngasnitCal = null;
	Calendar departesbmornCal = null, departesbnitCal = null, departgasmornCal = null, departgasnitCal = null;
	EditText datetext, txtTime;
	long currTime;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.power_utilities);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		timeFormat = new SimpleDateFormat("hh:mm");
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		currTime = 0;//new Date().getTime();
		setCurrentDate(currTime, currTime, currTime, currTime);
		setCurrentTime(currTime, currTime, currTime, currTime, currTime, currTime, currTime, currTime);
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
	private void setCurrentDate(long chkinesbdt, long chkingasdt, long departesbtime, long departgastime) {
		chkInesbCal = Calendar.getInstance();
		chkIngasCal = Calendar.getInstance();
		departesbCal = Calendar.getInstance();
		departgasCal = Calendar.getInstance();		
		try{
			if(chkinesbdt != 0){
				chkInesbCal.setTimeInMillis(chkinesbdt);
				((EditText) findViewById(R.id.chkinesbmordt)).setText(dateFormat.format(new Date(chkinesbdt)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(chkingasdt != 0){
				chkIngasCal.setTimeInMillis(chkingasdt);
				((EditText) findViewById(R.id.chkingasmordt)).setText(dateFormat.format(new Date(chkingasdt)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
	
		
		try{
			if(departesbtime != 0){
				departesbCal.setTimeInMillis(departesbtime);
				((EditText) findViewById(R.id.departesbmordt)).setText(dateFormat.format(new Date(departesbtime)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(departgastime != 0){
				departgasCal.setTimeInMillis(departgastime);
				((EditText) findViewById(R.id.departgasmordt)).setText(dateFormat.format(new Date(departgastime)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
	}
	
	private void setCurrentTime(long chkInesbmorn, long chkInesbnitn, long chkIngasmorn, long chkIngasnit, long departesbmorn, long departesbnit, long departgasmorn, long departgasnit) 
	{
		chkInesbmornCal = Calendar.getInstance();
		chkInesbnitnCal = Calendar.getInstance();
		chkIngasmornCal = Calendar.getInstance();
		chkIngasnitCal = Calendar.getInstance();
		departesbmornCal = Calendar.getInstance();
		departesbnitCal = Calendar.getInstance();
		departgasmornCal = Calendar.getInstance();
		departgasnitCal = Calendar.getInstance();
		
		try{
			if(chkInesbmorn != 0){
				chkInesbmornCal.setTimeInMillis(chkInesbmorn);
				((EditText) findViewById(R.id.chkinesbmortime)).setText(timeFormat.format(new Date(chkInesbmorn)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(chkInesbnitn != 0){
				chkInesbnitnCal.setTimeInMillis(chkInesbnitn);
				((EditText) findViewById(R.id.chkinesbnighttime)).setText(timeFormat.format(new Date(chkInesbnitn)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(chkIngasmorn != 0){
				chkIngasmornCal.setTimeInMillis(chkIngasmorn);
				((EditText) findViewById(R.id.chkingasmortime)).setText(timeFormat.format(new Date(chkIngasmorn)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(chkIngasnit != 0){
				chkIngasnitCal.setTimeInMillis(chkIngasnit);
				((EditText) findViewById(R.id.chkingasnighttime)).setText(timeFormat.format(new Date(chkIngasnit)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		
		
		try{
			if(departesbmorn != 0){
				departesbmornCal.setTimeInMillis(departesbmorn);
				((EditText) findViewById(R.id.departesbmortime)).setText(timeFormat.format(new Date(departesbmorn)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(departesbnit != 0){				
				departesbnitCal.setTimeInMillis(departesbnit);
				((EditText) findViewById(R.id.departesbnighttime)).setText(timeFormat.format(new Date(departesbnit)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(departgasmorn != 0){				
				departgasmornCal.setTimeInMillis(departgasmorn);
				((EditText) findViewById(R.id.departgasmortime)).setText(timeFormat.format(new Date(departgasmorn)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		
		try{
			if(departgasnit != 0){				
				departgasnitCal.setTimeInMillis(departgasnit);
				((EditText) findViewById(R.id.departgasnighttime)).setText(timeFormat.format(new Date(departgasnit)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}

		
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {			
			case 988:
				if(chkInesbmornCal == null)
					chkInesbmornCal = Calendar.getInstance();
				return new TimePickerDialog(this, chkinesbmorn, chkInesbmornCal.get(Calendar.HOUR), chkInesbmornCal.get(Calendar.MINUTE),false);
			case 989:
				if(chkInesbnitnCal == null)
					chkInesbnitnCal = Calendar.getInstance();
				return new TimePickerDialog(this, chkinesbnit, chkInesbnitnCal.get(Calendar.HOUR), chkInesbnitnCal.get(Calendar.MINUTE),false);
				
			case 990:
				if(chkIngasmornCal == null)
					chkIngasmornCal = Calendar.getInstance();
				return new TimePickerDialog(this, chkingasmorn, chkIngasmornCal.get(Calendar.HOUR), chkIngasmornCal.get(Calendar.MINUTE),false);
				
			case 991:
				if(chkIngasnitCal == null)
					chkIngasnitCal = Calendar.getInstance();
				return new TimePickerDialog(this, chkingasnit, chkIngasnitCal.get(Calendar.HOUR), chkIngasnitCal.get(Calendar.MINUTE),false);
				
			case 992:
				if(departesbmornCal == null)
					departesbmornCal = Calendar.getInstance();
				return new TimePickerDialog(this, departesbmorn, departesbmornCal.get(Calendar.HOUR), departesbmornCal.get(Calendar.MINUTE),false);
			case 993:
				if(departesbnitCal == null)
					departesbnitCal = Calendar.getInstance();
				return new TimePickerDialog(this, departesbnit, departesbnitCal.get(Calendar.HOUR), departesbnitCal.get(Calendar.MINUTE),false);
			
			case 994:
				if(departgasmornCal == null)
					departgasmornCal = Calendar.getInstance();
				return new TimePickerDialog(this, departgasmorn, departgasmornCal.get(Calendar.HOUR), departgasmornCal.get(Calendar.MINUTE),false);
			
			case 995:
				if(departgasnitCal == null)
					departgasnitCal = Calendar.getInstance();
				return new TimePickerDialog(this, departgasnit, departgasnitCal.get(Calendar.HOUR), departgasnitCal.get(Calendar.MINUTE),false);
			
		
			case CHKIN_ESB_DATE_DIALOG_ID:
				if(chkInesbCal == null)
					chkInesbCal = Calendar.getInstance();				
				return new DatePickerDialog(this,chkinesbdatePickList,chkInesbCal.get(Calendar.YEAR),chkInesbCal.get(Calendar.MONTH),chkInesbCal.get(Calendar.DAY_OF_MONTH));
			case CHKIN_GAS_DATE_DIALOG_ID:
				if(chkIngasCal == null)
					chkIngasCal = Calendar.getInstance();				
				return new DatePickerDialog(this,chkingasdatePickList,chkIngasCal.get(Calendar.YEAR),chkIngasCal.get(Calendar.MONTH),chkIngasCal.get(Calendar.DAY_OF_MONTH));
			case DEPART_ESB_DATE_DIALOG_ID:
				if(departesbCal == null)
					departesbCal = Calendar.getInstance();				
				return new DatePickerDialog(this,departesbdatePickList,departesbCal.get(Calendar.YEAR),departesbCal.get(Calendar.MONTH),departesbCal.get(Calendar.DAY_OF_MONTH));
			case DEPART_GAS_DATE_DIALOG_ID:
				if(departgasCal == null)
					departgasCal = Calendar.getInstance();				
				return new DatePickerDialog(this,departgasdatePickList,departgasCal.get(Calendar.YEAR),departgasCal.get(Calendar.MONTH),departgasCal.get(Calendar.DAY_OF_MONTH));
		}			
		return null;
	}
	private DatePickerDialog.OnDateSetListener chkinesbdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			chkInesbCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	private DatePickerDialog.OnDateSetListener chkingasdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			chkIngasCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	
	private DatePickerDialog.OnDateSetListener departesbdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			departesbCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	private DatePickerDialog.OnDateSetListener departgasdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			departgasCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	@SuppressWarnings("deprecation")
	public void calendarOperations(View v)
	{
		
		switch(v.getId())
		{
			case R.id.chkinesbmordt:
				datetext = (EditText) findViewById(R.id.chkinesbmordt);
				showDialog(CHKIN_ESB_DATE_DIALOG_ID);
				break;
			
			case R.id.chkingasmordt:
				datetext = (EditText) findViewById(R.id.chkingasmordt);
				showDialog(CHKIN_GAS_DATE_DIALOG_ID);
				break;
			
			case R.id.departesbmordt:
				datetext = (EditText) findViewById(R.id.departesbmordt);
				showDialog(DEPART_ESB_DATE_DIALOG_ID);
				break;
					
			case R.id.departgasmordt:
				datetext = (EditText) findViewById(R.id.departgasmordt);
				showDialog(DEPART_GAS_DATE_DIALOG_ID);
				break;
			
			case R.id.chkinesbmordateic:
				datetext = (EditText) findViewById(R.id.chkinesbmordt);
				showDialog(CHKIN_ESB_DATE_DIALOG_ID);
				break;
					
			case R.id.chkingasmordateic:
				datetext = (EditText) findViewById(R.id.chkingasmordt);
				showDialog(CHKIN_GAS_DATE_DIALOG_ID);
				break;
			case R.id.departesbmordateic:
				datetext = (EditText) findViewById(R.id.departesbmordt);
				showDialog(DEPART_ESB_DATE_DIALOG_ID);
				break;
			case R.id.departgasmordateic:
				datetext = (EditText) findViewById(R.id.departgasmordt);
				showDialog(DEPART_GAS_DATE_DIALOG_ID);
				break;
			
		}
	}
	
	
	private TimePickerDialog.OnTimeSetListener chkinesbmorn = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	    
	private TimePickerDialog.OnTimeSetListener chkinesbnit = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	        
	        
	private TimePickerDialog.OnTimeSetListener chkingasmorn = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };	        
	        
	private TimePickerDialog.OnTimeSetListener chkingasnit = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	        
	private TimePickerDialog.OnTimeSetListener departesbmorn = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	        
	        
	private TimePickerDialog.OnTimeSetListener departesbnit = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	        
	       
	private TimePickerDialog.OnTimeSetListener departgasmorn =
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };
	        
	        
	private TimePickerDialog.OnTimeSetListener departgasnit = 
	        new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					txtTime.setText(hourOfDay + ":" + minute);
				}
	        };

	@SuppressWarnings("deprecation")
	public void timerOperations(View v)
	{		
		switch(v.getId())
		{
			case R.id.chkinesbmortime:
				txtTime = (EditText) findViewById(R.id.chkinesbmortime);
				showDialog(988);
				break;
			
			case R.id.chkinesbnighttime:
				txtTime = (EditText) findViewById(R.id.chkinesbnighttime);
				showDialog(989);
				break;
			
			case R.id.chkingasmortime:
				txtTime = (EditText) findViewById(R.id.chkingasmortime);
				showDialog(990);
				break;
			
			case R.id.chkingasnighttime:
				txtTime = (EditText) findViewById(R.id.chkingasnighttime);
				showDialog(991);
				break;
			
			case R.id.departesbmortime:
				txtTime = (EditText) findViewById(R.id.departesbmortime);
				showDialog(992);
				break;
					
			case R.id.departesbnighttime:
				txtTime = (EditText) findViewById(R.id.departesbnighttime);
				showDialog(993);
				break;
					
			case R.id.departgasmortime:
				txtTime = (EditText) findViewById(R.id.departgasmortime);
				showDialog(994);
				break;
					
			case R.id.departgasnighttime:
				txtTime = (EditText) findViewById(R.id.departgasnighttime);
				showDialog(995);
				break;	
		}
	}

	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,CommonGoToActivity.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, CommunicationNPrecaution.class));
    	finish();
	}
	
	
	
	void saveData(View v)
	{
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();		
		try{
			
			if(((EditText) findViewById(R.id.chkingasmordt)).getText().equals(""))
				contentValues.put("trackdate", 0);
			else
				contentValues.put("trackdate", chkIngasCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.chkingasmortime)).getText().equals(""))
				contentValues.put("morntime", 0);
			else
				contentValues.put("morntime", chkIngasmornCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.chkingasnighttime)).getText().equals(""))
				contentValues.put("nittime", 0);
			else
				contentValues.put("nittime", chkIngasnitCal.getTimeInMillis());
			
			
			contentValues.put("meter_number", ((EditText)findViewById(R.id.chkingasmtrno)).getText().toString());
			contentValues.put("mornreading", ((EditText)findViewById(R.id.chkingasmorread)).getText().toString());
			contentValues.put("nitreading", ((EditText)findViewById(R.id.chkingasnightread)).getText().toString());
			contentValues.put("tank_loc", "");
			contentValues.put("tank_qty", "");
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='Gas' AND chkin_or_depart=1", null);
			
			
			if(((EditText) findViewById(R.id.departgasmordt)).getText().equals(""))
				contentValues.put("trackdate", 0);
			else
				contentValues.put("trackdate", departgasCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.departgasmortime)).getText().equals(""))
				contentValues.put("morntime", 0);
			else
				contentValues.put("morntime", departgasmornCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.departgasnighttime)).getText().equals(""))
				contentValues.put("nittime", 0);
			else
				contentValues.put("nittime", departgasnitCal.getTimeInMillis());
			
			
			contentValues.put("meter_number", ((EditText)findViewById(R.id.departgasmtrno)).getText().toString());
			contentValues.put("mornreading", ((EditText)findViewById(R.id.departgasmorread)).getText().toString());
			contentValues.put("nitreading", ((EditText)findViewById(R.id.departgasnightread)).getText().toString());
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='Gas' AND chkin_or_depart=3", null);
			
			
			
			if(((EditText) findViewById(R.id.departgasmordt)).getText().equals(""))
				contentValues.put("trackdate", 0);
			else
				contentValues.put("trackdate", chkInesbCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.chkinesbmortime)).getText().equals(""))
				contentValues.put("morntime", 0);
			else
				contentValues.put("morntime", chkInesbmornCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.chkinesbnighttime)).getText().equals(""))
				contentValues.put("nittime", 0);
			else
				contentValues.put("nittime", chkInesbnitnCal.getTimeInMillis());
			
			
			
			
			
			
			
			contentValues.put("meter_number", ((EditText)findViewById(R.id.chkinesbmtrno)).getText().toString());
			contentValues.put("mornreading", ((EditText)findViewById(R.id.chkinesbmorread)).getText().toString());
			contentValues.put("nitreading", ((EditText)findViewById(R.id.chkinesbnightread)).getText().toString());
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='ESB' AND chkin_or_depart=1", null);
			
			
			if(((EditText) findViewById(R.id.departesbmordt)).getText().equals(""))
				contentValues.put("trackdate", 0);
			else
				contentValues.put("trackdate", departesbCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.departesbmortime)).getText().equals(""))
				contentValues.put("morntime", 0);
			else
				contentValues.put("morntime", departesbmornCal.getTimeInMillis());
			
			if(((EditText) findViewById(R.id.departesbnighttime)).getText().equals(""))
				contentValues.put("nittime", 0);
			else
				contentValues.put("nittime", departesbnitCal.getTimeInMillis());
			
			
			
			contentValues.put("meter_number", ((EditText)findViewById(R.id.departesbmtrno)).getText().toString());
			contentValues.put("mornreading", ((EditText)findViewById(R.id.departesbmorread)).getText().toString());
			contentValues.put("nitreading", ((EditText)findViewById(R.id.departesbnightread)).getText().toString());
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='ESB' AND chkin_or_depart=3", null);
			
			contentValues = new ContentValues();
			contentValues.put("tank_loc", ((EditText)findViewById(R.id.chkintankloc)).getText().toString());
			contentValues.put("tank_qty", ((EditText)findViewById(R.id.chkinqty)).getText().toString());
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='Oil' AND chkin_or_depart=1", null);
			
			contentValues = new ContentValues();
			contentValues.put("tank_loc", ((EditText)findViewById(R.id.departtankloc)).getText().toString());
			contentValues.put("tank_qty", ((EditText)findViewById(R.id.departqty)).getText().toString());
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+editJobId+" AND type ='Oil' AND chkin_or_depart=3", null);
		}catch(Exception e){
			e.getStackTrace();//Log.e("Error", e.getLocalizedMessage());
		}	
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "POWER_UTILITIES", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"POWER_UTILITIES", editJobId);
		//if(status!= null && status.getInt(2) == 1)
		{
			SendToServer s2sv = new SendToServer(db, this, true, "POWER_UTILITIES", editJobId,"create/update",CommunicationNPrecaution.class);
			s2sv.frontEndSend();
		//}else{	
			
		}		
	}
	
	
	void renderData()
	{
		long chkInesbmorn =0, chkInesbnitn =0, chkIngasmorn =0, chkIngasnit =0, departesbmorn =0, departesbnit =0, departgasmorn =0, departgasnit = 0;
		long chkinesbdt = 0, chkingasdt = 0, departesbtime = 0, departgastime = 0;
		Cursor cursor = SelectDb.getPhaseByData(db, "POWER_UTILITIES", editJobId, false, true, false);
		if(cursor != null)
		{
			for(int j=0; j < cursor.getCount(); j++)
			{			
				switch(cursor.getString(cursor.getColumnIndex("type")))
				{
					case "ESB":
						if(cursor.getInt(cursor.getColumnIndex("chkin_or_depart")) == 1)
						{
							chkinesbdt = cursor.getLong(cursor.getColumnIndex("trackdate")); 
							chkInesbmorn = cursor.getLong(cursor.getColumnIndex("morntime"));
							chkInesbnitn =cursor.getLong(cursor.getColumnIndex("nittime"));
							((EditText)findViewById(R.id.chkinesbmtrno)).setText(cursor.getString(cursor.getColumnIndex("meter_number")));
							((EditText)findViewById(R.id.chkinesbmorread)).setText(cursor.getString(cursor.getColumnIndex("mornreading")));
							((EditText)findViewById(R.id.chkinesbnightread)).setText(cursor.getString(cursor.getColumnIndex("nitreading")));
							
						}else{
							((EditText)findViewById(R.id.departesbmtrno)).setText(cursor.getString(cursor.getColumnIndex("meter_number")));
							((EditText)findViewById(R.id.departesbmorread)).setText(cursor.getString(cursor.getColumnIndex("mornreading")));
							((EditText)findViewById(R.id.departesbnightread)).setText(cursor.getString(cursor.getColumnIndex("nitreading")));
							
							 departesbtime = cursor.getLong(cursor.getColumnIndex("trackdate"));
							 departesbmorn =cursor.getLong(cursor.getColumnIndex("morntime"));
							 departesbnit =cursor.getLong(cursor.getColumnIndex("nittime"));
						}
						break;
					case "Gas":
						if(cursor.getInt(cursor.getColumnIndex("chkin_or_depart")) == 1)
						{	
							chkingasdt = cursor.getLong(cursor.getColumnIndex("trackdate"));
							chkIngasmorn =cursor.getLong(cursor.getColumnIndex("morntime"));
							chkIngasnit =cursor.getLong(cursor.getColumnIndex("nittime"));
							((EditText)findViewById(R.id.chkingasmtrno)).setText(cursor.getString(cursor.getColumnIndex("meter_number")));
							((EditText)findViewById(R.id.chkingasmorread)).setText(cursor.getString(cursor.getColumnIndex("mornreading")));
							((EditText)findViewById(R.id.chkingasnightread)).setText(cursor.getString(cursor.getColumnIndex("nitreading")));
							
						}else{
							departgastime = cursor.getLong(cursor.getColumnIndex("trackdate"));
							departgasmorn =cursor.getLong(cursor.getColumnIndex("morntime"));
							departgasnit =cursor.getLong(cursor.getColumnIndex("nittime"));
							((EditText)findViewById(R.id.departgasmtrno)).setText(cursor.getString(cursor.getColumnIndex("meter_number")));
							((EditText)findViewById(R.id.departgasmorread)).setText(cursor.getString(cursor.getColumnIndex("mornreading")));
							((EditText)findViewById(R.id.departgasnightread)).setText(cursor.getString(cursor.getColumnIndex("nitreading")));
							
						}
						break;
					case "Oil":
						if(cursor.getInt(cursor.getColumnIndex("chkin_or_depart")) == 1)
						{
							((EditText)findViewById(R.id.chkintankloc)).setText(cursor.getString(cursor.getColumnIndex("tank_loc")));
							((EditText)findViewById(R.id.chkinqty)).setText(cursor.getString(cursor.getColumnIndex("tank_qty")));
							
						}else{
							((EditText)findViewById(R.id.departtankloc)).setText(cursor.getString(cursor.getColumnIndex("tank_loc")));
							((EditText)findViewById(R.id.departqty)).setText(cursor.getString(cursor.getColumnIndex("tank_qty")));
						}
						break;
				}
				cursor.moveToNext();
			}
			try{
				setCurrentTime(chkInesbmorn, chkInesbnitn, chkIngasmorn, chkIngasnit, departesbmorn, departesbnit, departgasmorn, departgasnit);
				setCurrentDate(chkinesbdt, chkingasdt, departesbtime, departgastime);
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
}