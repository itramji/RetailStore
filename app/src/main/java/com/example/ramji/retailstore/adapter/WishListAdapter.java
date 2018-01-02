package com.example.ramji.retailstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramji.retailstore.BaseActivity;
import com.example.ramji.retailstore.ProductDetailActivity;
import com.example.ramji.retailstore.R;
import com.example.ramji.retailstore.adapter.viewholder.DataBindViewHolder;
import com.example.ramji.retailstore.databinding.CartListContentBinding;
import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.model.ItemDetail;
import com.example.ramji.retailstore.model.Favorite;
import com.example.ramji.retailstore.viewmodel.ItemViewModel;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<DataBindViewHolder> {

    private List<Favorite> itemDetails;
    private Context context;

    public WishListAdapter(Context context, List<Favorite> itemDetails) {
        this.context = context;
        this.itemDetails =  itemDetails;
    }

    @Override
    public DataBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataBindViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cart_list_content, parent, false));
    }

    @Override
    public void onBindViewHolder(DataBindViewHolder holder, int position) {
        CartListContentBinding binding = ((CartListContentBinding) holder.dataBinding);
        ItemDetail itemDetail = ((BaseActivity)context).appDatabase.productsDao().loadItembyProductId(itemDetails.get(position).productId);
        binding.setItemModel(new ItemViewModel(itemDetail));
        binding.getRoot().setTag(itemDetail);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra(AppConstant.categoryName, ((ItemDetail) v.getTag()).getCategoryName());
                intent.putExtra(AppConstant.productId, ((ItemDetail) v.getTag()).getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDetails.size();
    }
}
