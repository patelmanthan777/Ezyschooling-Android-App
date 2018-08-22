package com.example.dts.finalintegration;

import java.util.List;
import java.util.Vector;

/**
 * Created by D.T.S on 9/5/2016.
 */
public class ShoppingCartHelper  {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";


    private static List<Product> cart;



    public static List<Product> getCart() {
        if(cart == null) {
            cart = new Vector<Product>();
        }

        return cart;
    }

}

