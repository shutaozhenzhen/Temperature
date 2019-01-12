package com.example.ljt.temperature.Fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ljt.temperature.Bluetooth.BluetoothExistenceCheck;
import com.example.ljt.temperature.Bluetooth.NoBluetoothException;
import com.example.ljt.temperature.Layout.SliderDiscreteLayout;
import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.Misc.ToastInContext;
import com.example.ljt.temperature.R;
import com.example.ljt.temperature.Setting.Setting;
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

/*        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        getActivity().registerReceiver(mReceiver, filter);*/

        return view;
    }

   /* private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                Integer nowState = intent.getParcelableExtra(BluetoothAdapter.EXTRA_STATE);
                Integer preState = intent.getParcelableExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE);
                if(nowState!=preState){
                    if(nowState==BluetoothAdapter.STATE_ON){
                        if(!setting.isBluetoothSwitch()){
                            setting.setBluetoothSwitch(true);
                        }

                    }else if(nowState==BluetoothAdapter.STATE_OFF){
                        if(setting.isBluetoothSwitch()){
                            setting.setBluetoothSwitch(false);
                        }
                    }
                }
            }

        }

    };*/

    public void setClickable(View view, @IdRes int id) {
        ((Switch) view.findViewById(id)).setClickable(false);
    }

    private void initAll(View view) {
        setting.init(view);
        //initAllSwitch(view);
        //initAllSlider(view);
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
    }

}
