package com.example.dts.finalintegration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;

public class Filter_class extends AppCompatActivity {
    SharedPreferences login, myPrefs,myPrefs1,myPrefs2,myPrefs3,
            myPrefs4,myPrefs5,myPrefs6,myPrefs7,myPrefs8,myPrefs9,myPrefs10;  int go1=0,go2=0,go3=0,button_sort=0;
    int ac_yes_true=0,ac_no_true=0,accomod_yes_true=0,accomod_no_true=0,catog_boys=0,catog_girls=0,catog_coed=0;
    int fees1=0,fees2=0,fees3=0,fees1l=0,fees2l=0,fees3l=0,fees1r=0,fees2r=0,fees3r=0,fees1s=0,fees2s=0,fees3s=0,
            fees1ss=0,fees2ss=0,fees3ss=0, fees1q=0,fees2q=0,fees3q=0,ac_yes_truet=0,ac_no_truet=0,type1=0,type2=0,type3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        myPrefs = this.getSharedPreferences("Accomod",MODE_PRIVATE);
        myPrefs1 = this.getSharedPreferences("Ac",MODE_PRIVATE);
        myPrefs2 = this.getSharedPreferences("Category",MODE_PRIVATE);
        myPrefs3 = this.getSharedPreferences("Fees",MODE_PRIVATE);
        myPrefs4 = this.getSharedPreferences("Languages",MODE_PRIVATE);
        myPrefs5 = this.getSharedPreferences("Region",MODE_PRIVATE);
        myPrefs6 = this.getSharedPreferences("Sports",MODE_PRIVATE);
        myPrefs7 = this.getSharedPreferences("Students",MODE_PRIVATE);
        myPrefs8 = this.getSharedPreferences("Subjects",MODE_PRIVATE);
        myPrefs9 = this.getSharedPreferences("Transport",MODE_PRIVATE);
        myPrefs10 = this.getSharedPreferences("Type",MODE_PRIVATE);



       TextView view_ac =(TextView) findViewById(R.id.textView_ac_filter) ;
        TextView view_accomod =(TextView) findViewById(R.id.textView_accomoadtion_filer) ;
        TextView view_category =(TextView) findViewById(R.id.textView_category_filer) ;
        TextView view_fees =(TextView) findViewById(R.id.textView_fees_filter) ;
        TextView view_lang =(TextView) findViewById(R.id.textView_languages_filter) ;
        TextView view_region =(TextView) findViewById(R.id.textView_region_filter) ;
        TextView view_sports =(TextView) findViewById(R.id.textView_sports_filter) ;
        TextView view_students =(TextView) findViewById(R.id.textView_studen_filter) ;
        TextView view_subjects =(TextView) findViewById(R.id.textView_subjects_filter) ;
        TextView view_transport =(TextView) findViewById(R.id.textView_transport_filter) ;
        TextView view_type =(TextView) findViewById(R.id.textView_type_filter) ;

        ac_no_true = myPrefs1.getInt("go_ac_no_true",0); ac_yes_true =myPrefs1.getInt("go_ac_yes_true",0);
        accomod_no_true = myPrefs.getInt("go_accomod_no_true",0);accomod_yes_true =myPrefs.getInt("go_accomod_yes_true",0);
        catog_boys = myPrefs2.getInt("go_Category_1_true",0); catog_girls =myPrefs2.getInt("go_Category_2_true",0);
        catog_coed =myPrefs2.getInt("go_Category_3_true",0);fees1 = myPrefs3.getInt("go_Fees_1_true",0);
        fees2 =myPrefs3.getInt("go_Fees_2_true",0); fees3 =myPrefs3.getInt("go_Fees_3_true",0);
        fees1l = myPrefs4.getInt("go_Fees_1_truel",0);
        fees2l =myPrefs4.getInt("go_Fees_2_truel",0);fees3l =myPrefs4.getInt("go_Fees_3_truel",0);
        fees1r = myPrefs5.getInt("go_Fees_1_truer",0);
        fees2r =myPrefs5.getInt("go_Fees_2_truer",0);fees3r =myPrefs5.getInt("go_Fees_3_truer",0);
        fees1s = myPrefs6.getInt("go_Fees_1_trues",0);
        fees2s =myPrefs6.getInt("go_Fees_2_trues",0);fees3s =myPrefs6.getInt("go_Fees_3_trues",0);
        fees1ss = myPrefs7.getInt("go_Fees_1_truess",0);fees2ss =myPrefs7.getInt("go_Fees_2_truess",0);
        fees3ss =myPrefs7.getInt("go_Fees_3_truess",0);
        fees1q = myPrefs8.getInt("go_Fees_1_trueq",0);
        fees2q =myPrefs8.getInt("go_Fees_2_trueq",0);fees3q =myPrefs8.getInt("go_Fees_3_trueq",0);
        ac_no_truet = myPrefs9.getInt("go_accomod_no_truet",0);ac_yes_truet =myPrefs9.getInt("go_accomod_yes_truet",0);
        type1 = myPrefs10.getInt("go_Fees_1_truep",0);type2 =myPrefs10.getInt("go_Fees_2_truep",0);
        type3 = myPrefs10.getInt("go_Fees_3_truep",0);


