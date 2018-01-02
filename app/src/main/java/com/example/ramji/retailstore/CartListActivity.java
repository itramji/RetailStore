package com.example.ramji.retailstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramji.retailstore.adapter.CartListAdapter;
import com.example.ramji.retailstore.model.Cart;
import com.example.ramji.retailstore.model.ItemDetail;

import java.util.List;

public class CartListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        initToolBar(true);

        showCartIcon(false);

        initList();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initList();
    }

    private void initList() {
        RecyclerView recyclerView = findViewById(R.id.item_list);

        List<Cart> itemDetails = appDatabase.cartDao().loadItemsInCart();

        recyclerView.setAdapter(new CartListAdapter(this, itemDetails));

        Button totalCart = findViewById(R.id.total_cart_amount);

        int totalAmount = 0;

        for (Cart cart : itemDetails) {
            ItemDetail itemDetail = appDatabase.productsDao().loadItembyProductId(cart.getcProductId());
            totalAmount += itemDetail.getPrice();
        }

        totalCart.setText(getString(R.string.total_Cart, totalAmount));

    }

}
