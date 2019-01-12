package com.example.ljt.temperature.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ljt.temperature.Layout.SliderDiscreteLayout;
import com.example.ljt.temperature.Layout.TemperatureSliderLayout;
import com.example.ljt.temperature.R;

public class TemperatureSliderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.temperature_seekbar_fragment,container,false);
        TemperatureSliderLayout sliderLayout = view.findViewById(R.id.temperature_slider);
        sliderLayout.setMax(100);
        sliderLayout.setOnSliderChangeListener(new TemperatureSliderLayout.OnSliderChangeListener() {
            @Override
            public void onProgressChanged(TemperatureSliderLayout slider, int progress, boolean fromUser) {
                slider.setValue(progress);
            }


        });
        return view;
    }
}
