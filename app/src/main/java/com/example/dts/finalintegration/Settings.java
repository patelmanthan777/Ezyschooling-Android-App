package com.example.dts.finalintegration;

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
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    RequestQueue requestQueue;    private EditText editTextEmail;
    private EditText editTextMessage;    private EditText editTextName;
    TextView name;
SharedPreferences login;
    String insertUrl = "http://ezyschooling.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        name = (TextView)findViewById(R.id.textView_name);
        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextMessage = (EditText) findViewById(R.id.editText_message);
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);

     Button  buttonSubmit = (Button)findViewById(R.id.button_submit);

        /*String status = login.getString("LoginStatus","false");
        if (status.equals("false")){
            Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
            Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
            startActivity(viewShoppingCartIntent1);
        }*/


        String name1= login.getString("Name","User");
        String caps_name = name1.toUpperCase();
        name.setText(caps_name);
        //Adding click listener
        buttonSubmit.setOnClickListener(this);
    }

    private void login(){

       final String email = editTextEmail.getText().toString().trim();
       final String message = editTextMessage.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();

        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();

        try {
           jsonobj.put("name", name);
            jsonobj.put("email",email);
            jsonobj.put("message",message);



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
                      //  Toast.makeText(getBaseContext(),"res :" + response, Toast.LENGTH_LONG).show();
                       // String res = response.toString();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hideProgressDialog();
                Toast.makeText(getBaseContext(),"not equal"+ error, Toast.LENGTH_LONG).show();
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
        final String email = editTextEmail.getText().toString().trim();
        final String message = editTextMessage.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();

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


                       /* try {
                            Success_state = jb.getString("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            token = jb.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                      /*  try {
                            fname = jb.getString("fname");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            lname = jb.getString("lname");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/




                      /*  if (Success_state.equals("true")){
                            startActivity(new Intent(getBaseContext(), Homepage.class));}
                        if (Success_state.equals("false")) {
                            Toast.makeText(getBaseContext(),"Invalid email or password", Toast.LENGTH_LONG).show();
                        }
                        //  Toast.makeText(getBaseContext(), " TOk is : " + Success_state, Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor prefsEditor = login.edit();
                        prefsEditor.putString("LoginStatus",Success_state);
                        //  prefsEditor.putString("Name",fname +" " +lname);
                        prefsEditor.putString("Token",token);
                        prefsEditor.commit();*/
                        Log.d("RES is :", response);
                        Toast.makeText(Settings.this,"FeedBack Submitted Successfully!",Toast.LENGTH_LONG ).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Toast.makeText(Settings.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",email);
                map.put("name",name);
                map.put("message",message);
                return map;
            }
        };

        //   RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        //Calling the login function
        //login();
        userLogin();
       // startActivity(new Intent(this, Homepage.class));
     /*  if (a==1){startActivity(new Intent(this, Homepage.class));}
        if (a==2){
           Toast.makeText(getBaseContext()," inavlid", Toast.LENGTH_LONG).show();
       }*/
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
