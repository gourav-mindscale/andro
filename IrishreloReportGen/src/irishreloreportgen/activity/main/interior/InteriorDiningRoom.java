package irishreloreportgen.activity.main.interior;
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

public class InteriorDiningRoom extends ActionBarActivity implements OnClickListener {
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
		setContentView(R.layout.dinning_room_photos);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		
		if(!editJobId.equals("0"))
		   renderData();


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);	
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furnishing_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);
	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous);
		mTextPicLayout.listener = this;                   
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);
	
	
	
//PRE Depuryure
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_carpetRug);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_wooden_floor);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
	
		
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_skirting_boards_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_doors_locks_keys);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);
		
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
	
	
//PRE DEpurture		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);

		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furnishing_title);
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_headset);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_directory);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title);
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
	
		
		
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_carpetRug);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);

		
//DEPARTURE	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_wooden_floor);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
	
		
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);
		
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);

		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_table_chair_coatstand);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.wooden_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.wooden_furnishing_title);

		

		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_headset);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_directory);
		mTextPicLayout.listener = this; 
		mTextPicLayout.et_text1.setHint(R.string.mirror_picture_title); 
		mTextPicLayout.tv_text1.setText(R.string.mirror_picture_title);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_miscellaneous);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
	
		
		
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
			case R.id.savephase:
				saveData(v);
				break;
			case R.id.proctonext:
				gotoNext(v);
				break;
			case R.id.backtoprev:
				backTheprocess(v);
				break;  
			default:
				break;
		}
	}
	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorLiving.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorKitchen.class));
		finish();
	}
	public void showImageInIV(String path,int ID)
	{	
		ImageView setImgpic;
		Uri mUrl;
		Bitmap bmImg = null;
		try {
			setImgpic = (ImageView) findViewById(ID);
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
	
	/*public void saveData(View v){
		
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
		//flooring_or_carpet_or_rugs
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_carpetRugs)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_carpetRugs"+ ((EditText)findViewById(R.id.hall_landing_check_in_carpetRugs)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_interior_carpets_and_rugs_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_interior_carpets_and_rugs_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.check_in_carpetRugs)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "check_in_carpetRugs");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_interior_carpets_and_rugs_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_interior_carpets_and_rugs_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_interior_carpets_and_rugs_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_interior_carpets_and_rugs_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'flooring_or_carpet_or_rugs'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			//paint_or_paperwall_or_ceiling
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.check_in_wooden_floor)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "check_in_wooden_floor"+ ((EditText)findViewById(R.id.check_in_wooden_floor)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_wooden_floor_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_wooden_floor)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_wooden_floor");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_wooden_floor_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_wooden_floor)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_wooden_floor");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_wooden_floor_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_wooden_floor_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			//skirting_boards_or_radiator
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_skirting_boards_radiator)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_skirting_boards_radiator"+ ((EditText)findViewById(R.id.hall_landing_check_in_skirting_boards_radiator)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_skirting_boards_radiator_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_skirting_boards_radiator_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_skirting_boards_radiator)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_skirting_boards_radiator");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_skirting_boards_radiator_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_skirting_boards_radiator_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_skirting_boards_radiator)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_skirting_boards_radiator");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_skirting_boards_radiator_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_skirting_boards_radiator_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'skirting_boards_or_radiator'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			//doors_or_locks_or_keys_or_handles
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_doors_locks_keys)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_doors_locks_keys"+ ((EditText)findViewById(R.id.hall_landing_check_in_doors_locks_keys)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_doors_locks_keys_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_doors_locks_keys_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_doors_locks_keys)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_doors_locks_keys");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_doors_locks_keys_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_doors_locks_keys_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_doors_locks_keys)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_doors_locks_keys");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_doors_locks_keys_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_doors_locks_keys_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			//windows_or_locks_or_keys_or_handles
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_window_locks_keys_handles)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_window_locks_keys_handles"+ ((EditText)findViewById(R.id.hall_landing_check_in_window_locks_keys_handles)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_window_locks_keys_handles_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_window_locks_keys_handles_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_window_locks_keys_handles)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_window_locks_keys_handles");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_window_locks_keys_handles_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_window_locks_keys_handles_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_window_locks_keys_handles)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_window_locks_keys_handles");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_window_locks_keys_handles_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_window_locks_keys_handles_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'windows_or_locks_or_keys_or_handles'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			//lighting_or_bulbs_or_sheds
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_lighting_bulbs_shades)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_lighting_bulbs_shades"+ ((EditText)findViewById(R.id.hall_landing_check_in_lighting_bulbs_shades)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_lighting_bulbs_shades_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_lighting_bulbs_shades_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_lighting_bulbs_shades)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_lighting_bulbs_shades");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_lighting_bulbs_shades_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_lighting_bulbs_shades_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_lighting_bulbs_shades)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_lighting_bulbs_shades");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_lighting_bulbs_shades_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_lighting_bulbs_shades_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulbs_or_sheds'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			//soft_furnishing
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_handles_doors_fininsh)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_handles_doors_fininsh"+ ((EditText)findViewById(R.id.hall_landing_check_in_handles_doors_fininsh)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_handles_doors_fininsh_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_handles_doors_fininsh_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_handles_doors_fininsh)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_handles_doors_fininsh");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_handles_doors_fininsh_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_handles_doors_fininsh_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_handles_doors_fininsh)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_handles_doors_fininsh");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_handles_doors_fininsh_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_handles_doors_fininsh_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'soft_furnishing'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			//wooden_furnishing
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_table_chair_coatstand)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_table_chair_coatstand"+ ((EditText)findViewById(R.id.hall_landing_check_in_table_chair_coatstand)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_table_chair_coatstand_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_table_chair_coatstand_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_table_chair_coatstand)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_table_chair_coatstand");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_table_chair_coatstand_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_table_chair_coatstand_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_table_chair_coatstand)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_table_chair_coatstand");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_table_chair_coatstand_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_table_chair_coatstand_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'wooden_furnishing'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			//curtains_or_blinds
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_phon_headset)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_phon_headset"+ ((EditText)findViewById(R.id.hall_landing_check_in_phon_headset)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_phone_headset_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_phone_headset_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_phon_headset)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_phon_headset");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_phon_headset_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_phon_headset_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_phon_headset)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_phon_headset");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_phone_headset_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_phone_headset_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			//mirror_or_picture
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_phon_directory)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_phon_directory"+ ((EditText)findViewById(R.id.hall_landing_check_in_phon_directory)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_phone_directory_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_phone_directory_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_phon_directory)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_phon_directory");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_phone_directory_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_phone_directory_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_phon_directory)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_phon_directory");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_phone_directory_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_phone_directory_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'mirror_or_picture'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			
			//miscellaneous
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_miscellaneous)).getText().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "hall_landing_check_in_miscellaneous"+ ((EditText)findViewById(R.id.hall_landing_check_in_miscellaneous)).getText().toString());
			}
			try {
				contentValues.put("checkin_img", ((ImageView)findViewById(R.id.hall_landing_check_in_miscellaneous_imageView)).getTag().toString());

			} catch (Exception e) {
				Log.v("for", "hall_landing_check_in_miscellaneous_imageView");
			}
			try {
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_miscellaneous)).getText().toString());

				
			} catch (Exception e) {
				Log.v("for", "hall_landing_pre_departure_miscellaneous");
			}
			try {
				contentValues.put("predepart_img",((ImageView)findViewById(R.id.hall_landing_pre_departure_miscellaneous_imageView)).getTag().toString());	
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_miscellaneous_imageView");
			}
			try {
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_miscellaneous)).getText().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_miscellaneous");

			}
			try {
				contentValues.put("depart_img",((ImageView)findViewById(R.id.hall_landing_departure_miscellaneous_imageView)).getTag().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_departure_miscellaneous_imageView");

			}
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
			Log.v("updated for", "DINING_ROOM"+Success);
			
			
			
			//action_plan_if_required
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_action_plan)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "hall_landing_check_in_action_plan");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_action_plan)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "hall_landing_pre_departure_action_plan");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_action_plan)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "hall_landing_departure_action_plan");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'action_plan_if_required'", null);
			Log.v("updated for", "action_plan_if_required"+Success);
			
			
			//follow_up
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_follow_up)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "hall_landing_check_in_follow_up"+((EditText)findViewById(R.id.hall_landing_check_in_follow_up)).getText().toString());
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.check_in_follow_up)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "check_in_follow_up");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_follow_up)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "hall_landing_departure_follow_up");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'follow_up'", null);
			Log.v("updated for", "follow_up"+Success);
			
			
			//final_comments
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_final_comments)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "hall_landing_check_in_final_comments");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_comments)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_pre_departure_final_comments");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_comments)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "hall_landing_departure_comments");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'final_comments'", null);
			Log.v("updated for", "final_comments"+Success);
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "DINING_ROOM", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"DINING_ROOM", editJobId);
			
			try{
				//if(status != null && status.getInt(2) == 1)
				{
					SendToServer s2sv = new SendToServer(db, this, true, "DINING_ROOM", editJobId,"create/update",InteriorCloakRoom.class);
					s2sv.frontEndSend();
				//}else{		
				}
				}catch(Exception e){
					e.getStackTrace();
				}
		
	}*/
	
	
	
	
	public void saveData(View v){
	
	long Success = -1;
	Log.v("editJobId",editJobId+"");
	ContentValues contentValues = new ContentValues();		
	db.openDataBase();	
	
	//flooring_or_carpet_or_rugs
		contentValues = new ContentValues();
		                                                                   
		try{
			
			
			
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug)).mButton.getTag().toString());
			Log.v("for", "Checkin_flooring_or_carpet_or_rugs=JOy "+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());

		}
		catch(Exception e)
		{
			Log.v("for", "textPicLayout_dinning_room_hall_landing_check_in_carpetRugsExceptionJOy"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_carpetRug)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_carpetRug)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "check_in_carpetRugs");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_carpetRug)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_carpetRug)).mButton.getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_pre_departure");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'flooring_or_carpet_or_rugs'", null);
		Log.v("updated for", "DINING_ROOM"+Success);                                                       
		
		
		//paint_or_paperwall_or_ceiling
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor)).et_text1.getText().toString());
			
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "check_in_wooden_floor"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_wooden_floor)).et_text1.getText().toString());
			
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_wooden_floor)).mButton.getTag().toString());

			
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_wooden_floor");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_wooden_floor)).et_text1.getText().toString());
			
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_wooden_floor)).mButton.getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_wooden_floor");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		//skirting_boards_or_radiator
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator)).et_text1.getText().toString());
			
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_skirting_boards_radiator)).et_text1.getText().toString());
			
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_skirting_boards_radiator)).mButton.getTag().toString());

			
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_skirting_boards_radiator");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator)).et_text1.getText().toString());
			
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator)).mButton.getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'skirting_boards_or_radiator'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		
		//doors_or_locks_or_keys_or_handles
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys)).et_text1.getText().toString());
			
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_doors_locks_keys"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_doors_locks_keys)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_doors_locks_keys)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "hall_landing_pre_departure_doors_locks_keys");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys)).et_text1.getText().toString());
			
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys)).mButton.getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		//windows_or_locks_or_keys_or_handles
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_window_locks_keys_handles"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles)).mButton.getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'windows_or_locks_or_keys_or_handles'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		//lighting_or_bulbs_or_sheds
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_lighting_bulbs_shades"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades)).mButton.getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulbs_or_sheds'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		//soft_furnishing
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_handles_doors_fininsh"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh)).mButton.getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'soft_furnishing'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		
		//wooden_furnishing
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_table_chair_coatstand"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand");
		}
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_table_chair_coatstand)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_table_chair_coatstand)).mButton.getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_departure_table_chair_coatstand");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'wooden_furnishing'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		
		//curtains_or_blinds
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset)).mButton.getTag().toString());
		}                                                                  
		catch(Exception e)
		{
			Log.v("for", "hall_landing_check_in_phon_headset"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_headset)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_headset)).mButton.getTag().toString());                                                        
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_phon_headset");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_headset)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_headset)).mButton.getTag().toString());
			                                                              
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_phon_headset");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		//mirror_or_picture
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "textPicLayout_dinning_room_hall_landing_check_in_phon_directory"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_directory)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_directory)).mButton.getTag().toString());
			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_phon_directory");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_directory)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_directory)).mButton.getTag().toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_departure_phon_directory");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'mirror_or_picture'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		
		
		//miscellaneous
		contentValues = new ContentValues();
		
		try{
			contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous)).et_text1.getText().toString());
			contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous)).mButton.getTag().toString());
		}
		catch(Exception e)
		{
			Log.v("for", "textPicLayout_dinning_room_hall_landing_check_in_miscellaneous"+ ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous)).et_text1.getText().toString());
		}
		
		try {
			contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous)).et_text1.getText().toString());
			contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous)).mButton.getTag().toString());

			
		} catch (Exception e) {
			Log.v("for", "textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous");
		}
		
		try {
			contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_miscellaneous)).et_text1.getText().toString());
			contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_miscellaneous)).mButton.getTag().toString());

		} catch (Exception e) {
			// TODO: handle exception
			Log.v("for", "hall_landing_departure_miscellaneous");

		}
		
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
		Log.v("updated for", "DINING_ROOM"+Success);
		
		
		
		//action_plan_if_required
		contentValues = new ContentValues();
		try{
			contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_action_plan_editText)).getText().toString());
		}
		catch(Exception e){
			Log.e("for", "hall_landing_check_in_action_plan");
		}
		contentValues.put("checkin_img", "");
		try{
			contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_action_plan_editText)).getText().toString());

			
		}catch(Exception e){
			Log.v("for", "hall_landing_pre_departure_action_plan");
		}
		contentValues.put("predepart_img","");
		try{
			contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_action_plan_editText)).getText().toString());
			
		}catch(Exception e){
			Log.v("for", "hall_landing_departure_action_plan");
		}
		contentValues.put("depart_img","");
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'action_plan_if_required'", null);
		Log.v("updated for", "action_plan_if_required"+Success);
		
		
		//follow_up
		contentValues = new ContentValues();
		try{
			contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_follow_up_editText)).getText().toString());
			
		}
		catch(Exception e){
			Log.e("for", "hall_landing_check_in_follow_up"+((EditText)findViewById(R.id.hall_landing_check_in_follow_up_editText)).getText().toString());
		}
		contentValues.put("checkin_img", "");
		try{
			contentValues.put("predepart_comm", ((EditText)findViewById(R.id.check_in_follow_up_editText)).getText().toString());
			
		}catch(Exception e){
			Log.v("for", "check_in_follow_up");
		}
		contentValues.put("predepart_img","");
		try{
			contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_follow_up_editText)).getText().toString());
			
		}catch(Exception e){
			Log.v("for", "hall_landing_departure_follow_up");
		}
		contentValues.put("depart_img","");
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'follow_up'", null);
		Log.v("updated for", "follow_up"+Success);
		
		
		//final_comments
		contentValues = new ContentValues();
		try{
			contentValues.put("checkin_comm", ((EditText)findViewById(R.id.hall_landing_check_in_final_comments_editText)).getText().toString());
			
		}
		catch(Exception e){
			Log.e("for", "hall_landing_check_in_final_comments");
		}
		contentValues.put("checkin_img", "");
		try{
			contentValues.put("predepart_comm", ((EditText)findViewById(R.id.hall_landing_pre_departure_comments_editText)).getText().toString());
			
		}catch(Exception e){
			Log.v("for", "downstairs_WC_pre_departure_final_comments");
		}
		contentValues.put("predepart_img","");
		try{
			contentValues.put("depart_comm", ((EditText)findViewById(R.id.hall_landing_departure_comments_editText)).getText().toString());
			
		}catch(Exception e){
			Log.v("for", "hall_landing_departure_comments");
		}
		contentValues.put("depart_img","");
		Success = db.MyDB().update("DINING_ROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'final_comments'", null);
		Log.v("updated for", "final_comments"+Success);
		
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "DINING_ROOM", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"DINING_ROOM", editJobId);
		
		try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "DINING_ROOM", editJobId,"create/update",InteriorCloakRoom.class);
				s2sv.frontEndSend();
			//}else{		
			}
			}catch(Exception e){
				e.getStackTrace();
			}
	
}
	
	
	
	
	
	
	
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "DINING_ROOM", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring_or_carpet_or_rugs"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_carpetRug)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_carpetRug)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_carpetRug)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_carpetRug)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_carpetRug)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("paint_or_paperwall_or_ceiling"))
				{                                
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_wooden_floor)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_wooden_floor)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_wooden_floor)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_wooden_floor)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_wooden_floor)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("skirting_boards_or_radiator"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_skirting_boards_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("doors_or_locks_or_keys_or_handles"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_doors_locks_keys)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_doors_locks_keys)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("windows_or_locks_or_keys_or_handles"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_window_locks_keys_handles)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lighting_or_bulbs_or_sheds"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_lighting_bulbs_shades)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("soft_furnishing"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_handles_doors_fininsh)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("wooden_furnishing"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_table_chair_coatstand)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_table_chair_coatstand)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					
					
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("curtains_or_blinds"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_headset)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_headset)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_headset)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("mirror_or_picture"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_directory)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_directory)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_phone_directory)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_check_in_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("checkin_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_pre_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("predepart_img")));
					((TextPicLayout) findViewById(R.id.textPicLayout_dinning_room_hall_landing_departure_miscellaneous)).mButton.setTag(cursor.getString(cursor.getColumnIndex("depart_img")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("action_plan_if_required"))
				{
					((EditText)findViewById(R.id.hall_landing_check_in_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.hall_landing_pre_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.hall_landing_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("follow_up"))
				{
					((EditText)findViewById(R.id.hall_landing_check_in_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.check_in_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.hall_landing_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("final_comments"))
				{
					((EditText)findViewById(R.id.hall_landing_check_in_final_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.hall_landing_pre_departure_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.hall_landing_departure_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
			}
		}
	}
}