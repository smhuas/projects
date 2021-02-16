package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView unitsConsumed;
    private TextView kilowatts;
    private TextView taxonunits;
    private TextView taxonamt;
    private TextView fixedonkw;
    private TextView totalbill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String units = getIntent().getStringExtra("UNITS");
        String kw = getIntent().getStringExtra("KILOWATTS");
        Boolean limits = getIntent().getBooleanExtra("LIMITS",false);

        //Log.d("SecondActivity", "Limits is : &&&&&&&&&& " + limits);

        unitsConsumed = (TextView) findViewById(R.id.txtUnits);
        kilowatts = (TextView) findViewById(R.id.txtKW);
        taxonunits = (TextView) findViewById(R.id.txtTaxOnTotalUnits);
        taxonamt = (TextView) findViewById(R.id.txtTaxOnAmount);
        fixedonkw = (TextView) findViewById(R.id.txtFixedChargesOnKW);
        totalbill = (TextView) findViewById(R.id.txtTotalBill);


        unitsConsumed.setText(units);
        kilowatts.setText(kw);

        int u = Integer.parseInt(units);
        int k = Integer.parseInt(kw);
        double amt = 0;
        double UNITS_BELOW_30, UNITS_BELOW_100, UNITS_BELOW_200, UNIT_ABOVE_200, FIRST_KW, ABOVE_FIRST_KW  = 0;

        if(limits == false ) {  // Panchayat
            UNITS_BELOW_30  = 3.90; UNITS_BELOW_100 = 5.15; UNITS_BELOW_200 = 6.70; UNIT_ABOVE_200  = 7.55;
            FIRST_KW        = 55.0; ABOVE_FIRST_KW  = 70.0;
        } else {                // BBMP
            UNITS_BELOW_30  = 3.90; UNITS_BELOW_100 = 5.15; UNITS_BELOW_200 = 6.70; UNIT_ABOVE_200  = 7.55;
            FIRST_KW        = 70.0; ABOVE_FIRST_KW  = 80.0;
        }

        if ((u > 0) && (u <= 30)) {
            amt += (u * UNITS_BELOW_30);
        } else if ((u > 30) && (u <= 100)) {
            amt += (30 * UNITS_BELOW_30) + ((u - 30) * UNITS_BELOW_100);
        } else if ((u > 100) && (u <= 200)) {
            amt += (30 * UNITS_BELOW_30) + (70 * UNITS_BELOW_100) + ((u - 100) * UNITS_BELOW_200);
        } else {
            amt += (30 * UNITS_BELOW_30) + (70 * UNITS_BELOW_100) + (100 * UNITS_BELOW_200) + ((u - 200) * UNIT_ABOVE_200);
        }

        taxonunits.setText(String.format("%.2f",(u * 0.08)));       // 8% tax on total units
        taxonamt.setText(String.format("%.2f",(amt * 0.09)));       // 9% tax on total amt

        double f = ((Integer.parseInt(kw) - 1) * ABOVE_FIRST_KW) + FIRST_KW;
        fixedonkw.setText(String.format("%.2f",f));

        double tb = f + (u * 0.08) + (amt * 0.09) + amt;
        tb = Math.round(tb);
        totalbill.setText(Double.toString(tb));
    }
}