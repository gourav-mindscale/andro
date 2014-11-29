package irishreloreportgen.activity.main.utilities;

import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.serv.SendToServer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MPRN_GAS extends Activity {
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	DbHelper db;
	String editJobId = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		setContentView(R.layout.mprn_gas);
		((Button) findViewById(R.id.proctonext)).setText("Finish");
		if (!editJobId.equals("0"))
			renderData();

	}

	public void onElementClick(View v) {
		switch (v.getId()) {
		case R.id.savephase:
			saveData(v);
			break;
		case R.id.proctonext:
			gotoNext(v);
			break;
		case R.id.backtoprev:
			backTheprocess(v);

			break;
		}
	}

	void backTheprocess(View v) {
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, CommunicationNPrecaution.class));
		finish();
	}

	void gotoNext(View v) {
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, CommonGoToActivity.class));
		finish();
	}

	void saveData(View v) {
		ContentValues contentValues = new ContentValues();
		int selectedId = 0;
		db.openDataBase();
		int Success = -1;
		Log.v("editJobId", editJobId + "");

		/*
		 * long Success = -1; Log.v("editJobId",editJobId+""); ContentValues
		 * contentValues = new ContentValues();
		 * 
		 * 
		 * contentValues = new ContentValues();
		 */

		// mprn_code
		try {
			contentValues.put("mprn_code",
					((EditText) findViewById(R.id.et_MPRN_code)).getText()
							.toString());
			Log.v("for", "et_MPRN_code value="
					+ ((EditText) findViewById(R.id.et_MPRN_code)).getText()
							.toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_MPRN_code Exception"
					+ ((EditText) findViewById(R.id.et_MPRN_code)).getText()
							.toString());

		}

		try {
			contentValues.put("mprn_supplier_code",
					((EditText) findViewById(R.id.et_MPRN_supplier_code))
							.getText().toString());
			Log.v("for", "et_MPRN_supplier_code value="
					+ ((EditText) findViewById(R.id.et_MPRN_supplier_code))
							.getText().toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_MPRN_supplier_code Exception"
					+ ((EditText) findViewById(R.id.et_MPRN_supplier_code))
							.getText().toString());

		}

		try {
			contentValues.put("mprn_supplier_name",
					((EditText) findViewById(R.id.et_MPRN_supplier_name))
							.getText().toString());
			Log.v("for", "et_MPRN_supplier_name value="
					+ ((EditText) findViewById(R.id.et_MPRN_supplier_name))
							.getText().toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_MPRN_supplier_name Exception"
					+ ((EditText) findViewById(R.id.et_MPRN_supplier_name))
							.getText().toString());

		}

		// et_GPRN_Code

		try {
			contentValues.put("gprn_code",
					((EditText) findViewById(R.id.et_GPRN_Code)).getText()
							.toString());
			Log.v("for", "et_GPRN_Code value="
					+ ((EditText) findViewById(R.id.et_GPRN_Code)).getText()
							.toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_GPRN_Code Exception"
					+ ((EditText) findViewById(R.id.et_GPRN_Code)).getText()
							.toString());

		}

		try {
			contentValues.put("gprn_supplier_code",
					((EditText) findViewById(R.id.et_GPRN_supplier_code))
							.getText().toString());
			Log.v("for", "et_GPRN_supplier_code value="
					+ ((EditText) findViewById(R.id.et_GPRN_supplier_code))
							.getText().toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_GPRN_supplier_code Exception"
					+ ((EditText) findViewById(R.id.et_GPRN_supplier_code))
							.getText().toString());

		}

		// gprn_supplier_name

		try {
			contentValues.put("gprn_supplier_name",
					((EditText) findViewById(R.id.et_GPRN_supplier_name))
							.getText().toString());
			Log.v("for", "et_GPRN_supplier_name value="
					+ ((EditText) findViewById(R.id.et_GPRN_supplier_name))
							.getText().toString());

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("for", "et_GPRN_supplier_name Exception"
					+ ((EditText) findViewById(R.id.et_GPRN_supplier_name))
							.getText().toString());

		}

		try {
			// Success = db.MyDB().update("COMMUNICATION_N_PRECAUTIONS",
			// contentValues, "jobid ="+editJobId, null);
			Success = db.MyDB().update("MPRN_GPRN", contentValues,
					"jobid =" + editJobId + " ", null);
			Log.v("updated for", "MPRN_GPRN" + Success);

		} catch (Exception e) {
			e.printStackTrace();
		}
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "MPRN_GPRN",
				"tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db, "MPRN_GPRN",
				editJobId);
		// if(status.getInt(2) == 1)
		{
			SendToServer s2sv = new SendToServer(db, this, true, "MPRN_GPRN",
					editJobId, "create/update", CommonGoToActivity.class);
			s2sv.frontEndSend();
			// }else{
		}

	}

	void renderData() {

		Cursor cursor = SelectDb.getPhaseByData(db, "MPRN_GPRN", editJobId,
				false, true, false);
		db.openDataBase();
		
		String name;
		try {
			Cursor c = db.MyDB().query(true, "MPRN_GPRN", null, "jobid=" + editJobId,
					null, null, null, null, null, null);
			int i = c.getColumnIndex("mprn_code");
			c.moveToFirst();
			name = c.getString(i);
			Log.v("Mprn -Code", name);
			Log.v("Select MPRN", String.valueOf(c.getCount()));
			if (c != null) {

				// && cursor.moveToNext()

				Log.v("count", "" + cursor.getCount());
				try {
					// ((EditText)findViewById(R.id.et_MPRN_code)).setText("Joy");

					((EditText) findViewById(R.id.et_MPRN_code)).setText(c
							.getString(c.getColumnIndex("mprn_code")));

					/*
					 * int nameIndex = cursor.getColumnIndex("mprn_code");
					 * ((EditText
					 * )findViewById(R.id.et_MPRN_code)).setText(nameIndex);
					 */
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("getting Exception", "mprn_code");

				}

				try {
					((EditText) findViewById(R.id.et_MPRN_supplier_code))
							.setText(c.getString(c
									.getColumnIndex("mprn_supplier_code")));
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("getting Exception", "mprn_Supplier_code");

				}

				try {
					((EditText) findViewById(R.id.et_MPRN_supplier_name))
							.setText(c.getString(c
									.getColumnIndex("mprn_supplier_name")));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					((EditText) findViewById(R.id.et_GPRN_Code)).setText(c
							.getString(c.getColumnIndex("gprn_code")));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					((EditText) findViewById(R.id.et_GPRN_supplier_code))
							.setText(c.getString(c
									.getColumnIndex("gprn_supplier_code")));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					((EditText) findViewById(R.id.et_GPRN_supplier_name))
							.setText(c.getString(c
									.getColumnIndex("gprn_supplier_name")));
				} catch (Exception e) {
					e.printStackTrace();
				}

				cursor.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		// Cursor cursor = SelectDb.getPhaseByData(db,
		// "COMMUNICATION_N_PRECAUTIONS", editJobId, false, true, false);

		// cursor.moveToFirst();
		// cursor.moveToNext();
		
	}

}
