package async;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

@SuppressWarnings("deprecation")
public class HttpConnection 
{

	HttpClient client ;
	HttpPost post;
	InputStream is;
	
	protected String getPostRespoonse(String url, ArrayList<NameValuePair> list)
	{
		String res = "";
		try
		{
			client = new DefaultHttpClient(StaticHttp.setTimeOutParam());
			
			post = new HttpPost(url);
			
			post.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse response = client.execute(post);
			StatusLine status = response.getStatusLine();
			if(status.getStatusCode() == 200)
			{
				is = response.getEntity().getContent();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);;
				StringBuilder sb = new StringBuilder();
				String line = null;
				
				while((line=br.readLine())!=null)
				{
					sb.append(line+"\n");
				}
				is.close();
				
				res = sb.toString();
//				System.out.print(result);
			}
			else
			{
				res = "";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
	
	protected String getMultiPartPostRespoonse(String url, B_SendData b_SendData)
	{
		String res = "";
		HttpClient httpClient;
		HttpPost post ;
		MultipartEntity reqEntity;
		
		try
		{
			HttpEntity resEntity;
			
			URI uri = new URI(url);
            httpClient = new DefaultHttpClient();
            post = new HttpPost(uri);
            
			reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			
			reqEntity.addPart("tablename", new StringBody(b_SendData.tableName));
			reqEntity.addPart("jobid", new StringBody(b_SendData.jobid));
			reqEntity.addPart("columnname", new StringBody(b_SendData.columnName));
			reqEntity.addPart("typename", new StringBody(b_SendData.typeName));
			reqEntity.addPart("typevalue", new StringBody(b_SendData.typeValue));
			String[] fileName = b_SendData.fileName.split("\\},\\{");
			for(int i=0; i<fileName.length; i++){
				if(fileName[i].length()>0 && i==0){
					try{
						reqEntity.addPart("file1", new FileBody(new File(fileName[i])));
					}catch(Exception e){
						Log.v(getClass().getSimpleName()+"-->Custom Sync", "File Creation");
					}
				}else if(fileName[i].length()>0 && i==1){
					try{
						reqEntity.addPart("file2", new FileBody(new File(fileName[i])));
					}catch(Exception e){
						Log.v(getClass().getSimpleName()+"-->Custom Sync", "File Creation");
					}
				}else if(fileName[i].length()>0 && i==2){
					try{
						reqEntity.addPart("file3", new FileBody(new File(fileName[i])));
					}catch(Exception e){
						Log.v(getClass().getSimpleName()+"-->Custom Sync", "File Creation");
					}
				}
				else if(fileName[i].length()>0 && i==3){
					try{
						reqEntity.addPart("file4", new FileBody(new File(fileName[i])));
					}catch(Exception e){
						Log.v(getClass().getSimpleName()+"-->Custom Sync", "File Creation");
					}
				}
			}
			/*for(int i =0; i<list.size(); i++)
			{
				if(list.get(i).getName().equals(""))
				{
					if(!list.get(i).getValue().equals("0"))
					{
						File file = new File(list.get(i).getValue());
						reqEntity.addPart(list.get(i).getName(), new FileBody(file));
					}
					else
						reqEntity.addPart(list.get(i).getName(), new StringBody(""));
				}
				else
					reqEntity.addPart(list.get(i).getName(), new StringBody(list.get(i).getValue()) );
			}*/
		
			post.setEntity(reqEntity);
            
            HttpResponse response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == 200)
            {
            	resEntity = response.getEntity();
            	res= EntityUtils.toString(resEntity);
                System.out.print(res);
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
}