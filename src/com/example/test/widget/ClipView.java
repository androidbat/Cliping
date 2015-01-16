package com.example.test.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Space;

/**
 * �ü��߿�
 * 
 * @author king
 * @time 2014-6-18 ����3:53:00
 */
public class ClipView extends View {

	/**
	 * �߿�����ұ߽���룬���ڵ����߿򳤶�
	 */
	public static final int BORDERDISTANCE = 200;

	private Paint mPaint;

	public ClipView(Context context) {
		this(context, null);
	}

	public ClipView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ClipView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle); 
		mPaint = new Paint(); 
	}

	private Paint paint = new Paint();

	private void initPaint() {
		if (paint == null) {
			paint = new Paint();
			paint.setColor(Color.RED);
			paint.setAntiAlias(true);
//			paint.setStrokeWidth(5);
//			paint.setStyle(Paint.Style.STROKE);
//			paint.setStrokeCap(Paint.Cap.ROUND);
		}
	}

	private Canvas mFgCanvas;
	private Paint mClearFgPaint;
	private Paint mRoundPaint;
	private Paint mFgroundPaint;
	private Bitmap mFgBitmap;
	private RectF mRect;
	private int mRadius;
	private Point mCenterPoint = new Point();
	private int mRingWidth = 2;
	private boolean isCircle  = true;
	protected void onDraw(Canvas canvas) {
		int width = this.getWidth();
		int height = this.getHeight();
		// �߿򳤶ȣ�����Ļ���ұ�Ե50px
		int borderlength = width - BORDERDISTANCE * 2;
		
		if(mFgBitmap == null) {
			mFgBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);  
			mFgCanvas = new  Canvas(mFgBitmap);
			mRect = new RectF(0, 0, getWidth(), getHeight());
			
			mCenterPoint.set(getWidth()/2, getHeight()/2);
			mClearFgPaint = new Paint();
			mClearFgPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			
			mRadius = borderlength/ 2;
			mRoundPaint = new Paint();
			mRoundPaint.setStrokeWidth(mRadius);  
	        mRoundPaint.setAntiAlias(true);
//	        mRoundPaint.setColor(0xaa000000);
			mRoundPaint.setARGB(185, 0, 0, 0);
			
			mFgroundPaint = new Paint();
			mFgroundPaint.setAntiAlias(true);
			mFgroundPaint.setARGB(180, 0 ,0, 0); 
			mFgroundPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
			//����ǰ���ɰ��Ļ���XfermodeģʽΪXOR��Ҳ�������ģʽ���Խ�����ͼ���ཻ�Ĳ����������ʣ��һ�����ſն����ɰ��
			
			mClearFgPaint = new Paint();
			mClearFgPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			
			mPaint = new Paint();
			mPaint.setColor(Color.WHITE);
			mPaint.setStrokeWidth(mRingWidth);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Style.STROKE);
		}
		
		mFgCanvas.drawPaint(mClearFgPaint);
		
		if (isCircle) {
			mFgCanvas.drawCircle(mCenterPoint.x,mCenterPoint.y, mRadius, mRoundPaint);  
		}else{
			canvas.drawRect(BORDERDISTANCE, (height-borderlength)/2, BORDERDISTANCE+borderlength, (height+borderlength)/2, mPaint);
			mFgCanvas.drawRect(BORDERDISTANCE, (height-borderlength)/2, BORDERDISTANCE+borderlength, (height+borderlength)/2, mRoundPaint);
		}
		
        //���ư�͸������
        mFgCanvas.drawRect(mRect, mFgroundPaint);
        //��ǰ���ɰ�㻭�ڱ�CutPicView��canvas��
        canvas.drawBitmap(mFgBitmap, null, mRect, mPaint);

        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mRadius, mPaint);
