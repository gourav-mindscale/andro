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
import android.app.Activity;
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

public class InteriorKitchen extends ActionBarActivity implements OnClickListener{
	public static final int SIGNATURE_ACTIVITY = 3;
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	SimpleDateFormat dateFormat;
	DbHelper db;
	String editJobId = "0";
	ImageView camerapic;
	TextPicLayout mTextPicLayout;
	String strtext="joy test kitchen";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kitchen);
		dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		db = new DbHelper(this);
		settings = getSharedPreferences(this.getString(R.string.app_name), 0);
		editor = settings.edit();
		editJobId = settings.getString("jobInEdit", "0");
		if(!editJobId.equals("0"))
			renderData();
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring);
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets);
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		mTextPicLayout.et_text1.setText(strtext);
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
		
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator);

		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades);
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_locks_keys_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_locks_keys_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.et_text1.setHint(R.string.handle_door_finish_title);
		mTextPicLayout.tv_text1.setText(R.string.handle_door_finish_title);
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.handle_door_finish_title);
		mTextPicLayout.tv_text1.setText(R.string.handle_door_finish_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
		
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill);
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher);
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave);
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light);
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
*/		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer);
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer);
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
*/		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals);
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		
		mTextPicLayout.et_text1.setText(strtext);

/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
*/		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture);
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		
		mTextPicLayout.et_text1.setText(strtext);

/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing);
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin);
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		
		mTextPicLayout.et_text1.setText(strtext);

/*		
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure);
			mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous);
			mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
/*		mTextPicLayout.listener = this;
			mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring);
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);

		mTextPicLayout.et_text1.setText(strtext);

		/*		
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets);
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		
		
		mTextPicLayout.et_text1.setText(strtext);

		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator);
		
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades);
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtext);

/*
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
*/		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill);
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher);
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
     	mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave);
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light);
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		/*
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer);
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer);
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals);
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_furniture);
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing);
		
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		

		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin);
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure);
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		mTextPicLayout.et_text1.setText(strtext);

		
	/*	
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		
		
		
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring);
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.flooring_title);
		mTextPicLayout.tv_text1.setText(R.string.flooring_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets);
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.countertops_or_cabinets_title);
		mTextPicLayout.tv_text1.setText(R.string.countertops_or_cabinets_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling);
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.tv_text1.setText(R.string.paint_paper_walls_celling_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator);
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.skirting_boards_radiator_title);
		mTextPicLayout.tv_text1.setText(R.string.skirting_boards_radiator_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades);
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.doors_windows_keys_locks_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles);
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);
	/*	
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.windows_keys_locks_title);
		mTextPicLayout.tv_text1.setText(R.string.windows_keys_locks_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill);
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.oven_hob_grill_title);
		mTextPicLayout.tv_text1.setText(R.string.oven_hob_grill_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds);
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.light_bulb_shades_title);
		mTextPicLayout.tv_text1.setText(R.string.light_bulb_shades_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
	*/
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher);
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
	/*	
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.dishwasher_title);
		mTextPicLayout.tv_text1.setText(R.string.dishwasher_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave);
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
	/*	
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.microwave_title);
		mTextPicLayout.tv_text1.setText(R.string.microwave_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light);
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.extractor_or_fan_light_title);
		mTextPicLayout.tv_text1.setText(R.string.extractor_or_fan_light_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer);
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.fridge_or_freezer_title);
		mTextPicLayout.tv_text1.setText(R.string.fridge_or_freezer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
*/		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer);
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.washing_machine_dryer_title);
		mTextPicLayout.tv_text1.setText(R.string.washing_machine_dryer_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals);
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		
/*		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.instruction_manual_title);
		mTextPicLayout.tv_text1.setText(R.string.instruction_manual_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		
*/
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture);
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		
	/*	mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.furniture_title);
		mTextPicLayout.tv_text1.setText(R.string.furniture_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);

	*/	
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing);
		mTextPicLayout.et_text1.setHint(R.string.soft_furnishing_title);
		mTextPicLayout.tv_text1.setText(R.string.soft_furnishing_title);
		
		
		
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin);
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		/*
		mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.sink_plug_dustbin_title);
		mTextPicLayout.tv_text1.setText(R.string.sink_plug_dustbin_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
		*/
		
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure);
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.water_pressure_title);
		mTextPicLayout.tv_text1.setText(R.string.water_pressure_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);
	*/	
		mTextPicLayout = (TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous);
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		
		/*mTextPicLayout.listener = this;
		mTextPicLayout.et_text1.setHint(R.string.miscellaneous_title);
		mTextPicLayout.tv_text1.setText(R.string.miscellaneous_title);
		mTextPicLayout.iv_pic1.setOnClickListener(this);
		mTextPicLayout.iv_pic2.setOnClickListener(this);
		mTextPicLayout.iv_pic3.setOnClickListener(this);
		mTextPicLayout.iv_pic4.setOnClickListener(this);*/
		
		
		
		
		
		
		
		
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
	void backTheprocess(View v)
	{	
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorDiningRoom.class));
		finish();
	}
	void gotoNext(View v)
	{
		saveData(((Button) findViewById(R.id.savephase)));
		startActivity(new Intent(this,InteriorUtilityRoom.class));
		finish();
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
	public void saveData(View v){
		
		long Success = -1;
		Log.v("editJobId",editJobId+"");
		ContentValues contentValues = new ContentValues();		
		db.openDataBase();	
		
			contentValues = new ContentValues();
		
			
//flooring  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).et_text1.getText().toString() );
				Log.v("for", "textPicLayout_kitchen_check_in_flooring=JOY"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).iv_pic4.getTag().toString();
				
				
				
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_pre_departure_flooring");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_flooring_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_hall_landing_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'flooring'", null);
			Log.v("updated for", "flooring"+Success);		
			
			

// countertops_or_cabinets 
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_check_in_countertops_or_cabinets");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_check_in_countertops_or_cabinets_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_pre_departure_countertops_or_cabinets_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'countertops_or_cabinets'", null);
			Log.v("updated for", "countertops_or_cabinets"+Success);		
			
			
			
			
			//paint_or_paperwall_or_ceiling  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img",imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).iv_pic1.getTag().toString()));

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'paint_or_paperwall_or_ceiling'", null);
			Log.v("updated for", "paint_or_paperwall_or_ceiling"+Success);		
			
			
			//skirting_boards_or_radiator  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'skirting_boards_or_radiator'", null);
			Log.v("updated for", "skirting_boards_or_radiator"+Success);		
			

			  
			
