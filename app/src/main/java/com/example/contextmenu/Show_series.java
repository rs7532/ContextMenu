package com.example.contextmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Show_series extends AppCompatActivity {
    int loc = 1;
    Intent gi;
    TextView tv;
    ListView lv;
    String[] series;
    int first_organ, differenceMultiplier;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_series);

        lv = findViewById(R.id.List_View);
        tv = findViewById(R.id.tv);

        registerForContextMenu(lv);

        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        tv.setVisibility(View.INVISIBLE);


        gi = getIntent();
        series = gi.getStringArrayExtra("series");
        first_organ = gi.getIntExtra("organ",-1);
        differenceMultiplier = gi.getIntExtra("difference_Multiplier", -1);


        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, series);
        lv.setAdapter(adp);


    }

    public int sum(int position){
        int sum = 0;
        for (int i = 0; i < position; i++){
            sum += Integer.parseInt(series[i]);
        }
        return sum;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose an Option");
        menu.add("Location");
        menu.add("Sum");
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper = String.valueOf(item.getTitle());
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (oper.equals("Location")){
            tv.setText("Location = "+(info.position + 1));
            tv.setVisibility(View.VISIBLE);
        }
        else{
            tv.setText("Sum = "+sum(info.position));
            tv.setVisibility(View.VISIBLE);
        }
        return super.onContextItemSelected(item);
    }

}