package com.example.test.widget;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener
{

	@Override
	public void onClick(View v) {
		
	}

//    private ImageView imageView;
//    private ImageView image2;
//    private LinearLayout cropLayout;
//    private Button btn, cancelBtn;
//    private static int height;
//    private static int width;
//    private static int line;
//    private Matrix matrix = new Matrix();
//    private static float startX, startY;
//    private float zoomDegree = 1;
//    private Context context;
//    private static Handler handler;
//    private static Bitmap img;
//    private View myView;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        this.context = this;
//
//        initView();
//
//        initHandler();
//
//    }
//
//    private void initHandler()
//    {
//        handler = new Handler()
//        {
//            @Override
//            public void handleMessage(Message msg)
//            {
//                super.handleMessage(msg);
//                switch (msg.what)
//                {
//                    case 1:
//                        findViewById(R.id.bar).setVisibility(View.GONE);
//                        image2.setVisibility(View.VISIBLE);
//                        break;
//
//                    default:
//                        break;
//                }
//            }
//        };
//    }
//
//    public static void sendHandlerMessage(int what, Object object)
//    {
//        if (handler == null)
//        {
//            return;
//        }
//
//        Message msg = handler.obtainMessage(what, object);
//        handler.sendMessage(msg);
//    }
//
//    private void initView()
//    {
//        imageView = (ImageView) findViewById(R.id.img);
//        imageView.setOnTouchListener(new TounchListener());
//        image2 = (ImageView) findViewById(R.id.img2);
//        cropLayout = (LinearLayout) findViewById(R.id.cropLayout);
//        myView = new MyView(this);
//        cropLayout.addView(myView);
//        btn = (Button) findViewById(R.id.ok);
//        btn.setOnClickListener(this);
//        cancelBtn = (Button) findViewById(R.id.cancel);
//        cancelBtn.setOnClickListener(this);
//        findViewById(R.id.animBtn).setOnClickListener(this);
//
//    }
//
//    /**
//     * 监听缩放、平移
//     * @author lcy
//     *
//     */
//    private class TounchListener implements OnTouchListener
//    {
//
//        private PointF startPoint = new PointF();
//
//        private Matrix currentMaritx = new Matrix();
//
//        private int mode = 0;//用于标记模式
//        private static final int DRAG = 1;//拖动
//        private static final int ZOOM = 2;//放大
//        private float startDis = 0;
//        private PointF midPoint;//中心点
//
//        public boolean onTouch(View v, MotionEvent event)
//        {
//            switch (event.getAction() & MotionEvent.ACTION_MASK)
//            {
//                case MotionEvent.ACTION_DOWN:
//                    mode = DRAG;
//                    currentMaritx.set(imageView.getImageMatrix());//记录ImageView当期的移动位置
//                    startPoint.set(event.getX(), event.getY());//开始点
//                    break;
//
//                case MotionEvent.ACTION_MOVE://移动事件
//                    if (mode == DRAG)
//                    {//图片拖动事件
//                        float dx = event.getX() - startPoint.x;//x轴移动距离
//                        float dy = event.getY() - startPoint.y;
//
//                        int x = (int) (dx / 50);
//                        int y = (int) (dy / 50);
//
//                        int[] start = new int[2];
//                        imageView.getLocationInWindow(start);
//                        matrix.set(currentMaritx);//在当前的位置基础上移动
//                        matrix.postTranslate(dx, dy);
//                        //                        }
//                    }
//                    else if (mode == ZOOM)
//                    {//图片放大事件
//                        float endDis = distance(event);//结束距离
//                        if (endDis > 10f)
//                        {
//                            float scale = endDis / startDis;//放大倍数
//                            matrix.set(currentMaritx);
//                            matrix.postScale(scale, scale, midPoint.x,
//                                    midPoint.y);
//                        }
//
//                    }
//
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    float dx = event.getX() - startPoint.x;//x轴移动距离
//                    float dy = event.getY() - startPoint.y;
//                    //                    startX -= dx / zoomDegree;
//                    //                    startY -= dy / zoomDegree;
//                    mode = 0;
//                    break;
//                //有手指离开屏幕，但屏幕还有触点(手指)
//                case MotionEvent.ACTION_POINTER_UP:
//                    float[] f = new float[9];
//                    matrix.getValues(f);
//                    zoomDegree = f[0];
//                    mode = 0;
//                    break;
//                //当屏幕上已经有触点（手指）,再有一个手指压下屏幕
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    mode = ZOOM;
//                    startDis = distance(event);
//
//                    if (startDis > 10f)
//                    {//避免手指上有两个茧
//                        midPoint = mid(event);
//                        currentMaritx.set(imageView.getImageMatrix());//记录当前的缩放倍数
//                    }
//
//                    break;
//
//            }
//            imageView.setImageMatrix(matrix);
//            return true;
//        }
//    }
//
//    /**
//     * 两点之间的距离
//     * @param event
//     * @return
//     */
//    private static float distance(MotionEvent event)
//    {
//        //两根线的距离
//        float dx = event.getX(1) - event.getX(0);
//        float dy = event.getY(1) - event.getY(0);
//        return FloatMath.sqrt(dx * dx + dy * dy);
//    }
//
//    /**
//     * 获得屏幕中心点
//     * @param event
//     * @return
//     */
//    private static PointF mid(MotionEvent event)
//    {
//        return new PointF(width / 2, height / 2);
//    }
//
//    private class MyView extends View
//    {
//
//        public MyView(Context context)
//        {
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas)
//        {
//            // TODO Auto-generated method stub
//            super.onDraw(canvas);
//            height = getHeight();
//            width = getWidth();
//            line = width / 2;
//            startX = width / 2 - line / 2;
//            startY = height / 2 - line / 2;
//
//            Paint p = new Paint();
//            p.setColor(Color.RED);
//            p.setStyle(Style.STROKE);
//            p.setStrokeWidth(2);
//            canvas.drawRect(width / 2 - line / 2, height / 2 - line / 2, width
//                    / 2 + line / 2, height / 2 + line / 2, p);
//            Paint p2 = new Paint();
//            p2.setColor(Color.BLACK);
//            p2.setAlpha(100);
//            canvas.drawRect(0, 0, width / 2 - line / 2, height, p2);
//            canvas.drawRect(width / 2 + line / 2, 0, width, height, p2);
//            canvas.drawRect(width / 2 - line / 2, height / 2 + line / 2, width
//                    / 2 + line / 2, height, p2);
//            canvas.drawRect(width / 2 - line / 2, 0, width / 2 + line / 2,
//                    height / 2 - line / 2, p2);
//            
//            if (img == null) {
//				return;
//			}
//
//            int h = img.getHeight();
//            int w = img.getWidth();
//            float zoom1 = (float) height / (float) h;
//            float zoom2 = (float) width / (float) w;
//            if (zoom1 < 1 || zoom2 < 1)
//            {
//                Matrix m = new Matrix();
//                float size = zoom1 > zoom2 ? zoom1 : zoom2;
//                m.setScale(size, size);
//
//                img = Bitmap.createBitmap(img, 0, 0, img.getWidth(),
//                        img.getHeight(), m, true);
//                
//                System.out.println(" b "+img.getWidth() +" "+img.getHeight());
//            }
//
//            imageView.setImageBitmap(img);
//
//        }
//
//    }
//
//    public void onClick(View v)
//    {
//        switch (v.getId())
//        {
//            case R.id.ok:
//                Bitmap bmp = img;
//                imageView.setDrawingCacheEnabled(false);
//                float[] f = new float[9];
//                matrix.getValues(f);
//                startX = (-f[2] / zoomDegree + ((float) (width - line) / 2)
//                        / zoomDegree);
//                startY = (-f[5] / zoomDegree + ((float) (height - line) / 2)
//                        / zoomDegree);
//                try
//                {
//                    Bitmap bitmap = Bitmap.createBitmap(bmp, (int) startX,
//                            (int) startY, (int) (line / zoomDegree),
//                            (int) (line / zoomDegree), matrix, true);
//                    image2.setImageBitmap(bitmap);
//                    imageView.setVisibility(View.GONE);
//                    image2.setVisibility(View.VISIBLE);
//                }
//                catch (IllegalArgumentException e)
//                {
//                    Toast.makeText(context, "请正确选择图片区域", Toast.LENGTH_LONG)
//                            .show();
//                    ;
//                }
//
//                break;
//
//            case R.id.cancel:
//                if (myView.getVisibility() == View.GONE)
//                {
//                    myView.setVisibility(View.VISIBLE);
//                }
//                image2.setVisibility(View.GONE);
//                imageView.setVisibility(View.VISIBLE);
//                break;
//
//            case R.id.animBtn:
//                bmp = img;
//
//                imageView.setDrawingCacheEnabled(false);
//                f = new float[9];
//                matrix.getValues(f);
//                startX = (-f[2] / zoomDegree + ((width - line) / 2)
//                        / zoomDegree);
//                startY = (-f[5] / zoomDegree + ((height - line) / 2)
//                        / zoomDegree);
//                Log.e("dx", -f[2] / zoomDegree + "");
//                Log.e("startX", startX + "");
//                try
//                {
//                    Bitmap bitmap = Bitmap.createBitmap(bmp, (int) startX,
//                            (int) startY, (int) (line / zoomDegree),
//                            (int) (line / zoomDegree), matrix, true);
//                    image2.setImageBitmap(bitmap);
//                    imageView.setVisibility(View.GONE);
//                    findViewById(R.id.bar).setVisibility(View.VISIBLE);
//                    myView.setVisibility(View.GONE);
//                    Timer t = new Timer();
//                    t.schedule(new TimerTask()
//                    {
//
//                        @Override
//                        public void run()
//                        {
//                            sendHandlerMessage(1, null);
//                        }
//                    }, 1000);
//                }
//                catch (IllegalArgumentException e)
//                {
//                    Toast.makeText(context, "请正确选择图片区域", Toast.LENGTH_LONG)
//                            .show();
//                }
//
//                break;
//
//            default:
//                break;
//        }
//
//    }
}
