package irishreloreportgen.activity.main.serv;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.ListReports;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.staticclassnconst.IrishreloAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
public class SendToServer{
	Boolean isFront = false;
	String phase = "";
	Context  clscontext;
	DbHelper db;
	String currJob = "0";
	String procceNphase="";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	Class start;
	public SendToServer(DbHelper db,Context ctx, Boolean isFront, String phase,String currJob, String procceNphase, Class cls){
		this.clscontext = ctx;
		this.isFront = isFront;
		this.phase = phase;
		this.db = db;
		this.currJob = currJob;
		this.procceNphase = procceNphase;
		this.start = cls;
		settings = ctx.getSharedPreferences(ctx.getString(R.string.app_name), 0);
		editor = settings.edit();
	}
	
	public String makeJshon()
	{
		String json = "";
		switch(phase)
		{
			case "INSPECTION_BASICS":
				
			case "COMMUNICATION_N_PRECAUTIONS":
				json = inspect_data();
				break;
//coded by joy				
			case "MPRN_GPRN":
				json = inspect_data();
				break;
				
//......				
			default:
				json = powutil_data();
			break;
		}
		Log.v("jsonbuild", json.toString());
		return json;
	}
	String inspect_data()
	{
		Cursor cursor = null;
		if(phase == "INSPECTION_BASICS")
			cursor = SelectDb.getPhaseByData(db, phase, currJob, true,true,true);
		else
			cursor = SelectDb.getPhaseByData(db, phase, currJob, true,true,false);
		JSONObject jsonObject = new JSONObject();
		if(cursor != null)
		{
			for(int i = 0; i< cursor.getColumnCount(); i++)
			{
				try
				{
					jsonObject.accumulate(cursor.getColumnName(i), cursor.getString(i));
				}catch(Exception e){
					Log.e("error",e.toString()+"");
				}
			}					
		}
		// 4. convert JSONObject to JSON to String
	    return jsonObject.toString();
	}
	String powutil_data()
	{
		JSONArray jsonArray = new JSONArray();
		Cursor cursor = SelectDb.getPhaseByData(db, phase, currJob, true, true, false);
		try
		{
			if(cursor != null)
			{
				for(int j=0; j < cursor.getCount(); j++)
				{
					JSONObject eachObj = new JSONObject();
					for(int i = 0; i< cursor.getColumnCount(); i++)
					{
						try
						{
							eachObj.accumulate(cursor.getColumnName(i), cursor.getString(i));
						}catch(Exception e){
							eachObj.accumulate(cursor.getColumnName(i), "");
						}
					}
					jsonArray.put(eachObj);
					cursor.moveToNext();
				}
			}else{
			}
		}catch(Exception e){
			Log.e("error",e.getLocalizedMessage()+"");
			e.printStackTrace();
		}
		Log.v("jsonbuild inspectdata", jsonArray.toString());
		
		 return jsonArray.toString();
	}	
	public  MultipartEntity build_multipart()
	{
		MultipartEntity  multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);  
        
