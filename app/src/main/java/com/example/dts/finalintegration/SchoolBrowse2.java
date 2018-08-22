package com.example.dts.finalintegration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SchoolBrowse2 extends AppCompatActivity {
    SharedPreferences login;

    ListViewAdapter1 adapter,adp1;
    ListView listview;
    int filtercalled = 0;
    SharedPreferences myPrefs,myPrefs1,myPrefs2,myPrefs3,myPrefs4,myPrefs5,myPrefs6,myPrefs7,myPrefs8,myPrefs9,myPrefs10,doubt2;
    // URL to get contacts JSON
    private static String url = "http://ezyschooling.com/schools";private ProgressDialog pDialog;
    ArrayList<SchoolProfile> add_to_listview = new ArrayList<SchoolProfile>();
    ArrayList<SchoolProfile> arrayList_counter = new ArrayList<SchoolProfile>();
    ArrayList<Class_filter_counter> arrayList_values_counter= new ArrayList<Class_filter_counter>();

    int go1=0,go2=0,go3=0,button_sort=0;
    int ac_yes_true=0,ac_no_true=0,accomod_yes_true=0,accomod_no_true=0,catog_boys=0,catog_girls=0,catog_coed=0;
    int fees1=0,fees2=0,fees3=0,fees1l=0,fees2l=0,fees3l=0,fees1r=0,fees2r=0,fees3r=0,fees1s=0,fees2s=0,fees3s=0,
            fees1ss=0,fees2ss=0,fees3ss=0, fees1q=0,fees2q=0,fees3q=0,ac_yes_truet=0,ac_no_truet=0,type1=0,type2=0,type3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_browse2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myPrefs = this.getSharedPreferences("Accomod",MODE_PRIVATE);
        myPrefs1 = this.getSharedPreferences("Ac",MODE_PRIVATE);
        myPrefs2 = this.getSharedPreferences("Category",MODE_PRIVATE);
        myPrefs3 = this.getSharedPreferences("Fees",MODE_PRIVATE);
        myPrefs4 = this.getSharedPreferences("Languages",MODE_PRIVATE);
        myPrefs5 = this.getSharedPreferences("Region",MODE_PRIVATE);  login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        myPrefs6 = this.getSharedPreferences("Sports",MODE_PRIVATE);
        myPrefs7 = this.getSharedPreferences("Students",MODE_PRIVATE);
        myPrefs8 = this.getSharedPreferences("Subjects",MODE_PRIVATE);
        myPrefs9 = this.getSharedPreferences("Transport",MODE_PRIVATE);
        myPrefs10 = this.getSharedPreferences("Type",MODE_PRIVATE);
        doubt2 = this.getSharedPreferences("Doubt2",MODE_PRIVATE);

        new GetContacts().execute();

        Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
            }
        });
        Button viewShoppingpart3 = (Button) findViewById(R.id.ButtonViewPart3);
        viewShoppingpart3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
            }
        });
        Button viewFilter = (Button) findViewById(R.id.button_filter);
        viewFilter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor prefsEditor = doubt2.edit();
                prefsEditor.putString("sb2_f","123");
                prefsEditor.commit();
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), Filter_class.class);
                startActivity(viewShoppingCartIntent);
            }
        });

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



        if (ac_no_true==1 ||ac_yes_true==1 || accomod_no_true==1 ||accomod_yes_true==1 ||
                catog_boys==1 ||catog_girls==1 || catog_coed==1 ||fees1==1 || fees2==1 ||
                fees3==1 || fees1l==1 ||fees2l==1 || fees3l==1 ||fees1r==1 ||
                fees2r==1 ||fees3r==1 ||
                fees1s==1 ||fees2s==1 ||
                fees3s==1 ||fees1ss==1 || fees2ss==1 ||fees3ss==1 ||
                fees1q==1 ||fees2q==1 ||
                fees3q==1 ||ac_no_truet==1 ||
                ac_yes_truet==1 ||type1==1 || type2==1 ||type3==1)
        {
            viewFilter.setText("Filter By (Applied)");
            viewFilter.setBackgroundResource(R.drawable.buttonbackground);
            //view_ac.setBackgroundColor(Color.BLUE);
            viewFilter.setTextColor(Color.WHITE);
        }




        Button viewSort = (Button) findViewById(R.id.button_sort);
        viewSort.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor prefsEditor = doubt2.edit();
                prefsEditor.putString("sb2_s","345");
                prefsEditor.commit();
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), Sortby.class);
                startActivity(viewShoppingCartIntent);
                button_sort=1;
            }
        });



    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SchoolBrowse2.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //  JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jarray = new JSONArray(jsonStr);
                    // Getting JSON Array node
                    //  contacts = jsonObj.getJSONArray();

                    // looping through All Contacts
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject c = jarray.getJSONObject(i);

                        String schoolid = c.getString("_id");String Name = c.getString("Name");
                        String Fees = c.getString("Fees");
                        String Description = c.getString("Description");
                        String Schooltimings = c.getString("Schooltimings");String Location = c.getString("Location");
                        //  String Studentteacherratio = c.getString("Studentteacherratio");
                        String Popularity = c.getString("Popularity");String Price = c.getString("Price");
                        String Daysleft = c.getString("Daysleft");

                        String Board = c.getString("Board");
                        String Sectionsperclass = c.getString("Sectionsperclass");
                        String Teachers = c.getString("Teachers");
                        String Address =null;// Address = c.getString("Address");
                        String Firstdatetoapply= null; Firstdatetoapply = c.getString("Firstdatetoapply");
                        String Lastdatetoapply = null; Lastdatetoapply = c.getString("Lastdatetoapply");
                        String Limitonforms= null; Limitonforms = c.getString("Limitonforms");
                        String Specialcriteria = null; Specialcriteria = c.getString("Specialcriteria");



                        JSONArray labs = c.getJSONArray("Labs");
                        ArrayList<String> labs123  = new ArrayList<String>();

                        for ( int k=0;k<labs.length();k++){
                            String item = (String) labs.get(k);
                            labs123.add(item);
                        }

                        JSONArray Milestones = c.getJSONArray("Milestones");
                        String milestone_1 = null,milestone_2 = null,milestone_3= null;
                        for (int j=0;j<Milestones.length();j++){

                            JSONObject milestone_obj = Milestones.getJSONObject(j);
                            if(j==0){
                                milestone_1 = milestone_obj.getString("1");}
                            if (j==1) {
                                milestone_2 = milestone_obj.getString("2");}
                            if (j==2) {
                                milestone_3 = milestone_obj.getString("3");}
                        }

                       /* JSONArray Labs = c.getJSONArray("Labs");
                          ArrayList<String> Labs123  = new ArrayList<String>();

                        for ( int k=0;k<Labs.length();k++){
                            String item = (String) Labs.get(k);
                            Labs123.add(item);
                        }

                        */

                        JSONArray Facilities = c.getJSONArray("Facilities");
                        ArrayList<String> Facilities123  = new ArrayList<String>() ;

                        for ( int k=0;k<Facilities.length();k++){
                            String item = (String) Facilities.get(k);
                            Facilities123.add(item);
                        }




                        JSONArray Contactus = c.getJSONArray("Contact");
                        String Contactus_1 = null,Contactus_2 =null,Contactus_3= null;
                        for (int j=0;j<Contactus.length();j++){

                            JSONObject Contactus_obj = Contactus.getJSONObject(j);

                            Contactus_1 = Contactus_obj.getString("phone");

                            Contactus_2 = Contactus_obj.getString("fax");

                            Contactus_3 = Contactus_obj.getString("email");
                        }

                        JSONArray Rating = c.getJSONArray("Rating");
                        String Rating_1 = null,Rating_2=null;
                        for (int j=0;j<Rating.length();j++){

                            JSONObject Rating_obj = Rating.getJSONObject(j);
                            if(j==0){
                                Rating_1 = Rating_obj.getString("Stars");}
                            if (j==1) {
                                Rating_2 = Rating_obj.getString("Comments");
                            }
                        }

                        JSONArray Type = c.getJSONArray("Type");
                        ArrayList<String> Type123  = new ArrayList<String>() ;

                        for ( int k=0;k<Type.length();k++){
                            String item = (String) Type.get(k);
                            Type123.add(item);
                        }
                        JSONArray Student = c.getJSONArray("Student");
                        ArrayList<String> Student123 = new ArrayList<String>();

                        for ( int k=0;k<Student.length();k++){
                            String item = (String) Student.get(k);
                            Student123.add(item);
                        }
                        JSONArray Category = c.getJSONArray("Category");
                        ArrayList<String> Category123 = new ArrayList<String>();

                        for ( int k=0;k<Category.length();k++){
                            String item = (String) Category.get(k);
                            Category123.add(item);
                        }
                        JSONArray Transport = c.getJSONArray("Transport");
                        ArrayList<String> Transport123 = new ArrayList<String>();

                        for ( int k=0;k<Transport.length();k++){
                            String item = (String) Transport.get(k);
                            Transport123.add(item);
                        }
                        JSONArray Accomodation = c.getJSONArray("Accomodation");
                        ArrayList<String> Accomodation123  = new ArrayList<String>();

                        for ( int k=0;k<Accomodation.length();k++){
                            String item = (String) Accomodation.get(k);
                            Accomodation123.add(item);
                        }

                        JSONArray AC = c.getJSONArray("AC");
                        ArrayList<String> AC123 = new ArrayList<String>();

                        for ( int k=0;k<AC.length();k++){
                            String item = (String) AC.get(k);
                            AC123.add(item);
                        }

                        JSONArray Region = c.getJSONArray("Region");
                        ArrayList<String> Region123  = new ArrayList<String>();

                        for ( int k=0;k<Region.length();k++){
                            String item = (String) Region.get(k);
                            Region123.add(item);
                        }

                        JSONArray Fees_struc = c.getJSONArray("Feestructure");
                        ArrayList<String> Fees123  = new ArrayList<String>();

                        for ( int k=0;k<Fees_struc.length();k++){
                            String item = (String) Fees_struc.get(k);
                            Fees123.add(item);
                        }
                        JSONArray foreign_languages = c.getJSONArray("Languages");
                        ArrayList<String> foreeign_lang = new ArrayList<String>();
                        for ( int f=0;f<foreign_languages.length();f++){
                            String item = (String) foreign_languages.get(f);
                            foreeign_lang.add(item);
                        }
                        ArrayList<String> SUBJECTS_IN11TH123 = new ArrayList<String>();
                        ArrayList<String> SPORTS123  = new ArrayList<String>();
                        ArrayList<String> RELIGION123  = new ArrayList<String>();
                        JSONArray SUBJECTS_IN11TH = c.getJSONArray("Subjects");
                        JSONArray SPORTS = c.getJSONArray("Sports");

                        for ( int k=0;k<SPORTS.length();k++){
                            String item = (String) SPORTS.get(k);
                            SPORTS123.add(item);
                        }
                        for ( int k1=0;k1<SUBJECTS_IN11TH.length();k1++){
                            String item = (String) SUBJECTS_IN11TH.get(k1);
                            SUBJECTS_IN11TH123.add(item);
                        }

                        JSONArray ImagesColl = c.getJSONArray("Images");
                        ArrayList<String> ImagesColl123  = new ArrayList<String>();

                        for ( int k=0;k<ImagesColl.length();k++){
                            String item = (String) ImagesColl.get(k);
                            ImagesColl123.add(item);
                        }
                        JSONArray Imagelogo = c.getJSONArray("Logo");
                        String Logo = null;

                        for ( int k=0;k<Imagelogo.length();k++){
                            Logo = (String) Imagelogo.get(k);

                        }

                        SchoolProfile schoolProfile123 = new SchoolProfile(schoolid,Name,Fees,Description,Schooltimings,Location,Popularity,Price,Daysleft, milestone_1 ,milestone_2 ,milestone_3,
                                Contactus_3,Contactus_2,Contactus_1,
                                Rating_1 ,Rating_2,Type123,Student123,SPORTS123,Category123,foreeign_lang,SUBJECTS_IN11TH123,Region123,
                                Fees123,Transport123,Accomodation123,AC123,ImagesColl123,  false,false,false,false,false,false,
                                false, false,false,false,false,0,Board,Logo,Teachers,Address,Firstdatetoapply,Lastdatetoapply,
                                Specialcriteria,Limitonforms,Facilities123,labs123,Sectionsperclass);

                        arrayList_counter.add(schoolProfile123);
                        add_to_listview.add(schoolProfile123);













                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            //Toast.makeText(MainActivity.this,)
            listview = (ListView) findViewById(R.id.listview);

            Intent i = getIntent();
            go1 = i.getIntExtra("go1", 0);
            go2 = i.getIntExtra("go2", 0);
            go3 = i.getIntExtra("go3", 0);


            if (go3 == 3) {
                Collections.sort(add_to_listview, new Comparator<SchoolProfile>() {
                    public int compare(SchoolProfile obj1, SchoolProfile obj2) {
                        float a = 0;
                        float b = 0;
                        // TODO Auto-generated method stub
                        try {
                            a= Float.parseFloat((obj1.getRating_stars()));
                            b = Float.parseFloat(obj2.getRating_stars());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        if (a < b) return 1;
                        if (a > b) return -1;
                        return 0;
                    }
                });

                adapter = new ListViewAdapter1(SchoolBrowse2.this, add_to_listview);
                listview.setAdapter(adapter);

            }
            if (go1 == 1) {
                Collections.sort(add_to_listview, new Comparator<SchoolProfile>() {
                    public int compare(SchoolProfile obj1, SchoolProfile obj2) {
                     /*   int a = 0, b = 0;
                        // TODO Auto-generated method stub
                        try {
                            a = Integer.parseInt(obj1.getFees());
                            b = Integer.parseInt(obj2.getFees());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }


                        //return (a > b) ? -1: (a > b) ? 1:0 ;
                        // return   obj1.getFees().compareTo(obj2.getFees());
                        return b - a;*/
                        float a = 0;
                        float b = 0;
                        // TODO Auto-generated method stub
                        try {
                            a= Float.parseFloat((obj1.getFees()));
                            b = Float.parseFloat(obj2.getFees());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        if (a < b) return 1;
                        if (a > b) return -1;
                        return 0;
                    }
                });
                adapter = new ListViewAdapter1(SchoolBrowse2.this, add_to_listview);
                listview.setAdapter(adapter);

            }
            if (go2 == 2) {
                Collections.sort(add_to_listview, new Comparator<SchoolProfile>() {
                    public int compare(SchoolProfile obj1, SchoolProfile obj2) {
                        float a = 0;
                        float b = 0;
                        // TODO Auto-generated method stub
                        try {
                            a= Float.parseFloat((obj1.getFees()));
                            b = Float.parseFloat(obj2.getFees());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        if (a < b) return -1;
                        if (a > b) return 1;
                        return 0;
                    }
                });

                adapter = new ListViewAdapter1(SchoolBrowse2.this, add_to_listview);
                listview.setAdapter(adapter);

            }





            accomod_no_true = myPrefs.getInt("go_accomod_no_true",0);
            if (accomod_no_true==1)
            {filters("accomodation", "No", true);}

            accomod_yes_true =myPrefs.getInt("go_accomod_yes_true",0);
            if (accomod_yes_true==1)
            {filters("accomodation", "Yes", true);}




            ac_no_true = myPrefs1.getInt("go_ac_no_true",0);
            if (ac_no_true==1)
            {filters("ac_nonac", "No", true);}

            ac_yes_true =myPrefs1.getInt("go_ac_yes_true",0);
            if (ac_yes_true==1)
            {filters("ac_nonac", "Yes", true);}


            catog_boys = myPrefs2.getInt("go_Category_1_true",0);
            if (catog_boys==1)
            {filters("category","Boysonly",true);}

            catog_girls =myPrefs2.getInt("go_Category_2_true",0);
            if (catog_girls==1)
            {filters("category", "Girlsonly", true);}

            catog_coed =myPrefs2.getInt("go_Category_3_true",0);
            if (catog_coed==1)
            {filters("category", "Coed", true);}



            fees1 = myPrefs3.getInt("go_Fees_1_true",0);
            if (fees1==1)
            {filters("fees","5000andabove",true);}

            fees2 =myPrefs3.getInt("go_Fees_2_true",0);
            if (fees2==1)
            {filters("fees","10000andabove",true);}

            fees3 =myPrefs3.getInt("go_Fees_3_true",0);
            if (fees3==1)
            {filters("fees","10000andbelow",true);}


            fees1l = myPrefs4.getInt("go_Fees_1_truel",0);
            if (fees1l==1)
            {filters("foreign_languages","German",true);}

            fees2l =myPrefs4.getInt("go_Fees_2_truel",0);
            if (fees2l==1)
            {filters("foreign_languages","French",true);}

            fees3l =myPrefs4.getInt("go_Fees_3_truel",0);
            if (fees3l==1)
            {filters("foreign_languages","Spanish",true);}



            fees1r = myPrefs5.getInt("go_Fees_1_truer",0);
            if (fees1r==1)
            {filters("region","East-Delhi",true);}

            fees2r =myPrefs5.getInt("go_Fees_2_truer",0);
            if (fees2r==1)
            {filters("region","West-Delhi",true);}

            fees3r =myPrefs5.getInt("go_Fees_3_truer",0);
            if (fees3r==1)
            {filters("region","Noida",true);}


            fees1s = myPrefs6.getInt("go_Fees_1_trues",0);
            if (fees1s==1)
            {filters("sports","Cricket",true);}

            fees2s =myPrefs6.getInt("go_Fees_2_trues",0);
            if (fees2s==1)
            {filters("sports","Netball",true);}

            fees3s =myPrefs6.getInt("go_Fees_3_trues",0);
            if (fees3s==1)
            {filters("sports","Basketball",true);}



            fees1ss = myPrefs7.getInt("go_Fees_1_truess",0);
            if (fees1ss==1)
            {filters("students_per_class","30-35",true);}

            fees2ss =myPrefs7.getInt("go_Fees_2_truess",0);
            if (fees2ss==1)
            {filters("students_per_class","35-40",true);}

            fees3ss =myPrefs7.getInt("go_Fees_3_truess",0);
            if (fees3ss==1)
            {filters("students_per_class","40-45",true);}




            fees1q = myPrefs8.getInt("go_Fees_1_trueq",0);
            if (fees1q==1)
            {filters("subjects_in_11th","Biotech",true);}

            fees2q =myPrefs8.getInt("go_Fees_2_trueq",0);
            if (fees2q==1)
            {filters("subjects_in_11th","Music",true);}

            fees3q =myPrefs8.getInt("go_Fees_3_trueq",0);
            if (fees3q==1)
            {filters("subjects_in_11th","Drawing",true);}



            ac_no_truet = myPrefs9.getInt("go_accomod_no_truet",0);
            if (ac_no_truet==1)
            {filters("transport_facility", "No", true);}

            ac_yes_truet =myPrefs9.getInt("go_accomod_yes_truet",0);
            if (ac_yes_truet==1)
            {filters("transport_facility", "Yes", true);}


            type1 = myPrefs10.getInt("go_Fees_1_truep",0);
            if (type1==1)
            {filters("type","Primary",true);}

            type2 =myPrefs10.getInt("go_Fees_2_truep",0);
            if (type2==1)
            {filters("type","Secondary",true);}


            type3 = myPrefs10.getInt("go_Fees_3_truep",0);
            if (type3==1)
            {filters("type","Seniorsec",true);}


            //   Toast.makeText(getBaseContext(),"yes ho"+ arrayList_values_counter.size(),Toast.LENGTH_LONG).show();
            if (arrayList_values_counter.size()==0){
              //  Toast.makeText(getBaseContext(),"yes ho rha h",Toast.LENGTH_LONG).show();

                adapter = new ListViewAdapter1(SchoolBrowse2.this, add_to_listview);
                listview.setAdapter(adapter);

            }
            else if (arrayList_values_counter.size()>0){
                add_to_listview.clear();
               // Toast.makeText(getBaseContext(),"yes ho rha h"+ add_to_listview.size()+ "  "+arrayList_counter.size(),Toast.LENGTH_LONG).show();
                for (int j =0; j< arrayList_counter.size();j++) {
                    Object object = arrayList_counter.get(j);
                    SchoolProfile cur1 = (SchoolProfile) object;
                    int values = cur1.getValues() ;
                    if (cur1.isAc_nonac_show()){
                        values++;
                       // Toast.makeText(getBaseContext(),"1vyes ho rha h"+ values,Toast.LENGTH_SHORT).show();
                    }
                    if (cur1.isAccomodation_show()){
                        values++; //Toast.makeText(getBaseContext(),"2vyes ho rha h"+ values,Toast.LENGTH_SHORT).show();
                         }
                    if (cur1.isCategory_show()){
                        values++;}
                    if (cur1.isForeign_languages_show()){
                        values++;}
                    if (cur1.isRegion_show()){
                        values++;}

                    if (cur1.isSports_show()){
                        values++;}
                    if (cur1.isStudents_per_class_show()){
                        values++;}
                    if (cur1.isSubjects_in_11th_show()){
                        values++;}
                    if (cur1.isTransport_facility_show()){
                        values++;}
                    if (cur1.isType_show()){
                        values++;}
                    if (cur1.isFees_structre_show()){
                        values++;
                        // Toast.makeText(getBaseContext(),"3vyes ho rha h"+ values,Toast.LENGTH_SHORT).show();
                    }
                    cur1.setValues(values);
                    //   Toast.makeText(getBaseContext()," final yes ho rha h"+ values,Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(getBaseContext(),"yes ho rha h"+ values,Toast.LENGTH_SHORT).show();
                for (int j =0; j< arrayList_counter.size();j++) {

                    Object object = arrayList_counter.get(j);
                    SchoolProfile cur1 = (SchoolProfile) object;
                    int values = cur1.getValues() ;
                    int size = arrayList_values_counter.size();
                    //   Toast.makeText(getBaseContext()," final 2 yes"+"  "+ values+"  "+ size,Toast.LENGTH_SHORT).show();
                    if (size==values){
                        add_to_listview.add(cur1);
                    }
                }
                //  notifyAll();
                adp1 = new ListViewAdapter1(SchoolBrowse2.this, add_to_listview);
                listview.setAdapter(adp1);
            }
            // adapter = new ListViewAdapter(MainActivity.this,add_to_listview);

        }

    }

    public void filters ( String filter_name, String filter_value, Boolean true_false){
        filtercalled = 5;
        Class_filter_counter arraylist_filters_counter ;
        SchoolProfile schoolProfile1;
        switch (filter_name){
            case "ac_nonac" :
                int last=-1;
                // Toast.makeText(getBaseContext(),"yes ho rha h",Toast.LENGTH_LONG);
                int hollow=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("ac_nonac", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow = 5;
                        //  Toast.makeText(getBaseContext(),"yes ho rha h",Toast.LENGTH_LONG).show();
                    }


                    if (hollow == (-1) ) {
                        for (int m = 0; m < arrayList_values_counter.size(); m++) {
                            Object obj = arrayList_values_counter.get(m);
                            Class_filter_counter cur = (Class_filter_counter) obj;
                            if ((cur.getValue()).equals("ac_nonac")) {
                                int counter = cur.getCounter();
                                counter++;
                                hollow = 1;
                            }
                        }
                    }
                    if (hollow == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("ac_nonac", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getAC();
                        //  Toast.makeText(getBaseContext()," " + items_con.get(0),Toast.LENGTH_SHORT).show();
                        if (items_con.contains(filter_value)){
                            cur1.setAc_nonac_show(true);
                            //    Toast.makeText(getBaseContext()," " + items_con.get(0),Toast.LENGTH_LONG).show();
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("ac_nonac")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last =1;
                            }
                            else if (counter>0){
                                last =5;
                            }
                        }
                    }

                    if (last==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setAc_nonac_show(false);
                        }
                    }
                    else if (last==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con = cur1.getAC();
                            if ((cur1.getAC()).contains(filter_value)){
                                cur1.setAc_nonac_show(false);
                            }
                        }
                    }

                }
                break;
            case "accomodation" :
                int last_1 =-1;
                int hollow_1=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("accomodation", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_1 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("accomodation")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_1 = 1;
                        }
                    }

                    if (hollow_1 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("accomodation", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getAccomodation();
                        if ((cur1.getAccomodation()).contains(filter_value)){
                            cur1.setAccomodation_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("accomodation")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_1 =1;
                            }
                            else if (counter>0){
                                last_1 =5;
                            }
                        }
                    }

                    if (last_1==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setAccomodation_show(true);
                        }
                    }
                    else if (last_1 ==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con = cur1.getAccomodation();
                            if ((cur1.getAccomodation()).contains(filter_value)){
                                cur1.setAccomodation_show(false);
                            }
                        }
                    }

                }
                break;
            case "category" :
                int last_2=-1;
                int hollow_2=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("category", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_2 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("category")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_2 = 1;
                        }
                    }

                    if (hollow_2 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("category", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getCategory();
                        if ((cur1.getCategory()).contains(filter_value)){
                            cur1.setCategory_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("category")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_2 =1;
                            }
                            else if (counter>0){
                                last_2 =5;
                            }
                        }
                    }

                    if (last_2==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setCategory_show(true);
                        }
                    }
                    else if (last_2==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con = cur1.getCategory();
                            if ((cur1.getCategory()).contains(filter_value)){
                                cur1.setCategory_show(false);
                            }
                        }
                    }

                }
                break;
            case "foreign_languages" :
                int last_3 =-1;
                int hollow_3=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("foreign_languages", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_3 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("foreign_languages")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_3 = 1;
                        }
                    }

                    if (hollow_3 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("foreign_languages", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getLanguages();
                        if (items_con.contains(filter_value)){
                            cur1.setForeign_languages_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("foreign_languages")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_3 =1;
                            }
                            else if (counter>0){
                                last_3 =5;
                            }
                        }
                    }

                    if (last_3==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setForeign_languages_show(true);
                        }
                    }
                    else if (last_3 ==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getLanguages();
                            if (items_con123.contains(filter_value) && items_con123.size()<2){
                                cur1.setForeign_languages_show(false);
                            }
                        }
                    }

                }
                break;
            case "region" :
                int last_4=-1;
                int hollow_4=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("region", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_4 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("region")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_4 = 1;
                        }
                    }

                    if (hollow_4 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("region", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getRegion();
                        if ((cur1.getRegion()).contains(filter_value)){
                            cur1.setRegion_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("region")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_4 =1;
                            }
                            else if (counter>0){
                                last_4 =5;
                            }
                        }
                    }

                    if (last_4==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setRegion_show(true);
                        }
                    }
                    else if (last_4==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con = cur1.getRegion();
                            if ((cur1.getRegion()).contains(filter_value)){
                                cur1.setRegion_show(false);
                            }
                        }
                    }

                }
                break;

            case "sports" :
                int last_6=-1;
                int hollow_6=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("sports", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_6 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("sports")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_6 = 1;
                        }
                    }

                    if (hollow_6 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("sports", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getSports();
                        if (items_con.contains(filter_value)){
                            cur1.setSports_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("sports")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_6 =1;
                            }
                            else if (counter>0){
                                last_6 =5;
                            }
                        }
                    }

                    if (last_6==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setSports_show(true);
                        }
                    }
                    else if (last_6==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getSports();
                            if (items_con123.contains(filter_value) && items_con123.size()<2){
                                cur1.setSports_show(false);
                            }
                        }
                    }

                }
                break;
            case "students_per_class" :
                int last_7 =-1;
                int hollow_7=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("students_per_class", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_7 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("students_per_class")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_7 = 1;
                        }
                    }

                    if (hollow_7 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("students_per_class", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getStudent();
                        if ((cur1.getStudent()).contains(filter_value)){
                            cur1.setStudents_per_class_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("students_per_class")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_7 =1;
                            }
                            else if (counter>0){
                                last_7 =5;
                            }
                        }
                    }

                    if (last_7==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setStudents_per_class_show(true);
                        }
                    }
                    else if (last_7 ==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con = cur1.getStudent();
                            if ((cur1.getStudent()).contains(filter_value)){
                                cur1.setStudents_per_class_show(false);
                            }
                        }
                    }

                }
                break;

            case "subjects_in_11th" :
                int last_8=-1;
                int hollow_8=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("subjects_in_11th", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_8 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("subjects_in_11th")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_8 = 1;
                        }
                    }

                    if (hollow_8 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("subjects_in_11th", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con = cur1.getSubjects();

                        if (items_con.contains(filter_value)){
                            cur1.setSubjects_in_11th_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("subjects_in_11th")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_8 =1;
                            }
                            else if (counter>0){
                                last_8 =5;
                            }
                        }
                    }

                    if (last_8==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setSubjects_in_11th_show(true);
                        }
                    }
                    else if (last_8==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getSubjects();
                            if (items_con123.contains(filter_value) && items_con123.size()<2){
                                cur1.setSubjects_in_11th_show(false);
                            }
                        }
                    }

                }
                break;
            case "transport_facility" :
                int last_9=-1;
                int hollow_9=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("transport_facility", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_9 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("transport_facility")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_9 = 1;
                        }
                    }

                    if (hollow_9 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("transport_facility", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con123 = cur1.getTransport();
                        if ((cur1.getTransport()).contains(filter_value)){
                            cur1.setTransport_facility_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("transport_facility")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_9 =1;
                            }
                            else if (counter>0){
                                last_9 =5;
                            }
                        }
                    }

                    if (last_9==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setTransport_facility_show(true);
                        }
                    }
                    else if (last_9==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getTransport();
                            if ((cur1.getTransport()).contains(filter_value)){
                                cur1.setTransport_facility_show(false);
                            }
                        }
                    }

                }
                break;

            case "type" :
                int last_10=-1;
                int hollow_10=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("type", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_10 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("type")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_10 = 1;
                        }
                    }

                    if (hollow_10 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("type", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con123 = cur1.getType();
                        if ((cur1.getType()).contains(filter_value)){
                            cur1.setType_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("type")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_10 =1;
                            }
                            else if (counter>0){
                                last_10 =5;
                            }
                        }
                    }

                    if (last_10==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setType_show(true);
                        }
                    }
                    else if (last_10==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getType();
                            if ((cur1.getType()).contains(filter_value)){
                                cur1.setType_show(false);
                            }
                        }
                    }

                }
                break;
            case "fees" :
                int last_11=-1;
                int hollow_11=-1;
                if (true_false) {

                    if ((arrayList_values_counter.size()) == 0) {
                        arraylist_filters_counter = new Class_filter_counter("fees", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                        hollow_11 = 5;
                    }


                    for (int m = 0; m < arrayList_values_counter.size(); m++) {
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("fees")) {
                            int counter = cur.getCounter();
                            counter++;
                            hollow_11 = 1;
                        }
                    }

                    if (hollow_11 == (-1)) {
                        arraylist_filters_counter = new Class_filter_counter("fees", 1);
                        arrayList_values_counter.add(arraylist_filters_counter);
                    }

                    for (int j =0; j< arrayList_counter.size();j++){
                        Object object = arrayList_counter.get(j);
                        SchoolProfile cur1= (SchoolProfile) object;
                        ArrayList<String> items_con123 = cur1.getFeestructure();
                        if ((cur1.getFeestructure()).contains(filter_value)){
                            cur1.setFees_structre_show(true);
                        }
                    }


                }

                else {
                    for (int m = 0; m < arrayList_values_counter.size(); m++){
                        Object obj = arrayList_values_counter.get(m);
                        Class_filter_counter cur = (Class_filter_counter) obj;
                        if ((cur.getValue()).equals("fees")) {
                            int counter = cur.getCounter();
                            counter--;
                            if (counter==0){
                                last_11 =1;
                            }
                            else if (counter>0){
                                last_11 =5;
                            }
                        }
                    }

                    if (last_11==1){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            cur1.setFees_structre_show(true);
                        }
                    }
                    else if (last_11==5){
                        for (int j =0; j< arrayList_counter.size();j++){
                            Object object = arrayList_counter.get(j);
                            SchoolProfile cur1= (SchoolProfile) object;
                            ArrayList<String> items_con123 = cur1.getFeestructure();
                            if ((cur1.getFeestructure()).contains(filter_value)){
                                cur1.setFees_structre_show(false);
                            }
                        }
                    }

                }
                break;


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
            } case R.id.action_homeout:
            {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_refresh:
            {
                finish();
                startActivity(getIntent());
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
        getMenuInflater().inflate(R.menu.settings_for_caf, menu);
        return true;
    }

}
