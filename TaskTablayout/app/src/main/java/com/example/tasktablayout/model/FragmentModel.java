package com.example.tasktablayout.model;

public class FragmentModel {
    String name;
    int amount;

    public FragmentModel() {
    }

    public FragmentModel(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FragmentModel{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
