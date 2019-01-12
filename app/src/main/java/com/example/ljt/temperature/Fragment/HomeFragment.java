package com.example.ljt.temperature.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.Misc.StringAdapter;
import com.example.ljt.temperature.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        List<String> stringList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        stringList.add("try");
        stringList.add("try2");
        StringAdapter adapter = new StringAdapter(stringList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.show_recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LJTlog","resume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LJTlog","start");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LJTlog","Pause");
    }
    /*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity=getActivity();
        activity.findViewById();
    }*/
}
