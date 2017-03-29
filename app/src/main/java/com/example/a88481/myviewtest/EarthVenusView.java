package com.example.a88481.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a88481.myviewtest.Models.TraceLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 88481 on 2017/3/29 0029.
 */

public class EarthVenusView extends View {

    private PointF currentEarthPosition;
    private PointF currentVenusPosition;

    private float venusAngleSpeed = 0.08f;
    private float earthAngleSpeed = 0.06f;

    private float earthTraceRadius = 0.9f;
    private float venusTraceRadius = 0.4f;

    private int traceListLength = 500;

    public EarthVenusView(Context context){
        super(context);
        this.thread.start();
    }

    public EarthVenusView(Context context,@Nullable AttributeSet attrs) {
        this(context);
    }

    public EarthVenusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public void setVenusAngleSpeed(float venusAngleSpeed) {
        this.venusAngleSpeed = venusAngleSpeed;
        this.traceLineList.clear();
    }
    public float getVenusAngleSpeed() {
        return venusAngleSpeed;
    }

    public void setEarthAngleSpeed(float earthAngleSpeed) {
        this.earthAngleSpeed = earthAngleSpeed;
        this.traceLineList.clear();
    }
    public float getEarthAngleSpeed() {
        return earthAngleSpeed;
    }

    public void setTraceListLength(int traceListLength) {
        this.traceListLength = traceListLength;
    }
    public int getTraceListLength() {
        return traceListLength;
    }

    private List<TraceLine> traceLineList = new ArrayList<TraceLine>();

    private int timeFlag = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    timeFlag++;

                    invalidate();
                    break;
            }
        }
    };

    private Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();

            while (true){
                try{
                    sleep(50);
                    Message msg = new Message();
                    msg.what = 1;

                    handler.sendMessage(msg);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        PointF centerPoint = new PointF(width/2f,height/2f);
        float radius = (width < height)?width/2f:height/2f;

        float sunRadius = (float)(radius*0.05);
        float venusRadius = (float)(sunRadius*0.3);
        float earthRadius = (float)(sunRadius*0.5);

        this.currentEarthPosition = new PointF(
                (float)(centerPoint.x + radius*this.earthTraceRadius*Math.cos(this.earthAngleSpeed*this.timeFlag)),
                (float)(centerPoint.y + radius*this.earthTraceRadius*Math.sin(this.earthAngleSpeed*this.timeFlag))
        );

        this.currentVenusPosition = new PointF(
                (float)(centerPoint.x + radius*this.venusTraceRadius*Math.cos(this.venusAngleSpeed*this.timeFlag)),
                (float)(centerPoint.y + radius*this.venusTraceRadius*Math.sin(this.venusAngleSpeed*this.timeFlag))
        );

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(centerPoint.x,centerPoint.y,sunRadius,paint);

        paint.setColor(Color.YELLOW);
        canvas.drawCircle(this.currentVenusPosition.x,
                            this.currentVenusPosition.y,venusRadius,paint);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(this.currentEarthPosition.x,
                this.currentEarthPosition.y,earthRadius,paint);

        paint.setPathEffect(new DashPathEffect(new float[]{3f,0f},0));
        paint.setColor(Color.RED);

        if(this.traceLineList.size()>this.traceListLength){
            this.traceLineList.clear();
        }

        this.traceLineList.add(new TraceLine(currentEarthPosition,currentVenusPosition));

        for (int index = 0; index < this.traceLineList.size();index++){
            float[] xyData = this.traceLineList.get(index).getXYData();
            canvas.drawLine(xyData[0],xyData[1],xyData[2],xyData[3],paint);
        }

    }
}

