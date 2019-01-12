package com.example.ljt.temperature.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ljt.temperature.Layout.SliderDiscreteLayout;
import com.example.ljt.temperature.MainActivity;
import com.example.ljt.temperature.Misc.DealFragmentInID;
import com.example.ljt.temperature.Misc.StringAdapter;
import com.example.ljt.temperature.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private List<String> stringList = new ArrayList<>();
    //private List<Entry> entryList=new ArrayList<>();

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        DealFragmentInID dealFragmentInID = new DealFragmentInID(R.id.temperature_view, getActivity().getSupportFragmentManager());
        TemperatureSliderFragment sliderFragment = new TemperatureSliderFragment();
        dealFragmentInID.replaceWithFragment(sliderFragment, sliderFragment.getClass().getName());
        SliderDiscreteLayout sliderDiscreteLayout = view.findViewById(R.id.temperature_slider);
/*        TemperatureChartFragment chartFragment = new TemperatureChartFragment();
        dealFragmentInID.replaceWithFragment(chartFragment, TemperatureChartFragment.class.getName());*/
        final EditText editText = view.findViewById(R.id.alarm_input);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    double alarm = Double.parseDouble(editText.getText().toString());
                    ((MainActivity) getActivity()).getConnectedOutputThread().tempF(alarm);
                    return true;
                }
                return false;

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
/*        stringList.add("try");
        stringList.add("try2");*/
        StringAdapter adapter = new StringAdapter(stringList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.show_recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LJTlog", "resume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LJTlog", "start");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LJTlog", "Pause");
    }
    /*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity=getActivity();
        activity.findViewById();
    }*/
}
