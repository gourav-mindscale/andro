package irishreloreportgen.activity.main.db;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;
public class UpdateDb {
	public static long updateTableAsusual(DbHelper db, String tableName, Context context, String id, ContentValues contentValues) {
		db.openDataBase();
		long Success = -1;
		try {
			Success = db.MyDB().update(tableName, contentValues, "jobid ="+id, null);
		} catch (Exception e) {
			Success = -1;
		}
		db.close();
		return Success;
	}
	public static long updatePhaseStatus(DbHelper db, Context context, String id, String phase, String status, String mode) {
		db.openDataBase();
		ContentValues contentValues = new ContentValues();
		if(!status.equals(""))
			contentValues.put("save_status", status);
		if(!mode.equals(""))
			contentValues.put("mode", mode);
		long Success = -1;
		try {
			Success = db.MyDB().update("JOB_LOCAL_OPERATION_PHASE_TRACK", contentValues, "jobid ="+id+" AND phase ='"+phase+"'", null);
		} catch (Exception e) {
			Success = -1;
		}
		db.close();
		return Success;
	}
	public static void clearData(DbHelper db, Context context)
	{
		db.openDataBase();
		long Success = -1;
		try {
			Success = db.MyDB().delete("INSPECTION_BASICS", null, null);
			Success = db.MyDB().delete("JOB_LOCAL_OPERATION_PHASE_TRACK", null, null);
		} catch (Exception e) {
			Success = -1;
		}
		if(Success > 0)
			Toast.makeText(context, "Al Dta Cleared!!", Toast.LENGTH_LONG).show();
		db.close();
	}
	public static long updateAllStatusOnNoResp(DbHelper db, Context context, String id) {
		db.openDataBase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("save_status", "tempsaved");
		contentValues.put("mode", "modified");
		long Success = -1;
		try {
			Success = db.MyDB().update("JOB_LOCAL_OPERATION_PHASE_TRACK", contentValues, "jobid ="+id+" AND save_status='sending'", null);
		} catch (Exception e) {
			Success = -1;
		}
		db.close();
		return Success;
	}
	
	public static long updateIdOnServerCreate(DbHelper db, Context context, String id, String idInResp) {
		db.openDataBase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("jobid", idInResp);
		long Success = -1;
		try {
			Success = db.MyDB().update("INSPECTION_BASICS", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("POWER_UTILITIES", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("COMMUNICATION_N_PRECAUTIONS ", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("IF_APPLICABLE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("APARTMENT_PARKING", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EXTERIOR_COMMENTS", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("SIGNED_BY", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("HALL_OR_LAND", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("UTILITY_ROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("MASTER_BED_ROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("BEDROOM_2", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("BEDROOM_3", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("MAIN_BATHROOM", contentValues, "jobid ="+id, null);
			
			
			//code added by Joy
			Success = db.MyDB().update("BEDROOM_5", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("ROOM_ONE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("ROOM_TWO", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("ROOM_THREE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("ROOM_FOUR", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("ROOM_FIVE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_ONE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_TWO", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_THREE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_FOUR", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("EN_SUITE_FIVE", contentValues, "jobid ="+id, null);
			Success = db.MyDB().update("MPRN_GPRN", contentValues, "jobid ="+id, null);
			
			
			
			
			
			
			
			contentValues.put("isold", 1);
			Success = db.MyDB().update("JOB_LOCAL_OPERATION_PHASE_TRACK", contentValues, "jobid ="+id+"", null);
		} catch (Exception e) {
			Success = -1;
		}
		db.close();
		return Success;
	}
}
