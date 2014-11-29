package irishreloreportgen.activity.main.interior;

import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.exterior.Exterior;
import irishreloreportgen.activity.main.exterior.ExteriorIfApplicable;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.activity.main.utilities.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import customview.Picture_4;
import customview.TextPicLayout;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Interior extends ActionBarActivity implements OnClickListener {
	public static final int SIGNATURE_ACTIVITY = 3;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	ImageView camerapic;

	TextPicLayout mTextPicLayout, mTextPicLayout1;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interior_hallorland);

		// mTextPicLayout.listener = this;

		if (savedInstanceState != null
				&& savedInstanceState.getInt("layout") != 0) {
			IrishreloAccess.camerapic = ((ImageView) (((TextPicLayout) findViewById(savedInstanceState
					.getInt("layout")))).findViewById(savedInstanceState.getInt("view")));
		}

		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if (!editJobId.equals("0"))
			renderData();

		Log.v(getPackageName().getClass().getSimpleName(), "On Create");

		// hall
		mTextPicLayout1 = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug);
		mTextPicLayout1.et_text1
				.setHint(R.string.interior_carpets_and_rugs_title);
		mTextPicLayout1.tv_text1
				.setText(R.string.interior_carpets_and_rugs_title);
		mTextPicLayout1.et_text1.setText("test hall curpet Rug");

		// mTextPicLayout1.iv_pic1.setOnClickListener(this);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor);
		mTextPicLayout.et_text1.setHint(R.string.interior_wooden_floors_title);
		mTextPicLayout.tv_text1.setText(R.string.interior_wooden_floors_title);
		mTextPicLayout.et_text1.setText("test hall wooden_floors");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_banister_stairway);
		mTextPicLayout.et_text1.setHint(R.string.banisters_stairway_title);
		mTextPicLayout.tv_text1.setText(R.string.banisters_stairway_title);

		mTextPicLayout.et_text1.setText("test hall banisters_stairway");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_walls_celing);
		mTextPicLayout.et_text1.setHint(R.string.wall_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.wall_celling_title);

		mTextPicLayout.et_text1.setText("test hall wall_celling ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_lighting_bulbs_shades);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		mTextPicLayout.et_text1.setText("test hall light_bulb_shades ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_handles_doors_fininsh);
		mTextPicLayout.et_text1.setHint(R.string.handle_door_finish_title);
		mTextPicLayout.tv_text1.setText(R.string.handle_door_finish_title);

		mTextPicLayout.et_text1.setText("test hall door_finish ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_skirting_boards_radiator);
		mTextPicLayout.et_text1
				.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1
				.setText(R.string.skirting_boards_radiator_title);

		mTextPicLayout.et_text1.setText("test hall skirting_boards_radiator ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_doors_locks_keys);
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		mTextPicLayout.et_text1.setText("test hall doors_locks_keys ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_window_locks_keys_handles);
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		mTextPicLayout.et_text1.setText("test hall windows_keys_locks ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_table_chair_coatstand);
		mTextPicLayout.et_text1
				.setHint(R.string.table_chair_coatstand_mirror_title);
		mTextPicLayout.tv_text1
				.setText(R.string.table_chair_coatstand_mirror_title);

		mTextPicLayout.et_text1.setText("test hall chair_coatstand_mirror ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_headset);
		mTextPicLayout.et_text1.setHint(R.string.phone_headset_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_headset_title);

		mTextPicLayout.et_text1.setText("test hall phone_headset ");

		// hall

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_directory);
		mTextPicLayout.et_text1.setHint(R.string.phone_directory_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_directory_title);

		mTextPicLayout.et_text1.setText("test hall phone_directory ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		mTextPicLayout.et_text1.setText("test hall curtains_blinds ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		mTextPicLayout.et_text1.setText("test hall miscellaneous ");

		// Pre depurture section start

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_carpetRug);
		mTextPicLayout.et_text1
				.setHint(R.string.interior_carpets_and_rugs_title);
		mTextPicLayout.tv_text1
				.setText(R.string.interior_carpets_and_rugs_title);

		mTextPicLayout.et_text1.setText("test hall pred carpets_and_rugs ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor);
		mTextPicLayout.et_text1.setHint(R.string.interior_wooden_floors_title);
		mTextPicLayout.tv_text1.setText(R.string.interior_wooden_floors_title);

		mTextPicLayout.et_text1.setText("test hall pred wooden_floors ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_banister_stairway);
		mTextPicLayout.et_text1.setHint(R.string.banisters_stairway_title);
		mTextPicLayout.tv_text1.setText(R.string.banisters_stairway_title);

		mTextPicLayout.et_text1.setText("test hall pred banisters_stairway ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_walls_celing);
		mTextPicLayout.et_text1.setHint(R.string.wall_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.wall_celling_title);

		mTextPicLayout.et_text1.setText("test hall pred wall_celling ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_lighting_bulbs_shades);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		mTextPicLayout.et_text1.setText("test hall pred light_bulb_shades ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_handles_doors_fininsh);
		mTextPicLayout.et_text1.setHint(R.string.handle_door_finish_title);
		mTextPicLayout.tv_text1.setText(R.string.handle_door_finish_title);

		mTextPicLayout.et_text1.setText("test hall pred handle_door_finish ");

		// pre depurture
		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_skirting_boards_radiator);
		mTextPicLayout.et_text1
				.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1
				.setText(R.string.skirting_boards_radiator_title);

		mTextPicLayout.et_text1.setText("test hall pred skirting_boards ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_doors_locks_keys);
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		mTextPicLayout.et_text1.setText("test hall pred doors_locks_keys ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_window_locks_keys_handles);
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		mTextPicLayout.et_text1.setText("test hall pred indows_keys_locks ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_table_chair_coatstand);
		mTextPicLayout.et_text1
				.setHint(R.string.table_chair_coatstand_mirror_title);
		mTextPicLayout.tv_text1
				.setText(R.string.table_chair_coatstand_mirror_title);

		mTextPicLayout.et_text1
				.setText("test hall pred table_chair_coatstand_mirror ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_headset);
		mTextPicLayout.et_text1.setHint(R.string.phone_headset_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_headset_title);

		mTextPicLayout.et_text1.setText("test hall pred phone_headset ");

		/*
		 * mTextPicLayout1.iv_pic1.setOnClickListener(this);
		 * mTextPicLayout1.iv_pic2.setOnClickListener(this);
		 * mTextPicLayout1.iv_pic3.setOnClickListener(this);
		 * mTextPicLayout1.iv_pic4.setOnClickListener(this);
		 */

		/*
		 * mTextPicLayout.iv_pic1.setOnClickListener(this);
		 * mTextPicLayout.iv_pic2.setOnClickListener(this);
		 * mTextPicLayout.iv_pic3.setOnClickListener(this);
		 * mTextPicLayout.iv_pic4.setOnClickListener(this);
		 */

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_directory);
		mTextPicLayout.et_text1.setHint(R.string.phone_directory_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_directory_title);

		mTextPicLayout.et_text1.setText("test hall pred phone_directory ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		mTextPicLayout.et_text1.setText("test hall pred curtains_blinds ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		mTextPicLayout.et_text1.setText("test hall pred miscellaneous ");

		// depurture section start

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_carpetRug);
		mTextPicLayout.et_text1
				.setHint(R.string.interior_carpets_and_rugs_title);
		mTextPicLayout.tv_text1
				.setText(R.string.interior_carpets_and_rugs_title);

		mTextPicLayout.et_text1.setText("test hall deprt carpets_and_rug ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor);
		mTextPicLayout.et_text1.setHint(R.string.interior_wooden_floors_title);
		mTextPicLayout.tv_text1.setText(R.string.interior_wooden_floors_title);

		mTextPicLayout.et_text1.setText("test hall deprt wooden_floor ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_banister_stairway);
		mTextPicLayout.et_text1.setHint(R.string.banisters_stairway_title);
		mTextPicLayout.tv_text1.setText(R.string.banisters_stairway_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt banisters_stairway_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_walls_celing);
		mTextPicLayout.et_text1.setHint(R.string.wall_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.wall_celling_title);

		mTextPicLayout.et_text1.setText("test hall deprt wall_celling_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_lighting_bulbs_shades);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt light_bulb_shades_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_handles_doors_fininsh);
		mTextPicLayout.et_text1.setHint(R.string.handle_door_finish_title);
		mTextPicLayout.tv_text1.setText(R.string.handle_door_finish_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt handle_door_finish_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_skirting_boards_radiator);
		mTextPicLayout.et_text1
				.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1
				.setText(R.string.skirting_boards_radiator_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt skirting_boards_radiator_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_doors_locks_keys);
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt doors_locks_keys_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_window_locks_keys_handles);
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt windows_keys_locks_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_table_chair_coatstand);
		mTextPicLayout.et_text1
				.setHint(R.string.table_chair_coatstand_mirror_title);
		mTextPicLayout.tv_text1
				.setText(R.string.table_chair_coatstand_mirror_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt table_chair_coatstand_mirror_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_headset);
		mTextPicLayout.et_text1.setHint(R.string.phone_headset_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_headset_title);

		mTextPicLayout.et_text1.setText("test hall deprt phone_headset_title ");

		/*
		 * mTextPicLayout = (TextPicLayout)findViewById(R.id.);
		 * mTextPicLayout.et_text1.setHint(R.string.);
		 * mTextPicLayout.tv_text1.setText(R.string.);
		 * mTextPicLayout.iv_pic1.setOnClickListener(this);
		 * mTextPicLayout.iv_pic2.setOnClickListener(this);
		 * mTextPicLayout.iv_pic3.setOnClickListener(this);
		 * mTextPicLayout.iv_pic4.setOnClickListener(this);
		 */
		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_directory);
		mTextPicLayout.et_text1.setHint(R.string.phone_directory_title);
		mTextPicLayout.tv_text1.setText(R.string.phone_directory_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt phone_directory_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		mTextPicLayout.et_text1
				.setText("test hall deprt curtains_blinds_title ");

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		mTextPicLayout.et_text1.setText("test hall deprt miscellaneous_title ");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_pic1:

			uploadPic(v);
			break;
		case R.id.iv_pic2:
			uploadPic(v);
			break;
		case R.id.iv_pic3:
			uploadPic(v);
			break;
		case R.id.iv_pic4:
			uploadPic(v);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		menu.removeItem(R.id.action_interior);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_commgoto:
			startActivity(new Intent(this, CommonGoToActivity.class));
			finish();
			return true;
		case R.id.action_utility:
			startActivity(new Intent(this, Utilities.class));
			finish();
			return true;
		case R.id.action_interior:
			startActivity(new Intent(this, Interior.class));
			finish();
			return true;
		case R.id.action_exterior:
			startActivity(new Intent(this, Exterior.class));
			finish();
			return true;
		case R.id.action_home:
			startActivity(new Intent(this, IrishreloLunch.class));
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void uploadPic(View v) {
		IrishreloAccess.captbyCam = IrishreloAccess.imagePath = "";
		camerapic = (ImageView) v;
		final Dialog dia = new Dialog(this);
		try {
			if (camerapic.getTag().toString().equals("")) {
				dia.setContentView(R.layout.custom);
				dia.setTitle("Get a pic");
			} else {
				dia.setContentView(R.layout.popup);
				dia.setTitle("Show/Get a pic");
				Uri mUrl;
				mUrl = Uri.parse(camerapic.getTag().toString());
				((ImageView) dia.findViewById(R.id.popuoimg)).setImageURI(mUrl);
			}
		} catch (Exception e) {
			dia.setContentView(R.layout.custom);
			dia.setTitle("Get a pic");
		}
		dia.show();
		Button close = ((Button) dia.findViewById(R.id.back));
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia.dismiss();
			}
		});

		((Button) dia.findViewById(R.id.browse_file))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						dia.dismiss();
						Intent intent = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent, 1);

					}
				});

		((Button) dia.findViewById(R.id.click_pic))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dia.dismiss();
						// TODO Auto-generated method stub
						String imageName = (new Date()).getTime() + ".jpg";
						String path = null;
						if (android.os.Environment.getExternalStorageState()
								.equals(android.os.Environment.MEDIA_MOUNTED)) {
							path = Environment.getExternalStorageDirectory()
									.getPath() + "/irishrelo/";

						} else {
							path = Environment.getRootDirectory().getPath()
									+ "/irishrelo/";
						}
						IrishreloAccess.captbyCam = path + imageName;
						File wallpaperDirectory = new File(path);
						// have the object build the directory structure, if
						// needed.
						wallpaperDirectory.mkdirs();
						Intent intentImg = new Intent(
								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);// (Exterior.this,CameraActivity.class);
						File file = new File(wallpaperDirectory, imageName);
						Uri outputFileUri = Uri.fromFile(file);
						intentImg.putExtra(MediaStore.EXTRA_OUTPUT,
								outputFileUri);
						startActivityForResult(intentImg, 2);

					}
				});
	}

	public void onPicUpload(View v) {
		IrishreloAccess.captbyCam = IrishreloAccess.imagePath = "";
		camerapic = (ImageView) v;
		final Dialog dia = new Dialog(this);
		try {
			if (camerapic.getTag().toString().equals("")) {
				dia.setContentView(R.layout.custom);
				dia.setTitle("Get a pic");
			} else {
				dia.setContentView(R.layout.popup);
				dia.setTitle("Show/Get a pic");
				Uri mUrl;
				mUrl = Uri.parse(camerapic.getTag().toString());
				((ImageView) dia.findViewById(R.id.popuoimg)).setImageURI(mUrl);
			}
		} catch (Exception e) {
			dia.setContentView(R.layout.custom);
			dia.setTitle("Get a pic");
		}
		dia.show();
		Button close = ((Button) dia.findViewById(R.id.back));
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia.dismiss();
			}
		});

		((Button) dia.findViewById(R.id.browse_file))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						dia.dismiss();
						Intent intent = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent, 1);

					}
				});

		((Button) dia.findViewById(R.id.click_pic))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dia.dismiss();
						// TODO Auto-generated method stub
						String imageName = (new Date()).getTime() + ".jpg";
						String path = null;
						if (android.os.Environment.getExternalStorageState()
								.equals(android.os.Environment.MEDIA_MOUNTED)) {
							path = Environment.getExternalStorageDirectory()
									.getPath() + "/irishrelo/";

						} else {
							path = Environment.getRootDirectory().getPath()
									+ "/irishrelo/";
						}
						IrishreloAccess.captbyCam = path + imageName;
						File wallpaperDirectory = new File(path);
						// have the object build the directory structure, if
						// needed.
						wallpaperDirectory.mkdirs();
						Intent intentImg = new Intent(
								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);// (Exterior.this,CameraActivity.class);
						File file = new File(wallpaperDirectory, imageName);
						Uri outputFileUri = Uri.fromFile(file);
						intentImg.putExtra(MediaStore.EXTRA_OUTPUT,
								outputFileUri);
						startActivityForResult(intentImg, 2);

					}
				});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(getPackageName().getClass().getSimpleName(), "On Resume");
		if (!IrishreloAccess.imagePath.equals(""))
			showImage(IrishreloAccess.imagePath);
		else if (!IrishreloAccess.captbyCam.equals(""))
			showImage(IrishreloAccess.captbyCam);
	}

	public void showImage(String path) {
		try {
			Bitmap bmImg = null;
			File imageFile = new File(path);
			if (imageFile.exists()) {
				bmImg = IrishreloAccess.getResizedImage(imageFile);
				IrishreloAccess.camerapic.setImageBitmap(bmImg);
				IrishreloAccess.camerapic.setTag(imageFile.getAbsolutePath());
				/*
				 * camerapic.setImageBitmap(bmImg);
				 * camerapic.setTag(imageFile.getAbsolutePath());
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v("Interior java", "On Pause Called");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("layout", TextPicLayout.clickedLayout);
		savedInstanceState.putInt("view", TextPicLayout.clickedView);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(getPackageName().getClass().getSimpleName(), "On Destroy");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		 //mTextPicLayout1.onActivityResult(requestCode, resultCode, data);
		
		if(getSupportFragmentManager().findFragmentByTag("frag") instanceof Picture_4)
			getSupportFragmentManager().findFragmentByTag("frag").onActivityResult(requestCode, resultCode, data);


		/*try {
			if (requestCode == 1 && resultCode == Activity.RESULT_OK
					&& null != data) {
				IrishreloAccess.imagePath = getRealPathFromURI(data.getData());
				showImage(IrishreloAccess.imagePath);
			}
			if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
				if ((new File(IrishreloAccess.captbyCam)).exists()) {
					showImage(IrishreloAccess.captbyCam);
				}
			}
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// Toast.makeText(this, "Image saved to:\n"
			// +e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

		}*/

		/*
		 * try { if (requestCode == 1 && resultCode == RESULT_OK && null !=
		 * data) { IrishreloAccess.imagePath =
		 * getRealPathFromURI(data.getData());
		 * showImage(IrishreloAccess.imagePath); } if (requestCode == 2 &&
		 * resultCode == RESULT_OK) { if((new
		 * File(IrishreloAccess.captbyCam)).exists()) {
		 * showImage(IrishreloAccess.captbyCam); } } // }catch (Exception e) {
		 * // TODO Auto-generated catch block //e.printStackTrace();
		 * //Toast.makeText(this, "Image saved to:\n" +e.getLocalizedMessage(),
		 * Toast.LENGTH_LONG).show();
		 * 
		 * }
		 */

	}

	public String getRealPathFromURI(Uri contentUri) {
		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, // Which columns to
														// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public void showImageInIV_NEW(String path, ImageView iv) {
		ImageView setImgpic = iv;
		Uri mUrl;
		Bitmap bmImg = null;
		try {
			if (path != null && path.toString().length() != 0) {

				File imageFile = new File(path);
				if (imageFile.exists()) {
					/*
					 * mUrl= Uri.parse(path); setImgpic.setImageURI(mUrl);
					 * setImgpic.setTag(path);
					 */
					bmImg = IrishreloAccess.getResizedImage(imageFile);
					setImgpic.setImageBitmap(bmImg);
					setImgpic.setTag(imageFile.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showImageInIV(String path, int ID) {
		ImageView setImgpic = (ImageView) findViewById(ID);
		Uri mUrl;
		Bitmap bmImg = null;
		try {
			if (path != null && path.toString().length() != 0) {

				File imageFile = new File(path);
				if (imageFile.exists()) {
					/*
					 * mUrl= Uri.parse(path); setImgpic.setImageURI(mUrl);
					 * setImgpic.setTag(path);
					 */
					bmImg = IrishreloAccess.getResizedImage(imageFile);
					setImgpic.setImageBitmap(bmImg);
					setImgpic.setTag(imageFile.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onElementClick(View v) {
		switch (v.getId()) {
		case R.id.backtoprev:
			backTheprocess(v);
			break;
		case R.id.proctonext:
			gotoNext(v);
			break;
		case R.id.savephase:
			saveData(v);
			break;
		default:
			break;
		}
	}

	void backTheprocess(View v) {
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, CommonGoToActivity.class));
		finish();
	}

	void gotoNext(View v) {
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, InteriorCloakRoom.class));
		// startActivity(new Intent(this,InteriorRoomFive.class));
		finish();
	}

	public void saveData(View v) {
		long Success = -1;
		Log.v("editJobId", editJobId + "");
		ContentValues contentValues = new ContentValues();
		db.openDataBase();

		/**
		 * CheckIn CarpetRag
		 */
		contentValues = new ContentValues();
		try {
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).mButton.getTag().toString());
			Log.v("for",
					"hall_landing_check_in_carpetRugs Joy"
							+ (((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).et_text1
									.getText().toString()));
		} catch (Exception e) {
			// Log.v("for", "hall_landing_check_in_carpetRugs"+
			// ((EditText)findViewById(R.id.hall_landing_check_in_carpetRugs)).getText().toString());
			

			// Log.v("for", "hall_landing_check_in_carpetRugs Joy"+
			// (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).et_text1.getText().toString()));
		}
		/**
		 * PreDept CarpetRag
		 */
		try {
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_carpetRug)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_carpetRug)).mButton.getTag().toString());
		} catch (Exception e) {
			Log.v("for", "check_in_carpetRugs");
		}
		
		/**
		 * 
		 */
		try {
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_carpetRug)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_carpetRug)).getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_pre_departure");

		}
		
		Success = db.MyDB().update("HALL_OR_LAND", contentValues,
				"jobid =" + editJobId + " AND inspect_type = 'carpet_or_rugs'",
				null);
		Log.v("updated for", "HALL_OR_LAND" + Success);

		

// wooden_floors
		contentValues = new ContentValues();
		try {
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).getTag().toString());


		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "check_in_wooden_floor_editText");
		}
		
		try {
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).et_text1
									.getText().toString());
			
			 contentValues.put("predepart_img",((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

		}
		
		try {
			// contentValues.put("depart_comm",
			// ((EditText)findViewById(R.id.hall_landing_departure_wooden_floor_editText)).getText().toString());
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).et_text1
									.getText().toString());
			 contentValues.put("depart_img",((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_departure_wooden_floor_editText");

		}
		
		Success = db.MyDB().update("HALL_OR_LAND", contentValues,
				"jobid =" + editJobId + " AND inspect_type = 'wooden_floors'",
				null);
		Log.v("updated for", "wooden_floor" + Success);

		

		// banister_or_stairway
		
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_banister_stairway)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_banister_stairway)).getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_banister_stairway_editText");
		}
		
		try {
			// contentValues.put("predepart_comm",
			// ((EditText)findViewById(R.id.hall_landing_pre_departure_banister_stairway_editText)).getText().toString());

			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_banister_stairway)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_banister_stairway)).getTag().toString());

		} catch (Exception e) {
			Log.v("for",
					"hall_landing_pre_departure_banister_stairway_editText");
		}

	
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_banister_stairway)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_banister_stairway)).getTag().toString());


		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_banister_stairway_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'banister_or_stairway'", null);
		Log.v("updated for", "banister_or_stairway" + Success);

		contentValues = new ContentValues();

		// walls_or_ceiling

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_walls_celing)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_walls_celing)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_walls_celling_editText");
		}
		
		try {  
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_walls_celing)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_walls_celing)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_walls_celling_editText");
		}

		
		try {
			// contentValues.put("depart_comm",
			// ((EditText)findViewById(R.id.hall_landing_departure_walls_celling_editText)).getText().toString());
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_walls_celing)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_walls_celing)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_walls_celling_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'walls_or_ceiling'", null);
		Log.v("updated for", "walls_or_ceiling" + Success);

