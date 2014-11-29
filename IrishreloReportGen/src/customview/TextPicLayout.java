package customview;

import java.io.File;
import java.util.Date;

import irishreloreportgen.activity.main.R;
import irishreloreportgen.staticclassnconst.IrishreloAccess;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("Recycle")
public class TextPicLayout extends RelativeLayout implements OnClickListener 
{
	Context mContext;
	
	public ImageView[] imgArr;
	public static int clickedLayout=0,clickedView=0;
	public Button mButton;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_pic1:
			IrishreloAccess.camerapic = (ImageView)v;
			if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_pic1;
			}
			onPicUpload(v);
			break;
		case R.id.iv_pic2:
			IrishreloAccess.camerapic = (ImageView)v;
			if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_pic1;
			}
			onPicUpload(v);
			break;
		case R.id.iv_pic3:
			IrishreloAccess.camerapic = (ImageView)v;
			if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_pic1;
			}
			onPicUpload(v);
			break;
		case R.id.iv_pic4:
			IrishreloAccess.camerapic = (ImageView)v;
			if((v.getParent()).getParent().getParent() instanceof TextPicLayout)
			{
				TextPicLayout.clickedLayout = ((View)(v.getParent()).getParent().getParent()).getId();
				TextPicLayout.clickedView = R.id.iv_pic1;
			}
			onPicUpload(v);
			break;
		case R.id.btn_PIC:
				FragmentManager mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
				Picture_4.btn = v;
				Picture_4 mPicture_4 = Picture_4.getInstance(v.getTag());
				mPicture_4.setRetainInstance(true);
				mPicture_4.setCancelable(true);
				mPicture_4.show(mFragmentManager, "frag");
			break;
		}
	}

	public ImageView iv_pic1, iv_pic2, iv_pic3, iv_pic4;
	public TextView tv_text1;
	public EditText et_text1;
	

	public OnClickListener listener;

	public TextPicLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		initView(context);

	}

	public TextPicLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initView(context);
	}

	public TextPicLayout(Context context) {
		super(context);
		this.mContext = context;
		initView(context);
	}

	private void initView(Context context) {
		View view = (LayoutInflater.from(context)).inflate(
				R.layout.text_pic_upload, this);
		tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
		et_text1 = (EditText) view.findViewById(R.id.et_text1);
		mButton = (Button)view.findViewById(R.id.btn_PIC);
		
		mButton.setOnClickListener(this);
		
		iv_pic1 = (ImageView) view.findViewById(R.id.iv_pic1);
		iv_pic2 = (ImageView) view.findViewById(R.id.iv_pic2);
		iv_pic3 = (ImageView) view.findViewById(R.id.iv_pic3);
		iv_pic4 = (ImageView) view.findViewById(R.id.iv_pic4);
		iv_pic1.setOnClickListener(this);
		iv_pic2.setOnClickListener(this);
		iv_pic3.setOnClickListener(this);
		iv_pic4.setOnClickListener(this);
		imgArr = new ImageView[]{iv_pic1, iv_pic2, iv_pic3, iv_pic4};
		/*
		 * iv_pic1.setOnClickListener(this); iv_pic2.setOnClickListener(this);
		 * iv_pic3.setOnClickListener(this); iv_pic4.setOnClickListener(this);
		 */
	}
	
	public void onPicUpload(View v)
	{	
		IrishreloAccess.captbyCam = IrishreloAccess.imagePath = "";
		//TextPicLayout.camerapic = (ImageView)v;camerapic = (ImageView) v;
		final Dialog dia = new Dialog(mContext);
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
				((Activity)mContext).startActivityForResult(intent, 1);
				
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
				((Activity)mContext).startActivityForResult(intentImg, 2);
				
			}					
		});
		
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
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
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
	public String getRealPathFromURI(Uri contentUri) {
        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = (mContext.getContentResolver()).query(contentUri
        		, proj	// Which columns to return
        		, null	// WHERE clause; which rows to return (all rows)
        		, null	// WHERE clause selection arguments (none)
        		, null); // Order-by clause (ascending by name)
                        
                        
                        
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
	}
}



