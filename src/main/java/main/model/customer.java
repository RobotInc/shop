package main.model;

public class customer {
    int id;
    String name;
    String gstin;
    String registered;
    String address;
    String state;
    String phone;

    public customer(){

    }
    public customer(int id,String name, String gstin, String registered,String state, String address, String phone) {
        this.id = id;
        this.name = name;
        this.gstin = gstin;
        this.registered = registered;
        this.address = address;
        this.state = state;
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