//		mPaint.setColor(0xaa000000);
//
//		// ���»���͸����ɫ����
//		// top
//		canvas.drawRect(0, 0, width, (height - borderlength) / 2, mPaint);
//		// bottom
//		canvas.drawRect(0, (height + borderlength) / 2, width, height, mPaint);
//		// left
//		canvas.drawRect(0, (height - borderlength) / 2, BORDERDISTANCE,(height + borderlength) / 2, mPaint);
//		// right
//		canvas.drawRect(borderlength + BORDERDISTANCE,(height - borderlength) / 2, width,(height + borderlength) / 2, mPaint);
//
//		// ���»��Ʊ߿���
//		mPaint.setColor(Color.WHITE);
//		mPaint.setStrokeWidth(10.0f);
//		mPaint.setStrokeCap(Paint.Cap.ROUND);
//		mPaint.setAntiAlias(true);
//		mPaint.setStyle(Style.STROKE);
//
////		initPaint();
////		Path path = new Path();
////		RectF rectF = new RectF(BORDERDISTANCE, (height - borderlength) / 2,
////				borderlength + BORDERDISTANCE, (height + borderlength) / 2);
////		path.addArc(rectF, -180, 180);
////		Paint citePaint = new Paint(paint);
////		citePaint.setTextSize(28);
////		citePaint.setStrokeWidth(1);
////		canvas.drawTextOnPath("http://www.android777.com", path, 0, 0,
////				citePaint);
////
////		canvas.drawArc(rectF, 0, 180, false, mPaint);
//		// top
//		 canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2, width -
//		 BORDERDISTANCE, (height - borderlength) / 2, mPaint);
//		 // bottom
//		 canvas.drawLine(BORDERDISTANCE, (height + borderlength) / 2, width -
//		 BORDERDISTANCE, (height + borderlength) / 2, mPaint);
//		 // left
//		 canvas.drawLine(BORDERDISTANCE, (height - borderlength) / 2,
//		 BORDERDISTANCE, (height + borderlength) / 2, mPaint);
//		 // right
//		 canvas.drawLine(width - BORDERDISTANCE, (height - borderlength) / 2,
//		 width - BORDERDISTANCE, (height + borderlength) / 2, mPaint);
	}

	protected void onDraw1(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.YELLOW);
		canvas.translate(canvas.getWidth() / 2, canvas.getHeight()/2);
		canvas.drawCircle(0, 0, 100, paint); // ��ԲȦ //ʹ��path����·������
		canvas.save();
//		canvas.translate(-75, -75);
		Path path = new Path();
		path.addArc(new RectF(-75, -75, 75, 75), -180, 180);
		Paint citePaint = new Paint(paint);
		citePaint.setTextSize(14);
		citePaint.setStrokeWidth(1);
		canvas.drawTextOnPath("http://www.android777.com", path, 28, 0,
				citePaint);
		canvas.restore();
		Paint tmpPaint = new Paint(paint);
		// С�̶Ȼ��ʶ���
		tmpPaint.setStrokeWidth(1);
		float y = 100;
		int count = 60;
		for (int i = 0; i < count; i++) {
			if (i % 5 == 0) {
				canvas.drawLine(0f, y, 0, y + 12f, paint);
				canvas.drawText(String.valueOf(i / 5 + 1), -4f, y + 25f,
						tmpPaint);
			} else {
				canvas.drawLine(0f, y, 0f, y + 5f, tmpPaint);
			}
			canvas.rotate(360 / count, 0f, 0f);
		}
		// ��ת��ֽ } //����ָ��
		tmpPaint.setColor(Color.GRAY);
		tmpPaint.setStrokeWidth(4);
		canvas.drawCircle(0, 0, 7, tmpPaint);
		tmpPaint.setStyle(Style.FILL);
		tmpPaint.setColor(Color.YELLOW);
		canvas.drawCircle(0, 0, 5, tmpPaint);
		canvas.drawLine(0, 10, 0, -65, paint);
		
		canvas.rotate(6,0f,0f);
	}

}
