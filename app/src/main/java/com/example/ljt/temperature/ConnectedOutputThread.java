package com.example.ljt.temperature;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedOutputThread extends Thread {

    private final OutputStream mmoutStream;
    //private final OutputStream mmOutStream;

    public ConnectedOutputThread(OutputStream outputStream) {
        mmoutStream = outputStream;
    }
    public void led(){
        try {
            mmoutStream.write('l');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void buzzer(){
        try {
            mmoutStream.write('b');
        } catch (IOException e) {
            
        }
    }
    public void time(){
        try {
            mmoutStream.write('r');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void tempF(double t){
        try {
            mmoutStream.write((String.valueOf(t)+"f").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isSendAction(byte b) {
        switch (b) {
            case 'l':
            case 'b':
            case 'r':
            case 'f':
                return true;
            default:
                return false;
        }

    }
    public void  cancel(){
        try {
            mmoutStream.close();
        } catch (IOException e) {

        }
    }
}




