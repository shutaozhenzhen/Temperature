package com.example.ljt.temperature.Layout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ljt.temperature.R;

public class SliderLayout extends ConstraintLayout {
    private Context context;
    private TextView minLabel;
    private TextView maxLabel;
    private TextView valueLabel;
    private SeekBar seekBar;
    private Integer max;

    private final Integer MODE_NORMAL = 0, MODE_LOG = 1;
    private Integer mode = MODE_NORMAL;

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setValue(Integer value) {
        seekBar.setProgress(value);
    }

    public void setMax(Integer max) {
        if (max < seekBar.getProgress()) {
            setValue(max);
        }
        seekBar.setMax(max);
        this.max = max;
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
        if (mode == MODE_NORMAL) {
            valueLabel.setText(String.valueOf(seekBar.getProgress()));
        } else if (mode == MODE_LOG) {
            valueLabel.setText(String.valueOf(max * (Math.log10(seekBar.getProgress()) - Math.log10(max))));
        }

    }

    public SliderLayout(Context context, AttributeSet attrs) {
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
