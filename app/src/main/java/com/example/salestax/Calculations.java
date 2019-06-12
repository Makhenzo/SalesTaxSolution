package com.example.salestax;

import android.util.Log;

import java.util.ArrayList;

public class Calculations
{

    private ArrayList<Goods> allGoods;

    public Calculations(ArrayList<Goods> allGoods) {
        this.allGoods = allGoods;
    }

    public ArrayList<Goods> getAllGoods() {
        return allGoods;
    }

    public void setAllGoods(ArrayList<Goods> allGoods) {
        this.allGoods = allGoods;
    }

    public void populateItems(String type , String desc , double price, int qty , String importStatus){

        Goods goodsObject = new Goods(type,desc,price,qty,importStatus);
        allGoods.add(goodsObject);
    }

    public double calculatePercentage(String product, String status, double price){

        double totalPrice = 0.0;

        if (product.equalsIgnoreCase("Food") || product.equalsIgnoreCase("Medical") || product.equalsIgnoreCase("Books")){

            if (status.equalsIgnoreCase("yes")) {
                {
                    totalPrice = totalPrice + (price + (price * 0.05));
                }
            } else {
                totalPrice = totalPrice + price;

            }
        }
        if (!product.equalsIgnoreCase("Food") && !product.equalsIgnoreCase("Medical") && !product.equalsIgnoreCase("Medical")) {
            if (status.equalsIgnoreCase("yes")) {
                {
                    totalPrice = totalPrice + (price + (price * 0.1)) + (price * 0.05);
                }
            } else {
                totalPrice = totalPrice + (price + (price * 0.1));
            }
        }

        return totalPrice;
    }
    public double calculateTotal() {

        double totalPrice = 0.0;

        for(int i = 0; i < allGoods.size(); i++){

            if (allGoods.get(i).getType().equalsIgnoreCase("Food") || allGoods.get(i).getType().equalsIgnoreCase("Medical") || allGoods.get(i).getType().equalsIgnoreCase("Books")){

                if (allGoods.get(i).getImportStatus().equalsIgnoreCase("yes")) {
                    {
                        totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.05));
                    }
                } else {
                    totalPrice = totalPrice + allGoods.get(i).getPrice();

                }
            }
            if (!allGoods.get(i).getType().equalsIgnoreCase("Food") && !allGoods.get(i).getType().equalsIgnoreCase("Medical") && !allGoods.get(i).getType().equalsIgnoreCase("Medical")) {
                if (allGoods.get(i).getImportStatus().equalsIgnoreCase("yes")) {
                    {
                        totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.1)) + (allGoods.get(i).getPrice() * 0.05);
                    }
                } else {
                    totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.1));
                }
            }

            Log.d("PricesForEach", "===============++++++---====" + totalPrice);
        }

        return totalPrice;
    }


    public double calculateSales(){

        double totalPrice = 0.0;
        double sales = 0.0;

        for (int i = 0; i < allGoods.size(); i++){


            if (allGoods.get(i).getType().equalsIgnoreCase("Food") || allGoods.get(i).getType().equalsIgnoreCase("Medical") || allGoods.get(i).getType().equalsIgnoreCase("Books")){

                if (allGoods.get(i).getImportStatus().equalsIgnoreCase("yes")) {
                    {
                        totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.05));
                        sales = sales + (allGoods.get(i).getPrice() * 0.05);
                    }
                } else {
                    totalPrice = totalPrice + allGoods.get(i).getPrice();

                }
            }
            if (!allGoods.get(i).getType().equalsIgnoreCase("Food") && !allGoods.get(i).getType().equalsIgnoreCase("Medical") && !allGoods.get(i).getType().equalsIgnoreCase("Medical")) {
                if (allGoods.get(i).getImportStatus().equalsIgnoreCase("yes")) {
                    {
                        totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.1)) + (allGoods.get(i).getPrice() * 0.05);
                        sales = sales +  (allGoods.get(i).getPrice() * 0.1) + (allGoods.get(i).getPrice() * 0.05);
                    }
                } else {
                    totalPrice = totalPrice + (allGoods.get(i).getPrice() + (allGoods.get(i).getPrice() * 0.1));
                    sales = sales +  (allGoods.get(i).getPrice() * 0.1);
                }
            }
        }
        return sales;
    }








}
