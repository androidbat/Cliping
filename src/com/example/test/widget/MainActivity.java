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
//     * �������š�ƽ��
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
//        private int mode = 0;//���ڱ��ģʽ
//        private static final int DRAG = 1;//�϶�
//        private static final int ZOOM = 2;//�Ŵ�
//        private float startDis = 0;
//        private PointF midPoint;//���ĵ�
//
//        public boolean onTouch(View v, MotionEvent event)
//        {
//            switch (event.getAction() & MotionEvent.ACTION_MASK)
//            {
//                case MotionEvent.ACTION_DOWN:
//                    mode = DRAG;
//                    currentMaritx.set(imageView.getImageMatrix());//��¼ImageView���ڵ��ƶ�λ��
//                    startPoint.set(event.getX(), event.getY());//��ʼ��
//                    break;
//
//                case MotionEvent.ACTION_MOVE://�ƶ��¼�
//                    if (mode == DRAG)
//                    {//ͼƬ�϶��¼�
//                        float dx = event.getX() - startPoint.x;//x���ƶ�����
//                        float dy = event.getY() - startPoint.y;
//
//                        int x = (int) (dx / 50);
//                        int y = (int) (dy / 50);
//
//                        int[] start = new int[2];
//                        imageView.getLocationInWindow(start);
//                        matrix.set(currentMaritx);//�ڵ�ǰ��λ�û������ƶ�
//                        matrix.postTranslate(dx, dy);
//                        //                        }
//                    }
//                    else if (mode == ZOOM)
//                    {//ͼƬ�Ŵ��¼�
//                        float endDis = distance(event);//��������
//                        if (endDis > 10f)
//                        {
//                            float scale = endDis / startDis;//�Ŵ���
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
//                    float dx = event.getX() - startPoint.x;//x���ƶ�����
//                    float dy = event.getY() - startPoint.y;
//                    //                    startX -= dx / zoomDegree;
//                    //                    startY -= dy / zoomDegree;
//                    mode = 0;
//                    break;
//                //����ָ�뿪��Ļ������Ļ���д���(��ָ)
//                case MotionEvent.ACTION_POINTER_UP:
//                    float[] f = new float[9];
//                    matrix.getValues(f);
//                    zoomDegree = f[0];
//                    mode = 0;
//                    break;
//                //����Ļ���Ѿ��д��㣨��ָ��,����һ����ָѹ����Ļ
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    mode = ZOOM;
//                    startDis = distance(event);
//
//                    if (startDis > 10f)
//                    {//������ָ����������
//                        midPoint = mid(event);
//                        currentMaritx.set(imageView.getImageMatrix());//��¼��ǰ�����ű���
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
//     * ����֮��ľ���
//     * @param event
//     * @return
//     */
//    private static float distance(MotionEvent event)
//    {
//        //�����ߵľ���
//        float dx = event.getX(1) - event.getX(0);
//        float dy = event.getY(1) - event.getY(0);
//        return FloatMath.sqrt(dx * dx + dy * dy);
//    }
//
//    /**
//     * �����Ļ���ĵ�
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
//                    Toast.makeText(context, "����ȷѡ��ͼƬ����", Toast.LENGTH_LONG)
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
//                    Toast.makeText(context, "����ȷѡ��ͼƬ����", Toast.LENGTH_LONG)
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
