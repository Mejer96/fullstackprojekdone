package com.example.projekt2.controllers;

import java.io.Serializable;

public class Wishlist implements Serializable {
    private String wishlistName;
    private String wishlistDescription;
    private String wishlistID;

    public Wishlist(String wishlistName, String wishlistDescription, String wishlistID) {
        this.wishlistName = wishlistName;
        this.wishlistDescription = wishlistDescription;
        this.wishlistID = wishlistID;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setName(String name) {
        this.wishlistName = name;
    }

    public String getWishlistDescription() {
        return wishlistDescription;
    }

    public void setDescription(String wishlistDescription) {
        this.wishlistDescription = wishlistDescription;
    }

    public String getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(String wishlistID) {
        this.wishlistID = wishlistDescription;
    }
}
