package customview;

import irishreloreportgen.activity.main.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyRelativeLayoutTxtPicUpload extends RelativeLayout implements
		View.OnClickListener {
	private ImageView iv_pic1, iv_pic2, iv_pic3, iv_pic4;
	public TextView tv_text1;

	public MyRelativeLayoutTxtPicUpload(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initDisplay(context);
	}

	public MyRelativeLayoutTxtPicUpload(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initDisplay(context);
	}

	public MyRelativeLayoutTxtPicUpload(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initDisplay(context);
	}

	private void initDisplay(Context context) {
		// TODO Auto-generated method stub
		View view = (LayoutInflater.from(context)).inflate(R.layout.text_pic_upload, this, true);
		tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
		iv_pic1 = (ImageView) view.findViewById(R.id.iv_pic1);
		iv_pic2 = (ImageView) view.findViewById(R.id.iv_pic2);
		iv_pic3 = (ImageView) view.findViewById(R.id.iv_pic3);
		iv_pic4 = (ImageView) view.findViewById(R.id.iv_pic4);
		iv_pic1.setOnClickListener(this);
		iv_pic2.setOnClickListener(this);
		iv_pic3.setOnClickListener(this);
		iv_pic4.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_pic1:

			break;
		case R.id.iv_pic2:
			break;
		case R.id.iv_pic3:
			break;
		case R.id.iv_pic4:
			break;
		}

	}

}
