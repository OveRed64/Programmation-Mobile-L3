package com.example.tp6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class NewPlanetDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.new_planet_form, null,false);

        builder.setView(view);

        EditText et_name = (EditText) view.findViewById(R.id.planet_name);
        EditText et_size = (EditText) view.findViewById(R.id.planet_size);

        et_name.setText("");
        et_size.setText("");

        Button add = (Button) view.findViewById(R.id.btn_add);
        // set onclicklistener
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String size = et_size.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.format("Ajout de la planete '%s' de la taille de '%s' km",name,size));
                        Planet planet = new Planet(name,Integer.parseInt(size));
                        MainActivity.getDb().planetDao().insert(planet);
                        MainActivity.getMAdapter().addPlanet(planet);
                    }
                }).start();

                dismiss();
            }
        });

        return builder.create();
    }

}
