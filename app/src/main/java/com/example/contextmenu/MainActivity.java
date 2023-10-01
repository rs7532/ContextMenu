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

    /**
     * @author Roey Schwartz rs7532@bs.amalnet.k12.il
     * @version 1
     * @since 1.10.2023
     * this code will inputs values to create a list of an engineer or invoice series
     */
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

    /**
     * <p>
     *     the function doesn't get a variable
     * </>
     * @return the function will return a list of strings that have the 20 first values of the engineer or invoice series
     */
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

    /**
     * <p>
     *     the function get a variable of the type View
     *     the function will start the Show_series activity and transfers the series, the first value of the series and the difference or multiplier of the series
     * </>
     */

    public void pressed(View view) {
        si = new Intent(MainActivity.this,Show_series.class);

        si.putExtra("series", create_series());
        si.putExtra("organ", Integer.parseInt(first_organ.getText().toString()));
        si.putExtra("difference_Multiplier", Integer.parseInt(differenceMultiplier.getText().toString()));
        startActivity(si);
    }

}