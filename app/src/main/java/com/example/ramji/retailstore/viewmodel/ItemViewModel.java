package com.example.ramji.retailstore.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;

import com.example.ramji.retailstore.model.ItemDetail;

public class ItemViewModel extends ViewModel {

    private ItemDetail itemDetail;

    public ItemViewModel(@NonNull ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public String getAmount() {
        return "Rs: " + itemDetail.getPrice();
    }

    /*@BindingAdapter("app:tint")
    public void changeIconColor(){

    }*/

}