		JSONArray jsonArray = new JSONArray();
		Cursor cursor = SelectDb.getPhaseByData(db, phase, currJob, true, true, false);
		try
		{
			String there = "";
			if(cursor != null)
			{
				
				for(int j=0; j < cursor.getCount(); j++)
				{
					JSONObject eachObj = new JSONObject();
					for(int i = 0; i< cursor.getColumnCount(); i++)
					{
						String key = "";
						try
						{
							if(cursor.getColumnName(i).contains("img") && !cursor.getColumnName(i).contains("name") && !cursor.getString(i).isEmpty())
							{
								key = phase+"_"+cursor.getString(cursor.getColumnIndex("inspect_type"))+"_"+cursor.getColumnName(i);
								File attach = new File (cursor.getString(i));
								if(attach.exists())
									multipartEntity.addPart(key, new FileBody(attach));
							}else if(cursor.getColumnName(i).contains("imp") && !cursor.getColumnName(i).contains("name") && !cursor.getString(i).isEmpty()){
								key = phase+"_"+cursor.getString(cursor.getColumnIndex("conserned_type"))+"_"+cursor.getColumnName(i);
								File attach = new File (cursor.getString(i));
								if(attach.exists())
								{
									multipartEntity.addPart(key, new FileBody(attach));
									there += key+",";
								}
							}
				            
							eachObj.accumulate(cursor.getColumnName(i), cursor.getString(i));
						}catch(Exception e){
							eachObj.accumulate(cursor.getColumnName(i), "");
						}
					}
					jsonArray.put(eachObj);
					cursor.moveToNext();
				}
				
			}else{
			}
			write("files", there);
		}catch(Exception e){
			Log.e("error",e.getLocalizedMessage()+"");
		}
		Log.v("jsonbuild inspectdata", jsonArray.toString());
		try
		{
			multipartEntity.addPart("data", new StringBody(jsonArray.toString()));
			multipartEntity.addPart("phase", new StringBody(phase));
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return multipartEntity;
	}
	public String POST(String url){
		InputStream inputStream = null;
		String result = "";
		try {
			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			// 5. set json to StringEntity
			switch(phase)
			{
				case "INSPECTION_BASICS":
					
				case "POWER_UTILITIES":
					
				case "COMMUNICATION_N_PRECAUTIONS":
					
				case "IF_APPLICABLE":
					 
				case "APARTMENT_PARKING":
					
				case "EXTERIOR_COMMENTS":
					 // 6. set httpPost Entity
					httpPost.setHeader("Accept", "application/json");
					httpPost.setHeader("Content-type", "application/json");
					StringEntity se = new StringEntity(makeJshon());
					httpPost.setEntity(se);
					break;
				default:
				    MultipartEntity multipartEntity = build_multipart();
			        httpPost.setEntity(multipartEntity);			   
					break;
			}
		//Toast.makeText(clscontext, "datasent", Toast.LENGTH_LONG).show();
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
	public String frontEndSend()
	{
		InputMethodManager inputManager = (InputMethodManager)((Activity)clscontext).getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.hideSoftInputFromWindow(((View)((Activity)clscontext).findViewById(R.id.savephase)).getWindowToken(),
				                              InputMethodManager.HIDE_NOT_ALWAYS);
		((Button)((Activity)clscontext).findViewById(R.id.synchtoserver)).setVisibility(View.VISIBLE);
		((Button)((Activity)clscontext).findViewById(R.id.synchtoserver)).setText("Sync to server");
		Toast.makeText(clscontext,"Data Saved.", Toast.LENGTH_SHORT).show();
		return currJob;
	 }
	public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return POST(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	Log.v("returned", result);
        	write("response", result);
        	try{
        		JSONObject jsonObject = new JSONObject(result);
        		if(Boolean.parseBoolean(jsonObject.getString("status")))
        		{
        			long suc = -1;
	        		if(jsonObject.getString("phase").equals("INSPECTION_BASICS"))
	        		{
        				//suc = UpdateDb.updateIdOnServerCreate(db, clscontext, currJob, Integer.parseInt(jsonObject.getString("id")));
        				editor.putInt("jobInEdit", Integer.parseInt(jsonObject.getString("id")));
		        		editor.putInt("isOldJob", 1);
		    			editor.commit();	
		    			try{
		    				((Button)((Activity)clscontext).findViewById(R.id.sendemail)).setVisibility(View.VISIBLE);
	    		        }catch(Exception e){
	    		        	e.getStackTrace();
	    		        }
	        		}		        		
	        		Cursor status = SelectDb.getstatusNmodeByPhase(db,jsonObject.getString("phase"),jsonObject.getString("id"));
	        		suc = UpdateDb.updatePhaseStatus(db, clscontext, jsonObject.getString("id"), jsonObject.getString("phase"), "saved", "read");
	        		if(suc > 0)
	        		{
	        			Toast.makeText(clscontext,"Data Saved.", Toast.LENGTH_SHORT).show();
	        		}	
        		}
        	}catch(Exception e){
        		Log.v("Error -->302", e.toString());
        		e.printStackTrace();
        	}
       }
	}
	
	private String POST_FULL_JOB(String url)
	{
		InputStream inputStream = null;
		String result = "";
		try {
			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			// 5. set json to StringEntity
			MultipartEntity multipartEntity = build_alldata_multipart();
	        httpPost.setEntity(multipartEntity);
			//Toast.makeText(clscontext, "datasent", Toast.LENGTH_LONG).show();
		    HttpResponse httpResponse = httpclient.execute(httpPost);
		    // 9. receive response as inputStream
		    inputStream = httpResponse.getEntity().getContent();
		    // 10. convert inputstream to string
		    if(inputStream != null)
		    	result = convertInputStreamToString(inputStream);
		    else{ 
		    	result = "Did not work!";
		    	UpdateDb.updateAllStatusOnNoResp(db, clscontext,currJob);
		    }
	     } catch (Exception e) {
	         Log.d("InputStream", e.getLocalizedMessage());
	         UpdateDb.updateAllStatusOnNoResp(db, clscontext,currJob);
	     }		
		// 11. return result
	    return result;
	}
	public  MultipartEntity build_alldata_multipart()
	{
		int isold = 0; 
		String there = "";
		MultipartEntity  multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);  
        Cursor all_phases_stat = SelectDb.getstatusNmodeByPhase(db,"", currJob);
        JSONObject toSendObj = new JSONObject();
        if(all_phases_stat != null)
        {	
	        for(int k=0; k < all_phases_stat.getCount(); k++)
			{
	        	JSONArray jsonArray = new JSONArray();
	        	phase = all_phases_stat.getString(all_phases_stat.getColumnIndex("phase"));
	        	if(k == 0)
					isold = all_phases_stat.getInt(all_phases_stat.getColumnIndex("isold"));
				if(all_phases_stat.getString(all_phases_stat.getColumnIndex("save_status")).equals("tempsaved"))
				{	
					//UpdateDb.updatePhaseStatus(db, this, editJobId, "INSPECTION_BASICS", "tempsaved", "modified");
					Cursor cursor = SelectDb.getPhaseByData(db, phase, currJob, true, true, false);
					
					if(cursor != null && cursor.getCount() != 0)
					{
						UpdateDb.updatePhaseStatus(db, clscontext, currJob, phase, "sending", "write");
						for(int j=0; j < cursor.getCount(); j++)
						{
							JSONObject eachObj = new JSONObject();
							for(int i = 0; i< cursor.getColumnCount(); i++)
							{
								String key = "";
								try
								{
 									if(cursor.getColumnName(i).contains("img") && !cursor.getColumnName(i).contains("name") && !cursor.getString(i).isEmpty())
									{
										key = phase+"_"+cursor.getString(cursor.getColumnIndex("inspect_type"))+"_"+cursor.getColumnName(i);
										File attach = new File (cursor.getString(i));
										if(attach.exists())
										{
											multipartEntity.addPart(key, new FileBody(attach));
											there += key+",";
										}
									}else if(cursor.getColumnName(i).contains("imp") && !cursor.getColumnName(i).contains("name") && !cursor.getString(i).isEmpty()){
										key = phase+"_"+cursor.getString(cursor.getColumnIndex("conserned_type"))+"_"+cursor.getColumnName(i);
										File attach = new File (cursor.getString(i));
										if(attach.exists())
										{
											multipartEntity.addPart(key, new FileBody(attach));
											there += key+",";
										}
									}
						            
									eachObj.accumulate(cursor.getColumnName(i), cursor.getString(i));
								}catch(Exception e){
									try {
										eachObj.accumulate(cursor.getColumnName(i), "");
									} catch (JSONException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							jsonArray.put(eachObj);
							cursor.moveToNext();
						}
					}
				}	
				try {
					toSendObj.accumulate(phase,jsonArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				all_phases_stat.moveToNext();
			}
	        write("req"+currJob, toSendObj.toString());
			try
			{
				Log.v("Sync Data", toSendObj.toString());
				multipartEntity.addPart("data", new StringBody(toSendObj.toString()));
				
				if(isold == 1)
				{
					multipartEntity.addPart("jobid", new StringBody(currJob+""));
				}
			}catch(Exception e)
			{
				e.getStackTrace();
			}
        }       
		return multipartEntity;
	}
	
	public  void backEndSend()
	{
		Cursor cr = SelectDb.getAllJobsToSend(db);
		if(cr != null && cr.getCount()!=0)
		for(int i = 0; i < cr.getCount(); i++)
		{
			currJob = cr.getString(cr.getColumnIndex("jobid"));
			new HttpAsyncSaveAllTask().execute(IrishreloAccess.WEBSERVICE+"save_all_data");
			cr.moveToNext();
		}
	}
	
	public  void SynchWithServer()
	{
		new HttpAsyncSaveAllTask().execute(IrishreloAccess.WEBSERVICE+"save_all_data");
	}
	
	public class HttpAsyncSaveAllTask extends AsyncTask<String, Void, String> {
	  @Override
       protected String doInBackground(String... urls) {
    	  // if(isFront && start.getName().contains("ListReports"))
    		 //  progressDialog= ProgressDialog.show((Activity)clscontext, "",  "Saving Data Please wait...", true);
            return POST_FULL_JOB(urls[0]);
       }
       // onPostExecute displays the results of the AsyncTask.
       @Override
       protected void onPostExecute(String result) {
    	   write("res"+currJob, result);
    	   try{
       		JSONObject jsonObject = new JSONObject(result);
       		if(Boolean.parseBoolean(jsonObject.getString("status")))
       		{
       			long suc = -1;
   				if(jsonObject.getString("operation").equals("INSERT"))
   					UpdateDb.updateIdOnServerCreate(db, clscontext, jsonObject.getString("id"), jsonObject.getString("id"));
   				if(currJob.equals(settings.getString("jobInEdit","0")))
   				{
   					editor.putString("jobInEdit", jsonObject.getString("id"));
   					editor.putInt("isOldJob", 1);
   					editor.commit();
   				}
   				
   				JSONObject updated = jsonObject.getJSONObject("done");
   				Iterator it = updated.keys();
    			while(it.hasNext()) {
    				String key = it.next()+"";
    				Log.v("key", key);
    				if(Boolean.parseBoolean(updated.get(key)+""))
    					suc = UpdateDb.updatePhaseStatus(db, clscontext, jsonObject.getString("id"), key, "saved", "read");
    				else if(!Boolean.parseBoolean(updated.get(key)+""))
    				{
    					suc = UpdateDb.updatePhaseStatus(db, clscontext, jsonObject.getString("id"), key, "tempsaved", "modified");
    				}
				}	
	        	if(suc > 0)
	        	{
	        		Toast.makeText(clscontext,"Data Saved.", Toast.LENGTH_SHORT).show();
	        	}
	        	try{
	        		IrishreloAccess.editbtnonsynch.setTag(jsonObject.getString("id")+"saved");
		        	((Button)((Activity)clscontext).findViewById(R.id.sendemail)).setVisibility(View.VISIBLE);
		        }catch(Exception e){
		        	e.getStackTrace();
		        }
	        	try{
	        		if(isFront && start.getName().contains("ListReports"))
	        			IrishreloAccess.synchbtn.setVisibility(View.INVISIBLE);
	        		IrishreloAccess.editbtnonsynch.setClickable(true);
	        	}catch(Exception e){
       		 	}
       		}else{
       			try{
		        	//if(isFront && start.getName().contains("ListReports"))
		        	{
		        		IrishreloAccess.synchbtn.setVisibility(View.VISIBLE);
		        		IrishreloAccess.editbtnonsynch.setClickable(true);
		        	}
		        	UpdateDb.updateAllStatusOnNoResp(db, clscontext,currJob);
		        	Toast.makeText(clscontext,"Data could not synchronized, try later!!.", Toast.LENGTH_SHORT).show();
	        	}catch(Exception e){
	           		
       		 	}
       		}
       	}catch(Exception e){
       		Log.v("Error --510", e.toString());
       		e.printStackTrace();
       	}
    	IrishreloAccess.synchbtn.setText("Sync to server");  
    	IrishreloAccess.synchbtn.setClickable(true);
    	if(IrishreloAccess.synchfromheader)
       	{
       		try{
       			//((ViewGroup)((Activity)clscontext).findViewById(R.id.navwraper)).setVisibility(View.VISIBLE);
       		 }catch(Exception e){
          		
       		 }
       	}
       }
	}
	private static String convertInputStreamToString(InputStream inputStream) throws IOException
	{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }  
	public Boolean write(String fname, String fcontent){
	  try {
	        String fpath = "/sdcard/"+fname+".txt";
	        File file = new File(fpath);
	        // If file does not exists, then create it
	        if (!file.exists()) {
	          file.createNewFile();
	        }
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fcontent);
			bw.close();
			Log.d("Suceess","Sucess");
			return true;
		  } catch (IOException e) {
		    e.printStackTrace();
		    return false;
		  }
	   }
}