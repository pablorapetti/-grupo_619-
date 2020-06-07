package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListaAdapter extends ArrayAdapter<EventSensor> {

    public ListaAdapter(Context context, int layout, List<EventSensor> lista) {
        super(context,0, lista);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layInf = LayoutInflater.from(getContext());
        view = layInf.inflate(R.layout.data, null);
        EventSensor e = getItem(i);
        TextView data = view.findViewById(R.id.dataId);
        data.setText(e.getName()+" "+e.getDesc());

        return view;
    }
}
