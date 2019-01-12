package com.example.ljt.temperature.Layout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ljt.temperature.R;

public class TemperatureSliderLayout extends ConstraintLayout {
    private Context context;
    private TextView minLabel;
    private TextView maxLabel;
    private TextView valueLabel;
    private SeekBar seekBar;
    private Integer max;
    private OnSliderChangeListener onSliderChangeListener;

    private Integer doubleToInt(double in) {
        return (int) (in * 100);
    }

    private double intToDouble(int in) {
        return (in * 1.0 / 100.0);
    }

    public void setOnSliderChangeListener(OnSliderChangeListener listener) {
        this.onSliderChangeListener = listener;
    }

    public interface OnSliderChangeListener {
        void onProgressChanged(TemperatureSliderLayout slider, int progress, boolean fromUser);

    }


    public void setValue(double value) {
        seekBar.setProgress(doubleToInt(value));
    }

    public double getValue() {
        return intToDouble(seekBar.getProgress());
    }

    public void setMax(Integer max) {
        Integer maxT = doubleToInt(max);
        if (maxT < seekBar.getProgress()) {
            setValue(maxT);
        }
        seekBar.setMax(maxT);
        this.max = maxT;
        maxLabel.setText(String.valueOf(max));
    }


    private void init() {
        minLabel = (TextView) findViewById(R.id.min_label);
        maxLabel = (TextView) findViewById(R.id.max_label);
        valueLabel = (TextView) findViewById(R.id.value_label);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        max = seekBar.getMax();
    }

    private void setLabelValue() {
        valueLabel.setText(String.format("%.2f",getValue()));
    }


    public TemperatureSliderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.seekbar_layout, this);
        init();
        maxLabel.setText(String.valueOf(max));
        minLabel.setText("0");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setLabelValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
