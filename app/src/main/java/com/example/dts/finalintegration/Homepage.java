package com.example.dts.finalintegration;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int STORAGE_PERMISSION_CODE = 123;SharedPreferences logout;
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logout = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        requestStoragePermission(); //showFileChooser();
        Button viewShoppingCart = (Button) findViewById(R.id.button_opencaf);
        viewShoppingCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent);
            }
        });

        ImageView im =(ImageView)findViewById(R.id.imageView2_opencaf);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent1);
            }
        });
        ImageView fb_icon = (ImageView)findViewById(R.id.imageView_facebook);
                fb_icon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/Ezyschooling-1750881605193728/");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
          }
               });

        ImageView insta_icon = (ImageView)findViewById(R.id.imageView_instagram);
        insta_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/ezyschooling/");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageView ln_icon = (ImageView)findViewById(R.id.imageView_ln);
        ln_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/company/13226358?trk=tyah&trkInfo=clickedVertical%3Acompany%2CentityType%3AentityHistoryName%2CclickedEntityId%3Acompany_13226358%2Cidx%3A1");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageView twt_icon = (ImageView)findViewById(R.id.imageView_tr);
        twt_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://twitter.com/EzySchooling");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            case R.id.action_login:
            {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_logout:
            {
                SharedPreferences.Editor prefsEditor = logout.edit();
                prefsEditor.putString("LoginStatus","false");

                prefsEditor.putString("Token","nil");
                prefsEditor.commit();
               // Intent intent = new Intent(getBaseContext(), MainActivity.class);
              //  startActivity(intent);
                showSimpleDialog_logout();
                return true;
            }

            case R.id.action_browseout:
            {
                Intent intent = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_myschools:
            {
                String status = logout.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), ShoppingCartActivity.class);
                    startActivity(viewShoppingCartIntent11);
                }

                else{
                  //  Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                   // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                  //  startActivity(viewShoppingCartIntent1);
                    showSimpleDialog();
                }
                return true;
            }
            case R.id.action_browse:
            {


                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), SchoolBrowse1.class);
                    startActivity(viewShoppingCartIntent11);

                return true;
            }
            case R.id.action_formout:
            {
                String status = logout.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), CAF.class);
                    startActivity(viewShoppingCartIntent11);
                }

                else{
                //    Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                 //   Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                 //   startActivity(viewShoppingCartIntent1);
                    showSimpleDialog();
                }
                return true;
            }
            case R.id.action_caf:
            {
                String status = logout.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), CAF.class);
                    startActivity(viewShoppingCartIntent11);
                }

                else{
                  //  Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                   // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                   // startActivity(viewShoppingCartIntent1);
                    showSimpleDialog();
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        String status = logout.getString("LoginStatus","null");
        if (status.equals("true")){

            getMenuInflater().inflate(R.menu.homepage, menu);
        }

        else {
            getMenuInflater().inflate(R.menu.homepage2, menu);
        }



        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            String status = logout.getString("LoginStatus","null");
            if (status.equals("true")){

                Intent viewShoppingCartIntent = new Intent(getBaseContext(), CAF.class);
                startActivity(viewShoppingCartIntent);
            }
            else{
               // Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
               // Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
              //  startActivity(viewShoppingCartIntent1);
                showSimpleDialog();
            }
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Homepage.this, Homepage.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Homepage.this, Homepage.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Homepage.this, OurTeam.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Homepage.this, Settings.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_schoolbrowse) {
            String status = logout.getString("LoginStatus","null");


                Intent viewShoppingCartIntent = new Intent(getBaseContext(), SchoolBrowse1.class);
                startActivity(viewShoppingCartIntent);

        }
        else if (id == R.id.nav_cart) {
            String status = logout.getString("LoginStatus","null");
            if (status.equals("true")){

                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
            }

            else{
               // Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                //Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                //startActivity(viewShoppingCartIntent1);
                showSimpleDialog();
            }
        }
       else if (id == R.id.nav_logout) {

            String status = logout.getString("LoginStatus","null");
            if (status.equals("true")){

                SharedPreferences.Editor prefsEditor = logout.edit();
                prefsEditor.putString("LoginStatus","false");

                prefsEditor.putString("Token","nil");
                prefsEditor.commit();
                // Intent intent = new Intent(Homepage.this, MainActivity.class);
                //  startActivity(intent);
                showSimpleDialog_logout();
            }

            if (status.equals("false")){
                 Toast.makeText(getBaseContext(),"You are already logged out !",Toast.LENGTH_LONG).show();
                //Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                //startActivity(viewShoppingCartIntent1);
              //  showSimpleDialog();
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
             Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
               // imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please Login To Proceed !!!");
        builder.setPositiveButton("OK !!!", new DialogInterface.OnClickListener() {
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

    public void showSimpleDialog_logout() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(viewShoppingCartIntent1);
            }

        });
               builder.setNegativeButton("No ", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), Homepage.class);
                       startActivity(viewShoppingCartIntent1);
                   }
               });

        // Create the AlertDialog object and return it
        builder.create().show();
    }


}
