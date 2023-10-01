package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText first_organ, differenceMultiplier;
    RadioButton rb_invoice, rb_engineer;
    Button btn;

    Intent si;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_organ = findViewById(R.id.First_organ);
        differenceMultiplier = findViewById(R.id.difference_multiplier);
        rb_engineer = findViewById(R.id.engineer_rb);
        rb_invoice = findViewById(R.id.Invoice_rb);
        btn = findViewById(R.id.Next_btn);

    }

    public String[] create_series(){
        String[] arr_series = new String[20];

        for (int i = 0; i < 20; i++){
            if (rb_engineer.isChecked()){
                if (i == 0){
                    arr_series[i] = (first_organ.getText().toString());
                }
                else{
                    arr_series[i] = String.valueOf(Integer.parseInt(arr_series[i - 1]) * Integer.parseInt(differenceMultiplier.getText().toString()));
                }
            }
            else{
                if (i == 0){
                    arr_series[i] = (first_organ.getText().toString());
                }
                else{
                    arr_series[i] = String.valueOf(Integer.parseInt(arr_series[i - 1]) + Integer.parseInt(differenceMultiplier.getText().toString()));
                }
            }
        }
        return arr_series;
    }

    public void pressed(View view) {
        si = new Intent(MainActivity.this,Show_series.class);

        si.putExtra("series", create_series());
        si.putExtra("organ", Integer.parseInt(first_organ.getText().toString()));
        si.putExtra("difference_Multiplier", Integer.parseInt(differenceMultiplier.getText().toString()));
        startActivity(si);
    }

}