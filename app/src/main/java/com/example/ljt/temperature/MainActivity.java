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
import android.util.Log;
import android.view.MenuItem;
import android.support.annotation.Nullable;
import android.widget.Switch;

import com.example.ljt.temperature.Bluetooth.BluetoothConnect;
import com.example.ljt.temperature.Bluetooth.BluetoothExistenceCheck;
import com.example.ljt.temperature.Bluetooth.NoBluetoothException;
import com.example.ljt.temperature.Fragment.HomeFragment;
import com.example.ljt.temperature.Fragment.ListViewDialog;
import com.example.ljt.temperature.Fragment.SettingFragment;
import com.example.ljt.temperature.Misc.ToastInContext;
import com.example.ljt.temperature.Misc.DealFragmentInID;
import com.example.ljt.temperature.Setting.Setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    public static final int Socket = 2;
    private InputStream inputStream;
    private OutputStream outputStream;
    private NavigationDeal deal;
    private ConnectedThread connectedThread;
    private ConnectedInputThread connectedInputThread;
    private ConnectedOutputThread connectedOutputThread;

    public NavigationDeal getDeal() {
        return deal;
    }

    private Handler handler = new Handler() {
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
    };

    public Handler getHandler() {
        return handler;
    }

    private class ConnectedThread extends Thread {
        private  BluetoothSocket mmSocket;

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
            connectedInputThread = new ConnectedInputThread(tmpIn);
            connectedInputThread.start();
            if (connectedOutputThread != null)
                connectedOutputThread.cancel();
            connectedOutputThread = new ConnectedOutputThread(tmpOut);
            connectedOutputThread.start();
        }/*

            try {
                mmOutStream.write("r".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    int bytesRead = 0;
                    int bytesToRead = 1024;
                    while (bytesRead < bytesToRead) {
                        byte[] bytes = new byte[bytesToRead - bytesRead];
                        int in;
                        do {
                            in = mmInStream.read();
                            if (in == '\n') break;
                            else if (in != '\r' && in != ' ') {
                                bytes[bytesRead] = (byte) in;
                                bytesRead++;
                            }
                        }
                        while (in != -1); //&& in != '\n');
                        if (bytesRead > 0) {
                            if (isReceiveAction(bytes[bytesRead - 1])) {
                                Log.v("LJTDL", (new String(bytes, 0, bytesRead - 1)));
                            } else
                                Log.e("LJTDL", (new String(bytes, 0, bytesRead - 1)));
                        }
                        bytesRead = 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        *//* Call this from the main activity to send data to the remote device *//*
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
            }
        }*/

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

    /*
        public MainFragments getMainFragments() {
            return mainFragments;
        }*/
    //private MainFragments mainFragments = new MainFragments();
    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Setting setting = new Setting(MainActivity.this);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainFragments.showHome();
                    return true;
                case R.id.navigation_setting:
                    mainFragments.showSetting();
                    return true;
            }
            return false;
        }
    };
*/
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
  /*                      deal.hideFragment(fragments[1]);
                       deal.showFragment(fragments[0]);*/
                        return true;
                    case R.id.navigation_setting:
                        deal.hideFragment(deal.getFragmentByClass(HomeFragment.class));
                        deal.showFragment(deal.getFragmentByClass(SettingFragment.class));
                        //deal.hideFragment(fragments[0]);
                        //deal.showFragment(fragments[1]);
                        //deal.showFragment(deal.getFragmentByClass(SettingFragment.class));
                        //mainFragments.showSetting();
                        return true;
                }
                return false;
            }
        });
        deal.hideAllFragment();
        ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_setting);
        /*  mainFragments.init();
        mainFragments.showSetting();*/
        try {
            BluetoothAdapter mBluetoothAdapter = BluetoothExistenceCheck.getBluetoothAdapter();
/*            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }*/
        } catch (NoBluetoothException ex) {
            ToastString("该设备不存在蓝牙。");
        }
       /* BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
    }

    /*
        public class MainFragments {
            private Fragment homeFragment, settingFragment;
            private FragmentManager manager = getSupportFragmentManager();
            private final int id = R.id.fragmentFrame;
            private DealFragmentInID deal = new DealFragmentInID(id, manager);
            public MainFragments init() {
                MainFragments mainFragments = new MainFragments();
                homeFragment = add(new HomeFragment());
                settingFragment = add(new SettingFragment());
                return mainFragments;
            }
            public void showHome() {
                hideAll();
                deal.showFragment(homeFragment);
            }
            public void showSetting() {
                hideAll();
                deal.showFragment(settingFragment);
            }
            private void hideAll() {
                deal.hideFragment(homeFragment);
                deal.hideFragment(settingFragment);
            }
            private String getTag(Object object) {
                return object.getClass().getName();
            }
            private Fragment add(Fragment fragment) {
                deal.addWithFragment(fragment, fragment.getTag());
                return fragment;
            }
            public HomeFragment getHomeFragment() {
                return (HomeFragment) homeFragment;
            }
            public SettingFragment getSettingFragment() {
                return (SettingFragment) settingFragment;
            }
            private MainFragments() {
            }
        }*/
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
