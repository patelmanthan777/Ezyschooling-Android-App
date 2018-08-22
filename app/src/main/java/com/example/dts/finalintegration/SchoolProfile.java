package com.example.dts.finalintegration;

import java.util.ArrayList;

/**
 * Created by D.T.S on 9/5/2016.
 */
public class SchoolProfile {
    public SchoolProfile(String schoolsid, String name, String fees, String description, String schooltimings, String location,  String popularity, String price, String daysleft, String milestones1, String milestones2, String milestones3,  String contactus_email, String contactus_fax, String contactus_phoneno, String rating_stars, String rating_comments, ArrayList<String> type, ArrayList<String> student, ArrayList<String> sports, ArrayList<String> category, ArrayList<String> languages, ArrayList<String> subjects, ArrayList<String> region, ArrayList<String> feestructure, ArrayList<String> transport, ArrayList<String> accomodation,
                         ArrayList<String> AC, ArrayList<String> image1,
                         boolean ac_nonac_show, boolean accomodation_show, boolean category_show, boolean foreign_languages_show,
                         boolean region_show, boolean sports_show, boolean students_per_class_show,
                         boolean subjects_in_11th_show, boolean transport_facility_show, boolean type_show,
                         boolean fees_structre_show, int values, String board, String logo, String teachers, String address,
                         String firstdatetoapply, String lastdatetoapply, String specialcriteria, String limitonforms,
                         ArrayList<String> facilities,ArrayList<String> Labs,String sec) {

        Schoolsid = schoolsid;
        Name = name;
        Fees = fees;
        Description = description;
        Schooltimings = schooltimings;
        Location = location;

        Board = board; Logo = logo; Teachers= teachers;Address = address;Firstdatetoapply = firstdatetoapply;Lastdatetoapply = lastdatetoapply;
        Specialcriteria = specialcriteria; Limitonforms = limitonforms;



        Popularity = popularity;
        Price = price;
        Daysleft = daysleft;
        Milestones1 = milestones1;
        Milestones2 = milestones2;
        Milestones3 = milestones3;
this.sec= sec;
        this.contactus_email = contactus_email;
        this.contactus_fax = contactus_fax;
        this.contactus_phoneno = contactus_phoneno;
        this.rating_stars = rating_stars;
        this.rating_comments = rating_comments;
        Type = type;
        Student = student;
        Sports = sports;
        this.category = category;
        Languages = languages;
        Subjects = subjects;
        Region = region;
        Feestructure = feestructure;
        Transport = transport;
        Accomodation = accomodation;
        this.AC = AC;
        this.Labs = Labs;
        Image1 = image1;
        this.Facilities = facilities;
        this.ac_nonac_show = ac_nonac_show;this.accomodation_show = accomodation_show;this.category_show = category_show;
        this.foreign_languages_show= foreign_languages_show;this.region_show = region_show;
        this.sports_show = sports_show;this.students_per_class_show= students_per_class_show;

        this.subjects_in_11th_show = subjects_in_11th_show;this.transport_facility_show= transport_facility_show;this.type_show= type_show;
        this.fees_structre_show=fees_structre_show;this.values= values;
    }

    String Schoolsid ;

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    String sec;
    String Name ;String Fees ;String Description ;String Schooltimings;
    String Location;
    String Popularity ;String Price;String Daysleft ;

    public String getBoard() {
        return Board;
    }

