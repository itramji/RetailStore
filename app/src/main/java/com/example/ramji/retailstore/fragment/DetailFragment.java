package com.example.ramji.retailstore.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramji.retailstore.BaseActivity;
import com.example.ramji.retailstore.CartListActivity;
import com.example.ramji.retailstore.R;
import com.example.ramji.retailstore.databinding.DetailFragmentBinding;
import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.model.Cart;
import com.example.ramji.retailstore.model.ItemDetail;
import com.example.ramji.retailstore.viewmodel.ItemViewModel;

public class DetailFragment extends Fragment {

    private ItemDetail itemDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        DetailFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false);
        itemDetail = getArguments().getParcelable(AppConstant.itemModel);
        assert itemDetail != null;
        binding.setItemModel(new ItemViewModel(itemDetail));
        binding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("Product purchased successfully");
                dialog.setPositiveButton("Ok", null);
                dialog.show();
            }
        });

        return binding.getRoot();
    }

    private void addToCart() {
        itemDetail.setInCart(true);
        ((BaseActivity)getActivity()).appDatabase.cartDao().insertItem(new Cart(itemDetail.getProductId()));
        //((BaseActivity)getActivity()).appDatabase.productsDao().updateItem(itemDetail);

        Intent intent = new Intent(getActivity(), CartListActivity.class);
        intent.putExtra(AppConstant.productId, itemDetail.getProductId());
        startActivity(intent);
    }
}
