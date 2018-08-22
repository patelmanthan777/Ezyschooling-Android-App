package com.example.dts.finalintegration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by D.T.S on 9/5/2016.
 */
public class ListViewAdapter extends BaseAdapter  {
    NetworkImageView COVERPIC; private ImageLoader imageLoader;
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<SchoolProfile> data;
     //ImageLoader imageLoader;
    Object resultp;

    public ListViewAdapter(Context context,
                           ArrayList<SchoolProfile> arraylist) {
        this.context = context;
        data = arraylist;
        //imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView Name;
        TextView ADDRESS;
        TextView DESCRIPTION;
        RatingBar EZYSCHOOLINGRATING;
        TextView ACHIEVEMENTS;
        TextView FEESTRUCTURE;
        TextView SECTIONSPERCLASS;
        TextView STUDENTSPERCLASS;
        TextView TEACHERSTUDENTRATIO;
        TextView CONTACTDETAILS;
        TextView BRANCH;



        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);
        final SchoolProfile cur1 = (SchoolProfile) resultp;
        COVERPIC = (NetworkImageView) itemView.findViewById(R.id.imageView_schoolicon);
        //new LoadImageTask(this).execute("http://www.ezyschooling.com"+cur1.getLogo());
        // Locate the TextViews in listview_item.xml
        Name = (TextView) itemView.findViewById(R.id.textView_schoolname1);
        BRANCH = (TextView) itemView.findViewById(R.id.textView_branch);
        EZYSCHOOLINGRATING = (RatingBar) itemView.findViewById(R.id.ratingBar_listview_display);
        LayerDrawable stars = (LayerDrawable) EZYSCHOOLINGRATING.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        // Locate the ImageView in listview_item.xml

