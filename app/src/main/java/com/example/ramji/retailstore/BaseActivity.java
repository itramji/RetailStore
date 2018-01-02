package com.example.ramji.retailstore;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramji.retailstore.db.AppDatabase;

public class BaseActivity extends AppCompatActivity {

    public AppDatabase appDatabase;
    private ImageView addCart, wishList;
    private TextView cartCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.getInMemoryDatabase(this);
    }

    void initToolBar(boolean showUp) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView backButton = findViewById(R.id.back_arrow);
        addCart = findViewById(R.id.add_cart);
        wishList = findViewById(R.id.wish_list);
        cartCount = findViewById(R.id.cart_counts);

        if (showUp) {
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            backButton.setVisibility(View.GONE);
        }

        observeCartCounts();
    }

    void setTitle(String title) {
        TextView textView = findViewById(R.id.title_Text);
        textView.setText(title);
    }

    void showCartIcon(boolean show) {
        if (show) {
            addCart.setVisibility(View.VISIBLE);
            cartCount.setVisibility(View.VISIBLE);
            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BaseActivity.this, CartListActivity.class));
                }
            });

        } else {
            addCart.setVisibility(View.GONE);
            cartCount.setVisibility(View.GONE);
        }

        showWishListIcon(show);
    }

    void showWishListIcon(boolean show) {
        if (show) {
            wishList.setVisibility(View.VISIBLE);
            wishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BaseActivity.this, WishListActivity.class));
                }
            });
        } else {
            wishList.setVisibility(View.GONE);
        }
    }

    private void observeCartCounts() {
        final TextView textView = findViewById(R.id.cart_counts);
        appDatabase.productsDao().getCartCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null && integer > 0) {
                    if (cartCount.getVisibility() == View.VISIBLE) {
                        textView.setText(String.valueOf(integer));
                    } else {
                        setTitle(getString(R.string.cart_count, integer));
                    }
                }
            }
        });
    }

}
