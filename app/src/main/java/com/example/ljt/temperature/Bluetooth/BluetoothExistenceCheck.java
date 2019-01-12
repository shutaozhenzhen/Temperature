package com.example.ljt.temperature.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ljt.temperature.Misc.ToastInContext;

import java.security.PrivateKey;

public class BluetoothExistenceCheck {
    /*
        BluetoothAdapter表示本地设备蓝牙适配器。 BluetoothAdapter允许您执行基本的蓝牙任务，例如启
        动设备发现，查询绑定（配对）设备列表，使用已知MAC地址实例化BluetoothDevice ，以及创建
        BluetoothServerSocket以侦听来自其他设备的连接请求，并启动扫描蓝牙LE设备。
        要获得代表本地蓝牙适配器的BluetoothManager.getAdapter() ，请在BluetoothManager.getAdapter()
        上调用BluetoothManager.getAdapter()函数。
        在JELLY_BEAN_MR1及以下，您将需要使用静态getDefaultAdapter()方法。
        从根本上说，这是所有蓝牙操作的起点。
        获得本地适配器后，您可以使用getBondedDevices()获取一组代表所有配对设备的BluetoothDevice对象;
        使用startDiscovery()启动设备发现;
         或使用listenUsingRfcommWithServiceRecord(String, UUID)
         创建一个BluetoothServerSocket来监听传入的RFComm连接请求;
         或使用startLeScan(LeScanCallback)开始扫描蓝牙LE设备。
         */
/*    private void ToastString(){
        new ToastInContext(getContext()).toast(myBluetooth.getName());
    }*/
    public static BluetoothAdapter getBluetoothAdapter() throws NoBluetoothException {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new NoBluetoothException("");
        } else {
            return mBluetoothAdapter;
        }
    }
   /* public static void openBluetooth(BluetoothAdapter mBluetoothAdapter){
        if (!mBluetoothAdapter.isEnabled()) {
            final Integer REQUEST_ENABLE_BT=1;
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            AppCompatActivity appCompatActivity=new AppCompatActivity() {
                private void ToastString(String string) {
                    Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
                }
                @Override
                protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                    if (requestCode!=RESULT_CANCELED){
                        ToastString("蓝牙开启失败");
                    }else {
                        ToastString("蓝牙开启成功");
                    }
                }
            };
            appCompatActivity.startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);

        }
    }*/
}
