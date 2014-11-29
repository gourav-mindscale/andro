package irishreloreportgen.staticclassnconst;
import java.util.Date;
import android.database.Cursor;

public class InspectionBasics {
	public String jobId;
	public int isOld;
	public Date reportdt;
	public long reportdtlong;
	public String tenant;
	public String occupier;
	public String address;
	public String savestatus;
	public InspectionBasics(Cursor cr)
	{
		this.jobId = cr.getString(cr.getColumnIndex("jobid"));
		this.isOld = cr.getInt(cr.getColumnIndex("isold"));
		this.tenant = cr.getString(cr.getColumnIndex("tenant"));
		this.occupier = cr.getString(cr.getColumnIndex("occupier"));
		this.reportdt = new Date(cr.getInt(cr.getColumnIndex("check_in")));
		this.address = cr.getString(cr.getColumnIndex("address"));
		this.reportdtlong = cr.getLong(cr.getColumnIndex("check_in"));
		this.savestatus = cr.getString(cr.getColumnIndex("save_status"));
	}
	
	public InspectionBasics(String jobId, int isOld, Date reportdt,Long reportdtint, String tenant, String occupier, String address, String savestatus)
	{
		this.jobId = jobId;//cr.getInt(cr.getColumnIndex("jobid"));
		this.isOld = isOld;//cr.getInt(cr.getColumnIndex("isold"));
		this.tenant = tenant;//cr.getString(cr.getColumnIndex("tenant"));
		this.occupier = occupier;//cr.getString(cr.getColumnIndex("occupier"));
		this.reportdt = reportdt;//new Date(cr.getInt(cr.getColumnIndex("occupier")));
		this.address = address;
		this.reportdtlong = reportdtint;
		this.savestatus = savestatus;
	}
}
