package com.yulong.manager.entity;

import java.sql.Date;

public class Announcement {
    private Integer id;
    private String content;
    private Integer type;
    private Date post_time;
    private String extend;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", post_time='" + post_time + '\'' +
                ", extend='" + extend + '\'' +
                ", status=" + status +
                '}';
    }
}
