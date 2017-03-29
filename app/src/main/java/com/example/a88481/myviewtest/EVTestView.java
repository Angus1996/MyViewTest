package com.example.a88481.myviewtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by 88481 on 2017/3/29 0029.
 */

public abstract class EVTestView extends LinearLayout
        implements SeekBar.OnSeekBarChangeListener {
    private TextView txtEarth;
    private TextView txtVenus;
    private TextView txtLength;

    private SeekBar skVenus;
    private SeekBar skEarth;
    private SeekBar skLength;

    private EarthVenusView veView;

    private Context context;

    public EVTestView(Context context) {
        super(context);
        this.context = context;
   }

    public EVTestView(Context context, @Nullable AttributeSet attrs){
        this(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init(){
        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout ly01 = new LinearLayout(this.context);
        ly01.setOrientation(LinearLayout.HORIZONTAL);

        TextView txtEarthLabel = new TextView(this.context);
        txtEarthLabel.setText("Earth:");
        ly01.addView(txtEarthLabel);

        this.skEarth = new SeekBar(this.context);
        ly01.addView(this.skEarth);

        this.txtEarth = new TextView(this.context);
        ly01.addView(this.txtEarth);


        LinearLayout ly02 = new LinearLayout(this.context);
        ly02.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout ly03 = new LinearLayout(this.context);
        ly03.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout ly04 = new LinearLayout(this.context);
        ly04.setOrientation(LinearLayout.HORIZONTAL);

        this.addView(ly01);
        this.addView(ly02);
        this.addView(ly03);
        this.addView(ly04);


        this.txtVenus = new TextView(this.context);
        this.txtLength = new TextView(this.context);


        this.skEarth.setOnSeekBarChangeListener(this);

        this.skVenus = new SeekBar(this.context);
        this.skVenus.setOnSeekBarChangeListener(this);

        this.skLength = new SeekBar(this.context);
        this.skLength.setOnSeekBarChangeListener(this);

        this.veView = new EarthVenusView(this.context);

        this.txtEarth.setText(String.valueOf(this.veView.getEarthAngleSpeed()));
        this.txtVenus.setText(String.valueOf(this.veView.getVenusAngleSpeed()));
        this.txtLength.setText(String.valueOf(this.veView.getTraceListLength()));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
