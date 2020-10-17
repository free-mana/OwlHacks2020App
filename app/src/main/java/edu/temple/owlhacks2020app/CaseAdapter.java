package edu.temple.owlhacks2020app;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CaseAdapter extends BaseAdapter {
    private Context context;
    private String names[];
    private Location locations[];
    private boolean infected[];

    public CaseAdapter(Context context, String names[], Location locations[], boolean infected[]){
        this.context = context;
        this.names = names;
        this.locations = locations;
        this.infected = infected;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int pos) {
        return names[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null){
            textView = new TextView(context);
        }else{
            textView = (TextView) convertView;
        }
        String display = names[pos];// + ": " + locations[pos].toString();
        textView.setText(display);
        textView.setGravity(Gravity.CENTER);
        if(infected[pos]){
            textView.setBackgroundColor(Color.RED);
        }
        return textView;
    }
}
