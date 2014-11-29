package irishreloreportgen.activity.main.interior;

import irishreloreportgen.activity.capturesignature.CaptureSign;
import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.exterior.Exterior;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.activity.main.utilities.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import customview.TextPicLayout;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class InteriorLiving extends ActionBarActivity implements OnClickListener {
	public static final int SIGNATURE_ACTIVITY = 3;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	ImageView camerapic;
	RadioGroup radioyesnoGroup;
	int selectedId;
	RadioButton radioyesnoButton;
	TextPicLayout mTextPicLayout;
	String strtest="livingroom  test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.living_room);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");

		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if (!editJobId.equals("0"))
			renderData();
		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		
		mTextPicLayout.et_text1.setText(strtest);

	

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furniture_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1
				.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1
				.setText(R.string.paint_paper_walls_celling_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1
				.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1
				.setText(R.string.skirting_boards_radiator_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furniture_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds);
    	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		
		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1
				.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1
				.setText(R.string.paint_paper_walls_celling_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1
				.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1
				.setText(R.string.skirting_boards_radiator_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);

mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furniture_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

		
		
		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);

		mTextPicLayout.et_text1.setText(strtest);

		mTextPicLayout = (TextPicLayout) findViewById(R.id.textPicLayout_living_room_departure_miscellaneous);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		ArrayList<View> mArrayList = getAllChildren(getWindow().getDecorView().findViewById(android.R.id.content));
		Log.v(getClass().getSimpleName(), mArrayList.toString());
		
		/*for(int i=0 ; i<mArrayList.size(); i++)
		{	Cursor cursor = SelectDb.getPhaseByData(db, "LIVING_ROOM", editJobId, false, true, false);
			switch (mArrayList.get(i).getId()) {
			case R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs:
				if (cursor != null) {
					Log.v("count", "" + cursor.getCount());
					for (int j = 0; j < cursor.getCount(); j++)
					{
						
						if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring_or_carpet_or_rugs"))
						{
							((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
							((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
							((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
							
							((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
						}}}
				
				break;
			case R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs:
				if (cursor != null) {
					Log.v("count", "" + cursor.getCount());
					for (int j = 0; j < cursor.getCount(); j++)
					{
						
						if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring_or_carpet_or_rugs"))
						{
							((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
							((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
							((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
							
							((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
						}}}
				break;
			case R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator:
				Log.v(getClass().getSimpleName(), "Found");
				((TextPicLayout)mArrayList.get(i)).et_text1.setText("JOY");
				break;
			case R.id.living_room_check_in_action_plan_if_required_editText:
				((EditText)mArrayList.get(i)).setText("Requirede");
			default:
				break;
			}
		}
		
		*/
	}
	
	
	
	
	public static ArrayList<View> getAllChildren(View v) 
	{
	    if (!(v instanceof ViewGroup)) 
	    {
	        ArrayList<View> viewArrayList = new ArrayList<View>();
	        viewArrayList.add(v);
	        return viewArrayList;
	    } 
	 
	    ArrayList<View> result = new ArrayList<View>();
	 
	    ViewGroup viewGroup = (ViewGroup) v;
	    for (int i = 0; i < viewGroup.getChildCount(); i++) 
	    {
	    	View child = viewGroup.getChildAt(i);
	        ArrayList<View> viewArrayList = new ArrayList<View>();
	        viewArrayList.add(v);
	        viewArrayList.addAll(getAllChildren(child));
	 
	        result.addAll(viewArrayList);
	    } 
	    return result;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_pic1:
			onPicUpload(v);
			break;
		case R.id.iv_pic2:
			onPicUpload(v);
			break;
		case R.id.iv_pic3:
			onPicUpload(v);
			break;
		case R.id.iv_pic4:
			onPicUpload(v);
			break;
		}

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
				camerapic.setImageBitmap(bmImg);
				camerapic.setTag(imageFile.getAbsolutePath());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mTextPicLayout.onActivityResult(requestCode, resultCode, data);
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

	public int getIdAssignedByR(Context pContext, String pIdString) {
		// Get the Context's Resources and Package Name
		Resources resources = pContext.getResources();
		String packageName = pContext.getPackageName();

		// Determine the result and return it
		int result = resources.getIdentifier(pIdString, "id", packageName);
		return result;
	}
	public void showImageInIV_NEW(String path,ImageView iv)
	{	
		ImageView setImgpic = iv;
		Uri mUrl;
		Bitmap bmImg = null;
		try {
			if(path !=null && path.toString().length() !=0 )
			{
				
				File imageFile = new File(path);
				if(imageFile.exists())
				{
					/*mUrl= Uri.parse(path);
					setImgpic.setImageURI(mUrl);
					setImgpic.setTag(path);*/
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
		startActivity(new Intent(this, InteriorCloakRoom.class));
		finish();
	}

	void gotoNext(View v) {
		 saveData(((Button) findViewById(R.id.savephase)));
	//	saveData_NEW(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this, InteriorDiningRoom.class));
		finish();
	}

	/*
	 * 
	 * public void saveData(View v){ long Success = -1;
	 * Log.v("editJobId",editJobId+""); ContentValues contentValues = new
	 * ContentValues(); db.openDataBase();
	 * 
	 * contentValues = new ContentValues();
	 * 
	 * String[] strArray =
	 * {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
	 * "skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
	 * "windows_or_locks_or_keys_or_handles"
	 * ,"lighting_or_bulbs_or_sheds","soft_furnishing",
	 * "wooden_furniture","curtains_or_blinds","mirror_or_picture",
	 * "miscellaneous"
	 * ,"tv_socket","phone_socket","action_plan_if_required","follow_up"
	 * ,"final_comments"}; int i; for(i=0;i< strArray.length;i++) {
	 * contentValues = new ContentValues(); int chkincmntId = 0, predeprtcmnt_Id
	 * = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id =
	 * 0; chkincmntId =
	 * getIdAssignedByR(this,"living_room_check_in_"+strArray[i]+"_editText");
	 * chkincmpId =
	 * getIdAssignedByR(this,"living_room_check_in_"+strArray[i]+"_imageView");
	 * predeprtcmnt_Id =
	 * getIdAssignedByR(this,"living_room_pre_departure_"+strArray
	 * [i]+"_editText"); predeprtcmp_Id =
	 * getIdAssignedByR(this,"living_room_pre_departure_"
	 * +strArray[i]+"_imageView"); deprtcmnt_Id =
	 * getIdAssignedByR(this,"living_room_departure_"+strArray[i]+"_editText");
	 * deprtcmp_Id =
	 * getIdAssignedByR(this,"living_room_departure_"+strArray[i]+"_imageView");
	 * 
	 * // textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs //
	 * textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs //
	 * textPicLayout_living_room_departure_flooring_or_carpet_or_rugs
	 * 
	 * chkincmntId =
	 * getIdAssignedByR(this,"textPicLayout_living_room_check_in_"+strArray[i]);
	 * chkincmpId =
	 * getIdAssignedByR(this,"living_room_check_in_"+strArray[i]+"_imageView");
	 * predeprtcmnt_Id =
	 * getIdAssignedByR(this,"textPicLayout_living_room_pre_departure_"
	 * +strArray[i]); predeprtcmp_Id =
	 * getIdAssignedByR(this,"living_room_pre_departure_"
	 * +strArray[i]+"_imageView"); deprtcmnt_Id =
	 * getIdAssignedByR(this,"textPicLayout_living_room_departure_"
	 * +strArray[i]); deprtcmp_Id =
	 * getIdAssignedByR(this,"living_room_departure_"+strArray[i]+"_imageView");
	 * 
	 * if(strArray[i].equals("tv_socket")) { try { radioyesnoGroup =
	 * (RadioGroup)
	 * findViewById(R.id.living_room_check_in_tv_socket_radioGroup); selectedId
	 * = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton =
	 * (RadioButton) findViewById(selectedId); contentValues.put("checkin_comm",
	 * radioyesnoButton.getText().toString()); } catch (Exception e) {
	 * Log.v("for", "living_room_check_in_tv_socket_radioGroup"); }
	 * contentValues.put("checkin_img", "");
	 * 
	 * try { radioyesnoGroup = (RadioGroup)
	 * findViewById(R.id.living_room_pre_departure_tv_socket_radioGroup);
	 * selectedId = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton
	 * = (RadioButton) findViewById(selectedId);
	 * contentValues.put("predepart_comm",
	 * radioyesnoButton.getText().toString()); } catch (Exception e) {
	 * Log.v("for", "living_room_predart_photo_socket_radioGroup"); }
	 * contentValues.put("predepart_img", ""); try { radioyesnoGroup =
	 * (RadioGroup)
	 * findViewById(R.id.living_room_departure_tv_socket_radioGroup); selectedId
	 * = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton =
	 * (RadioButton) findViewById(selectedId); contentValues.put("depart_comm",
	 * radioyesnoButton.getText().toString()); } catch (Exception e) {
	 * Log.v("for", "living_room_depart_photo_socket_radioGroup"); }
	 * contentValues.put("depart_img", ""); } else
	 * if(strArray[i].equals("phone_socket")) { try { radioyesnoGroup =
	 * (RadioGroup)
	 * findViewById(R.id.living_room_check_in_phone_socket_radioGroup);
	 * selectedId = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton
	 * = (RadioButton) findViewById(selectedId);
	 * contentValues.put("checkin_comm", radioyesnoButton.getText().toString());
	 * } catch (Exception e) { Log.v("for",
	 * "living_room_check_in_phone_socket_radioGroup"); }
	 * contentValues.put("checkin_img", ""); try { radioyesnoGroup =
	 * (RadioGroup)
	 * findViewById(R.id.living_room_pre_departure_phone_socket_radioGroup);
	 * selectedId = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton
	 * = (RadioButton) findViewById(selectedId);
	 * contentValues.put("predepart_comm",
	 * radioyesnoButton.getText().toString()); } catch (Exception e) {
	 * Log.v("for", "living_room_predart_phone_socket_radioGroup"); }
	 * contentValues.put("predepart_img", ""); try { radioyesnoGroup =
	 * (RadioGroup)
	 * findViewById(R.id.living_room_departure_phone_socket_radioGroup);
	 * selectedId = radioyesnoGroup.getCheckedRadioButtonId(); radioyesnoButton
	 * = (RadioButton) findViewById(selectedId);
	 * contentValues.put("depart_comm", radioyesnoButton.getText().toString());
	 * } catch (Exception e) { Log.v("for",
	 * "living_room_depart_phone_socket_radioGroup"); }
	 * contentValues.put("depart_img", ""); } else { try{
	 * contentValues.put("checkin_comm",
	 * ((EditText)findViewById(chkincmntId)).getText().toString()); }
	 * catch(Exception e) { Log.v("for", ""+chkincmntId ); } try {
	 * contentValues.put("checkin_img",
	 * ((ImageView)findViewById(chkincmpId)).getTag().toString());
	 * 
	 * } catch (Exception e) { Log.v("for", ""+chkincmpId); } try {
	 * contentValues.put("predepart_comm",
	 * ((EditText)findViewById(predeprtcmnt_Id)).getText().toString());
	 * 
	 * 
	 * } catch (Exception e) { Log.v("for", ""+predeprtcmnt_Id); } try {
	 * contentValues
	 * .put("predepart_img",((ImageView)findViewById(predeprtcmp_Id)
	 * ).getTag().toString()); } catch (Exception e) { // TODO: handle exception
	 * Log.v("for", ""+predeprtcmp_Id); } try { contentValues.put("depart_comm",
	 * ((EditText)findViewById(deprtcmnt_Id)).getText().toString());
	 * 
	 * } catch (Exception e) { // TODO: handle exception Log.v("for",
	 * ""+deprtcmnt_Id);
	 * 
	 * } try {
	 * contentValues.put("depart_img",((ImageView)findViewById(deprtcmp_Id
	 * )).getTag().toString());
	 * 
	 * } catch (Exception e) { // TODO: handle exception Log.v("for",
	 * ""+deprtcmp_Id);
	 * 
	 * }
	 * 
	 * } Success = db.MyDB().update("LIVING_ROOM", contentValues,
	 * "jobid ="+editJobId+" AND inspect_type = '"+strArray[i]+"'", null);
	 * Log.v("updated for", "LIVING_ROOM"+Success);
	 * 
	 * }
	 * 
	 * db.close(); UpdateDb.updatePhaseStatus(db, this, editJobId,
	 * "LIVING_ROOM", "tempsaved", "modified"); Cursor status =
	 * SelectDb.getstatusNmodeByPhase(db,"LIVING_ROOM", editJobId);
	 * 
	 * try{ //if(status != null && status.getInt(2) == 1) { SendToServer s2sv =
	 * new SendToServer(db, this, true, "LIVING_ROOM",
	 * editJobId,"create/update",InteriorCloakRoom.class); s2sv.frontEndSend();
	 * //}else{ } }catch(Exception e){ e.getStackTrace(); }
	 * 
	 * }
	 */
	//public void saveData_NEW(View v) 

	public void saveData(View v) {

		long Success = -1;
		Log.v("editJobId", editJobId + "");
		ContentValues contentValues = new ContentValues();
		db.openDataBase();

		contentValues = new ContentValues();
		
		
		
		
		
		//flooring_or_carpet_or_rugs
		try {
			//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.check_in_wooden_floor_editText)).getText().toString());
			
			//contentValues.put("",((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString() );
			contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString() );
			Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
		}
		try {
			//contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_wooden_floor_imageView)).getTag().toString());
			String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
			contentValues.put("checkin_img",imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
		}
		try {
			//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_wooden_floor_editText)).getText().toString());
			contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).et_text1.getText().toString() );
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

		} 
		try {
			//contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_wooden_floor_imageView)).getTag().toString());
			String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
			contentValues.put("predepart_img",imagePath);

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
		}
		try {
			//contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_wooden_floor_editText)).getText().toString());
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_departure_wooden_floor_editText");

		}
		try {
		//	contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_wooden_floor_imageView)).getTag().toString());
			String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
			contentValues.put("depart_img", imagePath);

		} catch (Exception e) {
			Log.v("for", "hall_landing_departure_wooden_floor_imageView");
		}
		Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'flooring_or_carpet_or_rugs'", null);
		Log.v("updated for", "flooring_or_carpet_or_rugs"+Success);		
		

		//paint_or_paperwall_or_ceiling
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_departure_wooden_floor_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
					contentValues.put("depart_img",imagePath);

				} catch (Exception e) {
					Log.v("for", "hall_landing_departure_wooden_floor_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
				Log.v("updated for", "paint_or_paperwall_or_ceiling"+Success);		
				
			
		
				//skirting_boards_or_radiator
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img",imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_departure_skirting_boards_or_radiator_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "hall_landing_departure_skirting_boards_or_radiator");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'skirting_boards_or_radiator'", null);
				Log.v("updated for", "skirting_boards_or_radiator"+Success);	
	
		
				//doors_or_locks_or_keys_or_handles
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "Checkin_doors_or_locks_or_keys_or_handles");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "living_room_check_in_doors_or_locks_or_keys_or_handles_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_ettext");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_img");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles'", null);
				Log.v("updated for", "doors_or_locks_or_keys_or_handles"+Success);	
		
				//textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles	
				
				//windows_or_locks_or_keys_or_handles
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles_editText");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_ettext");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
					contentValues.put("depart_img",imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_img");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'windows_or_locks_or_keys_or_handles'", null);
				Log.v("updated for", "windows_or_locks_or_keys_or_handles"+Success);	
		
				
				
				
				//lighting_or_bulbs_or_sheds
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds_editText");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_shedsr_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img",imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulbs_or_sheds'", null);
				Log.v("updated for", "lighting_or_bulbs_or_sheds"+Success);	
				
				/*	
				//
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs_editText");
				}
				try {
					contentValues.put("checkin_img", (((TextPicLayout)findViewById(R.id.)).iv_pic1.getTag().toString()));
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

				} 
				try {
					contentValues.put("predepart_img", (((TextPicLayout)findViewById(R.id.)).iv_pic1.getTag().toString()));

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_departure_wooden_floor_editText");

				}
				try {
					contentValues.put("depart_img", (((TextPicLayout)findViewById(R.id.)).iv_pic1.getTag().toString()));

				} catch (Exception e) {
					Log.v("for", "hall_landing_departure_wooden_floor_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = ''", null);
				Log.v("updated for", ""+Success);		
				*/
				
				//soft_furnishing
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img",imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_soft_furnishingr_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_soft_furnishing_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_soft_furnishingr_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_soft_furnishingr_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_soft_furnishing_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'soft_furnishing'", null);
				Log.v("updated for", "soft_furnishing"+Success);	
				
				
			
				
				//wooden_furniture   	textPicLayout_living_room_pre_departure_wooden_furniture
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_wooden_furniture_editTtext");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_wooden_furniture_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_wooden_furniture_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_wooden_furniture_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).iv_pic4.getTag().toString();
					contentValues.put("depart_img",imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_wooden_furniture_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'wooden_furniture'", null);
				Log.v("updated for", "wooden_furniture"+Success);		
				
				
				//  curtains_or_blinds   textPicLayout_living_room_pre_departure_curtains_or_blinds
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_curtains_or_blinds_editText");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_curtains_or_blinds_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_curtains_or_blindsr_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img",imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_curtains_or_blinds_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_curtains_or_blinds_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).iv_pic4.getTag().toString();
					contentValues.put("depart_img",imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_curtains_or_blinds_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds'", null);
				Log.v("updated for", "curtains_or_blinds"+Success);		
			
				
				
	//mirror_or_picture
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_mirror_or_picture_editText");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img",imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_mirror_or_picture_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_mirror_or_picture_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img", imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_mirror_or_picturer_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_mirror_or_picture_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_mirror_or_picture_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'mirror_or_picture'", null);
				Log.v("updated for", "mirror_or_picture"+Success);		
			
				
				//miscellaneous
				try {
					
					contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).et_text1.getText().toString() );
					Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).et_text1.getText().toString());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_miscellaneous_editText");
				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).iv_pic4.getTag().toString();
					contentValues.put("checkin_img", imagePath);
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_check_in_miscellaneous_imageView");
				}
				try {
					contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).et_text1.getText().toString() );
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_miscellaneous_editText");

				} 
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).iv_pic4.getTag().toString();
					contentValues.put("predepart_img",imagePath);

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_pre_departure_miscellaneous_imageView");
				}
				try {
					contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).et_text1.getText().toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "textPicLayout_living_room_departure_miscellaneous_editText");

				}
				try {
					String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).iv_pic1.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).iv_pic2.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).iv_pic3.getTag().toString()
							+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).iv_pic4.getTag().toString();
					contentValues.put("depart_img", imagePath);

				} catch (Exception e) {
					Log.v("for", "textPicLayout_living_room_departure_miscellaneous_imageView");
				}
				Success = db.MyDB().update("LIVING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
				Log.v("updated for", "miscellaneous"+Success);		
				
				
		/*		
				
		String[] strArray = { "flooring_or_carpet_or_rugs",
				"paint_or_paperwall_or_ceiling", "skirting_boards_or_radiator",
				"doors_or_locks_or_keys_or_handles",
				"windows_or_locks_or_keys_or_handles",
				"lighting_or_bulbs_or_sheds", "soft_furnishing",
				"wooden_furniture", "curtains_or_blinds", "mirror_or_picture",
				"miscellaneous", "tv_socket", "phone_socket",
				"action_plan_if_required", "follow_up", "final_comments" };
				
				*/
				
				String[] strArray = {  "tv_socket", "phone_socket",
						"action_plan_if_required", "follow_up", "final_comments" };
;int i;
		for (i = 0; i < strArray.length; i++) {
			contentValues = new ContentValues();
			int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0, chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
			
			  chkincmntId =
			  getIdAssignedByR(this,"living_room_check_in_"+strArray
			  [i]+"_editText"); chkincmpId =
			  getIdAssignedByR(this,"living_room_check_in_"
			  +strArray[i]+"_imageView"); predeprtcmnt_Id =
			  getIdAssignedByR(this
			  ,"living_room_pre_departure_"+strArray[i]+"_editText");
			  predeprtcmp_Id =
			  getIdAssignedByR(this,"living_room_pre_departure_"
			  +strArray[i]+"_imageView"); deprtcmnt_Id =
			  getIdAssignedByR(this,"living_room_departure_"
			  +strArray[i]+"_editText"); deprtcmp_Id =
			  getIdAssignedByR(this,"living_room_departure_"
			  +strArray[i]+"_imageView");
			 
		
			// textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs
			// textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs
			// textPicLayout_living_room_departure_flooring_or_carpet_or_rugs

/*
			chkincmntId = v.et_text1.getId();// getIdAssignedByR(this,"textPicLayout_living_room_check_in_"+strArray[i]+v.et_text1);
			chkincmpId = getIdAssignedByR(this, "living_room_check_in_"
					+ strArray[i] + "_imageView");
			predeprtcmnt_Id = getIdAssignedByR(this,
					"textPicLayout_living_room_pre_departure_" + strArray[i]
							+ v.et_text1);
			predeprtcmp_Id = getIdAssignedByR(this,
					"living_room_pre_departure_" + strArray[i] + "_imageView");
			deprtcmnt_Id = getIdAssignedByR(this,
					"textPicLayout_living_room_departure_" + strArray[i]);
			deprtcmp_Id = getIdAssignedByR(this, "living_room_departure_"
					+ strArray[i] + "_imageView" + v.et_text1);
*/
			if (strArray[i].equals("tv_socket")) {
				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_check_in_tv_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("checkin_comm", radioyesnoButton
							.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_check_in_tv_socket_radioGroup");
				}
				contentValues.put("checkin_img", "");

				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_pre_departure_tv_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("predepart_comm", radioyesnoButton
							.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_pre_departure_tv_socket_radioGroup");
				}
				contentValues.put("predepart_img", "");
				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_departure_tv_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("depart_comm", radioyesnoButton.getText().toString());
					Log.v("for", "living_room_departure_tv_socket_radioGroup JOY"+radioyesnoButton.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_depart_photo_socket_radioGroup");
				}
				contentValues.put("depart_img", "");
			} else if (strArray[i].equals("phone_socket")) {
				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_check_in_phone_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("checkin_comm", radioyesnoButton
							.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_check_in_phone_socket_radioGroup");
				}
				contentValues.put("checkin_img", "");
				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_pre_departure_phone_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("predepart_comm", radioyesnoButton
							.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_predart_phone_socket_radioGroup");
				}
				contentValues.put("predepart_img", "");
				try {
					radioyesnoGroup = (RadioGroup) findViewById(R.id.living_room_departure_phone_socket_radioGroup);
					selectedId = radioyesnoGroup.getCheckedRadioButtonId();
					radioyesnoButton = (RadioButton) findViewById(selectedId);
					contentValues.put("depart_comm", radioyesnoButton.getText()
							.toString());
				
					Log.v("for", "living_room_depart_phone_socket_radioGroup test JOY "+radioyesnoButton.getText().toString());
				} catch (Exception e) {
					Log.v("for", "living_room_depart_phone_socket_radioGroup");
				}
				contentValues.put("depart_img", "");
			} else {
				
				
				try {
					contentValues.put("checkin_comm",
							((EditText) findViewById(chkincmntId)).getText()
									.toString());
				} catch (Exception e) {
					Log.v("for", "" + chkincmntId);
				}
				try {
					contentValues.put("checkin_img",
							((ImageView) findViewById(chkincmpId)).getTag()
									.toString());

				} catch (Exception e) {
					Log.v("for", "" + chkincmpId);
				}
				try {
					contentValues.put("predepart_comm",
							((EditText) findViewById(predeprtcmnt_Id))
									.getText().toString());

				} catch (Exception e) {
					Log.v("for", "" + predeprtcmnt_Id);
				}
				try {
					contentValues.put("predepart_img",
							((ImageView) findViewById(predeprtcmp_Id)).getTag()
									.toString());
				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "" + predeprtcmp_Id);
				}
				try {
					contentValues.put("depart_comm",
							((EditText) findViewById(deprtcmnt_Id)).getText()
									.toString());

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "" + deprtcmnt_Id);

				}
				try {
					contentValues.put("depart_img",
							((ImageView) findViewById(deprtcmp_Id)).getTag()
									.toString());

				} catch (Exception e) {
					// TODO: handle exception
					Log.v("for", "" + deprtcmp_Id);

				}
				
				

			}
			Success = db.MyDB().update(
					"LIVING_ROOM",
					contentValues,
					"jobid =" + editJobId + " AND inspect_type = '"
							+ strArray[i] + "'", null);
			Log.v("updated for", "LIVING_ROOM" + Success);

		}

		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "LIVING_ROOM",
				"tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db, "LIVING_ROOM",
				editJobId);

		try {
			// if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true,
						"LIVING_ROOM", editJobId, "create/update",
						InteriorCloakRoom.class);
				s2sv.frontEndSend();
				// }else{
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
}
	

	void renderData() {
		Cursor cursor = SelectDb.getPhaseByData(db, "LIVING_ROOM", editJobId, false, true, false);
		if (cursor != null) {
			Log.v("count", "" + cursor.getCount());
			for (int j = 0; j < cursor.getCount(); j++)
			{
				

				
	//flooring_or_carpet_or_rugs
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring_or_carpet_or_rugs"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("paint_or_paperwall_or_ceiling"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
	
				
	//skirting_boards_or_radiator
		
		if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("skirting_boards_or_radiator"))
		{
			/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
			((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
			
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
			
			String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_skirting_boards_or_radiator)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("predepart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_skirting_boards_or_radiator)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("depart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_skirting_boards_or_radiator)).imgArr[i]);
				}						
			}

		/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
			cursor.moveToNext();
			continue;
		}
		
		
		if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("doors_or_locks_or_keys_or_handles"))
		{
			
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
			
			String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_doors_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("predepart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("depart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}

		/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
			cursor.moveToNext();
			continue;
		}
		
	//	windows_or_locks_or_keys_or_handles
		
		if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("windows_or_locks_or_keys_or_handles"))
		{
			
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
			
			String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_windows_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("predepart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("depart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
				}						
			}

		/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
			cursor.moveToNext();
			continue;
		}
		
		
		
