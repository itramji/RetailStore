package com.example.ramji.retailstore.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.fragment.DetailFragment;
import com.example.ramji.retailstore.model.ItemDetail;

import java.util.List;

public class DetailPagerAdapter extends FragmentPagerAdapter {

    private List<ItemDetail> list;

    public DetailPagerAdapter(List<ItemDetail> list, FragmentManager fm) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstant.itemModel, list.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
