package com.example.dts.finalintegration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Filter_CATEgory_new extends AppCompatActivity {
    SharedPreferences login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__category_new);
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        final SharedPreferences myPrefs = this.getSharedPreferences("Category",MODE_PRIVATE);
        CheckBox c1 = (CheckBox) findViewById(R.id.checkBox_yes1);
        CheckBox c2= (CheckBox) findViewById(R.id.checkBox_no2);
        CheckBox c3= (CheckBox) findViewById(R.id.checkBox_3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Boolean state1 = myPrefs.getBoolean("Category_value1",false);
        c1.setChecked(state1);
        Boolean state2 = myPrefs.getBoolean("Category_value2",false);
        c2.setChecked(state2);
        Boolean state3 = myPrefs.getBoolean("Category_value3",false);
        c3.setChecked(state3);


        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                  //  Toast.makeText(getBaseContext(),"Boys only clicked",Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putInt("go_Category_1_true",1);

                    prefsEditor.putBoolean("Category_value1",true);
                    prefsEditor.commit();



                }
                else {

                 //   Toast.makeText(getBaseContext(),"Boys only unclicked",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putBoolean("Category_value1",false);
                    prefsEditor.putInt("go_Category_1_true",0);

                    prefsEditor.commit();
                }
            }
        });



        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                  //  Toast.makeText(getBaseContext(),"Girls only clicked",Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putInt("go_Category_2_true",1);

                    prefsEditor.putBoolean("Category_value2",true);
                    prefsEditor.commit();

                }
                else {

                  //  Toast.makeText(getBaseContext(),"Girls only unclicked",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putBoolean("Category_value2",false);
                    prefsEditor.putInt("go_Category_2_true",0);

                    prefsEditor.commit();
                }
            }
        });


        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                  //  Toast.makeText(getBaseContext(),"Coed clicked",Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putInt("go_Category_3_true",1);

                    prefsEditor.putBoolean("Category_value3",true);
                    prefsEditor.commit();

                }
                else {

                   // Toast.makeText(getBaseContext(),"Coed unclicked",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putBoolean("Category_value3",false);
                    prefsEditor.putInt("go_Category_3_true",0);

                    prefsEditor.commit();
                }
            }
        });

    }
    public void showselected(View view){
        SharedPreferences abcd1,abcd2;



        abcd1 = this.getSharedPreferences("Doubt",MODE_PRIVATE); abcd2 = this.getSharedPreferences("Doubt2",MODE_PRIVATE);
        String a = abcd1.getString("sb1_f","nil"); String b = abcd2.getString("sb2_f","nil");
        String c = "go1",d="123";
        if (c.equals(a)){
            Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse1.class);

            SharedPreferences.Editor prefsEditor = abcd1.edit();
            prefsEditor.putString("sb1_f","0");
            prefsEditor.commit();
            startActivity(viewShoppingCartIntent);}
        else if (d.equals(b)){
            Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse2.class);

            SharedPreferences.Editor prefsEditor = abcd2.edit();
            prefsEditor.putString("sb2_f","0");
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

                else{
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

        else{
            getMenuInflater().inflate(R.menu.settings_for_caf2, menu);
        }
        return true;
    }

}
