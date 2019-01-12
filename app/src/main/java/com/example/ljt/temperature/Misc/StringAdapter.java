package com.example.ljt.temperature.Misc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ljt.temperature.Bluetooth.MyBluetooth;
import com.example.ljt.temperature.R;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder>  {
    private List<String> stringList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = (TextView) itemView.findViewById(R.id.textView_item);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String string=stringList.get(i);

        viewHolder.view.setText(string);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    public StringAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

}
