package com.inclus.kimdonggeun.rgb;

public class graph_item_class {
   private int number; //번호
    private int year; //년도
    private int month; //달
    private int days;// 일
    private int day_of_the_week;//요일;
    private int num_r;
    private int num_g;
    private int num_b;
    private String rgb_value;
    private boolean thisMonth;

    public graph_item_class(int number, int year, int month, int days, int day_of_the_week, int num_r, int num_g, int num_b, String rgb_value, boolean thisMonth) {
        this.number = number;
        this.year = year;
        this.month = month;
        this.days = days;
        this.day_of_the_week = day_of_the_week;
        this.num_r = num_r;
        this.num_g = num_g;
        this.num_b = num_b;
        this.rgb_value = rgb_value;
        this.thisMonth = thisMonth;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDay_of_the_week() {
        return day_of_the_week;
    }

    public void setDay_of_the_week(int day_of_the_week) {
        this.day_of_the_week = day_of_the_week;
    }

    public int getNum_r() {
        return num_r;
    }

    public void setNum_r(int num_r) {
        this.num_r = num_r;
    }

    public int getNum_g() {
        return num_g;
    }

    public void setNum_g(int num_g) {
        this.num_g = num_g;
    }

    public int getNum_b() {
        return num_b;
    }

    public void setNum_b(int num_b) {
        this.num_b = num_b;
    }

    public String getRgb_value() {
        return rgb_value;
    }

    public void setRgb_value(String rgb_value) {
        this.rgb_value = rgb_value;
    }

    public boolean isThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(boolean thisMonth) {
        this.thisMonth = thisMonth;
    }
}
