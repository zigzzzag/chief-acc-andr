package com.example.chiefacc.chief_acc_andr.personListView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chiefacc.chief_acc_andr.R;
import com.example.chiefacc.chief_acc_andr.StringUtils;

import org.chiefacc.core.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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

        final Button removeItemButton = view.findViewById(R.id.removeItemButton);
        removeItemButton.setTag(position);
        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int) view.getTag();
                persons.remove(index);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public Set<Person> getConvertedPersonsItem() {
        final Set<Person> result = new HashSet<>();
        for (PersonItem personItem : persons) {
            result.add(new Person(personItem.getName(), personItem.getSum()));
        }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        final PersonItem personItem = (PersonItem) adapterView.getItemAtPosition(position);
        personDialog(personItem).show();
    }


    public AlertDialog personDialog() {
        return personDialog(null);
    }

    private AlertDialog personDialog(final PersonItem personItem) {

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Add new person")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        final LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);

        final EditText inputName = new EditText(context);
        inputName.setHint("Name");
        ll.addView(inputName);

        final EditText inputSum = new EditText(context);
        inputSum.setHint("Sum");
        inputSum.setText("0");
        ll.addView(inputSum);

        if (personItem != null) {
            dialog.setTitle("Edit person");
            inputName.setText(personItem.getName());
            inputSum.setText(String.valueOf(personItem.getSum()));
        }

        dialog.setView(ll);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String newPersonName = inputName.getText().toString();
                        final String newPersonSum = inputSum.getText().toString();

                        final String errorPersonName = errorPersonName(personItem, newPersonName);
                        if (errorPersonName != null) {
                            inputName.setError(errorPersonName);
                            return;
                        }

                        final String errorPersonSum = errorPersonSum(newPersonSum);
                        if (errorPersonSum != null) {
                            inputSum.setError(errorPersonSum);
                            return;
                        }

                        if (personItem == null) {
                            add(new PersonItem(newPersonName, Double.valueOf(newPersonSum)));
                        } else {
                            personItem.setName(newPersonName);
                            personItem.setSum(Double.valueOf(newPersonSum));
                        }
                        notifyDataSetChanged();
                        dialog.dismiss();

                        Toast.makeText(context, "Welcome, " + newPersonName + "!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return dialog;
    }

    //todo remake with enum
    private String errorPersonName(PersonItem editedPersonItem, String newPersonName) {
        if (StringUtils.isBlank(newPersonName)) {
            return "Should not be empty!";
        }

        if (editedPersonItem == null) {
            return null;
        }

        final List<PersonItem> personsWithoutCurrent = new ArrayList<>(persons);
        personsWithoutCurrent.remove(editedPersonItem);
        for (PersonItem personItem : personsWithoutCurrent) {
            if (newPersonName.equals(personItem.getName())) {
                return "With that name already exists!";
            }
        }

        return null;
    }

    //todo remake with enum
    private String errorPersonSum(String personSum) {
        try {
            if (Double.valueOf(personSum) < 0) {
                return "Must be a non negative number!";
            }
        } catch (NumberFormatException e) {
            return "Must be a non negative number!";
        }

        return null;
    }
}
