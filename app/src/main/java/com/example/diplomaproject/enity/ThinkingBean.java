package com.example.diplomaproject.enity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 鱼握拳 on 2018/4/8.
 */

public class ThinkingBean  extends BmobObject{
    private String Content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public BmobRelation getLike() {
        return like;
    }

    public void setLike(BmobRelation like) {
        this.like = like;
    }

    public BmobRelation getShare() {
        return share;
    }

    public void setShare(BmobRelation share) {
        this.share = share;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    private String Comment;
    private BmobRelation like;
    private BmobRelation share;
    private BmobFile image;
}
