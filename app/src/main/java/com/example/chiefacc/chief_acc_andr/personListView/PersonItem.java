package com.example.chiefacc.chief_acc_andr.personListView;

public class PersonItem {

    private String name;
    private String sum;

    public PersonItem(String name, String sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
