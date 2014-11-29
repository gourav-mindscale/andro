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

public class InteriorCloakRoom extends ActionBarActivity implements OnClickListener {
	public static final int SIGNATURE_ACTIVITY = 3;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	ImageView camerapic;
	TextPicLayout mTextPicLayout;
	String strtest="dclock room test";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interior_clockroom);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		
		if(!editJobId.equals("0"))
			renderData();                                 
		
  //checkin	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_floor_covering);
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_tiles_grouting_seals);
		
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_paint_paper_walls_celling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_doors_windows_keys_locks);
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_lightfixture_bulbs_shades);
		mTextPicLayout.et_text1.setHint(R.string.lighting_fixture_title);
		mTextPicLayout.tv_text1.setText(R.string.lighting_fixture_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_toilet_flush_sink);
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);
		
		mTextPicLayout.et_text1.setText(strtest);

		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_checkin_toilet_brush_roll_holder);
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_roll_holder_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_roll_holder_title);
		
		mTextPicLayout.et_text1.setText(strtest);

		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_towel_rail);
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_title);
		
		mTextPicLayout.et_text1.setText(strtest);

	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_cabinet_shalves_mirror);
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);
		
		mTextPicLayout.et_text1.setText(strtest);

	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_extractorfan_radiator);
		mTextPicLayout.et_text1.setHint(R.string.extractorfan_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.extractorfan_radiator_title);
		
		mTextPicLayout.et_text1.setText(strtest);

	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);

		mTextPicLayout.et_text1.setText(strtest);

//pre depurture		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_floor_covering);
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_tiles_grouting_seals);
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_paint_paper_walls_celling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_doors_windows_keys_locks);
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtest);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_lightfixture_bulbs_shades);
		mTextPicLayout.et_text1.setHint(R.string.lighting_fixture_title);
		mTextPicLayout.tv_text1.setText(R.string.lighting_fixture_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_flush_sink);
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_brush_roll_holder);
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_roll_holder_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_roll_holder_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_towel_rail);
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_cabinet_shalves_mirror);
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_extractorfan_radiator);
		mTextPicLayout.et_text1.setHint(R.string.extractorfan_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.extractorfan_radiator_title);

		mTextPicLayout.et_text1.setText(strtest);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		
		mTextPicLayout.et_text1.setText(strtest);


//	deputture
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_floor_covering);
		mTextPicLayout.et_text1.setHint(R.string.floor_covering_title);
		mTextPicLayout.tv_text1.setText(R.string.floor_covering_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_tiles_grouting_seals);
		mTextPicLayout.et_text1.setHint(R.string.tiles_grouting_seals_title);
		mTextPicLayout.tv_text1.setText(R.string.tiles_grouting_seals_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_paint_paper_walls_celling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_doors_windows_keys_locks);
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_);
		mTextPicLayout.et_text1.setHint(R.string.lighting_fixture_title);
		mTextPicLayout.tv_text1.setText(R.string.lighting_fixture_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink);
		mTextPicLayout.et_text1.setHint(R.string.toilet_flush_sink_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_flush_sink_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder);
		mTextPicLayout.et_text1.setHint(R.string.toilet_brush_roll_holder_title);
		mTextPicLayout.tv_text1.setText(R.string.toilet_brush_roll_holder_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_towel_rail);
		mTextPicLayout.et_text1.setHint(R.string.towel_rail_title);
		mTextPicLayout.tv_text1.setText(R.string.towel_rail_title);

		
		mTextPicLayout.et_text1.setText(strtest);

		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_cabinet_shalves_mirror);
		mTextPicLayout.et_text1.setHint(R.string.cabinet_shalves_mirror_title);
		mTextPicLayout.tv_text1.setText(R.string.cabinet_shalves_mirror_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_extractorfan_radiator);
		mTextPicLayout.et_text1.setHint(R.string.extractorfan_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.extractorfan_radiator_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_curtains_blinds);
		mTextPicLayout.et_text1.setHint(R.string.curtains_blinds_title);
		mTextPicLayout.tv_text1.setText(R.string.curtains_blinds_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		mTextPicLayout.et_text1.setText(strtest);


		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
		startActivity(new Intent(this,Interior.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorLiving.class));
		finish();
	}
	public void saveData(View v){
		
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
		//floor_covering
			contentValues = new ContentValues();
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_floor_covering)).et_text1.getText().toString());
				contentValues.put("checkin_img", ((TextPicLayout) findViewById(R.id.textPicLayout_downstairs_WC_check_in_floor_covering)).mButton.getTag().toString());

			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_floor_covering_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).getText().toString());
			}
			
			try {
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).getText().toString());
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_floor_covering)).et_text1.getText().toString());
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_floor_covering)).mButton.getTag().toString());	

				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_floor_covering_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_floor_covering)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_floor_covering)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_floor_covering_editText");

			}
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'floor_covering'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
	//tiles_or_grouting_or_seals
			
			
			
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_tiles_grouting_seals)).et_text1.getText().toString());
				
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_tiles_grouting_seals)).mButton.getTag().toString());

			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_tiles_grouting_seals_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_tiles_grouting_seals_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_tiles_grouting_seals)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_tiles_grouting_seals)).mButton.getTag().toString());
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_tiles_grouting_seals_editText");
			}
			
			try {
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.downstairs_WC_departure_tiles_grouting_seals_editText)).getText().toString());
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_tiles_grouting_seals)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_tiles_grouting_seals)).mButton.getTag().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_tiles_grouting_seals_editText");

			}
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'tiles_or_grouting_or_seals'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
			
			
	//paint_or_paperwall_or_ceiling
			contentValues = new ContentValues();
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_paint_paper_walls_celling_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_paint_paper_walls_celling)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_paint_paper_walls_celling)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_paint_paper_walls_celling_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_paint_paper_walls_celling_editText)).getText().toString());
			}
			
			try {
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.downstairs_WC_pre_departure_paint_paper_walls_celling_editText)).getText().toString());
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_paint_paper_walls_celling)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_paint_paper_walls_celling)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_paint_paper_walls_celling_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_paint_paper_walls_celling)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_paint_paper_walls_celling)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_paint_paper_walls_celling_editText");

			}
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
			
			
//door_or_window_or_key_or_lock
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_doors_windows_keys_locks)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_doors_windows_keys_locks)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_doors_windows_keys_locks_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_doors_windows_keys_locks_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_doors_windows_keys_locks)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_doors_windows_keys_locks)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_doors_windows_keys_locks_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_doors_windows_keys_locks)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_doors_windows_keys_locks)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_doors_windows_keys_locks_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'door_or_window_or_key_or_lock'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
			
