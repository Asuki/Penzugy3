package com.example.seng.penzugy3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FinanceAdapter extends ArrayAdapter<FinanceElements> {

    private final String Tag = "FinanceAdapter";
    private Context context;
    private int resource;
    private ArrayList<FinanceElements> objects;



    public FinanceAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FinanceElements> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String category = getItem(position).getCategory();
        String spending = getItem(position).getSpending();
        String spDate = getItem(position).getSpDate();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView textViewName = convertView.findViewById(R.id.textVAdapName);
        TextView textViewCategory = convertView.findViewById(R.id.textVAdapCategory);
        TextView textViewSpending = convertView.findViewById(R.id.textVAdapSpending);
        TextView textViewSpDate = convertView.findViewById(R.id.textViewAdapDate);

        textViewCategory.setText(category);
        textViewName.setText(name);
        textViewSpDate.setText(spDate);
        textViewSpending.setText(spending);

        return convertView;
    }
}
