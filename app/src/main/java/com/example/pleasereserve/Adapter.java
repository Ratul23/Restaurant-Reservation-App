package com.example.pleasereserve;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Restaurant> {

    private Activity context;
    private List<Restaurant> memberlist;

    public Adapter(Activity context,List<Restaurant> memberlist) {
        super(context, R.layout.sample_layout, memberlist);
        this.context = context;
        this.memberlist = memberlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);
        Restaurant members=memberlist.get(position);
        TextView t6=view.findViewById(R.id.tv6);
        TextView t1=view.findViewById(R.id.tv1);
        TextView t2=view.findViewById(R.id.tv2);
        TextView t3=view.findViewById(R.id.tv3);

        t6.setText("Name: "+members.getName());
        t1.setText("Call: "+members.getCall());
        t2.setText("Seat: "+members.getSeat());
        t3.setText("Time: "+members.getTime());



        return view;
    }
}
