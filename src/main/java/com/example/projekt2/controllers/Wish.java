package com.example.projekt2.controllers;

public class Wish {
    private String name;
    private String price;
    private String description;
    private String wish_ID;
    private String wishlist_ID;

    public Wish(String name, String price, String description, String wish_ID, String wishlist_ID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.wish_ID = wish_ID;
        this.wishlist_ID = wishlist_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWish_ID() {
        return wish_ID;
    }

    public void setWish_ID(String wish_ID) {
        this.wish_ID = wish_ID;
    }

    public String getWishlist_ID() {
        return wishlist_ID;
    }

    public void setWishlist_ID(String wishlist_ID) {
        this.wishlist_ID = wishlist_ID;
    }
}
