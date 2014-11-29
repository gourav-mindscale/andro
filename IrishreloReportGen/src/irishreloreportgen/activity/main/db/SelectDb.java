package irishreloreportgen.activity.main.db;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.database.Cursor;
import android.util.Log;

public class SelectDb {
	public static int getLastAddedJobId(DbHelper db) 
	{
		db.openDataBase();
		int jobId = 0;
		try {			
			Cursor cursor = null;
			String qry = "SELECT max(b.jobid) FROM INSPECTION_BASICS b JOIN JOB_LOCAL_OPERATION_PHASE_TRACK t on b.jobid=t.jobid where t.isold = 0";
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
				cursor.moveToFirst();
			jobId = cursor.getInt(0);
			db.close();
		} catch (Exception e) {
			jobId = 0;
		}		
		db.close();
		return jobId;
	}
	
	
	
	public static Cursor getPhaseByData(DbHelper db, String phase, String jobId,Boolean toSend, Boolean isJoinToTrack, Boolean getTracsel) 
	{
		db.openDataBase();
		Cursor cursor = null;
		try {
			String jointrack = "",select_trck = "", track_where = "";
			if(isJoinToTrack)
			{
				jointrack = " JOIN JOB_LOCAL_OPERATION_PHASE_TRACK t on b.jobid=t.jobid ";
				if(getTracsel)
					select_trck = ", t.isold, t.phase, t.mode, t.save_status, t.last_updated_by";
					track_where = " AND t.phase = '"+phase+"'";
			}
			String qry = "SELECT b.* "+select_trck+" FROM "+phase+" b "+jointrack+"where 1=1 ";
			if(phase.equals("list"))
			{
				track_where = " AND t.phase = '"+"INSPECTION_BASICS"+"'";
				qry = "SELECT b.* "+select_trck+" FROM "+" INSPECTION_BASICS "+" b "+jointrack+"where 1=1 ";
			}
			qry += track_where;
			if(!jobId.equals("0"))
				qry += " AND b.jobid=" +jobId;
			if(toSend)
			{
				qry += " AND t.mode = 'modified'";
			}else{
				
			}
			
			if(phase.equals("list"))
				qry += " order by added_date DESC";
			Log.v("query",qry);
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
				cursor.moveToFirst();
		} catch (Exception e) {
			cursor = null;
		}
		db.close();
		return cursor;
	}
	
	public static Cursor getstatusNmodeByPhase(DbHelper db, String phase, String jobId) 
	{
		db.openDataBase();
		Cursor cursor = null;
		try {
			String qry = "SELECT save_status, mode, isold, phase FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1 AND jobid="+jobId ;
			if(!phase.equals(""))
				qry = "SELECT save_status, mode, isold, phase FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1 AND phase='"+phase+"' AND jobid="+jobId ;
			
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
				cursor.moveToFirst();
		} catch (Exception e) {
			cursor = null;
		}		
		db.close();
		return cursor;
	}
	
	public static Cursor getAllJobsToSend(DbHelper db) 
	{
		db.openDataBase();
		Cursor cursor = null;
		try {
			String qry = "SELECT jobid FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1 AND save_status='tempsaved' AND mode='modified' GROUP BY jobid" ;
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
				cursor.moveToFirst();
		} catch (Exception e) {
			cursor = null;
		}		
		db.close();
		return cursor;
	}
	
	
	public static Boolean getJobsIfToSend(DbHelper db, String jobid) 
	{
		db.openDataBase();
		Cursor cursor = null;
		Boolean needtosend = false;
		try {
			Boolean nocomment = true;
			String chk_cmnt_qry = "SELECT * from 'EXTERIOR_COMMENTS' where jobid="+jobid+" GROUP BY jobid";
			try{
				if(db.MyDB().rawQuery(chk_cmnt_qry, null) != null && cursor.getCount()!=0)
				{
					nocomment = false;
				}
			}catch(Exception e){
			}
			String qry = "SELECT jobid,phase FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1 AND save_status='tempsaved' AND mode='modified' AND jobid="+jobid;
			
			if(nocomment)
				qry += " AND phase <> 'EXTERIOR_COMMENTS'";
			Log.v("some text", qry);
			
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null && cursor.getCount()!=0)
				needtosend = true;
			else
				needtosend = false;
				//cursor.moveToFirst();
		} catch (Exception e) {
			cursor = null;
		}		
		db.close();
		return needtosend;
	}
	
	public static Cursor getdatapagination(DbHelper db,int offset,int Limit) 
	{
		Log.v("OFFSET",""+offset);
		Log.v("Limit",""+Limit);
		db.openDataBase();
		Cursor cursor = null;
		try {
			String jointrack = "",select_trck = "", track_where = "";
			String qry = "";
			jointrack = " JOIN JOB_LOCAL_OPERATION_PHASE_TRACK t on b.jobid=t.jobid ";
			select_trck = ", t.isold, t.phase, t.mode, t.save_status, t.last_updated_by";
			track_where = " AND t.phase = '"+"INSPECTION_BASICS"+"'";
			qry = "SELECT b.* "+select_trck+" FROM "+" INSPECTION_BASICS "+" b "+jointrack+"where 1=1";
			qry += track_where;
			qry += " group by b.jobid order by added_date DESC LIMIT "+Limit+" OFFSET "+offset+"";
			
			Log.v("query",qry);
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
				cursor.moveToFirst();
		} catch (Exception e) {
			cursor = null;
		}
		db.close();
		return cursor;
	}
	
	
	public static Boolean getJobsWaitingRes(DbHelper db, String jobid) 
	{
		db.openDataBase();
		Cursor cursor = null;
		Boolean iswaiting = false;
		try {
			String qry = "SELECT count(jobid) FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1 AND save_status='sending' AND jobid="+jobid;
			cursor = db.MyDB().rawQuery(qry, null);
			if(cursor != null)
			{
				cursor.moveToFirst();
				if(cursor.getInt(0) != 0)
					iswaiting = true;
			}
		} catch (Exception e) {
			cursor = null;
		}		
		db.close();
		return iswaiting;
	}
	public static void getAllStatus(DbHelper db)
	{
		db.openDataBase();
		Cursor cursor = null;
		String result = "";
		String qry = "SELECT * FROM JOB_LOCAL_OPERATION_PHASE_TRACK where 1=1";
		try{
		cursor = db.MyDB().rawQuery(qry, null);
		if(cursor != null)
		{
			cursor.moveToFirst();
			for(int i=0; i< cursor.getCount(); i++)
			{
				for(int j=0; j<cursor.getColumnCount(); j++)
					result +=  cursor.getString(j)+"|";
				cursor.moveToNext();
				result += "\n";
			}
		}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		result += "\n -----------------------------------------------------------";
		qry = "SELECT * FROM MAIN_BATHROOM where 1=1 AND jobid = 1322811123428024";
		try{
		cursor = db.MyDB().rawQuery(qry, null);
		if(cursor != null)
		{
			cursor.moveToFirst();
			for(int i=0; i< cursor.getCount(); i++)
			{
				for(int j=0; j<cursor.getColumnCount(); j++)
					result +=  cursor.getString(j)+"|";
				cursor.moveToNext();
				result += "\n";
			}
		}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		db.close();
		IrishreloAccess.write("status", result);
	}
}
