package com.bb.hotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.hotel.R;
import com.bb.hotel.model.Name;

import java.util.List;

public class NameAdaptor extends BaseAdapter {

    public NameAdaptor(List<Name> name){this.name = name;}

    private List<Name> name;

    @Override
    public int getCount(){return name.size();}

    @Override
    public Name getItem(int position){return name.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guest_item_layout, parent, false);

        ((TextView) view.findViewById(R.id.name_textview)).setText(
                name.get(position).getActualName());

        return view;
    }

}
