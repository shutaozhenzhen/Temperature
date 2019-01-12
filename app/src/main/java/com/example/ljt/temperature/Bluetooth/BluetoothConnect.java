package com.example.ljt.temperature.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.R;

import java.io.IOException;
import java.util.UUID;

public class BluetoothConnect {
    private BluetoothAdapter bluetoothAdapter;
    private MyBluetooth myBluetooth;
    private static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket socket;
    private Handler handler;

    public class MessageBackObj {

        private BluetoothAdapter bluetoothAdapter;
        private MyBluetooth myBluetooth;
        private BluetoothSocket bluetoothSocket;

        public BluetoothAdapter getBluetoothAdapter() {
            return bluetoothAdapter;
        }

        public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
            this.bluetoothAdapter = bluetoothAdapter;
        }

        public MyBluetooth getMyBluetooth() {
            return myBluetooth;
        }

        public void setMyBluetooth(MyBluetooth myBluetooth) {
            this.myBluetooth = myBluetooth;
        }

        public BluetoothSocket getBluetoothSocket() {
            return bluetoothSocket;
        }

        public void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
            this.bluetoothSocket = bluetoothSocket;
        }
    }

    public BluetoothConnect(BluetoothAdapter bluetoothAdapter, MyBluetooth myBluetooth, Handler handler) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.myBluetooth = myBluetooth;
        this.handler = handler;

    }

    public MyBluetooth getMyBluetooth() {
        return myBluetooth;
    }

    public void setMyBluetooth(MyBluetooth myBluetooth) {
        this.myBluetooth = myBluetooth;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void begin() {
        ConnectThread connectThread = new ConnectThread(myBluetooth.getBluetoothDevice());
        Log.v("LJTDL","createConnectThread");
        //connectThread.start();
        connectThread.start();
    }
/*    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        private AppCompatActivity appCompatActivity;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(uuid);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void start(AppCompatActivity mainActivity) {
            appCompatActivity=mainActivity;
            super.start();

        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            bluetoothAdapter.cancelDiscovery();
            ((MainActivity)appCompatActivity).getDeal().hideAllFragment();
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket(mmSocket);
        }

        public void manageConnectedSocket(BluetoothSocket socket) {
            ((MainActivity)appCompatActivity).getDeal().hideAllFragment();
*//*            Message message = new Message();
            MessageBackObj obj=new MessageBackObj();
            obj.setBluetoothAdapter(bluetoothAdapter);
            obj.setBluetoothSocket(socket);
            obj.setMyBluetooth(myBluetooth);
            message.obj = obj;
            message.what = MainActivity.Socket;

            handler.sendMessage(message);*//*
        }

        */

    /**
     * Will cancel an in-progress connection, and close the socket
     *//*
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }*/
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        //private AppCompatActivity appCompatActivity;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;
            Log.v("LJTDL","createRfcommSocketToServiceRecordSTART");
            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(uuid);
                Log.v("LJTDL","createRfcommSocketToServiceRecord");
            } catch (IOException e) {
                Log.e("LJTDL","createRfcommSocketToServiceRecordERROR");
            }
            mmSocket = tmp;
        }

/*        public void start() {
            super.start();
            //this.appCompatActivity=mainActivity;
        }*/

        public void run() {

            // Cancel discovery because it will slow down the connection
            bluetoothAdapter.cancelDiscovery();

            //manageConnectedSocket(mmSocket);
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                Log.v("LJTDL","run");
                mmSocket.connect();
                Log.v("LJTDL","connect");
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                    Log.v("LJTDL","close");
                } catch (IOException closeException) {
                    Log.v("LJTDL","ex");
                }

                return;
            }
            manageConnectedSocket(mmSocket);

            // Do work to manage the connection (in a separate thread)
            //manageConnectedSocket(mmSocket);
        }

        public void manageConnectedSocket(BluetoothSocket socket) {
            //Message message = new Message();
            MessageBackObj obj = new MessageBackObj();
            obj.setBluetoothAdapter(bluetoothAdapter);
            obj.setBluetoothSocket(socket);
            obj.setMyBluetooth(myBluetooth);
/*            message.obj = obj;
            message.what = MainActivity.Socket;*/

            handler.obtainMessage(MainActivity.Socket,obj).sendToTarget();
        }
    }
}

        /**
         * Will cancel an in-progress connection, and close the socket
         *//*
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }*/
/*    private class AcceptThread extends Thread {
      private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("temperature", uuid);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket(socket);
                    cancel();
                    break;
                }
            }
        }
        public void manageConnectedSocket(BluetoothSocket socket){

        }

        *//**//** Will cancel the listening socket, and cause the thread to finish *//**//*
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }*/

