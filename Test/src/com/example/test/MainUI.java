package com.example.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * ������Ū��������ˣ����ж��ǵ�����ǻ������жϻ��������ж������һ����������»�������ִ�ж�Ӧ���߼�
 * 
 * @author dell
 * 
 */
public class MainUI extends RelativeLayout { //�Զ���view������ôд
    private Context context;
    private FrameLayout LeftMenu, MiddleMenu, RightMenu;
    private Scroller scroller;// ���û���

    private FrameLayout MiddleMask;// ʵ���ɰ��˼·�ǣ��������м��һ�㣬Ȼ��һ��������õ�͸����͸���ģ�Ȼ��͸���������������ǻ����ľ���ı���ı�

    public static final int Left_ID = 0xaabbcc;//ʮ������
    public static final int Right_ID = 0xaaccbb;
    public static final int Middle_ID = 0xccaabb;

    // �Զ���ؼ��Ĺ��췽����׼��ʽ
    public MainUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * ΪʲôҪ��������췽���� ͨ��ʵ�����ֺ���
     * 
     * @param context
     */
    public MainUI(Context context) {
        // TODO Auto-generated constructor stub
        super(context);
        initview(context);
    }

    /**
     * ����������Ҫ�����Ӳ˵���������Ҫcontext���н������ģ�׼ȷ��˵�ƾ�����Ҫcontext��Ϊ����
     * 
     * @param context
     */
    private void initview(Context context) {
        this.context = context;
        scroller = new Scroller(context, new DecelerateInterpolator());// ����Ҳ����Ҫʵ�����ģ��ڶ������������ڸ������ģ���Ϊ��Ⱦ
        // ֱ��ʵ����һ��
        LeftMenu = new FrameLayout(context);
        RightMenu = new FrameLayout(context);
        MiddleMenu = new FrameLayout(context);
        MiddleMask = new FrameLayout(context);
        // Ϊ���Ӿ�Ч����������һЩ����
        LeftMenu.setBackgroundColor(Color.RED);
        MiddleMenu.setBackgroundColor(Color.GREEN);
        RightMenu.setBackgroundColor(Color.RED);

        MiddleMask.setBackgroundColor(0x88000000);// ԭ��������ɫ�������������ã�66666���������õ��������ɫ�������ǻ�ɫ�����ǲ��Ǻ���

        // ���ö�Ӧ��ID
        LeftMenu.setId(Left_ID);
        MiddleMenu.setId(Middle_ID);
        RightMenu.setId(Right_ID);
        // ��������Ҫ������������ȫ����䵽һ��view���У���ʵ�����view���ǳ��������������������Relativelayout
        addView(LeftMenu);
        addView(RightMenu);
        addView(MiddleMenu);
        addView(MiddleMask);// �����������˵�һ��ӽ�ȥ�ģ����Ҳ��Ҫ�ڿ��Ⱥ͸߶��Ͻ�������
        MiddleMask.setAlpha(0);// �ʼ�϶���͸����
    }

    /**
     * ��������������м����¼��ı仯�����趨͸����
     */

    @Override
    public void scrollTo(int x, int y) {//���ݻ����ľ���仯���仯�����Ҫ���ڻ�����ʱ����ʲô�������Ϳ��Է�����������У�����ִ����������Ϳ�����
        // TODO Auto-generated method stub
        super.scrollTo(x, y);
        int curX=Math.abs(getScrollX());//��������仯���仯
        float scale=curX/(float)LeftMenu.getMeasuredWidth();//���ñ���
        MiddleMask.setAlpha(scale);
    }

