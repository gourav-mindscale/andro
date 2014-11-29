package irishreloreportgen.activity.main;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.InsertDb;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.serv.SendToServer;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class ReportBasicDetails extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	Calendar chkInCal = null, preDeptCal = null, DeptCal = null;
	EditText datetext;
	DbHelper db;
	String editJobId ="0";
	ProgressDialog progress;
	Boolean valid = true;
	static final int CHKIN_DATE_DIALOG_ID = 997, PRDEPT_DATE_DIALOG_ID = 998, DEPT_DATE_DIALOG_ID = 999;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_basic_details);
		setCurrentDate(0,0,0);
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if(!editJobId.equals("0"))
		{
			showData(editJobId);
			((TextView) findViewById(R.id.addeditbasic)).setText("Edit Report");
		}else{
			((Button) findViewById(R.id.proctonext)).setVisibility(View.INVISIBLE);
		}
		((Button) findViewById(R.id.backtoprev)).setText("Exit");
	}
	private void setCurrentDate(long chkintime, long predepttime, long depttime) {
		chkInCal = Calendar.getInstance();
		preDeptCal = Calendar.getInstance();
		DeptCal = Calendar.getInstance();
		
		try{
			if(chkintime != 0){
				chkInCal.setTimeInMillis(chkintime);
				((EditText) findViewById(R.id.checkindt)).setText(dateFormat.format(new Date(chkintime)));			
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}		
		try{
			if(predepttime != 0){
				preDeptCal.setTimeInMillis(predepttime);
				((EditText) findViewById(R.id.predeptdt)).setText(dateFormat.format(new Date(predepttime)));				
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
		try{
			if(depttime != 0){
				DeptCal.setTimeInMillis(depttime);
				((EditText) findViewById(R.id.deptdt)).setText(dateFormat.format(new Date(depttime)));
			}
		}catch(Exception e)
		{
			Log.v("error",e.toString());
		}
	}	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case CHKIN_DATE_DIALOG_ID:
				// set date picker as current date
				if(chkInCal == null)
					chkInCal = Calendar.getInstance();				
				return new DatePickerDialog(this,chkindatePickList,chkInCal.get(Calendar.YEAR),chkInCal.get(Calendar.MONTH),chkInCal.get(Calendar.DAY_OF_MONTH));
				
			case PRDEPT_DATE_DIALOG_ID:
				// set date picker as current date
				if(preDeptCal == null)
					preDeptCal = Calendar.getInstance();				
				return new DatePickerDialog(this,predeptdatePickList,preDeptCal.get(Calendar.YEAR),preDeptCal.get(Calendar.MONTH),preDeptCal.get(Calendar.DAY_OF_MONTH));
			case DEPT_DATE_DIALOG_ID:
				// set date picker as current date
				if(DeptCal == null)
					DeptCal = Calendar.getInstance();				
				return new DatePickerDialog(this,deptdatePickList,DeptCal.get(Calendar.YEAR),DeptCal.get(Calendar.MONTH),preDeptCal.get(Calendar.DAY_OF_MONTH));
		}			
		return null;
	}
	private DatePickerDialog.OnDateSetListener chkindatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			chkInCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	private DatePickerDialog.OnDateSetListener predeptdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			preDeptCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	private DatePickerDialog.OnDateSetListener deptdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			DeptCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	public void onElementClick(View v) {
		switch(v.getId())
		{
			case R.id.proctonext:
				gotoNext(v);
				break;
			case R.id.savephase:
				saveData(v);
				break;
			case R.id.backtoprev:
				backTheprocess(v);
				break;
			case R.id.checkinicon:
				calendarOperations(v);
				break;
			case R.id.predeptdtic:
				calendarOperations(v);
				break;
			case R.id.deptdtic:
				calendarOperations(v);
				break;
			case R.id.checkindt:
				calendarOperations(v);
				break;
			case R.id.deptdt:
				calendarOperations(v);
				break;
			case R.id.predeptdt:
				calendarOperations(v);
				break;
			
		}
	}
	void backTheprocess(View v)
	{	
		if(!editJobId.equals("0"))
			saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, IrishreloLunch.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, CommonGoToActivity.class));
    	finish();
	}
	void saveData(View v)
	{
		v.setClickable(false);
		long res=0;
		
		if(valid)
		{
			ContentValues contentValues = new ContentValues();
			contentValues.put("tenant",((EditText)findViewById(R.id.tenant)).getText().toString());
			contentValues.put("occupier",((EditText)findViewById(R.id.occupier)).getText().toString());
			contentValues.put("address",((EditText)findViewById(R.id.address)).getText().toString());
			Spinner spinEle = (Spinner)findViewById(R.id.proptypedef);
			
			ArrayAdapter<String> ad = (ArrayAdapter<String>) spinEle.getAdapter();
			String spinVal = "";
			if(spinEle.getSelectedItemPosition() != 0)
				spinVal = ad.getItem(spinEle.getSelectedItemPosition());
			contentValues.put("property_type",spinVal);
			contentValues.put("no_of_bedrooms",((EditText)findViewById(R.id.numbed)).getText().toString());
			spinEle = (Spinner)findViewById(R.id.isfurnished);
			ad = (ArrayAdapter<String>) spinEle.getAdapter();
			spinVal = "";
			if(spinEle.getSelectedItemPosition() != 0)
				spinVal = ad.getItem(spinEle.getSelectedItemPosition());
			contentValues.put("isfurnished",spinEle.getSelectedItemPosition());
			String dateVal = ((EditText)findViewById(R.id.checkindt)).getText().toString();
			Calendar cal = Calendar.getInstance();
			long date_put = 0;
			if(!dateVal.equals(""))
				date_put = chkInCal.getTimeInMillis();
			contentValues.put("check_in",date_put);
			
			dateVal = ((EditText)findViewById(R.id.predeptdt)).getText().toString();
			date_put = 0;
			if(!dateVal.equals(""))
				date_put = preDeptCal.getTimeInMillis();
			contentValues.put("pre_departure",date_put);
			
			date_put = 0;
			dateVal = ((EditText)findViewById(R.id.deptdt)).getText().toString();
			if(!dateVal.equals(""))
				date_put = DeptCal.getTimeInMillis();
			contentValues.put("departure",date_put);
			
			int isOld  = settings.getInt("isOldJob", 0); 
			if(editJobId.equals("0"))
			{
				// = SelectDb.getLastAddedJobId(db)+1;
				BigInteger prevId = new BigInteger(settings.getString("lastadded", "0"),10);
				BigInteger curId = prevId.add(new BigInteger("1",10));
				editJobId = (settings.getString("deviceid", "0").equals("0") == false?settings.getString("deviceid", "0"):"")+""+(curId)+"";
				contentValues.put("added_date", cal.getTimeInMillis());
				contentValues.put("jobid",editJobId);
				InsertDb.insertTableAsusual(db, "INSPECTION_BASICS", this, contentValues);
				contentValues = new ContentValues();
				contentValues.put("jobid",editJobId);
				contentValues.put("isold",isOld);
				contentValues.put("phase","INSPECTION_BASICS");
				contentValues.put("mode","modified");
				contentValues.put("save_status","tempsaved");
				contentValues.put("last_updated_by",settings.getString("operator_email", ""));
				contentValues.put("update_time", cal.getTimeInMillis());
				res = InsertDb.insertTableAsusual(db, "JOB_LOCAL_OPERATION_PHASE_TRACK", this, contentValues);
				InsertDb.createAllRelateds(db,this, editJobId);
				if(res > 0)
				{
					((Button) findViewById(R.id.proctonext)).setVisibility(View.VISIBLE);
					editor.putString("lastadded",curId+"");
					editor.commit();
				}
				
			}else{
				res = UpdateDb.updateTableAsusual(db, "INSPECTION_BASICS", this, editJobId, contentValues);
				UpdateDb.updatePhaseStatus(db, this, editJobId, "INSPECTION_BASICS", "tempsaved", "modified");
			}	
			
			if(res > 0)
			{
				editor.putString("jobInEdit", editJobId);
				editor.commit();
				SendToServer s2sv = new SendToServer(db, this, true, "INSPECTION_BASICS", editJobId,"create/update",CommonGoToActivity.class);
				s2sv.frontEndSend();	
				v.setClickable(true);
			}
			
		}
	}
	
	
	@SuppressWarnings("deprecation")
	void calendarOperations(View v)
	{
		//clndrreqfrm
		switch(v.getId())
		{
			case R.id.checkinicon:
				datetext = (EditText) findViewById(R.id.checkindt);
				showDialog(CHKIN_DATE_DIALOG_ID);
				break;
			
			case R.id.deptdtic:
				datetext = (EditText) findViewById(R.id.deptdt);
				showDialog(DEPT_DATE_DIALOG_ID);
				break;
			
			case R.id.predeptdtic:
				datetext = (EditText) findViewById(R.id.predeptdt);
				showDialog(PRDEPT_DATE_DIALOG_ID);
				break;
					
			case R.id.checkindt:
				datetext = (EditText) findViewById(R.id.checkindt);
				showDialog(CHKIN_DATE_DIALOG_ID);
				break;
					
			case R.id.deptdt:
				datetext = (EditText) findViewById(R.id.deptdt);
				showDialog(DEPT_DATE_DIALOG_ID);
				break;
					
			case R.id.predeptdt:
				datetext = (EditText) findViewById(R.id.predeptdt);
				showDialog(PRDEPT_DATE_DIALOG_ID);
				break;
		}
	}	
	
	void showData(String jobid)
	{
		Cursor cr = SelectDb.getPhaseByData(db, "INSPECTION_BASICS", jobid, false,true,true);
		try{
			((EditText)findViewById(R.id.tenant)).setText(cr.getString(cr.getColumnIndex("tenant")));
		}catch(Exception e){
			e.getStackTrace();
		}
		
		try{
			((EditText)findViewById(R.id.occupier)).setText(cr.getString(cr.getColumnIndex("occupier")));
		}catch(Exception e){
			e.getStackTrace();
		}
		
		try{
			((EditText)findViewById(R.id.address)).setText(cr.getString(cr.getColumnIndex("address")));
		}catch(Exception e){
			e.getStackTrace();
		}
		
		try{
			((EditText)findViewById(R.id.numbed)).setText(cr.getString(cr.getColumnIndex("no_of_bedrooms")));
		}catch(Exception e){
			e.getStackTrace();
		}
		
		
		Spinner spin = (Spinner) findViewById(R.id.proptypedef);
		int pos = 0;
		try{
			if(cr.getString(cr.getColumnIndex("property_type")).equals("House"))
				pos = 1;
			else if(cr.getString(cr.getColumnIndex("property_type")).equals("Apartment"))
				pos = 2;
		}catch(Exception e){
			e.getStackTrace();
		}
		spin.setSelection(pos);
		
		
		spin = (Spinner) findViewById(R.id.isfurnished);
		pos = 0;
		try{
			if(cr.getString(cr.getColumnIndex("isfurnished")).equals("2"))
				pos = 2;
			else if(cr.getString(cr.getColumnIndex("isfurnished")).equals("1"))
				pos = 1;
		}catch(Exception e){
			e.getStackTrace();
		}
		spin.setSelection(pos);
		try{
			setCurrentDate(cr.getLong(cr.getColumnIndex("check_in")),cr.getLong(cr.getColumnIndex("pre_departure")),cr.getLong(cr.getColumnIndex("departure")));
		}catch(Exception e){
			e.getStackTrace();
		}
	}
}
