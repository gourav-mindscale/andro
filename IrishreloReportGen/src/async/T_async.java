package async;

import java.util.ArrayList;

import android.os.AsyncTask;

public class T_async extends AsyncTask<Object, Void, String> {

	public AsyncResponse callback = null;
	@Override
	protected String doInBackground(Object... obj) {
		
		
		return new HttpConnection().getMultiPartPostRespoonse((String)obj[0], (B_SendData)obj[1]);
	}

	@Override
	protected void onPostExecute(String result) 
	{
		callback.onResponse(result);
	}
	
	

}