//lightfixture_or_bulb_or_shed
			
			
			contentValues = new ContentValues();
			
			try{
			//	contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_lightfixture_bulbs_shades_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_lightfixture_bulbs_shades)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_lightfixture_bulbs_shades)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_lightfixture_bulbs_shades_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_lightfixture_bulbs_shades_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_lightfixture_bulbs_shades)).et_text1.getText().toString());
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_lightfixture_bulbs_shades)).mButton.getTag().toString());

				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_lightfixture_bulbs_shades_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_)).mButton.getTag().toString());                                                      

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_lightfixture_bulbs_shades_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lightfixture_or_bulb_or_shed'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			

			
			
		
//toilet_or_flush_or_sink_or_tap_stopper
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_toilet_flush_sink)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_toilet_flush_sink)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_toilet_flush_sink_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_toilet_flush_sink_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_flush_sink)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_flush_sink)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_toilet_flush_sink_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_toilet_flush_sink_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'toilet_or_flush_or_sink_or_tap_stopper'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			

	//toilet_brush_or_toilet_roll_header
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_checkin_toilet_brush_roll_holder)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_checkin_toilet_brush_roll_holder)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_checkin_toilet_brush_roll_holder_editText"+ ((EditText)findViewById(R.id.downstairs_WC_checkin_toilet_brush_roll_holder_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_brush_roll_holder)).et_text1.getText().toString());
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_brush_roll_holder)).mButton.getTag().toString());

				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_toilet_brush_roll_holder_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_toilet_brush_roll_holder_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'toilet_brush_or_toilet_roll_header'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
//towel_rail
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_towel_rail)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_towel_rail)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_towel_rail_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_towel_rail_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_towel_rail)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_towel_rail)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_towel_rail_editText");
			}
			
			try {
			//	contentValues.put("depart_comm", ((EditText)findViewById(R.id.downstairs_WC_departure_towel_rail_editText)).getText().toString());
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_towel_rail)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_towel_rail)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_towel_rail_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'towel_rail'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
			
//cabinet_or_shelve_or_mirror
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_cabinet_shalves_mirror)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_cabinet_shalves_mirror)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_cabinet_shalves_mirror_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_cabinet_shalves_mirror_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_cabinet_shalves_mirror)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_cabinet_shalves_mirror)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_cabinet_shalves_mirror_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_cabinet_shalves_mirror)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_cabinet_shalves_mirror)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_cabinet_shalves_mirror_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'cabinet_or_shelve_or_mirror'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
			
