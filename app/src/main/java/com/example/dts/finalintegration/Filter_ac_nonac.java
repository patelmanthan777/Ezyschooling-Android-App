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

public class Filter_ac_nonac extends AppCompatActivity {
    SharedPreferences login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_ac_nonac);
        final SharedPreferences myPrefs = this.getSharedPreferences("Ac",MODE_PRIVATE);
        CheckBox cyes = (CheckBox) findViewById(R.id.checkBox_yes1);
        CheckBox cno = (CheckBox) findViewById(R.id.checkBox_no_ac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        Boolean yesstate = myPrefs.getBoolean("ac_yesvalue",false);
        cyes.setChecked(yesstate);
        Boolean nostate = myPrefs.getBoolean("ac_novalue",false);
        cno.setChecked(nostate);

        cyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    //Toast.makeText(getBaseContext(),"Yes Clicked",Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putInt("go_ac_yes_true",1);
                    prefsEditor.putInt("go_ac_yes_false",0);
                    prefsEditor.putBoolean("ac_yesvalue",true);
                    prefsEditor.commit();



                }
                else {

                   // Toast.makeText(getBaseContext(),"Yes Unclicked",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putBoolean("ac_yesvalue",false);
                    prefsEditor.putInt("go_ac_yes_true",0);
                    prefsEditor.putInt("go_ac_yes_false",1);
                    prefsEditor.commit();
                }
            }
        });



        cno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                   // Toast.makeText(getBaseContext(),"no clicked",Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putInt("go_ac_no_true",1);
                    prefsEditor.putInt("go_ac_no_false",0);
                    prefsEditor.putBoolean("ac_novalue",true);
                    prefsEditor.commit();

                }
                else {

                  //  Toast.makeText(getBaseContext(),"no unclicked",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putBoolean("ac_novalue",false);
                    prefsEditor.putInt("go_ac_no_true",0);
                    prefsEditor.putInt("go_ac_no_false",1);
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