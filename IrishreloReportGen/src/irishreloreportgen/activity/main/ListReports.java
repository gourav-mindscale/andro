package irishreloreportgen.activity.main;
import java.util.Date;
import java.text.SimpleDateFormat;

import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.serv.FetchFromServer;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.staticclassnconst.InspectionBasics;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class ListReports extends ActionBarActivity {
	ListView lv;
	DbHelper db;
	SimpleDateFormat dateFormat;
	InspectionBasics[] replist; 
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	int currPage = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_reports);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		db = new DbHelper(this);
		settings = getSharedPreferences(getString(R.string.app_name), 0);
		editor = settings.edit();
		
		
		db.openDataBase();
		Cursor countcr =  db.MyDB().rawQuery("SELECT * FROM INSPECTION_BASICS group by jobid", null);
		int numJobPerPage = 10;
		int total = countcr.getCount();
		db.close();
		//Log.v("total record",""+total);
		int totalpg =(int) Math.ceil((float)total/(float)numJobPerPage);
		//Log.v("total page",""+totalpg);
		currPage = this.getIntent().getIntExtra("page", 0);
		int offset = (currPage*numJobPerPage);
		
		if(currPage == totalpg-1)
		{
			((Button) findViewById(R.id.proctonext)).setVisibility(View.INVISIBLE);;
		}
		if(currPage == 0)
			((Button) findViewById(R.id.backtoprevpage)).setVisibility(View.INVISIBLE);;
		
		setListUi(total,offset,numJobPerPage);
		editor.remove("jobInEdit");
		editor.remove("isOldJob");
		editor.putString("jobInEdit","0");
		editor.putInt("isOldJob",0);		
		editor.commit();
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
		switch(id)
		{
			case  R.id.action_exitreport:
				startActivity(new Intent(this, IrishreloLunch.class));
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	void setListUi(int ct,int offset,int Limit)
	{
		lv = (ListView) findViewById(R.id.listreport);
		Cursor fetched = SelectDb.getdatapagination(db, offset, Limit);
		replist = null;
		int count = 0;
		try{		
			if(fetched != null)
			{
				replist = new InspectionBasics[fetched.getCount()];
				for(count = 0; count < fetched.getCount(); count++)
				{	
					replist[count] = new InspectionBasics(fetched.getString(fetched.getColumnIndex("jobid")),fetched.getInt(fetched.getColumnIndex("isold")),new Date(fetched.getInt(fetched.getColumnIndex("added_date"))),fetched.getLong(fetched.getColumnIndex("added_date")),fetched.getString(fetched.getColumnIndex("tenant")),fetched.getString(fetched.getColumnIndex("occupier")),fetched.getString(fetched.getColumnIndex("address")),fetched.getString(fetched.getColumnIndex("save_status")));
					fetched.moveToNext();
				}
				lv.setAdapter(new ListReportAdapter(this,R.layout.listreportitem,replist));
			}
		}catch(Exception e){
			Log.e("error", e.toString());
		}		
	}	
	class ListReportAdapter extends ArrayAdapter<InspectionBasics>{
		Context context; 
		public ListReportAdapter(Context context, int resource, InspectionBasics[] objects) {
			super(context, resource, objects);
			this.context = context; 
			// TODO Auto-generated constructor stub		
		}
		public View getView(int position, View convertView, ViewGroup parent)
		{			
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.listreportitem, parent, false);			
			TextView tv = (TextView) rowView.findViewById(R.id.reporttitle);
			tv.setText(replist[position].tenant);
		    tv = (TextView) rowView.findViewById(R.id.reportdt);
		    tv.setText(dateFormat.format(new Date(replist[position].reportdtlong)));//date is not got properly
		    tv = (TextView) rowView.findViewById(R.id.reportid);
		    tv.setText("Job id - "+replist[position].jobId);
		    tv = (TextView) rowView.findViewById(R.id.occupier);
		    tv.setText("Occupier-\t"+replist[position].occupier);
		    tv = (TextView) rowView.findViewById(R.id.address);
		    tv.setText("Address-\t"+replist[position].address);
		    Button synch = (Button) rowView.findViewById(R.id.synchtosvr);
		    synch.setTag(replist[position].jobId+"");
		    Boolean allreadyin = SelectDb.getJobsIfToSend(db, replist[position].jobId);
		    if(allreadyin)
		    {
		    	synch.setVisibility(View.VISIBLE);
		    }
		    synch.setOnClickListener(new OnClickListener(){
		    	@Override
				public void onClick(View v) {
		    		if(isOnline())
					{
		    			((Button)v).setText("Sending..");
		    			IrishreloAccess.synchbtn = (Button) v;
		    			IrishreloAccess.editbtnonsynch = (LinearLayout)((ViewGroup)v.getParent()).findViewById(R.id.editbtn);
		    			IrishreloAccess.synchbtn.setClickable(false);
		    			IrishreloAccess.editbtnonsynch.setClickable(false);
		    			SendToServer s2s = new SendToServer(db,ListReports.this,true,"sendall",v.getTag().toString(), "fetchrelated",ListReports.class);
		    			s2s.SynchWithServer();
					}else{
						Toast.makeText(ListReports.this, "plaese check your internet connection!", Toast.LENGTH_LONG).show();
					}
		    	}
		    });
			   
		    
		    LinearLayout edit = (LinearLayout)rowView.findViewById(R.id.editbtn);
		    edit.setTag(replist[position].jobId+replist[position].savestatus);
		    if(replist[position].savestatus.contains("tempsave") || allreadyin)
			{
		    edit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Boolean gotoNext = false;
					String firstString = v.getTag().toString().replaceFirst(".*?(\\d+).*", "$1");
					
					editor.putString("jobInEdit", firstString);
					editor.commit();
					gotoNext = true;
					
					if(gotoNext == true)
					{
						ListReports.this.startActivity(new Intent(ListReports.this,ReportBasicDetails.class));
					}
				}		    	
		    });
		}else{
			edit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String firstString = v.getTag().toString().replaceFirst(".*?(\\d+).*", "$1");
					if(isOnline())
					{
						editor.putString("jobInEdit", firstString);
						editor.commit();
						FetchFromServer fs =  new FetchFromServer(db,context,false, "FETCHALLREL", firstString, "fetchrelateds", ReportBasicDetails.class);
						fs.downloadAllData();
					}else{
						Toast.makeText(ListReports.this,"Server can not be connected now!!",Toast.LENGTH_LONG).show();
					}
					/*
					{
						ListReports.this.startActivity(new Intent(ListReports.this,ReportBasicDetails.class));
					}*/
					
				}		    	
		    });
		}
		    
		    Boolean iswaitingres = SelectDb.getJobsWaitingRes(db, replist[position].jobId);
		    if(iswaitingres)
		    {
		    	synch.setVisibility(View.VISIBLE);
		    	synch.setText("Sending..");
		    	synch.setClickable(false);
		    	edit.setClickable(false);
		    }
		    
		    if(position%2 == 0)
		    	((RelativeLayout)rowView.findViewById(R.id.mainwraper)).setBackgroundColor(Color.rgb(242, 242, 242));
		    else
		    	((RelativeLayout)rowView.findViewById(R.id.mainwraper)).setBackgroundColor(Color.rgb(220, 220, 220));
		   if(position == replist.length-1)
		   {
			   
		   }
		   return rowView;
		}
	}
	Boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf = cm.getActiveNetworkInfo();
		if(nf != null && nf.isConnectedOrConnecting())
		{
			return true;
		}
		return false;		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		editor.remove("jobInEdit");
		editor.remove("isOldJob");
		editor.putString("jobInEdit","0");
		editor.putInt("isOldJob",0);			
		editor.commit();
		IrishreloAccess.activityResumed();
	}

	@Override
	protected void onPause() {
	  super.onPause();
	  IrishreloAccess.activityPaused();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		IrishreloAccess.activityPaused();
	}
	
	public void onElementClick(View v)
	{
		switch(v.getId())
		{
			case R.id.backtoprev:
				startActivity(new Intent(this, IrishreloLunch.class));
				finish();
				break;
			case R.id.backtoprevpage:
				Intent itt=new Intent(this, ListReports.class);
				itt.putExtra("page",currPage-1);
				startActivity(itt);
				finish();
				break;
			case R.id.proctonext:
				Intent its=new Intent(this, ListReports.class);
				its.putExtra("page",currPage+1);
				startActivity(its);
				finish();
				break;
		}
	}
}