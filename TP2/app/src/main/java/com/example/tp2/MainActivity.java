package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TEXT_INIT = "Cliquez sur le bouton « Calculer l'IMC » pour obtenir un résultat.";

    Button envoyer = null;
    Button reset = null;
    EditText taille = null;
    EditText poids = null;
    CheckBox commentaire = null;
    RadioGroup group = null;
    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        envoyer = (Button)findViewById(R.id.calcul);
        reset = (Button)findViewById(R.id.reset);
        taille = (EditText)findViewById(R.id.taille);
        poids = (EditText)findViewById(R.id.poids);
        commentaire = (CheckBox)findViewById(R.id.commentaire);
        group = (RadioGroup)findViewById(R.id.group);
        result = (TextView)findViewById(R.id.result);

        envoyer.setOnClickListener(envoyerListener);
        reset.setOnClickListener(resetListener);
        commentaire.setOnClickListener(checkedListener);
        taille.addTextChangedListener(textWatcherTaille);
        poids.addTextChangedListener(textWatcherPoid);
    }

    private final View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //  on récupère la taille
            String t = taille.getText().toString();
            // On récupère le poids
            String p = poids.getText().toString();
            float tValue = Float.parseFloat(t);

            // Puis on vérifie que la taille est cohérente
            if(tValue <= 0)
                Toast.makeText(MainActivity.this, "La taille doit être positive", Toast.LENGTH_SHORT).show();
            else {
                float pValue = Float.parseFloat(p);
                if(pValue <= 0)
                    Toast.makeText(MainActivity.this, "Le poids doit etre positif", Toast.LENGTH_SHORT).show();
                else {
                    // Si l'utilisateur a indiqué que la taille était en centimètres
                    // On vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant
                    if (group.getCheckedRadioButtonId() == R.id.radio_centimetre) tValue = tValue / 100;
                    float imc = pValue / (tValue * tValue);
                    String resultat="Votre IMC est " + imc+" . ";
                    if(commentaire.isChecked()) resultat += interpreteIMC(imc);
                    result.setText(resultat);
                }
            }
        }
    };

    private final View.OnClickListener resetListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(TEXT_INIT);
        }
    };

    private final View.OnClickListener checkedListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()) {
                result.setText(TEXT_INIT);
            }
        }
    };

    private final TextWatcher textWatcherTaille = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(TEXT_INIT);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private final TextWatcher textWatcherPoid = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(TEXT_INIT);
            // Selection automatique de centimetre si il y a un point dans la taille
            for(int i=0;i<s.length();i++)
                if( s.charAt(i) == '.' )
                    group.check(R.id.radio_centimetre);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private static String interpreteIMC(float imc){
        if( imc < 16.5 ) return "Famine";
        else if( imc < 18.5 ) return "Maigreur";
        else if( imc < 25 ) return "Corpulence normale";
        else if( imc < 30 ) return "Surpoids";
        else if( imc < 35 ) return "Obésité modérée";
        else if( imc < 40 ) return "Obésité sévère";
        else return "Obsité morbide ou massive";
    }
}