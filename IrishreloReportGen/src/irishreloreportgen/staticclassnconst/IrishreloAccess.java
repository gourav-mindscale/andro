package irishreloreportgen.staticclassnconst;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IrishreloAccess  extends Application {
	private static boolean activityVisible;
	public static String imagePath="",captbyCam="";
	public static Button synchbtn;
	public static ImageView camerapic = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("Application OnCreate()", "Application Started");
	}

	//public static String SERVER = "http://www.codeuridea.net/irsApp/";//"http://appit.ie/irishrelo/irishreloapp/";//
	
	public static String SERVER = "http://192.168.1.7/Wordpress/irsApp/";//"http://appit.ie/irishrelo/irishreloapp/";//

	
	public static String WEBSERVICE = SERVER+"irswebservice/";
	public static LinearLayout editbtnonsynch;
	public static boolean synchfromheader = false;
	
	public static boolean isActivityVisible() {
		return activityVisible;
	}  
	
	public static void activityResumed() {
		activityVisible = true;
	}
	
	public static void activityPaused() {
		activityVisible = false;
	}
	
	public static Bitmap getResizedImage(File file) {
		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getPath(), bounds);
		if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
			return null;
		int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
				: bounds.outWidth;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		if (bounds.outHeight > bounds.outWidth)
			opts.inSampleSize = originalSize / 360;
		else
			opts.inSampleSize = originalSize / 640;
		return BitmapFactory.decodeFile(file.getPath(), opts);
	}	
	public static long getSize(String path) {
		StatFs stat = new StatFs(path);
		long bytesAvailable = (long)stat.getBlockSize() *(long)stat.getBlockCount();
		long megAvailable = bytesAvailable / 1048576;
		return megAvailable;
	}
	
	public static Boolean write(String fname, String fcontent){
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
	
	public Boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf = cm.getActiveNetworkInfo();
		if(nf != null && nf.isConnectedOrConnecting())
		{
			return true;
		}
		return false;		
	}
}