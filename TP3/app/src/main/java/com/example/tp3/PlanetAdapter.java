package com.example.tp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Struct;
import java.util.ArrayList;

public class PlanetAdapter extends BaseAdapter {

    private final Data data;
    private final MainActivity mainActivity;
    private final ArrayList<Row> rows;

    public PlanetAdapter( MainActivity mainActivity ){
        this.data = new Data();
        this.mainActivity = mainActivity;
        this.rows = new ArrayList<Row>();
    }

    @Override
    public int getCount() {
        return data.getSize();
    }

    @Override
    public Object getItem(int arg0) { return data.get(arg0); }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, parent,false);
        }

        TextView tv_planetName = (TextView) itemView.findViewById(R.id.textView);
        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

        tv_planetName.setText(data.get(index).name);

        final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_spinner_item, data.PLANETS_SIZE);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = (CheckBox) compoundButton.findViewById(R.id.checkbox);
                spinner.setEnabled(!checkBox.isChecked());
                spinadapter.notifyDataSetChanged();
                mainActivity.isAllChecked();
            }
        });

        this.rows.add( new Row(checkBox,spinner));

        return itemView;
    }

    public Row getRow(int i){
        if( i >= rows.size() )
            return null;
        else
            return rows.get(i);
    }

    static class Row {
        public CheckBox checkBox;
        public Spinner spinner;
        public Row(CheckBox checkBox, Spinner spinner) {
            this.checkBox = checkBox;
            this.spinner = spinner;
        }
    }
}