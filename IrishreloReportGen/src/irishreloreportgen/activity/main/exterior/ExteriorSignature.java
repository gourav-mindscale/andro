package irishreloreportgen.activity.main.exterior;
import irishreloreportgen.activity.capturesignature.CaptureSign;
import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.utilities.CommunicationNPrecaution;
import irishreloreportgen.activity.main.utilities.Utilities;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.staticclassnconst.IrishreloAccess;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ExteriorSignature extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat, timeFormat;
	ImageView camerapic;
	DbHelper db;
	String editJobId = "0";
	final int SIGNATURE_ACTIVITY = 1, CHKIN_SIGN_DATE_DIALOG_ID = 996, PREDEPART_SIGN_DATE_DIALOG_ID = 997, DEPART_SIGN_DATE_DIALOG_ID = 998;
	Calendar checkinCal = null, predepartCal = null, departCal = null;
	long currTime;
	EditText datetext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exterior_signature);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		//setCurrentDate(new Date().getTime(),new Date().getTime(),new Date().getTime());
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		setCurrentDate(0, 0, 0);
		if(!editJobId.equals("0"))
			renderData();
		((Button) findViewById(R.id.proctonext)).setText("Finish");
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
			case R.id.proctonext:
				gotoNext(v);
				break;
			case R.id.savephase:
				saveData(v);
				break;
			case R.id.backtoprev:
				backTheprocess(v);
				break;
			case R.id.checkin_sign_icon:
				calendarOperations(v);
				break;
			case R.id.pre_departure_sign_icon:
				calendarOperations(v);
				break;
			case R.id.departure_sign_icon:
				calendarOperations(v);
				break;
			case R.id.checkin_sign_dt:
				calendarOperations(v);
				break;
			case R.id.pre_departure_sign_dt:
				calendarOperations(v);
				break;
			case R.id.departure_sign_dt:
				calendarOperations(v);
				break;
		}
	}
	
	
	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,CommentNComp.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,CommonGoToActivity.class));
		finish();
	}

	public void showImageInIV(String path,int ID)
	{
		ImageView setImgpic = (ImageView) findViewById(ID);
		Uri mUrl;
		try {
			if(path !=null && path.toString().length() !=0 )
			{
				
				File imageFile = new File(path);
				if(imageFile.exists())
				{
					mUrl= Uri.parse(path);
					setImgpic.setImageURI(mUrl);
					setImgpic.setTag(path);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private void setCurrentDate(long chkindt, long predepartdt, long departdt) {
		checkinCal = Calendar.getInstance();
		predepartCal = Calendar.getInstance();
		departCal = Calendar.getInstance();
		
		try{
			if(chkindt != 0)
			{
				checkinCal.setTimeInMillis(chkindt);
				((EditText) findViewById(R.id.checkin_sign_dt)).setText(dateFormat.format(new Date(chkindt)));
			}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		try{
			if(departdt != 0)
			{
				departCal.setTimeInMillis(departdt);
				((EditText) findViewById(R.id.departure_sign_dt)).setText(dateFormat.format(new Date(departdt)));
			}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		try{
			if(predepartdt != 0)
			{
				predepartCal.setTimeInMillis(predepartdt);
				((EditText) findViewById(R.id.pre_departure_sign_dt)).setText(dateFormat.format(new Date(predepartdt)));
			}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {			
			
			case CHKIN_SIGN_DATE_DIALOG_ID:
				if(checkinCal == null)
					checkinCal = Calendar.getInstance();				
				return new DatePickerDialog(this,chkindatePickList,checkinCal.get(Calendar.YEAR),checkinCal.get(Calendar.MONTH),checkinCal.get(Calendar.DAY_OF_MONTH));
			case PREDEPART_SIGN_DATE_DIALOG_ID:
				if(predepartCal == null)
					predepartCal = Calendar.getInstance();				
				return new DatePickerDialog(this,predepartdatePickList,predepartCal.get(Calendar.YEAR),predepartCal.get(Calendar.MONTH),predepartCal.get(Calendar.DAY_OF_MONTH));
			case DEPART_SIGN_DATE_DIALOG_ID:
				if(departCal == null)
					departCal = Calendar.getInstance();				
				return new DatePickerDialog(this,departdatePickList,departCal.get(Calendar.YEAR),departCal.get(Calendar.MONTH),departCal.get(Calendar.DAY_OF_MONTH));
			
		}			
		return null;
	}
	private DatePickerDialog.OnDateSetListener chkindatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			checkinCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	private DatePickerDialog.OnDateSetListener predepartdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			predepartCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	
	
	private DatePickerDialog.OnDateSetListener departdatePickList = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			Date datesel = new Date(selectedYear-1900, selectedMonth, selectedDay);
			departCal.setTimeInMillis(datesel.getTime());
			datetext.setText(dateFormat.format(datesel));
		}
	};
	public void saveData(View v)
	{
		String dateVal = "";
		Date date;
		long date_put = 0;
		
		long Success = -1;
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();
		contentValues = new ContentValues();
		try{
			contentValues.put("checkin_signimp_name", ((EditText)findViewById(R.id.exterior_occupier_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "occ checkin_signimp_name");
		}
		try{
			contentValues.put("checkin_signimp", ((ImageView)findViewById(R.id.exterior_occupier_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "occ checkin_signimp");
		}
		
		try{
			contentValues.put("predepart_signimp_name", ((EditText)findViewById(R.id.exterior_pre_departure_occupier_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "occ predepart_signimp_name");
		}
		
		
		try{
			contentValues.put("predepart_signimp",((ImageView)findViewById(R.id.exterior_pre_departure_occupier_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "patio predepart_signimp");
		}
		
		try{
			contentValues.put("depart_signimp_name", ((EditText)findViewById(R.id.exterior_departure_occupier_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "occ depart_signimp_name");
		}
		
		
		try{
			contentValues.put("depart_signimp",((ImageView)findViewById(R.id.exterior_departure_occupier_sign_imageView)).getTag().toString());//ground_departure_patioDeckBalcony_imageView
		}catch(Exception e){
			Log.v("for", "occ depart_signimp");
		}
		try{
			contentValues.put("depart_signimp_name", ((EditText)findViewById(R.id.exterior_departure_occupier_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "occ depart_signimp_name");
		}
		
		
		dateVal = ((EditText)findViewById(R.id.checkin_sign_dt)).getText().toString();
		date_put = 0;
		if(!dateVal.equals(""))
		{
			date = new Date() ;
			
				try {
					date = (Date) dateFormat.parse(dateVal);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			date_put = date.getTime();
		}
		
		try{
			contentValues.put("sign_date",date_put);
		}catch(Exception e){
			Log.v("for", "predepart checkin_signdt");
		}
		
		Success = db.MyDB().update("SIGNED_BY", contentValues, "jobid ="+editJobId+" AND conserned_type = 'occupier'", null);
		
		contentValues = new ContentValues();
		try{
			contentValues.put("checkin_signimp_name", ((EditText)findViewById(R.id.exterior_landlord_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "landlord checkin_signimp_name");
		}
		try{
			contentValues.put("checkin_signimp", ((ImageView)findViewById(R.id.exterior_landlord_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "landlord checkin_signimp");
		}
		
		try{
			contentValues.put("predepart_signimp_name", ((EditText)findViewById(R.id.exterior_pre_departure_landlord_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "landlord predepart_signimp_name");
		}
		
		
		try{
			contentValues.put("predepart_signimp",((ImageView)findViewById(R.id.exterior_pre_departure_landlord_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "landlord predepart_signimp");
		}
		
		try{
			contentValues.put("depart_signimp_name", ((EditText)findViewById(R.id.exterior_departure_landlord_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "landlord depart_signimp_name");
		}
		
		
		try{
			contentValues.put("depart_signimp",((ImageView)findViewById(R.id.exterior_departure_landlord_sign_imageView)).getTag().toString());//ground_departure_patioDeckBalcony_imageView
		}catch(Exception e){
			Log.v("for", "landlord depart_signimp");
		}
		
		
		dateVal = ((EditText)findViewById(R.id.pre_departure_sign_dt)).getText().toString();
		date_put = 0;
		if(!dateVal.equals(""))
		{
			date = new Date() ;
		
			try {
				date = (Date) dateFormat.parse(dateVal);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			date_put = date.getTime();
		}
		
		try{
			contentValues.put("sign_date",date_put);
		}catch(Exception e){
			Log.v("for", "predepart depart_signdt");
		}
		
		Success = db.MyDB().update("SIGNED_BY", contentValues, "jobid ="+editJobId+" AND conserned_type = 'landlord'", null);
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_signimp_name", ((EditText)findViewById(R.id.exterior_irs_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "irs checkin_signimp_name");
		}
		try{
			contentValues.put("checkin_signimp", ((ImageView)findViewById(R.id.exterior_irs_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "irs checkin_signimp");
		}
		
		try{
			contentValues.put("predepart_signimp_name", ((EditText)findViewById(R.id.exterior_pre_departure_irs_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "irs predepart_signimp_name");
		}
		
		
		try{
			contentValues.put("predepart_signimp",((ImageView)findViewById(R.id.exterior_pre_departure_irs_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "irs predepart_signimp");
		}
		
		try{
			contentValues.put("depart_signimp_name", ((EditText)findViewById(R.id.exterior_departure_irs_sign_editText)).getText().toString());
		}catch(Exception e){
			Log.v("for", "occ depart_signimp_name");
		}
		
		
		try{
			contentValues.put("depart_signimp",((ImageView)findViewById(R.id.exterior_departure_irs_sign_imageView)).getTag().toString());
		}catch(Exception e){
			Log.v("for", "irs depart_signimp");
		}
		
		
		dateVal = ((EditText)findViewById(R.id.departure_sign_dt)).getText().toString();
		date_put = 0;
		if(!dateVal.equals(""))
		{
			date = new Date() ;
			
				try {
					date = (Date) dateFormat.parse(dateVal);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			date_put = date.getTime();
		}
		
		try{
			contentValues.put("sign_date",date_put);
		}catch(Exception e){
			Log.v("for", "depart depart_signdt");
		}
		Success = db.MyDB().update("SIGNED_BY", contentValues, "jobid ="+editJobId+" AND conserned_type = 'irs'", null);
		
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "SIGNED_BY", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"SIGNED_BY", editJobId);
		
		try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "SIGNED_BY", editJobId,"create/update",ExteriorApartmentParking.class);
				s2sv.frontEndSend();
			//}else{		
			}
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "SIGNED_BY", editJobId, false, true, false);
		long checkindt = 0,predepartdt = 0,departdt =0; 
		
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
			    String conserned_type = cursor.getString(cursor.getColumnIndex("conserned_type"));
				chkincmntId = getIdAssignedByR(this,"exterior_"+conserned_type+"_sign_editText");
				chkincmpId = getIdAssignedByR(this,"exterior_"+conserned_type+"_sign_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"exterior_pre_departure_"+conserned_type+"_sign_editText");
				predeprtcmp_Id = getIdAssignedByR(this,"exterior_pre_departure_"+conserned_type+"_sign_imageView");
				deprtcmnt_Id = getIdAssignedByR(this,"exterior_departure_"+conserned_type+"_sign_editText");
				deprtcmp_Id = getIdAssignedByR(this,"exterior_departure_"+conserned_type+"_sign_imageView");  	
				try{
					((EditText)findViewById(chkincmntId)).setText(cursor.getString(cursor.getColumnIndex("checkin_signimp_name")));
				}catch(Exception e){
					e.getStackTrace();
				}
				try{
					((EditText)findViewById(predeprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("predepart_signimp_name")));
				}catch(Exception e){
					e.getStackTrace();
				}
				try{
					((EditText)findViewById(deprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("depart_signimp_name")));
				}catch(Exception e){
					e.getStackTrace();
				}
				
				showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_signimp")),chkincmpId);
				showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_signimp")),predeprtcmp_Id);
				showImageInIV(cursor.getString(cursor.getColumnIndex("depart_signimp")),deprtcmp_Id);
				switch(j)
				{
					case 0:
						checkindt = cursor.getLong(cursor.getColumnIndex("sign_date"));
						break;
					case 1:
						predepartdt = cursor.getLong(cursor.getColumnIndex("sign_date"));
						break;
					case 2:
						departdt = cursor.getLong(cursor.getColumnIndex("sign_date"));
						break;
				}
				
				cursor.moveToNext();
			}
			
			try{
				setCurrentDate(checkindt,predepartdt,departdt);
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	
	public void getSign(View v)
	{
		camerapic = (ImageView) v;
		Intent intent = new Intent(this, CaptureSign.class); 
        startActivityForResult(intent,SIGNATURE_ACTIVITY);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!IrishreloAccess.imagePath.equals(""))
		{
			showImage(IrishreloAccess.imagePath);
		}
	}
	
	public void showImage(String path)
	{
		try {
			Bitmap bmImg = null;
			camerapic.setImageBitmap(null);
			//camerapic.setTag(path);
			File imageFile = new File(path);
			if(imageFile.exists())
			{
				bmImg = IrishreloAccess.getResizedImage(imageFile);
				camerapic.setImageBitmap(bmImg);
				camerapic.setTag(imageFile.getAbsolutePath());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		//Toast.makeText(this, "in result", Toast.LENGTH_LONG).show();
		try {		
	       
			 if(requestCode == SIGNATURE_ACTIVITY && resultCode == RESULT_OK) 
			 {
	            Bundle bundle = data.getExtras();
	            String status  = bundle.getString("status");
	            if(status.equalsIgnoreCase("done")){
	            	//Toast.makeText(getApplicationContext(), IrishreloAccess.imagePath+"res", Toast.LENGTH_LONG).show();
	            	//camerapic = (ImageView) findViewById(R.id.showsignature);
	            	//showImage(IrishreloAccess.imagePath);
	            	//camerapic.setTag(IrishreloAccess.imagePath+"");
	            }
			 }
	            
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getRealPathFromURI(Uri contentUri) {
        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                        proj, // Which columns to return
                        null,       // WHERE clause; which rows to return (all rows)
                        null,       // WHERE clause selection arguments (none)
                        null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
	}	
	public int getIdAssignedByR(Context pContext, String pIdString)
	{
	    // Get the Context's Resources and Package Name
	    Resources resources = pContext.getResources();
	    String packageName  = pContext.getPackageName();

	    // Determine the result and return it
	    int result = resources.getIdentifier(pIdString, "id", packageName);
	    return result;
	} 
	void calendarOperations(View v)
	{
		//clndrreqfrm
		switch(v.getId())
		{
			case R.id.checkin_sign_dt:
				datetext = (EditText) findViewById(R.id.checkin_sign_dt);
				showDialog(CHKIN_SIGN_DATE_DIALOG_ID);
				break;
			
			case R.id.departure_sign_dt:
				datetext = (EditText) findViewById(R.id.departure_sign_dt);
				showDialog(DEPART_SIGN_DATE_DIALOG_ID);
				break;
			
			case R.id.pre_departure_sign_dt:
				datetext = (EditText) findViewById(R.id.pre_departure_sign_dt);
				showDialog(PREDEPART_SIGN_DATE_DIALOG_ID);
				break;
					
			case R.id.checkin_sign_icon:
				datetext = (EditText) findViewById(R.id.checkin_sign_dt);
				showDialog(CHKIN_SIGN_DATE_DIALOG_ID);
				break;
					
			case R.id.departure_sign_icon:
				datetext = (EditText) findViewById(R.id.departure_sign_dt);
				showDialog(DEPART_SIGN_DATE_DIALOG_ID);
				break;
					
			case R.id.pre_departure_sign_icon:
				datetext = (EditText) findViewById(R.id.pre_departure_sign_dt);
				showDialog(PREDEPART_SIGN_DATE_DIALOG_ID);
				break;
		}
	}
}