package com.example.ljt.temperature.Bluetooth;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ljt.temperature.Misc.ToastInContext;
import com.example.ljt.temperature.R;

import java.util.List;

public class MyBluetoothAdapter extends RecyclerView.Adapter<MyBluetoothAdapter.ViewHolder>  {
    private List<MyBluetooth> myBluetoothList;
    public interface OnMyBluetoothSelected{
        void OnSelected(MyBluetooth myBluetooth);
    }
    OnMyBluetoothSelected onMyBluetoothSelected;

    public void setOnMyBluetoothSelected(OnMyBluetoothSelected onMyBluetoothSelected) {
        this.onMyBluetoothSelected = onMyBluetoothSelected;
    }

    public MyBluetooth getMyBluetoothSelected() {
        return myBluetoothSelected;
    }

    private MyBluetooth myBluetoothSelected;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bluetoothName;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bluetoothName = (TextView) itemView.findViewById(R.id.textView_item);
            this.itemView = itemView;


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                myBluetoothSelected = myBluetoothList.get(position);
                if ( onMyBluetoothSelected!= null) {
                    onMyBluetoothSelected.OnSelected(myBluetoothSelected);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyBluetooth myBluetooth = myBluetoothList.get(i);
        viewHolder.bluetoothName.setText(myBluetooth.getName());
    }

    @Override
    public int getItemCount() {
        return myBluetoothList.size();
    }


    public MyBluetoothAdapter(List<MyBluetooth> myBluetoothList) {
        this.myBluetoothList = myBluetoothList;
    }

}
