package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView);
        btnVerify = (Button) findViewById(R.id.btnVerify);

        PlanetAdapter adapter = new PlanetAdapter(MainActivity.this);
        listview.setAdapter(adapter);

        btnVerify.setEnabled(false);
        btnVerify.setOnClickListener(onVerify);
    }

    View.OnClickListener onVerify = new View.OnClickListener() {
        public void onClick(View v) {
            PlanetAdapter adapter = (PlanetAdapter)listview.getAdapter();
            int errorAmount = 0;
            for (int i = 0; i < adapter.getCount(); i++) {
                PlanetAdapter.Row row = adapter.getRow(i);
                if (row != null) {
                    String size = row.spinner.getSelectedItem().toString();
                    if( !size.equals(Data.PLANETS_SIZE[i]) )
                        errorAmount++;
                }
            }
            popUp("Vous avez " + errorAmount + " erreur(s)");
        }
    };

    public void isAllChecked(){
        boolean allTrue = true;
        PlanetAdapter adapter = (PlanetAdapter)listview.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            PlanetAdapter.Row row = adapter.getRow(i);
            if (row != null && !row.checkBox.isChecked()) {
                allTrue = false;
                break;
            }
        }
        btnVerify.setEnabled(allTrue);
    }

    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}