//lighting_or_bulbs_or_sheds		
		
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lighting_or_bulbs_or_sheds"))
		{
			
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
			((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
			
			String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_lighting_or_bulbs_or_sheds)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("predepart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
				}						
			}
			
			path = cursor.getString(cursor.getColumnIndex("depart_img"));
			if(path != null)
			{
				String[] arr = path.split("\\},\\{");
				for(int i=0; i<arr.length; i++)
				{
					Log.v("ImageName", arr[i]);
					showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
				}						
			}

		/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
			showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
			cursor.moveToNext();
			continue;
		}
		
	
	
//soft_furnishing
	
	
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("soft_furnishing"))
	{
		
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
		
		String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_soft_furnishing)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("predepart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_soft_furnishing)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("depart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_soft_furnishing)).imgArr[i]);
			}						
		}

	/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
		cursor.moveToNext();
		continue;
	}
	

//wooden_furniture
	
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("wooden_furniture"))
	{
		
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));

		String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_wooden_furniture)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("predepart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_wooden_furniture)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("depart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_wooden_furniture)).imgArr[i]);
			}						
		}

	/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
		cursor.moveToNext();
		continue;
	}
	
	
	
	
//curtains_or_blinds
	
	
	
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("curtains_or_blinds"))
	{
		
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));

		String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_curtains_or_blinds)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("predepart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_curtains_or_blinds)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("depart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_curtains_or_blinds)).imgArr[i]);
			}						
		}

	/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
		cursor.moveToNext();
		continue;
	}
	

