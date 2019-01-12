package com.example.ljt.temperature.Misc;

import android.content.Context;
import android.widget.Toast;

public class ToastInContext {
    private Context context=null;
    public ToastInContext(Context context) {
        this.context=context;
    }
    public void toast(String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }
}
