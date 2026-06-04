package com.ibtissam.numberbook;

public class IbtissamContact {
    private int ibtissam_id;
    private String ibtissam_name;
    private String ibtissam_phone;
    private String ibtissam_source;
    private String created_at;

    public IbtissamContact() {
    }

    public IbtissamContact(String ibtissam_name, String ibtissam_phone) {
        this.ibtissam_name = ibtissam_name;
        this.ibtissam_phone = ibtissam_phone;
    }

    public int getIbtissam_id() {
        return ibtissam_id;
    }

    public void setIbtissam_id(int ibtissam_id) {
        this.ibtissam_id = ibtissam_id;
    }

    public String getIbtissam_name() {
        return ibtissam_name;
    }

    public void setIbtissam_name(String ibtissam_name) {
        this.ibtissam_name = ibtissam_name;
    }

    public String getIbtissam_phone() {
        return ibtissam_phone;
    }

    public void setIbtissam_phone(String ibtissam_phone) {
        this.ibtissam_phone = ibtissam_phone;
    }

    public String getIbtissam_source() {
        return ibtissam_source;
    }

    public void setIbtissam_source(String ibtissam_source) {
        this.ibtissam_source = ibtissam_source;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
