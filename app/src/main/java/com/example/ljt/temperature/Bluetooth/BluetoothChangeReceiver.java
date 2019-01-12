package com.example.ljt.temperature.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.bluetooth.BluetoothAdapter.*;

public class BluetoothChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int result = intent.getIntExtra("EXTRA_STATE", STATE_OFF);
        switch (result) {
            case STATE_OFF:

                break;
            case STATE_ON:

                break;
            default:
        }
    }
}
