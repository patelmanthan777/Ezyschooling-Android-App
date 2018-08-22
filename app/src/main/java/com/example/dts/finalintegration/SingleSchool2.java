package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleSchool2 extends AppCompatActivity implements LoadImageTask.Listener {
    String Name,Coverpic ;ImageView imgflag; private NetworkImageView imageView;
    private ImageLoader imageLoader;

    String ADDRESS ;SharedPreferences login;
    String DESCRIPTION ;
    String EZYSCHOOLINGRATING_stars,EZYSCHOOLINGRATING_response ;
    String ACHIEVEMENTS1,ACHIEVEMENTS2,ACHIEVEMENTS3;
    String CONTACTDETAILS_mail,CONTACTDETAILS_phone;
    String FEESTRUCTURE ;
    String SchoolTimings,Price,DaysLEfT,Board,Logo,FirstDate,LastDate,SpecialCriteria,LimitOnForms,Teachers ;
    String Type_val,Category,Accomod,AC,Region,Subjects,LAnguages,Student,Transport;
    String SCHOOLID,sec, schoollid ;

    String insertUrl = "http://www.ezyschooling.com/myschools/add";
    RequestQueue requestQueue;
  //  ImageLoader imageLoader = new ImageLoader(this);
    String SCHOOLIMAGE1 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg";
    String SCHOOLIMAGE2 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a2.jpg"; String SCHOOLIMAGE3 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg"; String SCHOOLIMAGE4 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg";
    String SCHOOLIMAGE5 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg"; String SCHOOLIMAGE6 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a2.jpg"; String SCHOOLIMAGE7 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg";  String SCHOOLIMAGE8 = "http://ezyschoolingproject-86203.onmodulus.net/public/schoolpics/a1.jpg";
    private ArrayList<String>  Fac,Sports,Labs,subject,lang;String image1,image2,image3,image4,image5,image6,image7,image8,image9,
            image10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_school2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        final List<Product> cart = ShoppingCartHelper.getCart();
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        Intent i = getIntent();
        // Get the result of rank
        Name = i.getStringExtra("Name");ADDRESS = i.getStringExtra("ADDRESS");schoollid = i.getStringExtra("SCHOOLID");
        DESCRIPTION = i.getStringExtra("DESCRIPTION");EZYSCHOOLINGRATING_stars = i.getStringExtra("EZYSCHOOLINGRATING_1");
        EZYSCHOOLINGRATING_response = i.getStringExtra("EZYSCHOOLINGRATING_2");ACHIEVEMENTS1 = i.getStringExtra("ACHIEVEMENTS_1");
        ACHIEVEMENTS2 = i.getStringExtra("ACHIEVEMENTS_2");ACHIEVEMENTS3 = i.getStringExtra("ACHIEVEMENTS_3");
        FEESTRUCTURE = i.getStringExtra("FeesSchool");
        SchoolTimings = i.getStringExtra("SchoolTiming");sec = i.getStringExtra("sec");
        CONTACTDETAILS_mail = i.getStringExtra("CONTACTDETAILS_mail");CONTACTDETAILS_phone = i.getStringExtra("CONTACTDETAILS_phone");
        SCHOOLID = i.getStringExtra("SCHOOLID");Coverpic = i.getStringExtra("Coverpic");
        Price = i.getStringExtra("Price");DaysLEfT = i.getStringExtra("Daysleft");
        Board = i.getStringExtra("Board");Logo = i.getStringExtra("Logo");
        FirstDate = i.getStringExtra("FirstDate");LastDate = i.getStringExtra("LastDate");
        SpecialCriteria = i.getStringExtra("SpecialCriteria");LimitOnForms = i.getStringExtra("LimitOnForms");
        Teachers = i.getStringExtra("Teachers");Type_val = i.getStringExtra("Type");
        Category = i.getStringExtra("Category");Accomod = i.getStringExtra("Accomod");
        AC = i.getStringExtra("AC");Region = i.getStringExtra("Region");
        Sports = (ArrayList<String>) getIntent().getSerializableExtra("SPORTS");
       Student = i.getStringExtra("Student");
        Transport = i.getStringExtra("Transport");Fac = (ArrayList<String>) getIntent().getSerializableExtra("Facilities");
        image1= i.getStringExtra("Image1"); image2= i.getStringExtra("Image2"); image3= i.getStringExtra("Image3");
        image4= i.getStringExtra("Image4"); image5= i.getStringExtra("Image5"); image6= i.getStringExtra("Image6");
        image7= i.getStringExtra("Image7"); image8= i.getStringExtra("Image8"); image9= i.getStringExtra("Image9");
        image10= i.getStringExtra("Image10");
        Labs= (ArrayList<String>) getIntent().getSerializableExtra("Labs");
        lang= (ArrayList<String>) getIntent().getSerializableExtra("foreeign_lang");
        subject= (ArrayList<String>) getIntent().getSerializableExtra("SUBJECTS");
        // Get the result of flag
        // SCHOOLID = i.getStringExtra("SCHOOLID");
        //    BRANCH = i.getStringExtra("BRANCH");

        // Locate the TextViews in singleitemview.xml
        TextView SCHOOLNAME1 = (TextView) findViewById(R.id.textView_schoolname);
        TextView ADDRESS1 = (TextView) findViewById(R.id.textView_address);
        TextView DESCRIPTION1 = (TextView) findViewById(R.id.textView_description);
        TextView Achiebemnts = (TextView) findViewById(R.id.textView_achievements);
        TextView rating_comments = (TextView) findViewById(R.id.textView_rating_comments);
        TextView section = (TextView) findViewById(R.id.textView_sections);
        TextView SCHOOLID1 = (TextView) findViewById(R.id.textView_uniqueid);
        TextView FEESTRUCTURE1 = (TextView) findViewById(R.id.textView_feestructure);

        TextView schooltimings = (TextView) findViewById(R.id.textView_school_timings);

        TextView CONTACTDETAILS1 = (TextView) findViewById(R.id.textView_contact_details);

        RatingBar schoolrating = (RatingBar)findViewById(R.id.ratingBar_schoolprofile);
        imageView = (NetworkImageView) findViewById(R.id.imageView_school_coverpic);

       // new LoadImageTask(this).execute("http://www.ezyschooling.com/public/schoolpics/ryan2.png");


        TextView Facilities1 = (TextView) findViewById(R.id.textView_facilities);
        TextView Price1 = (TextView) findViewById(R.id.textView_price);
        TextView DaysLEfT1 = (TextView) findViewById(R.id.textView_days_left);
       TextView FirstDate1 = (TextView) findViewById(R.id.textView_FirstDAte);
        TextView LastDate1 = (TextView) findViewById(R.id.textView_LAstDate);TextView SpecialCriteria1 = (TextView) findViewById(R.id.textView_Special_Criteria);
        TextView LimitOnForms1 = (TextView) findViewById(R.id.textView_limitonforms);TextView Teachers1= (TextView) findViewById(R.id.textView_no_ofteachers);
        TextView Type_val1 = (TextView) findViewById(R.id.textView_type);TextView Category1= (TextView) findViewById(R.id.textView_category);
        TextView Accomod1 = (TextView) findViewById(R.id.textView_accomod);TextView AC1 = (TextView) findViewById(R.id.textView_AC);
        TextView Region1 = (TextView) findViewById(R.id.textView_region);TextView Sports1 = (TextView) findViewById(R.id.textView_sports);
        TextView Subjects1 = (TextView) findViewById(R.id.textView_subjects);TextView LAnguages1 = (TextView) findViewById(R.id.textView_languages);
        TextView Student1 = (TextView) findViewById(R.id.textView_student_perclass);TextView Transport1 = (TextView) findViewById(R.id.textView_transport);
        TextView lab = (TextView) findViewById(R.id.textView_labs);
        Price1.setText(Price); DaysLEfT1.setText(DaysLEfT);  FirstDate1.setText(FirstDate);
        LastDate1.setText(LastDate); SpecialCriteria1.setText(SpecialCriteria); LimitOnForms1.setText(LimitOnForms); Teachers1.setText(Teachers);
        Type_val1.setText(Type_val); Category1.setText(Category); Accomod1.setText(Accomod); AC1.setText(AC);
        Region1.setText(Region);  Subjects1.setText(Subjects); LAnguages1.setText(LAnguages);
        Student1.setText(Student); Transport1.setText(Transport); rating_comments.setText(EZYSCHOOLINGRATING_response);
        section.setText(sec);
        // Facilities1.setText(Fac);
         for(int k=0;k<Fac.size();k++){
    Facilities1.append(k+1 + ". " + Fac.get(k) + "\n");

                }
        Sports1.setText(Sports.get(0));
        for(int k=1;k<Sports.size();k++){
            Sports1.append(  " , "+ Sports.get(k));

        }
        lab.setText(Labs.get(0));
        for(int k=1;k<Labs.size();k++){
            lab.append(" , "+Labs.get(k)  );

        }
        Subjects1.setText(subject.get(0));
        for(int k=1;k<subject.size();k++){
            Subjects1.append(  " , "+ subject.get(k));

        }
        LAnguages1.setText(lang.get(0));
        for(int k=1;k<lang.size();k++){
            LAnguages1.append(  " , "+ lang.get(k));

        }
      //  Sports1.setText(Sports);

        TextView tap = (TextView) findViewById(R.id.textView_taptoview_more);
        tap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startGalleryActivity();
            }
        });

        SCHOOLNAME1.setText(Name);
        ADDRESS1.setText(ADDRESS);
        DESCRIPTION1.setText(DESCRIPTION);
        schoolrating.setRating(Float.parseFloat(EZYSCHOOLINGRATING_stars));
        Achiebemnts.setText("1. "+ ACHIEVEMENTS1 + "\n" + "\n" + "2. "+ACHIEVEMENTS2 +"\n" + "\n"+ "3. "+ ACHIEVEMENTS3);
        FEESTRUCTURE1.setText(FEESTRUCTURE);

        schooltimings.setText(SchoolTimings);

        CONTACTDETAILS1.setText(CONTACTDETAILS_mail +"\n"+CONTACTDETAILS_phone);

      //  Coverpic = "http://www.ezyschooling.com/public/schoolpics/ryan2.png";
       String Coverpicset =  Coverpic ;
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(Coverpicset, ImageLoader.getImageListener(imageView,
                R.drawable.loading1, R.drawable.loading1));
        imageView.setImageUrl(Coverpicset, imageLoader);

     /*   Glide.with(getBaseContext())
                .load(Coverpicset
                )
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        //  imageView.setImage(ImageSource.bitmap(bitmap));
                        imgflag.setImageBitmap(bitmap);
                      //  thumbView.setImageBitmap(bitmap);
                    }
                });*/
      //  imageLoader.DisplayImage(Coverpicset, imgflag);



        final Product selectedproduct = new Product (Name,SCHOOLID,Coverpicset,ADDRESS);

        Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String status = login.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                    startActivity(viewShoppingCartIntent);
                }

                if (status.equals("false")){
                    Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                    Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(viewShoppingCartIntent1);
                }
            }
        });


       /* String status = login.getString("LoginStatus","null");
        if (status.equals("true")){

            Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
            startActivity(viewShoppingCartIntent);
        }

        if (status.equals("false")){
            Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
            Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
            startActivity(viewShoppingCartIntent1);
        }*/


        final Button addToCartButton = (Button) findViewById(R.id.button_addtocart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             //   Toast.makeText(getBaseContext(),"id: "+schoollid,Toast.LENGTH_LONG).show();
                String status123 = login.getString("Status_form1","null");
                // Toast.makeText(getBaseContext()," "+ status123,Toast.LENGTH_LONG).show();
                if ( status123.equals("yes1")) {

                    String status = login.getString("LoginStatus", "null");
                    //   String namecou = selectedproduct.country;
                    if (status.equals("true")) {
                        json();
                        cart.add(selectedproduct);
                        addToCartButton.setText("School added");


                    }

                    if (status.equals("false")) {
                        Toast.makeText(getBaseContext(), "Login To Add To MySchools", Toast.LENGTH_LONG).show();
                        Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(viewShoppingCartIntent1);
                    }
                }
                else showSimpleDialog();
            }
        });

       /* for(int m=0; m<cart.size(); m++) {
            Object obj = cart.get(m);
            Product cur = (Product) obj;
            if((selectedproduct.Name).equals(cur.Name)) {
                addToCartButton.setEnabled(false);
                addToCartButton.setText("Item in MySchools");
                //Toast.makeText(this,"Item already in cart, pls remove from cart",Toast.LENGTH_LONG).show();
            }

        }*/
    }

  /*  public void viewpics (View view){
        startGalleryActivity();

    }*/
    public void startGalleryActivity() {
        ArrayList<String> images = new ArrayList<String>();

        images.add( image1);images.add(image2);images.add( image3);
        images.add( image4);images.add(image5);images.add( image6);
        images.add( image7);images.add(image8);images.add( image9);
        images.add( image10);
        Intent intent = new Intent(SingleSchool2.this, GalleryActivity.class);
        intent.putStringArrayListExtra(GalleryActivity.EXTRA_NAME, images);
        startActivity(intent);
    }

    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(SingleSchool2.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill Common Admission Form to add to MySchools.");
        builder.setPositiveButton("OK !!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //
                Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), CAF.class);
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

            jsonobj.put("id", schoollid);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                insertUrl, jsonobj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tag is : ", response.toString());
                        Toast.makeText(getBaseContext(), "School added in MySchools successfully", Toast.LENGTH_LONG).show();
                        //  Toast.makeText(getBaseContext(),"response :" +response.toString(),Toast.LENGTH_LONG).show();
                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());
             //   Toast.makeText(getBaseContext(),"" +error,Toast.LENGTH_LONG).show();

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
        requestQueue.add(jsonObjReq);
    }

    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(SingleSchool2.this);
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

    @Override
    public void onImageLoaded(Bitmap bitmap) {

        imgflag.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
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
                String status = login.getString("LoginStatus","null");
                if (status.equals("true")){

                    Intent viewShoppingCartIntent11 = new Intent(getBaseContext(), SchoolBrowse1.class);
                    startActivity(viewShoppingCartIntent11);
                }

                else{
                    Toast.makeText(getBaseContext(),"Login To Proceed",Toast.LENGTH_LONG).show();
                    Intent viewShoppingCartIntent1 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(viewShoppingCartIntent1);
                }
                return true;
            }
            case R.id.action_myschoolout:
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_for_singleschool, menu);
        return true;
    }
}
