package com.example.a88481.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {

    private TextView txtEarth;
    private TextView txtVenus;
    private TextView txtLength;

    private SeekBar skVenus;
    private SeekBar skEarth;
    private SeekBar skLength;

    private EarthVenusView veView;

    private LinearLayout lyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EarthVenusView(this));
        setContentView(R.layout.layout_test_01);

        //setContentView(R.layout.layout_test_02);

        this.init();
    }

    private void init(){
        this.txtEarth = (TextView)this.findViewById(R.id.txtEarth);
        this.txtVenus = (TextView)this.findViewById(R.id.txtVenus);
        this.txtLength = (TextView)this.findViewById(R.id.txtLength);

        this.skEarth = (SeekBar) this.findViewById(R.id.skEarth);
        this.skEarth.setOnSeekBarChangeListener(this);

        this.skVenus = (SeekBar) this.findViewById(R.id.skVenus);
        this.skVenus.setOnSeekBarChangeListener(this);

        this.skLength = (SeekBar) this.findViewById(R.id.skLength);
        this.skLength.setOnSeekBarChangeListener(this);

        this.lyContent = (LinearLayout)this.findViewById(R.id.lyContent);

        this.veView = new EarthVenusView(this);
        this.lyContent.addView(this.veView);

        //this.veView = (EarthVenusView)this.findViewById(R.id.veView);

        this.txtEarth.setText(String.valueOf(this.veView.getEarthAngleSpeed()));
        this.txtVenus.setText(String.valueOf(this.veView.getVenusAngleSpeed()));
        this.txtLength.setText(String.valueOf(this.veView.getTraceListLength()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()){
            case R.id.skEarth:
                this.veView.setEarthAngleSpeed(progress/100f);
                this.txtEarth.setText(String.valueOf(this.veView.getEarthAngleSpeed()));

                break;
            case R.id.skVenus:
                this.veView.setVenusAngleSpeed(progress/100f);
                this.txtVenus.setText(String.valueOf(this.veView.getVenusAngleSpeed()));

                break;
            case R.id.skLength:
                this.veView.setTraceListLength(progress);
                this.txtLength.setText(String.valueOf(this.veView.getTraceListLength()));
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
