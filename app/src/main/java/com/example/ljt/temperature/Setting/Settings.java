package com.example.ljt.temperature.Setting;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.ljt.temperature.Layout.SliderDiscreteLayout;
import com.example.ljt.temperature.R;

public class Settings {
    SliderDiscreteLayout
            buzzer_slider,
            LED_slider1,
            LED_slider2;

    Switch

            bluetooth_switch,
            LED_switch1,
            LED_switch2,
            buzzer_switch;
    Button
            discovery_button;

    public void init(final View view) {
        buzzer_slider = (SliderDiscreteLayout) view.findViewById(R.id.buzzer_slider);
        LED_slider1 = (SliderDiscreteLayout) view.findViewById(R.id.LED_slider1);
        LED_slider2 = (SliderDiscreteLayout) view.findViewById(R.id.LED_slider2);
        bluetooth_switch = (Switch) view.findViewById(R.id.bluetooth_switch);
        LED_switch1 = (Switch) view.findViewById(R.id.LED_switch1);
        LED_switch2 = (Switch) view.findViewById(R.id.LED_switch2);
        buzzer_switch = (Switch) view.findViewById(R.id.buzzer_switch);
        bluetooth_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    setBluetoothSwitch(isChecked);
                    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    //Button button = (Button) buttonView.getRootView().findViewById(R.id.bluetooth_discovery_button);
                    if (isChecked) {


                        bluetoothAdapter.enable();
                        discovery_button.setEnabled(true);

                        //button.setEnabled(true);
                    } else {

                        bluetoothAdapter.disable();
                        discovery_button.setEnabled(false);
                        //button.setEnabled(false);
                    }
                }



        });
        buzzer_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setBuzzerSwitch(isChecked);
            }
        });
        LED_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setLedSwitch1(isChecked);
            }
        });
        LED_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setLedSwitch2(isChecked);
            }
        });

        buzzer_slider.setOnSliderChangeListener(new SliderDiscreteLayout.OnSliderChangeListener() {
            @Override
            public void onProgressChanged(SliderDiscreteLayout slider, int progress, boolean fromUser) {
                setBuzzerSlider(slider.getValue());
                //new  ToastInContext(getActivity()).toast("try");
            }
        });

        LED_slider1.setOnSliderChangeListener(new SliderDiscreteLayout.OnSliderChangeListener() {
            @Override
            public void onProgressChanged(SliderDiscreteLayout slider, int progress, boolean fromUser) {
                setLedSlider1(slider.getValue());
                //new  ToastInContext(getActivity()).toast("try");
            }
        });

        LED_slider2.setOnSliderChangeListener(new SliderDiscreteLayout.OnSliderChangeListener() {
            @Override
            public void onProgressChanged(SliderDiscreteLayout slider, int progress, boolean fromUser) {
                setLedSlider2(slider.getValue());
                //new  ToastInContext(getActivity()).toast("try");
            }
        });
        discovery_button = (Button) view.findViewById(R.id.bluetooth_discovery_button);
        buzzer_slider.setMode(1);

    }

    public boolean isBluetoothSwitch() {
        return bluetooth_switch.isChecked();
    }

    public void setBluetoothSwitch(boolean bluetoothSwitch) {
        this.bluetooth_switch.setChecked(bluetoothSwitch);
    }

    public boolean isButtonEnable() {
        return discovery_button.isEnabled();
    }

    public void setButtonEnable(boolean enable) {
        this.discovery_button.setEnabled(enable);
    }

    public boolean isBluetoothSwitchEnable() {
        return bluetooth_switch.isEnabled();
    }

    public void setBluetoothSwitchEnable(boolean bluetoothSwitch) {
        this.bluetooth_switch.setEnabled(bluetoothSwitch);
    }

    public boolean isBuzzerSwitch() {
        return buzzer_switch.isChecked();
    }

    public void setBuzzerSwitch(boolean buzzerSwitch) {
        this.buzzer_switch.setChecked(buzzerSwitch);
    }

    public boolean isLedSwitch1() {
        return LED_switch1.isChecked();
    }

    public void setLedSwitch1(boolean ledSwitch1) {
        this.LED_switch1.setChecked(ledSwitch1);
    }

    public boolean isLedSwitch2() {
        return LED_switch2.isChecked();
    }

    public void setLedSwitch2(boolean ledSwitch2) {
        this.LED_switch2.setChecked(ledSwitch2);
    }

    public Integer getBuzzerSlider() {
        return buzzer_slider.getValue();
    }

    public void setBuzzerSlider(Integer buzzerSlider) {
        this.buzzer_slider.setValue(buzzerSlider);
    }

    public Integer getLedSlider1() {
        return LED_slider1.getValue();
    }

    public void setLedSlider1(Integer ledSlider1) {
        this.LED_slider1.setValue(ledSlider1);
    }

    public Integer getLedSlider2() {
        return LED_slider2.getValue();
    }

    public void setLedSlider2(Integer ledSlider2) {
        this.LED_slider2.setValue(ledSlider2);
    }
}
