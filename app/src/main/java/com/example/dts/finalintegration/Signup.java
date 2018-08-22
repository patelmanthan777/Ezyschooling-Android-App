package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements View.OnClickListener {
   // private EditText editTextFirstName;
    @InjectView(R.id.editText1311)
   EditText editTextFirstName;
    @InjectView(R.id.editText1411) EditText editTextLastname;
    @InjectView(R.id.editText1511) EditText editTextEmail;
    @InjectView(R.id.editText1611) EditText editTextMobile;
    @InjectView(R.id.editText1811) EditText editTextPassword; String Success_state =null;
    @InjectView(R.id.editText1911) EditText editTextConfirmPassword;
   // private EditText editTextLastname;
   // private EditText editTextPassword;
   // private EditText editTextEmail;
   // private EditText editTextUsername;
  //  private EditText editTextConfirmPassword;
    SharedPreferences signup;
  //  private EditText editTextMobile;
    RequestQueue requestQueue;
    String insertUrl = "http://ezyschooling.com/verifymail";
    private Button buttonRegister;
int a =0;
    private static final String REGISTER_URL = "http://192.168.4.163/ezyschoolingregisterdb.php";
    Spinner spinner5;
    String[] SPINNERVALUES5= {"<--select-->","Male","Female"};
    String SpinnerValue5;
    private static Button btn;
    EditText passw1;
    EditText passw2;
    EditText field1;
    EditText field2;
    EditText field3;
    EditText field4;
    EditText field5;
    EditText field6;
    Spinner spinnerdob;
    String[] SPINNERVALUESdob= {"DD","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String SpinnerValuedob;
    Spinner spinnerdob2;
    String[] SPINNERVALUESdob2= {"MM","1","2","3","4","5","6","7","8","9","10","11","12"};
    String SpinnerValuedob2;
    Spinner spinnerdob3;
    String[] SPINNERVALUESdob3= {"YY","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
    String SpinnerValuedob3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonRegister = (Button) findViewById(R.id.button_proceedFromSignUp11);
        ButterKnife.inject(this);
        signup = this.getSharedPreferences("UserInfo",MODE_PRIVATE);
        buttonRegister.setOnClickListener(this);

        spinnerdob = (Spinner)findViewById(R.id.spinnerdob);



        ArrayAdapter<String> adapterdob= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob);
        spinnerdob.setAdapter(adapterdob);
        spinnerdob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob=(String)spinnerdob.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerdob2 = (Spinner)findViewById(R.id.spinnerdob2);



        ArrayAdapter<String> adapterdob2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob2);
        spinnerdob2.setAdapter(adapterdob2);
        spinnerdob2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob2=(String)spinnerdob2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerdob3 = (Spinner)findViewById(R.id.spinnerdob3);



        ArrayAdapter<String> adapterdob3= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SPINNERVALUESdob3);
        spinnerdob3.setAdapter(adapterdob3);
        spinnerdob3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValuedob3=(String)spinnerdob3.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        spinner5 = (Spinner)findViewById(R.id.spinner5);

        ArrayAdapter<String> adapter5= new ArrayAdapter<String>(Signup.this,android.R.layout.simple_list_item_1, SPINNERVALUES5);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue5=(String)spinner5.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        final EditText passw1= (EditText) findViewById(R.id.editText1811);
        final EditText passw2= (EditText) findViewById(R.id.editText1911);
        String firstname = editTextFirstName.getText().toString().trim().toLowerCase();
        String lastname = editTextLastname.getText().toString().trim().toLowerCase();

        String mobile = editTextMobile.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString();


         if (firstname.length() <3){
            showSimpleDialog_fname();
        }


        else if (lastname.length() <3){
            showSimpleDialog_lname();
        }
         else if (!isValidEmail(email)){
             showSimpleDialog_email();
         }else if (mobile.length()!=10){
            showSimpleDialog_mobile();
        } else if (password.length() <6){
            showSimpleDialog_password();
        }
         else if(SpinnerValuedob.matches("DD")|| SpinnerValuedob2.matches("MM")|| SpinnerValuedob3.matches("YY")
                 ){
             showSimpleDialog_dob();
         }
         else if (SpinnerValue5.matches("<--select-->")){
             showSimpleDialog_gender();
         }
        else {
            if (passw1.getText().toString().equals(passw2.getText().toString()) && v == buttonRegister){
                registerUser();


            }
            else {
                showSimpleDialog_conpassword();

            }

        }}

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void showSimpleDialog_fname() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
    public void showSimpleDialog_conpassword() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Confirm password and password doesn't match.");
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
    public void showSimpleDialog_email() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
    public void showSimpleDialog_password() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Password length must be greater than or equal to 6 characters.");
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
    private void registerUser() {
        String firstname = editTextFirstName.getText().toString().trim().toLowerCase();
        String lastname = editTextLastname.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
       // SharedPreferences.Editor prefsEditor = signup.edit();
      //  prefsEditor.putString("Name",firstname + " "+lastname);

     //   prefsEditor.commit();
        String mobile = editTextMobile.getText().toString().trim().toLowerCase();
        //int mob = Integer.parseInt(mobile);
        //String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        //  String confirmpassword = editTextConfirmPassword.getText().toString().trim().toLowerCase();
        String dob = SpinnerValuedob + "/"+ SpinnerValuedob2 + "/" + SpinnerValuedob3;
        String gender = SpinnerValue5;
        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();

        try {
         //   jsonobj.put("username", email);
            jsonobj.put("pw",password);
            jsonobj.put("fname", firstname);
            jsonobj.put("lname",lastname);
            jsonobj.put("mobile", mobile);
            jsonobj.put("email",email);jsonobj.put("gender",gender);
            jsonobj.put("dobmonth",SpinnerValuedob2); jsonobj.put("dobyear",SpinnerValuedob3); jsonobj.put("dobday",SpinnerValuedob);
            jsonobj.put("type","register");


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

                        JSONObject jb = null;
                        try {
                            jb = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {
                            Success_state = jb.getString("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getBaseContext(),Success_state, Toast.LENGTH_LONG).show();


                     //   else showSimpleDialog1();
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
           //    Toast.makeText(getBaseContext(),"error "+ error, Toast.LENGTH_LONG).show();
                if ((error.toString()).equals("com.android.volley.TimeoutError")){
                    showSimpleDialog1();

               }
                if ((error.toString()).equals("com.android.volley.ServeError")){
                    Toast.makeText(getBaseContext(),"Please fill all details.", Toast.LENGTH_LONG).show();

                }


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

    private void userLogin() {
        final String firstname = editTextFirstName.getText().toString().trim().toLowerCase();
        final String lastname = editTextLastname.getText().toString().trim().toLowerCase();
        final String email = editTextEmail.getText().toString().trim().toLowerCase();
        final String mobile = editTextMobile.getText().toString().trim().toLowerCase();
      //  int mob = Integer.parseInt(mobile);
      //  String username = editTextUsername.getText().toString().trim().toLowerCase();
        final String password = editTextPassword.getText().toString().trim().toLowerCase();
        //  String confirmpassword = editTextConfirmPassword.getText().toString().trim().toLowerCase();
        String dob = SpinnerValuedob + "/"+ SpinnerValuedob2 + "/" + SpinnerValuedob3;
        final String gender = SpinnerValue5;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       /* if(response.trim().equals("success")){
                           // openProfile();
                        }else{
                           // Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }*/
                        //Token1 = response;
                        Toast.makeText(getBaseContext()," TOk is : " + response, Toast.LENGTH_LONG).show();
                        Log.d("RES is :", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Signup.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",email);
                map.put("pw",password);
                map.put("fname",firstname);
                map.put("lname",lastname);
                map.put("mobile",mobile);
                map.put("gender",gender);
                map.put("dobmonth",SpinnerValuedob2);
                map.put("dobyear",SpinnerValuedob3); map.put("dobday",SpinnerValuedob); map.put("type","register");



                return map;
            }
        };

        //   RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // public void ProceedFromSignUp11 (View view){
    //     startActivity(new Intent(this, Homepage.class));
    // }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), Homepage.class);
                startActivity(viewShoppingCartIntent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setCancelable(false);
        builder.setTitle("Email Verification");
        builder.setMessage("Please check your mail to verify your account and login here again.  !!!");
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
}
