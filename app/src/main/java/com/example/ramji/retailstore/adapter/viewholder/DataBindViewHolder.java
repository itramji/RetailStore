package com.example.ramji.retailstore.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class DataBindViewHolder extends RecyclerView.ViewHolder {

    public ViewDataBinding dataBinding;

    public DataBindViewHolder(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }
}
