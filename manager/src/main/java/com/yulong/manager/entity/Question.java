package com.yulong.manager.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Question {

    private Integer id;
    private String title;
    private String description;
    private Integer user_id;
    private Integer followed;
    private Integer answer_num;
    private Timestamp comm_time;
    private Integer status;
    private String extend;
    private Integer rank;
    private Integer hot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }

    public Integer getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(Integer answer_num) {
        this.answer_num = answer_num;
    }

    public Timestamp getComm_time() {
        return comm_time;
    }

    public void setComm_time(Timestamp comm_time) {
        this.comm_time = comm_time;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", followed=" + followed +
                ", answer_num=" + answer_num +
                ", comm_time=" + comm_time +
                ", status=" + status +
                ", extend='" + extend + '\'' +
                ", rank=" + rank +
                ", hot=" + hot +
                '}';
    }
}
