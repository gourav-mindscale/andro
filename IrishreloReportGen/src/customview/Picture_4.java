package customview;

import java.io.File;
import java.util.Date;

import irishreloreportgen.activity.main.R;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class Picture_4 extends android.support.v4.app.DialogFragment implements	OnClickListener {

	Context mContext;
	
	
	ImageView iv_image1,iv_image2,iv_image3,iv_image4; 
	Button btn_save,btn_cancel;
	
	private View view ;
	public static View btn;
	
	public static Picture_4 getInstance(Object mObject) {
		 
		Picture_4 mPicture_4 = new Picture_4();
		if(mObject instanceof String){
			Bundle mBundle = new Bundle();
			mBundle.putString("date", (String)mObject);
			mPicture_4.setArguments(mBundle);
		}
		return mPicture_4;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanveState) {
		view = inflater.inflate(R.layout.image_ayout, parent, false);
		if(Picture_4.btn != null && Picture_4.btn.getTag() != null && Picture_4.btn.getTag() instanceof String){
			Toast.makeText(getActivity(), (String)Picture_4.btn.getTag(), Toast.LENGTH_LONG).show();
		}
		
		iv_image1=(ImageView)view.findViewById(R.id.iv_image1);
		iv_image2=(ImageView)view.findViewById(R.id.iv_image2);
		iv_image3=(ImageView)view.findViewById(R.id.iv_image3);
		iv_image4=(ImageView)view.findViewById(R.id.iv_image4);
		btn_save=(Button)view.findViewById(R.id.btn_save_imageLaout);
		btn_cancel=(Button)view.findViewById(R.id.btn_cancel_imageLaout);
		
		iv_image1.setOnClickListener(this);
		
		iv_image2.setOnClickListener(this);

		iv_image3.setOnClickListener(this);

		iv_image4.setOnClickListener(this);

		btn_save.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		 

		return view;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.iv_image1:
			IrishreloAccess.camerapic = (ImageView)v;
			onPicUpload(v);

			break;
		case R.id.iv_image2:
			IrishreloAccess.camerapic = (ImageView)v;
			/*if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_image2;
			}*/
			onPicUpload(v);

			break;
		case R.id.iv_image3:
			
			IrishreloAccess.camerapic = (ImageView)v;
			/*if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_image3;
			}*/
			onPicUpload(v);

			

			break;
		case R.id.iv_image4:

			IrishreloAccess.camerapic = (ImageView)v;
			/*if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_image4;
			}*/
			onPicUpload(v);

			break;
		case R.id.btn_save_imageLaout:
			String imagePath = iv_image1.getTag().toString()
			+ "},{"
			+ iv_image2.getTag().toString()
			+ "},{"
			+ iv_image3.getTag().toString()
			+ "},{"
			+ iv_image4.getTag().toString();

			Picture_4.btn.setTag(imagePath);
			Toast.makeText(getActivity(), Picture_4.btn.getTag().toString(), Toast.LENGTH_LONG).show();
	
			break;

		case R.id.btn_cancel_imageLaout:
			dismiss();

			break;

		default:
			break;
		}

	}
	
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("On Activbity result", "Picture_4");
		try {
			if (requestCode == 1 && resultCode == Activity.RESULT_OK && null != data) {
				IrishreloAccess.imagePath = getRealPathFromURI(data.getData());
				showImage(IrishreloAccess.imagePath);
			}
			if (requestCode == 2 && resultCode == Activity.RESULT_OK) 
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
	}
	
	public void showImage(String path)
	{
		try {
			Bitmap bmImg = null;
			File imageFile = new File(path);
			if(imageFile.exists())
			{
				bmImg = IrishreloAccess.getResizedImage(imageFile);
				IrishreloAccess.camerapic.setImageBitmap(bmImg);
				IrishreloAccess.camerapic.setTag(imageFile.getAbsolutePath());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRealPathFromURI(Uri contentUri) {
        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = (getActivity().getContentResolver()).query(contentUri
        		, proj	// Which columns to return
        		, null	// WHERE clause; which rows to return (all rows)
        		, null	// WHERE clause selection arguments (none)
        		, null); // Order-by clause (ascending by name)
                        
                        
                        
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
	}

	public void onPicUpload(View v)
	{	
		IrishreloAccess.captbyCam = IrishreloAccess.imagePath = "";
		//TextPicLayout.camerapic = (ImageView)v;camerapic = (ImageView) v;
		final Dialog dia = new Dialog(getActivity());
		try{
			if(IrishreloAccess.camerapic.getTag().toString().equals(""))
			{
				dia.setContentView(R.layout.custom);
				dia.setTitle("Get a");
			}
			else{
				dia.setContentView(R.layout.popup);
				dia.setTitle("Show/Get a pic");
				Uri mUrl;
				mUrl= Uri.parse(IrishreloAccess.camerapic.getTag().toString());
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
				((Activity)getActivity()).startActivityForResult(intent, 1);
				
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
				((Activity)getActivity()).startActivityForResult(intentImg, 2);
				
			}					
		});
		
	}

}
