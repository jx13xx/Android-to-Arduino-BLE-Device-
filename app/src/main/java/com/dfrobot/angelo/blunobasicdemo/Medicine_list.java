package com.dfrobot.angelo.blunobasicdemo;

public class Medicine_list {

    private String med_name;
    private String med_value;
    private String med_expiry;

    public Medicine_list(String med_name, String med_value, String med_expiry) {
        this.med_name = med_name;
        this.med_value = med_value;
        this.med_expiry = med_expiry;
    }

    public String getMed_name() {
        return med_name;
    }

    public String getMed_expiry() {
        return med_expiry;
    }

    public String getMed_value() {
        return med_value;
    }
}
