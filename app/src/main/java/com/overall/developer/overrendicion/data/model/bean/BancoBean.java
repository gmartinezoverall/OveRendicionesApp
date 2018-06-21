package com.overall.developer.overrendicion.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BancoBean extends RealmObject
{
    @PrimaryKey
    private String code;
    private String desc;

    public BancoBean() {
    }

    public BancoBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
