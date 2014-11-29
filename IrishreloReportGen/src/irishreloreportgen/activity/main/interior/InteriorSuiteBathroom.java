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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class InteriorSuiteBathroom extends ActionBarActivity {
	public static final int SIGNATURE_ACTIVITY = 3;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	ImageView camerapic;
	TextPicLayout mTextPicLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.en_suite_bathroom);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
			renderData();
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering);		
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals);		
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_radiator_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror);		
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan);		
		mTextPicLayout.et_text1.setHint(R.string.extractor_fan_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_fan_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray);		
		mTextPicLayout.et_text1.setHint(R.string.shower_curtain_or_shower_door_or_tray_title);
		mTextPicLayout.tv_text1.setText(R.string.shower_curtain_or_shower_door_or_tray_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure);		
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		
	
		
mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals);		
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_radiator_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror);		
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan);		
		mTextPicLayout.et_text1.setHint(R.string.extractor_fan_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_fan_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray);		
		mTextPicLayout.et_text1.setHint(R.string.shower_curtain_or_shower_door_or_tray_title);
		mTextPicLayout.tv_text1.setText(R.string.shower_curtain_or_shower_door_or_tray_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure);		
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);


mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_floor_covering);		
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals);		
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header);		
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_radiator_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror);		
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroominterior_departure_extractor_fan);		
		mTextPicLayout.et_text1.setHint(R.string.extractor_fan_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_fan_title);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray);		
		mTextPicLayout.et_text1.setHint(R.string.shower_curtain_or_shower_door_or_tray_title);
		mTextPicLayout.tv_text1.setText(R.string.shower_curtain_or_shower_door_or_tray_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_water_pressure);		
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering);		
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);
		
		
	}
	
	public void onPicUpload(View v)
	{	
		IrishreloAccess.captbyCam = IrishreloAccess.imagePath = "";
		camerapic = (ImageView) v;
		final Dialog dia = new Dialog(this);
		try{
			if(camerapic.getTag().toString().equals(""))
			{
				dia.setContentView(R.layout.custom);
				dia.setTitle("Get a pic");
			}
			else{
				dia.setContentView(R.layout.popup);
				dia.setTitle("Show/Get a pic");
				Uri mUrl;
				mUrl= Uri.parse(camerapic.getTag().toString());
				((ImageView)dia.findViewById(R.id.popuoimg)).setImageURI(mUrl);
			}
		}catch(Exception e)
		{
			dia.setContentView(R.layout.custom);
			dia.setTitle("Get a pic");
		}
		dia.show();
		Button close = ((Button)dia.findViewById(R.id.back));
		close.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
			// TODO Auto-generated method stub
				dia.dismiss();
			}					
		});
		
		((Button)dia.findViewById(R.id.browse_file))
		.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
			// TODO Auto-generated method stub
				 dia.dismiss();
				Intent intent = new Intent( Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				 startActivityForResult(intent, 1);
				
			}					
		});
		
		((Button)dia.findViewById(R.id.click_pic))
		.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dia.dismiss();
			// TODO Auto-generated method stub
				String imageName = (new Date()).getTime()+".jpg";
				String path = null;
				if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
				{
					path = Environment.getExternalStorageDirectory().getPath()
							+ "/irishrelo/" ;
					
				} else {
					path = Environment.getRootDirectory().getPath()
							+ "/irishrelo/";
				}
				IrishreloAccess.captbyCam = path+imageName;
				File wallpaperDirectory = new File(path);
				// have the object build the directory structure, if needed.
				wallpaperDirectory.mkdirs();
				Intent intentImg = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);//(Exterior.this,CameraActivity.class);
				File file = new File(wallpaperDirectory, imageName);
				Uri outputFileUri = Uri.fromFile(file);
				intentImg.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				startActivityForResult(intentImg, 2);
				
			}					
		});
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!IrishreloAccess.imagePath.equals(""))
			showImage(IrishreloAccess.imagePath);
		else if(!IrishreloAccess.captbyCam.equals(""))
			showImage(IrishreloAccess.captbyCam);
	}
	
	public void showImage(String path)
	{
		try {
			Bitmap bmImg = null;
			File imageFile = new File(path);
			if(imageFile.exists())
			{
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

		/*try {
			if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
				IrishreloAccess.imagePath = getRealPathFromURI(data.getData());
				showImage(IrishreloAccess.imagePath);
			}
			if (requestCode == 2 && resultCode == RESULT_OK) 
			{	
	            if((new File(IrishreloAccess.captbyCam)).exists())
	            {
	            	showImage(IrishreloAccess.captbyCam); 
	            }
			}
			//    
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//Toast.makeText(this, "Image saved to:\n" +e.getLocalizedMessage(), Toast.LENGTH_LONG).show(); 
		
		}*/
		 
		
	}
	public String getRealPathFromURI(Uri contentUri) {
        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                        proj, // Which columns to return
                        null,       // WHERE clause; which rows to return (all rows)
                        null,       // WHERE clause selection arguments (none)
                        null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
	}	
	
	public void showImageInIV(String path,int ID)
	{	
		ImageView setImgpic = (ImageView) findViewById(ID);
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
	
	public int getIdAssignedByR(Context pContext, String pIdString)
	{
	    // Get the Context's Resources and Package Name
	    Resources resources = pContext.getResources();
	    String packageName  = pContext.getPackageName();

	    // Determine the result and return it
	    int result = resources.getIdentifier(pIdString, "id", packageName);
	    return result;
	} 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		menu.removeItem( R.id.action_interior);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id)
		{
			case  R.id.action_commgoto:
				startActivity(new Intent(this, CommonGoToActivity.class));
				finish();
				return true;
			case  R.id.action_utility:
				startActivity(new Intent(this, Utilities.class));
				finish();
				return true;
			case  R.id.action_interior:
				startActivity(new Intent(this, Interior.class));
				finish();
				return true;
			case  R.id.action_exterior:
				startActivity(new Intent(this, Exterior.class));
				finish();
				return true;
			case  R.id.action_home:
				startActivity(new Intent(this, IrishreloLunch.class));
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
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
	
	public void onElementClick(View v)
	{
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

	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorMasterBedroom.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorBedroomTwo.class));
		finish();
	}
	public void saveData(View v){
		
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
			contentValues = new ContentValues();
		
			
			
		//  floor_covering
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_floor_covering="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_floor_covering)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_floor_covering)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_floor_covering");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'floor_covering'", null);
			Log.v("updated for", "floor_covering"+Success);		
		
			
	
   		
// tiles_or_grouting_or_seals 
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals)).mButton.getTag().toString());
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'tiles_or_grouting_or_seals'", null);
			Log.v("updated for", "tiles_or_grouting_or_seals"+Success);		
		
			
			
			
