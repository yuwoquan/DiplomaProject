package com.example.diplomaproject.enity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public class ReadBean extends BmobObject{
    private BmobFile viedo;

    public BmobFile getViedo() {
        return viedo;
    }

    public void setViedo(BmobFile viedo) {
        this.viedo = viedo;
    }

    public BmobFile getJpg() {
        return jpg;
    }

    public void setJpg(BmobFile jpg) {
        this.jpg = jpg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private BmobFile jpg;
    private String detail;
}