// lighting_or_bulbs_or_sheds

		contentValues = new ContentValues();

		try {
			// contentValues.put("checkin_comm",
			// ((EditText)findViewById(R.id.hall_landing_check_in_lighting_bulbs_shades_editText)).getText().toString());
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_lighting_bulbs_shades)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_lighting_bulbs_shades)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_lighting_bulbs_shades_editText");
		}
		
		try {
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_lighting_bulbs_shades)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_lighting_bulbs_shades)).getTag().toString());
		} catch (Exception e) {
			Log.v("for",
					"hall_landing_pre_departure_lighting_bulbs_shades_editText");
		}

		
		try {
		
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_lighting_bulbs_shades)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_lighting_bulbs_shades)).getTag().toString());


		} catch (Exception e) {
			Log.v("for",
					"hall_landing_departure_lighting_bulbs_shades_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'lighting_or_bulbs_or_sheds'",
				null);
		Log.v("updated for", "lighting_or_bulbs_or_sheds" + Success);

// handles_or_doorfinish
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_handles_doors_fininsh)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_handles_doors_fininsh)).getTag().toString());


		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_handles_doors_fininsh_editText");
		}
		
		
		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_handles_doors_fininsh)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_handles_doors_fininsh)).getTag().toString());
		} catch (Exception e) {
			Log.v("for",
					"hall_landing_departure_handles_doors_fininsh_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'handles_or_doorfinish'", null);
		Log.v("updated for", "handles_or_doorfinish" + Success);

		// skirting_boards_or_radiator
		contentValues = new ContentValues();

		try {
			// contentValues.put("checkin_comm",
			// ((EditText)findViewById(R.id.hall_landing_check_in_skirting_boards_radiator_editText)).getText().toString());
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_skirting_boards_radiator)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_skirting_boards_radiator)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for",
					"hall_landing_check_in_skirting_boards_radiator_editText");
		}
		
		try {
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_skirting_boards_radiator)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_skirting_boards_radiator)).getTag().toString());

		} catch (Exception e) {
			Log.v("for",
					"hall_landing_pre_departure_skirting_boards_radiator_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_skirting_boards_radiator)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_skirting_boards_radiator)).getTag().toString());

		} catch (Exception e) {
			Log.v("for",
					"hall_landing_departure_skirting_boards_radiator_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'skirting_boards_or_radiator'",
				null);
		Log.v("updated for", "skirting_boards_or_radiator" + Success);
		
		

// doors_or_locks_or_keys_or_handles
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_doors_locks_keys)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_doors_locks_keys)).getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_doors_locks_keys_editText");
		}
		
		try {
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_doors_locks_keys)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_doors_locks_keys)).getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_doors_locks_keys_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_doors_locks_keys)).et_text1
									.getText().toString());

			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_doors_locks_keys)).getTag().toString());
		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_doors_locks_keys_editText");
		}

	
		Success = db
				.MyDB()
				.update("HALL_OR_LAND",
						contentValues,
						"jobid ="
								+ editJobId
								+ " AND inspect_type = 'doors_or_locks_or_keys_or_handles'",
						null);
		Log.v("updated for", "doors_or_locks_or_keys_or_handles" + Success);

