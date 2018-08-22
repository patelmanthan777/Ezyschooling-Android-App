package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CAF_part3 extends AppCompatActivity {
    private String[] mCartList ={"ABC Public School","BCD Public School","CFG Public School"};
    Spinner year_father, year_mother;
    String[] SPINNERVALUES_years= {"<YY>","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};

    String SpinnerValue_year_father,SpinnerValue_year_mother,transport_yesno;
    SharedPreferences login,logout;

    String number_siblings,number_alumini;
    String response_ofalumini = null,noof_alumini= null,response_sibling= null,noof_sibling= null;

    CheckBox t1,t2;String trans = null;
ArrayList<String> schools = new ArrayList<>();ArrayList<String> schoolsid = new ArrayList<>();

    Spinner schoolnames_sib1,schoolnames_sib2;
    Spinner schoolnames_alu1,schoolnames_alu2;
    String SpinnerValue_schoolnames_sib1,SpinnerValue_schoolnames_sib2;
    String SpinnerValue_schoolnames_alu1,SpinnerValue_schoolnames_alu2;
    ArrayList<String> item_schoolnames = new ArrayList<String>();


EditText editText_name_sibl,editText_class_sibl,editText_name_sib2,editText_class_sib2;

    int go_sib1 = 0;
    int go_sib2 = 0;
    int go_father = 0;
    int go_mother = 0;
    int go_both = 0;

    RequestQueue requestQueue;
    String insertUrl = "http://ezyschooling.com/cafpart3/submit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caf_part3); requestQueue = Volley.newRequestQueue(getApplicationContext());
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        final CheckBox declaration =  (CheckBox)findViewById(R.id.checkBox_declaration);
        schools= (ArrayList<String>) getIntent().getSerializableExtra("schools");
        schoolsid= (ArrayList<String>) getIntent().getSerializableExtra("schoolsid");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner father_year = (Spinner)findViewById(R.id.spinner_year_father);
        final Spinner mother_year = (Spinner)findViewById(R.id.spinner_year_mother);

        ArrayAdapter<String> adapterrel= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUES_years);
        father_year.setAdapter(adapterrel);
        mother_year.setAdapter(adapterrel);

        father_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_year_father=(String)father_year.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mother_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_year_mother=(String)mother_year.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button proceed_from_sibling = (Button)findViewById(R.id.button_proceed_from_sibalumini);

//        mCartList = ShoppingCartHelper.getCart();

        TextView textView = (TextView)findViewById(R.id.textView_itemsall);

        final TextView textView_noofsiblings = (TextView)findViewById(R.id.textView_howmany);

         editText_name_sibl = (EditText) findViewById(R.id.editText_name_sibling);
         editText_class_sibl = (EditText) findViewById(R.id.editText_class_sibling);



        final RelativeLayout no_of_sibling = (RelativeLayout)findViewById(R.id.Linerarlooo);
        no_of_sibling.setVisibility(View.GONE);

        final LinearLayout sib1 = (LinearLayout) findViewById(R.id.linearlayout_sibling1);
        final LinearLayout sib2 = (LinearLayout) findViewById(R.id.linearlayout_sibling2);
        sib1.setVisibility(View.GONE); sib2.setVisibility(View.GONE);






        final LinearLayout alumini1 = (LinearLayout)findViewById(R.id.alumiini1);
        alumini1.setVisibility(View.GONE);
        final LinearLayout alumini2 = (LinearLayout)findViewById(R.id.alumini2);
        alumini2.setVisibility(View.GONE);
        final RadioGroup groupRadio_no_of_alumini =(RadioGroup)findViewById(R.id.radioGroup_alumini_number);
        groupRadio_no_of_alumini.setVisibility(View.GONE);
        final TextView textView_noofalumini = (TextView)findViewById(R.id.textView_alumini_number);
        textView_noofalumini.setVisibility(View.GONE);
        RadioGroup groupRadio_alumnini=(RadioGroup)findViewById(R.id.radioGroup_alumini_yesno);
        final RadioGroup groupRadio_no_of_siblings =(RadioGroup)findViewById(R.id.radioGroup_no_of_siblings);

        RadioGroup groupRadio=(RadioGroup)findViewById(R.id.radioGroup_sibling);
        RadioGroup group_transport=(RadioGroup)findViewById(R.id.radiogroup_transport);

       editText_name_sib2 = (EditText) findViewById(R.id.editText_sib2_name);
        editText_class_sib2 = (EditText) findViewById(R.id.editText_sib2_class);

        int selectedId = groupRadio_alumnini.getCheckedRadioButtonId();
        int selectedId2 = groupRadio_no_of_alumini.getCheckedRadioButtonId();
        int selectedIdsib = groupRadio_no_of_siblings.getCheckedRadioButtonId();
        int selectedIdsib2 = groupRadio.getCheckedRadioButtonId();



        final RadioButton radioButton_yesno = (RadioButton) findViewById(selectedId);
        RadioButton radioButton_noofalumini = (RadioButton) findViewById(selectedId2);
        RadioButton radioButton_yesnosib = (RadioButton) findViewById(selectedIdsib2);
        final RadioButton radioButton_noofsib = (RadioButton) findViewById(selectedIdsib);


        //textView.append(" "+"ABC Public School" + "\n" +" "+ "BCD Public School"+ "\n"+" "+"CFG Public School"+"\n"+" "+"SS Public School");

        for (int k=1;k<schools.size();k++){
            textView.append(" " + schools.get(k) + "\n");
        }


        TextView noofschool = (TextView)findViewById(R.id.textView_noofschools);
         String noschool = " " + (schools.size() - 1) ;
         noofschool.append(noschool);
        //   ArrayList<String> item1 = new ArrayList<String>();





        schoolnames_sib1 = (Spinner)findViewById(R.id.spinner_schoolname_sibling1);
        schoolnames_sib2 = (Spinner)findViewById(R.id.spinner_schoolname_sib2);
        ArrayAdapter<String> adapter_schoolnames = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, schools);
        schoolnames_sib1.setAdapter(adapter_schoolnames);
        schoolnames_sib2.setAdapter(adapter_schoolnames);
        schoolnames_sib1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_schoolnames_sib1=(String)schoolnames_sib1.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        schoolnames_sib2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_schoolnames_sib2=(String)schoolnames_sib2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });






        schoolnames_alu1 = (Spinner)findViewById(R.id.spinner_schoolnames_alumini1);
        schoolnames_alu2 = (Spinner)findViewById(R.id.spinner_alumini2_schoolnames);

        schoolnames_alu1.setAdapter(adapter_schoolnames);
        schoolnames_alu2.setAdapter(adapter_schoolnames);
        schoolnames_alu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_schoolnames_alu1=(String)schoolnames_alu1.getSelectedItem();
             //   Toast.makeText(getBaseContext(), "school is"+ SpinnerValue_schoolnames_alu1, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        schoolnames_alu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue_schoolnames_alu2=(String)schoolnames_alu2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        group_transport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton_transport_yes)
                {
                    trans="yes";
                }
                else if(i==R.id.radioButton_transport_no)
                {

                    trans="no";


                }

                RadioButton rb = (RadioButton)findViewById(i);
                transport_yesno = (String) rb.getText();
            }
        });

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.sibling_yes)
                {
                    no_of_sibling.setVisibility(View.VISIBLE);


                    groupRadio_no_of_siblings.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {


                            if(checkedId==R.id.radioButton_1)
                            {
                                sib1.setVisibility(View.VISIBLE);
                                sib2.setVisibility(View.GONE);
                                go_sib1 =1;go_sib2=0;
                            }
                            else if(checkedId==R.id.radioButton_2)
                            {

                                sib1.setVisibility(View.VISIBLE);
                                sib2.setVisibility(View.VISIBLE);
                                 go_sib1 =0;go_sib2 =1;

                            }

                            RadioButton rb = (RadioButton)findViewById(checkedId);
                            number_siblings = (String) rb.getText();
                            // Toast.makeText(getBaseContext(),""+number_siblings, Toast.LENGTH_LONG).show();
                        }
                    });


                    // ac = autoCompleteTextView.getText().toString().trim().toLowerCase();
                }
                else if(checkedId==R.id.Sibling_NO)
                {
                    no_of_sibling.setVisibility(View.GONE);
                    sib1.setVisibility(View.GONE);
                    sib2.setVisibility(View.GONE);
                   go_sib1 =0;go_sib2=0;

                }

                RadioButton rb1 = (RadioButton)findViewById(checkedId);
                response_ofalumini = (String)rb1.getText();

            }
        });





        // find the radiobutton by returned id




        groupRadio_alumnini.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton_alumini_yes){
                    textView_noofalumini.setVisibility(View.VISIBLE);
                    groupRadio_no_of_alumini.setVisibility(View.VISIBLE);

                    groupRadio_no_of_alumini.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId==R.id.radioButton_alumini_1){
                                alumini1.setVisibility(View.VISIBLE);
                                alumini2.setVisibility(View.GONE);
                                go_father =1;
                                go_mother=0;
                                go_both=0;
                            }

                            else if(checkedId==R.id.radioButton_alumini_2){
                                alumini1.setVisibility(View.GONE);
                                alumini2.setVisibility(View.VISIBLE);
                                go_father =0;
                                go_mother=1;
                                go_both=0;
                            }
                            else if(checkedId==R.id.radioButton_alumini_3){
                                alumini1.setVisibility(View.VISIBLE);
                                alumini2.setVisibility(View.VISIBLE);
                                go_father =0;
                                go_mother=0;
                                go_both=1;
                            }

                            RadioButton rb = (RadioButton)findViewById(checkedId);
                            number_alumini = (String) rb.getText();

                        }
                    });


                }

                else if (checkedId==R.id.radioButton_alumini_no){
                    textView_noofalumini.setVisibility(View.GONE);
                    groupRadio_no_of_alumini.setVisibility(View.GONE);
                    alumini1.setVisibility(View.GONE);
                    alumini2.setVisibility(View.GONE);
                    go_father =0;
                    go_mother=0;
                    go_both=0;
                }

                RadioButton rb1 = (RadioButton)findViewById(checkedId);
                response_sibling = (String)rb1.getText();
            }
        });

        if (radioButton_yesno != null){response_ofalumini = (String) radioButton_yesno.getText();}
        if (radioButton_noofalumini != null)  {noof_alumini = (String) radioButton_noofalumini.getText();}
        if (radioButton_yesnosib != null) { response_sibling  = (String) radioButton_yesnosib.getText();}
        if (radioButton_noofsib != null){ noof_sibling =  (String) radioButton_noofsib.getText();}

        proceed_from_sibling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (response_ofalumini == null || response_sibling == null || transport_yesno == null){
                    showSimpleDialog1();
                }
                else if (!declaration.isChecked()){
                    showSimpleDialog_dec();
                }
               else {
                    json();
                }
            }

        });


    }
    public void showSimpleDialog_dec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please agree for terms and conditions.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill all details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog2() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill all Sibling1 Details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog3() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill all Sibling2 Details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog4() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill all Father Alumini Details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog5() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF_part3.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill all Mother Alumini Details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
