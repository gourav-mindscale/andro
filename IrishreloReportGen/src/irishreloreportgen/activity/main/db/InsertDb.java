package irishreloreportgen.activity.main.db;
import java.util.Date;

import irishreloreportgen.activity.main.R;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
public class InsertDb {	
	static SharedPreferences settings;
	public static long insertTableAsusual(DbHelper db, String tablename, Context context,ContentValues contentValues) {
		db.openDataBase();
		long Success = -1;
		try {
			Log.v("Insertion", tablename);
			Success = db.MyDB().insert(tablename, null, contentValues);
		} catch (Exception e) {
			Success = -1;
		}		 
		db.close();
		return Success;
	}
	public static long createAllRelateds(DbHelper db, Context context, String jobId)
	{
		long Success = -1;
		ContentValues contentValues;
		settings = context.getSharedPreferences(context.getString(R.string.app_name), 0);
		db.openDataBase();
		contentValues = new ContentValues();
		/*utilities*/
		try {
			contentValues.put("jobid", jobId);
			contentValues.put("type", "ESB");
			contentValues.put("chkin_or_depart", "1");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);
			contentValues.put("chkin_or_depart", "3");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);
			contentValues.put("type", "Gas");
			contentValues.put("chkin_or_depart", "1");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);
			contentValues.put("chkin_or_depart", "3");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);
			contentValues.put("type", "Oil");
			contentValues.put("chkin_or_depart", "1");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);						
			contentValues.put("chkin_or_depart", "3");
			Success = db.MyDB().insert("POWER_UTILITIES", null, contentValues);
			 Cursor cr = db.MyDB().rawQuery("select max(jobid) from INSPECTION_BASICS", null);
			IrishreloAccess.write("lastjob", "jobid"+cr.getString(0));
		} catch (Exception e) {			
			Success = -1;
		}
		 
		try {
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			Success = db.MyDB().insert("COMMUNICATION_N_PRECAUTIONS", null, contentValues);	
		} catch (Exception e) {			
			Success = -1;
		}
		
		//code by joy
		
		try {
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			Success = db.MyDB().insert("MPRN_GPRN", null, contentValues);	
		} catch (Exception e) {			
			Success = -1;
		}
		
		
		/*utilities*/
		/*exterior*/
		try {
			String[] strArray = {"patio_or_deck_or_balcony","shade_or_sideGate_or_lock",
					"fences_or_walls_or_driveway","walks_or_paintworks",
					"roofs_or_slates_or_gutters","doorbell_or_lights_or_porch",
					"doorbell_or_lights_or_porch","clothes_line",
					"refuse_bins","action_plan_if_required","follow_up","final_comments",
					"grass_or_shrubs_or_trees"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("EXTERIOR_GROUND", null, contentValues);	
				//Log.v("EXTERIOR_GROUND Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		try {
		   String[] strArray = {"zapper_entrance_for_gate","entrance_swipe_card",
		     "entrance_key","letter_box_key",
		     "lift_swipe_card","pedestrian_access_code"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("IF_APPLICABLE", null, contentValues); 
		    //Log.v("EXTERIOR_IF_APPLICABLE Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
		try {
			String[] strArray = {"zapper","keys",
					"parking_code","parking_space_number_if_applicable",
					"miscellaneous","action_plan_if_required",
					"follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("APARTMENT_PARKING", null, contentValues);	
				//Log.v("EXTERIOR_APARTMENT Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		try {
			String[] strArray = {"occupier","landlord","irs"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("conserned_type", strArray[i]);
				Success = db.MyDB().insert("SIGNED_BY", null, contentValues);	
				Log.v("EXTERIOR_SIGN Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		/*exterior*/
		
		/*interior*/
		try {
			String[] strArray = {"carpet_or_rugs","wooden_floors",
					"banister_or_stairway","walls_or_ceiling",
					"lighting_or_bulbs_or_sheds","handles_or_doorfinish",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","table_or_chair_coatstand_mirror",
					"phone_handset","phone_directories","curtains_or_blinds",
					"miscellaneous","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("HALL_OR_LAND", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		try {
			String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
					"paint_or_paperwall_or_ceiling","door_or_window_or_key_or_lock",
					"lightfixture_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
					"toilet_brush_or_toilet_roll_header","towel_rail",
					"cabinet_or_shelve_or_mirror","extractor_fan_or_radiator",
					"curtains_or_blinds","miscellaneous","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("WC_OR_CLOAKROOM", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		try {
			String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
					"wooden_furniture","curtains_or_blinds","mirror_or_picture",
					"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("LIVING_ROOM", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		try {
			String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
					"wooden_furnishing","curtains_or_blinds","mirror_or_picture",
					"miscellaneous","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("DINING_ROOM", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		try {
			String[] strArray = {"flooring","countertops_or_cabinets","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","oven_or_hob_or_grill",
					"dishwasher","microwave","extractor_or_fan_and_light",
					"fridge_or_freezer","washing_machine_or_dryer","instruction_manuals","furniture","curtains_or_blinds_or_soft_furnishing",
					"sink_or_plug_or_dustbin","water_pressure","miscellaneous","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("KITCHEN", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		try {
			String[] strArray = {"flooring_or_tiles","walltiles_or_grouting_or_seals","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","sink_or_tap_stopper",
					"washing_machine_or_dryer","instruction_manuals","cabinet_or_shelves",
					"water_pressure","miscellaneous","action_plan_if_required",
					"follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("UTILITY_ROOM", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		try {
			String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
					"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
					"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
					"bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
					"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
			
			contentValues = new ContentValues();
			contentValues.put("jobid", jobId);
			for(int i=0; i < strArray.length; i++)
			{
				contentValues.put("inspect_type", strArray[i]);
				Success = db.MyDB().insert("MASTER_BED_ROOM", null, contentValues);	
				//Log.v("Success "+strArray[i],Success+"");
			}
		} catch (Exception e) {			
			Success = -1;
		}
		
		
		  try {
		   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
		     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
		     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
		     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
		     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
		     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("EN_SUITE_BATHROOM", null, contentValues); 
		    //Log.v("Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
			  
		  try {
		   String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
		     "skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
		     "windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
		     "bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
		     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("BEDROOM_2", null, contentValues); 
		   // Log.v("Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
			  
		  try {
		   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
		     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
		     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
		     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
		     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray",
		     "bath_tap_stopper","miscellaneous","action_plan_if_required","follow_up","final_comments","water_pressure"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("MAIN_BATHROOM", null, contentValues); 
		    //Log.v("Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
		  try {
		   String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
		     "skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
		     "windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
		     "bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
		     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("BEDROOM_3", null, contentValues); 
		    //Log.v("Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
		  try {
		   String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
		     "skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
		     "windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
		     "bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
		     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
		   
		   contentValues = new ContentValues();
		   contentValues.put("jobid", jobId);
		   for(int i=0; i < strArray.length; i++)
		   {
		    contentValues.put("inspect_type", strArray[i]);
		    Success = db.MyDB().insert("BEDROOM_4", null, contentValues); 
		    //Log.v("Success "+strArray[i],Success+"");
		   }
		  } catch (Exception e) {   
		   Success = -1;
		  }
		  
	///   coded by Joy********************************* start *****************************************************
		  
		  
		  try {
			   String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
			     "skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
			     "windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","wardrobes_or_furniture_or_headboard",
			     "bed_or_matress_or_protector","bedlinens_or_duvets_or_pillows_or_blankets","curtains_or_blinds",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("BEDROOM_5", null, contentValues); 
			   // Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  
		  try {
			   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
			     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
			     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
			     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
			     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("EN_SUITE_ONE", null, contentValues); 
			    //Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  try {
			   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
			     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
			     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
			     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
			     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("EN_SUITE_TWO", null, contentValues); 
			    //Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  try {
			   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
			     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
			     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
			     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
			     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("EN_SUITE_THREE", null, contentValues); 
			    //Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  try {
			   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
			     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
			     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
			     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
			     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("EN_SUITE_FOUR", null, contentValues); 
			    //Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  try {
			   String[] strArray = {"floor_covering","tiles_or_grouting_or_seals",
			     "paint_or_paperwall_or_ceiling","doors_or_windows_or_keys_or_locks",
			     "lighting_or_bulb_or_shed","toilet_or_flush_or_sink_or_tap_stopper",
			     "toilet_brush_or_toilet_roll_header","towel_rail_or_radiator",
			     "cabinet_or_shelve_or_mirror","extractor_fan","shower_curtain_or_shower_door_or_tray","water_pressure",
			     "miscellaneous","action_plan_if_required","follow_up","final_comments"};
			   
			   contentValues = new ContentValues();
			   contentValues.put("jobid", jobId);
			   for(int i=0; i < strArray.length; i++)
			   {
			    contentValues.put("inspect_type", strArray[i]);
			    Success = db.MyDB().insert("EN_SUITE_FIVE", null, contentValues); 
			    //Log.v("Success "+strArray[i],Success+"");
			   }
			  } catch (Exception e) {   
			   Success = -1;
			  }
		  
		  
		  
		  
			try {
				String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
						"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
						"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
						"wooden_furniture","curtains_or_blinds","mirror_or_picture",
						"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
				
				contentValues = new ContentValues();
				contentValues.put("jobid", jobId);
				for(int i=0; i < strArray.length; i++)
				{
					contentValues.put("inspect_type", strArray[i]);
					Success = db.MyDB().insert("ROOM_ONE", null, contentValues);	
					//Log.v("Success "+strArray[i],Success+"");
				}
			} catch (Exception e) {			
				Success = -1;
			}
			
			
			
			try {
				String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
						"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
						"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
						"wooden_furniture","curtains_or_blinds","mirror_or_picture",
						"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
				
				contentValues = new ContentValues();
				contentValues.put("jobid", jobId);
				for(int i=0; i < strArray.length; i++)
				{
					contentValues.put("inspect_type", strArray[i]);
					Success = db.MyDB().insert("ROOM_TWO", null, contentValues);	
					//Log.v("Success "+strArray[i],Success+"");
				}
			} catch (Exception e) {			
				Success = -1;
			}
			
			
			
			try {
				String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
						"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
						"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
						"wooden_furniture","curtains_or_blinds","mirror_or_picture",
						"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
				
				contentValues = new ContentValues();
				contentValues.put("jobid", jobId);
				for(int i=0; i < strArray.length; i++)
				{
					contentValues.put("inspect_type", strArray[i]);
					Success = db.MyDB().insert("ROOM_THREE", null, contentValues);	
					//Log.v("Success "+strArray[i],Success+"");
				}
			} catch (Exception e) {			
				Success = -1;
			}
			
			
			
			try {
				String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
						"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
						"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
						"wooden_furniture","curtains_or_blinds","mirror_or_picture",
						"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
				
				contentValues = new ContentValues();
				contentValues.put("jobid", jobId);
				for(int i=0; i < strArray.length; i++)
				{
					contentValues.put("inspect_type", strArray[i]);
					Success = db.MyDB().insert("ROOM_FOUR", null, contentValues);	
					//Log.v("Success "+strArray[i],Success+"");
				}
			} catch (Exception e) {			
				Success = -1;
			}
			
			
			
			try {
				String[] strArray = {"flooring_or_carpet_or_rugs","paint_or_paperwall_or_ceiling",
						"skirting_boards_or_radiator","doors_or_locks_or_keys_or_handles",
						"windows_or_locks_or_keys_or_handles","lighting_or_bulbs_or_sheds","soft_furnishing",
						"wooden_furniture","curtains_or_blinds","mirror_or_picture",
						"miscellaneous","tv_socket","phone_socket","action_plan_if_required","follow_up","final_comments"};
				
				contentValues = new ContentValues();
				contentValues.put("jobid", jobId);
				for(int i=0; i < strArray.length; i++)
				{
					contentValues.put("inspect_type", strArray[i]);
					Success = db.MyDB().insert("ROOM_FIVE", null, contentValues);	
					//Log.v("Success "+strArray[i],Success+"");
				}
			} catch (Exception e) {			
				Success = -1;
			}
			
			
			
		  
		  
		///   coded by Joy********************************* END *****************************************************
		  	  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		/*interior*/
		db.close();
		db.openDataBase();
		contentValues = new ContentValues();
		contentValues.put("jobid",jobId);
		contentValues.put("isold",0);
		contentValues.put("mode","modified");
		contentValues.put("save_status","tempsaved");
		contentValues.put("last_updated_by",settings.getString("operator_email", ""));
		contentValues.put("update_time", new Date().getTime());
		contentValues.put("phase","POWER_UTILITIES");
		long result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","COMMUNICATION_N_PRECAUTIONS");
		result =  db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EXTERIOR_GROUND");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","IF_APPLICABLE");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","APARTMENT_PARKING");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","EXTERIOR_COMMENTS");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","SIGNED_BY");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","HALL_OR_LAND");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","WC_OR_CLOAKROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","LIVING_ROOM");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","DINING_ROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","KITCHEN");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","UTILITY_ROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","MASTER_BED_ROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","BEDROOM_2");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","BEDROOM_3");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","BEDROOM_4");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","EN_SUITE_BATHROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		contentValues.put("phase","MAIN_BATHROOM");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
	// joys code FLOWING	
		contentValues.put("phase","BEDROOM_5");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EN_SUITE_ONE");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EN_SUITE_TWO");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EN_SUITE_THREE");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EN_SUITE_FOUR");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","EN_SUITE_FIVE");
		result = db.MyDB().insert("JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","ROOM_ONE");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		 
		contentValues.put("phase","ROOM_TWO");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","ROOM_THREE");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","ROOM_FOUR");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		contentValues.put("phase","ROOM_FIVE");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
	
		contentValues.put("phase","MPRN_GPRN");
		result = db.MyDB().insert( "JOB_LOCAL_OPERATION_PHASE_TRACK", null, contentValues);
		
		
		db.close();
		//need to check whether there is any row exist or not
		return Success|result;
	}
}