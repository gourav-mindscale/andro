package irishreloreportgen.activity.main.serv;

import java.util.ArrayList;
import java.util.List;

import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.ListReports;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class DataSynchronise extends BroadcastReceiver {
	private DbHelper db;
	//SharedPreferences settings;
	Context context;
	@Override
    public void onReceive(final Context context, final Intent intent) {
		this.context = context;
		db = new DbHelper(this.context);
		try{
		final ConnectivityManager connMgr = (ConnectivityManager) this.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
       
        if (wifi.isAvailable() || mobile.isAvailable()) {
        	if(!IrishreloAccess.isActivityVisible())
            {
        		//FetchFromServer ffsv= new FetchFromServer(db, this.context, false, "INSPECTION_BASICS" , "0", "fetchall", null);
				//ffsv.sendReuest();
				
				
        		//SendToServer s2sv = new SendToServer(db, this.context, false, "saveall","0", "create/update", null);	
        		//s2sv.backEndSend();
            }
        }
       }catch(Exception e){
    	   Toast.makeText(this.context, "problem: "+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    	   e.getStackTrace();
       }
    }
}
