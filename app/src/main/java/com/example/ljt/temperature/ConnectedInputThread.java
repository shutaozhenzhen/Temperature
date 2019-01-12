package com.example.ljt.temperature;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.ljt.temperature.Bluetooth.BluetoothConnect;
import com.example.ljt.temperature.Misc.ToastInContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.chrono.ChronoZonedDateTime;

public class ConnectedInputThread extends Thread {

    private final InputStream mmInStream;
    //private final OutputStream mmOutStream;

    public ConnectedInputThread(InputStream inputStream) {
        mmInStream = inputStream;
    }

    private boolean isReceiveAction(byte b,String string) {
        switch (b) {
            case 'i':
            case 'o':
            case 't':
                return true;
            default:
                return false;
        }

    }

    public void run() {
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
                    while (in != -1);
                    if (bytesRead > 0) {
                        if (isReceiveAction(bytes[bytesRead - 1],new String(bytes, 0, bytesRead - 1))) {
                            Log.v("LJTDL", (new String(bytes, 0, bytesRead - 1)));
                        } else
                            Log.e("LJTDL", (new String(bytes, 0, bytesRead - 1)));
                    }
                    bytesRead = 0;
                }
            } catch (IOException e) {

            }
        }
    }


    public void  cancel(){
        try {
            mmInStream.close();
        } catch (IOException e) {

        }
    }
}




