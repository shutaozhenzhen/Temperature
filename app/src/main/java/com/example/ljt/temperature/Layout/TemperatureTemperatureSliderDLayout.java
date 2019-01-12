package com.example.ljt.temperature.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class TemperatureTemperatureSliderDLayout extends TemperatureSliderLayout {
    public TemperatureTemperatureSliderDLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*


    @Override
    public void setValue(Integer value) {
        int valueT=doubleToInt(value);
        super.setValue(valueT);
    }

    public double getValueD() {
        return intToDouble(super.getValue());
    }

    public double getMaxD() {
        return intToDouble(super.getMax());
    }

    @Override
    public void setMax(Integer max) {
        int maxT=doubleToInt(max);
        super.setMax(maxT);
    }

    public TemperatureTemperatureSliderDLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        maxLabel.setText(String.valueOf(doubleToInt(max)));
        minLabel.setText("0");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private Integer doubleToInt(double in){
      return (int)(in*100);
    }
    private double intToDouble(int in){
        return (in*1.0/100.0);
    }*/
}
