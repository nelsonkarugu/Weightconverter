package com.nelson.weightconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtview;
    int sel1;
    int sel2;
    float OUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txtview = findViewById(R.id.myTextView);


        Spinner mySpinner1 = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.metric, android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(adapter1);
        mySpinner1.setOnItemSelectedListener(this);


        Spinner mySpinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.metric, android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);
        mySpinner2.setOnItemSelectedListener(this);
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spinner:
                sel1 = i;
                return;

            case R.id.spinner2:
                sel2 = i;
                return;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private static final DecimalFormat df = new DecimalFormat("0.0");

    public String kgToLb(String input) {
//        convert the input to a float
        float conv;
        conv = Float.parseFloat(input);
        float result = (float) (conv * 2.2);
        return (df.format(result).toString());

    }

    public String LbToKg(String input) {
//        convert the input to a float
        float conv;
        conv = Float.parseFloat(input);
        float result = (float) (conv / 2.2);
        return (df.format(result).toString());

    }

    @SuppressLint("SetTextI18n")
    public void unitconversion(View view) {
        EditText value = findViewById(R.id.input);
        TextView result = findViewById(R.id.myTextView);
        result.setText("");

        try {
            if (value.getText().toString().equals("")) {
                displayToast("You should Enter a metric to convert!");
            } else {
                if (sel1 == 1 && sel2 == 2) {

                    String output = kgToLb(value.getText().toString());
//                    displayToast("Convert from Kg to Lb"+ output);
                    result.setText(output);
                } else if (sel1 == 2 && sel2 == 1) {

                    String output = LbToKg(value.getText().toString());

                    result.setText(output);

                } else
                    displayToast("Invalid Selection");

            }
        } catch (NullPointerException e) {
            // Do something
            displayToast("There was an error");
        }


    }
}