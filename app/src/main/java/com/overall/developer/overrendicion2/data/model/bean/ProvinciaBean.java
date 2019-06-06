package com.overall.developer.overrendicion2.data.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProvinciaBean extends RealmObject
{
    @PrimaryKey
    String code;
    String desc;

    public ProvinciaBean()
    {
    }

    public ProvinciaBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
