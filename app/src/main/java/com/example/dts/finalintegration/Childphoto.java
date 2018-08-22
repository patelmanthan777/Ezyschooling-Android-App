package com.example.dts.finalintegration;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Childphoto extends AppCompatActivity implements View.OnClickListener{
    private TextView messageText;
    int go =0,go1=0;
    private Button uploadButton, btnselectpic,next;
      private Bitmap bitmap;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageview;
    private ProgressDialog dialog = null;SharedPreferences myPrefs;SharedPreferences login;
    RequestQueue requestQueue;
    String insertUrl = "http://ezyschooling.com/forms";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childphoto);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        myPrefs = this.getSharedPreferences("CAF",MODE_PRIVATE); requestStoragePermission();
        uploadButton = (Button)findViewById(R.id.uploadButton);
        btnselectpic = (Button)findViewById(R.id.button_selectpic);
        messageText  = (TextView)findViewById(R.id.messageText);
        imageview = (ImageView)findViewById(R.id.imageView_pic);  login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);

        next = (Button)findViewById(R.id.next);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        next.setOnClickListener(this);
        btnselectpic.setOnClickListener(this);
        uploadButton.setOnClickListener(this);

        //  dialog = new ProgressDialog(this);
        //   dialog.setMessage("Uploading Image...");
        //  dialog.setCancelable(false);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_selectpic:
              //  Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              //  startActivityForResult(intent, 100);
                go =1;
                showFileChooser();
                break;
            case R.id.uploadButton:
                Bitmap image = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("ChildPhoto",encodedImage);
                prefsEditor.commit();
                go1=1;
                break;

            case R.id.next :

                if (go==1){
                    if (go1==1) {
                        startActivity(new Intent(getBaseContext(), DOBphoto.class));
                    }
                    else showSimpleDialog1();
                }
                else showSimpleDialog();
        }
    }

    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Childphoto.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please click on save to proceed  !!!");
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
    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Childphoto.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please Select the picture and click on save to proceed  !!!");
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

