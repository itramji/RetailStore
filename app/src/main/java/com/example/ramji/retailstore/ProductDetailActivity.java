package com.example.ramji.retailstore;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.ramji.retailstore.adapter.DetailPagerAdapter;
import com.example.ramji.retailstore.databinding.ActivityProductDetailBinding;
import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.model.ItemDetail;

import java.util.List;

public class ProductDetailActivity extends BaseActivity {

    private ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);

        initToolBar(true);

        showCartIcon(true);

        initContent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initContent(intent);
    }

    private void initContent(Intent intent) {
        String category = intent.getStringExtra(AppConstant.categoryName);
        List<ItemDetail> itemDetail = appDatabase.productsDao().loadItemsByCategory(category);
        setTitle(category);

        binding.viewPager.setAdapter(new DetailPagerAdapter(itemDetail, getSupportFragmentManager()));

        for (int i = 0; i < itemDetail.size(); i++) {
            if (itemDetail.get(i).getProductId() == intent.getIntExtra(AppConstant.productId, 0)) {
                binding.viewPager.setCurrentItem(i);
            }
        }
    }


}
