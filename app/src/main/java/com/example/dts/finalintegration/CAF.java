package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CAF extends AppCompatActivity {



    @InjectView(R.id.editText_child_firstname) EditText child_firstname;
    @InjectView(R.id.editText_child_lastname) EditText child_lastname;
    @InjectView(R.id.editText_bloodgrp) EditText bloodgroup;
    @InjectView(R.id.editText_catagory) EditText disability;
    @InjectView(R.id.editText_disability) EditText catagory;
    @InjectView(R.id.editText_address) EditText address;

    @InjectView(R.id.linearlayout_guardian) LinearLayout linearlayout_guardian;

    @InjectView(R.id.radiogroup_singlechild)RadioGroup singlechild;
    @InjectView(R.id.radiogroup_singleparent) RadioGroup singleparent;
    @InjectView(R.id.radiogroup_guardian) RadioGroup guardian;

    @InjectView(R.id.editText_guardian_firstname) EditText guardian_firstname;
    @InjectView(R.id.editText_guardian_qualification) EditText guardian_qualification;
    @InjectView(R.id.editText_guardian_occuaption) EditText guardian_occupation;
    @InjectView(R.id.editText_guardian_officeaddress) EditText guardian_officeaddress;
    @InjectView(R.id.editText_guardian_annualincome) EditText guardian_annualincome;
    @InjectView(R.id.editText_guardian_officeadd) EditText guardian_officeadd;
    @InjectView(R.id.editText_guardian_officephone) EditText guardian_officephone;


    @InjectView(R.id.editText_father_firstname) EditText father_firstname;

    @InjectView(R.id.editText_father_qualification) EditText father_qualification;
    @InjectView(R.id.editText_father_occuaption) EditText father_occupation;
    @InjectView(R.id.editText_father_officeaddress) EditText father_officeaddress;
    @InjectView(R.id.editText_father_annualincome) EditText father_annualincome;
    @InjectView(R.id.editText_father_officeadd) EditText father_officeadd;
    @InjectView(R.id.editText_father_officephone) EditText father_officephone;

    @InjectView(R.id.editText_mother_firstname) EditText mother_firstname;

    @InjectView(R.id.editText_mother_qualification) EditText mother_qualification;
    @InjectView(R.id.editText_mother_occupation) EditText mother_occupation;
    @InjectView(R.id.editText_mother_officeaddress) EditText mother_officeaddress;
    @InjectView(R.id.editText_mother_annualincome) EditText mother_annualincome;
    @InjectView(R.id.editText_mother_officeadd) EditText mother_officeadd;
    @InjectView(R.id.editText_mother_officephone) EditText mother_officephone;


    Spinner gender,dob,dob2,dob3,religion;
    SharedPreferences myPrefs;
    SharedPreferences login;
    Button proceedfromcaf;

    String singlechild_value, singleparent_value, guardian_value;

    String[] SPINNERVALUESdob= {"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String SpinnerValuedob;

    String[] SPINNERVALUESdob2= {"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String SpinnerValuedob2;

    String[] SPINNERVALUESdob3= {"YY","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
    String SpinnerValuedob3;

    String[] SPINNERVALUESgender= {"<--select-->","male","female"};
    String SpinnerValuegender;



    String[] SPINNERVALUESreligion= {"<--select-->","hindu","muslim","sikh","jain","christian","buddhist","parsi","others"};
    String SpinnerValuereligion;




    RequestQueue requestQueue;
    String insertUrl = "http://ezyschooling.com/forms"; String insertUrl123 = "http://ezyschooling.com/forms/data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caf2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.inject(this);
        myPrefs = this.getSharedPreferences("CAF",MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        child_firstname = (EditText) findViewById(R.id.editText_child_firstname);
        child_lastname = (EditText) findViewById(R.id.editText_child_lastname);
        bloodgroup = (EditText) findViewById(R.id.editText_bloodgrp);
        catagory = (EditText) findViewById(R.id.editText_catagory);
        disability = (EditText) findViewById(R.id.editText_disability);
        address = (EditText) findViewById(R.id.editText_address);

        receive_data();


linearlayout_guardian.setVisibility(View.GONE);




        proceedfromcaf = (Button) findViewById(R.id.button_proceedfrom_cafform);



        singlechild.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                RadioButton rb = (RadioButton)findViewById(i);
                singlechild_value = (String) rb.getText();
            }
        });

        singleparent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                RadioButton rb = (RadioButton)findViewById(i);
                singleparent_value = (String) rb.getText();
            }
        });

        guardian.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.radioButton_guardian_yes)
                {
                    linearlayout_guardian.setVisibility(View.VISIBLE);
                }
                else if(i==R.id.radioButton_guardian_no)
                {

                    linearlayout_guardian.setVisibility(View.GONE);

                }
                RadioButton rb = (RadioButton)findViewById(i);
                guardian_value = (String) rb.getText();
            }
        });


        religion = (Spinner)findViewById(R.id.spinner_religion);



        ArrayAdapter<String> adapterrel= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESreligion);
        religion.setAdapter(adapterrel);
        religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuereligion=(String)religion.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dob = (Spinner)findViewById(R.id.spinnerdob);



        ArrayAdapter<String> adapterdob= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob);
        dob.setAdapter(adapterdob);
        dob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob=(String)dob.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dob2 = (Spinner)findViewById(R.id.spinnerdob2);



        ArrayAdapter<String> adapterdob2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob2);
        dob2.setAdapter(adapterdob2);
        dob2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob2=(String)dob2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dob3 = (Spinner)findViewById(R.id.spinnerdob3);



        ArrayAdapter<String> adapterdob3= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob3);
        dob3.setAdapter(adapterdob3);
        dob3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob3=(String)dob3.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });








        gender = (Spinner)findViewById(R.id.spinner_gender);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESgender);
        gender.setAdapter(adapter1);




        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuegender=(String)gender.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        proceedfromcaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               json();

                String child_firstnamec = child_firstname.getText().toString().trim().toLowerCase();
                String child_lastnamec = child_lastname.getText().toString().trim().toLowerCase();
                String bloodgroupc = bloodgroup.getText().toString().trim().toLowerCase();
                String disabilityc = disability.getText().toString().trim().toLowerCase();
                String catagoryc = catagory.getText().toString().trim().toLowerCase();
                String addressc = address.getText().toString().trim().toLowerCase();


                String guardian_firstnamec = guardian_firstname.getText().toString();
                String guardian_qualificationc = guardian_qualification.getText().toString();
                String guardian_occupationc = guardian_occupation.getText().toString();
                String guardian_officeaddressc = guardian_officeaddress.getText().toString();
                String guardian_annualincomec = guardian_annualincome.getText().toString();
                String guardian_officeaddc = guardian_officeadd.getText().toString();
                String guardian_annualphonec = guardian_officephone.getText().toString();

                String father_firstnamec = father_firstname.getText().toString().trim().toLowerCase();
                String father_qualificationc = father_qualification.getText().toString().trim().toLowerCase();
                String father_occupationc = father_occupation.getText().toString().trim().toLowerCase();
                String father_officeaddressc = father_officeaddress.getText().toString().trim().toLowerCase();
                String father_annualincomec = father_annualincome.getText().toString().trim().toLowerCase();
                String father_officeaddc = father_officeadd.getText().toString();
                String father_annualphonec = father_officephone.getText().toString();

                String mother_firstnamec = mother_firstname.getText().toString().trim().toLowerCase();
                String mother_qualificationc = mother_qualification.getText().toString().trim().toLowerCase();
                String mother_occupationc = mother_occupation.getText().toString().trim().toLowerCase();
                String mother_officeaddressc = mother_officeaddress.getText().toString().trim().toLowerCase();
                String mother_annualincomec = mother_annualincome.getText().toString().trim().toLowerCase();
                String mother_officeaddc = mother_officeadd.getText().toString();
                String mother_annualphonec = mother_officephone.getText().toString();


                if (child_firstnamec.length() <3){
                    showSimpleDialog_fname();
                }
                else if (child_lastnamec.length() <3){
                    showSimpleDialog_lname();
                }
                else if (!isValidEmail(bloodgroupc)){
                    showSimpleDialog_email();
                }else if (disabilityc.length()!=10){
                    showSimpleDialog_mobile();
                } else if (catagoryc.length() <2){
                    showSimpleDialog_category();
                }
                else if (addressc.length() <5){
                    showSimpleDialog_address();
                }
                else if(SpinnerValuedob.matches("DD")|| SpinnerValuedob2.matches("MM")|| SpinnerValuedob3.matches("YY")
                        ){
                    showSimpleDialog_dob();
                }
                else if (SpinnerValuegender.matches("<--select-->")){
                    showSimpleDialog_gender();
                }

                else if (father_firstnamec.length() <3){
                    showSimpleDialog_father_firstnamec();
                }
                else if (father_qualificationc.length() < 2){
                    showSimpleDialog_father_qualificationc();
                }else if (father_occupationc.length()< 2){
                    showSimpleDialog_father_occupationc();
                } else if (father_officeaddressc.length()!=10){
                    showSimpleDialog_father_officeaddressc();
                }
                else if (father_annualincomec.length() <2){
                    showSimpleDialog_father_annualincomec();
                }
                else if (father_officeaddc.length()<2){
                    showSimpleDialog_father_officeaddc();
                }

                else if (mother_firstnamec.length() <3){
                    showSimpleDialog_mther_firstnamec();
                }
                else if (singlechild_value == null){
                    showSimpleDialog_singlechild();

                }
                else if(singleparent_value == null){
                    showSimpleDialog_singleparent();
                }
                else if (guardian_value==null){
                    showSimpleDialog_guardian();
                }

                else if (mother_qualificationc.length() < 2){
                    showSimpleDialog_mther_qualificationc();
                }else if (mother_occupationc.length()< 2){
                    showSimpleDialog_mther_occupationc();
                } else if (mother_officeaddressc.length()<2){
                    showSimpleDialog_mther_officeaddressc();
                }
                else if (mother_annualincomec.length() <2){
                    showSimpleDialog_mther_annualincomec();
                }
                else if (mother_officeaddc.length()<2){
                    showSimpleDialog_mother_officeaddc();
                } else if (guardian_value.equals("Yes")){
                    if (guardian_firstnamec.length() <3){
                        showSimpleDialog_guardian_firstnamec();
                    }
                    else if (guardian_qualificationc.length() < 2){
                        showSimpleDialog_guardian_qualificationc();
                    } else if (guardian_occupationc.length()< 2){
                        showSimpleDialog_guardian_occupationc();
                    }
                    else if (guardian_officeaddressc.length()!=10){
                        showSimpleDialog_guardian_officeaddressc();
                    }
                    else if (guardian_annualincomec.length() <2){
                        showSimpleDialog_guardian_annualincomec();
                    }
                    else if (guardian_officeaddc.length()<2){
                        showSimpleDialog_guardian_officeaddc();
                    }
                    else {
                        startActivity(new Intent(getBaseContext(), Childphoto.class));
                    }

                }

                else {
                    startActivity(new Intent(getBaseContext(), Childphoto.class));
                }



            }

        });


    }


    public void receive_data (){
        JsonObjectRequest jsonObjReq123 = new JsonObjectRequest(Request.Method.GET,
                insertUrl123,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tag is : ", response.toString());



                        try {
                            JSONObject personel = response.getJSONObject("personal");
                            String child_firstnamec = personel.getString("firstname");
                            child_firstname.setText(child_firstnamec);
                            String child_lastnamec = personel.getString("lastname"); child_lastname.setText(child_lastnamec);
                            String bloodgroupc = personel.getString("id");bloodgroup.setText(bloodgroupc);
                            String disabilityc = personel.getString("phoneno");disability.setText(disabilityc);
                            String catagoryc = personel.getString("category");catagory.setText(catagoryc);
                            String addressc = personel.getString("Address");address.setText(addressc);
                            String religion123 = personel.getString("religion");
                             selectSpinnerItemByValue(religion,religion123);
                            selectSpinnerItemByValue(gender,personel.getString("gender"));
                            String dob111 = personel.getString("dob");
                            String yy,mm,dd;
                            yy = String.valueOf(dob111.charAt(0))+String.valueOf(dob111.charAt(1))
                                    +String.valueOf(dob111.charAt(2))+String.valueOf(dob111.charAt(3));
                            mm = String.valueOf(dob111.charAt(5))+String.valueOf(dob111.charAt(6));
                            dd = String.valueOf(dob111.charAt(8))+String.valueOf(dob111.charAt(9));
                            selectSpinnerItemByValue(dob,dd);
                            selectSpinnerItemByValue(dob2,mm);
                            selectSpinnerItemByValue(dob3,yy);
                         //   Toast.makeText(getBaseContext(),""+yy,Toast.LENGTH_LONG).show();
                            String singlechildc = personel.getString("singlechild");
                            String singleparentc = personel.getString("singleparent");
                            String noparentc = personel.getString("noparent");

                            if (singlechildc.equals("Yes")){
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_singlechild_yes);
                                rb.setChecked(true);
                            }
                            else {
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_singlechild_no);
                                rb.setChecked(true);
                            }

                            if (singleparentc.equals("Yes")){
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_singleparent_yes);
                                rb.setChecked(true);
                            }
                            else {
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_singleparent_no);
                                rb.setChecked(true);
                            }

                            if (noparentc.equals("Yes")){
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_guardian_yes);
                                rb.setChecked(true);
                            }
                            else {
                                RadioButton rb = (RadioButton)findViewById(R.id.radioButton_guardian_no);
                                rb.setChecked(true);
                            }

                            JSONObject guardian = response.getJSONObject("guardian");
                            guardian_firstname.setText(guardian.getString("name"));
                            guardian_qualification.setText(guardian.getString("education"));
                            guardian_occupation.setText(guardian.getString("occupation"));
                            guardian_officeaddress.setText(guardian.getString("phoneno"));
                            guardian_annualincome.setText(guardian.getString("annualinc"));
                            guardian_officeadd.setText(guardian.getString("oaddress"));
                            guardian_officephone.setText(guardian.getString("ophone"));

                            JSONObject father = response.getJSONObject("father");
                            father_firstname.setText(father.getString("name"));
                            father_qualification.setText(father.getString("education"));
                            father_occupation.setText(father.getString("occupation"));
                            father_officeaddress.setText(father.getString("phoneno"));
                            father_annualincome.setText(father.getString("annualinc"));
                            father_officeadd.setText(father.getString("oaddress"));
                            father_officephone.setText(father.getString("ophone"));

                            JSONObject mother = response.getJSONObject("mother");
                            mother_firstname.setText(mother.getString("name"));
                            mother_qualification.setText(mother.getString("education"));
                            mother_occupation.setText(mother.getString("occupation"));
                            mother_officeaddress.setText(mother.getString("phoneno"));
                            mother_annualincome.setText(mother.getString("annualinc"));
                            mother_officeadd.setText(mother.getString("oaddress"));
                            mother_officephone.setText(mother.getString("ophone"));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       // Toast.makeText(getBaseContext(),"response :" +response.toString(),Toast.LENGTH_LONG).show();


                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());
             //    Toast.makeText(getBaseContext(),"" +error,Toast.LENGTH_LONG).show();
                if ((error.toString()).equals("com.android.volley.AuthFailureError")){
                    showSimpleDialog1();
                    // startActivity(new Intent(getBaseContext(), SchoolBrowse2.class));
                }




                // hideProgressDialog();
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
        requestQueue.add(jsonObjReq123);
    }
    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please Login Again !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(viewShoppingCartIntent1);
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        SpinnerAdapter adapter = spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            String item = (String) adapter.getItem(position);
            if(item.equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }


    public void showSimpleDialog_mother_officeaddc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid mother's office address." + " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_father_officeaddc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's office address." + " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian_officeaddc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's office address." + " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }


    public void showSimpleDialog_singlechild() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please select the single child field.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_singleparent() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please select the single parent field." );
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please select the guardian field."
               );
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    public void showSimpleDialog_father_firstnamec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's name.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_father_qualificationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's qualification." +
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_father_occupationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's occupation."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_father_officeaddressc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's mobile number.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_father_annualincomec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's annual income."
                +
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    public void showSimpleDialog_guardian_firstnamec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's name.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian_qualificationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's qualification." +
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian_occupationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's occupation."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian_officeaddressc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's mobile number.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_guardian_annualincomec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid guardian's annual income."
                +
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    public void showSimpleDialog_mther_firstnamec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid mother's name.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_mther_qualificationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid father's qualification."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_mther_occupationc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid mother's occupation."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_mther_officeaddressc() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid mother's mobile number."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_mther_annualincomec() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid mother's annual income."+
                " Write nil if none.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void showSimpleDialog_fname() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid first name.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_lname() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid last name.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_category() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid category(General/SC/ST/OBC/Other).");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_address() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid address.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_email() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid email.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_mobile() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter a valid number.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_gender() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter gender.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog_dob() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please enter Date OF Birth.");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
                // startActivity(viewShoppingCartIntent1);

                builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    private void json(){
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        String dob = SpinnerValuedob3 + "-"+ SpinnerValuedob2 + "-" + SpinnerValuedob;


        prefsEditor.putString("firstname", child_firstname.getText().toString());
        prefsEditor.putString("lastname", child_lastname.getText().toString());
        prefsEditor.putString("dob", dob);
        prefsEditor.putString("id", bloodgroup.getText().toString());
        prefsEditor.putString("category", catagory.getText().toString());
        prefsEditor.putString("Address", address.getText().toString());
        prefsEditor.putString("gender", SpinnerValuegender);
        prefsEditor.putString("religion", SpinnerValuereligion);
        prefsEditor.putString("phoneno", disability.getText().toString());
        prefsEditor.putString("religion", SpinnerValuereligion);
        prefsEditor.putString("singlechild", singlechild_value);
        prefsEditor.putString("singleparent", singleparent_value);
        prefsEditor.putString("noparent", guardian_value);


        prefsEditor.putString("nameg", guardian_firstname.getText().toString());
        prefsEditor.putString("educationg", guardian_qualification.getText().toString());
        prefsEditor.putString("designationg", guardian_occupation.getText().toString());
        prefsEditor.putString("phonenog", guardian_officeaddress.getText().toString());
        prefsEditor.putString("annualincg", guardian_annualincome.getText().toString());
        prefsEditor.putString("officeaddressg", guardian_officeadd.getText().toString());
        prefsEditor.putString("officephoneg", guardian_officephone.getText().toString());

        prefsEditor.putString("namef", father_firstname.getText().toString());
        prefsEditor.putString("educationf", father_qualification.getText().toString());
        prefsEditor.putString("designationf", father_occupation.getText().toString());
        prefsEditor.putString("phonenof", father_officeaddress.getText().toString());
        prefsEditor.putString("annualincf", father_annualincome.getText().toString());
        prefsEditor.putString("officeaddressf", father_officeadd.getText().toString());
        prefsEditor.putString("officephonef", father_officephone.getText().toString());


        prefsEditor.putString("namem", mother_firstname.getText().toString());
        prefsEditor.putString("educationm", mother_qualification.getText().toString());
        prefsEditor.putString("designationm", mother_occupation.getText().toString());
        prefsEditor.putString("phonenom", mother_officeaddress.getText().toString());
        prefsEditor.putString("annualincm", mother_annualincome.getText().toString());
        prefsEditor.putString("officeaddressm", mother_officeadd.getText().toString());
        prefsEditor.putString("officephonem", mother_officephone.getText().toString());


        prefsEditor.commit();


    }

