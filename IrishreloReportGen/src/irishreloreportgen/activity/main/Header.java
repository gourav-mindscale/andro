package irishreloreportgen.activity.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.InsertDb;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.activity.main.serv.FetchFromServer.HttpAsyncTask;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import PopUpDialog.AltertEmailPopUp;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Header extends RelativeLayout{
	private View row; 
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	DbHelper db;
	String editJobId = "0";
	private Context mContext;
	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);	
		this.mContext = context;
		db = new DbHelper(mContext);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		row = null;
		row = inflater.inflate(R.layout.header, null);
		addView(row);
		settings = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
		{
			Cursor checkIsold = SelectDb.getstatusNmodeByPhase(db, "INSPECTION_BASICS", editJobId);
			try{
				if(checkIsold.getInt(2) == 1)
					((Button) findViewById(R.id.sendemail)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.sendemail)).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
//						AltertEmailPopUp altermailpopUp=new AltertEmailPopUp(mContext);
//						altermailpopUp.show();
						
	//......code for popup Alternet Email....////////////////////////////////////////////////////////////////////////////////////////			
						LayoutInflater li=LayoutInflater.from(mContext);
						View popupview=li.inflate(R.layout.alternativ_email_popup, null);
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								mContext);
		 
						// set prompts.xml to alertdialog builder
						alertDialogBuilder.setView(popupview);
		 
						final EditText userInput = (EditText) popupview
								.findViewById(R.id.et_alternet_email_popup);
						// set dialog message
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							  new DialogInterface.OnClickListener() {
							    public void onClick(DialogInterface dialog,int id) {
								// get user input and set it to result
								// edit text
								//result.setText(userInput.getText());
							    	Toast.makeText(mContext, userInput.getText().toString(), Toast.LENGTH_LONG).show();
							    	new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"fetch_data_generate_report_send_mail");
							    }
							  })
							.setNegativeButton("Cancel",
							  new DialogInterface.OnClickListener() {
							    public void onClick(DialogInterface dialog,int id) {
							    	new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"fetch_data_generate_report_send_mail");
								dialog.cancel();
							    }
							  });
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						
						
				//		new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"fetch_data_generate_report_send_mail");
					}
				});
				((Button) findViewById(R.id.synchtoserver)).setVisibility(View.VISIBLE);
				Boolean iswaitingres = SelectDb.getJobsWaitingRes(db, editJobId);
				if(iswaitingres)
			    {
					((Button) findViewById(R.id.synchtoserver)).setText("Sending..");
					((Button) findViewById(R.id.synchtoserver)).setClickable(false);
			    }
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		
		
		((Button) findViewById(R.id.synchtoserver)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editJobId = settings.getString("jobInEdit", "0");
				Toast.makeText(mContext, "jobid"+editJobId, Toast.LENGTH_LONG).show();
				if(!editJobId.equals("0"))
				{
					if(isOnline())
					{
						((Button)v).setVisibility(View.INVISIBLE);
						((Button)v).setText("Sending..");
						((Button)v).setVisibility(View.VISIBLE);
		    			IrishreloAccess.synchbtn = (Button) v;
		    			IrishreloAccess.synchbtn.setClickable(false);
		    			IrishreloAccess.synchfromheader = true;
		    			SendToServer s2s = new SendToServer(db,mContext,true,"sendall",editJobId, "insert/update",((Activity)mContext).getClass());
		    			s2s.SynchWithServer();
					}else{
						Toast.makeText(mContext, "plaese check your internet connection!", Toast.LENGTH_LONG).show();
					}
				}
			}
			
		});
		
	}
	public String sendMail(String url)
	{
		HttpClient httpclient = new DefaultHttpClient();
		InputStream inputStream = null;
		String result = "";
		if(isOnline())
		{	
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			try{
				jsonObject.accumulate("jobid",editJobId);	
				jsonObject.accumulate("request","mailsend");
			}catch(Exception e){
				e.getStackTrace();
			}
			String json = jsonObject.toString();
			StringEntity se = null;
			try{
				 se = new StringEntity(json);
				// 6. set httpPost Entity
				httpPost.setEntity(se);
				// 7. Set some headers to inform server about the type of the content   
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/json");
			    // 8. Execute POST request to the given URL
			    
			    HttpResponse httpResponse = httpclient.execute(httpPost);
			    // 9. receive response as inputStream
			    inputStream = httpResponse.getEntity().getContent();
			    // 10. convert inputstream to string
			    if(inputStream != null)
			    	result = convertInputStreamToString(inputStream);
			    else
			    	result = "Did not work!";
			}catch(Exception e){
				e.getStackTrace();
			}
			
		}
		return result;
	}
	Boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf = cm.getActiveNetworkInfo();
		if(nf != null && nf.isConnectedOrConnecting())
		{
			return true;
		}
		return false;		
	}
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
		result += line; 
		inputStream.close();
		return result; 
	}   
	
	
	 public class HttpAsyncTask extends AsyncTask<String, Void, String> {
		 	ProgressDialog dialog;
		 	protected void onPreExecute() {
				 dialog=new ProgressDialog(mContext);
				 dialog.setMessage("Please Wait!!");
			     dialog.setCancelable(false);
			     dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);			     
			     dialog.show();
			 }
		 	
	        @Override
	        protected String doInBackground(String... urls) {
	        	return sendMail(urls[0]);
	    	}
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	        	dialog.dismiss();
	        	try{
	        		IrishreloAccess.write("mailres", result);
	        		JSONObject jObject = null;
	        		jObject = new JSONObject(result);
	        		if(Boolean.parseBoolean(jObject.get("sent").toString()))
	        			Toast.makeText(mContext, "Mail has sent for job id: "+jObject.get("jobid").toString(), Toast.LENGTH_LONG).show();
	        	}catch(Exception e){
	        		Toast.makeText(mContext, "Mail could not sent. ", Toast.LENGTH_LONG).show();
	        	}
	        	
	       }
	    }
}
