package PopUpDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.Header.HttpAsyncTask;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AltertEmailPopUp extends Dialog {
	Context mContext;
	String editJobId = "0";

	public AltertEmailPopUp(Context context) {
		super(context);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.alternativ_email_popup);
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
			    	//new HttpAsyncTask().execute(IrishreloAccess.WEBSERVICE+"fetch_data_generate_report_send_mail");
			    	Toast.makeText(mContext, userInput.getText().toString(), Toast.LENGTH_LONG).show();
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