//  paint_or_paperwall_or_ceiling
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
			Log.v("updated for", "paint_or_paperwall_or_ceiling"+Success);		
		
			
			
			
//doors_or_windows_or_keys_or_locks  
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles)).mButton.getTag().toString());
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_windows_or_keys_or_locks'", null);
			Log.v("updated for", "doors_or_windows_or_keys_or_locks"+Success);		
		
			
			
			
//lighting_or_bulb_or_shed  
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulb_or_shed'", null);
			Log.v("updated for", "lighting_or_bulb_or_shed"+Success);		
		
			
			
			
// toilet_or_flush_or_sink_or_tap_stopper 
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'toilet_or_flush_or_sink_or_tap_stopper'", null);
			Log.v("updated for", "toilet_or_flush_or_sink_or_tap_stopper"+Success);		
		
			
			
//  toilet_brush_or_toilet_roll_header
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'toilet_brush_or_toilet_roll_header'", null);
			Log.v("updated for", "toilet_brush_or_toilet_roll_header"+Success);		
		
			
			
			
//towel_rail_or_radiator
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator)).mButton.getTag().toString());
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'towel_rail_or_radiator'", null);
			Log.v("updated for", "towel_rail_or_radiator"+Success);		
		
			
			
			
// cabinet_or_shelve_or_mirror 
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_ckeck_in_cabinet_or_shelve_or_mirror="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror)).et_text1.getText().toString());
				                                                                                                                               
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_ckeck_in_cabinet_or_shelve_or_mirror");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'cabinet_or_shelve_or_mirror'", null);
			Log.v("updated for", "cabinet_or_shelve_or_mirror"+Success);		
		
			
			
//  extractor_fan
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroominterior_departure_extractor_fan)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroominterior_departure_extractor_fan)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_extractor_fan");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'extractor_fan'", null);
			Log.v("updated for", "extractor_fan"+Success);		
		
			
			
