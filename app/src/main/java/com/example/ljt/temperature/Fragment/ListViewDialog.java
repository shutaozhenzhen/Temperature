package com.example.ljt.temperature.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ljt.temperature.Bluetooth.BluetoothConnect;
import com.example.ljt.temperature.Bluetooth.BluetoothDiscovery;
import com.example.ljt.temperature.Bluetooth.BluetoothExistenceCheck;
import com.example.ljt.temperature.Bluetooth.MyBluetooth;
import com.example.ljt.temperature.Bluetooth.NoBluetoothException;
import com.example.ljt.temperature.Bluetooth.MyBluetoothAdapter;
import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.Misc.ToastInContext;
import com.example.ljt.temperature.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ListViewDialog extends DialogFragment {
    private static final int REQUEST_ENABLE_BT = 1;
    private AppCompatActivity appCompatActivity;
    private BluetoothDiscovery discovery;
    private BTReceiver btReceiver;
    private BondReceiver bondReceiver;
    private BluetoothAdapter mBluetoothAdapter;
/*    public static final int Socket = 2;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {

            switch (message.what) {
                case Socket:
                    Log.v("LJTDL", handler.toString());
*//*
                    MainActivity.ConnectedThread connectedThread = new MainActivity.ConnectedThread((BluetoothConnect.MessageBackObj) message.obj);
                    connectedThread.start();*//*
                    break;

            }
        }
    };*/

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_dialog, null);
        try {
            mBluetoothAdapter = BluetoothExistenceCheck.getBluetoothAdapter();
            discovery = new BluetoothDiscovery(mBluetoothAdapter, getActivity());
            discovery.register();
            discovery.start();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
            btReceiver = new BTReceiver();
            getActivity().registerReceiver(btReceiver, intentFilter);

            //Set<BluetoothDevice> Bondedlist = mBluetoothAdapter.getBondedDevices();
           /* List<MyBluetooth> myBluetoothList = new ArrayList<>();
            for (BluetoothDevice device : Bondedlist) {
                myBluetoothList.add(new MyBluetooth(device));
            }
            ToastString(String.valueOf(myBluetoothList.size()));
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_dialog_recyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            MyBluetoothAdapter bluetoothAdapter = new MyBluetoothAdapter(myBluetoothList);
            recyclerView.setAdapter(bluetoothAdapter);*/
        } catch (NoBluetoothException ex) {
        }

        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void ToastString(String string) {
        ToastInContext toastInMain = new ToastInContext(getContext());
        toastInMain.toast(string);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        discovery.unregister();
        getActivity().unregisterReceiver(btReceiver);
        if(bondReceiver!=null)
        getActivity().unregisterReceiver(bondReceiver);
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
    }

    private void ToastString(int i) {
        ToastString(String.valueOf(i));
    }

    private class BTReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView

                List<MyBluetooth> myBluetoothList = discovery.getMyBluetoothList();
                //ToastString(String.valueOf(myBluetoothList.size()));
                RecyclerView recyclerView = (RecyclerView) getDialog().findViewById(R.id.list_dialog_recyclerView);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                final MyBluetoothAdapter bluetoothAdapter = new MyBluetoothAdapter(myBluetoothList);

                bluetoothAdapter.setOnMyBluetoothSelected(new MyBluetoothAdapter.OnMyBluetoothSelected() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void OnSelected(MyBluetooth myBluetooth) {
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        BluetoothDevice bluetoothDevice = myBluetooth.getBluetoothDevice();
                        if (bluetoothDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
                            bluetoothDevice.createBond();
                            // Create a BroadcastReceiver for ACTION_FOUND

                            IntentFilter Filter = new IntentFilter();
                            Filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
                            bondReceiver = new BondReceiver();
                            getActivity().registerReceiver(bondReceiver, Filter);
                        } else {
                            //Log.v("LJTDL", myBluetooth.getBluetoothDevice().getAddress());
                            BluetoothConnect connect = new BluetoothConnect(mBluetoothAdapter, myBluetooth, ((MainActivity) getActivity()).getHandler());
                            connect.begin();
                            dismiss();
                        }
                    }
                });
                recyclerView.setAdapter(bluetoothAdapter);

            }
        }
    }

   private class BondReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.v("LJTDL", action);
            // When discovery finds a device
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                if ((intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.BOND_BONDING)) == BluetoothDevice.BOND_BONDED) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    BluetoothConnect connect = new BluetoothConnect(mBluetoothAdapter, new MyBluetooth(device), ((MainActivity) getActivity()).getHandler());
                    connect.begin();
                    dismiss();
                }
            }
        }
    }
}