    /**
     * ֱ�����ӽ�ȥ��û�а취��ʾ�ģ�������Ҫ����һ�¿��Ⱥ͸߶ȣ�������Ⱥ͸߶Ⱦ������ǵ�ǰ��Ļ׼ȷ�Ŀ��Ⱥ͸߶�
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MiddleMenu.measure(widthMeasureSpec, heightMeasureSpec);// �м䲿��������ã���Ϊ������Ҫ�м䲿�ֳ�����Ļ�����Ҳ˵��߶���ͬ�����Ȳ�ͬ
        MiddleMask.measure(widthMeasureSpec, heightMeasureSpec);// �ɰ�Ŀ��ߺ��м�Ĳ˵�һ��
        int realWidth = MeasureSpec.getSize(widthMeasureSpec);// ��ȡ��������Ļ�Ŀ���
        int tempWidthMeasure = MeasureSpec.makeMeasureSpec(
                (int) (realWidth * 0.8), MeasureSpec.EXACTLY);// ��ȡ���Ҳ˵��Ŀ���ע��������ͣ��������Ĳ���ȫ��int����
        // �����ǵ�0.8�Ǹ�����������ʹ��EACTLYģʽ��
        RightMenu.measure(tempWidthMeasure, heightMeasureSpec);// �������Ҳ˵��Ŀ��Ⱥ͸߶ȣ����Ը�����Ŀ���������ò�ͬ�����Ҳ˵�����
        LeftMenu.measure(tempWidthMeasure, heightMeasureSpec);
    }

    /**
     * ͨ��onLayout�������
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
        // �����м䲿��
        // �����������ʱ����һ������������ڼ�����˵������Ͻǵ�����λ�øı�����½ǵ�����λ�ò��䣬�������Ҳ˵�ʱ���Ͻǲ�������½Ǹı�
        MiddleMenu.layout(l, t, r, b);// ����������Ļ,����

        MiddleMask.layout(l, t, r, b);// �ɰ��λ��Ҳ���м�˵����

        LeftMenu.layout(l - LeftMenu.getMeasuredWidth(), t, r, b);// ֻ��˵��ʦҲ��û��Ū��������ﻹ��Ҫʹ��view���������ش����⣬�����ĸ������Ƿ�ͼ
        // Ҳ����ʹ�ã�l-tempWidthMeasure,
        RightMenu.layout(
                l + MiddleMenu.getMeasuredWidth(),
                t,
                l + MiddleMenu.getMeasuredWidth()
                        + RightMenu.getMeasuredWidth(), b);
    }

    private boolean isWhat;// ���ڱ����ʲô�¼�������߀���c��
    private boolean isLeftOrRight;// ��춘�ӛ�Ƿ����һ���

    /**
     * �����дһ�������¼����ж������������һ������Լ�д�¼��ַ�������������¼��ַ�����˼Ӧ��Ҳ���ǲ�ͬ���¼�ִ�в�ͬ���߼�
     * ������ʵս��ͨ������if��䣬�ȶ��¼��������жϣ�Ȼ������¼�������Ӧ�Ķ��� 
     * 
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * ����Ҫ�ж��������¼����������¼��������¼���������»����ģ������¼���������ǵ����Ҳ˵������ģ�����һ���ǵ���¼��Ĵ���
         */
        // TODO Auto-generated method stub
        if (!isWhat) {
            getEventType(ev);// ����һ���������¼������ж�
            return true;
        }
        // �ж����һ���
        if (isLeftOrRight) {// �������һ���
            switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:// ���case��һ�㶼û�з�������һ�㶼��֪��ԭ��Ҳ���Ͳ�����
                int curScrollX = getScrollX();// ����һ�������ľ���
                int dis_x = (int) (ev.getX() - point.x);// ��ȡ��ָ�����Լ������ľ��룬���ǻ����ľ���
                int expectx = -dis_x + curScrollX;
                int finalX = 0;
                if (expectx < 0) {// ���󻬶�
                    finalX = Math.max(expectx, -LeftMenu.getMeasuredWidth());// ����ֵ���Ǹ��ģ�ȡ����ֵ��С�ġ�
                } else {// ���һ���
                    finalX = Math.min(expectx, RightMenu.getMeasuredWidth());// ����ֵ�������ģ�ȡ����ֵ�ϴ�ġ�
                }
                scrollTo(finalX, 0);
                point.x = (int) ev.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                curScrollX = getScrollX();// ���ֵҪ�����һ����Ǹ�ֵ�����󻬾�����ֵ,getScrollx()�������صľ���ϲ��������
                if (Math.abs(curScrollX) > LeftMenu.getMeasuredWidth() >> 1) {// ����һλ���ǳ��Զ��������趨�������ǻ������볬����˵�һ���ʱ��,��������
                    if (curScrollX < 0) {// ��ָ���Ҷ���������˵���
                        scroller.startScroll(curScrollX, 0,
                                -LeftMenu.getMeasuredWidth() - curScrollX, 0,
                                200);// ��ʼ��������ʼ����Ϊ��ָ�Ӵ���Ļ����������λ�ã�����λ������Ϊȫ����ʾ��y���ùܣ���Ϊ�����Ͳ��ñ�
                    } else {
                        scroller.startScroll(curScrollX, 0,
                                RightMenu.getMeasuredWidth() - curScrollX, 0,
                                200);// ���������Ǹ����Ķ���ִ��ʱ�䣬200ms
                    }
                } else {
                    scroller.startScroll(curScrollX, 0, -curScrollX, 0);// ��������û�г�����׼�ͷ��ص�ԭλ��
                }
                invalidate();//�ػ�
                isLeftOrRight = false;//�ٴγ�ʼ��
                isWhat = false;
                break;

            default:
                break;
            }
        } else {
            // ���������һ���������������
            switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                isLeftOrRight = false;
                isWhat = false;
                break;

            default:
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        super.computeScroll();// ����ص�����һ��Ҫ��д��Ȼ���Ử��
        if (!scroller.computeScrollOffset()) {
            return;
        }
        int tempX = scroller.getCurrX();// ����һ�����ǻ�����ֵ�����������ֵ�������ǻ����ľ���
        scrollTo(tempX, 0);
    }

    private Point point = new Point();// ����һ���㣬���ݵ��ȡ����ǰ�����ľ��룬���ݻ����ľ����ж��ǻ������ǵ��
    private static final int TEST_DIS = 20;// ���xһ�������^��ֵ��������20���ǻ�����

    private void getEventType(MotionEvent ev) {
        // TODO Auto-generated method stub
        // �������¼�Ҳ����ô�ࣨ������
        switch (ev.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:// ��� ���µ�ʱ��������
            point.x = (int) ev.getX();// ÿ�ζ�Ҫ�@ȡ�c�����ˣ�getX()�����Ƿ���float��ͣ�����҂�Ҫ���Ơ�int���
            point.y = (int) ev.getY();
            super.dispatchTouchEvent(ev);// ����Ĵ�������¼����߼�
            break;

        case MotionEvent.ACTION_MOVE:// ����ƶ���ʱ��������
            int dX = Math.abs((int) ev.getX() - point.x);// ֻ���^��ֵ�����һ��Ӳ�����
            int dY = (int) Math.abs(ev.getY() - point.y);
            if (dX >= TEST_DIS && dX > dY) {// ǰ�������ǻ�����������������ң����������һ���
                isLeftOrRight = true;// ���һ���
                isWhat = true;// �ǻ���
                point.x = (int) ev.getX();// Ϊ��ÿ�λ������󣬽������ܹ�����������ÿ�ζ�Ҫ�@ȡ�c�����ˣ�getX()�����Ƿ���float��ͣ�����҂�Ҫ���Ơ�int���
                point.y = (int) ev.getY();
            } else if (dY >= TEST_DIS && dY > dX) {// �������»���
                isLeftOrRight = false;// �������һ���
                isWhat = true;// �ǻ���
                point.x = (int) ev.getX();// ÿ�ζ�Ҫ�@ȡ�c�����ˣ�getX()�����Ƿ���float��ͣ�����҂�Ҫ���Ơ�int���
                point.y = (int) ev.getY();
            }
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:// ������ָ������Ե�����
            super.dispatchTouchEvent(ev);// ����¼�����Ҫ�����Լ�����������ֱ���׸�ϵͳ������ok��
            isLeftOrRight = false;
            isWhat = false;
            break;
        default:
            break;
        }

    }
}