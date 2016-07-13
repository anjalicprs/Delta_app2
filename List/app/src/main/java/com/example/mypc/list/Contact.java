package com.example.mypc.list;

/**
 * Created by my pc on 07-07-2016.
 */
public class Contact {
    private String name="";
    private String phone="";
    private String type="";
    public String getName() {
        return(name);
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getPhone() {
        return(phone);
    }
    public void setPhone(String phone) {
        this.phone=phone;
    }
    public String getType() {
        return(type);
    }
    public void setType(String type) {
        this.type=type;
    }
    public String toString() {
        return(getName());
    }
}