// windows_or_locks_or_keys_or_handles
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_window_locks_keys_handles)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_window_locks_keys_handles)).getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for",
					"hall_landing_check_in_window_locks_keys_handles_editText");
		}
		
		try {
			
			contentValues.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_window_locks_keys_handles)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_window_locks_keys_handles)).getTag().toString());

		} catch (Exception e) {
			Log.v("for",
					"hall_landing_pre_departure_window_locks_keys_handles_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_window_locks_keys_handles)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_window_locks_keys_handles)).getTag().toString());
		} catch (Exception e) {
			Log.v("for",
					"hall_landing_departure_window_locks_keys_handles_editText");
		}

		
		Success = db
				.MyDB()
				.update("HALL_OR_LAND",
						contentValues,
						"jobid ="
								+ editJobId
								+ " AND inspect_type = 'windows_or_locks_or_keys_or_handles'",
						null);
		Log.v("updated for", "windows_or_locks_or_keys_or_handles" + Success);

		
		
		
		
// table_or_chair_coatstand_mirror
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_table_chair_coatstand)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_table_chair_coatstand)).getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_table_chair_coatstand_editText");
		}
		
		try {
			
			// ((EditText)findViewById(R.id.hall_landing_pre_departure_table_chair_coatstand_editText)).getText().toString());
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_table_chair_coatstand)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_table_chair_coatstand)).getTag().toString());
		} catch (Exception e) {
			Log.v("for",
					"hall_landing_pre_departure_table_chair_coatstand_editText");
		}

		
		try {
			// contentValues.put("depart_comm",
			// ((EditText)findViewById(R.id.hall_landing_departure_table_chair_coatstand_editText)).getText().toString());
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_table_chair_coatstand)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_table_chair_coatstand)).getTag().toString());

			
		} catch (Exception e) {
			Log.v("for",
					"hall_landing_departure_table_chair_coatstand_editText");
		}

		
		Success = db
				.MyDB()
				.update("HALL_OR_LAND",
						contentValues,
						"jobid ="
								+ editJobId
								+ " AND inspect_type = 'table_or_chair_coatstand_mirror'",
						null);
		Log.v("updated for", "table_or_chair_coatstand_mirror" + Success);

		// phone_handset

		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_headset)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_headset)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_phon_headset_editText");
		}
		
		try {
			
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_headset)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_headset)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_phon_headset_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_headset)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_headset)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_phon_headset_editText");
		}

		
		Success = db.MyDB().update("HALL_OR_LAND", contentValues,
				"jobid =" + editJobId + " AND inspect_type = 'phone_handset'",
				null);
		Log.v("updated for", "phone_handset" + Success);

	// phone_directories

		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_directory)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_directory)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_phon_directory_editText");
		}
		
		try {
			// contentValues.put("predepart_comm",
			// ((EditText)findViewById(R.id.hall_landing_pre_departure_phon_directory_editText)).getText().toString());
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_directory)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_directory)).getTag().toString());
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_phon_directory_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_directory)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_directory)).getTag().toString());


		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_phon_directory_editText");
		}

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'phone_directories'", null);
		Log.v("updated for", "phone_directories" + Success);

		
		
		