/*
 * public void onPicUpload(View v) { IrishreloAccess.captbyCam =
 * IrishreloAccess.imagePath = ""; camerapic = (ImageView) v; final Dialog dia =
 * new Dialog(this); try{ if(camerapic.getTag().toString().equals("")) {
 * dia.setContentView(R.layout.custom); dia.setTitle("Get a pic"); } else{
 * dia.setContentView(R.layout.popup); dia.setTitle("Show/Get a pic"); Uri mUrl;
 * mUrl= Uri.parse(camerapic.getTag().toString());
 * ((ImageView)dia.findViewById(R.id.popuoimg)).setImageURI(mUrl); }
 * }catch(Exception e) { dia.setContentView(R.layout.custom);
 * dia.setTitle("Get a pic"); } dia.show(); Button close =
 * ((Button)dia.findViewById(R.id.back)); close.setOnClickListener(new
 * OnClickListener(){
 * 
 * @Override public void onClick(View arg0) { // TODO Auto-generated method stub
 * dia.dismiss(); } });
 * 
 * ((Button)dia.findViewById(R.id.browse_file)) .setOnClickListener(new
 * OnClickListener(){
 * 
 * @Override public void onClick(View arg0) { // TODO Auto-generated method stub
 * dia.dismiss(); Intent intent = new Intent( Intent.ACTION_PICK,android
 * .provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
 * startActivityForResult(intent, 1);
 * 
 * } });
 * 
 * ((Button)dia.findViewById(R.id.click_pic)) .setOnClickListener(new
 * OnClickListener(){
 * 
 * @Override public void onClick(View arg0) { dia.dismiss(); // TODO
 * Auto-generated method stub String imageName = (new Date()).getTime()+".jpg";
 * String path = null; if (android.os.Environment.getExternalStorageState
 * ().equals(android.os.Environment.MEDIA_MOUNTED)) { path =
 * Environment.getExternalStorageDirectory().getPath() + "/irishrelo/" ;
 * 
 * } else { path = Environment.getRootDirectory().getPath() + "/irishrelo/"; }
 * IrishreloAccess.captbyCam = path+imageName; File wallpaperDirectory = new
 * File(path); // have the object build the directory structure, if needed.
 * wallpaperDirectory.mkdirs(); Intent intentImg = new Intent(android
 * .provider.MediaStore.ACTION_IMAGE_CAPTURE);//(Exterior.this,
 * CameraActivity.class); File file = new File(wallpaperDirectory, imageName);
 * Uri outputFileUri = Uri.fromFile(file);
 * intentImg.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
 * startActivityForResult(intentImg, 2);
 * 
 * 
 * } });
 * 
 * }
 * 
 * @Override public void onActivityResult(int requestCode, int resultCode,
 * Intent data) { super.onActivityResult(requestCode, resultCode, data); try {
 * if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
 * IrishreloAccess.imagePath = getRealPathFromURI(data.getData());
 * showImage(IrishreloAccess.imagePath); } if (requestCode == 2 && resultCode ==
 * RESULT_OK) { if((new File(IrishreloAccess.captbyCam)).exists()) {
 * showImage(IrishreloAccess.captbyCam); } } // }catch (Exception e) { // TODO
 * Auto-generated catch block //e.printStackTrace(); //Toast.makeText(this,
 * "Image saved to:\n" +e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
 * 
 * }
 * 
 * 
 * } public String getRealPathFromURI(Uri contentUri) { // can post image String
 * [] proj={MediaStore.Images.Media.DATA}; Cursor cursor = managedQuery(
 * contentUri, proj, // Which columns to return null, // WHERE clause; which
 * rows to return (all rows) null, // WHERE clause selection arguments (none)
 * null); // Order-by clause (ascending by name) int column_index =
 * cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 * cursor.moveToFirst(); return cursor.getString(column_index); }
 * 
 * 
 * public void showImageInIV(String path,int ID) { ImageView setImgpic =
 * (ImageView) findViewById(ID); Uri mUrl; Bitmap bmImg = null; try { if(path
 * !=null && path.toString().length() !=0 ) {
 * 
 * File imageFile = new File(path); if(imageFile.exists()) { mUrl=
 * Uri.parse(path); setImgpic.setImageURI(mUrl); setImgpic.setTag(path); bmImg =
 * IrishreloAccess.getResizedImage(imageFile); setImgpic.setImageBitmap(bmImg);
 * setImgpic.setTag(imageFile.getAbsolutePath()); } } } catch (Exception e) { //
 * TODO Auto-generated catch block e.printStackTrace(); } }
 */

