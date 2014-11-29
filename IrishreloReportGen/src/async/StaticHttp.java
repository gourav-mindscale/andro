package async;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class StaticHttp 
{
	public static HttpParams setTimeOutParam()
	{
		HttpParams httpParameters = new BasicHttpParams();
		
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 20000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		
		// Set the return in milliseconds until a connection is established.
		int timeoutSocket = 20000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		return httpParameters;
	}
	
	public static boolean getStatusCode(HttpResponse response)
	{
		boolean check = false;
		
		StatusLine sl = response.getStatusLine();
		if(sl.getStatusCode() == 200)
		{
			check = true;
		}
		else
			check = false;
		
		return check;
	}
}
