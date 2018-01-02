package com.example.ramji.retailstore.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by ramji on 26/12/17.
 */


@Entity(foreignKeys = @ForeignKey(entity=ItemDetail.class,parentColumns = "productId",childColumns = "cProductId",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE))
public class Cart {

    public Cart(int cProductId){
        this.cProductId = cProductId;
    }

    @PrimaryKey(autoGenerate = true) public int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getcProductId() {
        return cProductId;
    }

    public void setcProductId(int cProductId) {
        this.cProductId = cProductId;
    }

    public int cProductId;
}
