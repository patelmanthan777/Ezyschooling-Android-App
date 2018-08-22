package com.example.dts.finalintegration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sortby extends AppCompatActivity {
    ListView listView_sort; SharedPreferences login;
    String[] sort = {"Price : High To Low","Price : Low To High","Popularity"};
    ArrayList<String> selected_items = new ArrayList<>();
    int go1=0,go2=0,go3=0;SharedPreferences abcd1,abcd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortby);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        listView_sort = (ListView)findViewById(R.id.listView_sort_values);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),R.layout.listview_checkbox_region,R.id.checkedTextView_region,sort);
        listView_sort.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_sort.setAdapter(arrayAdapter);

        listView_sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem = ((TextView)view).getText().toString();
                if(selected_items.contains(selecteditem)){
                    selected_items.remove(selecteditem);
                    switch(selecteditem){
                        case  "Price : High To Low":
                            go1=0;
                            break;

                        case  "Price : Low To High":
                            go2=0;
                            break;
                        case  "Popularity":
                            go3=0;
                            break;

                    }
                }
                else
                { selected_items.add(selecteditem);
                    switch(selecteditem){
                        case  "Price : High To Low":
                            go1=1;
                            break;

                        case  "Price : Low To High":
                            go2 = 2;
                            break;
                        case  "Popularity":
                            go3=3;
                            break;


                    }}
            }
        });
    }
    public void showselected(View view){
        abcd1 = this.getSharedPreferences("Doubt",MODE_PRIVATE); abcd2 = this.getSharedPreferences("Doubt2",MODE_PRIVATE);
        String a = abcd1.getString("sb1_s","nil"); String b = abcd2.getString("sb2_s","nil");
        String c = "go2",d="345";
        if (c.equals(a)){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse1.class);
            if (go1 ==1){ viewShoppingCartIntent.putExtra("go1",1);}
            if (go2 ==2){ viewShoppingCartIntent.putExtra("go2",2);}
            if (go3 ==3){ viewShoppingCartIntent.putExtra("go3",3);}
            SharedPreferences.Editor prefsEditor = abcd1.edit();
            prefsEditor.putString("sb1_s","0");
            prefsEditor.commit();
            startActivity(viewShoppingCartIntent);}
        else if (d.equals(b)){
            Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse2.class);
            if (go1 ==1){ viewShoppingCartIntent.putExtra("go1",1);}
            if (go2 ==2){ viewShoppingCartIntent.putExtra("go2",2);}
            if (go3 ==3){ viewShoppingCartIntent.putExtra("go3",3);}
            SharedPreferences.Editor prefsEditor = abcd2.edit();
            prefsEditor.putString("sb2_s","0");
            prefsEditor.commit();
            startActivity(viewShoppingCartIntent);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent);
                this.finish();
                return true;

            case R.id.action_settings:
            {
                Intent intent = new Intent(getBaseContext(), Settings.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_logout:
            {
                SharedPreferences.Editor prefsEditor = login.edit();
                prefsEditor.putString("LoginStatus","false");

                prefsEditor.putString("Token","nil");
                prefsEditor.commit();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_homepage:
            {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_homeout:
            {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_myschools:
            {
                String status = login.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), ShoppingCartActivity.class);
                    startActivity(viewShoppingCartIntent11);
                }

                if (status.equals("false")){
                    Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                    Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(viewShoppingCartIntent1);
                }
                return true;
            }
            case R.id.action_browse:
            {

                Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent11);
                return true;
            }
            case R.id.action_login:
            {

                Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(viewShoppingCartIntent11);
                return true;
            }
            case R.id.action_browseout:
            {


                Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent11);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        String status = login.getString("LoginStatus","null");
        if (status.equals("true")){

            getMenuInflater().inflate(R.menu.settings_for_caf, menu);
        }

        if (status.equals("false")){
            getMenuInflater().inflate(R.menu.settings_for_caf2, menu);
        }
        return true;
    }

}
