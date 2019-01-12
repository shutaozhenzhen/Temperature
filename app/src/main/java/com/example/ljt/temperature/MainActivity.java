package com.example.ljt.temperature;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ljt.temperature.Bluetooth.BluetoothConnect;
import com.example.ljt.temperature.Bluetooth.BluetoothExistenceCheck;
import com.example.ljt.temperature.Bluetooth.NoBluetoothException;
import com.example.ljt.temperature.Fragment.HomeFragment;
import com.example.ljt.temperature.Fragment.ListViewDialog;
import com.example.ljt.temperature.Fragment.SettingFragment;
import com.example.ljt.temperature.Misc.StringAdapter;
import com.example.ljt.temperature.Misc.ToastInContext;
import com.example.ljt.temperature.Misc.DealFragmentInID;
import com.example.ljt.temperature.Setting.Setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    public static final int Socket = 2;
    public static final int TIME = 3;
    public static final int TEMP_I = 4;
    public static final int TEMP_O = 5;
    private InputStream inputStream;
    private OutputStream outputStream;
    private NavigationDeal deal;
    private ConnectedThread connectedThread;
    private ConnectedInputThread connectedInputThread;


    private ConnectedOutputThread connectedOutputThread;

    public NavigationDeal getDeal() {
        return deal;
    }

    public ConnectedOutputThread getConnectedOutputThread() {
        return connectedOutputThread;
    }

    private Handler handler/* = new Handler() {
        public void handleMessage(Message message) {
            // Log.v("LJTDL", handler.toString());
            switch (message.what) {
                case Socket:
                    // Log.v("LJTDL", handler.toString());
                    if (connectedThread != null)
                        connectedThread.cancel();
                    connectedThread = new ConnectedThread((BluetoothConnect.MessageBackObj) message.obj);
                    connectedThread.start();
                    break;
            }
        }
    }*/;


    public Handler getHandler() {
        return handler;
    }

    private class ConnectedThread extends Thread {
        private BluetoothSocket mmSocket;

        private final BluetoothConnect.MessageBackObj obj;

        public ConnectedThread(BluetoothConnect.MessageBackObj obj) {
            this.obj = obj;


        }

        public void run() {
            BluetoothSocket socket = obj.getBluetoothSocket();
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
                Log.v("LJTDL", "input and output");
            } catch (IOException e) {
            }
            if (connectedInputThread != null)
                connectedInputThread.cancel();
            connectedInputThread = new ConnectedInputThread(tmpIn, MainActivity.this);
            connectedInputThread.start();
            if (connectedOutputThread != null)
                connectedOutputThread.cancel();
            connectedOutputThread = new ConnectedOutputThread(tmpOut);
            connectedOutputThread.start();
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {

            connectedInputThread.cancel();

            connectedOutputThread.cancel();

            try {


                mmSocket.close();

            } catch (IOException e) {
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Fragment[] fragments = {new HomeFragment(), new SettingFragment()};
        final NavigationDeal deal = new NavigationDeal((BottomNavigationView) findViewById(R.id.navigation), getSupportFragmentManager(), R.id.fragmentFrame, fragments); //new HomeFragment(), new SettingFragment());

        deal.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Setting setting = new Setting(MainActivity.this);
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        deal.hideFragment(deal.getFragmentByClass(SettingFragment.class));
                        deal.showFragment(deal.getFragmentByClass(HomeFragment.class));

                        return true;
                    case R.id.navigation_setting:
                        deal.hideFragment(deal.getFragmentByClass(HomeFragment.class));
                        deal.showFragment(deal.getFragmentByClass(SettingFragment.class));
                        return true;
                }
                return false;
            }
        });
        deal.hideAllFragment();
        ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_setting);

        try {
            BluetoothAdapter mBluetoothAdapter = BluetoothExistenceCheck.getBluetoothAdapter();

        } catch (NoBluetoothException ex) {
            ToastString("该设备不存在蓝牙。");
        }

        handler = new Handler() {
            public void handleMessage(Message message) {
                // Log.v("LJTDL", handler.toString());
                switch (message.what) {
                    case Socket:
                        // Log.v("LJTDL", handler.toString());
                        if (connectedThread != null)
                            connectedThread.cancel();
                        connectedThread = new ConnectedThread((BluetoothConnect.MessageBackObj) message.obj);
                        connectedThread.start();
                        break;
                    case TIME:
                        ((TextView) deal.getFragmentByClass(SettingFragment.class).getView().findViewById(R.id.time_label)).setText(message.obj.toString());
                        break;
                    case TEMP_I:
                        ((HomeFragment) deal.getFragmentByClass(HomeFragment.class)).getStringList().add(message.obj.toString());

                        break;
                    case TEMP_O:
                        break;
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                bluetoothResult(resultCode, data);
                break;
        }
    }

    private void bluetoothResult(int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                ToastString("蓝牙开启成功");
                showListViewDialog();
                break;
            case RESULT_CANCELED:
                ToastString("蓝牙开启失败");
                break;
        }
    }

    public void showListViewDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ListViewDialog listViewDialog = new ListViewDialog();
        listViewDialog.show(fragmentManager, listViewDialog.getClass().getName());
    }

    public void ToastString(String string) {
        ToastInContext toastInMain = new ToastInContext(MainActivity.this);
        toastInMain.toast(string);
    }
}
