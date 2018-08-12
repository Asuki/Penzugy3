package com.example.seng.penzugy3;

public class FinanceElements {

    private String Name;
    private String Category;
    private String Spending;
    private String SpDate;

    public FinanceElements(String name, String category, String spending, String spDate) {
        Name = name;
        Category = category;
        Spending = spending;
        SpDate = spDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSpending() {
        return Spending;
    }

    public void setSpending(String spending) {
        Spending = spending;
    }

    public String getSpDate() {
        return SpDate;
    }

    public void setSpDate(String spDate) {
        SpDate = spDate;
    }
}