public void json(){

    String  school_sibling1 =  SpinnerValue_schoolnames_sib1;
    String  school_sibling2 =  SpinnerValue_schoolnames_sib2;
    String  school_alumini1 =  SpinnerValue_schoolnames_alu1;
    String  school_alumini2 =  SpinnerValue_schoolnames_alu2;

    String name1 = editText_name_sibl.getText().toString();
    String class1 = editText_class_sibl.getText().toString();
    String name2 = editText_name_sib2.getText().toString();
    String class2 = editText_class_sib2.getText().toString();



    JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
    jsonobj = new JSONObject();

    //Toast.makeText(MainActivity.class,"name" + editText_name_sibl.getText().toString(),Toast.LENGTH_SHORT ).show();

    try {
        JSONArray sibling = new JSONArray();
        if(number_siblings != null) {

            if (number_siblings.equals("1") && go_sib1==1) {
            JSONObject sibling1 = new JSONObject();

                if (name1.length() <3 || class1.length() < 2 || school_sibling1.equals("<--select-->")){
                    showSimpleDialog2();
                }
                else {
                    sibling1.put("name", editText_name_sibl.getText().toString());
                    sibling1.put("class", editText_class_sibl.getText().toString());
                    // sibling1.put("gender", gender_sibling1);
                    //  sibling1.put("dateofbirth",dob_sibling1 );
                    sibling1.put("school", school_sibling1);
                   // Toast.makeText(getBaseContext(), "1 Sib is selected", Toast.LENGTH_LONG).show();
                    //    Toast.makeText(getBaseContext(),sibling.length() +" is length", Toast.LENGTH_LONG).show();
                    //sibling.put(Integer.parseInt(""),sibling1);

                    sibling.put(sibling1);
                }
        }

            if (number_siblings.equals("2") && go_sib2==1) {

                JSONObject sibling1 = new JSONObject();
                if (name1.length() <3 || class1.length() < 2 || school_sibling1.equals("<--select-->")){
                    showSimpleDialog2();
                }
                else {
                    sibling1.put("name", editText_name_sibl.getText().toString());
                    sibling1.put("class", editText_class_sibl.getText().toString());
                    // sibling1.put("gender", gender_sibling1);
                    //  sibling1.put("dateofbirth",dob_sibling1 );
                    sibling1.put("school", school_sibling1);
                  //  Toast.makeText(getBaseContext(), "1 Sib is selected", Toast.LENGTH_LONG).show();
                 //      Toast.makeText(getBaseContext(),sibling.length() +" is length", Toast.LENGTH_LONG).show();
                    //sibling.put(Integer.parseInt(""),sibling1);

                    sibling.put(sibling1);
                }



                if (name2.length() <3 || class2.length() < 2 || school_sibling2.equals("<--select-->")){
                    showSimpleDialog3();
                }
                else {
                JSONObject sibling2 = new JSONObject();
                sibling2.put("name", editText_name_sib2.getText().toString());
                sibling2.put("class", editText_class_sib2.getText().toString());
                sibling2.put("school", school_sibling2);

                sibling.put(sibling2);}
             //  Toast.makeText(getBaseContext(),"2 Sib is selected", Toast.LENGTH_LONG).show();
                //  Toast.makeText(getBaseContext(),sibling.length() +" is length", Toast.LENGTH_LONG).show();
            }
        }
       // Toast.makeText(getBaseContext(),sibling.length() +" is length of sib", Toast.LENGTH_LONG).show();
        jsonobj.put("sibling",sibling);


        JSONArray alumini = new JSONArray();

        if (number_alumini != null){ if( number_alumini.equals("Father") && go_father==1 ){
           // Toast.makeText(getBaseContext(), "school is"+ school_alumini1, Toast.LENGTH_LONG).show();
            JSONObject alumini1 = new JSONObject();
                            if ( SpinnerValue_year_father.equals("<YY>") ||
                                    school_alumini1.equals("<--select-->")){
                                     showSimpleDialog4();
                                     }else {
            alumini1.put("father",true );
            alumini1.put("mother",false );
            alumini1.put("year",SpinnerValue_year_father );
            alumini1.put("school",school_alumini1);

            alumini.put(alumini1);}
           // Toast.makeText(getBaseContext(),"Father is selected", Toast.LENGTH_LONG).show();
        }
        else if (number_alumini.equals("Mother") && go_mother==1)

        {

            JSONObject alumini2 = new JSONObject();
if ( SpinnerValue_year_mother.equals("<YY>") || school_alumini2.equals("<--select-->")) {


showSimpleDialog5();
         }  else { alumini2.put("year", SpinnerValue_year_mother);
            alumini2.put("father", false);
            alumini2.put("mother", true);
            alumini2.put("school", school_alumini2);

            alumini.put(alumini2);}
            //Toast.makeText(getBaseContext(),"Mother is selected", Toast.LENGTH_LONG).show();
        }

        else if (number_alumini.equals("Both") && go_both==1)
        {
            JSONObject alumini1 = new JSONObject();
            if ( SpinnerValue_year_father.equals("<YY>") || school_alumini1.equals("<--select-->")){
                showSimpleDialog4();
            }else {
                alumini1.put("father",true );
                alumini1.put("mother",false );
                alumini1.put("year",SpinnerValue_year_father );
                alumini1.put("school",school_alumini1);

                alumini.put(alumini1);}

            JSONObject alumini2 = new JSONObject();
            if ( SpinnerValue_year_mother.equals("<YY>") || school_alumini2.equals("<--select-->")) {


                showSimpleDialog5();
            }  else { alumini2.put("year", SpinnerValue_year_mother);
                alumini2.put("father", false);
                alumini2.put("mother", true);
                alumini2.put("school", school_alumini2);

                alumini.put(alumini2);}
           // Toast.makeText(getBaseContext(),"Both are selected", Toast.LENGTH_LONG).show();


        }}
      //  Toast.makeText(getBaseContext(),alumini.length() +" is length", Toast.LENGTH_LONG).show();
        jsonobj.put("alumni",alumini);

        JSONArray trans12 = new JSONArray();
       if (trans != null){

           if (trans.equals("yes"))
           {   // trans12.put(trans);
               for (int k=0;k<schoolsid.size();k++){
                  // textView.append(" " + schools.get(k) + "\n");
                   trans12.put(schoolsid.get(k));
                //   Toast.makeText(getBaseContext()," " + schoolsid.get(k), Toast.LENGTH_LONG).show();
               }
         }
       }
     //   Toast.makeText(getBaseContext(),trans12.length() +" is length of transport", Toast.LENGTH_LONG).show();
        jsonobj.put("transport",trans12);





    } catch (JSONException e) {
        e.printStackTrace();
    }


    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
            insertUrl, jsonobj,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {


                    SharedPreferences.Editor prefsEditor = login.edit();
                    prefsEditor.putString("Status_form1","no");
                    prefsEditor.commit();
                    //   Log.d(TAG, response.toString());
                    //   msgResponse.setText(response.toString());
                    //  hideProgressDialog();
                 //   Toast.makeText(getBaseContext()," " + response.toString(), Toast.LENGTH_LONG).show();
                    /*    String res = response.toString();
                        if (res.equals("success")){
                            a=1;  Toast.makeText(getBaseContext(),"equal", Toast.LENGTH_LONG).show();
                        }
                        else {a=2;
                            Toast.makeText(getBaseContext(),"not equal", Toast.LENGTH_LONG).show();
                        }*/
                }
            }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            //  VolleyLog.d(TAG, "Error: " + error.getMessage());
            // hideProgressDialog();

            Toast.makeText(getBaseContext(),"Error , Please login again", Toast.LENGTH_LONG).show();
        }
    }) {

        /**
         * Passing some request headers
         * */
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            String valueoftoken = login.getString("Token","null");
            headers.put("Content-Type", "application/json; charset=utf-8");

            headers.put("x-access-token",valueoftoken);
            return headers;
        }



    };

    // Adding request to request queue
    requestQueue.add(jsonObjReq);

}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
                this.finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }
}

