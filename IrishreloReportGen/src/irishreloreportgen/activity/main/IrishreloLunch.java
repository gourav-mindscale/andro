package irishreloreportgen.activity.main;

import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.serv.FetchFromServer;
import irishreloreportgen.staticclassnconst.IrishreloAccess;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
public class IrishreloLunch extends ActionBarActivity {
	Boolean preventElemAcc = false;
	DbHelper db;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rishrelo_lunch);
		
		db=new DbHelper(this);
        try {
			db.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
        SelectDb.getAllStatus(db);
        String path = "";
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			path = Environment.getExternalStorageDirectory().getPath()
					+ "/irishrelo/" ;
			
		} else {
			path = Environment.getRootDirectory().getPath()
					+ "/irishrelo/";
		}
        File wallpaperDirectory = new File(path);
		// have the object build the directory structure, if needed.
		wallpaperDirectory.mkdirs();
		db.close();	
		settings = getSharedPreferences(getString(R.string.app_name), 0);
		editor = settings.edit();
		editor.remove("jobInEdit");
		editor.remove("isOldJob");
		editor.putString("jobInEdit","0");
		editor.putInt("isOldJob",0);		
		editor.commit();
		onInstall();
		//, predepart_img=''
		/*db.openDataBase();
		db.MyDB().execSQL("update KITCHEN set depart_img ='' where jobid = 1322811123428024 and id<12");
		db.close();*/
	}
	@Override
	protected void onResume() {
	  super.onResume();
	  IrishreloAccess.activityResumed();
		editor.remove("jobInEdit");
		editor.remove("isOldJob");
	  	editor.putString("jobInEdit","0");
		editor.putInt("isOldJob",0);		
		editor.commit();
	}

	public void btntxtonClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.addnewrpt :
				startActivity(new Intent(this, ReportBasicDetails.class));
				finish();
				break;
			case R.id.listrpt :
				v.setClickable(false);
				FetchFromServer ffsv= new FetchFromServer(db, this, true, "INSPECTION_BASICS" , "0", "fetchall", ListReports.class);
				ffsv.sendReuest();
				//startActivity(new Intent(this, ListReports.class));
				
				break;
			case R.id.exit :
				finish();
				break;
			case R.id.cleardata :
				UpdateDb.clearData(db, this);
				finish();
				break;
		}
	}
	void onInstall()
	{
		try{
		if(settings.getString("deviceid", "0").equals("0"))
		{
			WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = manager.getConnectionInfo();
			String address = info.getMacAddress();
			
			address = address.replace("-", "");
			address = address.replace(":", "");
			address = address.replace(".", "");
			address = address.replace(address.substring(0, 6),"");
			BigInteger bi = new BigInteger(address, 16);
			editor.putString("deviceid", bi+"");
		}
		}catch(Exception e){
			e.getStackTrace();
			
		}
		
		
		if(settings.getString("lastadded", "0").equals("0"))
		{
			editor.putString("lastadded", (new BigInteger(((new Date()).getTime()/60000)+"",10))+"");
		}
		
		if(!settings.getBoolean("installed", false))
		{
			//if((new IrishreloAccess()).isOnline())
			new FetchFromServer(db, this, false, "INSPECTION_BASICS" , "0", "fetchall",null).sendReuest();
			editor.putBoolean("installed", true);
		}
		editor.commit();
	}
}
