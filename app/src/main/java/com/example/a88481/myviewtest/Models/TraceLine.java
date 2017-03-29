package com.example.a88481.myviewtest.Models;

import android.graphics.PointF;

/**
 * Created by 88481 on 2017/3/29 0029.
 */

public class TraceLine {
    private PointF earthPosition;
    private PointF venusPosition;

    public TraceLine(PointF earthPosition,PointF venusPosition){
        this.earthPosition = earthPosition;
        this.venusPosition = venusPosition;
    }

    public float[] getXYData(){
        return new float[]{
                this.earthPosition.x,
                this.earthPosition.y,
                this.venusPosition.x,
                this.venusPosition.y
        };
    }
}
