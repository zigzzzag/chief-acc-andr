package com.example.chiefacc.chief_acc_andr;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.chiefacc.chief_acc_andr.personListView.PersonItem;
import com.example.chiefacc.chief_acc_andr.personListView.PersonListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PersonListAdapter personsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView personsListView = (ListView) findViewById(R.id.personsList);

        ArrayList<PersonItem> test = new ArrayList<>();//todo remove
        test.add(new PersonItem("p1", 99));
        test.add(new PersonItem("p2", 123));
        test.add(new PersonItem("p3", 123));
        test.add(new PersonItem("p4", 123));

        personsAdapter = new PersonListAdapter(this, R.layout.person_item, test);
        personsListView.setAdapter(personsAdapter);
        personsListView.setOnItemClickListener(personsAdapter);

        final Button addNewPersonButton = (Button) findViewById(R.id.addNewPerson);
        addNewPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personsAdapter.personDialog().show();
            }
        });
    }
}
