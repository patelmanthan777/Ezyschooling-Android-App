package com.example.dts.finalintegration;

/**
 * Created by D.T.S on 9/5/2016.
 */
public class Product {
    public String Name;

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String schoolid;
    public String coverpic;
    public String branch;

    public boolean selected;


    public Product(String Name,  String schoolid,
                   String coverpic,String branch) {
        this.Name = Name;
        this.schoolid = schoolid;
        this.coverpic = coverpic;
        this.branch = branch;
    }
}
