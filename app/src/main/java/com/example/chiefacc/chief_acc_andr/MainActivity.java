package com.example.chiefacc.chief_acc_andr;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chiefacc.chief_acc_andr.personListView.PersonItem;
import com.example.chiefacc.chief_acc_andr.personListView.PersonListAdapter;

import org.chiefacc.core.PersonPair;
import org.chiefacc.core.SumDistributor;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private PersonListAdapter personsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView personsListView = (ListView) findViewById(R.id.personsList);

        ArrayList<PersonItem> test = new ArrayList<>();//todo remove
        test.add(new PersonItem("Gogi", 200));
        test.add(new PersonItem("Shirz", 1000));
        test.add(new PersonItem("Serega", 850));
        test.add(new PersonItem("Sanya B", 750));
        test.add(new PersonItem("Sanya F", 500));
        test.add(new PersonItem("Max", 0));

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

        final Button calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Collection<PersonPair> calculatedResult = new SumDistributor().distribute(
                        personsAdapter.getConvertedPersonsItem());

                //todo move into calcResult
                final StringBuilder sb = new StringBuilder();
                for (PersonPair pp : calculatedResult) {
                    sb.append(pp).append(NEW_LINE);
                }


                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Calculated result")
                        .create();

                final TextView resultTextView = new TextView(dialog.getContext());
                resultTextView.setText(sb);
                dialog.setView(resultTextView);

                dialog.show();
            }
        });
    }
}
