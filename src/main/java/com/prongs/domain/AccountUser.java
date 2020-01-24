package com.prongs.domain;

/**
 * @author Prongs
 * @date 2020/1/24 14:31
 */
public class AccountUser extends Account {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "AccountUser{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
