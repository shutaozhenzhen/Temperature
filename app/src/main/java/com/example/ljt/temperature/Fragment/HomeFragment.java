package com.example.ljt.temperature.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ljt.temperature.Layout.SliderDiscreteLayout;
import com.example.ljt.temperature.Layout.TemperatureSliderLayout;
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
    private TemperatureSliderFragment sliderFragment;
    private StringAdapter adapter;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private RecyclerView recyclerView;

    public StringAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(StringAdapter adapter) {
        this.adapter = adapter;
    }
    //private List<Entry> entryList=new ArrayList<>();
/*    public interface OnProgressChanged{
        void onProgressChanged(TemperatureSliderLayout slider, int progress, boolean fromUser);
    }

    public void setOnProgressChanged(OnProgressChanged onProgressChanged) {
        this.onProgressChanged = onProgressChanged;
    }

    private OnProgressChanged onProgressChanged;*/

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setTemperature(double temperature) {
        if (sliderFragment != null)
            sliderFragment.setValue(temperature);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        DealFragmentInID dealFragmentInID = new DealFragmentInID(R.id.temperature_view, fragmentManager);
        sliderFragment = new TemperatureSliderFragment();
        dealFragmentInID.replaceWithFragment(sliderFragment, sliderFragment.getClass().getName());
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ( (MainActivity) getActivity()).getHandler().obtainMessage(MainActivity.CHECK,isChecked).sendToTarget();
            }
        });
        if (checkBox.isChecked()) {

        }


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

/*        LinearLayoutManager manager = new LinearLayoutManager(getContext());
*//*        stringList.add("try");
        stringList.add("try2");*//*
        adapter = new StringAdapter(stringList);
        recyclerView = (RecyclerView) view.findViewById(R.id.show_recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);*/

        return view;
    }

/*    @Override
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
    }*/
    /*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity=getActivity();
        activity.findViewById();
    }*/
}