/* this function is NOT used*/
    private void registerUser() {

        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();

        try {

            JSONObject personal = new JSONObject();
            personal.put("firstname", myPrefs.getString("firstname","nil"));
            personal.put("lastname", myPrefs.getString("lastname","nil"));
            personal.put("dob",  myPrefs.getString("dob","nil"));
            personal.put("id",  myPrefs.getString("id","nil"));
            personal.put("category",  myPrefs.getString("category","nil"));
            personal.put("Address", myPrefs.getString("Address","nil"));
            personal.put("gender", myPrefs.getString("gender","nil"));
            personal.put("religion",  myPrefs.getString("religion","nil"));
            personal.put("phoneno", myPrefs.getString("phoneno","nil"));
            personal.put("photograph", myPrefs.getString("Photograph","nil"));
            personal.put("dobProof", myPrefs.getString("DOBPhoto","nil"));
            jsonobj.put("personal", personal);

            JSONObject father = new JSONObject();
            father.put("name", myPrefs.getString("namef","nil"));

            father.put("education",  myPrefs.getString("educationf","nil"));
            father.put("designation",  myPrefs.getString("designationf","nil"));
            father.put("phoneno",  myPrefs.getString("phonenof","nil"));
            father.put("annualinc",  myPrefs.getString("annualincf","nil"));
            father.put("photograph",  myPrefs.getString("FatherPhoto","nil"));
            jsonobj.put("father", father);

            JSONObject mother = new JSONObject();

            mother.put("name", myPrefs.getString("namem","nil"));
            mother.put("education", myPrefs.getString("educationm","nil"));
            mother.put("designation", myPrefs.getString("designationm","nil"));
            mother.put("phoneno",  myPrefs.getString("phonenom","nil"));
            mother.put("annualinc",  myPrefs.getString("annualincm","nil"));
            mother.put("photograph",  myPrefs.getString("MotherPhoto","nil"));
            jsonobj.put("mother", mother);


            JSONArray cafpart2 = new JSONArray();
            jsonobj.put("cafpart2",cafpart2);




            JSONObject cafpart3 = new JSONObject();
            cafpart3.put("transport","yes");
            cafpart3.put("dayboarding","no");
            JSONObject sib = new JSONObject();
            JSONObject alu = new JSONObject();
            cafpart3.put("sibling",sib);
            cafpart3.put("alumni",alu);

            jsonobj.put("cafpart3",cafpart3);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonobj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //   Log.d(TAG, response.toString());
                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                      //  Toast.makeText(getBaseContext(),"equal", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getBaseContext(),"error "+ error, Toast.LENGTH_LONG).show();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
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

        else {
            getMenuInflater().inflate(R.menu.settings_for_caf2, menu);
        }
        return true;
    }

    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(CAF.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please Fill All The Details !!!");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
               // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
               // startActivity(viewShoppingCartIntent1);

               builder.create().dismiss();
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    private void json12(){








        JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.PUT,
                insertUrl,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tag is : ", response.toString());


                        JSONObject personal = null;
                        try {
                            personal = response.getJSONObject("personal");
                            String firstname = personal.getString("firstname");
                            String lastname = personal.getString("lastname");
                            String dob = personal.getString("dob");
                            String id = personal.getString("id");
                            String category = personal.getString("category");
                            String Address = personal.getString("Address");
                            String gender = personal.getString("gender");
                            String religion = personal.getString("religion");
                            String phoneno = personal.getString("phoneno");
                            String photograph = personal.getString("photograph");
                            String dobProof = personal.getString("dobProof");

                            JSONObject father = response.getJSONObject("father");
                            String father_name = father.getString("name");
                            String father_education = father.getString("education");
                            String father_designation = father.getString("designation");
                            String father_phoneno = father.getString("phoneno");
                            String father_annualinc = father.getString("annualinc");
                            String father_photograph = father.getString("photograph");

                            JSONObject mother = response.getJSONObject("mother");
                            String mother_name = mother.getString("name");
                            String mother_education = mother.getString("education");
                            String mother_designation = mother.getString("designation");
                            String mother_phoneno = mother.getString("phoneno");
                            String mother_annualinc = mother.getString("annualinc");
                            String mother_photograph = mother.getString("photograph");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getBaseContext(),"" +response.toString(),Toast.LENGTH_LONG).show();
                        //  Toast.makeText(getBaseContext(),"error :" +response.toString(),Toast.LENGTH_LONG).show();
                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());
                Toast.makeText(getBaseContext(),"" +error,Toast.LENGTH_LONG).show();

                // hideProgressDialog();
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
        requestQueue.add(jsonObjReq1);
    }

}