//shower_curtain_or_shower_door_or_tray  
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'shower_curtain_or_shower_door_or_tray'", null);
			Log.v("updated for", "shower_curtain_or_shower_door_or_tray"+Success);		
		
			
				
			
			//water_pressure  
			
			contentValues = new ContentValues();
			try {                                                                  
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in__water_pressure="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_water_pressure)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_water_pressure)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_water_pressure");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'water_pressure'", null);
			Log.v("updated for", "water_pressure"+Success);		
			
			
			
			
			//miscellaneous  
			
			contentValues = new ContentValues();
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous)).et_text1.getText().toString() );
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous)).mButton.getTag().toString());
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous="+((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous)).et_text1.getText().toString() );
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous");

			} 
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_miscellaneous)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_miscellaneous)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_en_sute_bathroom_interior_departure_miscellaneous");

			}
			
			Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
			Log.v("updated for", "miscellaneous"+Success);		
			
			
			
			
			
			
			 
			
			
			
			/*String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
					"paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
					"lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
					"toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
					"cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
					"miscellaneous","action_plan_if_required","follow_up","final_comments"};*/
			
			String[] strArray = {"action_plan_if_required","follow_up","final_comments"};
			
			
			
			int i;
			for(i=0;i< strArray.length;i++)
			{
				contentValues = new ContentValues();
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
				chkincmntId = getIdAssignedByR(this,"interior_check_in_"+strArray[i]+"_editText");
				Log.v("id", "interior_check_in_"+strArray[i]+"_editText");
				chkincmpId = getIdAssignedByR(this,"interior_check_in_"+strArray[i]+"_imageView");
				Log.v("id", "interior_check_in_"+strArray[i]+"_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"interior_pre_departure_"+strArray[i]+"_editText");
				Log.v("idd","interior_pre_departure_"+strArray[i]+"_editText" );
				predeprtcmp_Id = getIdAssignedByR(this,"interior_pre_departure_"+strArray[i]+"_imageView");
				Log.v("idd","interior_pre_departure_"+strArray[i]+"_imageView" );
				deprtcmnt_Id = getIdAssignedByR(this,"interior_departure_"+strArray[i]+"_editText");
				Log.v("iddd", "interior_departure_"+strArray[i]+"_editText");
				deprtcmp_Id = getIdAssignedByR(this,"interior_departure_"+strArray[i]+"_imageView");
				Log.v("iddd", "interior_departure_"+strArray[i]+"_imageView");
				
					try{
						contentValues.put("checkin_comm", ((EditText)findViewById(chkincmntId)).getText().toString());
					}
					catch(Exception e)
					{
						Log.v("for", ""+chkincmntId );
					}
					try {
						contentValues.put("checkin_img", ((ImageView)findViewById(chkincmpId)).getTag().toString());
	
					} catch (Exception e) {
						Log.v("for", ""+chkincmpId);
					}
					try {
						contentValues.put("predepart_comm", ((EditText)findViewById(predeprtcmnt_Id)).getText().toString());
	
						
					} catch (Exception e) {
						Log.v("for", ""+predeprtcmnt_Id);
					}
					try {
						contentValues.put("predepart_img",((ImageView)findViewById(predeprtcmp_Id)).getTag().toString());	
					} catch (Exception e) {
						// TODO: handle exception
						Log.v("for", ""+predeprtcmp_Id);
					}
					try {
						contentValues.put("depart_comm", ((EditText)findViewById(deprtcmnt_Id)).getText().toString());
	
					} catch (Exception e) {
						// TODO: handle exception
						Log.v("for", ""+deprtcmnt_Id);
	
					}
					try {
						contentValues.put("depart_img",((ImageView)findViewById(deprtcmp_Id)).getTag().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						Log.v("for", ""+deprtcmp_Id);
	
					}
					
				
				Success = db.MyDB().update("EN_SUITE_BATHROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = '"+strArray[i]+"'", null);
				Log.v("updated for", "EN_SUITE_BATHROOM"+Success);
				
			}
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "EN_SUITE_BATHROOM", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"EN_SUITE_BATHROOM", editJobId);
			
			try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "EN_SUITE_BATHROOM", editJobId,"create/update",InteriorCloakRoom.class);
				s2sv.frontEndSend();
			//}else{		
			}
			}catch(Exception e){
				e.getStackTrace();
			}
	}
	
	
	
	
	
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "EN_SUITE_BATHROOM", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				
				
	//floor_covering			
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("floor_covering"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_floor_covering)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_floor_covering)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_floor_covering)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				
//tiles_or_grouting_or_seals
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("tiles_or_grouting_or_seals"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_tiles_or_grouting_or_seals)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_tiles_or_grouting_or_seals)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_tiles_or_grouting_or_seals)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//paint_or_paperwall_or_ceiling
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("paint_or_paperwall_or_ceiling"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_paint_or_paperwall_or_ceiling)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_paint_or_paperwall_or_ceiling)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_paint_or_paperwall_or_ceiling)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//doors_or_windows_or_keys_or_locks
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("doors_or_windows_or_keys_or_locks"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_doors_or_locks_or_keys_or_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_doors_or_locks_or_keys_or_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_doors_or_locks_or_keys_or_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				
//lighting_or_bulb_or_shed
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lighting_or_bulb_or_shed"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_lighting_or_bulb_or_shed)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_lighting_or_bulb_or_shed)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_lighting_or_bulb_or_shed)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					cursor.moveToNext();
					continue;
				}
				
				
