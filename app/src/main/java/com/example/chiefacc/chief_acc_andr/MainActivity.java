package com.example.chiefacc.chief_acc_andr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chiefacc.chief_acc_andr.personListView.PersonItem;
import com.example.chiefacc.chief_acc_andr.personListView.PersonListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<PersonItem> persons = new ArrayList<>();
    private PersonListAdapter personsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView personsListView = (ListView) findViewById(R.id.personsList);

        ArrayList<PersonItem> test = new ArrayList<>();//todo remove
        test.add(new PersonItem("p1", 123));
        test.add(new PersonItem("p2", 123));
        test.add(new PersonItem("p3", 123));
        test.add(new PersonItem("p4", 123));
        personsAdapter = new PersonListAdapter(this,test);
        personsListView.setAdapter(personsAdapter);

        final Button addNewPersonButton = (Button) findViewById(R.id.addNewPerson);
        addNewPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Add new person");

                final LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);

                final EditText inputName = new EditText(MainActivity.this);
                inputName.setHint("Name");
                ll.addView(inputName);

                final EditText inputSum = new EditText(MainActivity.this);
                inputSum.setHint("Sum");
                ll.addView(inputSum);

                alert.setView(ll);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String newPersonName = inputName.getText().toString();
                        final String newPersonSum = inputSum.getText().toString();

                        persons.add(new PersonItem(newPersonName, Double.valueOf(newPersonSum)));
                        personsAdapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Welcome, " + newPersonName + "!",
                                Toast.LENGTH_LONG).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alert.show();
            }
        });


        personsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String txt = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, txt, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
