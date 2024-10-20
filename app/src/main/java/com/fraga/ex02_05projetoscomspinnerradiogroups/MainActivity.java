/*
 *@author: Bruno Fraga
 */

package com.fraga.ex02_05projetoscomspinnerradiogroups;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Bruno Fraga - RA: 1110482112016

    private EditText inputBits;
    private Spinner spinnerUnits;
    private Button buttonConvert;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBits = findViewById(R.id.inputBits);
        spinnerUnits = findViewById(R.id.spinnerUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertBits();
            }
        });
    }

    private void convertBits() {
        String input = inputBits.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um valor em bits", Toast.LENGTH_SHORT).show();
            return;
        }

        long bits = Long.parseLong(input);
        String selectedUnit = spinnerUnits.getSelectedItem().toString();
        double result = 0;

        switch (selectedUnit) {
            case "Bytes":
                result = bits / 8.0;
                break;
            case "KB":
                result = bits / (8.0 * 1024);
                break;
            case "MB":
                result = bits / (8.0 * 1024 * 1024);
                break;
            case "GB":
                result = bits / (8.0 * 1024 * 1024 * 1024);
                break;
            case "TB":
                result = bits / (8.0 * 1024 * 1024 * 1024 * 1024);
                break;
        }

        resultText.setText(String.format("Resultado: %.2f %s", result, selectedUnit));
    }
}