    public void setBoard(String board) {
        Board = board;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getTeachers() {
        return Teachers;
    }

    public void setTeachers(String teachers) {
        Teachers = teachers;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFirstdatetoapply() {
        return Firstdatetoapply;
    }

    public void setFirstdatetoapply(String firstdatetoapply) {
        Firstdatetoapply = firstdatetoapply;
    }

    public String getLastdatetoapply() {
        return Lastdatetoapply;
    }

    public void setLastdatetoapply(String lastdatetoapply) {
        Lastdatetoapply = lastdatetoapply;
    }

    public String getLimitonforms() {
        return Limitonforms;
    }

    public void setLimitonforms(String limitonforms) {
        Limitonforms = limitonforms;
    }

    public String getSpecialcriteria() {
        return Specialcriteria;
    }

    public void setSpecialcriteria(String specialcriteria) {
        Specialcriteria = specialcriteria;
    }

    public boolean isReligion_show() {
        return religion_show;
    }

    public void setReligion_show(boolean religion_show) {
        this.religion_show = religion_show;
    }

    String Board ;String Logo ;String Teachers ;
    String Address ;String Firstdatetoapply ;String Lastdatetoapply ;String Limitonforms ;String Specialcriteria ;String Milestones3 ;
    String Milestones1 ;String Milestones2 ;

    public boolean isCategory_show() {
        return category_show;
    }

    public void setCategory_show(boolean category_show) {
        this.category_show = category_show;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public boolean isAc_nonac_show() {
        return ac_nonac_show;
    }

    public void setAc_nonac_show(boolean ac_nonac_show) {
        this.ac_nonac_show = ac_nonac_show;
    }

    public boolean isFees_structre_show() {
        return fees_structre_show;
    }

    public void setFees_structre_show(boolean fees_structre_show) {
        this.fees_structre_show = fees_structre_show;
    }

    public boolean isAccomodation_show() {
        return accomodation_show;
    }

    public void setAccomodation_show(boolean accomodation_show) {
        this.accomodation_show = accomodation_show;
    }

    public boolean isForeign_languages_show() {
        return foreign_languages_show;
    }

    public void setForeign_languages_show(boolean foreign_languages_show) {
        this.foreign_languages_show = foreign_languages_show;
    }

    public boolean isRegion_show() {
        return region_show;
    }

    public void setRegion_show(boolean region_show) {
        this.region_show = region_show;
    }



    public boolean isSports_show() {
        return sports_show;
    }

    public void setSports_show(boolean sports_show) {
        this.sports_show = sports_show;
    }

    public boolean isStudents_per_class_show() {
        return students_per_class_show;
    }

    public void setStudents_per_class_show(boolean students_per_class_show) {
        this.students_per_class_show = students_per_class_show;
    }

    public boolean isSubjects_in_11th_show() {
        return subjects_in_11th_show;
    }

    public void setSubjects_in_11th_show(boolean subjects_in_11th_show) {
        this.subjects_in_11th_show = subjects_in_11th_show;
    }

    public boolean isTransport_facility_show() {
        return transport_facility_show;
    }

    public void setTransport_facility_show(boolean transport_facility_show) {
        this.transport_facility_show = transport_facility_show;
    }

    public boolean isType_show() {
        return type_show;
    }

    public void setType_show(boolean type_show) {
        this.type_show = type_show;
    }

    int values = 0;
    boolean ac_nonac_show = false;
    boolean fees_structre_show = false;
    boolean accomodation_show = false;
    boolean category_show = false;
    boolean foreign_languages_show = false;
    boolean region_show = false;
    boolean religion_show = false;
    boolean sports_show = false;
    boolean students_per_class_show = false;
    boolean subjects_in_11th_show = false;
    boolean transport_facility_show = false;
    boolean type_show = false;


    public String getSchoolsid() {
        return Schoolsid;
    }

    public void setSchoolsid(String schoolsid) {
        Schoolsid = schoolsid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSchooltimings() {
        return Schooltimings;
    }

    public void setSchooltimings(String schooltimings) {
        Schooltimings = schooltimings;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }



    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDaysleft() {
        return Daysleft;
    }

    public void setDaysleft(String daysleft) {
        Daysleft = daysleft;
    }

    public String getMilestones1() {
        return Milestones1;
    }

    public void setMilestones1(String milestones1) {
        Milestones1 = milestones1;
    }

    public String getMilestones2() {
        return Milestones2;
    }

    public void setMilestones2(String milestones2) {
        Milestones2 = milestones2;
    }

    public String getMilestones3() {
        return Milestones3;
    }

    public void setMilestones3(String milestones3) {
        Milestones3 = milestones3;
    }




    public String getContactus_email() {
        return contactus_email;
    }

    public void setContactus_email(String contactus_email) {
        this.contactus_email = contactus_email;
    }

    public String getContactus_fax() {
        return contactus_fax;
    }

    public void setContactus_fax(String contactus_fax) {
        this.contactus_fax = contactus_fax;
    }

    public String getContactus_phoneno() {
        return contactus_phoneno;
    }

    public void setContactus_phoneno(String contactus_phoneno) {
        this.contactus_phoneno = contactus_phoneno;
    }

    public String getRating_stars() {
        return rating_stars;
    }

    public void setRating_stars(String rating_stars) {
        this.rating_stars = rating_stars;
    }

    public String getRating_comments() {
        return rating_comments;
    }

    public void setRating_comments(String rating_comments) {
        this.rating_comments = rating_comments;
    }

    public ArrayList<String> getType() {
        return Type;
    }

    public void setType(ArrayList<String> type) {
        Type = type;
    }

    public ArrayList<String> getStudent() {
        return Student;
    }

    public void setStudent(ArrayList<String> student) {
        Student = student;
    }

    public ArrayList<String> getSports() {
        return Sports;
    }

    public void setSports(ArrayList<String> sports) {
        Sports = sports;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public ArrayList<String> getLanguages() {
        return Languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        Languages = languages;
    }

    public ArrayList<String> getSubjects() {
        return Subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        Subjects = subjects;
    }

    public ArrayList<String> getRegion() {
        return Region;
    }

    public void setRegion(ArrayList<String> region) {
        Region = region;
    }

    public ArrayList<String> getFeestructure() {
        return Feestructure;
    }

    public void setFeestructure(ArrayList<String> feestructure) {
        Feestructure = feestructure;
    }

    public ArrayList<String> getTransport() {
        return Transport;
    }

    public void setTransport(ArrayList<String> transport) {
        Transport = transport;
    }

    public ArrayList<String> getAccomodation() {
        return Accomodation;
    }

    public void setAccomodation(ArrayList<String> accomodation) {
        Accomodation = accomodation;
    }

    public ArrayList<String> getAC() {
        return AC;
    }

    public void setAC(ArrayList<String> AC) {
        this.AC = AC;
    }

    public ArrayList<String> getImage1() {
        return Image1;
    }

    public void setImage1(ArrayList<String> image1) {
        Image1 = image1;
    }


    String contactus_email;String contactus_fax ;String contactus_phoneno ;
    String rating_stars;String rating_comments;

    ArrayList<String> Type ; ArrayList<String> Student; ArrayList<String> Sports ;
    ArrayList<String> category ; ArrayList<String> Languages ; ArrayList<String> Subjects;
    ArrayList<String> Region ; ArrayList<String> Feestructure; ArrayList<String> Transport;
    ArrayList<String> Accomodation; ArrayList<String> AC;

    public ArrayList<String> getFacilities() {
        return Facilities;
    }

    public void setFacilities(ArrayList<String> facilities) {
        Facilities = facilities;
    }

    ArrayList<String> Facilities;
    ArrayList<String> Image1;

    public ArrayList<String> getLabs() {
        return Labs;
    }

    public void setLabs(ArrayList<String> labs) {
        Labs = labs;
    }

    ArrayList<String> Labs;
}