        if (type1==1 ||type2==1 ||type3 ==1 )
        {
            view_type.setText("Type (Applied)");
            view_type.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_type.setTextColor(Color.WHITE);
        }


        if (ac_no_truet==1 ||ac_yes_truet==1 )
        {
            view_transport.setText("Transport (Applied)");
            view_transport.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_transport.setTextColor(Color.WHITE);
        }


        if (fees1q==1 ||fees2q==1 ||fees3q ==1 )
        {
            view_subjects.setText("Subjects (Applied)");
            view_subjects.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_subjects.setTextColor(Color.WHITE);
        }


        if (fees1ss==1 ||fees2ss==1 ||fees3ss ==1 )
        {
            view_students.setText("Students (Applied)");
            view_students.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_students.setTextColor(Color.WHITE);
        }


        if (fees1s==1 ||fees2s==1 ||fees3s ==1 )
        {
            view_sports.setText("Sports (Applied)");
            view_sports.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_sports.setTextColor(Color.WHITE);
        }


        if (fees1r==1 ||fees2r==1 ||fees3r ==1 )
        {
            view_region.setText("Region (Applied)");
            view_region.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_region.setTextColor(Color.WHITE);
        }


        if (fees1l==1 ||fees2l==1 ||fees3l ==1 )
        {
            view_lang.setText("Languages (Applied)");
            view_lang.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_lang.setTextColor(Color.WHITE);
        }

        if (fees1==1 ||fees2==1 ||fees3 ==1 )
        {
            view_fees.setText("Fee Structure (Applied)");
            view_fees.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_fees.setTextColor(Color.WHITE);
        }





        if (catog_boys==1 ||catog_girls==1 ||catog_coed ==1 )
        {
            view_category.setText("Category (Applied)");
            view_category.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_category.setTextColor(Color.WHITE);
        }



        if (ac_no_true==1 ||ac_yes_true==1)
        {
            view_ac.setText("Ac/Non-Ac (Applied)");
            view_ac.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_ac.setTextColor(Color.WHITE);
        }

        if (accomod_no_true==1 ||accomod_yes_true==1)
        {
            view_accomod.setText("Accomodation (Applied)");
            view_accomod.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            view_accomod.setTextColor(Color.WHITE);
        }
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
    }



    public void openFilterRegion(View view){
        Intent openFilterRegion = new Intent(getBaseContext(), Filter_Region.class);
        startActivity(openFilterRegion);
    }
    public void openFilterFees(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_fees_structure.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterAc(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_ac_nonac.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterSports(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_sports.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterCategory(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_CATEgory_new.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterType(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_type_.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterTransport(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_transport_facility.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterStudent(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_StudentsPerClass.class);
        startActivity(viewShoppingCartIntent);
    } public void openFilterSubjects(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_subjects_in11th.class);
        startActivity(viewShoppingCartIntent);
    } public void openFilterLanguages(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_foreign_languages.class);
        startActivity(viewShoppingCartIntent);
    }
    public void openFilterAccomodation(View view){
        Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_accomodation.class);
        startActivity(viewShoppingCartIntent);
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

