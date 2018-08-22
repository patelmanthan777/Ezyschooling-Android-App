package com.example.dts.finalintegration;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Motherphoto extends AppCompatActivity implements View.OnClickListener{
    private TextView messageText;
    private Button uploadButton, btnselectpic,next;
    int go =0,go1=0;
String Success_state=null,msg;

    private Bitmap bitmap;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private int PICK_IMAGE_REQUEST = 1;

    private ImageView imageview;SharedPreferences login;
    private ProgressDialog dialog = null;SharedPreferences myPrefs;
    RequestQueue requestQueue;
    String insertUrl = "http://ezyschooling.com:3002/forms";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motherphoto);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        myPrefs = this.getSharedPreferences("CAF",MODE_PRIVATE); login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        uploadButton = (Button)findViewById(R.id.uploadButton);
        btnselectpic = (Button)findViewById(R.id.button_selectpic);
        messageText  = (TextView)findViewById(R.id.messageText);
        imageview = (ImageView)findViewById(R.id.imageView_pic);
        SharedPreferences.Editor prefsEditor = login.edit();
        prefsEditor.putString("Status_form1","no");
        prefsEditor.commit();
        next = (Button)findViewById(R.id.next);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        next.setOnClickListener(this); requestStoragePermission();
        btnselectpic.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_selectpic:

                showFileChooser();
                go = 1;
                break;
            case R.id.uploadButton:


go1=1;

                Bitmap image = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
               ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
               String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("MotherPhoto",encodedImage);
                prefsEditor.commit();


                Log.d("Base is-",encodedImage);


                break;

            case R.id.next :

                if (go==1){
                    if (go1==1) { json();
                        //Toast.makeText(getBaseContext(),"yes" ,Toast.LENGTH_LONG).show();
                    }
                    else showSimpleDialog1111();
                }
                else showSimpleDialog111();

                Log.d("Base is-","data:image/jpeg;base64,"+  myPrefs.getString("MotherPhoto","nil"));
        }
    }



    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Motherphoto.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Form Submitted Successfully");
        builder.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), SchoolBrowse2.class);
                startActivity(viewShoppingCartIntent1);
            }
        });


        // Create the AlertDialog object and return it
        builder.create().show();
    }
    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Motherphoto.this);
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

    private void json(){


        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();

        // adding some keys
        try {
            JSONObject personal = new JSONObject();
            personal.put("firstname", myPrefs.getString("firstname","nil"));
            personal.put("lastname", myPrefs.getString("lastname","nil"));
            // personal.put("lastname","data:image/jpeg;base64,"+ myPrefs.getString("MotherPhoto","nil"));
            personal.put("dob",  myPrefs.getString("dob","nil"));
            personal.put("id",  myPrefs.getString("id","nil"));
            personal.put("category",  myPrefs.getString("category","nil"));
            personal.put("Address", myPrefs.getString("Address","nil"));
            personal.put("gender", myPrefs.getString("gender","nil"));
            personal.put("religion",  myPrefs.getString("religion","nil"));
            personal.put("phoneno", myPrefs.getString("phoneno","nil"));
            personal.put("singlechild", myPrefs.getString("singlechild","nil"));
            personal.put("singleparent",  myPrefs.getString("singleparent","nil"));
            personal.put("noparent", myPrefs.getString("noparent","nil"));
            personal.put("photograph", "data:image/jpeg;base64,"+myPrefs.getString("Photograph","nil"));
            personal.put("dobProof","data:image/jpeg;base64,"+ myPrefs.getString("DOBPhoto","nil"));
            // personal.put("photograph", "");
            // personal.put("dobProof", "");
            jsonobj.put("personal", personal);

            JSONObject guardian = new JSONObject();

            guardian.put("name", myPrefs.getString("nameg","nil"));
            guardian.put("education",  myPrefs.getString("educationg","nil"));
            guardian.put("occupation",  myPrefs.getString("designationg","nil"));
            guardian.put("phoneno",  myPrefs.getString("phonenog","nil"));
            guardian.put("annualinc",  myPrefs.getString("annualincg","nil"));
            guardian.put("oaddress",  myPrefs.getString("officeaddressg","nil"));
            guardian.put("ophone",  myPrefs.getString("officephoneg","nil"));
            guardian.put("photograph", "data:image/jpeg;base64,"+  myPrefs.getString("FatherPhoto","nil"));
            // father.put("photograph", "");
            jsonobj.put("guardian", guardian);
            // lets add some headers (nested headers)
            JSONObject father = new JSONObject();

            father.put("name", myPrefs.getString("namef","nil"));
            father.put("education",  myPrefs.getString("educationf","nil"));
            father.put("occupation",  myPrefs.getString("designationf","nil"));
            father.put("phoneno",  myPrefs.getString("phonenof","nil"));
            father.put("annualinc",  myPrefs.getString("annualincf","nil"));
            father.put("oaddress",  myPrefs.getString("officeaddressf","nil"));
            father.put("ophone",  myPrefs.getString("officephonef","nil"));
            father.put("photograph", "data:image/jpeg;base64,"+  myPrefs.getString("FatherPhoto","nil"));
            // father.put("photograph", "");
            jsonobj.put("father", father);

            JSONObject mother = new JSONObject();

            mother.put("name", myPrefs.getString("namem","nil"));
            mother.put("education", myPrefs.getString("educationm","nil"));
            mother.put("occupation", myPrefs.getString("designationm","nil"));
            mother.put("phoneno",  myPrefs.getString("phonenom","nil"));
            mother.put("annualinc",  myPrefs.getString("annualincm","nil"));
            mother.put("oaddress",  myPrefs.getString("officeaddressm","nil"));
            mother.put("ophone",  myPrefs.getString("officephonem","nil"));
            //  mother.put("photograph","data:image/jpeg;base64,"+ myPrefs.getString("MotherPhoto","nil"));
            mother.put("photograph","data:image/jpeg;base64,"+ myPrefs.getString("MotherPhoto","nil"));
            jsonobj.put("mother", mother);


            JSONArray cafpart2 = new JSONArray();
            //   String[] caf = new String[2];
            jsonobj.put("caf2",cafpart2);






        } catch (JSONException e) {
            e.printStackTrace();
        }





        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonobj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tag is : ", response.toString());

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
                            msg = jb.getString("error");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (Success_state.equals("true")){
                            SharedPreferences.Editor prefsEditor = login.edit();
                            prefsEditor.putString("Status_form1","yes1");
                            prefsEditor.commit();
                            showSimpleDialog();
                            //startActivity(new Intent(getBaseContext(), SchoolBrowse2.class));
                        }





                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());

                if ((error.toString()).equals("com.android.volley.AuthFailureError")){
                    showSimpleDialog1();

                }
                if ((error.toString()).equals("com.android.volley.TimeoutError")){
                    SharedPreferences.Editor prefsEditor = login.edit();
                    prefsEditor.putString("Status_form1","yes1");
                    prefsEditor.commit();
                    showSimpleDialog();

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
        requestQueue.add(jsonObjReq);
    }




    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void showSimpleDialog111() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Motherphoto.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please Select the picture and click on save to proceed.");
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
    public void showSimpleDialog1111() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Motherphoto.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please click on save to proceed.");
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

        else{
            getMenuInflater().inflate(R.menu.settings_for_caf2, menu);
        }
        return true;
    }

}
