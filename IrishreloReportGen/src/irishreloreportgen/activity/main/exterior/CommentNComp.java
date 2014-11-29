package irishreloreportgen.activity.main.exterior;
import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.utilities.Utilities;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.interior.InteriorCloakRoom;
import irishreloreportgen.activity.main.serv.SendToServer;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CommentNComp extends ActionBarActivity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	DbHelper db;
	String editJobId = "0";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment_n_comp);
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals(0))
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
		startActivity(new Intent(this,ExteriorApartmentParking.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,ExteriorSignature.class));
		finish();
	}
    public void saveData(View v)
	{
    	int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
    	ContentValues contvalues = new ContentValues();
    	db.openDataBase();
    	for(int i=0; i<13; i++)
    	{
    		chkincmntId = getIdAssignedByR(this,"chkin_comment"+i);
    		chkincmpId = getIdAssignedByR(this,"chkin_complement"+i);
    		predeprtcmnt_Id = getIdAssignedByR(this,"predepart_comment"+i);
    		predeprtcmp_Id = getIdAssignedByR(this,"predeprt_complement"+i);
    		deprtcmnt_Id = getIdAssignedByR(this,"depart_comment"+i);
    		deprtcmp_Id = getIdAssignedByR(this,"depart_comment"+i);    		
    		if(i == 0)
    			db.MyDB().delete("EXTERIOR_COMMENTS", "jobid="+editJobId, null);
    		Log.v("checkinid "+i, chkincmntId+"");
    		try{
    		if(
    				((EditText)findViewById(chkincmntId)).getText().toString().equals("") && ((EditText)findViewById(chkincmpId)).getText().toString().equals("")
    				&& ((EditText)findViewById(predeprtcmnt_Id)).getText().toString().equals("") && ((EditText)findViewById(predeprtcmp_Id)).getText().toString().equals("")
    				&& ((EditText)findViewById(deprtcmnt_Id)).getText().toString().equals("") && ((EditText)findViewById(deprtcmp_Id)).getText().toString().equals("")
    			)
    		{
    			continue;
    		}
    		contvalues = new ContentValues();
    		contvalues.put("checkin_comm", ((EditText)findViewById(chkincmntId)).getText().toString());
    		contvalues.put("checkin_comp", ((EditText)findViewById(chkincmpId)).getText().toString());
    		contvalues.put("predepart_comm", ((EditText)findViewById(predeprtcmnt_Id)).getText().toString());
    		contvalues.put("predepart_comp", ((EditText)findViewById(predeprtcmp_Id)).getText().toString());
    		contvalues.put("depart_comm", ((EditText)findViewById(deprtcmnt_Id)).getText().toString());
    		contvalues.put("depart_comp", ((EditText)findViewById(deprtcmnt_Id)).getText().toString());
    		contvalues.put("jobid", editJobId);
    		db.MyDB().insert("EXTERIOR_COMMENTS", null, contvalues);
    		}catch(Exception e)
    		{
    			e.getStackTrace();
    		}
    		
    	}
    	db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "EXTERIOR_COMMENTS", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"EXTERIOR_COMMENTS", editJobId);
		
		try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "BEDROOM_4", editJobId,"create/update",InteriorCloakRoom.class);
				s2sv.frontEndSend();
			//}else{		
			}
			}catch(Exception e){
				e.getStackTrace();
			}
	}
	void renderData()
	{
		int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
    	
		Cursor cursor = SelectDb.getPhaseByData(db, "EXTERIOR_COMMENTS", editJobId, false, true, false);
		if(cursor != null)
		{
			for(int i =1; i <= cursor.getCount(); i++)
			{	
				chkincmntId = getIdAssignedByR(this,"chkin_comment"+i);
	    		chkincmpId = getIdAssignedByR(this,"chkin_complement"+i);
	    		predeprtcmnt_Id = getIdAssignedByR(this,"predepart_comment"+i);
	    		predeprtcmp_Id = getIdAssignedByR(this,"predeprt_complement"+i);
	    		deprtcmnt_Id = getIdAssignedByR(this,"depart_comment"+i);
	    		deprtcmp_Id = getIdAssignedByR(this,"depart_comment"+i);  
	    		
	    		try{
	    			((EditText)findViewById(chkincmntId)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		
	    		try{
	    			((EditText)findViewById(chkincmpId)).setText(cursor.getString(cursor.getColumnIndex("checkin_comp")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		
	    		try{
	    			((EditText)findViewById(predeprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		try{
	    			((EditText)findViewById(predeprtcmp_Id)).setText(cursor.getString(cursor.getColumnIndex("predepart_comp")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		try{
	    			((EditText)findViewById(deprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		try{
		    		((EditText)findViewById(deprtcmp_Id)).setText(cursor.getString(cursor.getColumnIndex("depart_comp")));
		    	}catch(Exception e){
	    			e.getStackTrace();
	    		}
	    		cursor.moveToNext();
			}
		}
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
	
}