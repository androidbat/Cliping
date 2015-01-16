package com.example.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.test.widget.ClipImageView;
import com.example.test.widget.util.BitmapCacle;
import com.example.test.widget.util.CropHandler;
import com.example.test.widget.util.CropHelper;
import com.example.test.widget.util.CropParams;

public class MainActivity extends Activity implements CropHandler{

	private ClipImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ClipImageView) findViewById(R.id.src_pic);
		imageView.setImageResource(R.drawable.test_pic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_clip){
			// �˴���ȡ���ú��bitmap
			Bitmap bitmap = imageView.clip();
			// ����Intent����bitmap���ܳ���40k,�˴�ʹ�ö��������鴫��
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//			byte[] bitmapByte = baos.toByteArray();
			
			BitmapCacle.bitmap = bitmap;
			Intent intent = new Intent(this, PreviewActivity.class);
//			intent.putExtra("bitmap", bitmapByte);
			
			saveBitmapToFile(bitmap, "/mnt/sdcard/1.png");
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		CropHelper.handleResult(this, requestCode, resultCode, data);
	};
	
   public static void saveBitmapToFile(Bitmap photo, String fileName) {
		File file = new File(fileName);
		FileOutputStream out = null;
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();  
			out = new FileOutputStream(file);
			photo.compress(CompressFormat.PNG, 70, out);
			out.flush();
		} catch (Exception e) {
		} finally {
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
				}
			}
		}
	}
   
	CropParams mCropParams = new CropParams();

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bt_capture:
			Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
			startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
			break;
		case R.id.bt_gallery:
			startActivityForResult(CropHelper.buildPickIntent(),
					CropHelper.REQUEST_GALLERY);
			break;
		}
	}
	
	/**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// ���òü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 200);
		intent.putExtra("circleCrop", true);
		intent.putExtra("scaleUpIfNeeded", true);// ���ֻ��ͻ�ڱ�
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 11);
	}
	
	@Override
    public void onPhotoCropped(Uri uri) {
        Log.d("aa", "Crop Uri in path: " + uri.getPath());
        try{
        	Bitmap bm = CropHelper.decodeUriAsBitmap(this, uri);
        	if (imageView.getWidth() < bm.getWidth() || imageView.getHeight() < bm.getHeight()) {
        		bm = Bitmap.createScaledBitmap(bm, imageView.getWidth(), imageView.getHeight(), true);
        	}
        	imageView.setImageBitmap(bm);
        }catch(Exception e){
        	Toast.makeText(this, "Photo cropped error !", Toast.LENGTH_LONG).show();
        }
    }
	
	@Override
	public void onCropCancel() {
		
	}

	@Override
	public void onCropFailed(String message) {
		
	}

	@Override
	public CropParams getCropParams() {
		return mCropParams;
	}

	@Override
	public Activity getContext() {
		return this;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		CropHelper.clearCachedCropFile(getCropParams().uri);
	}
}
