package com.rootonelabs.vicky.lazeez.Model;

public class Order {
    private  String FoodId;
    private  String FoodName;
    private  String Price;
    private  String Quantity;
    private  String Discount;


    public Order() {
    }

    public Order(String foodId, String foodName, String price, String quantity, String discount) {
        FoodId = foodId;
        FoodName = foodName;
        Price = price;
        Quantity = quantity;
        Discount = discount;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getFoodId() {
        return FoodId;
    }

    public void setFoodId(String foodId) {
        FoodId = foodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