//extractor_fan_or_radiator
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_extractorfan_radiator)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_extractorfan_radiator)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_extractorfan_radiator_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_extractorfan_radiator_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_extractorfan_radiator)).et_text1.getText().toString());
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_extractorfan_radiator)).mButton.getTag().toString());

				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_extractorfan_radiator_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_extractorfan_radiator)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_extractorfan_radiator)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_extractorfan_radiator_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'extractor_fan_or_radiator'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
		
//curtains_or_blinds
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_curtains_blinds)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_curtains_blinds)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_curtains_blinds_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_curtains_blinds_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_curtains_blinds)).et_text1.getText().toString());
				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_curtains_blinds)).mButton.getTag().toString());

				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_curtains_blinds_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_curtains_blinds)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_curtains_blinds)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_curtains_blinds_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			
	//miscellaneous
			contentValues = new ContentValues();
			
			try{
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_miscellaneous)).et_text1.getText().toString());
				contentValues.put("checkin_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_miscellaneous)).mButton.getTag().toString());
			}
			catch(Exception e)
			{
				Log.v("for", "downstairs_WC_check_in_miscellaneous_editText"+ ((EditText)findViewById(R.id.downstairs_WC_check_in_miscellaneous_editText)).getText().toString());
			}
			
			try {
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_miscellaneous)).et_text1.getText().toString());

				contentValues.put("predepart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_miscellaneous)).mButton.getTag().toString());
				
			} catch (Exception e) {
				Log.v("for", "downstairs_WC_pre_departure_miscellaneous_editText");
			}
			
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).et_text1.getText().toString());
				contentValues.put("depart_img",((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).mButton.getTag().toString());

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "downstairs_WC_departure_miscellaneous_editText");

			}
			
			
			
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
			Log.v("updated for", "WC_OR_CLOAKROOM"+Success);
			
			//action_plan_if_required
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_action_plan_if_required_editText)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "downstairs_WC_check_in_action_plan_if_required_editText");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.downstairs_WC_pre_departure_action_plan_if_required_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_pre_departure_action_plan_if_required_editText");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.downstairs_WC_departure_action_plan_if_required_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_departure_action_plan_if_required_editText");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'action_plan_if_required'", null);
			Log.v("updated for", "action_plan_if_required"+Success);
			
			//follow_up
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_follow_up_editText)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "downstairs_WC_check_in_follow_up_editText"+((EditText)findViewById(R.id.downstairs_WC_check_in_follow_up_editText)).getText().toString());
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.downstairs_WC_pre_departure_follow_up_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_pre_departure_follow_up_editText");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.downstairs_WC_departure_follow_up_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_departure_follow_up_editText");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'follow_up'", null);
			Log.v("updated for", "follow_up"+Success);
			
			//final_comments
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.downstairs_WC_check_in_final_comments_editText)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "downstairs_WC_check_in_final_comments_editText");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.downstairs_WC_pre_departure_final_comments_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_pre_departure_final_comments_editText");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.downstairs_WC_departure_final_comments_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "downstairs_WC_departure_final_comments_editText");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("WC_OR_CLOAKROOM", contentValues, "jobid ="+editJobId+" AND inspect_type = 'final_comments'", null);
			Log.v("updated for", "final_comments"+Success);
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "WC_OR_CLOAKROOM", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"WC_OR_CLOAKROOM", editJobId);
			
			try{
				//if(status != null && status.getInt(2) == 1)
				{
					SendToServer s2sv = new SendToServer(db, this, true, "WC_OR_CLOAKROOM", editJobId,"create/update",InteriorCloakRoom.class);
					s2sv.frontEndSend();
				//}else{		
				}
				}catch(Exception e){
					e.getStackTrace();
				}
	}
	
	
	
	
	
	
	
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "WC_OR_CLOAKROOM", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("floor_covering"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_floor_covering)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					 String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_floor_covering)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_floor_covering)).imgArr[i]);
						}						
					}
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_floor_covering)).imgArr[i]);
						}						
					}
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("tiles_or_grouting_or_seals"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_tiles_grouting_seals_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_tiles_grouting_seals_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_tiles_grouting_seals_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_tiles_grouting_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_tiles_grouting_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_tiles_grouting_seals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_tiles_grouting_seals)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_tiles_grouting_seals_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_tiles_grouting_seals)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_tiles_grouting_seals_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_tiles_grouting_seals)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_tiles_grouting_seals_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("paint_or_paperwall_or_ceiling"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_paint_paper_walls_celling_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_paint_paper_walls_celling_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_paint_paper_walls_celling_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_paint_paper_walls_celling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_paint_paper_walls_celling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_paint_paper_walls_celling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_paint_paper_walls_celling)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_paint_paper_walls_celling_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_paint_paper_walls_celling)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_paint_paper_walls_celling_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_paint_paper_walls_celling)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_paint_paper_walls_celling_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("door_or_window_or_key_or_lock"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_doors_windows_keys_locks_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_doors_windows_keys_locks_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_doors_windows_keys_locks_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_doors_windows_keys_locks)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_doors_windows_keys_locks)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_doors_windows_keys_locks)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_doors_windows_keys_locks)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_doors_windows_keys_locks_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_doors_windows_keys_locks)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_doors_windows_keys_locks_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_doors_windows_keys_locks)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_doors_windows_keys_locks_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lightfixture_or_bulb_or_shed"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_lightfixture_bulbs_shades_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_lightfixture_bulbs_shades_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_lightfixture_bulbs_shades_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_lightfixture_bulbs_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_lightfixture_bulbs_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_lightfixture_bulbs_shades)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_lightfixture_bulbs_shades_imageView);*/
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_lightfixture_bulbs_shades_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_lightfixture_bulbs_shades)).imgArr[i]);
						}						
					}

					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_lightfixture_bulbs_shades_)).imgArr[i]);
						}						
					}

					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("toilet_or_flush_or_sink_or_tap_stopper"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_toilet_flush_sink_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_toilet_flush_sink_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_toilet_flush_sink_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_toilet_flush_sink)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_flush_sink)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_toilet_flush_sink)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_toilet_flush_sink_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_toilet_flush_sink_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_flush_sink)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_toilet_flush_sink_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("toilet_brush_or_toilet_roll_header"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_checkin_toilet_brush_roll_holder_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_toilet_brush_roll_holder_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_toilet_brush_roll_holder_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_checkin_toilet_brush_roll_holder)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_brush_roll_holder)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
				String	path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_checkin_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_checkin_toilet_brush_roll_holder_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_toilet_brush_roll_holder_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_toilet_brush_roll_holder_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("towel_rail"))
				{/*
					((EditText)findViewById(R.id.downstairs_WC_check_in_towel_rail_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_towel_rail_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_towel_rail_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_towel_rail)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_towel_rail)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_towel_rail)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
				String	path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_towel_rail_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_towel_rail_imageView);*/
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_toilet_brush_roll_holder)).imgArr[i]);
						}						
					}
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_towel_rail_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("cabinet_or_shelve_or_mirror"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_cabinet_shalves_mirror_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_cabinet_shalves_mirror_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_cabinet_shalves_mirror_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_cabinet_shalves_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_cabinet_shalves_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_cabinet_shalves_mirror)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_cabinet_shalves_mirror_imageView);*/
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_cabinet_shalves_mirror)).imgArr[i]);
						}						
					}

					/*showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_cabinet_shalves_mirror_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_cabinet_shalves_mirror)).imgArr[i]);
						}						
					}

					/*showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_cabinet_shalves_mirror_imageView);*/
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_cabinet_shalves_mirror)).imgArr[i]);
						}						
					}

					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("extractor_fan_or_radiator"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_extractorfan_radiator_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_extractorfan_radiator_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_extractorfan_radiator_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_extractorfan_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_extractorfan_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_extractorfan_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
				String 	path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_extractorfan_radiator)).imgArr[i]);
						}						
					}

					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_extractorfan_radiator)).imgArr[i]);
						}						
					}

					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_extractorfan_radiator)).imgArr[i]);
						}						
					}

					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_extractorfan_radiator_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_extractorfan_radiator_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_extractorfan_radiator_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("curtains_or_blinds"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_curtains_blinds_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_curtains_blinds_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_curtains_blinds_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_curtains_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_curtains_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_curtains_blinds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_curtains_blinds_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_curtains_blinds_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_curtains_blinds_imageView);*/
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_curtains_blinds)).imgArr[i]);
						}						
					}

					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_curtains_blinds)).imgArr[i]);
						}						
					}

					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_curtains_blinds)).imgArr[i]);
						}						
					}

					
					
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_miscellaneous_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_check_in_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_pre_departure_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_downstairs_WC_departure_miscellaneous)).imgArr[i]);
						}						
					}
					
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_miscellaneous_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_miscellaneous_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_miscellaneous_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("action_plan_if_required"))
				{
					((EditText)findViewById(R.id.downstairs_WC_check_in_action_plan_if_required_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_action_plan_if_required_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_action_plan_if_required_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("follow_up"))
				{
					((EditText)findViewById(R.id.downstairs_WC_check_in_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("final_comments"))
				{
					((EditText)findViewById(R.id.downstairs_WC_check_in_final_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_final_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_final_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
			}
		}
	}

	
	
}