//doors_or_locks_or_keys_or_handles_shades  
			/*try { 
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString() );
				Log.v("for", "textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades=joy "+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shadesException");
			}
			try {
				contentValues.put("checkin_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).iv_pic1.getTag().toString()));
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString() );
				Log.v("for", "textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_JOy"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString()); 
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shadesEXception");

			} 
			try {
				contentValues.put("predepart_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).iv_pic1.getTag().toString()));

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString());
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editTextJoy "+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString());

			} catch (Exception e){                                                
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editTextEXCEPTION");

			}
			try {
				contentValues.put("depart_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).iv_pic1.getTag().toString()));


			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles_shades'", null);
			Log.v("updated for", "doors_or_locks_or_keys_or_handles_shades"+Success);
			
			
		*/
			
			
			
//doors_or_locks_or_keys_or_handles
			try {                                                                   
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_doors_or_locks_or_keys_or_handles");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "living_room_check_in_doors_or_locks_or_keys_or_handles_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_living_room_pre_departure_doors_or_locks_or_keys_or_handles_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_ettext");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_living_room_departure_doors_or_locks_or_keys_or_handles_img");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'doors_or_locks_or_keys_or_handles'", null);
			Log.v("updated for", "doors_or_locks_or_keys_or_handles"+Success);	
	
			
			
			
			
			
			
		
			
			
			
			
			
	//windows_or_locks_or_keys_or_handles  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img",imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'windows_or_locks_or_keys_or_handles'", null);
			Log.v("updated for", "windows_or_locks_or_keys_or_handles"+Success);		
			
			
			//lighting_or_bulbs_or_sheds  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'lighting_or_bulbs_or_sheds'", null);
			Log.v("updated for", "lighting_or_bulbs_or_sheds"+Success);		
			
			
			
			//departure_oven_or_hob_or_grill  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img",imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'oven_or_hob_or_grill'", null);
			Log.v("updated for", "oven_or_hob_or_grill"+Success);		
			
			
			
			//dishwasher  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'dishwasher'", null);
			Log.v("updated for", "dishwasher"+Success);		
			
			
			/*//flooring  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				contentValues.put("checkin_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_check_in_wooden_floor)).iv_pic1.getTag().toString()));
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
				contentValues.put("predepart_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_pre_departure_wooden_floor)).iv_pic1.getTag().toString()));

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landtextPicLayout_kitchen_departureloor_editText");

			}
			try {
				contentValues.put("depart_img", (((TextPicLayout)findViewById(R.id.textPicLayout_hall_landing_departure_wooden_floor)).iv_pic1.getTag().toString()));

			} catch (Exception e) {
				Log.v("for", "hall_landing_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = ''", null);
			Log.v("updated for", ""+Success);	*/	
			
			
			
			//microwave  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img",imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_check_in_wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'microwave'", null);
			Log.v("updated for", "microwave"+Success);	
			
			
			
			
			//extractor_or_fan_and_light  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'extractor_or_fan_and_light'", null);
			Log.v("updated for", "extractor_or_fan_and_light"+Success);	
			
			
			
			