        //new LoadImageTask(this).execute("http://www.ezyschooling.com"+cur1.getLogo());
        // Capture position and set results to the TextViews
        Name.setText(cur1.getName());
        BRANCH.setText(cur1.getLocation());
        EZYSCHOOLINGRATING.setRating(Float.parseFloat(cur1.getRating_stars()));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
      //  new LoadImageTask(this).execute("http://www.ezyschooling.com"+cur1.getLogo());
        // imageLoader.DisplayImage("http://www.ezyschooling.com/public/schoolpics/ryanlogo.jpg", COVERPIC);
        imageLoader = CustomVolleyRequest.getInstance(context)
                .getImageLoader();
        imageLoader.get("http://www.ezyschooling.com"+cur1.getLogo(), ImageLoader.getImageListener(COVERPIC,
                R.drawable.applogo123, R.drawable.loading1));
        COVERPIC.setImageUrl("http://www.ezyschooling.com"+cur1.getLogo(), imageLoader);
  //      imageLoader.DisplayImage("http://www.ezyschooling.com"+cur1.getLogo(), COVERPIC);
        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);

                Intent intent = new Intent(context, SingleSchool2.class);
                // Pass all data rank
                intent.putExtra("Name", cur1.getName());


                // Pass all data country
                intent.putExtra("ADDRESS", cur1.getLocation());
                // Pass all data population
                intent.putExtra("DESCRIPTION", cur1.getDescription());
                // Pass all data flag
                intent.putExtra("EZYSCHOOLINGRATING_1", cur1.getRating_stars());
                intent.putExtra("EZYSCHOOLINGRATING_2", cur1.getRating_comments());
                intent.putExtra("ACHIEVEMENTS_1", cur1.getMilestones1());
                intent.putExtra("ACHIEVEMENTS_2", cur1.getMilestones2());
                intent.putExtra("ACHIEVEMENTS_3", cur1.getMilestones3());

                // Pass all data country

                // Pass all data population

                // Pass all data country
                intent.putExtra("CONTACTDETAILS_mail", cur1.getContactus_email());
                intent.putExtra("CONTACTDETAILS_phone", cur1.getContactus_phoneno());
                // Pass all data population
                //   intent.putExtra("COVERPIC",cur1.getCoverpic());
                // Pass all data flag
                intent.putExtra("FeesSchool", cur1.getFees());
                intent.putExtra("SchoolTiming", cur1.getSchooltimings());

                intent.putExtra("Price", cur1.getPrice());
                intent.putExtra("Daysleft", cur1.getDaysleft());
                intent.putExtra("Board", cur1.getBoard());
                intent.putExtra("Logo", cur1.getLogo());
                intent.putExtra("Teachers", cur1.getTeachers());
                intent.putExtra("FirstDate", cur1.getFirstdatetoapply());
                intent.putExtra("LastDate", cur1.getLastdatetoapply());
                intent.putExtra("LimitOnForms", cur1.getLimitonforms());
                intent.putExtra("SpecialCriteria", cur1.getSpecialcriteria());
                intent.putExtra("sec", cur1.getSec());

                ArrayList<String> Type = cur1.getType();
                int i; String Type_value = null;
                for (i=0;i<Type.size();i++){
                     Type_value = Type.get(i);
                }
                intent.putExtra("Type", Type_value);

                ArrayList<String> Student = cur1.getStudent();
                int j; String Student_value = null;
                for (j=0;j<Student.size();j++){
                    Student_value = Student.get(j);
                }
                intent.putExtra("Student", Student_value);

                ArrayList<String> SPORTS = cur1.getSports();
               //  String SPORTS_value = null;
               // for (i=0;i<SPORTS.size();i++){
              //      SPORTS_value = SPORTS.get(i);
              //  }
                intent.putStringArrayListExtra("SPORTS", SPORTS);


                ArrayList<String> Category = cur1.getCategory();
                 String Category_value = null;
                for (i=0;i<Category.size();i++){
                    Category_value = Category.get(i);
                }
                intent.putExtra("Category", Category_value);

                ArrayList<String> foreeign_lang = cur1.getLanguages();
            //  String foreeign_lang_value = null;
            //    for (i=0;i<foreeign_lang.size();i++){
             //       foreeign_lang_value = foreeign_lang.get(i);
             //   }
                intent.putStringArrayListExtra("foreeign_lang", foreeign_lang);


                ArrayList<String> SUBJECTS = cur1.getSubjects();
            // String SUBJECTS_value = null;
               // for (i=0;i<SUBJECTS.size();i++){
                //    SUBJECTS_value = SUBJECTS.get(i);
             //   }
                intent.putStringArrayListExtra("SUBJECTS", SUBJECTS);


                ArrayList<String> Region = cur1.getRegion();
                 String Region_value = null;
                for (i=0;i<Region.size();i++){
                    Region_value = Region.get(i);
                }
                intent.putExtra("Region", Region_value);


                ArrayList<String> Transport = cur1.getTransport();
                 String Transport_value = null;
                for (i=0;i<Transport.size();i++){
                    Transport_value = Transport.get(i);
                }
                intent.putExtra("Transport", Transport_value);

                ArrayList<String> Accomod = cur1.getAccomodation();
                String Accomod_value = null;
                for (i=0;i<Accomod.size();i++){
                    Accomod_value = Accomod.get(i);
                }
                intent.putExtra("Accomod", Accomod_value);


                ArrayList<String> AC = cur1.getAC();
                String AC_value = null;
                for (i=0;i<AC.size();i++){
                    AC_value = AC.get(i);
                }
                intent.putExtra("AC", AC_value);




                ArrayList<String> Facilities = cur1.getFacilities();
              //  String Facilities_value = null;
              //  for (i=0;i<Facilities.size();i++){
              //      Facilities_value = Facilities.get(i);
             //   }
                intent.putStringArrayListExtra("Facilities", Facilities);

                ArrayList<String> Labs = cur1.getLabs();
                //  String Facilities_value = null;
                //  for (i=0;i<Facilities.size();i++){
                //      Facilities_value = Facilities.get(i);
                //   }
                intent.putStringArrayListExtra("Labs", Labs);


                ArrayList<String> Images = cur1.getImage1();
                String Image1 = null,Image2 = null, Image3 = null,Image4 = null,Image5 = null, Image6 = null,
                Image7 = null,Image8 = null, Image9 = null,Image10 = null;
              //  for (i=0;i<Images.size();i++){
              //      Image1 = Facilities.get(i);
              //  }
              Image1 = Images.get(0);
                intent.putExtra("Image1", Image1);
                Image2 = Images.get(1);
                intent.putExtra("Image2", Image2);
               Image3 = Images.get(2);
                intent.putExtra("Image3", Image3);
                Image4 = Images.get(3);
                intent.putExtra("Image4", Image4);
                Image5 = Images.get(4);
                intent.putExtra("Image5", Image5);
                Image6 = Images.get(5);
                intent.putExtra("Image6", Image6);
                Image7 = Images.get(6);
                intent.putExtra("Image7", Image7);
                Image8 = Images.get(7);
                intent.putExtra("Image8", Image8);
                Image9 = Images.get(8);
                intent.putExtra("Image9", Image9);
                Image10 = Images.get(9);
                intent.putExtra("Image10", Image10);

                intent.putExtra("Coverpic", Image1);
                // Pass all data country

                // Pass all data countryhttp://www.ezyschooling.com/schoolpics/arwachinbharti/arwachin1.jpg
                //    intent.putExtra("SCHOOLIMAGE7", cur1.getSchoolimage7());
                // Pass all data population
                //    intent.putExtra("SCHOOLIMAGE8",cur1.getSchoolimage8());
                // Pass all data flag
                intent.putExtra("SCHOOLID", cur1.getSchoolsid());
                //  intent.putExtra("BRANCH", cur1.getB());
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }



}
