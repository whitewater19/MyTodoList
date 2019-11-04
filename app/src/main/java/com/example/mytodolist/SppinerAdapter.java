package com.example.mytodolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SppinerAdapter extends BaseAdapter {

    ArrayList<Colordata> colorlist;
    LayoutInflater inflater;
    Context context;

    public SppinerAdapter(Context context, ArrayList<Colordata> colorlist) {
        this.colorlist = colorlist;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return colorlist.size();
    }

    @Override
    public Object getItem(int position) {
        return colorlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return colorlist.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Colordata item = (Colordata) getItem(position);
        View v = inflater.inflate(R.layout.colorview,null);

        ImageView ticket = v.findViewById(R.id.ticket);
        TextView color_name = v.findViewById(R.id.color_name);

        ticket.setBackgroundColor(Color.parseColor(item.code));
        color_name.setText(item.name);

        return v;
    }
}
