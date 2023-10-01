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
    /**
     * @author Roey Schwartz rs7532@bs.amalnet.k12.il
     * @version 1
     * @since 1.10.2023
     * this code will show the first 20 values of the series in accordance of the user choices
     */
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

    /**
     *
     * @param position is the position of the value in the list
     * @return the sum of the values from the start of the series to the selected item
     */

    public int sum(int position){
        int sum = 0;
        for (int i = 0; i < position; i++){
            sum += Integer.parseInt(series[i]);
        }
        return sum;
    }

    /**
     *
     * @param menu The context menu that is being built
     * @param v The view for which the context menu is being built
     * @param menuInfo Extra information about the item for which the
     *            context menu should be shown. This information will vary
     *            depending on the class of v.
     * <p>
     *      the function creates the context menu in the code
     * </p>
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose an Option");
        menu.add("Location");
        menu.add("Sum");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     *
     * @param item The context menu item that was selected.
     * <p>
     *      the function is shows the location of the selected item in the list,
     *      or the sum from the start of the list to the selected item.
     * </p>
     */
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