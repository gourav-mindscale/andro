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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
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

public class InteriorBedroomFour extends ActionBarActivity {
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
		setContentView(R.layout.bedroom_4);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
		    renderData();
		//((Button) findViewById(R.id.proctonext)).setText("Finish");
		
		                                                  
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs);		
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard);		
		mTextPicLayout.et_text1.setHint(R.string.wardrobes_or_furniture_or_headboard_title);
		mTextPicLayout.tv_text1.setText(R.string.wardrobes_or_furniture_or_headboard_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector);		
		mTextPicLayout.et_text1.setHint(R.string.bed_or_matress_or_protector_title);
		mTextPicLayout.tv_text1.setText(R.string.bed_or_matress_or_protector_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets);		
		mTextPicLayout.et_text1.setHint(R.string.bedlinen_title);
		mTextPicLayout.tv_text1.setText(R.string.bedlinen_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds);		
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		
		
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs);		
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard);		
		mTextPicLayout.et_text1.setHint(R.string.wardrobes_or_furniture_or_headboard_title);
		mTextPicLayout.tv_text1.setText(R.string.wardrobes_or_furniture_or_headboard_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector);		
		mTextPicLayout.et_text1.setHint(R.string.bed_or_matress_or_protector_title);
		mTextPicLayout.tv_text1.setText(R.string.bed_or_matress_or_protector_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets);		
		mTextPicLayout.et_text1.setHint(R.string.bedlinen_title);
		mTextPicLayout.tv_text1.setText(R.string.bedlinen_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds);		
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs);		
		mTextPicLayout.et_text1.setHint(R.string.flooring_carpets_rugs_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_carpets_rugs_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling);		
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator);		
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles);		
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds);		
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard);		
		mTextPicLayout.et_text1.setHint(R.string.wardrobes_or_furniture_or_headboard_title);
		mTextPicLayout.tv_text1.setText(R.string.wardrobes_or_furniture_or_headboard_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector);		
		mTextPicLayout.et_text1.setHint(R.string.bed_or_matress_or_protector_title);
		mTextPicLayout.tv_text1.setText(R.string.bed_or_matress_or_protector_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets);		
		mTextPicLayout.et_text1.setHint(R.string.bedlinen_title);
		mTextPicLayout.tv_text1.setText(R.string.bedlinen_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds);		
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous);		
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
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
		
		}
		 */
		
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
		startActivity(new Intent(this,InteriorBedroomThree.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorBedroomFive.class));
		finish();
	}
	public void saveData(View v){
		
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
			contentValues = new ContentValues();
			
			
			
			
			
			
		//  flooring_or_carpet_or_rugs
						try {
							
							contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString() );
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img", imagePath);
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
						}
						try {
							contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).et_text1.getText().toString() );
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs");

						} 
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img", imagePath);

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).iv_pic4.getTag().toString();
							contentValues.put("depart_img", imagePath);

						} catch (Exception e) {
							Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'flooring_or_carpet_or_rugs'", null);
						Log.v("updated for", "flooring_or_carpet_or_rugs"+Success);		
					
					
						
						
		//  paint_or_paperwall_or_ceiling
						try {
							
							contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceilingException");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img", imagePath);
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
						}
						try {
							contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling");

						} 
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img", imagePath);

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
							contentValues.put("depart_img",imagePath  );
							

						} catch (Exception e) {
							Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
						Log.v("updated for", "paint_or_paperwall_or_ceiling"+Success);		
					
						
		//skirting_boards_or_radiator
						contentValues = new ContentValues();
						
						try{
							contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).et_text1.getText().toString());
						}
						catch(Exception e)
						{
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator"+ ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).et_text1.getText().toString());
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img",imagePath);

						} catch (Exception e) {
							Log.v("for", "hall_landing_check_in_skirting_boards_radiator_imageView");
						}
						try {
							contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).et_text1.getText().toString());

							
						} catch (Exception e) {
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img",imagePath);	
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_skirting_boards_radiator_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).et_text1.getText().toString());

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_dinning_room_hall_landing_departure_skirting_boards_radiator");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
							contentValues.put("depart_img",imagePath);
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_departure_skirting_boards_radiator_imageView");

						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'skirting_boards_or_radiator'", null);
						Log.v("updated for", "skirting_boards_or_radiator"+Success);
						
						
						
		//doors_or_locks_or_keys_or_handles  
						try {
							
							contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
							Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img",imagePath);
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
						}
						try {
							contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles");

						} 
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img", imagePath);

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).et_text1.getText().toString());
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("depart_img", imagePath);

						} catch (Exception e) {
							Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles'", null);
						Log.v("updated for", "doors_or_locks_or_keys_or_handles"+Success);		
					
		// windows_or_locks_or_keys_or_handles 
						try {                                                                  
							
							contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img", imagePath);
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
						}
						try {
							contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
						} catch (Exception e) {                                                  
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles");

						} 
						try {
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img", imagePath);

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString());
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
							contentValues.put("depart_img", imagePath);

						} catch (Exception e) {
							Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'windows_or_locks_or_keys_or_handles'", null);
						Log.v("updated for", "windows_or_locks_or_keys_or_handles"+Success);		
												
						


		//lighting_or_bulbs_or_sheds  
						try {                                                                  
							
							contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
							Log.v("for", "textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).et_text1.getText().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
							contentValues.put("checkin_img",imagePath);
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
						}
						try {
							contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
						} catch (Exception e) {                                                  
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds");

						} 
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
							contentValues.put("predepart_img",imagePath);

						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
						}
						try {
							contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString());
						} catch (Exception e) {
							// TODO: handle exception
							Log.v("for", "textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds");

						}
						try {
							
							String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
									+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
							contentValues.put("depart_img",imagePath);

						} catch (Exception e) {
							Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
						}
						Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulbs_or_sheds'", null);
						Log.v("updated for", "lighting_or_bulbs_or_sheds"+Success);		
									
					
			
						
						
						
					// wardrobes_or_furniture_or_headboard 
									try {                                                                  
										
										contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).et_text1.getText().toString() );
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).et_text1.getText().toString());
										
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard");
									}
									try {
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).iv_pic4.getTag().toString();
										contentValues.put("checkin_img", imagePath);
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
									}
									try {
										contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).et_text1.getText().toString() );
									} catch (Exception e) {                                                  
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard");

									} 
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).iv_pic4.getTag().toString();
										contentValues.put("predepart_img", imagePath);

									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
									}
									try {
										contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).et_text1.getText().toString());
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard");

									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).iv_pic4.getTag().toString();
										contentValues.put("depart_img", imagePath);

									} catch (Exception e) {
										Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
									}
									Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'wardrobes_or_furniture_or_headboard'", null);
									Log.v("updated for", "wardrobes_or_furniture_or_headboard"+Success);		
									
									
									
									
									
									
									
			//  bed_or_matress_or_protector
									try {                                                                  
										
										contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).et_text1.getText().toString() );
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).et_text1.getText().toString());
										
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).iv_pic4.getTag().toString();
										contentValues.put("checkin_img",imagePath);
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
									}
									try {
										contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).et_text1.getText().toString() );
									} catch (Exception e) {                                                  
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector");

									} 
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).iv_pic4.getTag().toString();
										contentValues.put("predepart_img",imagePath);

									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
									}
									try {
										contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).et_text1.getText().toString());
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector");

									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).iv_pic4.getTag().toString();
										contentValues.put("depart_img",imagePath);

									} catch (Exception e) {
										Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
									}
									Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'bed_or_matress_or_protector'", null);
									Log.v("updated for", "bed_or_matress_or_protector"+Success);		
									
									
									
									
		//  bedlinens_or_duvets_or_pillows_or_blankets
									try {                                                                  
										
										contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.getText().toString() );
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.getText().toString());
										
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic4.getTag().toString();
										contentValues.put("checkin_img", imagePath);
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
									}
									try {
										contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.getText().toString() );
									} catch (Exception e) {                                                  
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets");

									} 
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic4.getTag().toString();
										contentValues.put("predepart_img",imagePath);

									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
									}
									try {
										contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.getText().toString());
									} catch (Exception e) {
										// TODO: handle exception
							 			Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets");

									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).iv_pic4.getTag().toString();
										contentValues.put("depart_img", imagePath);

									} catch (Exception e) {
										Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
									}
									Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'bedlinens_or_duvets_or_pillows_or_blankets'", null);
									Log.v("updated for", "bedlinens_or_duvets_or_pillows_or_blankets"+Success);		
									
									
									
									
		//  curtains_or_blinds
									try {                                                                  
										
										contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).et_text1.getText().toString() );
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_curtains_or_blinds="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).et_text1.getText().toString());
										
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).iv_pic4.getTag().toString();
										contentValues.put("checkin_img",imagePath);
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
									}
									try {
										contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).et_text1.getText().toString() );
									} catch (Exception e) {                                                  
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds");

									} 
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).iv_pic4.getTag().toString();
										contentValues.put("predepart_img", imagePath);

									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
									}
									try {
										contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).et_text1.getText().toString());
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_departure_curtains_or_blinds");

									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).iv_pic4.getTag().toString();
										contentValues.put("depart_img", imagePath);

									} catch (Exception e) {
										Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
									}
									Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds'", null);
									Log.v("updated for", "curtains_or_blinds"+Success);		
									
									
									
									
				//  miscellaneous
									try {                                                                  
										
										contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).et_text1.getText().toString() );
										Log.v("for", "="+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).et_text1.getText().toString());
										
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_check_in_miscellaneous");
									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).iv_pic4.getTag().toString();
										contentValues.put("checkin_img", imagePath);
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
									}
									try {
										contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).et_text1.getText().toString() );
									} catch (Exception e) {                                                  
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_pre_departure_miscellaneous");

									} 
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).iv_pic4.getTag().toString();
										contentValues.put("predepart_img",imagePath);

									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
									}
									try {
										contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).et_text1.getText().toString());
									} catch (Exception e) {
										// TODO: handle exception
										Log.v("for", "textPicLayout_bedroom4_interior_departure_miscellaneous");

									}
									try {
										
										String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).iv_pic1.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).iv_pic2.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).iv_pic3.getTag().toString()
												+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).iv_pic4.getTag().toString();
										contentValues.put("depart_img",imagePath);

									} catch (Exception e) {
										Log.v("for", "textPicLayout_utility_departure_wooden_floor_imageView");
									}
									Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
									Log.v("updated for", "miscellaneous"+Success);		
									
									
			
			
			
			/*
			String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
					"bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
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
					
				
				Success = db.MyDB().update("BEDROOM_4", contentValues, "jobid ="+editJobId+" AND inspect_type = '"+strArray[i]+"'", null);
				Log.v("updated for", "BEDROOM_4"+Success);
				
			}
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "BEDROOM_4", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"BEDROOM_4", editJobId);
			
			try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "BEDROOM_4", editJobId,"create/update",InteriorCloakRoom.class);
				s2sv.frontEndSend();
			//}else{		
			}
			}catch(Exception e){
				e.getStackTrace();
			}
		
	}
	
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "BEDROOM_4", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				
				
				
				
				//flooring_or_carpet_or_rugs
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring_or_carpet_or_rugs"))
				{                                     
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_flooring_or_carpet_or_rugs)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
				
//paint_or_paperwall_or_ceiling
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("paint_or_paperwall_or_ceiling"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
//skirting_boards_or_radiator
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("skirting_boards_or_radiator"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_skirting_boards_or_radiator)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_skirting_boards_or_radiator)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_skirting_boards_or_radiator)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
//doors_or_locks_or_keys_or_handles
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("doors_or_locks_or_keys_or_handles"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_doors_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_doors_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_doors_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
//windows_or_locks_or_keys_or_handles
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("windows_or_locks_or_keys_or_handles"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
							
//lighting_or_bulbs_or_sheds
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lighting_or_bulbs_or_sheds"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
					cursor.moveToNext();
					continue;
				}
				
				
//wardrobes_or_furniture_or_headboard
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("wardrobes_or_furniture_or_headboard"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_wardrobes_or_furniture_or_headboard)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_wardrobes_or_furniture_or_headboard)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_wardrobes_or_furniture_or_headboard)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
//bed_or_matress_or_protector
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("bed_or_matress_or_protector"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bed_or_matress_or_protector)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bed_or_matress_or_protector)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bed_or_matress_or_protector)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
				
//bedlinens_or_duvets_or_pillows_or_blankets
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("bedlinens_or_duvets_or_pillows_or_blankets"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_bedlinens_or_duvets_or_pillows_or_blankets)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_bedlinens_or_duvets_or_pillows_or_blankets)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_bedlinens_or_duvets_or_pillows_or_blankets)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
				
//
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("curtains_or_blinds"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_curtains_or_blinds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_curtains_or_blinds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_curtains_or_blinds)).imgArr[i]);
						}						
					}
					
					cursor.moveToNext();
					continue;
				}
				
				
//miscellaneous
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
				{
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
				
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_check_in_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_pre_departure_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_bedroom4_interior_departure_miscellaneous)).imgArr[i]);
						}						
					}
					
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