//toilet_or_flush_or_sink_or_tap_stopper
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("toilet_or_flush_or_sink_or_tap_stopper"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_or_flush_or_sink_or_tap_stopper)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_or_flush_or_sink_or_tap_stopper)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_or_flush_or_sink_or_tap_stopper)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					cursor.moveToNext();
					continue;
				}
				
				
				
//toilet_brush_or_toilet_roll_header
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("toilet_brush_or_toilet_roll_header"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_toilet_brush_or_toilet_roll_header)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_toilet_brush_or_toilet_roll_header)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_toilet_brush_or_toilet_roll_header)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//towel_rail_or_radiator
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("towel_rail_or_radiator"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_towel_rail_or_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_towel_rail_or_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_towel_rail_or_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//cabinet_or_shelve_or_mirror
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("cabinet_or_shelve_or_mirror"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
			
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_cabinet_or_shelve_or_mirror)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_cabinet_or_shelve_or_mirror)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_cabinet_or_shelve_or_mirror)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//extractor_fan
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("extractor_fan"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroominterior_departure_extractor_fan)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_extractor_fan)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_extractor_fan)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroominterior_departure_extractor_fan)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					cursor.moveToNext();
					continue;
				}
				
				
//shower_curtain_or_shower_door_or_tray
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("shower_curtain_or_shower_door_or_tray"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_shower_curtain_or_shower_door_or_tray)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_shower_curtain_or_shower_door_or_tray)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_shower_curtain_or_shower_door_or_tray)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//water_pressure
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("water_pressure"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_water_pressure)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_water_pressure)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_water_pressure)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
//miscellaneous
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
				{
				
					
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_check_in_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_pre_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_en_sute_bathroom_interior_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				
				
				
				
				
				
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
			    String conserned_type = cursor.getString(cursor.getColumnIndex("inspect_type"));
			    chkincmntId = getIdAssignedByR(this,"interior_check_in_"+conserned_type+"_editText");
				chkincmpId = getIdAssignedByR(this,"interior_check_in_"+conserned_type+"_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"interior_pre_departure_"+conserned_type+"_editText");
				predeprtcmp_Id = getIdAssignedByR(this,"interior_pre_departure_"+conserned_type+"_imageView");
				deprtcmnt_Id = getIdAssignedByR(this,"interior_departure_"+conserned_type+"_editText");
				deprtcmp_Id = getIdAssignedByR(this,"interior_departure_"+conserned_type+"_imageView");
				
				
						try{
							((EditText)findViewById(chkincmntId)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
						}catch(Exception e){
							e.getStackTrace();
						}
						try{
							((EditText)findViewById(predeprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
						}catch(Exception e){
							e.getStackTrace();
						}
						try{
							((EditText)findViewById(deprtcmnt_Id)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						}catch(Exception e){
							e.getStackTrace();
						}
						
						
						try{
							if(!cursor.getString(cursor.getColumnIndex("checkin_img")).equals(""))
							{
								showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),chkincmpId);

							
							}
						}catch(Exception e){
							e.getStackTrace();
						}
						
						try{
							if(!cursor.getString(cursor.getColumnIndex("predepart_img")).equals(""))
							{
								showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),predeprtcmp_Id);

							
							}
						}catch(Exception e){
							e.getStackTrace();
						}
		
						try{
							if(!cursor.getString(cursor.getColumnIndex("depart_img")).equals(""))
							{
								showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),deprtcmp_Id);

							
							}
						}catch(Exception e){
							e.getStackTrace();
						}
						
						cursor.moveToNext();
					
			}
		}
	}
}