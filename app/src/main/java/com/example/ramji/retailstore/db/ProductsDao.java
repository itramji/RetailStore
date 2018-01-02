/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ramji.retailstore.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ramji.retailstore.model.ItemDetail;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductsDao {

    @Query("SELECT categoryName FROM itemdetail GROUP BY categoryId")
    List<String> loadCategories();

    @Query("SELECT * FROM itemdetail WHERE categoryName is :categoryName")
    List<ItemDetail> loadItemsByCategory(String categoryName);

    @Query("SELECT * FROM itemdetail WHERE productId = :id")
    ItemDetail loadItembyProductId(int id);

    @Query("SELECT COUNT(*) FROM itemdetail WHERE inCart = 1")
    LiveData<Integer> getCartCount();

    @Insert(onConflict = REPLACE)
    void updateItem(ItemDetail itemDetail);

    @Insert(onConflict = REPLACE)
    void insertItems(List<ItemDetail> itemDetails);

}