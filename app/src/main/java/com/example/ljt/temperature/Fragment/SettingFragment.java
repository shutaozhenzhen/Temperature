package com.example.ljt.temperature.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.ljt.temperature.Bluetooth.BluetoothExistenceCheck;
import com.example.ljt.temperature.Bluetooth.NoBluetoothException;
import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.R;
import com.example.ljt.temperature.Setting.Settings;

import static com.example.ljt.temperature.Layout.SliderDiscreteLayout.*;

public class SettingFragment extends Fragment {
    private Settings setting = new Settings();

    public Settings getSetting() {
        return setting;
    }
    public interface onSettingChangedListener{
        void onSwitchChanged();
        void onSliderChanged();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        initAll(view);
        return view;
    }


    public void setClickable(View view, @IdRes int id) {
        ((Switch) view.findViewById(id)).setClickable(false);
    }

    private void initAll(View view) {
        setting.init(view);
        Button discovery = (Button) view.findViewById(R.id.bluetooth_discovery_button);
        discovery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showListViewDialog();
            }
        });
        try {
            BluetoothExistenceCheck.getBluetoothAdapter();
            setting.setBluetoothSwitch(true);
        } catch (NoBluetoothException ex) {
            setting.setBluetoothSwitchEnable(false);
            setting.setButtonEnable(false);
        }
        setting.setOnSettingChangeListener(new Settings.OnSettingChangeListener() {
            @Override
            public void onLED_switch1Changed() {
                ((MainActivity)getActivity()).getConnectedOutputThread().led();
            }

            @Override
            public void onLED_switch2Changed() {

            }

            @Override
            public void onBuzzer_switchChanged() {
                ((MainActivity)getActivity()).getConnectedOutputThread().buzzer();
            }

            @Override
            public void onTime_buttonClick() {
                ((MainActivity)getActivity()).getConnectedOutputThread().time();

            }
        });
    }

}