//fridge_or_freezer  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'fridge_or_freezer'", null);
			Log.v("updated for", "fridge_or_freezer"+Success);	
			
			
			
			
			//washing_machine_or_dryer  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).iv_pic1.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).iv_pic2.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).iv_pic3.getTag().toString()
					+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'washing_machine_or_dryer'", null);
			Log.v("updated for", "washing_machine_or_dryer"+Success);	
			
			
			
			
//instruction_manuals  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'instruction_manuals'", null);
			Log.v("updated for", "instruction_manuals"+Success);	
			
			
			
			//furniture  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'furniture'", null);
			Log.v("updated for", "furniture"+Success);	
			
			
			
//curtains_or_blinds_or_soft_furnishing  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'curtains_or_blinds_or_soft_furnishing'", null);
			Log.v("updated for", "curtains_or_blinds_or_soft_furnishing"+Success);	
			
			
			
			//sink_or_plug_or_dustbin  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img", imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'sink_or_plug_or_dustbin'", null);
			Log.v("updated for", "sink_or_plug_or_dustbin"+Success);	
			
			
			//water_pressure  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_living_room_check_in_flooring_or_carpet_or_rugs)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).iv_pic4.getTag().toString();
				contentValues.put("depart_img", imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'water_pressure'", null);
			Log.v("updated for", "water_pressure"+Success);	
			
			
			//miscellaneous  
			try {
				
				contentValues.put("checkin_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).et_text1.getText().toString() );
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs="+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).et_text1.getText().toString());
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "Checkin_flooring_or_carpet_or_rugs");
			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).iv_pic4.getTag().toString();
				contentValues.put("checkin_img", imagePath);
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_hall_landing_check__wooden_floor_imageView");
			}
			try {
				contentValues.put("predepart_comm",((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).et_text1.getText().toString() );
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_editText");

			} 
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).iv_pic4.getTag().toString();
				contentValues.put("predepart_img",imagePath);

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "hall_landing_pre_departure_wooden_floor_imageView");
			}
			try {
				contentValues.put("depart_comm", ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).et_text1.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_editText");

			}
			try {
				String imagePath = ((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).iv_pic1.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).iv_pic2.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).iv_pic3.getTag().toString()
						+"},{"+((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).iv_pic4.getTag().toString();
				contentValues.put("depart_img",imagePath);

			} catch (Exception e) {
				Log.v("for", "textPicLayout_kitchen_departure_wooden_floor_imageView");
			}
			Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = 'miscellaneous'", null);
			Log.v("updated for", "miscellaneous"+Success);	
			
					
			
			
			
			
			
		/*	"flooring_or_tiles","walltiles_or_grouting_or_seals","paint_or_paperwall_or_ceiling",
			"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
			"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","sink_or_tap_stopper",
			"washing_machine_or_dryer","instruction_manuals","cabinet_or_shelves",
			"water_pressure","miscellaneous",
			*/
			
			
			
