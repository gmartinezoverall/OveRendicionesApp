package com.overall.developer.overrendicion.data.model.entity;

public class ProvinciaEntity
{
    String code;
    String desc;

    public ProvinciaEntity() {
    }

    public ProvinciaEntity(String code, String desc) {
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

