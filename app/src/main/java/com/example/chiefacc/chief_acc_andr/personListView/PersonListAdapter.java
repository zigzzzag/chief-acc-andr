package com.example.chiefacc.chief_acc_andr.personListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chiefacc.chief_acc_andr.R;

import java.util.ArrayList;

public class PersonListAdapter extends BaseAdapter {

    private final Context context;
    private LayoutInflater lInflater;
    private ArrayList<PersonItem> persons;

    public PersonListAdapter(Context context) {
        this(context, new ArrayList<PersonItem>());
    }

    public PersonListAdapter(Context context, ArrayList<PersonItem> persons) {
        this.context = context;
        this.persons = persons;
        this.lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public PersonItem getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = convertView != null ? convertView
                : lInflater.inflate(R.layout.person_item, parent, false);

        final PersonItem personItem = getItem(position);

        ((TextView) view.findViewById(R.id.personName)).setText(personItem.getName());
        ((TextView) view.findViewById(R.id.personSum)).setText(Double.toString(personItem.getSum()));

        return view;
    }
}
