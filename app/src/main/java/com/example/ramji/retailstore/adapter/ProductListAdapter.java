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
import com.example.ramji.retailstore.databinding.ItemTitleLayoutBinding;
import com.example.ramji.retailstore.databinding.ProductListContentBinding;
import com.example.ramji.retailstore.db.AppDatabase;
import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.model.Favorite;
import com.example.ramji.retailstore.model.ItemDetail;
import com.example.ramji.retailstore.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<DataBindViewHolder> {

    private List<Object> itemDetails;
    private Context context;
    List<Favorite> wishLists;
    AppDatabase appDatabase;

    public ProductListAdapter(Context context) {
        this.context = context;

        appDatabase = ((BaseActivity) context).appDatabase;
        List<String> categories = appDatabase.productsDao().loadCategories();
        wishLists = appDatabase.wishListDao().loadItemsInFavorite();

        itemDetails = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            itemDetails.add(categories.get(i));
            List<ItemDetail> details = appDatabase.productsDao().loadItemsByCategory(categories.get(i));
            itemDetails.addAll(details);
        }

    }

    @Override
    public DataBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new DataBindViewHolder(DataBindingUtil.inflate(inflater, R.layout.product_list_content, parent, false));
        } else {
            return new DataBindViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_title_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(DataBindViewHolder holder, final int position) {
        if (getItemViewType(position) == 1) {
            final ItemDetail itemDetail = (ItemDetail) itemDetails.get(position);
            ((ProductListContentBinding) holder.dataBinding).setItemModel(new ItemViewModel(itemDetail));
            final Favorite favorite = appDatabase.wishListDao().favoriteFromProductId(itemDetail.getProductId());
            final boolean inFavorite = favorite != null;
            ((ProductListContentBinding) holder.dataBinding).setInFavorite(inFavorite);

            holder.dataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra(AppConstant.categoryName, itemDetail.getCategoryName());
                    intent.putExtra(AppConstant.productId, itemDetail.getProductId());
                    context.startActivity(intent);
                }
            });

            ((ProductListContentBinding) holder.dataBinding).addToWish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (inFavorite) {
                        ((BaseActivity) context).appDatabase.wishListDao().removeItem(favorite);
                    } else {
                        ((BaseActivity) context).appDatabase.wishListDao().insertItem(new Favorite(itemDetail.getProductId()));
                    }

                    notifyItemChanged(position);
                }
            });
        } else {
            ((ItemTitleLayoutBinding) holder.dataBinding).setCategory((String) itemDetails.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemDetails.get(position) instanceof String ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return itemDetails.size();
    }
}
