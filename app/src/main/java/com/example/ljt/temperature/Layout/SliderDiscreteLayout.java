package com.example.ljt.temperature.Layout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ljt.temperature.R;

public class SliderDiscreteLayout extends ConstraintLayout {
    protected Context context;
    protected TextView minLabel;
    protected TextView maxLabel;
    protected TextView valueLabel;
    protected SeekBar seekBar;
    protected Integer max;

    public void setOnSliderChangeListener(OnSliderChangeListener listener) {
        this.onSliderChangeListener = listener;
    }

    private OnSliderChangeListener onSliderChangeListener;

    public interface OnSliderChangeListener{
         void onProgressChanged(SliderDiscreteLayout slider, int progress, boolean fromUser);

    }
    public SeekBar getSeekBar() {
        return seekBar;
    }

    public final Integer MODE_NORMAL = 0, MODE_LOG = 1;
    private Integer mode = MODE_NORMAL;

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setValue(Integer value) {
        seekBar.setProgress(value);
        //setLabelValue();
    }
    public Integer getValue() {
        return seekBar.getProgress();
    }
    public Integer getMax() {
        return max;
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
            maxLabel.setText("10^"+max);
            valueLabel.setText("10^"+String.valueOf(seekBar.getProgress()));//String.valueOf(max * (Math.log10(seekBar.getProgress()) - Math.log10(max))));
        }


    }

    /*public class OnSliderChangeListener implements SeekBar.OnSeekBarChangeListener {
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
    }*/
    public SliderDiscreteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.seekbar_discrete_layout, this);
        init();
        maxLabel.setText(String.valueOf(max));
        minLabel.setText("0");
        setLabelValue();
        final SliderDiscreteLayout temp=this;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setLabelValue();
                if ( onSliderChangeListener!= null) {
                    onSliderChangeListener.onProgressChanged(temp, progress, fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /*new */

    }

}