//	mirror_or_picture	
	
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("mirror_or_picture"))
	{
		
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));

		String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_mirror_or_picture)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("predepart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_mirror_or_picture)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("depart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_mirror_or_picture)).imgArr[i]);
			}						
		}

	/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
		cursor.moveToNext();
		continue;
	}
		
	
//miscellaneous	
	
	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
	{
		
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
		((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));

		String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_miscellaneous)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("predepart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_pre_departure_miscellaneous)).imgArr[i]);
			}						
		}
		
		path = cursor.getString(cursor.getColumnIndex("depart_img"));
		if(path != null)
		{
			String[] arr = path.split("\\},\\{");
			for(int i=0; i<arr.length; i++)
			{
				Log.v("ImageName", arr[i]);
				showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_living_room_departure_miscellaneous)).imgArr[i]);
			}						
		}

	/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
		showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
		cursor.moveToNext();
		continue;
	}
		
		
		
		
		
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0, chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
				String conserned_type = cursor.getString(cursor.getColumnIndex("inspect_type"));
				chkincmntId = getIdAssignedByR(this, "living_room_check_in_"+ conserned_type + "_editText");
				chkincmpId = getIdAssignedByR(this, "living_room_check_in_"+ conserned_type + "_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"living_room_pre_departure_" + conserned_type+ "_editText");
				predeprtcmp_Id = getIdAssignedByR(this,"living_room_pre_departure_" + conserned_type+ "_imageView");
				deprtcmnt_Id = getIdAssignedByR(this, "living_room_departure_"+ conserned_type + "_editText");
				deprtcmp_Id = getIdAssignedByR(this, "living_room_departure_"+ conserned_type + "_imageView");

				if (conserned_type.equals("tv_socket")) {
					try {
						if (cursor.getString(
								cursor.getColumnIndex("checkin_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_check_in_tv_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_check_in_tv_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						if (cursor.getString(
								cursor.getColumnIndex("predepart_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_pre_departure_tv_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_pre_departure_tv_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						if (cursor.getString(
								cursor.getColumnIndex("depart_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_departure_tv_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_departure_tv_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else if (conserned_type.equals("phone_socket")) {
					try {
						if (cursor.getString(
								cursor.getColumnIndex("checkin_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_check_in_phone_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_check_in_phone_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						if (cursor.getString(
								cursor.getColumnIndex("predepart_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_pre_departure_phone_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_pre_departure_phone_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						if (cursor.getString(
								cursor.getColumnIndex("depart_comm"))
								.equalsIgnoreCase("yes")) {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_departure_phone_socket_y);
							radioyesnoButton.setChecked(true);

						} else {
							radioyesnoButton = (RadioButton) findViewById(R.id.living_room_departure_phone_socket_n);
							radioyesnoButton.setChecked(true);
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else {
					
					
					try {
						((EditText) findViewById(chkincmntId)).setText(cursor
								.getString(cursor
										.getColumnIndex("checkin_comm")));
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						((EditText) findViewById(predeprtcmnt_Id))
								.setText(cursor.getString(cursor
										.getColumnIndex("predepart_comm")));
					} catch (Exception e) {
						e.getStackTrace();
					}
					try {
						((EditText) findViewById(deprtcmnt_Id))
								.setText(cursor.getString(cursor
										.getColumnIndex("depart_comm")));
					} catch (Exception e) {
						e.getStackTrace();
					}

					try {
						if (!cursor.getString(
								cursor.getColumnIndex("checkin_img"))
								.equals("")) {
							showImageInIV(cursor.getString(cursor
									.getColumnIndex("checkin_img")), chkincmpId);

						}
					} catch (Exception e) {
						e.getStackTrace();
					}

					try {
						if (!cursor.getString(
								cursor.getColumnIndex("predepart_img")).equals(
								"")) {
							showImageInIV(cursor.getString(cursor
									.getColumnIndex("predepart_img")),
									predeprtcmp_Id);

						}
					} catch (Exception e) {
						e.getStackTrace();
					}

					try {
						if (!cursor.getString(
								cursor.getColumnIndex("depart_img")).equals("")) {
							showImageInIV(cursor.getString(cursor
									.getColumnIndex("depart_img")), deprtcmp_Id);

						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					
					
				}
				cursor.moveToNext();
			}
		}
	}

}