// curtains_or_blinds
		contentValues = new ContentValues();

		try {
			
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds)).getTag().toString());
			Log.v("for",
					"hall_landing_check_in_curtains_blinds_editText"
							+ ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds)).et_text1
									.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_curtains_blinds_editText");
		}
		
		try {
			// contentValues.put("predepart_comm",
			// ((EditText)findViewById(R.id.hall_landing_pre_departure_curtains_blinds_editText)).getText().toString());
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_curtains_blinds)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_curtains_blinds)).getTag().toString());
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_curtains_blinds_editText");
		}

		
		try {
			
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_curtains_blinds)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_curtains_blinds)).getTag().toString());
		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_curtains_blinds_editText");
		}

		
		

		
		Success = db.MyDB().update(
				"HALL_OR_LAND",
				contentValues,
				"jobid =" + editJobId
						+ " AND inspect_type = 'curtains_or_blinds'", null);
		Log.v("updated for", "curtains_or_blinds" + Success);

		// miscellaneous
		contentValues = new ContentValues();

		try {
			// contentValues.put("checkin_comm",
			// ((EditText)findViewById(R.id.hall_landing_check_in_miscellaneous_editText)).getText().toString());
			contentValues
					.put("checkin_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_miscellaneous)).et_text1
									.getText().toString());
			contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous)).getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_miscellaneous_editText");
		}
		
		try {
			// contentValues.put("predepart_comm",
			// ((EditText)findViewById(R.id.hall_landing_pre_departure_miscellaneous_editText)).getText().toString());
			contentValues
					.put("predepart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous)).et_text1
									.getText().toString());
			contentValues.put("predepart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_miscellaneous_editText");
		}

		
		try {
			// contentValues.put("depart_comm",
			// ((EditText)findViewById(R.id.hall_landing_departure_miscellaneous_editText)).getText().toString());
			contentValues
					.put("depart_comm",
							((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_miscellaneous)).et_text1
									.getText().toString());
			contentValues.put("depart_img", ((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_miscellaneous)).getTag().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_miscellaneous_editText");
		}

		
		Success = db.MyDB().update("HALL_OR_LAND", contentValues,
				"jobid =" + editJobId + " AND inspect_type = 'miscellaneous'",
				null);
		Log.v("updated for", "miscellaneous" + Success);

		// action_plan_if_required
		contentValues = new ContentValues();
		try {
			contentValues
					.put("checkin_comm",
							((EditText) findViewById(R.id.hall_landing_check_in_action_plan_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.e("for", "hall_landing_check_in_action_plan_editText");
		}
		contentValues.put("checkin_img", "");
		try {
			contentValues
					.put("predepart_comm",
							((EditText) findViewById(R.id.hall_landing_pre_departure_action_plan_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_action_plan_editText");
		}
		contentValues.put("predepart_img", "");
		try {
			contentValues
					.put("depart_comm",
							((EditText) findViewById(R.id.hall_landing_departure_action_plan_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_action_plan_editText");
		}
		contentValues.put("depart_img", "");
		Success = db
				.MyDB()
				.update("HALL_OR_LAND",
						contentValues,
						"jobid ="
								+ editJobId
								+ " AND inspect_type = 'action_plan_if_required'",
						null);
		Log.v("updated for", "action_plan_if_required" + Success);

		// follow_up
		contentValues = new ContentValues();
		try {
			contentValues
					.put("checkin_comm",
							((EditText) findViewById(R.id.hall_landing_check_in_follow_up_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.e("for",
					"hall_landing_check_in_follow_up_editText"
							+ ((EditText) findViewById(R.id.hall_landing_check_in_follow_up_editText))
									.getText().toString());
		}
		contentValues.put("checkin_img", "");
		try {
			contentValues.put("predepart_comm",
					((EditText) findViewById(R.id.check_in_follow_up_editText))
							.getText().toString());

		} catch (Exception e) {
			Log.v("for", "check_in_follow_up_editText");
		}
		contentValues.put("predepart_img", "");
		try {
			contentValues
					.put("depart_comm",
							((EditText) findViewById(R.id.hall_landing_departure_follow_up_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_follow_up_editText");
		}
		contentValues.put("depart_img", "");
		Success = db.MyDB()
				.update("HALL_OR_LAND",
						contentValues,
						"jobid =" + editJobId
								+ " AND inspect_type = 'follow_up'", null);
		Log.v("updated for", "follow_up" + Success);

		// final_comments
		contentValues = new ContentValues();
		try {
			contentValues
					.put("checkin_comm",
							((EditText) findViewById(R.id.hall_landing_check_in_final_comments_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.e("for", "hall_landing_check_in_final_comments_editText");
		}
		contentValues.put("checkin_img", "");
		try {
			contentValues
					.put("predepart_comm",
							((EditText) findViewById(R.id.hall_landing_pre_departure_comments_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_comments_editText");
		}
		contentValues.put("predepart_img", "");
		try {
			contentValues
					.put("depart_comm",
							((EditText) findViewById(R.id.hall_landing_departure_comments_editText))
									.getText().toString());

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_comments_editText");
		}
		contentValues.put("depart_img", "");
		Success = db.MyDB().update("HALL_OR_LAND", contentValues,
				"jobid =" + editJobId + " AND inspect_type = 'final_comments'",
				null);
		Log.v("updated for", "final_comments" + Success);
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "HALL_OR_LAND",
				"tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db, "HALL_OR_LAND",
				editJobId);

		try {
			// if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true,
						"HALL_OR_LAND", editJobId, "create/update",
						InteriorCloakRoom.class);
				s2sv.frontEndSend();
				// }else{
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}



	
	void renderData() {
		Cursor cursor = SelectDb.getPhaseByData(db, "HALL_OR_LAND", editJobId,
				false, true, false);
		if (cursor != null) {
			Log.v("count", "" + cursor.getCount());
			for (int j = 0; j < cursor.getCount(); j++) {
				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("carpet_or_rugs")) {
					// textPicLayout_hall_landing_check_in_carpetRug
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_carpetRug)).mButton.setTag(cursor.getString(cursor
							.getColumnIndex("checkin_img")));

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_carpetRug)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_carpetRug)).setTag(cursor.getString(cursor
							.getColumnIndex("predepart_img")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_carpetRug)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_carpetRug)).mButton.setTag(cursor
							.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("wooden_floors")) {

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).mButton.setTag(cursor.getString(cursor
							.getColumnIndex("checkin_img")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).mButton.setTag(cursor.getString(cursor
							.getColumnIndex("predepart_img")));

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).mButton.setTag(cursor
					.getString(cursor.getColumnIndex("depart_img")));
					

					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("banister_or_stairway")) {
	
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_banister_stairway)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_banister_stairway)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_banister_stairway)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_banister_stairway)).mButton.setTag(
							cursor.getString(cursor
							.getColumnIndex("predepart_img")));

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_banister_stairway)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_banister_stairway)).mButton.setTag(cursor
							.getString(cursor.getColumnIndex("depart_img")));

					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("walls_or_ceiling")) {
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_walls_celing)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_walls_celing)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_walls_celing)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_walls_celing)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_walls_celing)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_walls_celing)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					
					
					

					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("lighting_or_bulbs_or_sheds")) {
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_lighting_bulbs_shades)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_lighting_bulbs_shades)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_lighting_bulbs_shades)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					

										cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("handles_or_doorfinish")) {
					

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_handles_doors_fininsh)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_handles_doors_fininsh)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_handles_doors_fininsh)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

				
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("skirting_boards_or_radiator")) {
					

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_skirting_boards_radiator)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_skirting_boards_radiator)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_skirting_boards_radiator)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

				

					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("doors_or_locks_or_keys_or_handles")) {
					

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_doors_locks_keys)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_doors_locks_keys)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_doors_locks_keys)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));


					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("windows_or_locks_or_keys_or_handles")) {
					/*
					 * ((EditText)findViewById(R.id.
					 * hall_landing_check_in_window_locks_keys_handles_editText
					 * )).setText(cursor.getString(cursor.getColumnIndex(
					 * "checkin_comm"))); ((EditText)findViewById(R.id.
					 * hall_landing_pre_departure_window_locks_keys_handles_editText
					 * )).setText(cursor.getString(cursor.getColumnIndex(
					 * "predepart_comm"))); ((EditText)findViewById(R.id.
					 * hall_landing_departure_window_locks_keys_handles_editText
					 * )).setText(cursor.getString(cursor.getColumnIndex(
					 * "depart_comm")));
					 */

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_window_locks_keys_handles)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_window_locks_keys_handles)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_window_locks_keys_handles)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("table_or_chair_coatstand_mirror")) {
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_table_chair_coatstand)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_table_chair_coatstand)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_table_chair_coatstand)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					

					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("phone_handset")) {
					

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_headset)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_headset)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_headset)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("phone_directories")) {
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_directory)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_directory)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_directory)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("curtains_or_blinds")) {
					
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_curtains_blinds)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_curtains_blinds)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_curtains_blinds)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_curtains_blinds)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_curtains_blinds)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("miscellaneous")) {
					

					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_miscellaneous)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_check_in_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_pre_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_miscellaneous)).et_text1
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					((TextPicLayout) findViewById(R.id.textPicLayout_hall_landing_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));

					
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("action_plan_if_required")) {
					((EditText) findViewById(R.id.hall_landing_check_in_action_plan_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((EditText) findViewById(R.id.hall_landing_pre_departure_action_plan_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((EditText) findViewById(R.id.hall_landing_departure_action_plan_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("follow_up")) {
					((EditText) findViewById(R.id.hall_landing_check_in_follow_up_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((EditText) findViewById(R.id.check_in_follow_up_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((EditText) findViewById(R.id.hall_landing_departure_follow_up_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}

				if (cursor.getString(cursor.getColumnIndex("inspect_type"))
						.equals("final_comments")) {
					((EditText) findViewById(R.id.hall_landing_check_in_final_comments_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("checkin_comm")));
					((EditText) findViewById(R.id.hall_landing_pre_departure_comments_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("predepart_comm")));
					((EditText) findViewById(R.id.hall_landing_departure_comments_editText))
							.setText(cursor.getString(cursor
									.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
			}
		}
	}
}