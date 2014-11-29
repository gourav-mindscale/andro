package irishreloreportgen.activity.main.serv;

import irishreloreportgen.activity.main.Decompress;
import irishreloreportgen.activity.main.ListReports;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.ReportBasicDetails;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.InsertDb;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.staticclassnconst.IrishreloAccess;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.audiofx.BassBoost.Settings;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class FetchFromServer {
	static Boolean FETCH_REQ_SENT = false; 
	Boolean isFront = false;
	String phase = "";
	Context  clscontext;
	DbHelper db;
	String currJob = "0";
	String procceNphase="";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	ProgressDialog dialog;
	String path ="";
	Class open = null;
	public FetchFromServer(DbHelper db,Context ctx, Boolean isFront, String phase,String currJob, String procceNphase, Class open){
		this.clscontext = ctx;
		this.isFront = isFront;
		this.phase = phase;
		this.db = db;
		this.currJob = currJob;
		this.procceNphase = procceNphase;
		settings = ctx.getSharedPreferences(ctx.getString(R.string.app_name), 0);
		editor = settings.edit();
		path =  Environment.getExternalStorageDirectory() +"/irishrelo/";
		this.open = open;
		if (!android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			
			path = Environment.getRootDirectory().getPath()
					 +"/irishrelo/";
		}
	}	
	public String makeJshon()
	{
		String json = "";
		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.accumulate("preccessNphase",procceNphase);
			jsonObject.accumulate("phase",phase);
			jsonObject.accumulate("jobid",currJob);			
		}catch(Exception e){
			Log.e("error",e.toString()+"");
		}
		// 4. convert JSONObject to JSON to String
	    if(phase.equals("FETCHALLREL"))
	    {
	    	jsonObject = new JSONObject();
			try
			{
				jsonObject.accumulate("jobid",currJob);	
				jsonObject.accumulate("request","fetch");			
			}catch(Exception e){
				Log.e("error",e.toString()+"");
			}
	    }
		json = jsonObject.toString();
		return json;
	}
	
	
	public String REQUEST_DATA(String url){
		FETCH_REQ_SENT = true;
		InputStream inputStream = null;
		String result = "";
		try {
		// 1. create HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 2. make POST request to the given URL
		HttpPost httpPost = new HttpPost(url);		
	    // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
	    //not required
		// 5. set json to StringEntity
		StringEntity se = new StringEntity(makeJshon());
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
	     } catch (Exception e) {
	         Log.d("InputStream", e.getLocalizedMessage());
	     }		
		// 11. return result
	    return result;
	}
	
	 public String sendReuest()
	 {
		 if(!FETCH_REQ_SENT){
			 new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"getReports");
		 }
		return currJob;
	 }
	 
	 public void downloadAllData()
	 {
		 new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"fetch_data_generate_report_send_mail");
		 new HttpAsyncFileDwnLd().execute(IrishreloAccess.SERVER+"assets/irishrelo/"+currJob+".zip");
	 }
	
	 public class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
			
			 protected void onPreExecute() {
				 try{
					 dialog=new ProgressDialog(clscontext);
					 dialog.setMessage("Please Wait!!");
				     dialog.setCancelable(false);
				     dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				     dialog.show();
				 }catch(Exception e){
					 e.getStackTrace();
				 }
			 }
		 
	        @Override
	        protected String doInBackground(String... urls) {
	        	return REQUEST_DATA(urls[0]);
	    	}
	        // onPostExecute displays the results of the AsyncTask.
	        @SuppressLint("NewApi")
			@Override
	        protected void onPostExecute(String result) {
	        	Log.v("res tag",result);
	        	if(phase.equals("FETCHALLREL"))
	        	{
	        		//Log.v("res tag",result);
	        		try{
		        		JSONObject jObject = null;
		        		jObject = new JSONObject(result);
		        		String currji = jObject.get("jobid")+"";
		        		ContentValues values = new ContentValues();
		        	//	String[] allTables = {"INSPECTION_BASICS","POWER_UTILITIES","COMMUNICATION_N_PRECAUTIONS","EXTERIOR_GROUND","IF_APPLICABLE","APARTMENT_PARKING","EXTERIOR_COMMENTS","SIGNED_BY","HALL_OR_LAND","WC_OR_CLOAKROOM","LIVING_ROOM","DINING_ROOM","KITCHEN","UTILITY_ROOM","MASTER_BED_ROOM","LIVING_ROOM","MASTER_BED_ROOM","EN_SUITE_BATHROOM","BEDROOM_2","MAIN_BATHROOM","BEDROOM_3","BEDROOM_4"};
		        		
		        		
//CODED BY JOY
		        		String[] allTables = {"INSPECTION_BASICS","POWER_UTILITIES","COMMUNICATION_N_PRECAUTIONS","EXTERIOR_GROUND",
		        				              "IF_APPLICABLE","APARTMENT_PARKING","EXTERIOR_COMMENTS","SIGNED_BY","HALL_OR_LAND",
		        				              "WC_OR_CLOAKROOM","LIVING_ROOM","DINING_ROOM","KITCHEN","UTILITY_ROOM","MASTER_BED_ROOM",
		        				              "LIVING_ROOM","MASTER_BED_ROOM","EN_SUITE_BATHROOM","BEDROOM_2","MAIN_BATHROOM","BEDROOM_3",
		        				               "BEDROOM_4","BEDROOM_5","ROOM_ONE","ROOM_TWO","ROOM_THREE","ROOM_FOUR","ROOM_FIVE",
		        				               "EN_SUITE_ONE","EN_SUITE_TWO","EN_SUITE_THREE","EN_SUITE_FOUR","EN_SUITE_FIVE","MPRN_GPRN"};
//-------
		        		for(int j=0; j < allTables.length; j++)
		        		{
		        			
		        			try{
		        			JSONArray jsonDataArray = jObject.getJSONArray(allTables[j].toLowerCase());
		        			if(!(allTables[j].equals("INSPECTION_BASICS") || allTables[j].equals("COMMUNICATION_N_PRECAUTIONS")) && jsonDataArray.length() != 0)
		        			{
		        				db.openDataBase();
		        				db.MyDB().delete(allTables[j], "jobid="+currji, null);
		        				db.close();
		        			}
		        			//Log.v("row", allTables[j]+""+jsonDataArray.length());
			        		for(int i= 0; i < jsonDataArray.length(); i++)
			        		{
			        			values = new ContentValues();
			        			JSONObject each = (JSONObject) jsonDataArray.get(i);
			        			Iterator it = each.keys();
			        			while(it.hasNext()) {
			        				String key = it.next()+"";
			        				//Log.v("if",each.get(key).toString());
			        				if(each.get(key) != null && !each.get(key).toString().contains("null") && !each.get(key).toString().isEmpty())
			        				{
			        					if((key.contains("imp") || key.contains("img")) && !key.contains("name"))
			        					{
			        						values.put(key, path+each.get(key)+"");
			        					Log.v("if","exec");
			        					}else{
			        						values.put(key, each.get(key)+"");
			        					}
			        				}else{
			        					
			        				}
			        			}
			        			if(jsonDataArray.length() != 0)
		        				{
				        			if(allTables[j].equals("INSPECTION_BASICS") || allTables[j].equals("COMMUNICATION_N_PRECAUTIONS"))
				        			{
			        					if(UpdateDb.updateTableAsusual(db, allTables[j], clscontext, currji, values) <= 0)
				        					InsertDb.insertTableAsusual(db, allTables[j], clscontext, values);
				        			}else{
				        				InsertDb.insertTableAsusual(db, allTables[j], clscontext, values);
				        			}
		        				}	
		        				if(UpdateDb.updatePhaseStatus(db, clscontext, currji , allTables[j], "saved", "read") <= 0)
		        				{
		        					values = new ContentValues();
		        					values.put("jobid",currji);
		        					values.put("isold",1);
		        					values.put("phase",allTables[j]);
		        					values.put("mode","read");
		        					values.put("save_status","saved");
		        					InsertDb.insertTableAsusual(db, "JOB_LOCAL_OPERATION_PHASE_TRACK", clscontext, values);
		        				}
			        		}	
		        			}catch(Exception e){
		        				e.getStackTrace();
		        			}
		        		}
		        	}catch(Exception e){
		        		Log.e("Error: ",e.getLocalizedMessage());
		        	}
	        	}else{
	        	try{
	        		JSONObject jObject = null;
	        		jObject = new JSONObject(result);
	        		JSONArray jsonDataArray = jObject.getJSONArray("dataret");
	        		
	        		for(int i= 0; i < jsonDataArray.length(); i++)
	        		{
	        			ContentValues values = new ContentValues();
	        			JSONObject each = (JSONObject) jsonDataArray.get(i);
	        			Iterator it = each.keys();
	        			//Log.v("row", each+"");
	        			String currji = each.get("jobid")+"";
	        			while(it.hasNext()) {
	        				String key = it.next()+"";
	        				values.put(key, each.get(key)+"");
	        			}
	        			Cursor status = SelectDb.getstatusNmodeByPhase(db, phase, currji);
	        			if((status != null  && status.getCount() != 0)&& !status.getString(1).equals("read"))
	        			{
		        			continue;
	        			}
	        			else
	        			{
	        				if(UpdateDb.updateTableAsusual(db, phase, clscontext, currji, values) <= 0)
	        					InsertDb.insertTableAsusual(db, phase, clscontext, values);
	        				if(UpdateDb.updatePhaseStatus(db, clscontext, currji , phase, "saved", "read") <= 0)
	        				{
	        					values = new ContentValues();
	        					values.put("jobid",currji);
	        					values.put("isold",1);
	        					values.put("phase",phase);
	        					values.put("mode","read");
	        					values.put("save_status","saved");
	        					InsertDb.insertTableAsusual(db, "JOB_LOCAL_OPERATION_PHASE_TRACK", clscontext, values);
	        				}
	        			}
	        		}
	        	}catch(Exception e){
	        		Log.e("Error: ",e.getLocalizedMessage());
	        	}
	        }
	        	/*if(isFront)
	        	{
	        		clscontext.startActivity(new Intent(clscontext, ListReports.class));
	        		((Activity) clscontext).finish();	
	        	}*/
	        	if(procceNphase.equals("fetchall"))
	        	{
	        		try{
	        			dialog.dismiss();
		        	}catch(Exception e){
						 e.getStackTrace();
					}
		        	if(open!=null)
		        	{
		        		clscontext.startActivity(new Intent(clscontext, open));
		        		((Activity) clscontext).finish();
		        	}
	        	}
	        	FETCH_REQ_SENT = false;
	       }
	    }
	 public String REQUEST_FILES(String aurl)
	{
		try {
			int count;
			URL url = new URL(aurl);
			URLConnection conexion = url.openConnection();
			conexion.connect();
			int lenghtOfFile = conexion.getContentLength();
			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
			InputStream input = new BufferedInputStream(url.openStream());
			
			String downldd = path+currJob+".zip";
			
			OutputStream output = new FileOutputStream(downldd);
			byte data[] = new byte[1024];
			long total = 0;
			while ((count = input.read(data)) != -1) {
				total += count;
				//publishProgress(""+(int)((total*100)/lenghtOfFile));
				output.write(data, 0, count);
			}
			output.flush();
			output.close();
			input.close();
			Decompress d = new Decompress(downldd, path); 
			d.unzip();
		} catch (Exception e) {}
		
		if(procceNphase.equals("fetchrelateds"))
		{
    		dialog.dismiss();
        	if(open!=null)
        	{
        		editor.putString("jobInEdit", currJob);
				editor.commit();
        		clscontext.startActivity(new Intent(clscontext, open));
        		((Activity) clscontext).finish();
        	}
    	}
		return "";
	}
	public class HttpAsyncFileDwnLd extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			return REQUEST_FILES(urls[0]);
		}
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			//dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			
		}
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
}