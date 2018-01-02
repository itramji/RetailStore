package com.example.ramji.retailstore.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class ItemDetail implements Parcelable{
    private String modelName;
    private String image;
    @PrimaryKey
    private int productId;
    private int price;
    private String name;
    private String discount;
    private String categoryName;
    private int categoryId;
    private boolean inCart;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public ItemDetail(){}

    protected ItemDetail(Parcel in) {
        modelName = in.readString();
        image = in.readString();
        productId = in.readInt();
        price = in.readInt();
        name = in.readString();
        discount = in.readString();
        categoryName = in.readString();
        categoryId = in.readInt();
        inCart = in.readByte() != 0;
    }

    public static final Creator<ItemDetail> CREATOR = new Creator<ItemDetail>() {
        @Override
        public ItemDetail createFromParcel(Parcel in) {
            return new ItemDetail(in);
        }

        @Override
        public ItemDetail[] newArray(int size) {
            return new ItemDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelName);
        dest.writeString(image);
        dest.writeInt(productId);
        dest.writeInt(price);
        dest.writeString(name);
        dest.writeString(discount);
        dest.writeString(categoryName);
        dest.writeInt(categoryId);
        dest.writeByte((byte) (inCart ? 1 : 0));
    }
}
