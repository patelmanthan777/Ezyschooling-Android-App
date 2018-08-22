package com.example.dts.finalintegration;

/**
 * Created by D.T.S on 9/5/2016.
 */
public class Class_filter_counter  { public String value;
    public int counter =0;




    public Class_filter_counter(String value,int counter) {
        this.value = value;
        this.counter = counter;

    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}