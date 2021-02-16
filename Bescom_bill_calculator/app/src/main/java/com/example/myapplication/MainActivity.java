package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinr;
    private String spinr_value;
    private TextView units;
    private Button calculate;
    private TextView dummy;
    private Switch limits;
    Boolean switch_limits= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinr       =   (Spinner)   findViewById(R.id.spin);
        dummy       =   (TextView)  findViewById(R.id.etText_dummy);
        units       =   (TextView)  findViewById(R.id.etUnits);
        calculate   =   (Button)    findViewById(R.id.button);
        limits      =   (Switch)    findViewById(R.id.switch_limits);

        /*                      spinner                      */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinr.setAdapter(adapter);
        spinr.setOnItemSelectedListener(this);

        calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(units.getText().toString(),spinr_value);
            }
        });

        limits.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_limits = limits.isChecked();
                Log.d("MainActivity","switch "+limits.isChecked());
            }
        });
    }

    private void validate(String units, String kilowatts) {
        dummy.setText(units + kilowatts);
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("UNITS", units);
        intent.putExtra("KILOWATTS", kilowatts);
        intent.putExtra("LIMITS", switch_limits);
        if(Integer.parseInt(units) != 0) {
            startActivity(intent);
        } else {
            dummy.setText("Give units more than 0");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT).show();
        spinr_value = Integer.toString(i + 1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}