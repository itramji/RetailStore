package com.example.ramji.retailstore;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.ramji.retailstore.adapter.ProductListAdapter;
import com.example.ramji.retailstore.dummy.AppConstant;
import com.example.ramji.retailstore.model.ItemDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ProductListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        initToolBar(false);

        showCartIcon(true);

        RecyclerView recyclerView = findViewById(R.id.item_list);

        if (!getDatabasePath(AppConstant.databaseName).exists()) {
            List<ItemDetail> itemDetails = new Gson().fromJson(AppConstant.dummyJson, new TypeToken<List<ItemDetail>>() {
            }.getType());
            appDatabase.productsDao().insertItems(itemDetails);
        }

        recyclerView.setAdapter(new ProductListAdapter(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appDatabase.destroyInstance();
    }
}
