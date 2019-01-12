package com.example.ljt.temperature.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BluetoothDiscovery {
    private BluetoothAdapter bluetoothAdapter;
    private List<MyBluetooth> myBluetoothList = new ArrayList<>();
    Context context;
    BTReceiver btReceiver;

    public BluetoothDiscovery(BluetoothAdapter bluetoothAdapter, Context context) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.context = context;
    }

    public void clearList() {
        myBluetoothList = new ArrayList<>();
    }

    public void start() {

        bluetoothAdapter.startDiscovery();
    }

    public void unregister() {
        context.unregisterReceiver(btReceiver);

    }

    public void add(MyBluetooth myBluetooth) {
        myBluetoothList.add(myBluetooth);
    }

    public List<MyBluetooth> getMyBluetoothList() {
        return myBluetoothList;
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        btReceiver = new BTReceiver();
        context.registerReceiver(btReceiver, intentFilter);

    }

    private class BTReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                myBluetoothList.add(new MyBluetooth(device));
            }
        }
    }

    ;
}
