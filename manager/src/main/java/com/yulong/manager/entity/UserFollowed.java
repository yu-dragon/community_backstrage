package com.yulong.manager.entity;

import java.sql.Timestamp;

public class UserFollowed {

    private Integer id;
    private Integer user_id;
    private Integer question_id;
    private Timestamp followed_time;
    private Timestamp first_followed;
    private Integer status;
    private String extend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Timestamp getFollowed_time() {
        return followed_time;
    }

    public void setFollowed_time(Timestamp followed_time) {
        this.followed_time = followed_time;
    }

    public Timestamp getFirst_followed() {
        return first_followed;
    }

    public void setFirst_followed(Timestamp first_followed) {
        this.first_followed = first_followed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "UserFollowed{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", question_id=" + question_id +
                ", followed_time=" + followed_time +
                ", first_followed=" + first_followed +
                ", status=" + status +
                ", extend='" + extend + '\'' +
                '}';
    }
}