/*			String[] strArray = {"flooring","countertops_or_cabinets","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","oven_or_hob_or_grill",
					"dishwasher","microwave","extractor_or_fan_and_light",
					"fridge_or_freezer","washing_machine_or_dryer","instruction_manuals","furniture","curtains_or_blinds_or_soft_furnishing",
					"sink_or_plug_or_dustbin","water_pressure","miscellaneous","action_plan_if_required","follow_up","final_comments"};*/
			
			
			String[] strArray = {"action_plan_if_required","follow_up","final_comments"};
			
			int i;
			for(i=0;i< strArray.length;i++)
			{
				contentValues = new ContentValues();
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
				chkincmntId = getIdAssignedByR(this,"kitchen_check_in_"+strArray[i]+"_editText");
				Log.v("id", "kitchen_check_in_"+strArray[i]+"_editText");
				chkincmpId = getIdAssignedByR(this,"kitchen_check_in_"+strArray[i]+"_imageView");
				Log.v("id", "kitchen_check_in_"+strArray[i]+"_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"kitchen_pre_departure_"+strArray[i]+"_editText");
				Log.v("idd","kitchen_pre_departure_"+strArray[i]+"_editText" );
				predeprtcmp_Id = getIdAssignedByR(this,"kitchen_pre_departure_"+strArray[i]+"_imageView");
				Log.v("idd","kitchen_pre_departure_"+strArray[i]+"_imageView" );
				deprtcmnt_Id = getIdAssignedByR(this,"kitchen_departure_"+strArray[i]+"_editText");
				Log.v("iddd", "kitchen_departure_"+strArray[i]+"_editText");
				deprtcmp_Id = getIdAssignedByR(this,"kitchen_departure_"+strArray[i]+"_imageView");
				Log.v("iddd", "kitchen_departure_"+strArray[i]+"_imageView");
				
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
						//contentValues.put("depart_img",((ImageView)findViewById(deprtcmp_Id)).getTag().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						Log.v("for", ""+deprtcmp_Id);
	
					}
					
				
				Success = db.MyDB().update("KITCHEN", contentValues, "jobid ="+editJobId+" AND inspect_type = '"+strArray[i]+"'", null);
				Log.v("updated for", "KITCHEN"+Success);
				
			}
			
			db.close();
			UpdateDb.updatePhaseStatus(db, this, editJobId, "KITCHEN", "tempsaved", "modified");
			Cursor status = SelectDb.getstatusNmodeByPhase(db,"KITCHEN", editJobId);
			
			try{
			//if(status != null && status.getInt(2) == 1)
			{
				SendToServer s2sv = new SendToServer(db, this, true, "KITCHEN", editJobId,"create/update",InteriorCloakRoom.class);
				s2sv.frontEndSend();
			//}else{		
			}
			}catch(Exception e){
				e.getStackTrace();
			}
	}
	
	void renderData()
	{
		Cursor cursor = SelectDb.getPhaseByData(db, "KITCHEN", editJobId, false, true, false);
		if(cursor != null)
		{
			Log.v("count", ""+cursor.getCount());
			for(int j=0; j < cursor.getCount(); j++)
			{	
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("countertops_or_cabinets"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_countertops_or_cabinets)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_countertops_or_cabinets)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_countertops_or_cabinets)).imgArr[i]);
						}						
					}
					
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("flooring"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_flooring)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_flooring)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_flooring)).imgArr[i]);
						}						
					}
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("skirting_boards_or_radiator"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_skirting_boards_or_radiator)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_skirting_boards_or_radiator)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_skirting_boards_or_radiator)).imgArr[i]);
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
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kichen_check_in_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kichen_pre_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kichen_departure_paint_or_paperwall_or_ceiling)).imgArr[i]);
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
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));  
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_doors_or_locks_or_keys_or_handles_shades)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_doors_or_locks_or_keys_or_handles_shades)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_doors_or_locks_or_keys_or_handles_shades)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("windows_or_locks_or_keys_or_handles"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_windows_or_locks_or_keys_or_handles)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("lighting_or_bulbs_or_sheds"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_lighting_or_bulbs_or_sheds)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("oven_or_hob_or_grill"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_oven_or_hob_or_grill)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_oven_or_hob_or_grill)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_oven_or_hob_or_grill)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("dishwasher"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_dishwasher)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_dishwasher)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_dishwasher)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("microwave"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_microwave)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_microwave)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_microwave)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("extractor_or_fan_and_light"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_extractor_or_fan_and_light)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_extractor_or_fan_and_light)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_extractor_or_fan_and_light)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("fridge_or_freezer"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_fridge_or_freezer)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_fridge_or_freezer)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_fridge_or_freezer)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("washing_machine_or_dryer"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_washing_machine_or_dryer)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_washing_machine_or_dryer)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_washing_machine_or_dryer)).imgArr[i]);
						}						
					}
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("instruction_manuals"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_instruction_manuals)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_instruction_manuals)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_instruction_manuals)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("furniture"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_furniture)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_furniture)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_furniture)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("curtains_or_blinds_or_soft_furnishing"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_curtains_or_blinds_or_soft_furnishing)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_curtains_or_blinds_or_soft_furnishing)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_curtains_or_blinds_or_soft_furnishing)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("sink_or_plug_or_dustbin"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_sink_or_plug_or_dustbin)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_sink_or_plug_or_dustbin)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_sink_or_plug_or_dustbin)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("water_pressure"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_water_pressure)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_water_pressure)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_water_pressure)).imgArr[i]);
						}						
					}
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
				if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals("miscellaneous"))
				{
					/*((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));*/
					
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					String path = cursor.getString(cursor.getColumnIndex("checkin_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_check_in_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("predepart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_pre_departure_miscellaneous)).imgArr[i]);
						}						
					}
					
					path = cursor.getString(cursor.getColumnIndex("depart_img"));
					if(path != null)
					{
						String[] arr = path.split("\\},\\{");
						for(int i=0; i<arr.length; i++)
						{
							Log.v("ImageName", arr[i]);
							showImageInIV_NEW(arr[i],((TextPicLayout)findViewById(R.id.textPicLayout_kitchen_departure_miscellaneous)).imgArr[i]);
						}						
					}
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);*/
					cursor.moveToNext();
					continue;
				}
				
			/*	if(cursor.getString(cursor.getColumnIndex("inspect_type")).equals(""))
				{
					((EditText)findViewById(R.id.downstairs_WC_check_in_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((EditText)findViewById(R.id.downstairs_WC_pre_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((EditText)findViewById(R.id.downstairs_WC_departure_floor_covering_editText)).setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					((TextPicLayout)findViewById(R.id.)).et_text1.setText(cursor.getString(cursor.getColumnIndex("checkin_comm")));
					((TextPicLayout)findViewById(R.id.)).et_text1.setText(cursor.getString(cursor.getColumnIndex("predepart_comm")));
					((TextPicLayout)findViewById(R.id.)).et_text1.setText(cursor.getString(cursor.getColumnIndex("depart_comm")));
					
					
				/*	showImageInIV(cursor.getString(cursor.getColumnIndex("checkin_img")),R.id.downstairs_WC_check_in_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("predepart_img")),R.id.downstairs_WC_pre_departure_floor_covering_imageView);
					showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),R.id.downstairs_WC_departure_floor_covering_imageView);
					cursor.moveToNext();
					continue;
				}
				
				*/
				
				
				int chkincmntId = 0, predeprtcmnt_Id = 0, deprtcmnt_Id = 0,chkincmpId = 0, predeprtcmp_Id = 0, deprtcmp_Id = 0;
			    String conserned_type = cursor.getString(cursor.getColumnIndex("inspect_type"));
			    chkincmntId = getIdAssignedByR(this,"kitchen_check_in_"+conserned_type+"_editText");
				chkincmpId = getIdAssignedByR(this,"kitchen_check_in_"+conserned_type+"_imageView");
				predeprtcmnt_Id = getIdAssignedByR(this,"kitchen_pre_departure_"+conserned_type+"_editText");
				predeprtcmp_Id = getIdAssignedByR(this,"kitchen_pre_departure_"+conserned_type+"_imageView");
				deprtcmnt_Id = getIdAssignedByR(this,"kitchen_departure_"+conserned_type+"_editText");
				deprtcmp_Id = getIdAssignedByR(this,"kitchen_departure_"+conserned_type+"_imageView");
				
				
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
								//showImageInIV(cursor.getString(cursor.getColumnIndex("depart_img")),deprtcmp_Id);

							
							}
						}catch(Exception e){
							e.getStackTrace();
						}
						
						cursor.moveToNext();
				
			}
		}
	}
}