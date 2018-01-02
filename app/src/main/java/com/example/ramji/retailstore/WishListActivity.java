package com.example.ramji.retailstore;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.ramji.retailstore.adapter.WishListAdapter;
import com.example.ramji.retailstore.model.Favorite;

import java.util.List;

public class WishListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        initToolBar(true);

        showCartIcon(false);

        initList();
    }


    private void initList() {
        RecyclerView recyclerView = findViewById(R.id.item_list);

        //List<ItemDetail> itemDetails = appDatabase.productsDao().loadItemsInWishList();

        List<Favorite> wishLists = appDatabase.wishListDao().loadItemsInFavorite();

        recyclerView.setAdapter(new WishListAdapter(this, wishLists));

        setTitle("Favorite");
    }

}
