package irishreloreportgen.activity.main.exterior;
import irishreloreportgen.activity.main.CommonGoToActivity;
import irishreloreportgen.activity.main.IrishreloLunch;
import irishreloreportgen.activity.main.R;
import irishreloreportgen.activity.main.db.DbHelper;
import irishreloreportgen.activity.main.db.SelectDb;
import irishreloreportgen.activity.main.db.UpdateDb;
import irishreloreportgen.activity.main.interior.Interior;
import irishreloreportgen.activity.main.serv.SendToServer;
import irishreloreportgen.activity.main.utilities.Utilities;
import irishreloreportgen.staticclassnconst.IrishreloAccess;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import customview.TextPicLayout;
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
public class Exterior extends ActionBarActivity {
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
		setContentView(R.layout.exterior_form);		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
			 renderData();
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_patio_deck_balcony_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_patio_deck_balcony_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_shade_gate_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_shade_gate_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree);		
		mTextPicLayout.et_text1.setHint(R.string.ground_grassShrubTree_title);
 		mTextPicLayout.tv_text1.setText(R.string.ground_grassShrubTree_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive);		
		mTextPicLayout.et_text1.setHint(R.string.ground_fencesWallDrive_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_fencesWallDrive_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint);		
		mTextPicLayout.et_text1.setHint(R.string.ground_walksPaint_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_walksPaint_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter);		
		mTextPicLayout.et_text1.setHint(R.string.ground_roofSlateGutter_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_roofSlateGutter_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch);		
		mTextPicLayout.et_text1.setHint(R.string.ground_doorbellLightsPorch_title);
	mTextPicLayout.tv_text1.setText(R.string.ground_doorbellLightsPorch_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine);		
		mTextPicLayout.et_text1.setHint(R.string.ground_clothesLine_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_clothesLine_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins);		
		mTextPicLayout.et_text1.setHint(R.string.ground_refuseBins_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_refuseBins_title);

		
		
mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_patio_deck_balcony_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_patio_deck_balcony_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_shade_gate_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_shade_gate_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree);		
		mTextPicLayout.et_text1.setHint(R.string.ground_grassShrubTree_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_grassShrubTree_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive);		
		mTextPicLayout.et_text1.setHint(R.string.ground_fencesWallDrive_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_fencesWallDrive_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint);		
		mTextPicLayout.et_text1.setHint(R.string.ground_walksPaint_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_walksPaint_title);
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter);		
		mTextPicLayout.et_text1.setHint(R.string.ground_roofSlateGutter_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_roofSlateGutter_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch);		
		mTextPicLayout.et_text1.setHint(R.string.ground_doorbellLightsPorch_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_doorbellLightsPorch_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth);		
		mTextPicLayout.et_text1.setHint(R.string.ground_clothesLine_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_clothesLine_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin);		
		mTextPicLayout.et_text1.setHint(R.string.ground_refuseBins_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_refuseBins_title);

		
		
mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_patio_deck_balcony_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_patio_deck_balcony_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock);		
		mTextPicLayout.et_text1.setHint(R.string.exterior_shade_gate_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.exterior_shade_gate_locks_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree);		
		mTextPicLayout.et_text1.setHint(R.string.ground_grassShrubTree_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_grassShrubTree_title);
		
mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive);		
		mTextPicLayout.et_text1.setHint(R.string.ground_fencesWallDrive_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_fencesWallDrive_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint);		
		mTextPicLayout.et_text1.setHint(R.string.ground_walksPaint_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_walksPaint_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter);		
		mTextPicLayout.et_text1.setHint(R.string.ground_roofSlateGutter_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_roofSlateGutter_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch);		
		mTextPicLayout.et_text1.setHint(R.string.ground_doorbellLightsPorch_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_doorbellLightsPorch_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes);		
		mTextPicLayout.et_text1.setHint(R.string.ground_clothesLine_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_clothesLine_title);

mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins);		
		mTextPicLayout.et_text1.setHint(R.string.ground_refuseBins_title);
		mTextPicLayout.tv_text1.setText(R.string.ground_refuseBins_title);


		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		menu.removeItem( R.id.action_exterior);
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
	
	public void showImageInIV(String path,int ID)
	{	
		ImageView setImgpic = (ImageView) findViewById(ID);
		
		Bitmap bmImg = null;
		try {
			if(path !=null && path.toString().length() !=0 )
			{
				
				File imageFile = new File(path);
				if(imageFile.exists())
				{
					/*
					Uri mUrl;
					mUrl= Uri.parse(path);
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
		switch(v.getId())
		{
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
	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,CommonGoToActivity.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,ExteriorIfApplicable.class));
		finish();
	}
	
		
	public void saveData(View v)
	{
		long Success = -1;
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
			contentValues = new ContentValues();
			
//patio_or_deck_or_balcony
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_checking_patioDeckBalconys)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "patio textPicLayout_ground_checking_patioDeckBalcony");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).iv_pic4.getTag().toString();
				Log.v("imagePath", imagePath);
				contentValues.put("checkin_img", imagePath);
				//contentValues.put("checkin_img", ((ImageView)findViewById(R.id.ground_checking_patioDeckBalcony_imageView)).getTag().toString());
				
				//contentValues.put("checkin_img", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).iv_pic1.getTag().toString());
			}catch(Exception e){
				Log.v("for", "patio checkin_img");
			}
			
			try{
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.pre_departure_patio_deck_balcony)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).et_text1.getText().toString());
			}catch(Exception e){
				Log.v("for", "patio predepart_comm");
			}
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).iv_pic4.getTag().toString();
			//	contentValues.put("predepart_img",((ImageView)findViewById(R.id.ground_pre_departure_patioDeckBalcony_imageView)).getTag().toString());
				
				contentValues.put("predepart_img",imagePath);

			}catch(Exception e){
				Log.v("for", "patio predepart_img");
			}
			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_patioDeckBalconys)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).et_text1.getText().toString());

			}catch(Exception e){
				Log.v("for", "patio depart_comm");
			}
			
			
			try{
			//	contentValues.put("depart_img",((ImageView)findViewById(R.id.ground_departure_patioDeckBalcony_imageView)).getTag().toString());//ground_departure_patioDeckBalcony_imageView
			
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);//ground_departure_patioDeckBalcony_imageView

			}catch(Exception e){
				Log.v("for", "patio depart_img");
			}
			
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'patio_or_deck_or_balcony'", null);
			Log.v("updated for", "patio_or_deck_or_balcony"+Success);
			
		
			
	//shade_or_sideGate_or_lock		
			
			contentValues = new ContentValues();
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_shadeGateLock_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).et_text1.getText().toString());

			}catch(Exception e){
				Log.v("for", "shade checkin_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).iv_pic4.getTag().toString();
				
				contentValues.put("checkin_img",imagePath);//ground_shadeGateLock_imageView
				
			}catch(Exception e){
				
				Log.v("for", "shade checkin_img");
			}
			
			try{
			//	contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_shadeGateLocks_editText)).getText().toString());
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "shade predepart_comm");
			}
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);//ground_pre_departure_shadeGateLocks_imageView
				
				
			}catch(Exception e){
				Log.v("for", "shade predepart_img");
			}
			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_shadeGateLock_editText)).getText().toString());
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "shade depart_comm");
			}
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);//ground_departure_shadeGateLock_imageView
				
			}catch(Exception e){
				Log.v("for", "shade depart_img");
			}
			
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'shade_or_sideGate_or_lock'", null);
			
			Log.v("updated for", "shade"+Success);
			
			contentValues = new ContentValues();
			

			
//grass_or_shrubs_or_trees			
			
	contentValues = new ContentValues();
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_grassShrubTree_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "grass checkin_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);//ground_grassShrubTree_imageView
				
				
			}catch(Exception e){
				Log.v("for", "grass checkin_img");
			}
			
			try{
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_grussShrubTree_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).et_text1.getText().toString());

			
			}catch(Exception e){
				Log.v("for", "grass predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);//ground_pre_departure_grussShrubTree_imageView
				

				
			}catch(Exception e){
				Log.v("for", "grass predepart_img");
			}
			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_grassShrubTree_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "grass depart_comm");
			}
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);//ground_departure_grassShrubTree_imageView
				
			}catch(Exception e){
				Log.v("for", "grass depart_img");
			}
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'grass_or_shrubs_or_trees'", null);
			
			Log.v("updated for", "grass"+Success);
			//contentValues.put("inspect_type","grass_or_shrubs_or_trees");
			
			
//fences_or_walls_or_driveway			
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","fences_or_walls_or_driveway");
			try{
			//	contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_fencesWallDrive_editText)).getText().toString());
				
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).et_text1.getText().toString());

			}
			catch(Exception e){
				Log.e("for", "fences checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);//ground_fencesWallDrive_imageView
				
			}catch(Exception e){
				Log.v("for", "fence checkin_img");
			}
			
			try{
			//	contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_fenceWallDrive_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "fence predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);//ground_pre_departure_fenceWallDrive_imageView
				
			}catch(Exception e){
				Log.v("for", "fence predepart_img");
			}			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_fenceWallDrive_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).et_text1.getText().toString());

			}catch(Exception e){
				Log.v("for", "fence depart_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);//ground_departure_fenceWallDrive_textView
				
			}catch(Exception e){
				Log.v("for", "fence depart_img");
			}
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'fences_or_walls_or_driveway'", null);
			Log.v("updated for", "fence"+Success);
			

			
			
//walks_or_paintworks
			
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","walks_or_paintworks");
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_walksPaint_editText)).getText().toString());
				
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).et_text1.getText().toString());

				
			}
			catch(Exception e){
				Log.e("for", "walkPaint checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
				
			}catch(Exception e){
				Log.v("for", "walkPaint checkin_img");
			}
			
			try{
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_walksPaint_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "walkPaint predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);
				
			}catch(Exception e){
				Log.v("for", "walkPaint predepart_img");
			}			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_walkPaint_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "walkPaint depart_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "walkPaint depart_img");
			}
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'walks_or_paintworks'", null);
			Log.v("updated for", "walkPaint"+Success);
			
			
			
			
			
//roofs_or_slates_or_gutters	
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","roofs_or_slates_or_gutters");
			
			try{
			//	contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_roofSlateGutter_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).et_text1.getText().toString());

				
				
			}
			catch(Exception e){
				Log.e("for", "roof checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "roof checkin_img");
			}
			
			try{
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_roofSlateGutter_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "roof predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "roof predepart_img");
			}			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_roofSlateGutter_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "roof depart_comm");
			}
			try{
				
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "roof depart_img");
			}

			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'roofs_or_slates_or_gutters'", null);
			
			Log.v("updated for", "roof"+Success);
			
		
			
			
//doorbell_or_lights_or_porch
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","doorbell_or_lights_or_porch");
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_doorbellLightsPorch_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).et_text1.getText().toString());

			}
			catch(Exception e){
				Log.e("for", "doorbellL checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
				
			}catch(Exception e){
				Log.v("for", "doorbellL checkin_img");
			}
			
			try{

				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_doorLightPorch_editText)).getText().toString());
				
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).et_text1.getText().toString());

				
				
			}catch(Exception e){
				Log.v("for", "doorbellL predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "doorbellL predepart_img");
			}	
			
			
			try{
			//	contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_doorLightPorch_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "doorbellL depart_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "doorbellL depart_img");
			}

			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doorbell_or_lights_or_porch'", null);
			Log.v("updated for", "doorbellL"+Success);
			
			contentValues = new ContentValues();
			
			
//clothes_line			
			
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","doorbell_or_lights_or_porch");
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_clothesLine_editText)).getText().toString());
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).et_text1.getText().toString());

			}
			catch(Exception e){
				Log.e("for", "clothe checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
				
			}catch(Exception e){
				Log.v("for", "clothe checkin_img");
			}
			
			try{
				//contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_cloth_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "clothe predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "clothe predepart_img");
			}			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_clothes_editText)).getText().toString());
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).et_text1.getText().toString());

				
				
			}catch(Exception e){
				Log.v("for", "clothe depart_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);
					
			}catch(Exception e){
				Log.v("for", "clothe depart_img");
			}
			//contentValues.put("inspect_type","clothes_line");
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'clothes_line'", null);
			Log.v("updated for", "clothe"+Success);
			
	
			
			
//refuse_bins	
			
			contentValues = new ContentValues();
			//contentValues.put("inspect_type","refuse_bins");
			
			try{
				//contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_refuseBins_editText)).getText().toString());
				
				contentValues.put("checkin_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).et_text1.getText().toString());

				
			}
			catch(Exception e){
				Log.e("for", "refuse checkin_comm");
			}	
			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
				
			}catch(Exception e){
				Log.v("for", "refuse checkin_img");
			}
			
			try{
			//	contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_refuseBin_editText)).getText().toString());
				
				contentValues.put("predepart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "refuse predepart_comm");
			}			
			
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);
				
			}catch(Exception e){
				Log.v("for", "refuse predepart_img");
			}			
			try{
				//contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_refuseBins_editText)).getText().toString());
				
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).et_text1.getText().toString());

				
			}catch(Exception e){
				Log.v("for", "refuse depart_comm");
			}
			try{
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);
						
			}catch(Exception e){
				Log.v("for", "refuse depart_img");
			}
			
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'refuse_bins'", null);
			
			
			
			
			
			
			contentValues = new ContentValues();
			try{
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_check_in_action_plan_editText)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "action checkin_comm");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_action_plan_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "action predepart_comm");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_action_plan_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "action depart_comm");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'action_plan_if_required'", null);
			Log.v("updated for", "action"+Success);
			
			contentValues = new ContentValues();
			try{			
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_check_in_follow_up_editText)).getText().toString());
				
			}
			catch(Exception e){
				Log.e("for", "follow_up checkin_comm");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_follow_up_editText)).getText().toString());
					
			}catch(Exception e){
				Log.v("for", "follow_up predepart_comm");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_follow_up_editText)).getText().toString());
				
			}catch(Exception e){
				Log.v("for", "follow_up depart_comm");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'follow_up'", null);
			Log.v("updated for", "follow_up"+Success);
			
			
			
			contentValues = new ContentValues();
			try{			
				contentValues.put("checkin_comm", ((EditText)findViewById(R.id.ground_check_in_final_comments_editText)).getText().toString());
			}
			catch(Exception e){
				Log.e("for", "final_comments checkin_comm");
			}
			contentValues.put("checkin_img", "");
			try{
				contentValues.put("predepart_comm", ((EditText)findViewById(R.id.ground_pre_departure_comments_editText)).getText().toString());
					
			}catch(Exception e){
				Log.v("for", "final_comments predepart_comm");
			}
			contentValues.put("predepart_img","");
			try{
				contentValues.put("depart_comm", ((EditText)findViewById(R.id.ground_departure_comments_editText)).getText().toString());
					
			}catch(Exception e){
				Log.v("for", "final_comments depart_comm");
			}
			contentValues.put("depart_img","");
			Success = db.MyDB().update("EXTERIOR_GROUND", contentValues, "jobid ="+editJobId+" AND inspect_type = 'final_comments'", null);
			Log.v("updated for", "final_comments"+Success);
		db.close();
		UpdateDb.updatePhaseStatus(db, this, editJobId, "EXTERIOR_GROUND", "tempsaved", "modified");
		Cursor status = SelectDb.getstatusNmodeByPhase(db,"EXTERIOR_GROUND", editJobId);
		
		try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "EXTERIOR_GROUND", editJobId,"create/update",ExteriorApartmentParking.class);
				s2sv.frontEndSend();
			//}else{		
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "EXTERIOR_GROUND", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				
				
//patio_or_deck_or_balcony
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("patio_or_deck_or_balcony"))
				{
					/*((EditText)findViewById(R.id.ground_checking_patioDeckBalconys)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.pre_departure_patio_deck_balcony)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_patioDeckBalconys)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_checking_patioDeckBalcony)).imgArr[i]);
						}						
					}
					
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_patioDeckBalcony)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_patioDeckBalconys)).imgArr[i]);
						}						
					}
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_checking_patioDeckBalcony_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_patioDeckBalcony_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_patioDeckBalcony_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("shade_or_sideGate_or_lock"))
				{
					/*((EditText)findViewById(R.id.ground_shadeGateLock_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_shadeGateLocks_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_shadeGateLock_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_shadeGateLock)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_shadeGateLocks)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_shadeGateLock)).imgArr[i]);
						}						
					}
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_shadeGateLock_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_shadeGateLocks_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_shadeGateLock_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("grass_or_shrubs_or_trees"))
				{
					/*((EditText)findViewById(R.id.ground_grassShrubTree_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_grussShrubTree_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_grassShrubTree_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_grassShrubTree)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_grussShrubTree)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_grassShrubTree)).imgArr[i]);
						}						
					}
					
					
					/*
					showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_grassShrubTree_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_grussShrubTree_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_grassShrubTree_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("fences_or_walls_or_driveway"))
				{
					/*((EditText)findViewById(R.id.ground_fencesWallDrive_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_fenceWallDrive_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_fenceWallDrive_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_fencesWallDrive)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_fenceWallDrive)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_fenceWallDrive)).imgArr[i]);
						}						
					}
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_fencesWallDrive_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_fenceWallDrive_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_fenceWallDrive_imageView);
					*/
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("walks_or_paintworks"))
				{
					/*((EditText)findViewById(R.id.ground_walksPaint_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_walksPaint_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_walkPaint_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_walksPaint)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_walksPaint)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_walkPaint)).imgArr[i]);
						}						
					}
					
					
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_walksPaint_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_walksPaint_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_walkPaint_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("roofs_or_slates_or_gutters"))
				{
					/*((EditText)findViewById(R.id.ground_roofSlateGutter_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_roofSlateGutter_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_roofSlateGutter_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));

					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_roofSlateGutter)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_roofSlateGutter)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_roofSlateGutter)).imgArr[i]);
						}						
					}
					
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_roofSlateGutter_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_roofSlateGutter_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_roofSlateGutter_imageView);
					*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("doorbell_or_lights_or_porch"))
				{
					/*((EditText)findViewById(R.id.ground_doorbellLightsPorch_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_doorLightPorch_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_doorLightPorch_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_doorbellLightsPorch)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_doorLightPorch)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_doorLightPorch)).imgArr[i]);
						}						
					}
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_doorbellLightsPorch_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_doorLightPorch_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_doorLightPorch_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("clothes_line"))
				{
					/*((EditText)findViewById(R.id.ground_clothesLine_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_cloth_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_clothes_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_clothesLine)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_cloth)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_clothes)).imgArr[i]);
						}						
					}
					
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_clothesLine_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_cloth_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_clothes_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("refuse_bins"))
				{
					/*((EditText)findViewById(R.id.ground_refuseBins_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_refuseBin_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_refuseBins_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
			    	((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
						
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_refuseBins)).imgArr[i]);
						}						
					}
					
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_pre_departure_refuseBin)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_ground_departure_refuseBins)).imgArr[i]);
						}						
					}
					
					/*showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.ground_refuseBins_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.ground_pre_departure_refuseBin_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.ground_departure_refuseBins_imageView);*/
					
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("action_plan_if_required"))
				{
					((EditText)findViewById(R.id.ground_check_in_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_action_plan_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("follow_up"))
				{
					((EditText)findViewById(R.id.ground_check_in_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_follow_up_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("final_comments"))
				{
					((EditText)findViewById(R.id.ground_check_in_final_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.ground_pre_departure_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.ground_departure_comments_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					cursor.moveToNext();
					continue;
				}				
			}
	}
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
}