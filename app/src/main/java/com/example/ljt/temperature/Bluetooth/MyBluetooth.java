package com.example.ljt.temperature.Bluetooth;

import android.bluetooth.BluetoothDevice;

public class MyBluetooth {
    private String fullName;
    private String name;
    private String address;

    private BluetoothDevice bluetoothDevice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        init();
    }

    public MyBluetooth(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        init();
    }

    public void init() {
        address = bluetoothDevice.getAddress();

        //name = fullName = bluetoothDevice.getName();

        name = fullName = bluetoothDevice.getAddress();
        name +=": "+bluetoothDevice.getName();


    }
}
