package com.example.dts.finalintegration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity {
    SharedPreferences login;  private static String insertUrl1 = "http://ezyschooling.com/myschools";private ProgressDialog pDialog;
    private List<Product> mCartList; ArrayList<Product> add_to_listview = new ArrayList<Product>();
    private ProductAdapter mProductAdapter; String insertUrl = "http://www.ezyschooling.com/myschools/remove";
    RequestQueue requestQueue; ArrayList<String> schools = new ArrayList<String>();ArrayList<String> schoolsid = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
       // new GetContacts().execute();
        json1();
        mCartList = ShoppingCartHelper.getCart();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button checkoutsend = (Button)findViewById(R.id.Button02_checkoutsend);
        checkoutsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // login = this.getSharedPreferences("Login_sp",MODE_PRIVATE);

                String status123 = login.getString("Status_form1","null");
               // Toast.makeText(getBaseContext()," "+ status123,Toast.LENGTH_LONG).show();
                if ( status123.equals("yes1")){
                    Intent viewShoppingCartIntent = new Intent(getBaseContext(), CAF_part3.class);
                    viewShoppingCartIntent.putStringArrayListExtra("schools",schools);
                    viewShoppingCartIntent.putStringArrayListExtra("schoolsid",schoolsid);
                    startActivity(viewShoppingCartIntent);
                }

               else {

                    showSimpleDialog();
                }

            }
        });
        // Make siure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }





        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(add_to_listview, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Product selectedProduct = add_to_listview.get(position);
                if(selectedProduct.selected == true)
                    selectedProduct.selected = false;
                else
                    selectedProduct.selected = true;

                mProductAdapter.notifyDataSetInvalidated();

            }
        });
        Button removeButton = (Button) findViewById(R.id.ButtonRemoveFromCart);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Loop through and remove all the products that are selected
                // Loop backwards so that the remove works correctly
                String sclid = null;
                for(int i=add_to_listview.size()-1; i>=0; i--) {

                    if(add_to_listview.get(i).selected) {
                        //   mCartList.remove(i);
                        Object obj = add_to_listview.get(i);
                        Product cur = (Product) obj;
                        sclid = cur.getSchoolid();
                    //    Toast.makeText(getBaseContext(),"id :" +sclid.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                json(sclid);
                for(int i=mCartList.size()-1; i>=0; i--) {

                    if(mCartList.get(i).selected) {
                        mCartList.remove(i);
                    }
                }
                for(int i=add_to_listview.size()-1; i>=0; i--) {

                    if(add_to_listview.get(i).selected) {
                        add_to_listview.remove(i);
                    }
                }
                mProductAdapter.notifyDataSetChanged();
            }
        });


        // Create the list
      //  final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
     //   mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
     //   listViewCatalog.setAdapter(mProductAdapter);






    }



    private void json(String schoollid){


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





                      //  Toast.makeText(getBaseContext(),"response :" +response.toString(),Toast.LENGTH_LONG).show();
                        for(int i=mCartList.size()-1; i>=0; i--) {

                            if(mCartList.get(i).selected) {
                                mCartList.remove(i);
                            }
                        }
                        for(int i=add_to_listview.size()-1; i>=0; i--) {

                            if(add_to_listview.get(i).selected) {
                                add_to_listview.remove(i);
                            }
                        }
                        mProductAdapter.notifyDataSetChanged();
                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());
              //  Toast.makeText(getBaseContext(),"" +error,Toast.LENGTH_LONG).show();





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

    public void showSimpleDialog() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
        builder.setCancelable(false);
        builder.setTitle("EzySchooling");
        builder.setMessage("Please fill Common Admission Form to proceed!!!");
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

    private void json1(){




        // adding some keys


        JsonArrayRequest jsonObjReq1 = new JsonArrayRequest(Request.Method.POST,
                insertUrl1,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("tag is : ", response.toString());
                       // Toast.makeText(getBaseContext(),"response :" +response.toString(),Toast.LENGTH_LONG).show();
                        if (response.toString() != null) {
                            try {
                                //  JSONObject jsonObj = new JSONObject(jsonStr);
                                JSONArray jarray = new JSONArray(response.toString());
                                // Getting JSON Array node
                                //  contacts = jsonObj.getJSONArray();

                                // looping through All Contacts
                                schools.add("<--select-->");

                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject c = jarray.getJSONObject(i);

                                    String schoolid = c.getString("_id");String Name = c.getString("Name");
                                    String Location = c.getString("Location");
                                    //  String Studentteacherratio = c.getString("Studentteacherratio");
                                    String Price = c.getString("Price");


                                    JSONArray ImagesColl = c.getJSONArray("Images");
                                    ArrayList<String> ImagesColl123  = new ArrayList<String>();

                                    for ( int k=0;k<ImagesColl.length();k++){
                                        String item = (String) ImagesColl.get(k);
                                        ImagesColl123.add(item);
                                    }
                                    JSONArray labs = c.getJSONArray("Labs");
                                    ArrayList<String> labs123  = new ArrayList<String>();

                                    for ( int k=0;k<labs.length();k++){
                                        String item = (String) labs.get(k);
                                        labs123.add(item);
                                    }
                                    JSONArray Imagelogo = c.getJSONArray("Logo");
                                    String Logo = null;

                                    for ( int k=0;k<Imagelogo.length();k++){
                                        Logo = (String) Imagelogo.get(k);

                                    }


                                    String Coverpicset = "http://www.ezyschooling.com"+ Logo ;
                                    Product selectedproduct = new Product (Name,schoolid,Coverpicset,Location);

                                   schools.add(Name);schoolsid.add(schoolid);
                                    add_to_listview.add(selectedproduct);


                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("ServiceHandler", "Couldn't get any data from the url");
                        }

                        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
                        mProductAdapter = new ProductAdapter(add_to_listview, getLayoutInflater(), true);
                        listViewCatalog.setAdapter(mProductAdapter);
                       // Toast.makeText(getBaseContext(),"response :" +response.toString(),Toast.LENGTH_LONG).show();

                        //   msgResponse.setText(response.toString());
                        //  hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d("tag is : ", error.toString());
              //  Toast.makeText(getBaseContext(),"" +error,Toast.LENGTH_LONG).show();
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
        requestQueue.add(jsonObjReq1);
    }

    public void showSimpleDialog1() {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
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
    public class ProductAdapter extends BaseAdapter {

        private List<Product> mProductList;
        private LayoutInflater mInflater;
        private boolean mShowCheckbox;
         ImageLoader imageLoader = new ImageLoader(getBaseContext());

        public ProductAdapter(List<Product> list, LayoutInflater inflater, boolean showCheckbox) {
            mProductList = list;
            mInflater = inflater;
            mShowCheckbox = showCheckbox;
        }

        @Override
        public int getCount() {
            return mProductList.size();
        }

        @Override
        public Object getItem(int position) {
            return mProductList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewItem item;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item,
                        null);
                item = new ViewItem();

                item.productImageView = (ImageView) convertView
                        .findViewById(R.id.ImageViewItem);

                item.productTitle = (TextView) convertView.findViewById(R.id.TextViewItem);

                item.productCheckbox = (CheckBox) convertView.findViewById(R.id.CheckBoxSelected);

                convertView.setTag(item);
            } else {
                item = (ViewItem) convertView.getTag();
            }

            Product curProduct = mProductList.get(position);
         /*   Glide.with(getBaseContext())
                    .load(curProduct.coverpic
                    )
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            //  imageView.setImage(ImageSource.bitmap(bitmap));
                            item.productImageView.setImageBitmap(bitmap);
                            //  thumbView.setImageBitmap(bitmap);
                        }
                    });*/
             imageLoader.DisplayImage(curProduct.coverpic, item.productImageView);
            //item.productImageView.setImageDrawable(curProduct.image);
            item.productTitle.setText(curProduct.Name);

            if(!mShowCheckbox) {
                item.productCheckbox.setVisibility(View.GONE);
            } else {
                if(curProduct.selected == true)
                    item.productCheckbox.setChecked(true);
                else
                    item.productCheckbox.setChecked(false);
            }


            return convertView;
        }


        private class ViewItem {
            ImageView productImageView;
            TextView productTitle;
            CheckBox productCheckbox;
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
