package com.example.massign1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    EditText pAmount;
    Button calc;
    int pNum, sYear, sRate;
    double[] rVal = new double[]{3.14,4.5,3.19,3.49}; //array of the rate values featured in the rates string array
    int[] yrVal = new int[]{2,3,4,5}; //array of the years featured in the years string array
    TextView CalcDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setup for the Interest rates drop down menu (spinner)
        final Spinner spinner = (Spinner) findViewById(R.id.Interest_rates);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rates, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //Setup for the Amortization period drop down menu (spinner)
        final Spinner sp = (Spinner) findViewById(R.id.periods);
        ArrayAdapter<CharSequence> adpt = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adpt);

        pAmount = (EditText) findViewById(R.id.pAmount);
        calc = (Button) findViewById(R.id.calc);
        //Once the user clicks on the calculate button run the method below
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x,y,upper,lower,ans;
                pNum = Integer.parseInt(pAmount.getText().toString()); //Get the value entered by the user and change it into an integer
                sRate = spinner.getSelectedItemPosition(); //Get the position of the option selected by the user (This will be used to get the appropriate position in the rval array)
                sYear = sp.getSelectedItemPosition(); //Get the position of the option selected by the user (This will be used to get the appropriate position in the yrVal array)
                x = ((rVal[sRate]/100)/12); //Takes the rate and puts it in terms of months
                y = 12*yrVal[sYear]; //Gets the number of months based on the year chosen by the user
                upper = x*Math.pow(1+x,y);
                lower = Math.pow(1+x,y)-1;
                ans = pNum*(upper/lower);
                ans = (double) Math.round(ans*100)/100; //rounds to 2 decimal places
                CalcDisplay = (TextView) findViewById(R.id.calcDisplay);
                CalcDisplay.setText("$ "+String.valueOf(ans)+" /Month");
            }
        });
    }


}