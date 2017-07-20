package com.example.chiefacc.chief_acc_andr.personListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chiefacc.chief_acc_andr.R;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends ArrayAdapter<PersonItem> implements AdapterView.OnItemClickListener {

    private final Context context;
    private final int layoutResourceId;
    private LayoutInflater lInflater;
    private List<PersonItem> persons;

    public PersonListAdapter(Context context, int layoutResourceId) {
        this(context, layoutResourceId, new ArrayList<PersonItem>());
    }

    public PersonListAdapter(Context context, int layoutResourceId, List<PersonItem> persons) {
        super(context, layoutResourceId, persons);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        final PersonItem pi = (PersonItem) adapterView.getItemAtPosition(position);
        final String msg = String.format("Hello, %s%s", pi.getName(),
                pi.getSum() < 100 ? " Нищеброд" : "");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
