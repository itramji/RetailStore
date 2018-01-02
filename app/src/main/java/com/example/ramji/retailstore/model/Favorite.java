package com.example.ramji.retailstore.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ramji on 7/12/17.
 */

@Entity(foreignKeys = @ForeignKey(entity = ItemDetail.class, parentColumns = "productId", childColumns = "productId"))
public class Favorite {

    public Favorite(int productId){
        this.productId = productId;
    }

    @PrimaryKey(autoGenerate = true)
    public int wishListId;

    public int productId;

}
