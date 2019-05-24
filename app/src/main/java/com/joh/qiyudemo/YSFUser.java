package com.joh.qiyudemo;

/**
 * 七鱼用户信息
 *
 * @author : Joh_hz
 * @date : 2019/5/24 15:27
 */
public class YSFUser {
    String key;
    String value;

    public YSFUser(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
