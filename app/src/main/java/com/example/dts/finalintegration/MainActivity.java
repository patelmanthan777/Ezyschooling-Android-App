package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.editText1811) EditText editTextEmail;
    @InjectView(R.id.editText120) EditText editTextPassword;
    @InjectView(R.id.button_login) Button buttonLogin;




    String token = null;String Success_state = null,fname = null, lname = null;

    String Token1;
    RequestQueue requestQueue;
    SharedPreferences login;
    String insertUrl = "http://ezyschooling.com/users/login";

    private boolean loggedIn = false;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
            String status = login.getString("LoginStatus","false");
        if (status.equals("true")){

            Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), Homepage.class);
            SharedPreferences.Editor prefsEditor = login.edit();
            prefsEditor.putString("LoginStatus","true");


            prefsEditor.commit();
            startActivity(viewShoppingCartIntent1);
        }

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonLogin.setOnClickListener(this);
    }

/* This function is not used*/
    private void login(){

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();

        try {
            jsonobj.put("email", email);
            jsonobj.put("password",password);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonobj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        JSONObject jb = null;
                        try {
                            jb = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            String Success_state = jb.getString("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String  token = null;
                        try {
                            token = jb.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getBaseContext(), " TOk is : " + token, Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                  Log.d("Error: " , error.toString());
                // hideProgressDialog();
                Toast.makeText(getBaseContext(),"error : "+ error, Toast.LENGTH_LONG).show();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
               // headers.put("x-access-token",Token);
                return headers;
            }



        };

        // Adding request to request queue
        requestQueue.add(jsonObjReq);




    }

    private void userLogin() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

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
                        JSONObject jb = null;
                        try {
                            jb = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {
                            Success_state = jb.getString("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            token = jb.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {
                            fname = jb.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }






                        if (Success_state.equals("true")){
                            startActivity(new Intent(getBaseContext(), Homepage.class));}
                        if (Success_state.equals("false")) {
                           // Toast.makeText(getBaseContext(),"Invalid email or password"+token, Toast.LENGTH_LONG).show();
                            showSimpleDialog1();
                        }
                      //  Toast.makeText(getBaseContext(), " TOk is : " + Success_state, Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor prefsEditor = login.edit();
                        prefsEditor.putString("LoginStatus",Success_state);
                       prefsEditor.putString("Name",fname);
                        prefsEditor.putString("Token",token);
                        prefsEditor.commit();
                        Log.d("RES is :", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };

     //   RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        //Calling the login function
        // login();
        switch (v.getId()) {
            case R.id.button_login:
                userLogin();

        break;

        }
    }
    public void Open_Main_Signup (View view){
        startActivity(new Intent(this, Signup.class));
    }
    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Invalid email or password !!!"+"\n"+"Please try again.");
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here

                this.finish();
                return true;

            case R.id.action_home:
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
            case R.id.action_browse:
            {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);
                return true;
            }






            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainactivity, menu);
        return true;
    }
}


